package weixin.popular.api;

import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;

import weixin.popular.bean.component.Authorization_token;
import weixin.popular.client.LocalHttpClient;

/**
 * 获取第三方平台下的公众号的access_token
 * 
 * @author yeran
 *
 */
public class PlatformAccessTokenApi extends BaseAPI {

	public static Authorization_token token(String authorizer_appid){
		HttpUriRequest httpUriRequest = RequestBuilder.post()
				.setUri(THIRD_PLATFORM_AUTH_ACCESSTOKEN)
				.addParameter("appid", authorizer_appid)
				.build();
		return LocalHttpClient.executeJsonResult(httpUriRequest,Authorization_token.class);
	} 
}
