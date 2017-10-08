package dao;

import model.StoreAccountBuyRecord;
import model.StoreAccountBuyRecordExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StoreAccountBuyRecordMapper {
    int countByExample(StoreAccountBuyRecordExample example);

    int deleteByExample(StoreAccountBuyRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(StoreAccountBuyRecord record);

    int insertSelective(StoreAccountBuyRecord record);

    List<StoreAccountBuyRecord> selectByExample(StoreAccountBuyRecordExample example);

    StoreAccountBuyRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") StoreAccountBuyRecord record, @Param("example") StoreAccountBuyRecordExample example);

    int updateByExample(@Param("record") StoreAccountBuyRecord record, @Param("example") StoreAccountBuyRecordExample example);

    int updateByPrimaryKeySelective(StoreAccountBuyRecord record);

    int updateByPrimaryKey(StoreAccountBuyRecord record);
}