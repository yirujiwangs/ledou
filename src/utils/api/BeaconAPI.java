package utils.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import common.weixin.LocalHttpClient;
import model.dto.ProxyDevicesInfo;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class BeaconAPI extends BaseAPI {


    /**
     * 获取代理商的设备数指标
     *
     * @param ptoken
     * @param param
     * @return
     */
    public static String getDeviceStatistic(String ptoken, String param, String pathUrl) throws IOException {

      //  CloseableHttpClient closeableHttpClient = HttpClients.createDefault();

        HttpUriRequest httpUriRequest = RequestBuilder.post()
                .setUri(BASE_URL_ADPLATFORM_TEST + pathUrl)
                .setHeader("Accept", "application/json")
                .addParameter("param", param)
                .build();

     //   CloseableHttpResponse  httpResponse =  closeableHttpClient.execute(httpUriRequest);
            HttpResponse  httpResponse =  LocalHttpClient.execute(httpUriRequest);
                //LocalHttpClient.execute(httpUriRequest);
        if(httpResponse==null){
            return null;
        }
        //        httpResponse.close();
        String result =  EntityUtils.toString(httpResponse.getEntity(), "utf-8");
        return result;
    }

    /**
     * 获取指定代理商的设备列表
     *
     * @param param
     * @return
     */

    public static String getProxyDeviceInfo(String ptoken, String param) throws IOException {

        HttpUriRequest httpUriRequest = RequestBuilder.post()
                .setUri(BASE_URL_ADPLATFORM_TEST + "device/getOperator")
                .setHeader("Accept", "application/json")
                .addParameter("param", param).build();
        HttpResponse httpResponse = LocalHttpClient.execute(httpUriRequest);

        List<ProxyDevicesInfo> list = new ArrayList<ProxyDevicesInfo>();

        JSONObject jsonObject = new JSONObject();

        if (httpResponse != null) {

            String result = EntityUtils.toString(httpResponse.getEntity(), "utf-8");

            JSONObject jsons = JSON.parseObject(result);

            jsonObject.put("pages", jsons.getInteger("pages"));

            JSONArray jsonArray = JSON.parseArray(jsons.getString("deviceInfo"));

            //解析设备列表，封装获有效设备信息
            ProxyDevicesInfo proxyDevicesInfo;

            for (int i = 0; i < jsonArray.size(); i++) {
                proxyDevicesInfo = new ProxyDevicesInfo();
                JSONObject jsonTmp = jsonArray.getJSONObject(i);
                proxyDevicesInfo.setiBeaconID(jsonTmp.getString("device_id"));
                proxyDevicesInfo.setGroupName(jsonTmp.getString("groupName")); //groupName
                proxyDevicesInfo.setStatus(jsonTmp.getString("STATUS"));
                proxyDevicesInfo.setCreateTime(jsonTmp.getDate("time"));
                proxyDevicesInfo.setRemark(jsonTmp.getString("comment"));

                list.add(proxyDevicesInfo);
            }

            jsonObject.put("deviceInfo", list);
        }
        return jsonObject.toJSONString();
    }

    /**
     * @param param
     * @return
     * @descr :封装运营商设备信息给前端
     */
    public static String getProxyDeviceInfos(String param) {
        try {
            List<ProxyDevicesInfo> list = new ArrayList<>();
            JSONObject jsonObject = new JSONObject();

            if (!param.isEmpty()) {
                JSONObject jsons = JSON.parseObject(param);

                jsonObject.put("pages", jsons.getInteger("pages"));

                JSONArray jsonArray = jsons.getJSONArray("deviceInfo");

                //解析设备列表，封装获有效设备信息
                ProxyDevicesInfo proxyDevicesInfo;

                if (jsonArray != null) {
                    for (int i = 0; i < jsonArray.size(); i++) {
                        proxyDevicesInfo = new ProxyDevicesInfo();
                        JSONObject jsonTmp = jsonArray.getJSONObject(i);
                        proxyDevicesInfo.setiBeaconID(jsonTmp.getString("serializeNum"));
                        proxyDevicesInfo.setGroupName(jsonTmp.getString("groupName")); //groupName
                        proxyDevicesInfo.setStatus(jsonTmp.getString("STATUS"));
                        proxyDevicesInfo.setCreateTime(jsonTmp.getDate("time"));
                        proxyDevicesInfo.setRemark(jsonTmp.getString("comment"));
                        proxyDevicesInfo.setDtoken(jsonTmp.getString("dtoken"));
                        proxyDevicesInfo.setStoreName(jsonTmp.getString("business_name"));
                        proxyDevicesInfo.setStoreAddress(jsonTmp.getString("address"));
                        proxyDevicesInfo.setDeviceType(jsonTmp.getInteger("is_code"));
                        proxyDevicesInfo.setActivatePercent(jsonTmp.getInteger("active_percent"));
                        proxyDevicesInfo.setRedPercent(jsonTmp.getInteger("red_percent"));
                        list.add(proxyDevicesInfo);
                    }
                }

                jsonObject.put("deviceInfo", list);
            }
            return jsonObject.toJSONString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

