package utils.finance;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.UnsupportedEncodingException;

/*
 * 支付宝签名验证MD5算法
 */

public class AlipayMD5 {

	    public static String sign(String text, String key, String input_charset) {
	    	text = text + key;
	        return DigestUtils.md5Hex(getContentBytes(text, input_charset));
	    }
	    
       //也是验证 但是有支付宝返回的sign  可验证此消息是否是来自alipay的
	    public static boolean verify(String text, String sign, String key, String input_charset) {
	    	text = text + key;
	    	String mysign = DigestUtils.md5Hex(getContentBytes(text, input_charset));
	    	if(mysign.equals(sign)) {
	    		return true;
	    	}
	    	else {
	    		return false;
	    	}
	    }

	    /*
	     * @param content
	     * @param charset
	     * @return
	     * @throws SignatureException
	     * @throws UnsupportedEncodingException 
	     */
	    private static byte[] getContentBytes(String content, String charset) {
	        if (charset == null || "".equals(charset)) {
	            return content.getBytes();
	        }
	        try {
	            return content.getBytes(charset);
	        } catch (UnsupportedEncodingException e) {
	            throw new RuntimeException("MD5签名过程中出现错误,指定的编码集不对,您目前指定的编码集是:" + charset);
	        }
	    }
	

}
