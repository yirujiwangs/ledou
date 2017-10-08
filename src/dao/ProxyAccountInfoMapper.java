package dao;

import model.ProxyAccountInfo;
import model.ProxyAccountInfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProxyAccountInfoMapper {
    int countByExample(ProxyAccountInfoExample example);

    int deleteByExample(ProxyAccountInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProxyAccountInfo record);

    int insertSelective(ProxyAccountInfo record);

    List<ProxyAccountInfo> selectByExample(ProxyAccountInfoExample example);

    ProxyAccountInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProxyAccountInfo record, @Param("example") ProxyAccountInfoExample example);

    int updateByExample(@Param("record") ProxyAccountInfo record, @Param("example") ProxyAccountInfoExample example);

    int updateByPrimaryKeySelective(ProxyAccountInfo record);

    int updateByPrimaryKey(ProxyAccountInfo record);
}