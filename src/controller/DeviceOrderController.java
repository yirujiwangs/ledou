package controller;

import com.alibaba.fastjson.JSONObject;
import model.Admin;
import model.DeviceBuyRecord;
import model.Logistics;
import model.base.BaseResult;
import model.base.DivideBaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import service.DeviceService;
import service.LogisticsService;
import service.PayService;
import utils.api.BaseAPI;
import utils.common.BaseQr;
import utils.common.Constant;
import weixin.popular.bean.paymch.UnifiedorderResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by yeran on 2017/4/27.
 */
@RestController
@RequestMapping(value = "/device/order")
public class DeviceOrderController {

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private LogisticsService logisticsService;

    @Autowired
    PayService payService;

    @RequestMapping("/record.do")
    public BaseResult orderRecord(HttpSession session,@RequestBody String param){
        Admin admin = (Admin) session.getAttribute(Constant.SESSION_KEY_PROXY);

        BaseResult baseResult = new BaseResult(BaseResult.RESULT_ERROR, "获取列表失败");

        if(admin!=null){
            String utoken = admin.getProxy_token();

            JSONObject jsonObject = JSONObject.parseObject(param);
            Integer startPage = jsonObject.getInteger("startPage");
            Integer pageSize = jsonObject.getInteger("pageSize");
            String tradeNo = jsonObject.getString("tradeNo");

            if (startPage==null)
                startPage=1;
            if (pageSize==null)
                pageSize=10;
            Integer[] pages = new Integer[1];
            List<DeviceBuyRecord> deviceBuyRecords = deviceService.deviceBuyRecord(utoken, startPage, pageSize, tradeNo, pages);

            baseResult.setErrcode(BaseResult.RESULT_SUCCESS);
            baseResult.setErrmsg("获取列表成功");
            DivideBaseResult<DeviceBuyRecord> divideBaseResult = new DivideBaseResult<DeviceBuyRecord>();
            divideBaseResult.setPages(pages[0]);
            divideBaseResult.setPage(startPage);
            divideBaseResult.setPageSize(pageSize);
            divideBaseResult.setList(deviceBuyRecords);

            baseResult.setObject(divideBaseResult);
        }

        return baseResult;

    }

    @RequestMapping("/delivery.do")
    public BaseResult deliveryAddress(@RequestParam(value = "dNo") String dNo){
        BaseResult baseResult = new BaseResult(-1, "获取物流信息失败");
        if (dNo!=null){
            Logistics logistics = logisticsService.getNowLogisticByNo(dNo);
            if (logistics!=null){
                baseResult.setErrcode(BaseResult.RESULT_SUCCESS);
                baseResult.setErrmsg("获取物流信息成功");
                baseResult.setObject(logistics);
                return baseResult;
            }
        }
        return baseResult;
    }

    @RequestMapping("/buy.do")
    public BaseResult deviceBuy(@RequestBody String param_s,HttpServletRequest request, HttpSession httpSession) throws UnsupportedEncodingException {
        BaseResult baseResult = new BaseResult(-1, "购买异常");
        Admin admin = (Admin) httpSession.getAttribute(Constant.SESSION_KEY_PROXY);
        if (admin != null) {
            String utoken = admin.getProxy_token();
            JSONObject params = JSONObject.parseObject(param_s);
            String deviceType = params.getString("deviceType");
            String amount = params.getString("amount");
            Integer unitPrice = params.getInteger("unitPrice");
            Integer policyReduct = params.getInteger("policyReduct");
            Integer originalPrice = params.getInteger("buyPrice");
            Integer ruleId   =  params.getInteger("ruleId");
            String address =  params.getString("address");
            String contact =  params.getString("contact");
            String consignee =  params.getString("name");
            Integer totalPrice =  params.getInteger("totalPrice");
            String reason = params.getString("reason");


            if (ruleId==null || deviceType==null || unitPrice==null
                    || amount==null || totalPrice==null
                    || address==null || contact==null || consignee==null || reason==null){
                baseResult.setErrmsg("参数缺失");
                return baseResult;
            }

            DeviceBuyRecord deviceBuyRecord =  deviceService.deviceOrder(utoken, deviceType,
                    Integer.parseInt(amount),
                    originalPrice,
                    policyReduct,
                    unitPrice,
                    totalPrice,
                    ruleId,
                    address, contact, consignee,null,"WX",null,reason);
            if (deviceBuyRecord==null){
                return baseResult;
            }

            if(totalPrice==0){
                baseResult.setErrcode(1);
                baseResult.setErrmsg("订单提交成功");
                return baseResult;
            }


            String notify_url = BaseAPI.baseUrl+"/payback/wx/pc/deviceBuy.do";

            String payType="WX";
            String body="乐豆呀红包盒";
            Object o = payService.payUrl(utoken,payType, deviceBuyRecord.getPartner_trade_no(), body, deviceBuyRecord.getTotal_fee() + "",
                    request.getLocalAddr(), notify_url, "NATIVE", null, deviceBuyRecord.getPartner_trade_no(),
                    "WEB", deviceBuyRecord.getComment());
            if (o!=null) {
                UnifiedorderResult unifiedorderResult = (UnifiedorderResult) o;
                if("SUCCESS".equals(unifiedorderResult.getReturn_code())){

                    baseResult.setErrcode(BaseResult.RESULT_SUCCESS);
                    String codeUrl = unifiedorderResult.getCode_url();
                    String payUrl = BaseQr.qrUrl(codeUrl);
                    baseResult.setErrmsg(deviceBuyRecord.getPartner_trade_no());
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

    @RequestMapping("/updateLogistic")
    public BaseResult updateOrderLogistic(@RequestBody String param){
        BaseResult baseResult = new BaseResult(BaseResult.RESULT_ERROR, "修改订单物流信息失败");
        JSONObject jsonObject = JSONObject.parseObject(param);
        String tradeNo = jsonObject.getString("tradeNo");
        String status  = jsonObject.getString("status");
        String logistic_company = jsonObject.getString("logisticCompany");
        String logistic_no = jsonObject.getString("logisticNo");
        String reason = jsonObject.getString("reason");
        if(deviceService.updateDeviceOrderLogisticState(tradeNo,status,logistic_company,logistic_no,reason)){
            baseResult.setErrcode(BaseResult.RESULT_ERROR);
            baseResult.setErrmsg("修改订单物流信息成功");
        }
        return baseResult;
    }




}
