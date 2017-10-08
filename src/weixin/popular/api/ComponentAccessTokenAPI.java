package weixin.popular.api;

import java.nio.charset.Charset;

import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.StringEntity;

import weixin.popular.bean.component.ComponentAccessToken;
import weixin.popular.client.LocalHttpClient;

import com.alibaba.fastjson.JSONObject;

public class ComponentAccessTokenAPI extends BaseAPI{
	
	/**
	 * 向ComponentAccessToken维护服务器发送请求，获取有效的ComponentAccessToken
	 * @param appId
	 * @return
	 */
	public static String getComponentAccessToken(String appId){
		HttpUriRequest httpUriRequest = RequestBuilder.post()
				.setUri(THIRD_PLATFORM_COMPONENT_ACCESSTOKEN)
				.setHeader("Accept", "application/json")
				.addParameter("appId", appId)
				.build();
		//
		ComponentAccessToken componentAccessToken = LocalHttpClient.executeJsonResult(httpUriRequest, ComponentAccessToken.class);
	    if(componentAccessToken!=null){
	    	return componentAccessToken.getComponent_access_token();
	    }
	    return null;
	}
	
	
	

	/**
	 * 获取ComponentAccessToken
	 * @param component_appid
	 * @param component_appsecret
	 * @param component_verify_ticket
	 * @return
	 */
	public static ComponentAccessToken token(String component_appid
			,String component_appsecret,String component_verify_ticket){
		JSONObject body = new JSONObject();
		body.put("component_appid", component_appid);
		body.put("component_appsecret", component_appsecret);
		body.put("component_verify_ticket", component_verify_ticket);
		
	
		HttpUriRequest httpUriRequest = RequestBuilder.post()
				.setUri(COMPONENT_URL + "/api_component_token")
				.setHeader("Content-type","application/json; charset=utf-8")
				.setHeader("Accept", "application/json")
				.setEntity(new StringEntity(body.toJSONString(), Charset.forName("UTF-8")))
				.build();
		return LocalHttpClient.executeJsonResult(httpUriRequest,ComponentAccessToken.class);
	}
}
