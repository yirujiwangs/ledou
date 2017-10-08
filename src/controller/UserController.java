package controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import model.Admin;
import model.base.BaseResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import service.UserService;
import utils.api.BaseAPI;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 商户账号管理
 */
@Controller
@RequestMapping("/account")
public class UserController {

    @Resource
    private UserService userService;


    /**
     * @return
     */
    @RequestMapping(value = "index.do")
    @ResponseBody
    public String accountManage(HttpSession session ,@RequestBody String param) {

        return userService.accountManage(session, param).toJSONString();
    }

    /**
     * @return
     */
    @RequestMapping(value = "update.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String update(@RequestBody String param, HttpSession session) {
        return userService.updateByModel(param).toJSONString();
    }

    /**
     * delete账号 by phoneNum
     *
     * @param
     * @return
     */
    @RequestMapping(value = "delete.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String delete(@RequestBody String param, HttpSession session) {
        JSONObject json = JSON.parseObject(param);

        String account = json.getString("id");
        String username = session.getAttribute("userTag").toString();
        return userService.delete(account, username).toJSONString();
    }


    @RequestMapping(value = "status.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String changeStatus(@RequestBody String param, HttpSession session) {
        //System.out.println("changeStatus : " + param);
        JSONObject json = JSON.parseObject(param);

        Integer account = json.getInteger("id");
        String status = json.getString("status");
        //System.out.println("accout: " + account + " status: " + status);

        return userService.changeStatus(account, status).toJSONString();
    }

    @RequestMapping(value = "adduser_v1.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String addUser_v1(HttpServletRequest request, HttpSession session
            , RedirectAttributes redirectAttributes) {

        String phonenum = request.getParameter("phoneNum");
        String passwd = request.getParameter("passwd");
        String storeName = request.getParameter("storeName");
        String totalFee = request.getParameter("totalFee");

        Integer maxGroupNum = 0, maxStoreNum = 0;
        if (request.getParameter("maxGroupNum") != null)
            maxGroupNum = Integer.parseInt(request.getParameter("maxGroupNum"));
        if (request.getParameter("maxStoreNum") != null)
            maxStoreNum = Integer.parseInt(request.getParameter("maxStoreNum"));

        String descr = request.getParameter("descr");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("phonenum", phonenum);
        jsonObject.put("passwd", passwd);
        jsonObject.put("storeName", storeName);
        jsonObject.put("totalFee", totalFee);
        jsonObject.put("maxGroupNum", maxGroupNum);
        jsonObject.put("maxStoreNum", maxStoreNum);
        jsonObject.put("descr", descr);

        Admin admin = (Admin) session.getAttribute("proxy");
        if (admin != null) {
            String phoneNum = admin.getPhoneNum();
            int id = admin.getId();

            jsonObject.put("phoneNum", phoneNum);
            jsonObject.put("corporationId", id);
            String str = jsonObject.toString();

            String notify_url = BaseAPI.baseUrl
                    + "/account/alipaynotify_adduser.do";
            String return_url = BaseAPI.BASE_URL;

            jsonObject = new JSONObject();
            jsonObject.put("info", str);
            jsonObject.put("notify_url", notify_url);
            jsonObject.put("return_url", return_url);

            //转接充值业务
            redirectAttributes.addFlashAttribute("json", jsonObject.toString());
            return "redirect:/proxyFinance/toDeposit.do";
        }

        return "web/index";
    }

    @RequestMapping(value = "adduser.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public
    @ResponseBody
    String addUser(@RequestBody String param, HttpSession session) {
        Admin admin = (Admin) session.getAttribute("proxy");

        if (admin != null) {
            int id = admin.getId();
            return userService.add(param, id,admin.getPhoneNum()).toString();
        }
        return null;
    }

    @RequestMapping(value = "bindWxAccount.do", method = RequestMethod.POST)
    public @ResponseBody String bindWxAccount(HttpSession session, @RequestBody String param){
        Admin admin = (Admin) session.getAttribute("proxy");
        JSONObject jsonObject = JSON.parseObject(param);
        BaseResult baseResult = new BaseResult(-1,"获取二维码失败", null);
        //System.out.println("----");
        if(admin != null) {
            //System.out.println("----"+jsonObject.toJSONString());
            String phoneNum = jsonObject.getString("account");
            String qrCodeUrl = userService.userQrCodeUrl(phoneNum, admin.getProxy_token());

            //System.out.println("qrCodeUrl: "+qrCodeUrl);

            if (!qrCodeUrl.equals("")) {
                baseResult = new BaseResult(1, "获取二维码成功", qrCodeUrl);
            }
        }

        return JSON.toJSONString(baseResult);
    }
    /**
     * 无效
     * @param model
     * @return
     *
     * @deprecated
     */
    @RequestMapping(value = "toLogin.do", method = RequestMethod.GET)
    public String toLogin(ModelMap model) {
        //System.out.println("ok");
        return "web/index";
    }

}
