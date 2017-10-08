package dao;

import java.util.Date;
import java.util.List;
import model.Wxdevdata;
import model.WxdevdataExample;
import org.apache.ibatis.annotations.Param;

public interface WxdevdataMapper {
    int countDeviceByDate(@Param("date") Date date);

    int countByExample(WxdevdataExample example);

    int deleteByExample(WxdevdataExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Wxdevdata record);

    int insertSelective(Wxdevdata record);

    List<Wxdevdata> selectByExample(WxdevdataExample example);

    Wxdevdata selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Wxdevdata record, @Param("example") WxdevdataExample example);

    int updateByExample(@Param("record") Wxdevdata record, @Param("example") WxdevdataExample example);

    int updateByPrimaryKeySelective(Wxdevdata record);

    int updateByPrimaryKey(Wxdevdata record);

}