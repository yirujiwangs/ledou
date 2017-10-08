package controller.pay;

import model.PayOrders;
import model.base.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import service.DeviceService;
import service.PayService;
import service.ProxyAccountService;
import utils.common.LogUtil;
import weixin.popular.bean.paymch.MchBaseResult;
import weixin.popular.bean.paymch.MchPayNotify;
import weixin.popular.util.XMLConverUtil;

import java.util.Objects;

/**
 * Created by yeran on 2017/4/28.
 */

@Controller
@RequestMapping("/payback")
public class PayBackController {

    @Autowired
    private PayService payService;

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private ProxyAccountService proxyAccountService;


    @RequestMapping("/wx/pc/deviceBuy.do")
    public @ResponseBody String deviceBuyResult(@RequestBody String resultXml){

        //System.out.println(resultXml);
        MchPayNotify mchPayNativeReply = XMLConverUtil.convertToObject(MchPayNotify.class,resultXml);

        MchBaseResult mchBaseResult = new MchBaseResult();

        PayOrders payOrders = payService.payResult(mchPayNativeReply);
        if (payOrders==null){

            mchBaseResult.setReturn_code("FAIL");
            mchBaseResult.setReturn_msg("NO LOCAL SIGN");
            return XMLConverUtil.convertToXML(mchBaseResult);
        }

        if (payOrders.getStatus()!=null && Objects.equals("N", payOrders.getStatus())){
            //支付成功
            //修改设备购买状态
            String out_trade_no = payOrders.getOut_trade_no();
            LogUtil.log(this.getClass(), LogUtil.LogType.SUCCESS,"微信支付成功，订单号为："+out_trade_no);
            if (out_trade_no!=null){
                //根据订单号，查找设备购买记录
                if(deviceService.deviceBuyResult(out_trade_no,true)){
                    LogUtil.log(this.getClass(), LogUtil.LogType.SUCCESS,"设备购买成功，订单号为："+out_trade_no);
                }else{
                    LogUtil.log(this.getClass(), LogUtil.LogType.ERROR,"设备购买状态修改失败，订单号为："+out_trade_no);
                }
            }
        }else{
            LogUtil.log(this.getClass(), LogUtil.LogType.SUCCESS,"微信支付状态修改失败，错误原因："+mchPayNativeReply.getReturn_code()
                                                        +"/"+mchPayNativeReply.getResult_code()
                                                        +"/"+mchPayNativeReply.getReturn_msg()
                                                        +"/"+mchPayNativeReply.getErr_code()
                                                        +"/"+mchPayNativeReply.getErr_code_des());
        }

        mchBaseResult.setReturn_code("SUCCESS");
        mchBaseResult.setReturn_msg("OK");
        return XMLConverUtil.convertToXML(mchBaseResult);
    }


    @RequestMapping("/wx/pc/accountBuy.do")
    public @ResponseBody String AccountBuyResult(@RequestBody String resultXml){

        //System.out.println(resultXml);
        MchPayNotify mchPayNativeReply = XMLConverUtil.convertToObject(MchPayNotify.class,resultXml);

        MchBaseResult mchBaseResult = new MchBaseResult();

        PayOrders payOrders = payService.payResult(mchPayNativeReply);
        if (payOrders==null){

            mchBaseResult.setReturn_code("FAIL");
            mchBaseResult.setReturn_msg("NO LOCAL SIGN");
            return XMLConverUtil.convertToXML(mchBaseResult);
        }

        if (payOrders.getStatus()!=null && Objects.equals("N", payOrders.getStatus())){
            //支付成功
            //修改设备购买状态
            String out_trade_no = payOrders.getOut_trade_no();
            LogUtil.log(this.getClass(), LogUtil.LogType.SUCCESS,"微信支付成功，订单号为："+out_trade_no);
            if (out_trade_no!=null){
                //根据订单号，查找设备购买记录
                if(proxyAccountService.accountBuyResult(out_trade_no,true)){
                    LogUtil.log(this.getClass(), LogUtil.LogType.SUCCESS,"账号购买成功，订单号为："+out_trade_no);
                }else{
                    LogUtil.log(this.getClass(), LogUtil.LogType.ERROR,"设备账户状态修改失败，订单号为："+out_trade_no);
                }
            }
        }else{
            LogUtil.log(this.getClass(), LogUtil.LogType.SUCCESS,"微信支付状态修改失败，错误原因："+mchPayNativeReply.getReturn_code()
                    +"/"+mchPayNativeReply.getResult_code()
                    +"/"+mchPayNativeReply.getReturn_msg()
                    +"/"+mchPayNativeReply.getErr_code()
                    +"/"+mchPayNativeReply.getErr_code_des());
        }

        mchBaseResult.setReturn_code("SUCCESS");
        mchBaseResult.setReturn_msg("OK");
        return XMLConverUtil.convertToXML(mchBaseResult);
    }

}
