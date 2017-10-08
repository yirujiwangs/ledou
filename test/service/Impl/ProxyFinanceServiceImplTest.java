package service.Impl;

import com.alibaba.fastjson.JSONObject;
import junit.framework.TestCase;
import model.ProxyFinanceRecord;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import service.ProxyFinanceService;

/**
 * Created by yeran on 2016/8/16.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/conf/spring-mvc.xml","/conf/spring-mybatis.xml","/conf/spring.xml"})
public class ProxyFinanceServiceImplTest extends TestCase {

    @Autowired
    private ProxyFinanceService proxyFinanceService;

    @Test
    public void testGetFinanceRecordDetail() throws Exception {
        JSONObject jsonObject = new JSONObject();
        ProxyFinanceRecord proxyFinanceRecord = new ProxyFinanceRecord();
        proxyFinanceRecord.setId(3);
        proxyFinanceRecord = proxyFinanceService.getFinanceRecordDetail(proxyFinanceRecord);
        jsonObject.put("proxyFinanceRecord",proxyFinanceRecord);
        System.out.println(jsonObject.toJSONString());
    }
}