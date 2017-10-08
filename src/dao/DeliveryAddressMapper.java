package dao;


import java.util.List;

import model.DeliveryAddress;
import model.DeliveryAddressExample;
import org.apache.ibatis.annotations.Param;

public interface DeliveryAddressMapper {
    int countByExample(DeliveryAddressExample example);

    int deleteByExample(DeliveryAddressExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DeliveryAddress record);

    int insertSelective(DeliveryAddress record);

    List<DeliveryAddress> selectByExample(DeliveryAddressExample example);

    DeliveryAddress selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DeliveryAddress record, @Param("example") DeliveryAddressExample example);

    int updateByExample(@Param("record") DeliveryAddress record, @Param("example") DeliveryAddressExample example);

    int updateByPrimaryKeySelective(DeliveryAddress record);

    int updateByPrimaryKey(DeliveryAddress record);
}