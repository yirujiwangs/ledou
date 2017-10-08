package controller.login;

import cache.BindWxQueueCache;
import cache.QrLoginCache;
import cache.WxLoginQueueCache;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import model.Admin;
import model.base.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.AdminService;
import service.UserService;
import utils.api.AdBaseAPI;
import utils.api.BaseAPI;
import utils.common.BaseQr;
import utils.common.Constant;
import utils.common.LogUtil;
import utils.common.WechatConstansUtil;
import utils.serialnum.SerialNumCreater;
import weixin.popular.api.SnsAPI;
import weixin.popular.bean.SnsToken;
import weixin.popular.bean.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by yeran on 2016/10/3.
 * <p/>
 * 微信扫码登陆
 */

@RequestMapping("/wx")
@Controller
public class WxLoginController {

    @Autowired
    private AdminService adminService;



    @Autowired
    private UserService userService;

    private final boolean InterfaceTest = false;

    @RequestMapping("/{ptoken}/{code}/authCode.do")
    //重定向到微信让用户授权，授权成功携带微信code重定向到 proxyAuth.do
    public String authCode(@PathVariable String ptoken, @PathVariable String code) {
        String redirect_url = BaseAPI.baseUrl + "/wx/proxyAuth.do?ptoken=" + ptoken;
        String url = SnsAPI.connectOauth2Authorize(WechatConstansUtil.AppID, redirect_url, true, code);
        //System.out.println(url);
        return "redirect:" + url;
    }

    @RequestMapping("/{state}/wxlogin")
    //@TODO: 重定向到微信让用户授权，授权成功携带微信code重定向到proxyAuth.do
    public String wxloginUrl(@PathVariable String state,ModelMap modelMap) {
        Long time = QrLoginCache.getAviTime(state);
        //System.out.println("loginstate: "+state + " "+ "logintime: "+time);
        String msg="系统繁忙，稍后再试";
        if (time == null){
            msg ="参数丢失";

            modelMap.addAttribute("errormsg", new BaseResult(-1, msg));
//            modelMap.addAttribute("errormsg", msg);
            return "web/wxinfo";
        }

        if (System.currentTimeMillis()/1000>time){
            msg ="链接超时";
            modelMap.addAttribute("errormsg", new BaseResult(-1, msg));
//            modelMap.addAttribute("errormsg", msg);
            return "web/wxinfo";
        }

        String redirect_url = BaseAPI.baseUrl + "/wx/wxLogin.do";
        String url = SnsAPI.connectOauth2Authorize(WechatConstansUtil.AppID, redirect_url, true, state);
        //System.out.println(url);
        return "redirect:" + url;
    }


    /**
     * MNORRL/3845861/858d24b2ed753f776e39e8f462f6d182
     * 微信扫码登陆
     * <p/>
     * 前端通过微信wxLogin.js直接显示微信登陆二维码，并将code等信息，返回后台，避免了session域的脱离
     *
     * @return
     */
    @RequestMapping("/wxLogin.do")
    public String wxLogin(HttpSession httpSession, HttpServletRequest request, ModelMap modelMap) {
        String state = request.getParameter("state");
        String code = request.getParameter("code");
        LogUtil.log(WxLoginController.class, LogUtil.LogType.INFO, "进入授权回调");
        String msg = "登录失败~~~";
        if (code != null) {
            //System.out.println("code="+code);
            SnsToken snsToken = SnsAPI.oauth2AccessToken(WechatConstansUtil.AppID
                    , WechatConstansUtil.AppSecret, code);
            if (snsToken != null) {
                String openId = snsToken.getOpenid();
                if (openId != null) {
                    //System.out.println(openId);
                    String account = adminService.wxLogin(httpSession, state, openId, null);
                    if (account != null) {
                        //登陆成功
                        LogUtil.log(WxLoginController.class, LogUtil.LogType.SUCCESS,
                                "登陆成功", "account=" + account);
                        WxLoginQueueCache.putOpenIdCache(state,openId);
                        msg = "登陆成功";
                        modelMap.addAttribute("errormsg", new BaseResult(1, msg));

                    }else{
                        msg = "无此用户，登录失败";
                        modelMap.addAttribute("errormsg", new BaseResult(-1, msg));
                        WxLoginQueueCache.putOpenIdCache(state,"NoAccount");
                    }

                }else{
                    LogUtil.log(WxLoginController.class, LogUtil.LogType.ERROR, "openId获取失败");
                    msg = "openId 获取失败";
                    modelMap.addAttribute("errormsg", new BaseResult(-1, msg));
                }
            } else {
                LogUtil.log(WxLoginController.class, LogUtil.LogType.ERROR, "access_token获取失败");
                msg = "登录异常";
                modelMap.addAttribute("errormsg", new BaseResult(-1, msg));

            }
        }
//        modelMap.addAttribute("errormsg", msg);
        return "web/wxinfo";
    }

    /**
     * 判断此代理商是否已经绑定了微信，如果已经绑定了，则提示绑定失败以及原因，如果
     * 没有绑定，则将openid绑定到此代理商
     * <p/>
     * state代表了代理商的proxy_token
     *
     * @param request
     * @return
     */
    @RequestMapping("/proxyAuth.do")
    public String proxyAuth(HttpServletRequest request, ModelMap modelMap) throws IOException {
        String state = request.getParameter("state");
        String ptoken = request.getParameter("ptoken");

        String code = request.getParameter("code");
        String msg = "绑定失败~~~";
        modelMap.addAttribute("errormsg", new BaseResult(-1, msg));
        if (code != null) {
            SnsToken snsToken = SnsAPI.oauth2AccessToken(WechatConstansUtil.AppID, WechatConstansUtil.AppSecret, code);
            if (snsToken != null) {
                String openId = snsToken.getOpenid();
                if (openId != null) {
                    if (adminService.bindWx(state, ptoken, openId)) {
                        User user = SnsAPI.userinfo(snsToken.getAccess_token(), openId, null);
                        if (user != null) {
                            //System.out.println(JSON.toJSONString(user));
                            //记录基本微信信息
                            if (adminService.updateProxyAccountWxInfo(state, user)) {
                                //绑定成功
                                msg = "绑定成功";
                                modelMap.addAttribute("errormsg", new BaseResult(1, msg));
                                BindWxQueueCache.putBindOpenIdCache(state, openId);
                                //System.out.println(msg);
                            }
                        }
                    }
                }
            }
        }
//        System.out.println(msg);
//        modelMap.addAttribute("errormsg", msg);
        return "web/wxinfo";
    }


    @RequestMapping("/{ptoken}/{phoneNum}/userAuthCode.do")
    public String userAuthCode(@PathVariable String ptoken, @PathVariable String phoneNum) {
        String redirect_url = BaseAPI.baseUrl + "/wx/userAuth.do?ptoken=" + ptoken;
        String url = SnsAPI.connectOauth2Authorize(WechatConstansUtil.AppID, redirect_url, true, phoneNum);//@TODO url 作用？
        //System.out.println(url);
        return "redirect:" + url;
    }


    /**
     * 判断此商户是否已经绑定了微信，如果已经绑定了，则提示绑定失败以及原因，如果
     * 没有绑定，根据获取到的openid去PHP端获取用户token。
     * <p/>
     * state代表了代理商的proxy_token
     *
     * @param request
     * @return
     */
    @RequestMapping("/userAuth.do")
    public String userAuth(HttpServletRequest request, ModelMap modelMap) {
        String state = request.getParameter("state");//@TODO state :phoneNum#proxyToken
        String[] states = state.split("_");
        String phoneNum = states[0];
        String proxyToken = states[1];

        String code = request.getParameter("code");
        String msg = "绑定失败";

        model.User user = userService.getUserByPhone(phoneNum);
        if (user.getUtoken() != null && !user.getUtoken().isEmpty()) {
            modelMap.addAttribute("errormsg",  new BaseResult(-1, "账户已绑定"));
            return "web/wxinfo";
        }

        Admin admin = adminService.getAdminByProxyToken(proxyToken);
        if(admin==null){
            modelMap.addAttribute("errormsg",  new BaseResult(-1, "无此代理商信息"));
            return "web/wxinfo";
        }
        String ptoken = admin.getPtoken();
        if (code != null) {
            SnsToken snsToken = SnsAPI.oauth2AccessToken(WechatConstansUtil.AppID, WechatConstansUtil.AppSecret, code);
            if (snsToken != null) {
                String res;
                String openId = snsToken.getOpenid();
                //System.out.println("userOpenId: " + openId);
                if (openId != null) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("openId", openId);
                    jsonObject.put("proxyToken", proxyToken);

                    //根据openId去PHP端获取uToken；
                    //urlpath: "wx/get/userToken
                    if (InterfaceTest) {
                        jsonObject.put("uToken", "12345abcde");
                        res = jsonObject.toJSONString();
                    } else {
                        res = AdBaseAPI.executeResult(ptoken, jsonObject.toJSONString(), "wxuserinfo/useropenid");
                    }

                    String uToken = JSON.parseObject(res).getString("utoken");
                    if (userService.bindToken(phoneNum, uToken)) {
                        msg = "绑定成功";
                    }
                }
            }
        }
        modelMap.addAttribute("errormsg",  new BaseResult(1, msg));
        return "web/wxinfo";
    }


    /**
     * 获取用户的绑定二维码
     *
     * @param httpSession
     * @return
     */
    @RequestMapping("/qrCode.do")
    public
    @ResponseBody
    String qrCode(HttpSession httpSession) {
        Admin admin = (Admin) httpSession.getAttribute(Constant.SESSION_KEY_PROXY);
        String wxBindQrUrl = BaseQr.qrUrl(BaseAPI.baseUrl + "/wx/" + Constant.LEDOUYA_PTOKEN + "/" + admin.getProxy_token() + "/authCode.do");
        return JSONObject.toJSONString(new BaseResult(1, "获取二维码成功", wxBindQrUrl));
    }


    /**
     * 获取用户的二维码
     *
     * @return
     */
    //TODO add random code
    @RequestMapping("/loginQrCode.do")
    public
    @ResponseBody
    //String loginQrCode(HttpSession httpSession) {
    String loginQrCode() {
        String state = SerialNumCreater.nommonSerialCreate(10);
        long availableSchedule = 5*60;//2分钟失效期
        long availableTime =availableSchedule + System.currentTimeMillis()/1000;

        //httpSession.setAttribute("loginstate",state+"@"+availableTime);
        QrLoginCache.addAviTimeCache(state,availableTime);
        String wxBindQrUrl = BaseQr.qrUrl(BaseAPI.baseUrl + "/wx/" + state + "/wxlogin");

        return JSONObject.toJSONString(new BaseResult(1, "获取二维码成功", wxBindQrUrl));
    }


    /**
     * 用户绑定二维码长轮询
     * 
     * @param httpSession
     * @return
     * @throws InterruptedException
     */
    @RequestMapping("/requestState.do")
    public
    @ResponseBody
    String requestBindState(HttpSession httpSession) throws InterruptedException {
        try {
            Admin admin = (Admin) httpSession.getAttribute(Constant.SESSION_KEY_PROXY);
            long startTime = System.currentTimeMillis();
            long lostTime = 60 * 1000 + startTime;
            while (System.currentTimeMillis() < lostTime) {
                //执行轮询，等待绑定
                String openId = BindWxQueueCache.getBindOpenId(admin.getProxy_token());
                if (openId != null && !openId.isEmpty()) {
                    BindWxQueueCache.deleteBindOpenId(admin.getProxy_token());
                    return JSONObject.toJSONString(new BaseResult(1, "绑定成功", openId));
                }
                Thread.sleep(3 * 1000);
            }
            return JSONObject.toJSONString(new BaseResult(0, "二维码失效"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSONObject.toJSONString(new BaseResult(-1, "绑定失败"));
    }


    /**
     * 用户登录二维码长轮询
     *
     * @return
     * @throws InterruptedException
     */
    @RequestMapping("/requestLogin.do")
    public
    @ResponseBody
    String requestLoginState(@RequestBody String param,HttpSession httpSession) {

        try {
            JSONObject jsonObject = JSONObject.parseObject(param);
            String state = jsonObject.getString("state");
            //System.out.println("用户登录state： "+ state);


            if (state==null || state.length() < 10) {
                return JSONObject.toJSONString(new BaseResult(-1, "非法访问"));
            }
            long startTime = System.currentTimeMillis();
            long lostTime = 60 * 1000 + startTime;
            while (System.currentTimeMillis() < lostTime) {
                //执行轮询，等待绑定
                String openId = WxLoginQueueCache.getOpenId(state);

                if (openId != null && !openId.isEmpty()) {
                    if(openId.equals("NoAccount")){
                        return JSONObject.toJSONString(new BaseResult(-1, "无此账户"));
                    }

                    //System.out.println("用户登录openId: "+openId);
                    BindWxQueueCache.deleteBindOpenId(state);

                    String account = adminService.wxLogin(httpSession, state, openId, null);
                    if (account != null) {
                        //登陆成功
                        LogUtil.log(WxLoginController.class, LogUtil.LogType.SUCCESS,
                                "登陆成功", "account=" + account);

                    }
//
                    return JSONObject.toJSONString(new BaseResult(1, "登录成功", account));
                }
                Thread.sleep(3 * 1000);
            }
            return JSONObject.toJSONString(new BaseResult(0, "二维码失效"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSONObject.toJSONString(new BaseResult(-1, "登录失败"));
    }

}


