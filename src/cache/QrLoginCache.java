package cache;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by miaolu on 16-11-8.
 */

public class QrLoginCache {
    public static Map<String,Long>  codeMap = new HashMap<>();
    public static int MAX_SIZE = 100;

    public static boolean addAviTimeCache(String code, Long availableTime){
        if(codeMap.size() > MAX_SIZE){
            codeMap.clear();
        }
        codeMap.put(code,availableTime);
        return true;
    }

    public static Long getAviTime(String code){
        if(codeMap.containsKey(code)){
            return codeMap.get(code);
        }
        return null;
    }

    public static boolean deleteAviTime(String code){
        if(codeMap.containsKey(code)){
            codeMap.remove(code);
            return true;
        }

        return false;
    }

}
