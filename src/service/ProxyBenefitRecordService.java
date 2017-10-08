package service;

import model.dto.ProxyBenefitRecord;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/7/28.
 */
public interface ProxyBenefitRecordService {

    int proxyBenefitTotal(String proxy_token);

    /**
     * 商户营销总金额
     *
     * @param proxy_token
     * @param stime
     * @param etime
     * @return
     */
    int proxyBenefitTotal(String proxy_token,Date stime,Date etime);

    List<ProxyBenefitRecord> proxyBenefitMonthList(String proxy_token,int startSize,int pageSize);

    List<ProxyBenefitRecord> proxyBenefitDayList(String proxy_token,String createtime,int startSize,int pageSize);

    ProxyBenefitRecord distProxyBenefitMonth(String proxy_token,String createtime);

    int proxyBenefitRecordCount(String proxy_token);
}
