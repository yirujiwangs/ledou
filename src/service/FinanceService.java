package service;

import com.alibaba.fastjson.JSONObject;
import model.DepositWithTaxes;
import model.Finance;
import model.ShopFinance;
import model.StoreFinanceStatistics;
import model.dto.FinanceStatistics;
import model.dto.StoreAdDeposit;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * Created by lenovo on 2015/12/5.
 */
public interface FinanceService {
    JSONObject financeManage();
    JSONObject depositApply();

    JSONObject rechargeConfirm(String param, String nameString);
    JSONObject recashConfirm(String param, String nameString);

    List<StoreAdDeposit> storeAdDeposit(String utoken, Integer startPage, Integer pageSize, Integer[] pages);

    JSONObject userHistory(String param);
    JSONObject searchByTradeNo(String param);
    //type and time
    JSONObject searchByClause(String param);

    List<Finance> getBelongShopsFinance(String phone);

    int countBelongShopsFinanceInfo(String phone);

    int countBelongShopsFinanceInfo(String phone,String keyword);

    List<ShopFinance> getBelongShopsFinanceInfo(String phone, Integer startPage, Integer pageSize);

    List<ShopFinance> getBelongShopsFinanceInfo(String phone, Integer startPage, Integer pageSize,String keyword);

    int countDetailDepositWithTaxes(String phone,Date startDate, Date endDate);

    List<DepositWithTaxes> getDetailDepositWithTaxes(String phone,Date startDate, Date endDate,Integer startPage,Integer pageSize);

    int countStoreDetailDepositWithTaxes(String phone,Date startDate, Date endDate);

    List<DepositWithTaxes> getStoreDetailDepositWithTaxes(String phone, Date startDate, Date endDate,Integer startPage,Integer pageSize);

    Double[] getStoreProxyBenefitByTime(String phone);

    double getBenefitByTime(String phone, Date startDate,Date endDate);


    boolean insertDesport(HttpServletRequest req,String phone);

    List<DepositWithTaxes> getDetailDepositWithTaxes(String phone, Date startDate, Date endDate, Integer startPage, Integer pageSize, String keyword);

    int countDetailDepositWithTaxes(String phone, Date startDate, Date endDate, String keyword);

    List<DepositWithTaxes> getStoreDetailDepositWithTaxes(String phone, Date startDate, Date endDate, Integer startPage, Integer pageSize, String keyword);

    int countStoreDetailDepositWithTaxes(String phone, Date startDate, Date endDate, String keyword);

    StoreFinanceStatistics storeFinanceStatistics(String proxyPhone);

    FinanceStatistics allFinanceStatics_v2(String phone, String utoken);

    FinanceStatistics allFinanceStatics(String phone,String utoken);
}
