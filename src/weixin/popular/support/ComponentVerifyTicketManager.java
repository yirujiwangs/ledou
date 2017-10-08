package weixin.popular.support;

import weixin.popular.bean.component.ComponentReceiveXML;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 推送component_verify_ticket协议
 * 
 * 10分钟变化一次
 * 
 * @author yeran
 *
 */
public class ComponentVerifyTicketManager {
	private static Map<String, String> tokenMap = new LinkedHashMap<String, String>();

	/**
	 * 取消 token 刷新
	 */
	public static void destroyed() {
		tokenMap.clear();
	}

	/**
	 * 获取 access_token
	 * 
	 * @param appid
	 * @return
	 */
	public static String getToken(String appid) {
		if (firstToken(appid))
			return null;
		return tokenMap.get(appid);
	}

	public static boolean firstToken(String appid) {
		if (!tokenMap.containsKey(appid)) {
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
	 * 更新Token
	 * 
	 * @param component
	 */
	public static void updateToken(ComponentReceiveXML component) {
		if (component != null) {
			String componentVerifyTicket = component.getComponentVerifyTicket();

			if (tokenMap.containsKey(component.getAppid())) {
				tokenMap.remove(component.getAppid());
			}
			tokenMap.put(component.getAppid(), componentVerifyTicket);
		}
	}
}
