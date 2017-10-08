import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import junit.framework.TestCase;
import model.DepositWithTaxes;
import model.ProxyFinanceRecord;
import model.ShopFinance;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import service.FinanceService;
import service.ProxyFinanceService;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by yeran on 2016/8/6.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/conf/spring-mvc.xml", "/conf/spring-mybatis.xml", "/conf/spring.xml"})
public class TestFinanceService extends TestCase {

    @Autowired
    FinanceService financeService;

    @Autowired
    ProxyFinanceService proxyFinanceService;

    @Test
    public void Test_getBelongShopsFinance_key() {
        String phone = "12345678905";

        System.out.println(financeService.countBelongShopsFinanceInfo(phone,"绿豆芽"));

        List<ShopFinance> finances = financeService.getBelongShopsFinanceInfo(phone, 1, 15,"绿豆芽");
        JSONArray objects = new JSONArray();
        if (finances != null)
            objects.addAll(finances);

        System.out.println(objects);
    }

    @Test
    public void Test_getBelongShopsFinance() {
        String phone = "12345678905";

        System.out.println(financeService.countBelongShopsFinanceInfo(phone,null));

        List<ShopFinance> finances = financeService.getBelongShopsFinanceInfo(phone, 2, 8);
        JSONArray objects = new JSONArray();
        if (finances != null)
            objects.addAll(finances);

        System.out.println(objects);
    }

    @Test
    public void Test_getDetailDepositWithTaxes() {
        String phone = "12345678905";
        Calendar calendar = Calendar.getInstance();
        calendar.set(2016, 6, 26);
        Date startDate = calendar.getTime();
        Date endDate = new Date(2016, 7, 28);
        List<DepositWithTaxes> finances = financeService.getDetailDepositWithTaxes(phone, null, null, 1, 2, "绿豆芽");
        JSONArray objects = new JSONArray();
        if (finances!=null)
        objects.addAll(finances);

        System.out.println(objects);
    }


    @Test
    public void Test_() {
        String phone = "12345678905";
        int pages = financeService.countBelongShopsFinanceInfo(phone);
        List<ShopFinance> belongShopsFinanceInfo = financeService.getBelongShopsFinanceInfo(phone, 1, 15);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("belongShopsFinanceInfo", belongShopsFinanceInfo);
        System.out.println(pages + "--" + jsonObject.toString());
    }


    @Test
    public void Test_getBenefitByTime() {
        String phone = "12345678905";
        Calendar calendar = Calendar.getInstance();
        calendar.set(2016, 6, 26);
        Date startDate = calendar.getTime();
        Date endDate = new Date(2016, 7, 28);
        double benefit = financeService.getBenefitByTime(phone, startDate, null);

        System.out.println("benefit==" + benefit);
    }

    @Test
    public void Test_BenefitStatistics() {
        String phone = "18392576541";
        Date startDate = new Date();
        //��������
        Double todayBenefit = financeService.getBenefitByTime(phone, startDate, startDate);
        //��������
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Double monthBenefit = financeService.getBenefitByTime(phone, calendar.getTime(), null);
        //�ۼ�����
        Double totalBenefit = financeService.getBenefitByTime(phone, null, null);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("todayBenefit", todayBenefit);
        jsonObject.put("monthBenefit", monthBenefit);
        jsonObject.put("totalBenefit", totalBenefit);

        System.out.println(jsonObject);
    }


    @Test
    public void Test_ProxyGetFinanceDetails() {
        String phone = "15245688777";
        Calendar calendar = Calendar.getInstance();
        calendar.set(2015, 6, 26);
        Date startDate = calendar.getTime();
        Date endDate = new Date(2016, 7, 28);

        System.out.println("count==" + proxyFinanceService.countProxyFinanceRecord(phone, 2, null, null, null));
        List<ProxyFinanceRecord> proxyFinanceRecords =
                proxyFinanceService
                        .getProxyFinanceRecord(phone, ProxyFinanceRecord.PROXY_FINANCE_RECORD_REFUND, null, null, null, 1, 1);

        JSONArray objects = new JSONArray();
        objects.addAll(proxyFinanceRecords);

        System.out.println(objects);
    }

    @Test
    public void Test_getStoreDepositDetail_key() {
        String phone = "18392576541";
        Calendar calendar = Calendar.getInstance();
        calendar.set(2016, 6, 26);
        Date startDate = calendar.getTime();
        Date endDate = new Date(2016, 7, 28);
        List<DepositWithTaxes> apply = financeService.getStoreDetailDepositWithTaxes(phone, null, endDate, 1, 2, "20160801002122344073");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", apply);
        System.out.println(jsonObject.toJSONString());
    }
    @Test
    public void Test_getStoreDepositDetail() {
        String phone = "18392576541";
        Calendar calendar = Calendar.getInstance();
        calendar.set(2016, 6, 26);
        Date startDate = calendar.getTime();
        Date endDate = new Date(2016, 7, 28);
        List<DepositWithTaxes> apply = financeService.getStoreDetailDepositWithTaxes(phone, null, endDate, 1, 2,null);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", apply);
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void Test_refundApply() {
        String phone = "18710819416";
        double money = 20;
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("contract_phone", "18710847308");
        jsonObject.put("alipay_account_name", "yeran");
        jsonObject.put("alipay_account_num", "18710847308");
        jsonObject.put("bank_account_num", "");
        jsonObject.put("bank_account_name", "");
        jsonObject.put("bank_name", "");

        Integer apply = proxyFinanceService.refundApply(phone, money, jsonObject);

        System.out.println(apply);
    }

    @Test
    public void Test_getProxyFinanceRecord() {
        String phone = "18710819415";
        Integer type = 2;
        Date startDate = null, endDate = null;
        Integer state = 0;
        int startPage = 2, pageSize = 1;
        List<ProxyFinanceRecord> proxyFinanceRecord = proxyFinanceService.getProxyFinanceRecord(phone, type, startDate, endDate, state, startPage, pageSize);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("proxyFinanceRecord", proxyFinanceRecord);
        System.out.println(jsonObject.toString());
    }

}
