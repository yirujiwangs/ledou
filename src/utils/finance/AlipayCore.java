package utils.finance;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AlipayCore {
	
    //除去数组中的空值和签名参数
    public static Map<String, String> paraFilter(Map<String, String> sArray) {

        Map<String, String> result = new HashMap<String, String>();
        if (sArray == null || sArray.size() <= 0) {
            return result;
        }
        for (String key : sArray.keySet()) {
            String value = sArray.get(key);
            if (value == null || value.equals("") || key.equalsIgnoreCase("sign")
                || key.equalsIgnoreCase("sign_type")) {
                continue;
            }
            result.put(key, value);
        }

        return result;
    }
    

    //把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
    public static String createLinkString(Map<String, String> params) {

        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);
        StringBuffer result = new StringBuffer("");
        
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);

            if (i == keys.size()-1) {       //拼接时，不包括最后一个&字符
            	result.append(key).append("=").append(value);
            } else {
            	result.append(key).append("=").append(value).append("&");        
            }
        }
        return result.toString();
    }
    
    //得到加密后map参数串
    public static Map<String, String> buildRequestPara(Map<String, String> sParaTemp) {
        Map<String, String> sPara = AlipayCore.paraFilter(sParaTemp);
        String res = AlipayCore.createLinkString(sPara);
	    String md5String = AlipayMD5.sign(res, Alipay.key, Alipay.input_charset);
        sPara.put("sign", md5String);
        sPara.put("sign_type", Alipay.sign_type);
        return sPara;        
    }
    


}
