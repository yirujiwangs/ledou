package utils.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import utils.common.weixinUtil;

/**
 * Created by Administrator on 2016/10/3.
 */
public class AdvCommission {

    /**
     * @desc : 获取广告提成总收益
     * @param : json格式 1、用户token,2开始日期；3结束日期
     *      @当天收益：开始与结束均表示当前日期
     *      @本月收益：开始日期：本月开始，结束日期：空
     *      @累计收益：开始与结束日期均为null
     */

    public static String getBenefitStatistics(String param) {
        JSONObject json = JSON.parseObject(param);
        JSONObject jsons = weixinUtil.post(BaseAPI.GET_ADV_COMI_URL, json);
        return jsons.toJSONString();
    }


    /**
     * @desc : 获取广告提成信息列表
     * @param : json格式 1、用户token；2、页码数；3、每页条数
     * @return: json数组：[{待定}]
     */
    public static String advCommissionList(String param){
        JSONObject json = JSON.parseObject(param);
        String jsons = weixinUtil.post(BaseAPI.GET_ADV_LIST_URL, json).toJSONString();
        //System.out.println("jsons"+jsons);
        return jsons;
    }



}
