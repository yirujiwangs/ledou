package dao;

import java.util.List;
import model.ProxyAccountBuyRecord;
import model.ProxyAccountBuyRecordExample;
import org.apache.ibatis.annotations.Param;

public interface ProxyAccountBuyRecordMapper {
    int countByExample(ProxyAccountBuyRecordExample example);

    int deleteByExample(ProxyAccountBuyRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProxyAccountBuyRecord record);

    int insertSelective(ProxyAccountBuyRecord record);

    List<ProxyAccountBuyRecord> selectByExample(ProxyAccountBuyRecordExample example);

    ProxyAccountBuyRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProxyAccountBuyRecord record, @Param("example") ProxyAccountBuyRecordExample example);

    int updateByExample(@Param("record") ProxyAccountBuyRecord record, @Param("example") ProxyAccountBuyRecordExample example);

    int updateByPrimaryKeySelective(ProxyAccountBuyRecord record);

    int updateByPrimaryKey(ProxyAccountBuyRecord record);
}