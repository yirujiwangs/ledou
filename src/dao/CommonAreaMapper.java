package dao;

import model.base.CommonArea;
import model.base.CommonAreaExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommonAreaMapper {
    int countByExample(CommonAreaExample example);

    int deleteByExample(CommonAreaExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CommonArea record);

    int insertSelective(CommonArea record);

    List<CommonArea> selectByExample(CommonAreaExample example);

    CommonArea selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CommonArea record, @Param("example") CommonAreaExample example);

    int updateByExample(@Param("record") CommonArea record, @Param("example") CommonAreaExample example);

    int updateByPrimaryKeySelective(CommonArea record);

    int updateByPrimaryKey(CommonArea record);

    List<String> selectSubByAreaCode(@Param("areaCode") String areaCode);

    List<String> selectDirSubByAreaCode(@Param("areaCode") String areaCode);
}