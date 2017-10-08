package controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import dao.ProxyFinanceRecordMapper;
import model.Admin;
import model.ProxyFinance;
import model.ProxyFinanceRecord;
import model.base.BaseResult;
import org.apache.commons.httpclient.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import utils.finance.AlipayRequest;
import service.ProxyFinanceService;
import utils.common.DividePageUtil;
import utils.common.LogUtil;
import utils.finance.Alipay;
import utils.finance.FinanceUtil;
import utils.finance.UtilDate;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yeran on 2016/8/8.
 * <p/>
 * 代理资产管理
 *
 * @author yeran
 */

@RequestMapping("/proxyFinance")
@Controller
public class ProxyFinanceController {

    @Autowired
    ProxyFinanceService proxyFinanceService;

    @Autowired
    ProxyFinanceRecordMapper proxyFinanceRecordMapper;


    @Autowired
    private AlipayRequest aliHttpRequest;

    @RequestMapping(value = "finance.do")
    @ResponseBody
    public String getFinance(HttpSession httpSession
            , HttpServletResponse response) throws IOException {

        Admin admin = (Admin) httpSession.getAttribute("proxy");
        if (admin == null) {
            response.sendError(401, "当前session已失效，重新登陆");
            return null;
        }
        String phone = admin.getPhoneNum();

        ProxyFinance proxyFinance = proxyFinanceService.selectProxyFinanceByPhone(phone);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("proxyFinance", proxyFinance);

        return jsonObject.toString();
    }

    /**
     * 申请提现结算
     *
     * @param httpSession
     * @param param
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "refund.do")
    @ResponseBody
    public String refundApply(HttpSession httpSession
            , @RequestBody String param
            , HttpServletResponse response) throws IOException {
        BaseResult baseResult = new BaseResult(-1, "申请失败");

        Admin admin = (Admin) httpSession.getAttribute("proxy");
        if (admin != null) {
            //System.out.println("refund----");
            try {
                JSONObject jsonObject = JSON.parseObject(param);
                Double money = 0.0;
                if (jsonObject.getString("refund") != null)
                    money = Double.parseDouble(jsonObject.getString("refund"));

                if (money <= 0) {
                    baseResult.setErrmsg("提现金额格式错误");
                    return JSONObject.toJSONString(baseResult);
                }

                String phone = admin.getPhoneNum();
                money = FinanceUtil.Yuan2Fen(money);
                //System.out.println(money + "---");

                //获取用户可以提现资产
                Integer result = proxyFinanceService.refundApply(phone, money, jsonObject);
                if (result == 1) {
                    LogUtil.log(ProxyFinanceController.class,
                            LogUtil.LogType.SUCCESS,
                            "[结算申请成功]-phone=" + phone + "/money=" + money + "--time:"
                                    + DateUtil.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
                    baseResult.setErrcode(BaseResult.RESULT_SUCCESS);
                    baseResult.setErrmsg("申请结算成功，请耐心等待处理");

                } else if (result == 0) {
                    LogUtil.log(ProxyFinanceController.class,
                            LogUtil.LogType.WARN,
                            "[结算申请失败=>余额不足]-phone=" + phone + "/money=" + money + "--time:"
                                    + DateUtil.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
                    baseResult.setErrcode(BaseResult.RESULT_ERROR);
                    baseResult.setErrmsg("余额不足，无法进行提现结算！");
                } else {
                    baseResult.setErrcode(BaseResult.RESULT_ERROR);
                    baseResult.setErrmsg("申请结算失败，请稍后再试，或联系工作人员");
                }

                return JSONObject.toJSONString(baseResult);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return JSONObject.toJSONString(baseResult);
    }

    /**
     * 获取代理商财务详情
     * 结算记录
     *
     * @author yeran
     * @time 2016年8月12日10:05:18
     */
    @RequestMapping(value = "getFinanceDetail.do", produces = "application/json;charset=UTF-8")
    public
    @ResponseBody
    String getFinanceDetails(HttpSession httpSession
            , @RequestBody String param
            , HttpServletResponse response) throws IOException, ParseException {
        Admin admin = (Admin) httpSession.getAttribute("proxy");
        if (admin == null) {
            response.sendError(401, "当前session已失效，请重新登陆");
            return null;
        }
        String phone = admin.getPhoneNum();
        JSONObject jsonObject = JSON.parseObject(param);
        Integer type = jsonObject.getInteger("type");
        Integer state = jsonObject.getInteger("state");

        Date startDate = null, endDate = null;

        String startDateS = jsonObject.getString("startTime");
        if (startDateS != null && startDateS.trim().length() >= 10) {
            startDate = new SimpleDateFormat("yyyy-MM-dd").parse(startDateS);
        }
        String endDateS = jsonObject.getString("endTime");
        if (endDateS != null && endDateS.trim().length() >= 10) {
            endDate = new SimpleDateFormat("yyyy-MM-dd").parse(endDateS);
        }

        Integer startPage = jsonObject.getInteger("startPage");
        Integer pageSize = jsonObject.getInteger("pageSize");

        List<ProxyFinanceRecord> proxyFinanceRecords = proxyFinanceService.getProxyFinanceRecord
                (phone, type, startDate, endDate, state, startPage, pageSize);

        jsonObject = new JSONObject();
        if (startPage != null && startPage == 1) {
            //获取总页数
            int pages = proxyFinanceService.countProxyFinanceRecord(phone, type, startDate, endDate, state);
            jsonObject.put("pages", DividePageUtil.getPages(pages, pageSize));
        }
        jsonObject.put("financeRecords", proxyFinanceRecords);

        return jsonObject.toString();
    }

    @RequestMapping(value = "refundDetail.do")
    public
    @ResponseBody
    String getRefundDetail(HttpSession httpSession, @RequestBody String param) {
        JSONObject jsonObject = JSON.parseObject(param);
        Integer id = jsonObject.getInteger("id");

        jsonObject = new JSONObject();
        ProxyFinanceRecord proxyFinanceRecord = new ProxyFinanceRecord();
        proxyFinanceRecord.setId(id);
        proxyFinanceRecord = proxyFinanceService.getFinanceRecordDetail(proxyFinanceRecord);

        jsonObject.put("proxyFinanceRecord", proxyFinanceRecord);

        return jsonObject.toString();
    }

    /**
     * 充值接口
     *
     * @param session
     * @param param
     * @param map
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/toDeposit.do")
    public String depositing(HttpSession session, @ModelAttribute("json") JSONObject param,
                             ModelMap map, HttpServletResponse response) throws IOException {
        Admin user = (Admin) session.getAttribute("proxy");
        try {
            response.setCharacterEncoding("UTF-8");
            if (user == null) {
                response.sendError(401, "当前session已失效，请重新登陆");
                return null;
            }
            String extra_common_param = param.getString("info");

            String total_fee = param.getString("totalFee");
            String body = param.getString("remark");
            String out_trade_no = param.getString("out_trade_no");

            String payment_type = "1";
            String notify_url = param.getString("notify_url");
            String return_url = param.getString("return_url");

            if (out_trade_no == null) {
                out_trade_no = FinanceUtil.outTradeNo();
            }
            String subject = "充值到乐豆呀平台";

            Map<String, String> sParaTemp = new HashMap<>();
            sParaTemp.put("service", "create_direct_pay_by_user");// 接口服务----即时到账
            sParaTemp.put("partner", Alipay.partner); // PID
            sParaTemp.put("_input_charset", Alipay.input_charset);
            sParaTemp.put("sign_type", Alipay.sign_type);
            sParaTemp.put("sign", ""); // sign是生成加密串
            sParaTemp.put("payment_type", payment_type); // 支付类型
            sParaTemp.put("notify_url", notify_url); // 异步通知页面
            sParaTemp.put("return_url", return_url); // 页面跳转同步通知页面
            sParaTemp.put("out_trade_no", out_trade_no);
            sParaTemp.put("seller_id", Alipay.seller_id); // 我公司支付宝账号id
            sParaTemp.put("subject", subject);
            sParaTemp.put("total_fee", total_fee);
            sParaTemp.put("body", body); // 充值的备注
            sParaTemp.put("extra_common_param", extra_common_param);//extra_common_param
            String htmlText = aliHttpRequest.buildRequest(sParaTemp, "post", "充值");
            map.addAttribute("showString", htmlText);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "web/views/pay/skip";
    }


    /**
     * 提供给前端直接调用的支付接口
     *
     * @param session
     * @param extra_common_param
     * @param total_fee
     * @param body
     * @param notify_url
     * @param return_url
     * @param map
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/v2/toDeposit.do", method = RequestMethod.POST)
    public String depositing_direct(HttpSession session
            , @RequestHeader(value = "key", required = true) String key
            , @RequestParam("time") Integer time
            , @RequestParam("info") String extra_common_param
            , @RequestParam("info") String total_fee, @RequestParam("info") String body
            , @RequestParam("info") String notify_url
            , @RequestParam("info") String return_url,
                                    ModelMap map, HttpServletResponse response) throws IOException {
        if (!FinanceUtil.havaPayRight(key, time)) {
            return "web/views/400";
        }
        Admin user = (Admin) session.getAttribute("proxy");
        try {
            response.setCharacterEncoding("UTF-8");
            if (user == null) {
                response.sendError(401, "当前session已失效，请重新登陆");
                return null;
            }
            UtilDate utilDate = new UtilDate();

            String payment_type = "1";

            String out_trade_no = utilDate.getOrderNum()
                    + Thread.currentThread().getId() + utilDate.getThree();
            String subject = "充值到乐豆呀平台";

            Map<String, String> sParaTemp = new HashMap<String, String>();
            sParaTemp.put("service", "create_direct_pay_by_user");// 接口服务----即时到账
            sParaTemp.put("partner", Alipay.partner); // PID
            sParaTemp.put("_input_charset", Alipay.input_charset);
            sParaTemp.put("sign_type", Alipay.sign_type);
            sParaTemp.put("sign", ""); // sign是生成加密串
            sParaTemp.put("payment_type", payment_type); // 支付类型
            sParaTemp.put("notify_url", notify_url); // 异步通知页面
            sParaTemp.put("return_url", return_url); // 页面跳转同步通知页面
            sParaTemp.put("out_trade_no", out_trade_no);
            sParaTemp.put("seller_id", Alipay.seller_id); // 我司支付宝账号id
            sParaTemp.put("subject", subject);
            sParaTemp.put("total_fee", total_fee);
            sParaTemp.put("body", body); // 充值的备注
            sParaTemp.put("extra_common_param", extra_common_param);//extra_common_param
            String htmlText = aliHttpRequest.buildRequest(sParaTemp, "post", "充值");
            map.addAttribute("showString", htmlText);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "web/views/pay/skip";
    }

}
