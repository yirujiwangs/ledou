package dao;

import model.StoreAccountRule;
import model.StoreAccountRuleExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StoreAccountRuleMapper {
    int countByExample(StoreAccountRuleExample example);

    int deleteByExample(StoreAccountRuleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(StoreAccountRule record);

    int insertSelective(StoreAccountRule record);

    List<StoreAccountRule> selectByExample(StoreAccountRuleExample example);

    StoreAccountRule selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") StoreAccountRule record, @Param("example") StoreAccountRuleExample example);

    int updateByExample(@Param("record") StoreAccountRule record, @Param("example") StoreAccountRuleExample example);

    int updateByPrimaryKeySelective(StoreAccountRule record);

    int updateByPrimaryKey(StoreAccountRule record);
}