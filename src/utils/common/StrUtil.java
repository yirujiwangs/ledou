package utils.common;

import java.util.UUID;

public class StrUtil {
	/**
	 * 生成16位Id
	 * @return
	 */
	public static String getUUID(){
		
		String uuid=UUID.randomUUID().toString().substring(16);
		return uuid.replaceAll("-", "");
		
	}

	public static boolean isEmpty(String str){
		return !(str != null && !str.isEmpty());
	}
	
}
