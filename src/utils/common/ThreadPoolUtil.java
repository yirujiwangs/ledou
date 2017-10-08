package utils.common;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池管理控制工具
 * 
 * @author yeran
 *
 */
public class ThreadPoolUtil {
	
	public static ScheduledThreadPoolExecutor poolExecutor = new ScheduledThreadPoolExecutor(3*JVMUtil.cpuNum());
	
	public static <T extends Runnable > void addPool(T t){
		poolExecutor.execute(t);
	}
	
	public static <T extends Runnable > void addPool(T t,int delayTime){
		poolExecutor.schedule(t, delayTime, TimeUnit.MILLISECONDS);
	}
	
	public static ScheduledThreadPoolExecutor getThreadPool(){
		return poolExecutor;
	}

}
