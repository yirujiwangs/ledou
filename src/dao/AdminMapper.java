package dao;

import model.Admin;
import model.AdminExample;
import model.dto.AdminCity;
import model.dto.FranchiseFee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminMapper {

    List<FranchiseFee> franchiseFeeList(@Param("utoken") String utoken,@Param("startSize") int startSize,@Param("pageSize") int pageSize);

    int countByExample(AdminExample example);

    int deleteByExample(AdminExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Admin record);

    int insertSelective(Admin record);

    List<Admin> selectByExample(AdminExample example);

    List<AdminCity> selectAdminCity(@Param("record") Admin admin);

    Admin selectByAdmin(Admin example);

    Admin selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Admin record, @Param("example") AdminExample example);

    int updateByExample(@Param("record") Admin record, @Param("example") AdminExample example);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);

    Admin exitAdmin(String phone);

    List<String> selectDealerUtoken(String recommend_token);

}