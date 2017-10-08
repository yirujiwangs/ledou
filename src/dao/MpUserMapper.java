package dao;

import model.wx.MpUser;
import model.wx.MpUserExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MpUserMapper {
    int countByExample(MpUserExample example);

    int deleteByExample(MpUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MpUser record);

    int insertSelective(MpUser record);

    List<MpUser> selectByExample(MpUserExample example);

    MpUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MpUser record, @Param("example") MpUserExample example);

    int updateByExample(@Param("record") MpUser record, @Param("example") MpUserExample example);

    int updateByPrimaryKeySelective(MpUser record);

    int updateByPrimaryKey(MpUser record);
}