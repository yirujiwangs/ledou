package service.Impl;

import com.alibaba.fastjson.JSONObject;
import dao.PayOrdersMapper;
import model.PayOrders;
import model.PayOrdersExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import service.PayService;
import utils.common.LogUtil;
import utils.common.RandomNonceUtil;
import utils.common.WechatConstansUtil;
import weixin.popular.api.PayMchAPI;
import weixin.popular.bean.paymch.MchPayNativeReply;
import weixin.popular.bean.paymch.MchPayNotify;
import weixin.popular.bean.paymch.Unifiedorder;
import weixin.popular.bean.paymch.UnifiedorderResult;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by yeran on 2017/4/28.
 */

@Service
public class PayServiceImpl implements PayService {

    @Autowired
    private PayOrdersMapper payOrdersMapper;


    public Unifiedorder preOrder(String appid, String mch_id, String device_info, String body, String attach,
                                 String out_trade_no, String total_fee,
                                 String spbill_create_ip, String notify_url, String trade_type,
                                 String openid, String product_id,String nonce_str) {
        if (device_info==null)
            device_info="WEB";
        if (trade_type==null)
            trade_type="NATIVE";


        Unifiedorder unifiedorder = new Unifiedorder();
        unifiedorder.setAppid(appid);
        unifiedorder.setBody(body);
        unifiedorder.setDevice_info(device_info);
        unifiedorder.setMch_id(mch_id);
        unifiedorder.setNotify_url(notify_url);
        unifiedorder.setOut_trade_no(out_trade_no);
        unifiedorder.setSpbill_create_ip(spbill_create_ip);
        unifiedorder.setTotal_fee(total_fee);
        unifiedorder.setTrade_type(trade_type);
        unifiedorder.setNonce_str(nonce_str);

        if (product_id!=null)
        unifiedorder.setProduct_id(product_id);

        if(openid!=null)
        unifiedorder.setOpenid(openid);

        if (attach!=null)
        unifiedorder.setAttach(attach);

        return unifiedorder;
    }

    @Override
    public Object payUrl(String utoken, String payType, String out_trade_no, String body, String total_fee, String spbill_create_ip,
                         String notify_url, String trade_type, String openid,
                         String product_id, String device_info, String attach) throws UnsupportedEncodingException {
        if("WX".equals(payType)){

            //获取微信账户的参数
            String appid =WechatConstansUtil.AppID;
            String mch_id = WechatConstansUtil.mchID;
            String key = WechatConstansUtil.key;

            if (device_info==null)
                device_info="WEB";
            if (trade_type==null)
                trade_type="NATIVE";

            String nonce_str = RandomNonceUtil.createString(32);

            Unifiedorder unifiedorder = preOrder(appid, mch_id, device_info, body, attach, out_trade_no,
                    total_fee,spbill_create_ip, notify_url, trade_type,openid, product_id,nonce_str);

            UnifiedorderResult unifiedorderResult = PayMchAPI.payUnifiedorder(unifiedorder,key);
            if(unifiedorderResult==null)
                return null;

           LogUtil.log(this.getClass(), LogUtil.LogType.INFO,JSONObject.toJSONString(unifiedorderResult));

            if("SUCCESS".equals(unifiedorderResult.getReturn_code())){
                if("SUCCESS".equals(unifiedorderResult.getResult_code())){
                    //预订单成功
                    //返回结果
                    LogUtil.log(this.getClass(),LogUtil.LogType.SUCCESS,"预订单成功");
                    try {
                        //保存pay_order
                        PayOrders payOrders = new PayOrders();
                        payOrders.setAppid(appid);
                        payOrders.setBody(body);
                        payOrders.setMch_id(mch_id);
                        payOrders.setTrade_type(trade_type);
                        payOrders.setNonce_str(nonce_str);
                        payOrders.setNotify_url(notify_url);
                        payOrders.setOpenid(openid);
                        payOrders.setOut_trade_no(out_trade_no);
                        payOrders.setSpbill_create_ip(spbill_create_ip);
                        payOrders.setTotal_fee(Integer.parseInt(total_fee));
                        payOrders.setUtoken(utoken);
                        payOrders.setPrepay_id(unifiedorderResult.getPrepay_id());
                        payOrders.setAttach(attach);
                        payOrders.setSign(unifiedorder.getSign());

                        int id = payOrdersMapper.insertSelective(payOrders);
                        if (id > 0)
                            LogUtil.log(this.getClass(), LogUtil.LogType.SUCCESS, "新建支付记录成功");
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }else{
                    //发生错误
                    LogUtil.log(this.getClass(), LogUtil.LogType.ERROR,unifiedorderResult.getReturn_msg());
                }
                return unifiedorderResult;
            }

        }else{

        }

        return null;
    }


    /**
     *
     * 如果返回null，则需要微信继续返回支付结果
     * 否则，认为已经消费了这个通知事件
     *
     * @param mchPayNativeReply
     * @return
     */
    @Override
    @Transactional(readOnly = false,rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    public PayOrders payResult(MchPayNotify mchPayNativeReply) {


        if("SUCCESS".equals(mchPayNativeReply.getReturn_code())){

            String appid = mchPayNativeReply.getAppid();
            String out_trade_no = mchPayNativeReply.getOut_trade_no();
            String nonce_str = mchPayNativeReply.getNonce_str();

            PayOrdersExample payOrdersExample = new PayOrdersExample();
            payOrdersExample.createCriteria().andAppidEqualTo(appid)
                    .andOut_trade_noEqualTo(out_trade_no)
                    .andNonce_strEqualTo(nonce_str);

            List<PayOrders> orderses = payOrdersMapper.selectByExample(payOrdersExample);
            if(orderses==null || orderses.size()==0)//微信返回了支付信息，但是本地却没有记录，则需要微信重复返回，理论上不可能出现
                return null;

            PayOrders payOrder = orderses.get(0);

            if(!"P".equals(payOrder.getStatus())) {
                //已经处理过了，直接返回
                return payOrder;
            }

            if ("SUCCESS".equals(mchPayNativeReply.getResult_code())){
                //  支付成功，更改pay_order的状态，注意，这里必须在事务内进行
                payOrder.setStatus("N");
                payOrder.setOpenid(mchPayNativeReply.getOpenid());
                payOrder.setTransaction_id(mchPayNativeReply.getTransaction_id());
                payOrder.setSign(mchPayNativeReply.getSign());
                int rows = payOrdersMapper.updateByExampleSelective(payOrder, payOrdersExample);
                if (rows > 0)
                    //更新成功
                    return payOrder;
                else return null;

            }else {
                if (mchPayNativeReply.getErr_code()!=null){
                    //支付出现问题
                    payOrder.setStatus("D");
                    payOrder.setErr_code(mchPayNativeReply.getErr_code());
                    payOrder.setErr_msg(mchPayNativeReply.getErr_code_des());
                    payOrdersMapper.updateByExampleSelective(payOrder, payOrdersExample);
                    return payOrder;
                }

            }

        }

        return new PayOrders();
    }

    @Override
    public PayOrders getOrderByOutTradeNo(String outTradeNo, String utoken) {
        PayOrdersExample payOrdersExample = new PayOrdersExample();
        payOrdersExample.createCriteria().andUtokenEqualTo(utoken).andOut_trade_noEqualTo(outTradeNo);
        List<PayOrders> payOrders = payOrdersMapper.selectByExample(payOrdersExample);
        if (payOrders!=null && payOrders.size()>0){
            return payOrders.get(0);
        }
        return null;
    }

}
