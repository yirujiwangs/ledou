package utils.api;

import com.alibaba.fastjson.JSONArray;
import dao.WxStatisticsMapper;
import dao.WxdevdataMapper;
import model.WxStatistics;
import model.Wxdevdata;
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
import com.alibaba.fastjson.JSONObject;

import common.weixin.LocalHttpClient;
import common.weixin.TokenBean;
import dao.TokenMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import utils.common.WechatConstansUtil;
import weixin.popular.api.PublicAccessTokenAPI;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 微信相关
 * @author lenovo
 *
 */
public class wxUtil {
	
	protected static final String AppID = "wxd8f65d8da47e5173";
	protected static final String AppSecret = "1fbc5dd4d4d0003314a279419981e739";	
	protected static final String DEVICE_URI="https://api.weixin.qq.com/shakearound/device/";
	protected static final String BASE_URI = "https://api.weixin.qq.com";



	/**
	 * @descr 获取微信摇周边数据并出入数据库
	 */
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public static void getwxStatistics(WxStatisticsMapper wxStatisticsMapper,WxdevdataMapper wxdevdataMapper,TokenMapper tokenMapper){
		Date lastUpdateDate = wxStatisticsMapper.getLastDateTime();
		long lastUpdateTime =0;

		if(lastUpdateDate != null){
			lastUpdateTime = lastUpdateDate.getTime();
		}

		//获取数据库小时
		SimpleDateFormat sdf = new SimpleDateFormat("HH");
		int currentHour = Integer.parseInt(sdf.format(System.currentTimeMillis()));
//		System.out.println("currentHour : "+ currentHour);

		//0~8点微信的更新数据库不方便抓取！
		if(currentHour < 8 && currentHour>0){
			return;
		}

		//查询数据库中最后更新时间（86400000一天的毫秒值）
		while(lastUpdateTime == 0 || lastUpdateTime/86400000 < System.currentTimeMillis()/86400000 -1){
			//最多获取微信端90天的数据
			if(lastUpdateTime == 0){
				lastUpdateTime = System.currentTimeMillis() -(long) 86400000*89;

				//System.out.println("currentTime : "+ System.currentTimeMillis());
				//System.out.println("lastUpdateTime : "+ lastUpdateTime);
			}
			else if( (lastUpdateTime/86400000) == (System.currentTimeMillis()/86400000 -1)){
				break;
			}
			else{
				lastUpdateTime+=86400000;
			}

			//获取每日的摇周边数据 pv次数，uv人数
			int total_count = 0;
			int daySpv = 0,daySuv=0,dayCpv=0,dayCuv=0;
			WxStatistics ws = new WxStatistics();
			int  pageIdx = 0;

			do{
				pageIdx++;

				JSONObject wxJson = wxUtil.getWxDevStatistics(lastUpdateTime,pageIdx);

				total_count = wxJson.getInteger("total_count");
				System.out.println("errmsg : " + wxJson.getString("errmsg") + "   totalCount : " + total_count);

				String devices = wxJson.getJSONObject("data").getString("devices");
				JSONArray deviceJson = JSON.parseArray(devices);
				JSONObject jsonTmp;
				Wxdevdata wd = new Wxdevdata();

				if(deviceJson.size()<1){
					break;
				}

				for(int j=0;j<deviceJson.size();j++){
					System.out.println(deviceJson.get(j));
					jsonTmp = JSON.parseObject(deviceJson.get(j).toString());

					wd.setDeviceid(jsonTmp.getString("device_id"));
					wd.setShakepv(jsonTmp.getInteger("shake_pv"));
					wd.setShakeuv(jsonTmp.getInteger("shake_uv"));
					wd.setClickpv(jsonTmp.getInteger("click_pv"));
					wd.setClickuv(jsonTmp.getInteger("click_uv"));
					wd.setDate(new Date(lastUpdateTime));

					wxdevdataMapper.insertSelective(wd);

					daySpv+=jsonTmp.getInteger("shake_pv");
					daySuv+=jsonTmp.getInteger("shake_uv");
					dayCpv+=jsonTmp.getInteger("click_pv");
					dayCuv+=jsonTmp.getInteger("click_uv");



					wd = new Wxdevdata();
				}

			}while(total_count - pageIdx*50 > 0);

			ws.setDate(new Date(lastUpdateTime));
			ws.setShakepv(daySpv);
			ws.setShakeuv(daySuv);
			ws.setClickpv(dayCpv);
			ws.setClickuv(dayCuv);

			wxStatisticsMapper.insertSelective(ws);
			ws = new WxStatistics();
			System.out.println("微信数据更新成功");
		}

	}


	/**
	 * @descr 从微信端获取摇周边数据
	 * @param datetime
	 * @param pageIdx
	 * @return
	 */
	public static JSONObject getWxDevStatistics(long datetime,int pageIdx){
		String accessToken = getAccessToken();
		//System.out.println("当前token -- "+accessToken);
		String url = "https://api.weixin.qq.com/shakearound/statistics/devicelist?access_token="+accessToken;

		JSONObject json = new JSONObject();
		json.put("date",datetime/1000);
		json.put("page_index",pageIdx);
		//System.out.println("request json : " + json);
		JSONObject jsonTmp = json;
        json = new JSONObject();
		json = post(url,jsonTmp);
		return json;
	}



	//批量申请50个微信Id
	public static JSONObject applyWeixinId(){
		String accessToken=getAccessToken();
		//System.out.println("当前token--"+accessToken);
		String url=DEVICE_URI+"applyid?access_token="+accessToken;
		JSONObject json = new JSONObject();
		json.put("quantity", 50);
		json.put("apply_reason", "spread information");
		json.put("comment", "weibangding");
		JSONObject jsonTemp=json;
		json=post(url, jsonTemp);
		return null;
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
	
	/**
	 *
	 * @return 现在可用的AccessToken
	 */
	public static String getAccessToken(){
		try {
			weixin.popular.bean.Token mtoken = PublicAccessTokenAPI.token(WechatConstansUtil.AppID);
				return mtoken.getAccess_token();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
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
}
