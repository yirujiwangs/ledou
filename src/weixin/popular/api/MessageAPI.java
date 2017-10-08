package weixin.popular.api;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;

import weixin.popular.client.LocalHttpClient;

import com.alibaba.fastjson.JSONObject;

public class MessageAPI extends BaseAPI {

	/**
	 * 
	 * @param toId
	 * @param fromId
	 * @param msg
	 * @return
	 */
	public static boolean sendTextMessage(String type, String toId, String fromId,
			String msg, String authorizer_access_token) {
		try {
			JSONObject jsonObjec1 = new JSONObject();
			jsonObjec1.put("content", msg);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("touser", toId);
			jsonObject.put("msgtype","text");
			jsonObject.put("text", jsonObjec1);
			HttpUriRequest httpUriRequest = RequestBuilder
					.post()
					.setUri(SEND_MESSAGE_URL + "?access_token="
							+ authorizer_access_token)
					.setEntity(
							new StringEntity(jsonObject.toJSONString(), "utf-8"))
					.build();
		HttpResponse httpResponse =	LocalHttpClient.execute(httpUriRequest);
		if(httpResponse.getStatusLine().getStatusCode()==200){
			String result = EntityUtils.toString(httpResponse.getEntity());
			System.out.println(result);
			return true;
		}else{
			System.out.println("netcode=="+httpResponse.getStatusLine().getStatusCode());
		}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

}
