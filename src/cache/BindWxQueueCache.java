package cache;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/22.
 */
public class BindWxQueueCache {
    public static Map<String, String> codeMap = new HashMap<>();
    public static int MAX_SIZE = 100;
    public static boolean putBindOpenIdCache(String code, String openid) {
        if (codeMap.size() > MAX_SIZE)
            codeMap.clear();
        codeMap.put(code, openid);
        return true;
    }

    public static String getBindOpenId(String code) {
        if (codeMap.containsKey(code)) {
            return codeMap.get(code);
        }
        return null;
    }

    public static boolean deleteBindOpenId(String code) {
        if (codeMap.containsKey(code)) {
            codeMap.remove(code);
        }
        return true;
    }
}
