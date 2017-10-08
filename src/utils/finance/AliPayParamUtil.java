package utils.finance;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by yeran on 2016/10/17.
 */
public class AliPayParamUtil {

    public static JSONObject formJson(String return_url, String notify_url, Double pay_fee, String info
            , String remark, String out_trade_no) {
        JSONObject jsonTmp = new JSONObject();
        jsonTmp.put("notify_url", notify_url);
        jsonTmp.put("return_url", return_url);
        jsonTmp.put("totalFee", pay_fee);
        jsonTmp.put("info", info);
        jsonTmp.put("remark", remark);
        jsonTmp.put("out_trade_no", out_trade_no);

        return jsonTmp;
    }
}
