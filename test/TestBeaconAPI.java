import org.junit.Test;
import org.junit.runner.RunWith;
import utils.api.BeaconAPI;
import com.alibaba.fastjson.JSONObject;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

/**
 * Created by Administrator on 2016/10/6.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/conf/spring-mvc.xml","/conf/spring-mybatis.xml","/conf/spring.xml"})

public class TestBeaconAPI {

    @Test
    public void Test_getProxyDeviceInfo() throws IOException {
        String pToken ="ledouya";
        JSONObject json = new JSONObject();

        json.put("proxyToken","84fdafd69bfa593478f26139e78599b2");
        json.put("groupId",-1);
        json.put("startPage",1);
        json.put("pageSize",8);

        BeaconAPI.getProxyDeviceInfo(null,json.toJSONString());
    }

}
