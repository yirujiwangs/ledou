package dao;


import model.ProxyFinanceRecord;
import model.ProxyFinanceRecordExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProxyFinanceRecordMapper {
    int countByExample(ProxyFinanceRecordExample example);

    int deleteByExample(ProxyFinanceRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProxyFinanceRecord record);

    int insertSelective(ProxyFinanceRecord record);

    Double getSumBenefitByTime(ProxyFinanceRecordExample example);

    List<ProxyFinanceRecord> selectByExample(ProxyFinanceRecordExample example);

    ProxyFinanceRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProxyFinanceRecord record, @Param("example") ProxyFinanceRecordExample example);

    int updateByExample(@Param("record") ProxyFinanceRecord record, @Param("example") ProxyFinanceRecordExample example);

    int updateByPrimaryKeySelective(ProxyFinanceRecord record);

    int updateByPrimaryKey(ProxyFinanceRecord record);
}