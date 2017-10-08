package cache;

import weixin.popular.bean.SnsToken;

import java.util.HashMap;
import java.util.Map;

/**
 * 微信授权code缓存器，防止用于返回后重复进入导致的微信code重用bug
 * 
 * 
 * @author yeran
 * @time 2016年10月13日04:09:53
 */
public class AuthCodeCache {

	public static Map<String, SnsToken> codeMap = new HashMap<String, SnsToken>();
	public static int MAX_SIZE = 100;

	public static boolean putCodeAuthCache(String code, SnsToken snsToken) {
		if (codeMap.size() > MAX_SIZE)
			codeMap.clear();
		codeMap.put(code, snsToken);
		return true;
	}

	public static SnsToken getCodeAuthCache(String code) {
		if (codeMap.containsKey(code)) {
			return codeMap.get(code);
		}
		return null;
	}

	public boolean deleteCodeAuthCache(String code) {
		if (codeMap.containsKey(code)) {
			codeMap.remove(code);
		}
		return true;
	}

}
