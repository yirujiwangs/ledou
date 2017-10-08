package service.Impl;

import junit.framework.TestCase;
import model.Admin;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import service.AdminService;

/**
 * Created by yeran on 2016/8/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/conf/spring-mvc.xml","/conf/spring-mybatis.xml","/conf/spring.xml"})
public class AdminServiceImplTest extends TestCase {

    @Autowired
    private AdminService adminService;

    @Test
    public void testUpdatePsw() throws Exception {
        Admin admin = new Admin();
        admin.setId(2);
        admin.setPhoneNum("12345678905");
        //System.out.println(adminService.updatePsw(admin, "test5", "123456"));
    }
}