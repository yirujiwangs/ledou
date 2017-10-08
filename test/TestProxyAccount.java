
import com.alibaba.fastjson.JSONObject;
import junit.framework.TestCase;
import model.Admin;
import model.ProxyAccountInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import service.AdminService;
import service.ProxyAccountService;
import service.ProxyFinanceService;

/**
 * Created by yeran on 2016/8/11.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/conf/spring-mvc.xml", "/conf/spring-mybatis.xml", "/conf/spring.xml"})
public class TestProxyAccount extends TestCase{

    @Autowired
    ProxyFinanceService proxyFinanceService;

    @Autowired
    ProxyAccountService proxyAccountService;


    @Autowired
    AdminService adminService;

    @Test
    public void Test_getProxyInfo() {
        Admin admin = new Admin();
        admin.setId(5);
        ProxyAccountInfo proxyAccountInfo = adminService.getProxyAccountInfo(admin);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("proxyAccountInfo", proxyAccountInfo);
        System.out.println(jsonObject.toString());
    }
}
