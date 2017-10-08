import com.alibaba.fastjson.JSONObject;
import junit.framework.TestCase;
import model.Device;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import service.SupervisorService;
import utils.common.ExcelUtil;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/conf/spring-mvc.xml","/conf/spring-mybatis.xml","/conf/spring.xml"})
public class TestSuperviorService extends TestCase {

    @Autowired
    private SupervisorService supervisorService;

//    @Autowired
//    private TokenMapper tokenMapper;
//
//    @Autowired
//    private WxStatisticsMapper wxStatisticsMapper;
//
//    @Autowired
//    private WxdevdataMapper wxdevdataMapper;



    @Test
    public void Test_overview(){
        JSONObject json = new JSONObject();
        json.put("test", 1);

        JSONObject jsons = supervisorService.overview(json.toJSONString());

        System.out.println("overview : " + jsons);
//        getwxStatistics();

    }

    @Test
    public void tendencyChartTest(){
        JSONObject json = new JSONObject();
        json.put("showIndex","shakepv");
        json.put("startTime",System.currentTimeMillis()-(long)(86400000*10));
        json.put("endTime",System.currentTimeMillis());

        JSONObject resultJson = new JSONObject();
        resultJson = supervisorService.tendencyChart(json.toJSONString());

        System.out.println("resultJson : " + resultJson);
    }


    @Test
    public void accountManageTest(){
        JSONObject json = new JSONObject();
        json.put("startPage",1);
        json.put("pageSize",5);

        JSONObject resultJson = new JSONObject();
        resultJson = supervisorService.accountManage(json.toJSONString());
        System.out.println("resultJson : "+resultJson);

    }


    @Test
    public void insertAccountTest(){
        JSONObject json = new JSONObject();

        json.put("account","123456789012");
        json.put("accountName","miaoye");
        json.put("phoneNum","123456789012");
        json.put("remark","this is miaoye");
        json.put("status","true");
        json.put("password","123456");

        System.out.println("insertAccount : " +supervisorService.insertAccount(json.toJSONString()));

    }

    @Test
    public void updateStatusTest(){
        JSONObject json = new JSONObject();
        json.put("account","123456789012");
        //json.put("status","flase");
    //    System.out.println("updateStatus : " + supervisorService.updateStatus(json.toJSONString()));

    }

    @Test
    public void updateRemarkTest(){
        JSONObject json = new JSONObject();
        json.put("account","123456789012");
        json.put("remark","hello ~~~");
        System.out.println("updateRemark : " + supervisorService.updateRemark(json.toJSONString()));

    }


    @Test
    public void accountListByStatusTest(){
        JSONObject json = new JSONObject();
        json.put("startPage",1);
        json.put("pageSize",10);
        json.put("status","false");
        System.out.println("accountListByStatus : " + supervisorService.accountListByStatus(json.toJSONString()));
    }

    @Test
    public void deviceManageTest(){
        JSONObject json = new JSONObject();
        json.put("startPage",1);
        json.put("pageSize",5);
        System.out.println("deviceManage : "+supervisorService.deviceManage(json.toJSONString()));
    }


    @Test
    public void accountOrderListTest(){
        JSONObject json = new JSONObject();
        json.put("startPage",1);
        json.put("pageSize",10);
        json.put("state",null);
        System.out.println("accountOrderList : "+supervisorService.accountOrderList(json.toJSONString()));
    }


    @Test
    public void financeManageTest(){
        JSONObject json = new JSONObject();
        json.put("startPage",1);
        json.put("pageSize",5);
        System.out.println("financeManage : "+supervisorService.financeManage(json.toJSONString()));
    }


    @Test
    public void settlementCenterTest(){
        JSONObject json = new JSONObject();
        json.put("startPage",1);
        json.put("pageSize",3);
        json.put("tradeState",1);
        System.out.println("settlementCenter : "+supervisorService.settlementCenter(json.toJSONString()));
    }

    @Test
    public void financeOrderProcessTest(){
        JSONObject json = new JSONObject();
        json.put("tradeId",4);
        json.put("tradeState",2);
        System.out.println("settlementCenter : " + supervisorService.financeOrderProcess(json.toJSONString()));
    }

    @Test
    public void settlemnetOrderDetailTest(){
        JSONObject json = new JSONObject();
        json.put("tradeId",2);
        System.out.println("settlemnetOrderDetail : " + supervisorService.settlemnetOrderDetail(json.toJSONString()));
    }


    @Test
    public void accountManageSearchTest(){
        JSONObject json = new JSONObject();
        json.put("keyword","this");
        json.put("startTime",System.currentTimeMillis()-(long)(86400000*1));
//        json.put("endTime",System.currentTimeMillis());
        json.put("startPage",1);
        json.put("pageSize",4);
        System.out.println("accountManageSearch : " + supervisorService.accountManageSearch(json.toJSONString()));
    }


    @Test
    public void deviceManageSearchTest(){
        JSONObject json = new JSONObject();
        json.put("keyword","test5");
        json.put("startTime",System.currentTimeMillis()-(long)(86400000*20));
//        json.put("endTime",System.currentTimeMillis());
        json.put("startPage",1);
        json.put("pageSize",4);
        System.out.println("deviceManageSearch : " + supervisorService.deviceManageSearch(json.toJSONString()));
    }

    @Test
    public void financeManageSearchTest(){
        JSONObject json = new JSONObject();
        json.put("keyword","miao");
        //json.put("startTime",System.currentTimeMillis()-(long)(86400000)*50);
        json.put("startTime",0);
//        json.put("endTime",System.currentTimeMillis());
        json.put("endTime",7289625355000L);
        json.put("startPage",2);
        json.put("pageSize",5);
        System.out.println("financeManageSearch : " + supervisorService.financeManageSearch(json.toJSONString()));

    }

    @Test
    public void accountOrderSearchTest(){
        JSONObject json = new JSONObject();
        json.put("keyword","17802929721");
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 20);
        date = calendar.getTime();
        System.out.println(date.getTime());
        json.put("startTime", date.getTime());
//        json.put("startTime",System.currentTimeMillis()-(long)(86400000*20));
//        json.put("endTime",System.currentTimeMillis());
        json.put("startPage",1);
        json.put("pageSize",4);
        System.out.println("accountOrderSearch : " + supervisorService.accountOrderSearch(json.toJSONString()));
    }

    @Test
    public void settlementSearchTest(){
        JSONObject json = new JSONObject();
        json.put("keyword","miao");
        json.put("startTime",System.currentTimeMillis()-(long)(86400000*20));
//        json.put("endTime",System.currentTimeMillis());
        json.put("startPage",1);
        json.put("pageSize",4);
        System.out.println("settlementSearch : " + supervisorService.settlementSearch(json.toJSONString()));
    }


    @Test
    public void unbindDevManageTest(){
        JSONObject json = new JSONObject();
        json.put("startPage",1);
        json.put("pageSize",4);
        System.out.println("unbindDevManage : " + supervisorService.unbindDevManage(json.toJSONString()));
    }


    @Test
    public void updateUnbindDevRemarkTest(){
        JSONObject json = new JSONObject();
        json.put("serialNum","197602389933");
        json.put("remark","I am SuperMan");
        System.out.println("updateUnbindDevRemark : " + supervisorService.updateUnbindDevRemark(json.toJSONString()));

    }

    @Test
    public void GetExcelDataTest() throws IOException {
        String path = "aaa";
        List<Device> deviceList = ExcelUtil.GetExcelData(path);
        System.out.println("deviceList: "+deviceList);
    }
//
//    public void getwxStatistics(){
//
//
//
//        Date lastUpdateDate = wxStatisticsMapper.getLastDateTime();
//        long lastUpdateTime =0;
//
//        if(lastUpdateDate != null){
//            lastUpdateTime = lastUpdateDate.getTime();
//        }
//
//        System.out.println("lastUpdateTime : " +lastUpdateTime);
//
//        SimpleDateFormat sdf = new SimpleDateFormat("HH");
//        int currentHour = Integer.parseInt(sdf.format(System.currentTimeMillis()));
//        System.out.println("currentHour : "+ currentHour);
//
//        if(currentHour < 8 && currentHour>0){
//            return;
//        }
//
//        while(lastUpdateTime == 0 || lastUpdateTime/86400000 < System.currentTimeMillis()/86400000 -1){
//
//            if(lastUpdateTime == 0){
//                lastUpdateTime = System.currentTimeMillis() -(long) 86400000*89;
//
//                System.out.println("currentTime : "+ System.currentTimeMillis());
//                System.out.println("lastUpdateTime : "+ lastUpdateTime);
//            }
//            else if( (lastUpdateTime/86400000) == (System.currentTimeMillis()/86400000 -1)){
//                break;
//            }
//            else{
//                lastUpdateTime+=86400000;
//            }
//
//            int total_count = 0;
//            int daySpv = 0,daySuv=0,dayCpv=0,dayCuv=0;
//            WxStatistics ws = new WxStatistics();
//            int  pageIdx = 0;
//
//            do{
//                pageIdx++;
//
//                Token token = new Token();
//                TokenExample tokenExample = new TokenExample();
//                tokenExample.createCriteria().andIdIsNotNull();
//                token = tokenMapper.selectByExample(tokenExample).get(0);
//
//
//                JSONObject wxJson = wxUtil.getWxDevStatistics(token, tokenMapper,lastUpdateTime,pageIdx);
//
//                total_count = wxJson.getInteger("total_count");
//                System.out.println("errmsg : " + wxJson.getString("errmsg") + "   totalCount : " + total_count);
//
//                String devices = wxJson.getJSONObject("data").getString("devices");
//                JSONArray deviceJson = JSON.parseArray(devices);
//                JSONObject jsonTmp = new JSONObject();
//                Wxdevdata wd = new Wxdevdata();
//
//
//                for(int j=0;j<deviceJson.size();j++){
//                    System.out.println(deviceJson.get(j));
//                    jsonTmp = JSON.parseObject(deviceJson.get(j).toString());
//
//                    wd.setDeviceid(jsonTmp.getString("device_id"));
//                    wd.setShakepv(jsonTmp.getInteger("shake_pv"));
//                    wd.setShakeuv(jsonTmp.getInteger("shake_uv"));
//                    wd.setClickpv(jsonTmp.getInteger("click_pv"));
//                    wd.setClickuv(jsonTmp.getInteger("click_uv"));
//                    wd.setDate(new Date(lastUpdateTime));
//
//                    wxdevdataMapper.insertSelective(wd);
//
//                    daySpv+=jsonTmp.getInteger("shake_pv");
//                    daySuv+=jsonTmp.getInteger("shake_uv");
//                    dayCpv+=jsonTmp.getInteger("click_pv");
//                    dayCuv+=jsonTmp.getInteger("click_uv");
//
//
//
//                    wd = new Wxdevdata();
//                }
//
//            }while(total_count == 50);
//
//            ws.setDate(new Date(lastUpdateTime));
//            ws.setShakepv(daySpv);
//            ws.setShakeuv(daySuv);
//            ws.setClickpv(dayCpv);
//            ws.setClickuv(dayCuv);
//
//            wxStatisticsMapper.insertSelective(ws);
//            ws = new WxStatistics();
//        }
//
//    }


}
