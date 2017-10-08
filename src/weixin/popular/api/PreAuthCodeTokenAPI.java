package weixin.popular.api;

import java.nio.charset.Charset;

import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.StringEntity;

import com.alibaba.fastjson.JSONObject;

import weixin.popular.bean.component.PreAuthCode;
import weixin.popular.client.LocalHttpClient;

public class PreAuthCodeTokenAPI extends BaseAPI{
	
	/**
	 * 
	 * @param component_appid
	 * @param component_access_token
	 * @return
	 */
	public static PreAuthCode token(String component_appid,String component_access_token){
		JSONObject body = new JSONObject();
		body.put("component_appid", component_appid);
		HttpUriRequest httpUriRequest = RequestBuilder.post()
				.setUri(COMPONENT_URL + "/api_create_preauthcode?component_access_token="+component_access_token)
				.setHeader("Content-type","application/json; charset=utf-8")
				.setHeader("Accept", "application/json")
				.setEntity(new StringEntity(body.toJSONString(), Charset.forName("UTF-8")))
				.build();
		return LocalHttpClient.executeJsonResult(httpUriRequest,PreAuthCode.class);
	}

}
