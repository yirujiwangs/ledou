package dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import model.DeviceBuyRecord;
import model.DeviceBuyRecordExample;
import model.dto.DealerDeviceStatistics;
import model.dto.DistBuyDetail;
import model.dto.DistBuyRecord;
import org.apache.ibatis.annotations.Param;

public interface DeviceBuyRecordMapper {
    int countByExample(DeviceBuyRecordExample example);

    int deleteByExample(DeviceBuyRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DeviceBuyRecord record);

    int insertSelective(DeviceBuyRecord record);

    List<DeviceBuyRecord> selectByExample(DeviceBuyRecordExample example);

    DeviceBuyRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DeviceBuyRecord record, @Param("example") DeviceBuyRecordExample example);

    int updateByExample(@Param("record") DeviceBuyRecord record, @Param("example") DeviceBuyRecordExample example);

    int updateByPrimaryKeySelective(DeviceBuyRecord record);

    int updateByPrimaryKey(DeviceBuyRecord record);

    int distDeviceBuyNum(@Param("utoken") String utoken,@Param("stime") Date stime
            ,@Param("etime") Date etime);

    List<DistBuyRecord> monthDistBuyRecord(@Param("utoken") String utoken,@Param("startSize") Integer startSize
            ,@Param("pageSize") Integer pageSize);

    List<DistBuyDetail> monthDistBuyDetail(@Param("utoken") String utoken,@Param("time") String time
            ,@Param("startSize") Integer startSiz ,@Param("pageSize") Integer pageSize);

    int countMonthDistBuyRecord(@Param("utoken") String utoken);

    int countMonthDistBuyDetail(@Param("utoken") String utoken,@Param("time") String time);


    int fDealerDeviceNum(@Param("recommend_token") String recommend_token,@Param("stime") Date stime
            ,@Param("etime") Date etime);
    int sDealerDeviceNum(@Param("recommend_token") String recommend_token,@Param("stime") Date stime
            ,@Param("etime") Date etime);

    List<DealerDeviceStatistics> dealerDevices(@Param("recommend_token") String recommend_token,
                                        @Param("stime") Date stime,
                                        @Param("etime") Date etime,
                                        @Param("startSize") Integer startSize,
                                        @Param("pageSize") Integer pageSize);

    int dealerCount(@Param("recommend_token") String recommend_token);

    int myDeviceNum(@Param("utoken") String utoken,@Param("type") String type,
                    @Param("stime") Date stime
            ,@Param("etime") Date etime);

    List<model.dto.DeviceBuyRecord> selectDealerDeviceRecord(@Param("recommend_token") String utoken,
                                                         @Param("startSize") Integer startSize,
                                                         @Param("pageSize") Integer pageSize);

    Integer countDealerDeviceRecord(@Param("recommend_token") String utoken);


    Integer countIndirectDealerDeviceRecord(@Param("recommend_token") String utoken);
    List<model.dto.DeviceBuyRecord> selectIndirectDealerDeviceRecord(@Param("recommend_token") String utoken,
                                                                     @Param("startSize") Integer startSize,
                                                                     @Param("pageSize") Integer pageSize);


    Integer countDeviceRecord(@Param("tradeState") String tradeState,
                              @Param("searchKey") String searchKey ,
                              @Param("stime") Date stime,
                              @Param("etime") Date etime);

    List<model.dto.DeviceBuyRecord> selectDeviceRecord(@Param("tradeState") String tradeState,
                                                       @Param("searchKey") String searchKey,
                                                                     @Param("startSize") Integer startSize,
                                                                     @Param("pageSize") Integer pageSize,
                                                       @Param("stime") Date stime,
                                                       @Param("etime") Date etime);
}