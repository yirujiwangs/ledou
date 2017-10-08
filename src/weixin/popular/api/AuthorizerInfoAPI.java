package weixin.popular.api;

import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;

import weixin.popular.bean.component.AuthorizerDetailInfo;
import weixin.popular.client.LocalHttpClient;

/**
 * 获取公众号信息API
 * 
 * 这里通过第三方平台进行授权获取
 * 
 * @author yeran
 *
 */
public class AuthorizerInfoAPI extends BaseAPI {

	/**
	 * 通过公众号APPID获取公众号资料
	 * @param authorizer_appid
	 * @return
	 */
	public static AuthorizerDetailInfo getAuthorizerDetailInfo(
			String authorizer_appid) {

		HttpUriRequest httpUriRequest = RequestBuilder.post()
				.setUri(THIRD_PLATFORM_AUTHORZER_INFO)
				.setHeader("Accept", "application/json")
				.addParameter("authorizer_appid", authorizer_appid).build();
		AuthorizerDetailInfo authorizerDetailInfo = LocalHttpClient
				.executeJsonResult(httpUriRequest, AuthorizerDetailInfo.class);
		return authorizerDetailInfo;
	}

}
