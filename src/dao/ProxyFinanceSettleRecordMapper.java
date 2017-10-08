package dao;

import model.ProxyFinanceSettleRecord;
import model.ProxyFinanceSettleRecordExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProxyFinanceSettleRecordMapper {
    int countByExample(ProxyFinanceSettleRecordExample example);

    int deleteByExample(ProxyFinanceSettleRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProxyFinanceSettleRecord record);

    int insertSelective(ProxyFinanceSettleRecord record);

    List<ProxyFinanceSettleRecord> selectByExample(ProxyFinanceSettleRecordExample example);

    ProxyFinanceSettleRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProxyFinanceSettleRecord record, @Param("example") ProxyFinanceSettleRecordExample example);

    int updateByExample(@Param("record") ProxyFinanceSettleRecord record, @Param("example") ProxyFinanceSettleRecordExample example);

    int updateByPrimaryKeySelective(ProxyFinanceSettleRecord record);

    int updateByPrimaryKey(ProxyFinanceSettleRecord record);
}