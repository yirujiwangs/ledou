package utils.serialnum;

import java.util.Random;

/**
 * 序列化生成器
 * @author yeran
 *
 */
public class SerialNumCreater {

	public static <T extends BaseSerialNumCreater> String serialNum(T t){
		return t.create();
	}

	public static  String serialNum(Class<? extends BaseSerialNumCreater> t) {
		try {
			return t.newInstance().create();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String pageInfoSerialNumCreate(){
		return null;
	}


	/**
	 * 普通序列号生成器
	 * @param size
	 * @return
	 */
	public static String nommonSerialCreate(int size){
		String base = "abcdefghijklmnopqrstuvwxyz0123456789";

		Random random = new Random();

		StringBuffer sb = new StringBuffer();

		for (int i=0;i<size;i++){
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

	/**
	 * 代理商序列号生成，采用的是UUID生成机制
	 * @return
	 *
	 */
	public static String ProxyTokenCreate(){
		return serialNum(UuidSerialNumCreater.getInstance());
	}

}
