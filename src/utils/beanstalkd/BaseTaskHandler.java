package utils.beanstalkd;

import com.alibaba.fastjson.JSONObject;

public interface BaseTaskHandler {
	
	public void  handle(JSONObject jsonObject);

}
