package utils.api;

import com.alibaba.fastjson.JSONObject;

import com.sun.istack.internal.NotNull;
import model.base.BaseResult;

import java.util.Date;
import java.util.List;

/**
 * Created by yeran on 2016/10/20.
 */
public class AdControlClient extends BaseAPI {


    /**
     * 删除广告
     * @param proxyToken
     * @param advUuid
     * @return
     */
    public static BaseResult delAdByUuid(String ptoken,@NotNull String proxyToken, @NotNull String advUuid) {
        BaseResult baseResult = new BaseResult(-1, "请求失败");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("proxyToken", proxyToken);
        jsonObject.put("advUUID", advUuid);
        jsonObject.put("status","D");
        String result = AdBaseAPI.executeResult(ptoken, jsonObject.toJSONString(), BaseAPI.ADV_CONTROL_DELETE_URL);
        if (result != null) {
            AdBaseResult adBaseResult = JSONObject.parseObject(result, AdBaseResult.class);
            if (adBaseResult == null)
                return baseResult;
            baseResult.setErrmsg(adBaseResult.getError_reason());
            if (adBaseResult.success()) {
                baseResult.setErrcode(1);
            }
        }
        return baseResult;
    }


    /**
     *
     * 获取列表中的代理商 所有的平台广告总金额
     *
     * @param utokens
     * @return
     *
     */
    public static BaseResult adBenefit(String ptoken,List<String> utokens){
        BaseResult baseResult = new BaseResult(-1, "请求失败");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("utokens", utokens);

        String result = AdBaseAPI.executeResult(ptoken, jsonObject.toJSONString(), BaseAPI.FINANCE_AD_BENEFIT);
        if (result != null) {
            AdBaseResult adBaseResult = JSONObject.parseObject(result, AdBaseResult.class);
            if (adBaseResult == null)
                return baseResult;
            baseResult.setErrmsg(adBaseResult.getError_reason());
            if (adBaseResult.success()) {
                baseResult.setErrcode(1);
                baseResult.setObject(adBaseResult.getObject());
            }
        }
        return baseResult;
    }


    /**
     *
     * 获取列表中的代理商 所有的平台广告总金额
     *
     * @param utokens
     * @return
     *
     */
    public static BaseResult adBenefit(String ptoken,List<String> utokens, java.sql.Date sDate, java.sql.Date eDate){
        BaseResult baseResult = new BaseResult(-1, "请求失败");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("utokens", utokens);
        jsonObject.put("stime",sDate.getTime());
        jsonObject.put("etime",eDate.getTime());

        String result = AdBaseAPI.executeResult(ptoken, jsonObject.toJSONString(), BaseAPI.FINANCE_AD_BENEFIT);
        if (result != null) {
            AdBaseResult adBaseResult = JSONObject.parseObject(result, AdBaseResult.class);
            if (adBaseResult == null)
                return baseResult;
            baseResult.setErrmsg(adBaseResult.getError_reason());
            if (adBaseResult.success()) {
                baseResult.setErrcode(1);
                baseResult.setObject(adBaseResult.getObject());
            }
        }
        return baseResult;
    }

    /**
     *
     * 下级（区县）设备品牌红包、设备品牌红包收益
     *
     * @param utokens
     * @return
     *
     */
    public static BaseResult adBenefit(String ptoken,List<String> utokens, java.sql.Date sDate, java.sql.Date eDate,int benefitType){
        BaseResult baseResult = new BaseResult(-1, "请求失败");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("utokens", utokens);
        jsonObject.put("stime",sDate.getTime());
        jsonObject.put("etime",eDate.getTime());
        jsonObject.put("benefitType",benefitType);

        String result = AdBaseAPI.executeResult(ptoken, jsonObject.toJSONString(), BaseAPI.FINANCE_AD_BENEFIT);
        if (result != null) {
            AdBaseResult adBaseResult = JSONObject.parseObject(result, AdBaseResult.class);
            if (adBaseResult == null)
                return baseResult;
            baseResult.setErrmsg(adBaseResult.getError_reason());
            if (adBaseResult.success()) {
                baseResult.setErrcode(1);
                baseResult.setObject(adBaseResult.getObject());
            }
        }
        return baseResult;
    }

    /**
     *
     * 获取列表中的代理商 所有的平台广告总金额
     *
     * @param utoken
     * @return
     *
     */
    public static BaseResult storeAdDepositSum(String ptoken,String utoken, java.sql.Date sDate, java.sql.Date eDate){
        BaseResult baseResult = new BaseResult(-1, "请求失败");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("utoken", utoken);
        jsonObject.put("stime",sDate.getTime());
        jsonObject.put("etime",eDate.getTime());

        String result = AdBaseAPI.executeResult(ptoken, jsonObject.toJSONString(), BaseAPI.FINANCE_STORE_AD_DEPOSIT_SUM);
        if (result != null) {
            AdBaseResult adBaseResult = JSONObject.parseObject(result, AdBaseResult.class);
            if (adBaseResult == null)
                return baseResult;
            baseResult.setErrmsg(adBaseResult.getError_reason());
            if (adBaseResult.success()) {
                baseResult.setErrcode(1);
                baseResult.setObject(adBaseResult.getObject());
            }
        }
        return baseResult;
    }

    /**
     *
     * 查询代理商下属门店广告充值记录
     *
     * @param ptoken
     * @param utoken
     * @param startPage
     * @param pageSize
     * @return
     */
    public static BaseResult storeAdDepositRecord(String ptoken,String utoken,Integer startPage,Integer pageSize){
        BaseResult baseResult = new BaseResult(-1, "请求失败");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("ptoken", ptoken);
        jsonObject.put("utoken", utoken);
        jsonObject.put("currentPage", startPage);
        jsonObject.put("pageSize", pageSize);

        String result = AdBaseAPI.executeResult(ptoken, jsonObject.toJSONString(), BaseAPI.FINANCE_STORE_AD_DEPOSIT);
        if (result != null) {
            AdBaseResult adBaseResult = JSONObject.parseObject(result, AdBaseResult.class);
            if (adBaseResult == null)
                return baseResult;
            baseResult.setErrmsg(adBaseResult.getError_reason());
            if (adBaseResult.success()) {
                baseResult.setErrcode(1);
                baseResult.setObject(adBaseResult.getObject());
            }
        }
        return baseResult;
    }


    /**
     *
     * 获取代理商设备管理信息
     *
     * @param ptoken
     * @param utoken
     * @return
     */
    public static BaseResult deviceStatistic(String ptoken,String utoken){
        BaseResult baseResult = new BaseResult(-1, "请求失败");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("ptoken", ptoken);
        jsonObject.put("utoken", utoken);
        String result = AdBaseAPI.executeResult(ptoken, jsonObject.toJSONString(), BaseAPI.DEVICE_STATISTIC);
        if (result != null) {
            AdBaseResult adBaseResult = JSONObject.parseObject(result, AdBaseResult.class);
            if (adBaseResult == null)
                return baseResult;
            baseResult.setErrmsg(adBaseResult.getError_reason());
            if (adBaseResult.success()) {
                baseResult.setErrcode(1);
                baseResult.setObject(adBaseResult.getObject());
            }
        }
        return baseResult;
    }

    /**
     *
     * 获取控制中心设备管理信息
     *
     * @param ptoken
     * @return
     */
    public static BaseResult deviceStatisticTotal(String ptoken){
        BaseResult baseResult = new BaseResult(-1, "请求失败");
        JSONObject jsonObject = new JSONObject();
        String result = AdBaseAPI.executeResult(ptoken, jsonObject.toJSONString(), BaseAPI.DEVICE_STATISTIC_TOTAL);
        if (result != null) {
            AdBaseResult adBaseResult = JSONObject.parseObject(result, AdBaseResult.class);
            if (adBaseResult == null)
                return baseResult;
            baseResult.setErrmsg(adBaseResult.getError_reason());
            if (adBaseResult.success()) {
                baseResult.setErrcode(1);
                baseResult.setObject(adBaseResult.getObject());
            }
        }
        return baseResult;
    }


    /**
     *
     * 获取代理商设备管理信息（控制中心）
     *
     * @param ptoken
     * @return
     */
    public static BaseResult deviceStatisticProxy(String ptoken,List<String> utokens){
        BaseResult baseResult = new BaseResult(-1, "请求失败");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("utokens",utokens);
        String result = AdBaseAPI.executeResult(ptoken, jsonObject.toJSONString(), BaseAPI.DEVICE_STATICTIC_PROXY);
        if (result != null) {
            AdBaseResult adBaseResult = JSONObject.parseObject(result, AdBaseResult.class);
            if (adBaseResult == null)
                return baseResult;
            baseResult.setErrmsg(adBaseResult.getError_reason());
            if (adBaseResult.success()) {
                baseResult.setErrcode(1);
                baseResult.setObject(adBaseResult.getObject());
            }
        }
        return baseResult;
    }

    public static BaseResult deviceBenefit(String utoken, java.sql.Date sDate, java.sql.Date eDate) {
        BaseResult baseResult = new BaseResult(-1, "请求失败");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("proxyToken", utoken);
        if (sDate!=null)
        jsonObject.put("stime",sDate.getTime());
        if (eDate!=null)
        jsonObject.put("etime",eDate.getTime());
        try {
            return AdBaseAPI.execute(jsonObject, BaseAPI.DEVICE_ACTIVATE_BENEFIT_PROXY);
        }catch (Exception e){
            e.printStackTrace();
        }
        return baseResult;
    }
}
