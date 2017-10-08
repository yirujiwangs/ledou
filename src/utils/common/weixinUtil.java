package utils.common;

import model.Token;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import common.weixin.LocalHttpClient;
import common.weixin.TokenBean;

import dao.TokenMapper;

public class weixinUtil {
	/**微信公共平台url**/
	protected static final String BASE_URI = "https://api.weixin.qq.com";
	
	protected static final String AppID = "wxd8f65d8da47e5173";
	protected static final String AppSecret = "1fbc5dd4d4d0003314a279419981e739";
	
	protected static final String DEVICE_URI="https://api.weixin.qq.com/shakearound/device/";
	
	/**
	 * 生成deviceId,UUID,minor,major
	 * https://api.weixin.qq.com/shakearound/device/applyid?access_token=ACCESS_TOKEN
	 */
	public static JSONObject generateDeviceId(Token token,TokenMapper tokenMapper, String serialNum){
		
		String accessToken=getAccessToken(token, tokenMapper);
		//System.out.println("当前token--"+accessToken);
		String url=DEVICE_URI+"applyid?access_token="+accessToken;
		JSONObject json = new JSONObject();
		json.put("quantity", 1);
		json.put("apply_reason", "spread information");
		json.put("comment", serialNum);
		JSONObject jsonTemp=json;
		json=post(url, jsonTemp);
			try{
				if(json.getString("errcode").equals("40001")){
					//重新获取，写入数据库
					TokenBean tokenBean=token(AppID,AppSecret);
					//将要被更新的token属性
					Token tokenNew=new Token();
					tokenNew.setId(token.getId());
					tokenNew.setAccesstoken(tokenBean.getAccess_token());
					tokenNew.setFirstgettime((long)System.currentTimeMillis()/1000);
					tokenNew.setExpiresin(tokenBean.getExpires_in());
					tokenMapper.updateByPrimaryKeySelective(tokenNew);
					url=DEVICE_URI+"applyid?access_token="+tokenBean.getAccess_token();
					json=post(url, jsonTemp);
				}
				
		} catch (Exception e1) {
			///
		}
		//System.out.println("----"+json.getString("errmsg"));
		json = json.getJSONObject("data");
		//申请状态
		String audit_status=json.getString("audit_status");
		//申请批次id-----------------------------------------------重要
		String apply_id=json.getString("apply_id");
		//System.out.println(audit_status+"----申请id:"+apply_id);
		try {
			Thread.sleep(1000);			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(!checkState(apply_id, token, tokenMapper)){
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(checkState(apply_id, token, tokenMapper)){
			//得到最终需要的数据
			JSONObject finalJson=getId(apply_id , token, tokenMapper);
			//System.out.println("final:  "+finalJson.getString("errmsg"));
			finalJson=finalJson.getJSONObject("data");
			//System.out.println("total_acount:  "+finalJson.getString("total_count"));
			JSONArray jsons = finalJson.getJSONArray("devices");
			if(jsons.isEmpty()){
				//未知错误
				return null;
			}
			JSONObject jsonObject = jsons.getJSONObject(0);
			
			//配置默认页面
			int device_id=jsonObject.getInteger("device_id");
			JSONObject jsonPage=new JSONObject();
			jsonPage.put("device_id", device_id);
			JSONObject jsonPage2=new JSONObject();
			jsonPage2.put("device_identifier", jsonPage);
			jsons=new JSONArray();
			//此处添加页面 by page id
			jsons.add(1253076);
			
			jsonPage2.put("page_ids", jsons);
			url=DEVICE_URI+"bindpage?access_token="+accessToken;
			
			try {
				post(url, jsonPage2);
			} catch (Exception e) {
				url=DEVICE_URI+"bindpage?access_token="+getAccessToken(token, tokenMapper);
				post(url, jsonPage2);
				return jsonObject;
			}

			
			String uuid =jsonObject.getString("uuid");
			//System.out.println("这是uuid: "+uuid);
			
			return jsonObject;
		}else{
			//仍在审核中
			return null;
		}
		
	}
	/**
	 * 根据applyId和申请状态得到device_id,UUID,major,minor.先根据apply_id查询状态是否为2
	 * @return
	 */
	public static JSONObject getId(String apply_id,Token token,TokenMapper tokenMapper){
		String accessToken=getAccessToken(token, tokenMapper);
		String url=DEVICE_URI+"search?access_token="+accessToken;
		JSONObject json=new JSONObject();
		json.put("type", 3);
		json.put("apply_id", Integer.parseInt(apply_id));
		json.put("begin", 0);
		json.put("count", 1);	
		
		return post(url, json);
	}
	/**
	 * 检查申请是否通过
	 * @param apply_id
	 * @return
	 */
	public static boolean checkState(String apply_id,Token token,TokenMapper tokenMapper){
		String accessToken=getAccessToken(token, tokenMapper);
		String url=DEVICE_URI+"applystatus?access_token="+accessToken;
		JSONObject json=new JSONObject();
		json.put("apply_id", Integer.parseInt(apply_id));
		JSONObject jsonObject=post(url, json);
		//System.out.println(jsonObject.getString("errmsg")+"---这是checkState");
		JSONObject json2= jsonObject.getJSONObject("data");
		if(json2.getInteger("audit_status")==2){
			return true;
		}else{
			//System.out.println("申请设备id仍在审核中："+json2.getInteger("audit_status"));
			return false;
		}	
	}
	/**
	 * 
	 * @param token
	 * @param tokenMapper
	 * @return 现在可用的AccessToken
	 */
	public static String getAccessToken(Token token,TokenMapper tokenMapper){
		
		String accessTokeninDb=token.getAccesstoken();//数据库中的token
		
		long currentTime=(long)System.currentTimeMillis()/1000;
		if(currentTime-token.getFirstgettime()<3600){
			//System.out.println("accessToken 未失效------------");
			return accessTokeninDb;
		}else{
			//System.out.println("accessToken 失效------------");
			TokenBean tokenBean=token(AppID,AppSecret);
			//将要被更新的token属性
			Token tokenNew=new Token();
			tokenNew.setId(token.getId());
			tokenNew.setAccesstoken(tokenBean.getAccess_token());
			tokenNew.setFirstgettime((long)System.currentTimeMillis()/1000);
			tokenNew.setExpiresin(tokenBean.getExpires_in());
			tokenMapper.updateByPrimaryKeySelective(tokenNew);
			
			return tokenNew.getAccesstoken();
		}
		
	}
	
	/**
	 * 获取access_token
	 * @param appid
	 * @param secret
	 * @return
	 */
	public static TokenBean token(String appid,String secret){
		HttpUriRequest httpUriRequest = RequestBuilder.post()
				.setUri(BASE_URI + "/cgi-bin/token")
				.addParameter("grant_type","client_credential")
				.addParameter("appid", appid)
				.addParameter("secret", secret)
				.build();
		return LocalHttpClient.executeJsonResult(httpUriRequest,TokenBean.class);
	}


	//向微信发送请求并得到json格式的响应
	public static JSONObject post(String url,JSONObject json){
		HttpClient client = HttpClients.createDefault();
		HttpPost post = new HttpPost(url);
		HttpResponse res = null;
		JSONObject response = null;
		try {
			StringEntity s = new StringEntity(json.toString());
			s.setContentEncoding("UTF-8");
			s.setContentType("application/json");
			post.setEntity(s);
			res = client.execute(post);
			if(res.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
				HttpEntity entity = res.getEntity();
				String resData = EntityUtils.toString(entity);
				response=JSON.parseObject(resData);
				//System.out.println(response.toJSONString());
			}

		} catch (Exception e) {

			throw new RuntimeException(e);

		}finally{
			
			post.releaseConnection();
		}
		return response;

	}
}
