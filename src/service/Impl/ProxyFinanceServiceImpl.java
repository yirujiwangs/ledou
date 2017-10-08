package service.Impl;

import com.alibaba.fastjson.JSONObject;
import dao.IncomeDetailsProxyMapper;
import dao.ProxyFinanceMapper;
import dao.ProxyFinanceRecordMapper;
import model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.ProxyFinanceService;
import utils.finance.FinanceUtil;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by yeran on 2016/8/8.
 */

@Service
public class ProxyFinanceServiceImpl implements ProxyFinanceService {

    @Autowired
    ProxyFinanceMapper proxyFinanceMapper;

    @Autowired
    ProxyFinanceRecordMapper proxyFinanceRecordMapper;

    @Autowired
    private IncomeDetailsProxyMapper incomeDetailsProxyMapper;

    /**
     * 申请结算
     *
     * @param phone
     * @param money
     * @param jsonObject
     */
    @Override
    public Integer refundApply(String phone, Double money, JSONObject jsonObject) {
        if (phone != null && money > 0) {
            String name = jsonObject.getString("name");
            String contract_phone = jsonObject.getString("contract_phone");
            String bank_account_name = jsonObject.getString("bank_account_name");
            String bank_account_num = jsonObject.getString("bank_account_num");
            String bank_name = jsonObject.getString("bank_name");
            String alipay_account_name = jsonObject.getString("alipay_account_name");
            String alipay_account_num = jsonObject.getString("alipay_account_num");
            try {
                synchronized (this) {
                    ProxyFinance proxyFinance = proxyFinanceMapper.selectByPhone(phone);
                    if (proxyFinance == null)
                        return -1;
                    Double available = proxyFinance.getAvaiable();
                    if (money > available)
                        return 0;

                    proxyFinance.setAvaiable(available - money);
                    proxyFinance.setBalancing(proxyFinance.getBalancing() + money);

                    ProxyFinanceExample proxyFinanceExample = new ProxyFinanceExample();
                    proxyFinanceExample.createCriteria().andIdEqualTo(proxyFinance.getId());

                    //更新代理商财务信息
                    proxyFinanceMapper.updateByExample(proxyFinance, proxyFinanceExample);
                }
                //添加代理商提现记录，更新标记位
                ProxyFinanceRecord proxyFinanceRecord = new ProxyFinanceRecord();
                proxyFinanceRecord.setFee(money);
                proxyFinanceRecord.setProxy_phone(phone);
                proxyFinanceRecord.setType(ProxyFinanceRecord.PROXY_FINANCE_RECORD_REFUND);
                proxyFinanceRecord.setState(ProxyFinanceRecord.PROXY_FINANCE_DEPOSIT_WAITING);
                proxyFinanceRecord.setContract_phone(contract_phone);
                proxyFinanceRecord.setAlipay_account_name(alipay_account_name);
                proxyFinanceRecord.setAlipay_account_num(alipay_account_num);
                proxyFinanceRecord.setBank_account_num(bank_account_num);
                proxyFinanceRecord.setBank_account_name(bank_account_name);
                proxyFinanceRecord.setBank_name(bank_name);
                proxyFinanceRecord.setContract_name(name);

                proxyFinanceRecordMapper.insertSelective(proxyFinanceRecord);

                return 1;
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return -1;
    }



    @Override
    public ProxyFinance selectProxyFinanceByPhone(String phone) {
        if (phone != null) {
            ProxyFinanceExample proxyFinanceExample = new ProxyFinanceExample();
            proxyFinanceExample.createCriteria().andPhoneNumEqualTo(phone);
            List<ProxyFinance> proxyFinances = proxyFinanceMapper.selectByExample(proxyFinanceExample);
            if (proxyFinances != null && proxyFinances.size() > 0) {
                ProxyFinance proxyFinance = proxyFinances.get(0);
                proxyFinance.setBalancing(FinanceUtil.Fen2Yuan(proxyFinance.getBalancing()));
                proxyFinance.setAvaiable(FinanceUtil.Fen2Yuan(proxyFinance.getAvaiable()));
                proxyFinance.setBalanced(FinanceUtil.Fen2Yuan(proxyFinance.getBalanced()));
                proxyFinance.setSum_income(FinanceUtil.Fen2Yuan(proxyFinance.getSum_income()));

                return proxyFinance;
            }

        }

        return null;
    }

    @Override
    public FinanceTaxes getTaxesByPhone(Admin admin) {
        if (admin != null) {
            return proxyFinanceMapper.getFinanceTaxes(admin);
        }
        return null;
    }

    @Override
    public ProxyFinanceRecord getFinanceRecordDetail(ProxyFinanceRecord proxyFinanceRecord) {
        if (proxyFinanceRecord == null)
            return null;
        Integer id = proxyFinanceRecord.getId();
        if (id != null && id > 0) {
            return proxyFinanceRecordMapper.selectByPrimaryKey(id);
        }
        return null;
    }

    @Override
    public int countProxyFinanceRecord(String phone, Integer type, Date startDate, Date endDate, Integer state) {
        if (startDate == null) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(1971, 1, 1);
            startDate = calendar.getTime();
        }
        if (endDate == null) {
            endDate = new Date();
        }

        ProxyFinanceRecordExample proxyFinanceRecordExample = new ProxyFinanceRecordExample();
        ProxyFinanceRecordExample.Criteria criteria = proxyFinanceRecordExample.createCriteria().andProxy_phoneEqualTo(phone)
                .andCreatetimeBetween(startDate, endDate);
        if (type != null)
            criteria.andTypeEqualTo(type);
        if (state != null)
            criteria.andStateEqualTo(state);

        return proxyFinanceRecordMapper.countByExample(proxyFinanceRecordExample);
    }

    /**
     * 提现记录 分页
     *
     * @param phone
     * @param type
     * @param startDate
     * @param endDate
     * @param state
     * @return
     */
    @Override
    public List<ProxyFinanceRecord> getProxyFinanceRecord(
            String phone, Integer type, Date startDate, Date endDate, Integer state, Integer startPage, Integer pageSize) {

        if (startDate == null) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(1971, 1, 1);
            startDate = calendar.getTime();
        }

        if (endDate == null) {
            endDate = new Date();
        }

        ProxyFinanceRecordExample proxyFinanceRecordExample = new ProxyFinanceRecordExample();
        ProxyFinanceRecordExample.Criteria criteria = proxyFinanceRecordExample.createCriteria().andProxy_phoneEqualTo(phone)
                .andCreatetimeBetween(startDate, endDate);
        if (type != null)
            criteria.andTypeEqualTo(type);
        if (state != null)
            criteria.andStateEqualTo(state);

        proxyFinanceRecordExample.setDividePage(startPage, pageSize);

        List<ProxyFinanceRecord> proxyFinanceRecords = proxyFinanceRecordMapper.selectByExample(proxyFinanceRecordExample);

        if (proxyFinanceRecords != null && proxyFinanceRecords.size() > 0) {
            //金额转化为元
            for (int i = 0; i < proxyFinanceRecords.size(); i++) {
                double fee = proxyFinanceRecords.get(i).getFee();
                proxyFinanceRecords.get(i).setFee(FinanceUtil.Fen2Yuan(fee));
            }
        }
        return proxyFinanceRecords;
    }


}
