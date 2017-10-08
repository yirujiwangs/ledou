package dao;

import java.util.Date;
import java.util.List;

import model.ProxyBenefitRecordExample;
import model.dto.ProxyBenefitRecord;
import org.apache.ibatis.annotations.Param;

public interface ProxyBenefitRecordMapper {

    int proxyBenefitRecordCount(@Param("proxy_token") String proxy_token);

    List<ProxyBenefitRecord> proxyBenefitMonthList(@Param("proxy_token") String proxy_token,@Param("startSize") int startSize,@Param("pageSize") int pageSize);

    List<ProxyBenefitRecord> proxyBenefitDayList(@Param("proxy_token") String proxy_token,@Param("createtime")String createtime,
                                                 @Param("startSize") int startSize,@Param("pageSize") int pageSize);

    ProxyBenefitRecord distProxyBenefitMonth(@Param("proxy_token") String proxy_token,@Param("createtime")String createtime);

    int proxyBenefitTotal(@Param("proxy_token") String proxy_token);

    int proxyBenefitTotal(@Param("proxy_token") String proxy_token,@Param("stime") Date stime,@Param("etime") Date eitme);

    int countByExample(ProxyBenefitRecordExample example);

    int deleteByExample(ProxyBenefitRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(model.ProxyBenefitRecord record);

    int insertSelective(model.ProxyBenefitRecord record);

    List<model.ProxyBenefitRecord> selectByExample(ProxyBenefitRecordExample example);

    model.ProxyBenefitRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") model.ProxyBenefitRecord record, @Param("example") ProxyBenefitRecordExample example);

    int updateByExample(@Param("record") model.ProxyBenefitRecord record, @Param("example") ProxyBenefitRecordExample example);

    int updateByPrimaryKeySelective(model.ProxyBenefitRecord record);

    int updateByPrimaryKey(model.ProxyBenefitRecord record);
}