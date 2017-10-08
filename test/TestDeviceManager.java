import com.alibaba.fastjson.JSONObject;

import dao.PayOrdersMapper;
import junit.framework.TestCase;

import model.PayOrders;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import service.DealerService;
import service.DeviceService;

/**
 * Created by yeran on 2016/8/7.
 */


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/conf/spring-mvc.xml","/conf/spring-mybatis.xml","/conf/spring.xml"})
public class TestDeviceManager extends TestCase{

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private PayOrdersMapper payOrdersMapper;

    @Autowired
    private DealerService dealerService;

    @Test
    public void Test_DeviceManager() {
        try {
            PayOrders payOrders = new PayOrders();
            payOrders.setAppid("12");
            payOrders.setBody("da");
            payOrders.setMch_id("dasd");
            payOrders.setTrade_type("dsa");
            payOrders.setNonce_str("dasda");
            payOrders.setNotify_url("dsasds");
            payOrders.setOpenid(null);
            payOrders.setOut_trade_no("dasdasd");
            payOrders.setSpbill_create_ip("dasd");
            payOrders.setTotal_fee(1);
            payOrders.setUtoken("sdadsa");
            payOrders.setPrepay_id("dasda");
            payOrders.setSign("dasdadsa");

            int id = payOrdersMapper.insertSelective(payOrders);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


        @Test
        public void searchUnbindTest(){
            JSONObject json = new JSONObject();
            json.put("keyword", "4");
            System.out.println("searchUnbind: " + deviceService.searchUnbind(5,json.toJSONString()));

        }

        @Test
        public void testDevice(){
            String uToken = "bfcc2c550b8948f390d279b876066162";
            java.sql.Date sDate = new  java.sql.Date(1498869946000L);
            java.sql.Date eDate = new  java.sql.Date(1501461946000L);
            int myDeviceNum = dealerService.myDevicesNum(uToken,null, sDate, eDate);
            System.out.println("nim=="+myDeviceNum+"---"+sDate + "--"+eDate);
        }

//       JSONObject jsonObject = deviceService.deviceManage(1);
//        System.out.println(jsonObject);

}
