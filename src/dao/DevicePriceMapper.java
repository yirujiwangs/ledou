package dao;

import java.util.List;
import model.DevicePrice;
import model.DevicePriceExample;
import org.apache.ibatis.annotations.Param;

public interface DevicePriceMapper {
    int countByExample(DevicePriceExample example);

    int deleteByExample(DevicePriceExample example);

    int insert(DevicePrice record);

    int insertSelective(DevicePrice record);

    List<DevicePrice> selectByExample(DevicePriceExample example);

    int updateByExampleSelective(@Param("record") DevicePrice record, @Param("example") DevicePriceExample example);

    int updateByExample(@Param("record") DevicePrice record, @Param("example") DevicePriceExample example);
}