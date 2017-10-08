package service.Impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import dao.*;
import model.*;

import model.adplatform.ListData;
import model.base.BaseResult;
import model.dto.FinanceStatistics;
import model.dto.StoreAdDeposit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import service.AdminService;
import service.DealerService;
import service.FinanceService;
import service.UserService;
import utils.api.AdControlClient;
import utils.finance.Alipay;
import utils.finance.AlipayNotify;
import utils.finance.FinanceUtil;
import utils.common.StrUtil;

import javax.servlet.http.HttpServletRequest;
import java.sql.*;
import java.util.*;
import java.util.Date;

/**
 * Created by lenovo on 2015/12/5.
 */
@Service
public class FinanceServiceImpl implements FinanceService {

    @Autowired
    private FinanceMapper financeMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private User_infoMapper userInfoMapper;
    @Autowired
    private DepositMapper depositMapper;
    @Autowired
    private RefundMapper refundMapper;
    @Autowired
    private HistoryMapper historyMapper;

    @Autowired
    private ProxyFinanceRecordMapper proxyFinanceRecordMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private AdminService adminService;


    @Autowired
    private DealerService dealerService;

    @Autowired
    private IncomeDetailsProxyMapper incomeDetailsProxyMapper;

    public JSONObject financeManage() {
        JSONObject json;
        int sum = 0;
        int available = 0;
        int freeze = 0;
        JSONArray jsons = new JSONArray();
        //
        List<Finance> finances = financeMapper.selectByExample(null);
        for (Finance finance : finances) {
            UserExample userExample = new UserExample();
            userExample.createCriteria().andPhonenumEqualTo(finance.getPhonenum());
            User user = userMapper.selectByExample(userExample).get(0);
            String username = user.getStorename();
            String remark = userInfoMapper.selectByPrimaryKey(user.getUserinfoid()).getRemark();
            json = new JSONObject();
            json.put("phoneNum", finance.getPhonenum());
            json.put("username", username);
            json.put("totalNum", finance.getSum());
            sum += finance.getSum();
            json.put("freezeNum", finance.getFreeze());
            freeze += finance.getFreeze();
            json.put("availableNum", finance.getAvailable());
            available += finance.getAvailable();

            json.put("sumDeposit", finance.getSumdeposit());

            json.put("sumExpense", finance.getSumexpense());

            json.put("sumRefund", finance.getSumrefund());
            json.put("remark", remark);
            jsons.add(json);
        }
        JSONArray jsons1 = new JSONArray();

        json = new JSONObject();
        json.put("name", "总资产");
        json.put("number", sum);
        jsons1.add(json);
        json = new JSONObject();
        json.put("name", "可用资产");
        json.put("number", available);
        jsons1.add(json);
        json = new JSONObject();
        json.put("name", "冻结资产");
        json.put("number", freeze);
        jsons1.add(json);
        json = new JSONObject();
        json.put("amount", jsons1);
        json.put("allUser", jsons);
        return json;
    }

    //
    public JSONObject depositApply() {
        JSONObject json = new JSONObject();
        DepositExample depositExample = new DepositExample();
        depositExample.createCriteria().andResultEqualTo("");
        List<Deposit> deposits = depositMapper.selectByExample(depositExample);
        if (deposits == null || deposits.size() == 0) {
            json.put("flag", false);
            return json;
        } else {
            JSONArray jsons = new JSONArray();
            for (Deposit deposit : deposits) {
                json = new JSONObject();
                json.put("createTime", deposit.getDate_time());
                json.put("outTradeNo", deposit.getOut_trade_no());

                UserExample userExample = new UserExample();
                userExample.createCriteria().andPhonenumEqualTo(deposit.getPhoneNum());
                String username = userMapper.selectByExample(userExample).get(0).getStorename();
                json.put("phoneNum", deposit.getPhoneNum());
                json.put("username", username);
                json.put("fee", deposit.getFee());
                json.put("remark", deposit.getRemark());
                jsons.add(json);
            }
            json = new JSONObject();
            json.put("allDeposit", jsons);
            return json;
        }
    }

    //
    public JSONObject refundApply() {
        JSONObject json;
        JSONArray jsons = new JSONArray();
        RefundExample refundExample = new RefundExample();
        refundExample.createCriteria().andResultEqualTo("");
        List<Refund> refunds = refundMapper.selectByExample(refundExample);
        for (Refund refund : refunds) {
            json = new JSONObject();
            json.put("createTime", refund.getDate_time());
            json.put("outTradeNo", refund.getOutTradeNo());
            UserExample userExample = new UserExample();
            userExample.createCriteria().andPhonenumEqualTo(refund.getPhonenum());
            String username = userMapper.selectByExample(userExample).get(0).getStorename();
            json.put("phoneNum", refund.getPhonenum());
            json.put("username", username);
            json.put("fee", refund.getFee());
            json.put("applicant", refund.getApplicant());
            json.put("account", refund.getAccount());
            json.put("remark", refund.getRemark());
            jsons.add(json);
        }
        json = new JSONObject();
        json.put("totalRefund", jsons);
        return json;
    }

    //
    public JSONObject rechargeConfirm(String param, String nameString) {
        JSONObject json;
        json = JSON.parseObject(param);
        String outTradeNo = json.getString("outTradeNo");
        json = new JSONObject();
        Deposit deposit = new Deposit();
        deposit.setResult("充值成功");
        deposit.setOut_trade_no(outTradeNo);
        int flag = depositMapper.updateByPrimaryKeySelective(deposit);

        deposit = depositMapper.selectByPrimaryKey(outTradeNo);
        String phoneNum = deposit.getPhoneNum();

        UserExample userExample = new UserExample();
        userExample.createCriteria().andPhonenumEqualTo(phoneNum);
        List<User> users = userMapper.selectByExample(userExample);

        if (users.size() > 0) {
            User user = users.get(0);
            History history = new History(StrUtil.getUUID(),
                    new Date(), user.getStorename(), user.getPhonenum(), "财务操作", "充值确认", nameString, "");
            if (flag == 0) {
                history.setContent("充值确认失败");
                historyMapper.insert(history);
                json.put("flag", false);
            } else {
                historyMapper.insert(history);
                json.put("flag", true);
            }
            return json;
        } else {

            //数据库数据不对应
            json.put("flag", false);
            return json;
        }
    }

    @Transactional
    public JSONObject recashConfirm(String param, String nameString) {
        JSONObject json;
        json = JSON.parseObject(param);
        String outTradeNo = json.getString("outTradeNo");
        Double fee = json.getDouble("fee");
        Refund refund = refundMapper.selectByPrimaryKey(outTradeNo);
        String phoneNum = refund.getPhonenum();
        //true
        int typeFlag = json.getInteger("type");
        String result = null;
        String cause = "";
        String content = "";
        if (typeFlag == 1) {
            content = "同意提现";
            result = "提现成功";
        } else {
            content = "拒绝提现";
            result = "提现失败";
            cause = json.getString("reason");
            FinanceExample financeExample = new FinanceExample();
            financeExample.createCriteria().andPhonenumEqualTo(phoneNum);
            List<Finance> finances = financeMapper.selectByExample(financeExample);
            if (finances.size() <= 0) {
                json = new JSONObject();
                json.put("flag", false);
            }
            Finance finance = finances.get(0);
            finance.setSum(finance.getSum() + fee);
            finance.setAvailable(finance.getAvailable() + fee);
            finance.setSumrefund(finance.getSumrefund() - fee);
            financeMapper.updateByPrimaryKey(finance);
        }
        //Refund refundNew=new Refund();
        refund.setOutTradeNo(outTradeNo);
        refund.setResult(result);
        refund.setCause(cause);
        int flag = refundMapper.updateByPrimaryKey(refund);
        //System.out.println(flag);
        UserExample userExample = new UserExample();
        userExample.createCriteria().andPhonenumEqualTo(phoneNum);
        List<User> users = userMapper.selectByExample(userExample);
        User user = users.get(0);
        History history = new History(StrUtil.getUUID(),
                new Date(), user.getStorename(), user.getPhonenum(), "财务操作", content, nameString, "");
        json = new JSONObject();
        if (flag == 0) {
            history.setContent("确认提现失败");
            json.put("flag", false);
        } else {
            json.put("flag", true);
        }
        historyMapper.insert(history);
        return json;
    }

    /**
     * 获取代理商下属所有商户的财务信息
     * 总资产
     * 冻结资产
     * 可用资产
     * 累积充值
     * 累积提现
     *
     * @param phone 包含代理商身份信息，目前使用phone
     * @return List<Finance>
     * @author yeran
     * @time 2016年8月6日22:51:57
     */
    @Override
    public List<Finance> getBelongShopsFinance(String phone) {
        List<String> phones = userService.getUsersPhoneByProxyPhone(phone);

        FinanceExample financeExample = new FinanceExample();
        financeExample.createCriteria().andPhonenumIn(phones);
        List<Finance> finances = financeMapper.selectByExample(financeExample);
        return finances;
    }

    @Override
    public int countBelongShopsFinanceInfo(String phone) {
        return userService.countUsersByProxyPhone(phone, null);
    }

    @Override
    public int countBelongShopsFinanceInfo(String phone, String keyword) {
        return userService.countUsersByProxyPhone(phone, keyword);
    }

    @Override
    public List<ShopFinance> getBelongShopsFinanceInfo(String phone, Integer startPage, Integer pageSize) {
        return getBelongShopsFinanceInfo(phone, startPage, pageSize, null);
    }


    /**
     * 获取代理商下属所有商户的财务信息
     * 商户信息
     * 总资产
     * 冻结资产
     * 可用资产
     * 累积充值
     * 累积提现
     *
     * @param phone 包含代理商身份信息，目前使用phone
     * @return List<Finance>
     * @author yeran
     * @time 2016年8月6日22:51:57
     */
    @Override
    public List<ShopFinance> getBelongShopsFinanceInfo(String phone, Integer startPage, Integer pageSize, String keyword) {
        List<User> users = userService.getUsersByProxyPhone(phone, startPage, pageSize, keyword);

        if (users != null && users.size() > 0) {
            ShopFinance shopFinance = new ShopFinance();
            //   shopFinance.setDividePage(startPage, pageSize);
            if (keyword != null)
                shopFinance.setKeyword(keyword);

            //System.out.println(shopFinance.limitValue + "--" + shopFinance.startSize + "--" + users.size());

            try {
                List<ShopFinance> shopFinances = financeMapper.selectShopFinance(users, shopFinance);
                if (shopFinances != null) {
                    for (ShopFinance shopFinance1 : shopFinances) {
                        shopFinance1.setProfit(FinanceUtil.Fen2Yuan(shopFinance1.getProfit()));
                        shopFinance1.setSumrefund(FinanceUtil.Fen2Yuan(shopFinance1.getSumrefund()));
                        shopFinance1.setSum(FinanceUtil.Fen2Yuan(shopFinance1.getSum()));
                        shopFinance1.setSumexpense(FinanceUtil.Fen2Yuan(shopFinance1.getSumexpense()));
                        shopFinance1.setAvailable(FinanceUtil.Fen2Yuan(shopFinance1.getAvailable()));
                        shopFinance1.setFreeze(FinanceUtil.Fen2Yuan(shopFinance1.getFreeze()));
                        shopFinance1.setSumdeposit(FinanceUtil.Fen2Yuan(shopFinance1.getSumdeposit()));
                    }
                }
//            for (int i = 0; i < users.size(); i++) {
//                shopFinance = new ShopFinance();
//                shopFinance.setPhonenum(users.get(i).getPhonenum());
//                shopFinance.setStoreName(users.get(i).getStorename());
//
//                FinanceExample financeExample = new FinanceExample();
//                financeExample.createCriteria().andPhonenumEqualTo(users.get(i).getPhonenum());
//
//                List<Finance> finances = financeMapper.selectByExample(financeExample);
//                if (finances != null && finances.size() > 0) {
//                    Finance finance = finances.get(0);
//                    shopFinance.setAvailable(finance.getAvailable());
//                    shopFinance.setFreeze(finance.getFreeze());
//                    shopFinance.setSum(finance.getSum());
//                    shopFinance.setSumdeposit(finance.getSumdeposit());
//                    shopFinance.setSumexpense(finance.getSumexpense());
//                    shopFinance.setSumrefund(finance.getSumrefund());
//                }
//                //增加抽成
//                DepositExample depositExample = new DepositExample();
//                depositExample.createCriteria()
//                        .andPhoneNumEqualTo(users.get(i).getPhonenum());
//                Double benefit = depositMapper.getSumBenefitByTime(depositExample);
//                if (benefit == null)
//                    benefit = 0.0;
//                shopFinance.setProfit(benefit);
//
//                shopFinances.add(shopFinance);
//            }

                //查询每个店铺所有的充值记录
                return shopFinances;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public int countDetailDepositWithTaxes(String phone, Date startDate, Date endDate) {
        List<String> phones = userService.getUsersPhoneByProxyPhone(phone);
        if (startDate == null) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(1971, 1, 1);
            startDate = calendar.getTime();
        }
        if (endDate == null) {
            endDate = new Date();
        }
        return depositMapper.countDepositWithTaxes(new java.sql.Date(startDate.getTime()), new java.sql.Date(endDate.getTime()), phones, null);

    }


    /**
     * 通过代理商电话号码，获取下属所有商家的充值详情
     * 支持条件查询
     *
     * @param phone //代理商电话
     * @return List<DepositWithTaxes>
     * @author yeran
     * @time 2016年8月8日00:24:21
     */
    @Override
    public List<DepositWithTaxes> getDetailDepositWithTaxes(String phone, Date startDate, Date endDate, Integer startPage, Integer pageSize) {
        List<String> phones = userService.getUsersPhoneByProxyPhone(phone);

        if (phones != null && phones.size() > 0) {
            if (startDate == null) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(1971, 1, 1);
                startDate = calendar.getTime();
            }
            if (endDate == null) {
                endDate = new Date();
            }

            DepositWithTaxes depositWithTaxes = new DepositWithTaxes();
            depositWithTaxes.setDividePage(startPage, pageSize);

            List<DepositWithTaxes> depositWithTaxess = depositMapper.selectDepositWithTaxes(
                    new java.sql.Date(startDate.getTime()), new java.sql.Date(endDate.getTime()), phones, depositWithTaxes);
            return depositWithTaxess;
        }
        return null;
    }

    @Override
    public int countStoreDetailDepositWithTaxes(String phone, Date startDate, Date endDate) {
        if (startDate == null) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(1971, 1, 1);
            startDate = calendar.getTime();
        }
        if (endDate == null) {
            endDate = new Date();
        }

        List<String> phones = new ArrayList<>();
        phones.add(phone);

        return depositMapper.countDepositWithTaxes(new java.sql.Date(startDate.getTime()), new java.sql.Date(endDate.getTime()), phones, null);
    }


    /**
     * 通过商户商电话号码，获取下属所有商家的充值详情
     * 支持条件查询
     *
     * @param phone //代理商电话
     * @return List<DepositWithTaxes>
     * @author yeran
     * @time 2016年8月8日00:24:21
     */
    @Override
    public List<DepositWithTaxes> getStoreDetailDepositWithTaxes(String phone, Date startDate, Date endDate, Integer startPage, Integer pageSize) {

        if (startDate == null) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(1971, 1, 1);
            startDate = calendar.getTime();
        }
        if (endDate == null) {
            endDate = new Date();
        }

        List<String> phones = new ArrayList<>();
        phones.add(phone);

        DepositWithTaxes depositWithTaxes = new DepositWithTaxes();
        depositWithTaxes.setDividePage(startPage, pageSize);

        List<DepositWithTaxes> depositWithTaxess = depositMapper.selectDepositWithTaxes(
                new java.sql.Date(startDate.getTime()), new java.sql.Date(endDate.getTime()), phones, depositWithTaxes);

        return depositWithTaxess;
    }

    /**
     * 获取代理商当日、本月、累计的商户充值收益
     * @param phone
     * @return
     */
    @Override
    public Double[] getStoreProxyBenefitByTime(String phone){

        Date startDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        try {
            Double[] benefits=new Double[3];
            //今日收入
            calendar.set(Calendar.HOUR_OF_DAY,0);
            calendar.set(Calendar.MINUTE,0);
            calendar.set(Calendar.SECOND,0);
            Double todayBenefit = getBenefitByTime(phone, calendar.getTime(), null);
            //本月收入

            calendar.set(Calendar.DAY_OF_MONTH, 1);
            Double monthBenefit = getBenefitByTime(phone, calendar.getTime(), null);
            //累计收入
            Double totalBenefit = getBenefitByTime(phone, null, null);

            benefits[0]= todayBenefit;
            benefits[1]= monthBenefit;
            benefits[2]=totalBenefit;
            return benefits;
        }catch (Exception e){
         e.printStackTrace();
        }

        return new Double[]{0.0,0.0,0.0};
    }

    /**
     * 获取时间周期内的收益
     * <p/>
     * 注意，时间的月份需要做减一处理
     *
     * @param phone
     * @param startDate
     * @param endDate
     * @return double/元
     * @author yeran
     * @time 2016年8月16日14:08:51
     */
    public double getBenefitByTime(String phone, Date startDate, Date endDate) {
        if (phone != null) {

            if (startDate == null) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(1971, 1, 1);
                startDate = calendar.getTime();
            }
            if (endDate == null) {
                endDate = new Date();
            }

            ProxyFinanceRecordExample proxyFinanceRecordExample = new ProxyFinanceRecordExample();
            proxyFinanceRecordExample.createCriteria().andProxy_phoneEqualTo(phone)
                    .andCreatetimeBetween(startDate, endDate).andTypeEqualTo(1);
            Double benefit = proxyFinanceRecordMapper.getSumBenefitByTime(proxyFinanceRecordExample);

            if (benefit != null)
                return FinanceUtil.Fen2Yuan(benefit);

        }
        return 0;
    }

    /**
     * 获取时间周期内的收益
     * <p/>
     * 注意，时间的月份需要做减一处理
     *
     * @param phone
     * @param startDate
     * @param endDate
     * @return double/元
     * @vertion 1.0
     * @author yeran
     * @time 2016年8月8日12:59:37
     * @deprecated
     */
    public double getBenefitByTime_v1(String phone, Date startDate, Date endDate) {
        if (phone != null) {

            if (startDate == null) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(1971, 1, 1);
                startDate = calendar.getTime();
            }
            if (endDate == null) {
                endDate = new Date();
            }

            List<String> phones = userService.getUsersPhoneByProxyPhone(phone);
            if (phones != null && phones.size() > 0) {

                DepositExample depositExample = new DepositExample();
                depositExample.createCriteria().andPhoneNumIn(phones).andDateBetween(startDate, endDate);
                Double benefit = depositMapper.getSumBenefitByTime(depositExample);

                if (benefit != null)
                    return FinanceUtil.Fen2Yuan(benefit);
            }
        }
        return 0;
    }

    @Override
    public boolean insertDesport(HttpServletRequest req, String phone) {
        Map<String, String> toGet = new HashMap<>();
        Map<String, String[]> paraAli = req.getParameterMap();
        for (Iterator<String> it = paraAli.keySet().iterator(); it.hasNext(); ) {
            String name =  it.next();
            String[] values = paraAli.get(name);
            toGet.put(name, values[0]);
        }

        String trade_no = req.getParameter("trade_no");// 支付宝交易流水号
        String out_trade_no = req.getParameter("out_trade_no"); // 我司交易订单号
        String trade_status = req.getParameter("trade_status");
        String seller = req.getParameter("seller_id");
        // 总订单钱数，单位元，转化为分
        Double fee = FinanceUtil.Yuan2Fen(Double.parseDouble(req
                .getParameter("total_fee")));

        // 返回参数中取交易创建时间
        String date_time = req.getParameter("gmt_create");
        Timestamp stamp = Timestamp.valueOf(date_time);
        String process = date_time.substring(0, 10);
        java.sql.Date dd = java.sql.Date.valueOf(process);
        String remark = req.getParameter("body"); // 备注字段
        String result = "success"; // 结果字段
        String cause = ""; // 失败或成功的原因

        // 新建充值记录
        Deposit record = new Deposit();
        record.setPhoneNum(phone);
        record.setDate(dd);
        record.setDate_time(stamp);

        record.setOut_trade_no(out_trade_no);
        record.setTrade_no(trade_no);
        record.setRemark(remark);
        record.setFee(fee);

        // 验证是支付宝
        if (AlipayNotify.verify(toGet) && seller.equals(Alipay.seller_id)) {
            if (trade_status.equals("TRADE_SUCCESS")
                    || trade_status.equals("TRADE_FINISHED")) {
                try {
                    //System.out.println("######**notify url验证成功");
                    record.setCause(cause);
                    record.setResult(result);
                    depositMapper.insertSelective(record);

                    return true;
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
        result = "失败";
        cause = "支付宝支付未完成";
        record.setCause(cause);
        record.setResult(result);
        depositMapper.insertSelective(record);
        return false;
    }

    @Override
    public List<DepositWithTaxes> getDetailDepositWithTaxes(String phone, Date startDate, Date endDate, Integer startPage, Integer pageSize, String keyword) {
        List<String> phones = userService.getUsersPhoneByProxyPhone(phone);

        if (phones != null && phones.size() > 0) {
            if (startDate == null) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(1971, 1, 1);
                startDate = calendar.getTime();
            }
            if (endDate == null) {
                endDate = new Date();
            }

            DepositWithTaxes depositWithTaxes = new DepositWithTaxes();
            depositWithTaxes.setDividePage(startPage, pageSize);
            if (keyword != null)
                depositWithTaxes.setKeyword(keyword);

            List<DepositWithTaxes> depositWithTaxess = depositMapper.selectDepositWithTaxes(
                    new java.sql.Date(startDate.getTime()), new java.sql.Date(endDate.getTime()), phones, depositWithTaxes);
            return depositWithTaxess;
        }

        return null;
    }

    @Override
    public int countDetailDepositWithTaxes(String phone, Date startDate, Date endDate, String keyword) {
        List<String> phones = userService.getUsersPhoneByProxyPhone(phone);
        if (startDate == null) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(1971, 1, 1);
            startDate = calendar.getTime();
        }
        if (endDate == null) {
            endDate = new Date();
        }

        DepositWithTaxes depositWithTaxes = new DepositWithTaxes();
        if (keyword != null)
            depositWithTaxes.setKeyword(keyword);
        return depositMapper.countDepositWithTaxes(new java.sql.Date(startDate.getTime()), new java.sql.Date(endDate.getTime()), phones, depositWithTaxes);
    }

    @Override
    public List<DepositWithTaxes> getStoreDetailDepositWithTaxes(String phone, Date startDate, Date endDate, Integer startPage, Integer pageSize, String keyword) {

        if (startDate == null) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(1971, 1, 1);
            startDate = calendar.getTime();
        }
        if (endDate == null) {
            endDate = new Date();
        }

        List<String> phones = new ArrayList<>();
        phones.add(phone);

        DepositWithTaxes depositWithTaxes = new DepositWithTaxes();
        depositWithTaxes.setDividePage(startPage, pageSize);
        if (keyword != null)
            depositWithTaxes.setKeyword(keyword);

        List<DepositWithTaxes> depositWithTaxess = depositMapper.selectDepositWithTaxes(
                new java.sql.Date(startDate.getTime()), new java.sql.Date(endDate.getTime()), phones, depositWithTaxes);

        return depositWithTaxess;
    }

    @Override
    public int countStoreDetailDepositWithTaxes(String phone, Date startDate, Date endDate, String keyword) {
        if (startDate == null) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(1971, 1, 1);
            startDate = calendar.getTime();
        }
        if (endDate == null) {
            endDate = new Date();
        }

        List<String> phones = new ArrayList<>();
        phones.add(phone);

        DepositWithTaxes depositWithTaxes = new DepositWithTaxes();
        if (keyword != null)
            depositWithTaxes.setKeyword(keyword);

        return depositMapper.countDepositWithTaxes(new java.sql.Date(startDate.getTime()), new java.sql.Date(endDate.getTime()), phones, depositWithTaxes);
    }

    @Override
    public StoreFinanceStatistics storeFinanceStatistics(String proxyPhone) {
        List<String> phones = userService.getUsersPhoneByProxyPhone(proxyPhone);
        if (phones != null && phones.size()>0) {
            return financeMapper.storeFinanceStatistics(phones);
        }
        return null;
    }


    @Override
    public FinanceStatistics allFinanceStatics_v2(String phone, String utoken) {
        try {
            FinanceStatistics financeStatistics = new FinanceStatistics();

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.set(Calendar.HOUR_OF_DAY, 23);
            calendar.set(Calendar.MINUTE, 59);
            calendar.set(Calendar.SECOND, 59);
            calendar.set(Calendar.DAY_OF_MONTH, 1);

            calendar.add(Calendar.DATE, -1);
            long etime = calendar.getTimeInMillis();

            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            long stime = calendar.getTimeInMillis();

            IncomeDetailsProxyExample incomeDetailsProxyExample = new IncomeDetailsProxyExample();
            incomeDetailsProxyExample.createCriteria().andUtokenEqualTo(utoken)
                    .andRecord_timeBetween(new java.sql.Date(stime), new java.sql.Date(etime));

            List<IncomeDetailsProxy> incomeDetailsProxys = incomeDetailsProxyMapper.selectByExample(incomeDetailsProxyExample);
            if (incomeDetailsProxys != null && incomeDetailsProxys.size() > 0) {
                IncomeDetailsProxy incomeDetailsProxy = incomeDetailsProxys.get(0);

                financeStatistics.setCombinationDeviceNum(incomeDetailsProxy.getDevice_buy_amount_self() + incomeDetailsProxy.getDevice_buy_amount_dealer_direct());
                financeStatistics.setIndirectDeviceNum(incomeDetailsProxy.getDevice_buy_amount_dealer_indirect());
                financeStatistics.setStoreAdDepositPrice(incomeDetailsProxy.getMoney_deposit_store());
                financeStatistics.setSelfAdPrice(incomeDetailsProxy.getMoney_ad_normal_self());
                financeStatistics.setDirectAdPrice(incomeDetailsProxy.getMoney_ad_normal_dealer_direct());
                financeStatistics.setCombinationAdPrice(financeStatistics.getDirectAdPrice() + financeStatistics.getSelfAdPrice());

                financeStatistics.setCombinationAdBenefit(incomeDetailsProxy.getBenefit_ad_normal_self()
                        + incomeDetailsProxy.getBenefit_ad_normal_direct());
                financeStatistics.setCombinationDeviceBenefit(incomeDetailsProxy.getBenefit_device_self()
                        + incomeDetailsProxy.getDevice_buy_amount_dealer_direct());
                financeStatistics.setDirectAdBenefit(incomeDetailsProxy.getDevice_buy_amount_dealer_direct());
                financeStatistics.setIndirectDeviceBenefit(incomeDetailsProxy.getBenefit_deivce_dealer_indirect());
                financeStatistics.setStoreAdDepositBenefit(incomeDetailsProxy.getBenefit_deposit_store());


                financeStatistics.setActivatedDeviceNum(incomeDetailsProxy.getDevice_activated());
                financeStatistics.setActivatedDeviceBenefit(incomeDetailsProxy.getBenefit_device_activated());

                financeStatistics.setRebateAdBenefit(incomeDetailsProxy.getBenefit_rebate_ad());

                financeStatistics.setStoreMarketingBenefit(incomeDetailsProxy.getBenefit_merchant_market());
                financeStatistics.setStoreMarketingPrice(incomeDetailsProxy.getMerchant_market());
                financeStatistics.setTotalFranchiseArea(incomeDetailsProxy.getFranchise_districts());
                financeStatistics.setTotalFranchiseBenefit(incomeDetailsProxy.getBenefit_franchise_districts());
                financeStatistics.setDistDeviceBuyNum(incomeDetailsProxy.getDevice_buy_amount_dist());
                financeStatistics.setDistDeviceBuyBenefit(incomeDetailsProxy.getBenefit_device_buy_dist());
                financeStatistics.setDist_device_red(incomeDetailsProxy.getDist_device_red());
                financeStatistics.setBenefit_dist_device_red(incomeDetailsProxy.getBenefit_dist_device_red());
                financeStatistics.setDist_merchant_market(incomeDetailsProxy.getDist_merchant_market());
                financeStatistics.setBenefit_dist_merchant_market(incomeDetailsProxy.getBenefit_dist_merchant_market());


                return financeStatistics;

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return new FinanceStatistics();
    }

    /**
     *
     * 默认是上个月的统计值
     *
     * @param phone
     * @param utoken
     * @return
     */
    @Override
    public FinanceStatistics allFinanceStatics(String phone, String utoken) {

        FinanceStatistics financeStatistics = new FinanceStatistics();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);

        calendar.add(Calendar.DATE, -1);
        Date etime = calendar.getTime();

        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date stime = calendar.getTime();


        //组合采购设备数量：自身+直接下级代理
        int myDeviceNum = dealerService.myDevicesNum(utoken,null, stime, etime);
        int directDeviceNum = dealerService.directDealerDeviceNum(utoken, stime, etime);
        int indirectDeviceNum = dealerService.indirectDealerDeviceNum(utoken, stime, etime);

        //设备采购信息
        financeStatistics.setCombinationDeviceNum(myDeviceNum + directDeviceNum);
        financeStatistics.setIndirectDeviceNum(indirectDeviceNum);

        //组合代理商的广告收益
        List<String> utokens = new ArrayList<>();
        utokens.add(utoken);
        utokens.addAll(adminService.dealerTokens(utoken));
        if (utokens.size()>0) {
            //广告金额
            BaseResult baseResult = AdControlClient.adBenefit(null, utokens);
            if (BaseResult.RESULT_SUCCESS == baseResult.getErrcode()) {
                int combinationAdPrice = (int) baseResult.getObject();
                financeStatistics.setCombinationAdPrice(combinationAdPrice);
            }
        }

        //商户广告金充值总金额
//        BaseResult baseResult =  AdControlClient.storeAdDepositSum(null,utoken);
//        if (BaseResult.RESULT_SUCCESS == baseResult.getErrcode()) {
//            int storeAdDepositSum = (int) baseResult.getObject();
//            financeStatistics.setStoreAdDepositPrice(storeAdDepositSum);
//        }

        //计算详细收益



        return financeStatistics;
    }


    @Override
    public List<StoreAdDeposit> storeAdDeposit(String utoken, Integer startPage, Integer pageSize,Integer[] pages) {

        BaseResult baseResult = AdControlClient.storeAdDepositRecord(null, utoken, startPage, pageSize);
        if (BaseResult.RESULT_SUCCESS == baseResult.getErrcode()) {

            String json = JSONObject.toJSONString(baseResult.getObject());
            ListData<StoreAdDeposit> listData = JSONObject.parseObject(json,ListData.class);
            //取得列表
            pages[0] = listData.getPages();
            List<StoreAdDeposit> storeAdDeposits = listData.getList();
            return storeAdDeposits;
        }
        return null;
    }


    /**
     * 店铺的财务流水详情
     */
    public JSONObject userHistory(String param) {
        JSONObject json;
        json = JSON.parseObject(param);
        String phoneNum = json.getString("account");

        DepositExample depositExample = new DepositExample();
        depositExample.createCriteria().andPhoneNumEqualTo(phoneNum).andResultNotEqualTo("");
        depositExample.setOrderByClause("date_time desc");
        List<Deposit> deposits = depositMapper.selectByExample(depositExample);
        //
        RefundExample refundExample = new RefundExample();
        refundExample.createCriteria().andPhonenumEqualTo(phoneNum).andResultNotEqualTo("");
        refundExample.setOrderByClause("date_time desc");
        List refunds = refundMapper.selectByExample(refundExample);
        refunds.addAll(deposits);
        json = new JSONObject();
        json.put("generalHistory", refunds);
        return json;
    }

    public JSONObject searchByTradeNo(String param) {
        JSONObject json;
        json = JSON.parseObject(param);
        String outTradeNo = json.getString("outTradeNo");
        String account = json.getString("account");
        DepositExample depositExample = new DepositExample();
        depositExample.createCriteria().andOut_trade_noEqualTo(outTradeNo).andPhoneNumEqualTo(account);
        depositExample.setOrderByClause("date_time desc");
        List<Deposit> deposits = depositMapper.selectByExample(depositExample);
        if (!deposits.isEmpty()) {
            json = new JSONObject();
            json.put("generalHistory", deposits);
            return json;
        } else {
            RefundExample refundExample = new RefundExample();
            refundExample.createCriteria().andOutTradeNoEqualTo(outTradeNo).andPhonenumEqualTo(account);
            refundExample.setOrderByClause("date_time desc");
            List<Refund> refunds = refundMapper.selectByExample(refundExample);
            json = new JSONObject();
            json.put("generalHistory", refunds);
            return json;
        }

    }

    public JSONObject searchByClause(String param) {
        JSONObject json;
        json = JSON.parseObject(param);
        String type = json.getString("type");
        Date startTime = json.getDate("startTime");
        Date endTime = json.getDate("endTime");
        if (!type.equalsIgnoreCase("all")) {
            if (type.startsWith("充值")) {
                // type=type.substring(2);
                DepositExample depositExample = new DepositExample();
                DepositExample.Criteria criteria = depositExample.createCriteria();
                criteria.andResultEqualTo(type);
                if (startTime != null) {
                    criteria.andDateGreaterThanOrEqualTo(startTime);
                }
                if (endTime != null) {
                    criteria.andDateLessThanOrEqualTo(endTime);
                }
                depositExample.setOrderByClause("date_time desc");
                List<Deposit> deposits = depositMapper.selectByExample(depositExample);
                //System.out.println("###" + deposits.size());
                json = new JSONObject();
                json.put("generalDeposit", deposits);
                return json;
            } else if (type.startsWith("提现")) {
                //type=type.substring(2);
                RefundExample refundExample = new RefundExample();
                RefundExample.Criteria criteria = refundExample.createCriteria();
                criteria.andResultEqualTo(type);
                if (startTime != null) {
                    criteria.andDateGreaterThanOrEqualTo(startTime);
                }
                if (endTime != null) {
                    criteria.andDateLessThanOrEqualTo(endTime);
                }
                refundExample.setOrderByClause("date_time desc");
                List<Refund> refunds = refundMapper.selectByExample(refundExample);
                json = new JSONObject();
                json.put("generalDeposit", refunds);
                return json;
            } else {
                json = new JSONObject();
                json.put("error", "未知错误");
                return json;
            }
        } else {
            DepositExample depositExample = new DepositExample();
            RefundExample refundExample = new RefundExample();
            DepositExample.Criteria criteria = depositExample.createCriteria();
            RefundExample.Criteria criteria1 = refundExample.createCriteria();
            if (startTime != null) {
                criteria.andDateGreaterThanOrEqualTo(startTime);
                criteria1.andDateGreaterThanOrEqualTo(startTime);
            }
            if (endTime != null) {
                criteria.andDateLessThanOrEqualTo(endTime);
                criteria1.andDateLessThanOrEqualTo(endTime);
            }
            List deposits = depositMapper.selectByExample(depositExample);
            List refunds = refundMapper.selectByExample(refundExample);
            deposits.addAll(refunds);
            json = new JSONObject();
            json.put("generalDeposit", deposits);
            return json;
        }
    }

}
