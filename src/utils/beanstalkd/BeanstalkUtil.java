package utils.beanstalkd;

import java.io.UnsupportedEncodingException;

import com.alibaba.fastjson.JSONObject;

public class BeanstalkUtil {

	
	public static String BEANSTALK_SERVICE_IP="139.129.14.187";
	public static Integer BEANSTALK_SERVICE_PORT=11300;
	
	public static Integer MAX_POOL_SIZE = 30;
	
	private static 
		BeanstalkPool pool = new BeanstalkPool(BEANSTALK_SERVICE_IP, BEANSTALK_SERVICE_PORT, MAX_POOL_SIZE);
	
	/**
	 * Example for using an unpooled client
	 * @throws BeanstalkException 
	 */
	public static void clientInit() throws BeanstalkException {
		
		
		BeanstalkClient client = pool.getClient();
		
		System.out.println("Putting a job");
		client.put(1l, 0, 5000, "this is some data".getBytes());
		BeanstalkJob job = client.reserve(60);
		System.out.println("GOt job: " + job);
		client.deleteJob(job);
		client.close();  //returns the connection to the pool
	}
	
	
	/**
	 * 推送消息至队列
	 * 
	 * @param obj
	 * @param tube
	 * @throws BeanstalkException
	 */
	public static Long put(JSONObject obj,String tube) throws BeanstalkException{
		return put(obj, tube, null);
	}
	
	/**
	 * 延时队列消息推送
	 * 
	 * @param obj
	 * @param tube
	 * @param delay
	 * @throws BeanstalkException
	 * 
	 */
	public static Long put(JSONObject obj,String tube,Integer delay) throws BeanstalkException{
		if(delay==null)
			delay=0;
		BeanstalkClient client = pool.getClient();
		if(client==null){
			client = new BeanstalkClient(BeanstalkUtil.BEANSTALK_SERVICE_IP,
					BeanstalkUtil.BEANSTALK_SERVICE_PORT);
		}
		client.useTube(tube);
		String str = obj.toJSONString();
		Long id = client.put(1l, delay, 5000, str.getBytes());
		client.close();  //returns the connection to the pool
		
		System.out.println("推送消息至队列=="+str+"延时"+delay+"秒生效，job id="+id);
		
		return id;
	}
	
	
	public static BeanstalkClient getClient(){
		try {
			return pool.getClient();
		} catch (BeanstalkException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public static void consumeJob(String tube,BaseTaskHandler handler) throws BeanstalkException, UnsupportedEncodingException{
		BeanstalkClient client = pool.getClient();
		client.watchTube(tube);
		System.out.println("监听"+tube+"...");
		BeanstalkJob job;
		while((job = client.reserve(60))!=null){
			byte[] bs = job.getData();
			String str = new String(bs);
			
			handler.handle(JSONObject.parseObject(str));
			client.deleteJob(job);
		}
		
	}


	/**
	 * 清除指定tube中的job
	 * 
	 * @param jobId
	 * @param tube
	 * @throws BeanstalkException 
	 */
	public static void delJob(Long jobId, String tube) throws BeanstalkException {
		BeanstalkClient client = pool.getClient();
		client.watchTube(tube);
		client.deleteJob(jobId);
		client.close();
	}
	
	
}
