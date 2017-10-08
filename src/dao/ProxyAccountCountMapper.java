package dao;

import model.ProxyAccountCount;
import model.ProxyAccountCountExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProxyAccountCountMapper {
    int countByExample(ProxyAccountCountExample example);

    int deleteByExample(ProxyAccountCountExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProxyAccountCount record);

    int insertSelective(ProxyAccountCount record);

    List<ProxyAccountCount> selectByExample(ProxyAccountCountExample example);

    ProxyAccountCount selectByProxyPhone(String phone);

    ProxyAccountCount selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProxyAccountCount record, @Param("example") ProxyAccountCountExample example);

    int updateByExample(@Param("record") ProxyAccountCount record, @Param("example") ProxyAccountCountExample example);

    int updateByPrimaryKeySelective(ProxyAccountCount record);

    int updateByPrimaryKey(ProxyAccountCount record);
}