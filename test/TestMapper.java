import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import dao.ProxyAreaMapper;
import dao.ProxyFinanceMapper;
import model.ProxyFinance;
import model.base.ProxyArea;
import model.base.ProxyAreaExample;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import service.DealerService;

import java.util.Calendar;
import java.util.Date;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/conf/spring-mvc.xml","/conf/spring-mybatis.xml","/conf/spring.xml"})
public class TestMapper {

    @Autowired ProxyAreaMapper proxyAreaMapper;

    @Autowired
    DealerService dealerService;

    @Autowired
    ProxyFinanceMapper proxyFinanceMapper;

    //@Autowired ProxyAreaService proxyAreaService;

    @Test
    public void testMapper(){
        ProxyAreaExample proxyAreaExample = new ProxyAreaExample();
        //proxyAreaExample.setDistinct(true);

        ProxyAreaExample.Criteria criteria = proxyAreaExample.createCriteria();
        criteria.andSupertokenEqualTo("0");

        proxyAreaExample.or().andSupertokenEqualTo("110");

        List<ProxyArea> proxyAreaList = proxyAreaMapper.selectByExample(proxyAreaExample);
        for(ProxyArea proxyArea : proxyAreaList){
            System.out.println(proxyArea.getArea_rid());
        }
    }

    @Test
    public void testDealerService(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.DAY_OF_MONTH, 1);

        calendar.add(Calendar.DATE, -1);
        Date etime = calendar.getTime();

        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date stime = calendar.getTime();

        //我的上月设备采购量
        int lastDecivesNum = dealerService.myDevicesNum("3869457006a84fa28886d4c1e546defb","N",stime,etime);
        //我的本月设备采购量
        int nowDecivesNum = dealerService.myDevicesNum("3869457006a84fa28886d4c1e546defb","N",etime,null);


        //累计设备采购数
        int sumDevicesNum = dealerService.myDevicesNum("3869457006a84fa28886d4c1e546defb", "N", null, null);

        System.out.println(nowDecivesNum);
        System.out.println(sumDevicesNum);

    }

    @Test
    public void testSelectByKeywords(){
        JSONObject pageData = new JSONObject();
        //pageData.put("startTime", "2017-08-01");
        //pageData.put("endTime", "2017-08-07");
        //pageData.put("keyword", "17802929721");
        String param = JSON.toJSONString(pageData);
        JSONObject json = JSON.parseObject(param);
        String keyword = json.getString("keyword");
        Date startTime = json.getDate("startTime");
        Date endTime = json.getDate("endTime");
        ProxyFinance proxyFinance = proxyFinanceMapper.selectByKeywords(startTime, endTime, keyword);
        System.out.println("sum: "+proxyFinance.getSum_income()+" "+
                        "balanced: "+proxyFinance.getBalanced()+" "+
                        "balancing: "+proxyFinance.getBalancing()+" "+
                        "avaiable: "+proxyFinance.getAvaiable()+" ");
    }
}
