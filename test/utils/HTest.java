package utils;

/**
 * Created by yeran on 2017/5/1.
 */
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import controller.DeviceOrderController;
import model.base.BaseResult;
import org.junit.Test;
import utils.api.AdControlClient;
import utils.common.LogUtil;
import weixin.popular.bean.paymch.MchBaseResult;
import weixin.popular.bean.paymch.Unifiedorder;
import weixin.popular.util.XMLConverUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class HTest {

    @Test
    public void test(){
        MchBaseResult mchBaseResult = new MchBaseResult();
        mchBaseResult.setReturn_code("SUCCESS");
        mchBaseResult.setReturn_msg("OK");
        System.out.println(XMLConverUtil.convertToXML(mchBaseResult));

//        Unifiedorder unifiedorder = new Unifiedorder();
//        unifiedorder.setAppid("dsfds");
//        unifiedorder.setBody("该换个环境");
//        unifiedorder.setDevice_info("rftyrt");
//        unifiedorder.setMch_id("7808");
//        unifiedorder.setNotify_url("sdsdgg");
//        unifiedorder.setOut_trade_no("34345665");
//        unifiedorder.setSpbill_create_ip("dghfgg");
//        unifiedorder.setTotal_fee("123");
//        unifiedorder.setTrade_type("dfd");
//        unifiedorder.setNonce_str("retytu");
//        String unifiedorderXML = XMLConverUtil.convertToXML(unifiedorder);
//
//        System.out.println(unifiedorderXML);
    }


    @Test
    public void test3(){
//        StringBuilder stringBuilder = new StringBuilder("18710847308");
//        String  str = stringBuilder.replace(3,7, "****").toString();
//        System.out.println(str);

//        java.sql.Date date = new java.sql.Date(1494428421);
//        System.out.println(date.toString());
//        System.out.println(date.getTime());
    }


    @Test
    public void test1(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.DAY_OF_MONTH, 1);

        calendar.add(Calendar.DATE, -1);
        long etime = calendar.getTimeInMillis();
        System.out.println(calendar.get(Calendar.MONTH) + "-" + calendar.get(Calendar.DAY_OF_MONTH) + "-" + etime);

        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        calendar.set(Calendar.DAY_OF_MONTH, 1);
        long stime = calendar.getTimeInMillis();

        java.sql.Date date = new java.sql.Date(stime);
        System.out.println(date.toString());

        date = new java.sql.Date(etime);
        System.out.println(date.toString());
        System.out.println(calendar.get(Calendar.MONTH)+"-"+calendar.get(Calendar.DAY_OF_MONTH)+"-"+stime);
    }

    @Test
    public void test2() throws ParseException {
        String utoken = "3cd4dc9d13084b3fb9a4b03fbb5aeeb5";
        List<String> utokens = new ArrayList<>();
        utokens.add(utoken);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.DAY_OF_MONTH, 1);

        calendar.add(Calendar.DATE, -1);
        long etime = calendar.getTimeInMillis();
        System.out.println(calendar.get(Calendar.MONTH) + "-" + calendar.get(Calendar.DAY_OF_MONTH) + "-" + etime);

        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        calendar.set(Calendar.DAY_OF_MONTH, 1);
        long stime = calendar.getTimeInMillis();

        java.sql.Date sdate = new java.sql.Date(stime);
        java.sql.Date edate = new java.sql.Date(etime);
        BaseResult baseResult = AdControlClient.storeAdDepositSum(null, utoken, sdate, edate);

    }

    @Test
    public void test4(){
        String utoken = "d6077adb440649fe94e6891343c14868";
        BaseResult baseResult = AdControlClient.deviceBenefit(utoken, null, null);
        if (BaseResult.RESULT_SUCCESS == baseResult.getErrcode()) {
            System.out.println(baseResult.getObject().toString());
            JSONObject jsonObject = JSON.parseObject(baseResult.getObject().toString());
            System.out.println(jsonObject.getInteger("num")+"-"+jsonObject.getInteger("benefit"));
//            deviceActivatedBenefit = (int) baseResult.getObject();
        } else {
            LogUtil.log(this.getClass(), LogUtil.LogType.ERROR, "直接代理商广告金额清算出现问题：" + utoken);

        }
    }
}
