package dao;


import model.base.ProxyAreaExample;
import model.base.ProxyArea;
import model.dto.AdminCity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProxyAreaMapper {
    int countByExample(ProxyAreaExample example);

    int dealerCountByUtoken(@Param("utoken")String utoken);

    int distCountByUtoken(@Param("utoken")String utoken);

    List<AdminCity> distAreaByUtoken(@Param("utoken")String utoken);

    int deleteByExample(ProxyAreaExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProxyArea record);

    int insertSelective(ProxyArea record);

    List<ProxyArea> selectByExample(ProxyAreaExample example);

    ProxyArea selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProxyArea record, @Param("example") ProxyAreaExample example);

    int updateByExample(@Param("record") ProxyArea record, @Param("example") ProxyAreaExample example);

    int updateByPrimaryKeySelective(ProxyArea record);

    int updateByPrimaryKey(ProxyArea record);

    int countBySearchRid(@Param("rid") Integer rid);
    List<ProxyArea> selectBySearchRid(@Param("rid") Integer rid,@Param("startSize") Integer startSize,
                                      @Param("pageSize") Integer pageSize);
}