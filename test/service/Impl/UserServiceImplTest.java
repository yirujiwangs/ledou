package service.Impl;

import com.alibaba.fastjson.JSONArray;
import junit.framework.TestCase;
import model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import service.UserService;

import java.util.List;

/**
 * Created by yeran on 2016/8/16.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/conf/spring-mvc.xml","/conf/spring-mybatis.xml","/conf/spring.xml"})
public class UserServiceImplTest extends TestCase {

    @Autowired
    private UserService userService;

    @Test
    public void testGetUsersByProxyPhone() throws Exception {
        System.out.println( userService.countUsersByProxyPhone("12345678905", "绿豆芽"));
       List<User> userlist = userService.getUsersByProxyPhone("12345678905",1,10,"绿豆芽");
        JSONArray objects = new JSONArray();
        objects.addAll(userlist);
        System.out.println(objects.toJSONString());
    }
}