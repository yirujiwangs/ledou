package utils.common;

import java.util.Random;

public class RandomNonceUtil {
	public static final String[] args = new String[] { "0", "1", "2", "3", "4",
			"5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H",
			"I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
			"V", "W", "X", "Y", "Z" };

	public static long onlyNumb = 1000;

	/**
	 * 创建随机数 在0-Z范围内
	 * 
	 * @param length
	 * */
	public static String createString(int length) {
		if (length > -1) {
			Random random = new Random();
			StringBuffer buffer = new StringBuffer(32);
			for (int i = 0; i < length; i++) {
				buffer.append(args[random.nextInt(args.length)]);
			}
			return buffer.toString();
		} else
			return null;

	}
}
