package utils.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import utils.common.weixinUtil;

/**
 * Created by Administrator on 2016/10/3.
 */
public class AdvertiseList {

    /**
     * @desc : ��ȡ�ѷ�������б�
     * @param : json��ʽ 1���û�token��2��ҳ������3��ÿҳ����
     * @return: json���飺[{pic,logo,url,ad_begin_time,ad_end_time,status,utoken,ptoken,time,tou_num,withdrawals_id,province,uuid}]
      */

    public static String advList(String param){
        JSONObject json = JSON.parseObject(param);
        String jsons = weixinUtil.post(BaseAPI.GET_ADV_LIST_URL, json).toJSONString();
        //System.out.println("jsons"+jsons);
        return jsons;
    }
}
