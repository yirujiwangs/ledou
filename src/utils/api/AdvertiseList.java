package utils.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import utils.common.weixinUtil;

/**
 * Created by Administrator on 2016/10/3.
 */
public class AdvertiseList {

    /**
     * @desc : 获取已发布广告列表
     * @param : json格式 1、用户token；2、页码数；3、每页条数
     * @return: json数组：[{pic,logo,url,ad_begin_time,ad_end_time,status,utoken,ptoken,time,tou_num,withdrawals_id,province,uuid}]
      */

    public static String advList(String param){
        JSONObject json = JSON.parseObject(param);
        String jsons = weixinUtil.post(BaseAPI.GET_ADV_LIST_URL, json).toJSONString();
        //System.out.println("jsons"+jsons);
        return jsons;
    }
}
