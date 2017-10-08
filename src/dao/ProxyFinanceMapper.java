package dao;

import java.util.Date;
import java.util.List;

import model.Admin;
import model.FinanceTaxes;
import model.ProxyFinance;
import model.ProxyFinanceExample;
import org.apache.ibatis.annotations.Param;

public interface ProxyFinanceMapper {
    int countByExample(ProxyFinanceExample example);

    int deleteByExample(ProxyFinanceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProxyFinance record);

    int insertSelective(ProxyFinance record);

    List<ProxyFinance> selectByExample(ProxyFinanceExample example);

    ProxyFinance selectByPhone(String phone);

    ProxyFinance selectByPrimaryKey(Integer id);

    ProxyFinance selectByKeywords(@Param("stime") Date stime,@Param("etime") Date etime,@Param("keywords") String keywords);

    int updateByExampleSelective(@Param("record") ProxyFinance record, @Param("example") ProxyFinanceExample example);

    int updateByExample(@Param("record") ProxyFinance record, @Param("example") ProxyFinanceExample example);

    int updateByPrimaryKeySelective(ProxyFinance record);

    int updateByPrimaryKey(ProxyFinance record);

    FinanceTaxes getFinanceTaxes(@Param("admin") Admin admin);


}