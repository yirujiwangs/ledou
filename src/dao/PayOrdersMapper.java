package dao;


import java.util.List;

import model.PayOrders;
import model.PayOrdersExample;
import org.apache.ibatis.annotations.Param;

public interface PayOrdersMapper {
    int countByExample(PayOrdersExample example);

    int deleteByExample(PayOrdersExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PayOrders record);

    int insertSelective(PayOrders record);

    List<PayOrders> selectByExample(PayOrdersExample example);

    PayOrders selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PayOrders record, @Param("example") PayOrdersExample example);

    int updateByExample(@Param("record") PayOrders record, @Param("example") PayOrdersExample example);

    int updateByPrimaryKeySelective(PayOrders record);

    int updateSelective();

    int updateByPrimaryKey(PayOrders record);
}