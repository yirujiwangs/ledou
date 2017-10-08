package service.Impl;

import dao.ProxyBenefitRecordMapper;
import model.ProxyBenefitRecordExample;
import model.dto.ProxyBenefitRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.ProxyBenefitRecordService;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/7/28.
 */
@Service
public class ProxyBenefitRecordServiceImpl implements ProxyBenefitRecordService {

    @Autowired
    private ProxyBenefitRecordMapper proxyBenefitRecordMapper;

    @Override
    public int proxyBenefitTotal(String proxy_token) {
        ProxyBenefitRecordExample proxyBenefitRecordExample = new ProxyBenefitRecordExample();
        proxyBenefitRecordExample.createCriteria().andProxy_tokenEqualTo(proxy_token);
        int count = proxyBenefitRecordMapper.countByExample(proxyBenefitRecordExample);
        if(count==0)
            return 0;
        return proxyBenefitRecordMapper.proxyBenefitTotal(proxy_token,null,null);
    }

    @Override
    public int proxyBenefitTotal(String proxy_token, Date stime, Date etime) {
        ProxyBenefitRecordExample proxyBenefitRecordExample = new ProxyBenefitRecordExample();
        proxyBenefitRecordExample.createCriteria().andProxy_tokenEqualTo(proxy_token);
        int count = proxyBenefitRecordMapper.countByExample(proxyBenefitRecordExample);
        if(count==0)
            return 0;
        return proxyBenefitRecordMapper.proxyBenefitTotal(proxy_token,stime,etime);
    }

    @Override
    public List<ProxyBenefitRecord> proxyBenefitMonthList(String proxy_token, int startSize, int pageSize) {
        ProxyBenefitRecordExample proxyBenefitRecordExample = new ProxyBenefitRecordExample();
        proxyBenefitRecordExample.createCriteria().andProxy_tokenEqualTo(proxy_token);
        int count = proxyBenefitRecordMapper.countByExample(proxyBenefitRecordExample);
        if(count==0)
            return null;
        return proxyBenefitRecordMapper.proxyBenefitMonthList(proxy_token, startSize, pageSize);
    }

    @Override
    public List<ProxyBenefitRecord> proxyBenefitDayList(String proxy_token, String createtime, int startSize, int pageSize) {
        ProxyBenefitRecordExample proxyBenefitRecordExample = new ProxyBenefitRecordExample();
        proxyBenefitRecordExample.createCriteria().andProxy_tokenEqualTo(proxy_token);
        int count = proxyBenefitRecordMapper.countByExample(proxyBenefitRecordExample);
        if(count==0)
            return null;
        return proxyBenefitRecordMapper.proxyBenefitDayList(proxy_token, createtime, startSize, pageSize);
    }

    @Override
    public ProxyBenefitRecord distProxyBenefitMonth(String proxy_token, String createtime) {
        ProxyBenefitRecordExample proxyBenefitRecordExample = new ProxyBenefitRecordExample();
        proxyBenefitRecordExample.createCriteria().andProxy_tokenEqualTo(proxy_token);
        int count = proxyBenefitRecordMapper.countByExample(proxyBenefitRecordExample);
        if(count==0)
            return null;
        return proxyBenefitRecordMapper.distProxyBenefitMonth(proxy_token, createtime);
    }

    @Override
    public int proxyBenefitRecordCount(String proxy_token) {
        return proxyBenefitRecordMapper.proxyBenefitRecordCount(proxy_token);
    }

}
