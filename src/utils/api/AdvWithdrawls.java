package utils.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import utils.common.weixinUtil;

/**
 * @desc : 获取广告模板列表
 * @param : json格式 1、用户token
 * @return: json数组：[{待定}]
 */
public class AdvWithdrawls {
    public static String getAdvWithdrawls(String param){
        JSONObject json = JSON.parseObject(param);


        String jsons = weixinUtil.post(BaseAPI.GET_ADV_WIDL_URL, json).toJSONString();
        //System.out.println("jsons"+jsons);
        return jsons;
    }
}
