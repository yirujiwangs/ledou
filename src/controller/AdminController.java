package controller;

import cache.ResponseQueueCache;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import model.Admin;
import model.ProxyAccountInfo;
import model.Supervisor;
import model.base.BaseResult;
import model.base.ProxyArea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.AdminService;
import service.ProxyAreaService;
import utils.common.Constant;
import utils.net.CookieUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by lenovo on 2015/12/10.
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @Resource
    private AdminService adminService;

    @Autowired
    private ProxyAreaService proxyAreaService;

    @RequestMapping(value = "superLogin.do")
    public
    @ResponseBody
    String superLogin(HttpSession session, @RequestBody String param) {
        JSONObject json = JSON.parseObject(param);
        String username = json.getString("username");
        String password = json.getString("password");

        BaseResult baseResult = new BaseResult(-1, "登陆失败");

        Supervisor supervisor = adminService.superLogin(username, password);
        if (supervisor != null) {
            supervisor.setPassword(null);
            session.setAttribute(Constant.SESSION_KEY_SUPERVISOR_ACCOUNT, supervisor.getAccount());
            session.setAttribute(Constant.SESSION_KEY_SUPERVISOR_ACCOUNT_INFO, supervisor);

            baseResult = new BaseResult(1, "登陆成功");
        }
        return JSON.toJSONString(baseResult);
    }


    @RequestMapping(value = "login.do")
    @ResponseBody
    public String loginCheck(HttpSession session, @RequestBody String param,HttpServletResponse response,HttpServletRequest request) {
        JSONObject json = JSON.parseObject(param);
        String username = json.getString("username");
        String password = json.getString("password");
        BaseResult baseResult = new BaseResult(-1, "登陆失败");
        Admin admin = adminService.loginCheck(username, password);
        if (admin != null) {
            //System.out.println(admin.getCreateTime());
            admin.setPassword(null);
            session.setAttribute(Constant.SESSION_KEY_PROXY, admin);
            session.setAttribute("userTag", admin.getUsername());
            session.setAttribute("userId", admin.getId());

            JSONObject jsonObject = new JSONObject();

            //返回代理等级
            ProxyArea proxyArea = proxyAreaService.proxyAreaByUtoken(admin.getProxy_token());
            if(proxyArea != null){

                //传递信息到php,此功能已被取消
//                String ptoken = admin.getPtoken();
//                String pathUrl = "device/saveDeviceAreaCode";
//                jsonObject.put("proxy_token",admin.getProxy_token());
//                jsonObject.put("area_code", proxyArea.getArea_rid());
//                jsonObject.put("proxy_level",proxyArea.getProxy_level());
//                AdBaseAPI.executeResult(ptoken, jsonObject.toJSONString(), pathUrl);

                //传递信息到前端
                jsonObject.clear();
                jsonObject.put("username", admin.getUsername());
                jsonObject.put("area_name",proxyArea.getArea_name());
                jsonObject.put("area_rid",proxyArea.getArea_rid());
                jsonObject.put("policy",admin.getPolicy());
                int level = admin.getLevel();
                if (level==0) {
                    jsonObject.put("proxyType","P");
                }else if(level==1){
                    jsonObject.put("proxyType","M");
                }
            }else{
                jsonObject.put("username",admin.getUsername());
                jsonObject.put("area_name","none");
                jsonObject.put("area_rid","none");
                jsonObject.put("proxyType","none");
            }
//            if (admin.getOpenid() == null || admin.getOpenid().equals("")) {
//                baseResult = new BaseResult(0, "登陆成功,未绑定微信",admin.getUsername());
//            } else baseResult = new BaseResult(1, "登陆成功",admin.getUsername());
            //前端显示用户名（修改）
            if (admin.getOpenid() == null || admin.getOpenid().equals("")) {
                baseResult = new BaseResult(0, "登陆成功,未绑定微信",jsonObject);
            } else baseResult = new BaseResult(1, "登陆成功",jsonObject);

            Integer remindMe = json.getInteger("remind");
            if (remindMe!=null&& remindMe ==1){
                //cookie记录
                CookieUtil.updateCookie(Constant.COOKIE_LOGIN_PROXY,request,response, username,null);
            }else {
                CookieUtil.del_cookie(Constant.COOKIE_LOGIN_PROXY,request,response);
            }
        }
        return JSON.toJSONString(baseResult);
    }


    /**
     * 获取代理商账户信息
     *
     * @param httpSession
     * @return
     */
    @RequestMapping(value = "accountInfo.do")
    public
    @ResponseBody
    String getProxyAccountInfo(HttpSession httpSession,HttpServletResponse servletResponse) {
        Admin admin = (Admin) httpSession.getAttribute(Constant.SESSION_KEY_PROXY);
        admin=adminService.getAdminByPhone(admin.getPhoneNum());
        admin.setPassword(null);
        ProxyAccountInfo proxyAccountInfo = adminService.getProxyAccountInfo(admin);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("account", admin);
        jsonObject.put("accountInfo", proxyAccountInfo);

        ResponseQueueCache.putServletResponseCache(admin.getPtoken(), servletResponse);
        httpSession.setAttribute(Constant.SESSION_KEY_PROXY, admin);
        return jsonObject.toString();
    }


    /**
     * @param httpSession
     * @param param
     * @return
     */
    @RequestMapping("getCorporationInfo.do")
    public
    @ResponseBody
    String
    getCorporationInfo(HttpSession httpSession, @RequestBody String param) {
        JSONObject jsonObject = JSON.parseObject(param);
        if (httpSession.getAttribute("supervisor") != null) {
            Integer corporationid = jsonObject.getInteger("corporationid");
            if (corporationid != null) {
                Admin admin = adminService.cutAdmin(corporationid);
                jsonObject = new JSONObject();
                jsonObject.put("proxyInfo", admin);
                return jsonObject.toString();
            }
        }
        jsonObject = new JSONObject();
        jsonObject.put("errorcode", -1);
        return jsonObject.toString();
    }


    /**
     * 重置密码
     * @param httpSession
     * @param param
     * @return
     */
    @RequestMapping(value = "cp.do")
    public
    @ResponseBody
    String updatePsw(HttpSession httpSession, @RequestBody String param) {
        Admin admin = (Admin) httpSession.getAttribute("proxy");
        JSONObject jsonObject = JSON.parseObject(param);
        String psw = jsonObject.getString("psw");
        String oldpsw = jsonObject.getString("oldpsw");

        jsonObject = new JSONObject();
        if (psw != null && psw.trim().length() >= 6) {
            if (adminService.updatePsw(admin, psw, oldpsw)) {
                jsonObject.put("errorcode", 1);
                jsonObject.put("errormsg", "success");
                return jsonObject.toString();
            }
        }
        jsonObject.put("errorcode", -1);
        jsonObject.put("errormsg", "failed");
        return jsonObject.toString();
    }

    /**
     * 更新代理商账户信息
     * @param param
     * @return
     */
    @RequestMapping(value = "updateProxyAccountInfo.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public
    @ResponseBody
    String updateProxyAccountInfo(@RequestBody String param) {
        JSONObject jsonObject = new JSONObject();
        if (param != null) {
            jsonObject = JSON.parseObject(param);
            Integer id = jsonObject.getInteger("id");
            String nickname = jsonObject.getString("nickname");

            String logourl = jsonObject.getString("logourl");
            String address = jsonObject.getString("address");
            String contract_name = jsonObject.getString("contract_name");
            String contract_phone = jsonObject.getString("contract_phone");
            String email = jsonObject.getString("email");

            String wx_name = jsonObject.getString("wx_name");
            String wx_qr_url = jsonObject.getString("wx_qr_url");

            String bank_account_name = jsonObject.getString("bank_account_name");
            String bank_account_num = jsonObject.getString("bank_account_num");
            String alipay_account_name = jsonObject.getString("alipay_account_name");
            String alipay_account_num = jsonObject.getString("alipay_account_num");
            String company_name = jsonObject.getString("company_name");
            String company_num = jsonObject.getString("company_num");
            String bank_name = jsonObject.getString("bank_name");

            ProxyAccountInfo proxyAccountInfo = new ProxyAccountInfo();
            proxyAccountInfo.setId(id);
            proxyAccountInfo.setAddress(address);
            proxyAccountInfo.setNickname(nickname);
            proxyAccountInfo.setLogourl(logourl);
            proxyAccountInfo.setContract_name(contract_name);
            proxyAccountInfo.setContract_phone(contract_phone);
            proxyAccountInfo.setEmail(email);
            proxyAccountInfo.setWx_name(wx_name);
            proxyAccountInfo.setWx_qr_url(wx_qr_url);

            proxyAccountInfo.setBank_name(bank_name);

            proxyAccountInfo.setCompany_name(company_name);
            proxyAccountInfo.setCompany_num(company_num);
            proxyAccountInfo.setBank_account_name(bank_account_name);
            proxyAccountInfo.setBank_account_num(bank_account_num);
            proxyAccountInfo.setAlipay_account_name(alipay_account_name);
            proxyAccountInfo.setAlipay_account_num(alipay_account_num);

            int upid = -1;
            try {
                upid = adminService.updateProxyAccountInfo(proxyAccountInfo);
                //System.out.println(upid);
            } catch (Exception e) {
                e.printStackTrace();
            }
            jsonObject = new JSONObject();
            if (upid > 0) {
                jsonObject.put("errorcode", 0);
                jsonObject.put("errormsg", "更新代理商信息成功");
                return jsonObject.toString();
            }
        }

        jsonObject.put("errorcode", -1);
        jsonObject.put("errormsg", "更新代理商信息失败");
        return jsonObject.toString();
    }

}
