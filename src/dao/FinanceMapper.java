package dao;

import java.util.List;

import model.*;

import org.apache.ibatis.annotations.Param;

public interface FinanceMapper {
    int countByExample(FinanceExample example);

    int deleteByExample(FinanceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Finance record);

    int insertSelective(Finance record);

    int countShopFinance(@Param("users") List<User> users);

    List<ShopFinance> selectShopFinance(@Param("users") List<User> users,@Param("shopFinance") ShopFinance shopFinance);

    List<Finance> selectByExample(FinanceExample example);

    Finance selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Finance record, @Param("example") FinanceExample example);

    int updateByExample(@Param("record") Finance record, @Param("example") FinanceExample example);

    int updateByPrimaryKeySelective(Finance record);

    int updateByPrimaryKey(Finance record);

    StoreFinanceStatistics storeFinanceStatistics(@Param("phones") List<String> phones);
}