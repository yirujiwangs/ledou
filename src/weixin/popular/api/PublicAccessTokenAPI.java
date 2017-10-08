package weixin.popular.api;

import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;

import weixin.popular.bean.Token;
import weixin.popular.client.LocalHttpClient;

public class PublicAccessTokenAPI extends BaseAPI{
	/**
	 * 获取access_token
	 * @param appid
	 * @return
	 */
	public static Token token(String appid){
		HttpUriRequest httpUriRequest = RequestBuilder.post()
				.setUri(PUBLIC_ACCESS_TOKEN_URL)
				.addParameter("appid", appid)
				.build();
		return LocalHttpClient.executeJsonResult(httpUriRequest,Token.class);
	}
}
