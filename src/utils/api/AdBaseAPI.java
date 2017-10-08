package utils.api;

import com.alibaba.fastjson.JSONObject;
import common.weixin.LocalHttpClient;
import model.base.BaseResult;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import utils.common.LogUtil;
import utils.common.WechatConstansUtil;

import java.util.List;

/**
 * Created by Administrator on 2016/10/7.
 */
public class AdBaseAPI extends BaseAPI {

    /**
     * @param ptoken
     * @param param
     * @param pathUrl
     * @return
     * @descr 从PHP端获取数据的通用接口
     */

    public static String executeResult(String ptoken, String param, String pathUrl) {
        try {
            if(ptoken==null){
               ptoken = WechatConstansUtil.PTOKEN_LEDOUYA;
            }
            HttpUriRequest httpUriRequest = RequestBuilder.post()
                    .setUri(
                            BASE_URL_ADPLATFORM_TEST + pathUrl)
                    .addParameter("ptoken", ptoken)
                    .setEntity(new StringEntity(param, "utf-8"))
                    .build();
            LogUtil.log(AdBaseAPI.class, LogUtil.LogType.INFO,"request=" + httpUriRequest.getURI() + "--" + param);
            //System.out.println(httpUriRequest.getURI() + "--" + param);
            HttpResponse httpResponse = LocalHttpClient.execute(httpUriRequest);

            String result = null;

            if (httpResponse != null) {
                int httpStatus = httpResponse.getStatusLine().getStatusCode();
                System.out.println("HttpStatus: " + httpStatus);



                if (httpStatus != HttpStatus.SC_OK ) {
                    EntityUtils.consume(httpResponse.getEntity());

                    if (httpStatus == HttpStatus.SC_MOVED_TEMPORARILY) {
                        String url = httpResponse.getLastHeader("Location").getValue().substring(8);
                        System.out.println("url:"+url);
                        return executeResult(ptoken,param,url);
                    }

                    return "HttpResponseError";
                }
                if (httpResponse.getEntity() != null) {
                    result = new String(EntityUtils.toString(httpResponse.getEntity()).getBytes(), "utf-8");
                    System.out.println("result: " + result);
                    Header header = httpResponse.getFirstHeader("Referer");
                    String refer = "@@";
                    if (header != null)
                        refer = header.getName() + "@" + header.getValue();
                    LogUtil.log(AdBaseAPI.class, LogUtil.LogType.INFO, "result==" + refer + "--" + result);
                } else {
                    result = "HttpResponseEmpty";
                }
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * 执行请求
     *
     * @param jsonObject
     * @param url
     * @return
     */
    public static BaseResult execute(JSONObject jsonObject,String url){
        BaseResult baseResult = new BaseResult(BaseResult.RESULT_ERROR, "请求失败");
        String ptoken = WechatConstansUtil.PTOKEN_LEDOUYA;
        if(!jsonObject.containsKey("ptoken")){
            jsonObject.put("ptoken",ptoken);
        }
        String result = AdBaseAPI.executeResult(ptoken, jsonObject.toJSONString(), url);
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
}
