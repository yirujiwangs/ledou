package cache;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/22.
 */
public class ResponseQueueCache {

    public static Map<String, HttpServletResponse> codeMap = new HashMap<String, HttpServletResponse>();
    public static int MAX_SIZE = 100;
    public static boolean putServletResponseCache(String code, HttpServletResponse response) {
        if (codeMap.size() > MAX_SIZE)
            codeMap.clear();
        codeMap.put(code, response);
        return true;
    }

    public static HttpServletResponse getServletResponseCache(String response) {
        if (codeMap.containsKey(response)) {
            return codeMap.get(response);
        }
        return null;
    }

    public boolean deleteServletResponseCache(String response) {
        if (codeMap.containsKey(response)) {
            codeMap.remove(response);
        }
        return true;
    }

}
