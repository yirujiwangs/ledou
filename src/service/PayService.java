package service;

import model.PayOrders;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import weixin.popular.bean.paymch.MchPayNotify;

import java.io.UnsupportedEncodingException;

/**
 * Created by yeran on 2017/4/28.
 */
public interface PayService {


    /**
     *
     * 生成支付url
     *
     *
     * @param utoken
     * @param payType
     * @return
     */
    Object payUrl(String utoken, String payType, String out_trade_no, String body, String total_fee, String spbill_create_ip,
                  String notify_url, String trade_type, String openid,
                  String product_id, String device_info, String attach) throws UnsupportedEncodingException;


    @Transactional(readOnly = false,rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    PayOrders payResult(MchPayNotify mchPayNativeReply);

    PayOrders getOrderByOutTradeNo(String outTradeNo,String utoken);
}
