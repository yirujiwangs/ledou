package weixin.popular.api;

import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.entity.ContentType;
import org.apache.http.message.BasicHeader;

public abstract class BaseAPI {
	protected static final String BASE_URL_LEDOUYA="http://www.ledouya.com/wxAuth";
//	protected static final String BASE_URL_LEDOUYA_TEST="http://e15d226741.imwork.net/wxAuth";
	protected static final String BASE_URL_LEDOUYA_TEST="http://lutianfei.vicp.io/wxAuth";

	protected static final String BASE_URI = "https://api.weixin.qq.com";
	protected static final String MEDIA_URI = "http://file.api.weixin.qq.com";
	protected static final String QRCODE_DOWNLOAD_URI = "https://mp.weixin.qq.com";
	protected static final String MCH_URI = "https://api.mch.weixin.qq.com";
	protected static final String OPEN_URI = "https://open.weixin.qq.com";

	protected static final String COMPONENT_URL = BASE_URI
			+ "/cgi-bin/component";
	
	protected static final String QR_PARAM_URI = BASE_URI
			+ "/cgi-bin/qrcode/create";
	
	protected static final String THIRD_AUTH_ACCESSTOKEN=BASE_URI+"/sns/oauth2/component/access_token";
	

	protected static Header jsonHeader = new BasicHeader(
			HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.toString());
	protected static Header xmlHeader = new BasicHeader(
			HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_XML.toString());
	
	protected static final String SEND_MESSAGE_URL="https://api.weixin.qq.com/cgi-bin/message/custom/send";

	protected static final String THIRD_PLATFORM_COMPONENT_ACCESSTOKEN =BASE_URL_LEDOUYA_TEST+"/api/getComponentAccessToken.do";
	
	protected static final String THIRD_PLATFORM_AUTH_ACCESSTOKEN = BASE_URL_LEDOUYA_TEST+"/api/getAuthorizerAccessToken.do";
	
	protected static final String THIRD_PLATFORM_AUTHORZER_INFO=BASE_URL_LEDOUYA_TEST+"/api/getAuthorizerInfo.do";
	
	protected static final String PUBLIC_ACCESS_TOKEN_URL=BASE_URL_LEDOUYA+"/wxservice/accessToken.do";

}
