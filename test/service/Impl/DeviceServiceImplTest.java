package service.Impl;

import com.alibaba.fastjson.JSONObject;
import junit.framework.TestCase;
import model.DeviceStatistics;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import service.DeviceService;

/**
 * Created by yeran on 2016/8/17.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/conf/spring-mvc.xml", "/conf/spring-mybatis.xml", "/conf/spring.xml"})
public class DeviceServiceImplTest extends TestCase {

    @Autowired
    private DeviceService deviceService;

    @Test
    public void testUnbinding() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("serialNum", "3624212");
        String param = jsonObject.toString();
        deviceService.unbinding(param, 1);
    }

    @Test
    public void testStoreDeviceStatistics() throws Exception {
        String storePhone = "18392576541";
        JSONObject jsonObject = new JSONObject();
        DeviceStatistics deviceStatistics = deviceService.storeDeviceStatistics(storePhone);
        jsonObject.put("deviceStatistics", deviceStatistics);
        System.out.println(jsonObject.toJSONString());
    }
}