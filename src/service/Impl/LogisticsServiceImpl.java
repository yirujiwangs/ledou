package service.Impl;

import dao.LogisticsMapper;
import model.Logistics;
import model.LogisticsExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.BaseService;
import service.LogisticsService;

import java.util.List;

/**
 * Created by yeran on 2017/4/28.
 */

@Service
public class LogisticsServiceImpl extends BaseService implements LogisticsService{

    @Autowired
    LogisticsMapper logisticsMapper;

    @Override
    public Logistics getNowLogisticByNo(String no) {

        LogisticsExample logisticsExample = new LogisticsExample();
        logisticsExample.createCriteria().andLogistic_noEqualTo(no);
        logisticsExample.setOrderByClause("id");
        List<Logistics> logisticses  = logisticsMapper.selectByExample(logisticsExample);

        if (logisticses!=null && logisticses.size()>0){
            return logisticses.get(logisticses.size()-1);
        }

        return null;
    }

    @Override
    public boolean updateLogisticInfo(Logistics logistics) {
        if (logisticsMapper.updateByPrimaryKeySelective(logistics)>0)
            return true;
        return false;
    }

    @Override
    public boolean insertLogistic(Logistics logistics) {
        if(logisticsMapper.insertSelective(logistics)>0){
            return true;
        }
        return false;
    }

}
