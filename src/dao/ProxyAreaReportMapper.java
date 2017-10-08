package dao;


import model.ProxyAreaReport;
import model.ProxyAreaReportExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProxyAreaReportMapper {
    int countByExample(ProxyAreaReportExample example);

    int deleteByExample(ProxyAreaReportExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProxyAreaReport record);

    int insertSelective(ProxyAreaReport record);

    List<ProxyAreaReport> selectByExample(ProxyAreaReportExample example);

    ProxyAreaReport selectByPrimaryKey(Integer id);

    ProxyAreaReport selectByRid(Integer rid);


    int updateByExampleSelective(@Param("record") ProxyAreaReport record, @Param("example") ProxyAreaReportExample example);

    int updateByExample(@Param("record") ProxyAreaReport record, @Param("example") ProxyAreaReportExample example);

    int updateByPrimaryKeySelective(ProxyAreaReport record);

    int updateByPrimaryKey(ProxyAreaReport record);

    int updateReportNum(Integer rid);
}