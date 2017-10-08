package dao;


import model.FinanceTaxes;
import model.FinanceTaxesExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FinanceTaxesMapper {
    int countByExample(FinanceTaxesExample example);

    int deleteByExample(FinanceTaxesExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FinanceTaxes record);

    int insertSelective(FinanceTaxes record);

    List<FinanceTaxes> selectByExample(FinanceTaxesExample example);

    FinanceTaxes selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FinanceTaxes record, @Param("example") FinanceTaxesExample example);

    int updateByExample(@Param("record") FinanceTaxes record, @Param("example") FinanceTaxesExample example);

    int updateByPrimaryKeySelective(FinanceTaxes record);

    int updateByPrimaryKey(FinanceTaxes record);
}