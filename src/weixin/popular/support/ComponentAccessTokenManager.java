package weixin.popular.support;

import utils.common.WechatConstansUtil;
import weixin.popular.api.ComponentAccessTokenAPI;
import weixin.popular.bean.component.ComponentAccessToken;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Timer;

public class ComponentAccessTokenManager {
	private static Map<String, String> tokenMap = new LinkedHashMap<String, String>();

	private static Map<String, Timer> timerMap = new HashMap<String, Timer>();

	private static final long time = 1000 * 60 * 115;
	private static long lastTime = 0;

	/**
	 * 初始化token 刷新，每118分钟刷新一次。
	 * 
	 * @param component_appid
	 * @param component_appsecret
	 * @param component_verify_ticket
	 */
	public static String init(final String component_appid,
			final String component_appsecret,
			final String component_verify_ticket) {

		ComponentAccessToken token = ComponentAccessTokenAPI.token(
				component_appid, component_appsecret, component_verify_ticket);
		tokenMap.remove(component_appid);
		tokenMap.put(component_appid, token.getComponent_access_token());
		
		//System.out.println("getComponent_access_token="+token.getComponent_access_token());

		lastTime = System.currentTimeMillis();

		return token.getComponent_access_token();
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
			String componentVerifyTicket = ComponentVerifyTicketManager
					.getToken(appid);
			//System.out.println("componentVerifyTicket=" + componentVerifyTicket);
			if (componentVerifyTicket != null)
				return init(appid, WechatConstansUtil.PUBLIC_APPSECRET,
						componentVerifyTicket);
			else
				return null;
		}
		return tokenMap.get(appid);
	}

	public static boolean refreshToken(String appid) {
		if (!tokenMap.containsKey(appid)
				|| (System.currentTimeMillis() - lastTime > time)) {
			return true;
		}
		return false;
	}

	/**
	 * 获取第一个appid 的 access_token 适用于单一微信号
	 *
	 * @return
	 */
	public static String getDefaultToken() {
		Object[] objs = tokenMap.values().toArray();
		return objs.length > 0 ? objs[0].toString() : null;
	}

	/**
	 * 清除缓存，直接获取最新的token
	 * 
	 * @param component_appid
	 * @param component_appsecret
	 * @param component_verify_ticket
	 */
	public static String cleanToken(final String component_appid,
			final String component_appsecret,
			final String component_verify_ticket) {
		ComponentAccessToken token = ComponentAccessTokenAPI.token(
				component_appid, component_appsecret, component_verify_ticket);
		tokenMap.put(component_appid, token.getComponent_access_token());

		return token.getComponent_access_token();
	}
}
