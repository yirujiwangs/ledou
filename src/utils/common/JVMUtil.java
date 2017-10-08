package utils.common;


/**
 * 一些关于JVM或系统的小工具
 * @author yeran
 *
 */
public class JVMUtil {

	/**
	 * 返回cpu核心数
	 * @return
	 */
	public static int cpuNum(){
		return Runtime.getRuntime().availableProcessors();
	}
}
