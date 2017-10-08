package utils.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import utils.common.weixinUtil;

/**
 * Created by Administrator on 2016/10/3.
 */
public class AdvCommission {

    /**
     * @desc : ��ȡ������������
     * @param : json��ʽ 1���û�token,2��ʼ���ڣ�3��������
     *      @�������棺��ʼ���������ʾ��ǰ����
     *      @�������棺��ʼ���ڣ����¿�ʼ���������ڣ���
     *      @�ۼ����棺��ʼ��������ھ�Ϊnull
     */

    public static String getBenefitStatistics(String param) {
        JSONObject json = JSON.parseObject(param);
        JSONObject jsons = weixinUtil.post(BaseAPI.GET_ADV_COMI_URL, json);
        return jsons.toJSONString();
    }


    /**
     * @desc : ��ȡ��������Ϣ�б�
     * @param : json��ʽ 1���û�token��2��ҳ������3��ÿҳ����
     * @return: json���飺[{����}]
     */
    public static String advCommissionList(String param){
        JSONObject json = JSON.parseObject(param);
        String jsons = weixinUtil.post(BaseAPI.GET_ADV_LIST_URL, json).toJSONString();
        //System.out.println("jsons"+jsons);
        return jsons;
    }



}
