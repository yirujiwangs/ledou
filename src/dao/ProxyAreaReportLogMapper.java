package dao;


import model.ProxyAreaReportLog;
import model.ProxyAreaReportLogExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProxyAreaReportLogMapper {
    int countByExample(ProxyAreaReportLogExample example);

    int deleteByExample(ProxyAreaReportLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProxyAreaReportLog record);

    int insertSelective(ProxyAreaReportLog record);

    List<ProxyAreaReportLog> selectByExample(ProxyAreaReportLogExample example);

    ProxyAreaReportLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProxyAreaReportLog record, @Param("example") ProxyAreaReportLogExample example);

    int updateByExample(@Param("record") ProxyAreaReportLog record, @Param("example") ProxyAreaReportLogExample example);

    int updateByPrimaryKeySelective(ProxyAreaReportLog record);

    int updateByPrimaryKey(ProxyAreaReportLog record);

    int countBySearchRid(@Param("rid") Integer rid);
    List<ProxyAreaReportLog> selectBySearchRid(@Param("rid") Integer rid,@Param("startSize") Integer startSize,
                                      @Param("pageSize") Integer pageSize);
}