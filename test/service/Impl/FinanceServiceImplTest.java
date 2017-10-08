package service.Impl;

import com.alibaba.fastjson.JSONObject;
import junit.framework.TestCase;
import model.StoreFinanceStatistics;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import service.FinanceService;

/**
 * Created by yeran on 2016/8/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/conf/spring-mvc.xml","/conf/spring-mybatis.xml","/conf/spring.xml"})
public class FinanceServiceImplTest extends TestCase {

    @Autowired
    FinanceService financeService;


    @Test
    public void testStoreFinanceStatistics() throws Exception {
    StoreFinanceStatistics storeFinanceStatistics = financeService.storeFinanceStatistics("12345678905");
        JSONObject jsonObject = new JSONObject();
        if (storeFinanceStatistics!=null) {
            jsonObject.put("storeFinanceStatistics",storeFinanceStatistics);
            System.out.println(jsonObject.toString());
        }
    }
}