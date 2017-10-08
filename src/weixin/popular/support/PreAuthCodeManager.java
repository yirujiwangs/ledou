package weixin.popular.support;

import weixin.popular.api.PreAuthCodeTokenAPI;
import weixin.popular.bean.component.PreAuthCode;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Timer;

/**
 * 预授权码管理 20分钟失效，18分钟进行更新
 * 
 * 
 * @author yeran
 *
 */
public class PreAuthCodeManager {

	private static Map<String, String> tokenMap = new LinkedHashMap<String, String>();

	private static Map<String, Timer> timerMap = new HashMap<String, Timer>();

	private static final long time = 1000 * 60 * 18;
	
	private static long lastTime = 0;


	public static String init(final String component_appid) {

		String component_access_token = ComponentAccessTokenManager
				.getToken(component_appid);
		//System.out.println("component_access_token=" + component_access_token);
		if (component_access_token != null) {
			PreAuthCode token = PreAuthCodeTokenAPI.token(component_appid,
					component_access_token);
			if (tokenMap.containsKey(component_appid))
				tokenMap.remove(component_appid);
			tokenMap.put(component_appid, token.getPre_auth_code());
			
			lastTime = System.currentTimeMillis();
			
			return token.getPre_auth_code();
		}

		return null;
	}

	/**
	 * 取消 token 刷新
	 */
	public static void destroyed() {
		for (Timer timer : timerMap.values()) {
			timer.cancel();
		}
	}

	/**
	 * 获取 access_token
	 * 
	 * @param appid
	 * @return
	 */
	public static String getToken(String appid) {
		if (refreshToken(appid)) {
			return init(appid);
		}
		return tokenMap.get(appid);
	}

	public static boolean refreshToken(String appid) {
		if (!tokenMap.containsKey(appid)|| (System.currentTimeMillis() - lastTime > time)) {
			return true;
		}
		return false;
	}


	public static String getDefaultToken() {
		Object[] objs = tokenMap.values().toArray();
		return objs.length > 0 ? objs[0].toString() : null;
	}


	public static String cleanToken(final String component_appid,
			final String component_access_token) {
		PreAuthCode token = PreAuthCodeTokenAPI.token(component_appid,
				component_access_token);
		tokenMap.put(component_appid, token.getPre_auth_code());

		return token.getPre_auth_code();
	}

}
