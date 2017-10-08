package weixin.popular.api;

import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;

import utils.common.WechatConstansUtil;
import weixin.popular.bean.component.ThirdAuthAuthorization_token;
import weixin.popular.client.LocalHttpClient;

/**
 * 第三方平台控制下属公众号授权 的access_token以及openId获取
 * @author yeran
 *
 */
public class ThirdAuthAccessTokenApi extends BaseAPI{
	public static ThirdAuthAuthorization_token token(String authorizer_appid,String code,String component_access_token){
		HttpUriRequest httpUriRequest = RequestBuilder.post()
				.setUri(THIRD_AUTH_ACCESSTOKEN)
				.addParameter("appid",authorizer_appid)
				.addParameter("code",code)
				.addParameter("grant_type", "authorization_code")
				.addParameter("component_appid", WechatConstansUtil.PUBLIC_APPID)
				.addParameter("component_access_token",component_access_token)
				.build();
		return LocalHttpClient.executeJsonResult(httpUriRequest,ThirdAuthAuthorization_token.class);
	} 
}
