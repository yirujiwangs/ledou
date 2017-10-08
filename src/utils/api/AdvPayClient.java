package utils.api;

import com.alibaba.fastjson.JSONObject;
import utils.finance.AliPayParamUtil;

/**
 * Created by yeran on 2016/10/17.
 */
public class AdvPayClient extends BaseAPI {

    /**
     * 支付参数设置
     *
     * @param fee
     * @param info
     * @param out_trade_no
     * @return
     */
    public static JSONObject aliPayParam(Double fee,String info,String out_trade_no){
        String notify_url = ALI_PAY_URL_AD;
        String return_url = BASE_URL;
        String remark= "PictureAdv";
       return AliPayParamUtil.formJson(return_url,notify_url,fee,info,remark,out_trade_no);
    }

}
