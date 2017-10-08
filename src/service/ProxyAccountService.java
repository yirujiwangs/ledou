package service;

import model.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * Created by yeran on 2016/8/11.
 */
public interface ProxyAccountService {

    List<ProxyAccountCount> getProxyAccountCountInfo(Admin admin);

    ProxyAccountCount getCertainProxyAccountCountInfoById(int id);

    ProxyAccountCount getCertainProxyAccountCountInfo(String phone);

    StoreAccountRule getStoreAccountRule();

    void consumeProxyAccountCount(ProxyAccountCount proxyAccountCount, String storeType, int numb);

    int addProxyAccountBuyRecord(String phone,int normal_num,int playform_num);

    ProxyAccountBuyRecord getProxyAccountBuyRecord(int id);

    @Transactional(readOnly = false,rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    boolean accountBuyResult(String out_trade_no, boolean result);

    List<ProxyAccountBuyRecord> getProxyAccountBuyRecords(String phone
            , Date startTime, Date endTime, Integer startpage, Integer pagesize);

    int updateProxyAccountBuyRecord(ProxyAccountBuyRecord proxyAccountBuyRecord);

    boolean updateProxyAccountInfo(Integer accountNum,String descr,String account);

    boolean updateStatus(String account, String status);

    int buyProxyAccountSuccess(String out_trade_no,int id,String phone,Integer fee);

    String notifyBuyUserService(HttpServletRequest req);

    int countProxyAccountBuyRecords(String phone, Date startTime, Date endfTime);

    List<ProxyAccountBuyRecord> getProxyAccountBuyRecords(String phone, Date startTime, Date endTime, Integer startPage, Integer pageSize, String keyword);

    int countProxyAccountBuyRecords(String phone, Date startTime, Date endTime, String keyword);

    /**
     * 初始化代理商库存账号系统
     * @param account_num
     * @param phoneNum
     * @param proxy_token
     * @return
     */
    boolean initProxyAccount(Integer account_num, String phoneNum, String proxy_token);

    void initProxyInfo(Integer id);

    StoreAccountBuyRecord buyStoreAccount(String utoken,Integer amount,Integer unitPrice,Integer totalPrice,Integer ruleId,String comment);
}
