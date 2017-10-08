package controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import model.*;
import model.base.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import service.PayService;
import service.ProxyAccountService;
import service.UserService;
import utils.api.BaseAPI;
import utils.common.BaseQr;
import utils.common.Constant;
import utils.common.DividePageUtil;
import weixin.popular.bean.paymch.UnifiedorderResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by yeran on 2016/8/11.
 */

@Controller
@RequestMapping("/proxyAccount")
public class ProxyAccountController {


    @Autowired
    ProxyAccountService proxyAccountService;


    @Autowired
    private UserService userService;

    @Autowired
    private PayService payService;



    @RequestMapping("/buy.do")
    public BaseResult deviceBuy(@RequestBody String param_s,HttpServletRequest request, HttpSession httpSession) throws UnsupportedEncodingException {
        BaseResult baseResult = new BaseResult(-1, "购买异常");
        Admin admin = (Admin) httpSession.getAttribute(Constant.SESSION_KEY_PROXY);
        if (admin != null) {

            String utoken = admin.getProxy_token();
            JSONObject params = JSONObject.parseObject(param_s);
            String type = params.getString("accountType");
            String amount = params.getString("num");
            Integer unitPrice = params.getInteger("unitPrice");
            Integer ruleId   =  params.getInteger("ruleId");

            Integer totalPrice =  params.getInteger("totalPrice");
            String remark = request.getParameter("remark");

            if (ruleId==null || type==null || unitPrice==null
                    || amount==null || totalPrice==null){
                baseResult.setErrmsg("参数缺失");
                return baseResult;
            }

            StoreAccountBuyRecord storeAccountBuyRecord  =  proxyAccountService.buyStoreAccount(utoken,
                    Integer.parseInt(amount),
                    unitPrice,
                    totalPrice,
                    ruleId, remark);

            if (storeAccountBuyRecord==null){
                return baseResult;
            }


            String notify_url = BaseAPI.baseUrl+"/payback/wx/pc/accountBuy.do";
            String payType="WX";
            String body="乐豆呀红包盒购买商户账户";
            Object o = payService.payUrl(utoken,payType, storeAccountBuyRecord.getPartner_trade_no()
                    , body, storeAccountBuyRecord.getTotal_fee() + "",
                    request.getLocalAddr(), notify_url, "NATIVE", null, storeAccountBuyRecord.getPartner_trade_no(),
                    "WEB", storeAccountBuyRecord.getComment());
            if (o!=null) {

                UnifiedorderResult unifiedorderResult = (UnifiedorderResult) o;
                if("SUCCESS".equals(unifiedorderResult.getReturn_code())){

                    baseResult.setErrcode(BaseResult.RESULT_SUCCESS);
                    String codeUrl = unifiedorderResult.getCode_url();
                    String payUrl = BaseQr.qrUrl(codeUrl);
                    baseResult.setErrmsg("预订单成功");
                    baseResult.setObject(payUrl);
                }else{

                    String errMsg = unifiedorderResult.getErr_code_des();
                    String errCode = unifiedorderResult.getErr_code();
                    baseResult.setErrcode(BaseResult.RESULT_ERROR);
                    baseResult.setErrmsg(errCode+"|"+errMsg);
                }
            }

            return baseResult;
        }

        return baseResult;

    }



    /**
     * 代理商购买账号
     *
     * @return
     */
    @RequestMapping(value = "buyAccount.do")
    public String buyAccount(HttpServletRequest request, HttpSession httpSession, RedirectAttributes redirectAttributes) {
        Admin admin = (Admin) httpSession.getAttribute(Constant.SESSION_KEY_PROXY);
        if (admin != null) {
            String type = request.getParameter("accountType");
            String num = request.getParameter("num");
            String remark = request.getParameter("remark");

            int normal_num = 0, platform_num = 0;
            if ("normal".equals(type)) {
                normal_num = Integer.parseInt(num);
            } else if ("platform".equals(type)) {
                platform_num = Integer.parseInt(num);
            }

            JSONObject jsonObject = new JSONObject();

            try {
                double total_fee = userService.getValue(normal_num, platform_num);
                //System.out.println("total_fee==" + total_fee);
                if (total_fee > 0) {
                    int updateId = userService.canBuyUserThenBuy(admin.getPhoneNum(), normal_num, platform_num);
                    if (updateId > -1) {
                        String notify_url = BaseAPI.baseUrl+"/proxyAccount/alipaynotify_buyusers.do";
                        String return_url = BaseAPI.BASE_URL;

                        jsonObject.put("totalFee", total_fee);
                        jsonObject.put("notify_url", notify_url);
                        jsonObject.put("return_url", return_url);
                        jsonObject.put("remark", remark);

                        jsonObject.put("info", updateId + "#" + admin.getPhoneNum());

                        redirectAttributes.addFlashAttribute("json", jsonObject);

                        return "redirect:/proxyFinance/toDeposit.do";
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "web/views/400";
    }


    /**
     * 更改代理商信息，包括可用账号库存数，备注信息
     *
     * @param param
     * @return
     */
    @RequestMapping("update/account")
    public
    @ResponseBody
    String updateProxyInfo(@RequestBody String param) {
        JSONObject jsonObject = JSON.parseObject(param);
        Integer accountNum = jsonObject.getInteger("account_num");
        String descr = jsonObject.getString("remark");
        String proxyToken = jsonObject.getString("proxy_token");
        String account = jsonObject.getString("account");
        BaseResult baseResult = new BaseResult(-1, "修改代理商信息失败");
        if (proxyAccountService.updateProxyAccountInfo(accountNum, descr, account)) {
            //修改代理商信息成功
            baseResult = new BaseResult(1, "修改代理商信息成功");
        }
        return JSON.toJSONString(baseResult);
    }


    /**
     * @param param
     * @return
     * @descr 修改运营商状态 禁用或者解封（账号管理页面）
     */
    @RequestMapping(value = "update/status",
            method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String updateStatus(@RequestBody String param) {
        JSONObject json = JSON.parseObject(param);
        String account = json.getString("account");
        String status = json.getString("status");
        BaseResult baseResult = new BaseResult(-1,"更改失败");
        if (proxyAccountService.updateStatus(account, status)) {
            baseResult = new BaseResult(1,"更改成功");
        }
        return JSON.toJSONString(baseResult);
    }

    /**
     *
     * 支付宝支付回调接口，更改代理商账号数量信息
     * @param req
     * @return
     * @deprecated
     */
    @RequestMapping(value = "alipaynotify_buyusers.do")
    public
    @ResponseBody
    String notify_BuyUsers(HttpServletRequest req) {
        String result = proxyAccountService.notifyBuyUserService(req);
        return result;
    }

    /**
     * @param httpSession
     * @param param
     * @return
     * @throws ParseException
     */
    @RequestMapping(value = "accountBuyRecord.do")
    public
    @ResponseBody
    String getProxyAccountBuyRecord(HttpSession httpSession, @RequestBody String param) throws ParseException {

        Admin admin = (Admin) httpSession.getAttribute("proxy");
        if (admin != null) {
            String phone = admin.getPhoneNum();

            JSONObject jsonObject = JSON.parseObject(param);

            Date startTime = null, endTime = null;
            String startTimeString = jsonObject.getString("startTime");
            if (startTimeString != null && startTimeString.trim().length() > 4) {
                startTime = new SimpleDateFormat("yyyy-MM-dd").parse(startTimeString);
            }
            String endTimeString = jsonObject.getString("endTimeString");
            if (endTimeString != null && endTimeString.trim().length() > 4) {
                endTime = new SimpleDateFormat("yyyy-MM-dd").parse(startTimeString);
            }

            Integer startPage = jsonObject.getInteger("startPage");
            Integer pageSize = jsonObject.getInteger("pageSize");

            List<ProxyAccountBuyRecord> proxyAccountBuyRecords = proxyAccountService.getProxyAccountBuyRecords(phone, startTime, endTime, startPage, pageSize);
            jsonObject = new JSONObject();
            if (startPage != null && startPage == 1) {
                int pages = proxyAccountService.countProxyAccountBuyRecords(phone, startTime, endTime);
                jsonObject.put("pages", DividePageUtil.getPages(pages, pageSize));
            }
            jsonObject.put("accountbuyrecord", proxyAccountBuyRecords);
            return jsonObject.toString();
        }
        return null;
    }

    /**
     * 代理商账号信息
     *
     * @return
     */
    @RequestMapping(value = "proxyAccount.do")
    public
    @ResponseBody
    String getProxyAccount(HttpSession httpSession) {
        Admin admin = (Admin) httpSession.getAttribute("proxy");
        String phone = admin.getPhoneNum();
        ProxyAccountCount proxyAccountCount = proxyAccountService.getCertainProxyAccountCountInfo(phone);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("proxyAccountCount", proxyAccountCount);

        return jsonObject.toString();
    }
}
