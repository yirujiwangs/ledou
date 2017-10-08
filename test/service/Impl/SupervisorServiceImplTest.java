package service.Impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import dao.AdminMapper;
import dao.IncomeDetailsProxyMapper;
import dao.ProxyFinanceMapper;
import junit.framework.TestCase;
import model.Admin;
import model.IncomeDetailsProxy;
import model.IncomeDetailsProxyExample;
import model.ProxyFinance;
import model.base.BaseResult;
import model.dto.AdminCity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import service.AdminService;
import service.DealerService;
import service.ProxyFinanceService;
import service.SupervisorService;
import utils.api.AdBaseResult;
import utils.common.LogUtil;
import utils.finance.FinanceUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/8/15.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/conf/spring-mvc.xml","/conf/spring-mybatis.xml","/conf/spring.xml"})
public class SupervisorServiceImplTest extends TestCase {

    @Autowired
    private SupervisorService supervisorService;

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private AdminService adminService;

    @Autowired
    private IncomeDetailsProxyMapper incomeDetailsProxyMapper;

    @Autowired
    private DealerService dealerService;

    @Autowired
    private ProxyFinanceMapper proxyFinanceMapper;

    @Autowired
    private ProxyFinanceService proxyFinanceService;

    @Test
    public void testOverview()  {

        JSONObject json = new JSONObject();
        json.put("test", 1);

        JSONObject jsons = supervisorService.overview(json.toJSONString());

        System.out.println("overview : " + jsons);

    }


    @Test
    public void proxy_info_test(){
       String str = "{\"error\":0,\"error_reason\":\"\u67e5\u8be2\u8d26\u6237\u7edf\u8ba1\u6210\u529f\",\"object\":{\"money\":0,\"pmoney\":0}}";
        AdBaseResult adBaseResult = JSONObject.parseObject(str, AdBaseResult.class);

    }

    @Test
    public void proxyFinance_test() throws ParseException {
        BaseResult baseResult = new BaseResult();
        String startMonth = "2017-07";//month of year : 2017-05
        String endMonth = "2017-07";
        String utoken =null;// "a251aa5133c24e6d9618eeee2ca4309c";

     //   boolean result = supervisorService.settleProxyIncome(utoken, startMonth, endMonth);

        long etime,stime;
        Calendar calendar = Calendar.getInstance();
        if(startMonth!=null && endMonth!=null){

            stime = new SimpleDateFormat("yyyy-MM").parse(startMonth).getTime();
            etime = new SimpleDateFormat("yyyy-MM").parse(endMonth).getTime();
            calendar.setTimeInMillis(etime);
            calendar.add(Calendar.MONTH,1);
            calendar.add(Calendar.DATE,-1);
            calendar.set(Calendar.HOUR_OF_DAY, 23);
            calendar.set(Calendar.MINUTE, 59);
            calendar.set(Calendar.SECOND, 59);
            etime= calendar.getTimeInMillis();
        }else {
            calendar.setTime(new Date());
            calendar.set(Calendar.HOUR_OF_DAY, 23);
            calendar.set(Calendar.MINUTE, 59);
            calendar.set(Calendar.SECOND, 59);
            calendar.set(Calendar.DAY_OF_MONTH, 1);

            calendar.add(Calendar.DATE, -1);
            etime = calendar.getTimeInMillis();

            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            stime = calendar.getTimeInMillis();
        }


        boolean result = testTest(utoken,stime,etime);

        if (result) {
            baseResult.setErrcode(BaseResult.RESULT_SUCCESS);
            baseResult.setErrmsg("执行清算任务已经推送至队列，预计5-10分钟内完成清算");
        }

        System.out.println(JSONObject.toJSONString(baseResult));
    }

    public boolean testTest( String uToken, Long stime, Long etime){
        List<Admin> admins = adminService.getAllProxy();

        if (admins!=null && admins.size()>0) {
            //执行各个代理商的资金结算
            //整个过程根据不同指标，分为：设备购买、设备购买奖励、广告投放金额、广告奖励、商户充值金额、商户充值奖励

            for (Admin admin : admins) {
                final String utoken = admin.getProxy_token();
                final String phone = admin.getAccount();

                if (uToken != null) {
                    if (!utoken.equals(uToken)) {
                        continue;
                    }
                }

                java.sql.Date sDate = new java.sql.Date(stime);
                java.sql.Date eDate = new java.sql.Date(etime);

                System.out.println("" + sDate + "--" + eDate);

                try {
                    //需要判断这个月份的数据是否已经被清算过
                    //执行查询
                    IncomeDetailsProxyExample incomeDetailsProxyExample = new IncomeDetailsProxyExample();
                    incomeDetailsProxyExample.createCriteria()
                            .andUtokenEqualTo(utoken).andRecord_timeEqualTo(sDate);

                    List<IncomeDetailsProxy> incomeDetailsProxies = incomeDetailsProxyMapper.selectByExample(incomeDetailsProxyExample);
                    if (incomeDetailsProxies != null && incomeDetailsProxies.size() > 0) {
                        LogUtil.log(this.getClass(), LogUtil.LogType.ERROR, "此代理商" + utoken + "在：" + sDate.toString() + "时间的奖励已经被清算过！");
                     //   continue;
                    //  执行设备奖励更新
                        IncomeDetailsProxy incomeDetailsProxy = incomeDetailsProxies.get(0);
                        //我的设备采购量
                        int myDeviceNum = dealerService.myDevicesNum(utoken, null, sDate, eDate);

                        //直接下级代理设备采购量
                        int directDeviceNum = dealerService.directDealerDeviceNum(utoken, sDate, eDate);
                        //间接代理设备采购量
                        int indirectDeviceNum = dealerService.indirectDealerDeviceNum(utoken, sDate, eDate);

                        incomeDetailsProxy.setDevice_buy_amount_dealer_direct(directDeviceNum);
                        incomeDetailsProxy.setDevice_buy_amount_self(myDeviceNum);
                        incomeDetailsProxy.setDevice_buy_amount_dealer_indirect(indirectDeviceNum);


                        int selfDevices = incomeDetailsProxy.getDevice_buy_amount_self();
                        int directDevices = incomeDetailsProxy.getDevice_buy_amount_dealer_direct();
                        int inDirectDevices = incomeDetailsProxy.getDevice_buy_amount_dealer_indirect();

                        int combineDevices = selfDevices+ directDevices;
                        int benefitSelfDevices = benefitDirectDevicePurchase(combineDevices,selfDevices);
                        int benefitDirectDevices = benefitDirectDevicePurchase(combineDevices, directDevices);
                        int benefitIndirectDevice = benefitIndirectDevicePurchase(inDirectDevices);

                        incomeDetailsProxy.setBenefit_device_self(benefitSelfDevices);
                        incomeDetailsProxy.setBenefit_device_dealer_direct(benefitDirectDevices);
                        incomeDetailsProxy.setBenefit_deivce_dealer_indirect(benefitIndirectDevice);

                        incomeDetailsProxyMapper.updateByPrimaryKeySelective(incomeDetailsProxy);

                        int income = benefitSelfDevices + benefitDirectDevices + benefitIndirectDevice;

                        System.out.println("myDeviceNum=="+myDeviceNum+"income==" + income);

                        //执行此代理商收益总和增加
                        ProxyFinance proxyFinance = proxyFinanceService.selectProxyFinanceByPhone(phone);
                        if (proxyFinance != null) {
                            proxyFinance.setSum_income(FinanceUtil.Yuan2Fen(proxyFinance.getSum_income()) + income);
                            proxyFinance.setAvaiable(FinanceUtil.Yuan2Fen(proxyFinance.getAvaiable()) + income);
                            proxyFinance.setBalanced(FinanceUtil.Yuan2Fen(proxyFinance.getBalanced()));
                            proxyFinance.setBalancing(FinanceUtil.Yuan2Fen(proxyFinance.getBalancing()));
                            //执行收益表更新
                           int rows = proxyFinanceMapper.updateByPrimaryKey(proxyFinance);
                            if (rows > 0) {
                                // 代理商收益更新成功
                                System.out.println("代理商收益更新成功");

                            }
                        }
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
        return true;
    }

    /**
     *
     * 组合采购奖励，单位分
     *
     * @param nums
     * @param trueNums
     * @return
     */
    private int benefitDirectDevicePurchase(int nums,int trueNums){
        int benefitScore = 0;
        if (nums<200){
            benefitScore = 0;
        }else if (nums<300){
            benefitScore =  3;
        }else if(nums < 500){
            benefitScore = 4;
        }else if (nums<1000){
            benefitScore = 6;
        }else if(nums<5000){
            benefitScore = 9;
        }else if(nums<10000){
            benefitScore = 12;
        }else {
            benefitScore = 15;
        }

        return benefitScore * 100 * trueNums;
    }

    private int benefitIndirectDevicePurchase(int nums){
        int benefitScore = 0;
        if (nums<500){
            benefitScore = 1;

        }else if (nums<1000){
            benefitScore =  2;

        }else if(nums < 2000){
            benefitScore = 3;

        }else if (nums<5000){
            benefitScore = 4;

        }else if(nums<1000){
            benefitScore = 5;

        }else {
            benefitScore = 6;
        }

        return benefitScore * 100 * nums;
    }

}