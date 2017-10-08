package dao;

import java.util.Date;
import java.util.List;
import model.WxStatistics;
import model.WxStatisticsExample;
import org.apache.ibatis.annotations.Param;

public interface WxStatisticsMapper {
    int countByExample(WxStatisticsExample example);

    int deleteByExample(WxStatisticsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(WxStatistics record);

    int insertSelective(WxStatistics record);

    List<WxStatistics> selectByExample(WxStatisticsExample example);

    WxStatistics selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") WxStatistics record, @Param("example") WxStatisticsExample example);

    int updateByExample(@Param("record") WxStatistics record, @Param("example") WxStatisticsExample example);

    int updateByPrimaryKeySelective(WxStatistics record);

    int updateByPrimaryKey(WxStatistics record);

    int getLastId();
    Date getLastDateTime();
}