package dao;


import model.ProxyTaxesRule;
import model.ProxyTaxesRuleExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProxyTaxesRuleMapper {
    int countByExample(ProxyTaxesRuleExample example);

    int deleteByExample(ProxyTaxesRuleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProxyTaxesRule record);

    int insertSelective(ProxyTaxesRule record);

    List<ProxyTaxesRule> selectByExample(ProxyTaxesRuleExample example);

    ProxyTaxesRule selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProxyTaxesRule record, @Param("example") ProxyTaxesRuleExample example);

    int updateByExample(@Param("record") ProxyTaxesRule record, @Param("example") ProxyTaxesRuleExample example);

    int updateByPrimaryKeySelective(ProxyTaxesRule record);

    int updateByPrimaryKey(ProxyTaxesRule record);
}