package dao;


import model.IncomeDetailsProxy;
import model.IncomeDetailsProxyExample;
import model.dto.FinanceStatistics;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IncomeDetailsProxyMapper {
    int countByExample(IncomeDetailsProxyExample example);

    int deleteByExample(IncomeDetailsProxyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(IncomeDetailsProxy record);

    int insertSelective(IncomeDetailsProxy record);

    List<IncomeDetailsProxy> selectByExample(IncomeDetailsProxyExample example);

    IncomeDetailsProxy selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") IncomeDetailsProxy record, @Param("example") IncomeDetailsProxyExample example);

    int updateByExample(@Param("record") IncomeDetailsProxy record, @Param("example") IncomeDetailsProxyExample example);

    int updateByPrimaryKeySelective(IncomeDetailsProxy record);

    int updateByPrimaryKey(IncomeDetailsProxy record);

    FinanceStatistics financeStatistic(@Param("utoken") String utoken);
}