package dao;


import model.Deposit;
import model.DepositExample;
import model.DepositWithTaxes;
import model.User;
import org.apache.ibatis.annotations.Param;

import java.sql.Date;
import java.util.List;


public interface DepositMapper {
    int countByExample(DepositExample example);

    int deleteByExample(DepositExample example);

    int deleteByPrimaryKey(String out_trade_no);


    int insert(Deposit record);

    int insertSelective(Deposit record);

    Double getSumBenefitByTime(DepositExample example);

    List<Deposit> selectByExample(DepositExample example);

    List<DepositWithTaxes> selectDepositWithTaxes(@Param("startTime") Date startTimes
            , @Param("endTime") Date endTime,
                                                  @Param("phones") List<String> phones
            , @Param("depositWithTaxes") DepositWithTaxes depositWithTaxes);


    List<DepositWithTaxes> selectByUsers(@Param("users") List<User> users);

    Deposit selectByPrimaryKey(String out_trade_no);


    int updateByExampleSelective(@Param("record") Deposit record, @Param("example") DepositExample example);

    int updateByExample(@Param("record") Deposit record, @Param("example") DepositExample example);

    int updateByPrimaryKeySelective(Deposit record);

    int updateByPrimaryKey(Deposit record);

    int countDepositWithTaxes(@Param("startTime") Date date, @Param("endTime") Date date1
            , @Param("phones") List<String> phones,@Param("depositWithTaxes") DepositWithTaxes depositWithTaxes);
}