package dao;

import model.RuleMoneyDeviceBuy;
import model.RuleMoneyDeviceBuyExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RuleMoneyDeviceBuyMapper {
    int countByExample(RuleMoneyDeviceBuyExample example);

    int deleteByExample(RuleMoneyDeviceBuyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RuleMoneyDeviceBuy record);

    int insertSelective(RuleMoneyDeviceBuy record);

    List<RuleMoneyDeviceBuy> selectByExample(RuleMoneyDeviceBuyExample example);

    RuleMoneyDeviceBuy selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RuleMoneyDeviceBuy record, @Param("example") RuleMoneyDeviceBuyExample example);

    int updateByExample(@Param("record") RuleMoneyDeviceBuy record, @Param("example") RuleMoneyDeviceBuyExample example);

    int updateByPrimaryKeySelective(RuleMoneyDeviceBuy record);

    int updateByPrimaryKey(RuleMoneyDeviceBuy record);
}