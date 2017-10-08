package dao;

import java.util.List;
import model.AdvOrderRecord;
import model.AdvOrderRecordExample;
import org.apache.ibatis.annotations.Param;

public interface AdvOrderRecordMapper {
    int countByExample(AdvOrderRecordExample example);

    int deleteByExample(AdvOrderRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AdvOrderRecord record);

    int insertSelective(AdvOrderRecord record);

    List<AdvOrderRecord> selectByExample(AdvOrderRecordExample example);

    AdvOrderRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AdvOrderRecord record, @Param("example") AdvOrderRecordExample example);

    int updateByExample(@Param("record") AdvOrderRecord record, @Param("example") AdvOrderRecordExample example);

    int updateByPrimaryKeySelective(AdvOrderRecord record);

    int updateByPrimaryKey(AdvOrderRecord record);
}