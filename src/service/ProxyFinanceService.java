package service;

import com.alibaba.fastjson.JSONObject;
import model.Admin;
import model.FinanceTaxes;
import model.ProxyFinance;
import model.ProxyFinanceRecord;

import java.util.Date;
import java.util.List;

/**
 * Created by yeran on 2016/8/8.
 */
public interface ProxyFinanceService {

    /**
     * 申请结算
     * @param phone
     * @param money
     * @param jsonObject
     * @return
     */
    Integer refundApply(String phone, Double money, JSONObject jsonObject);

    ProxyFinance selectProxyFinanceByPhone(String phone);



    FinanceTaxes getTaxesByPhone(Admin admin);

    ProxyFinanceRecord getFinanceRecordDetail(ProxyFinanceRecord proxyFinanceRecord);

    List<ProxyFinanceRecord> getProxyFinanceRecord(
            String phone, Integer type, Date startDate, Date endDate, Integer state, Integer startPage, Integer pageSize);

    int countProxyFinanceRecord(String phone, Integer type, Date startDate, Date endDate, Integer state);
}
