package service.Impl;

import com.alibaba.fastjson.JSONObject;
import dao.DeviceBuyRecordMapper;
import model.Admin;
import model.base.DivideBaseResult;
import model.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.AdminService;
import service.DealerService;
import utils.common.DividePageUtil;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by yeran on 2017/4/15.
 */

@Service
public class DealerServiceImpl implements DealerService {

    @Autowired
    AdminService adminService;

    @Autowired
    DeviceBuyRecordMapper deviceBuyRecordMapper;


    @Override
    public DivideBaseResult<?> dealerList(String phone, Integer page, Integer pageSize) {
        return null;
    }



    @Override
    public DealerDeviceNumer dealerDeviceStatistics(String phone, String utoken) {
        //获取下级代理商总数
        List<Admin> admins = adminService.dealers(phone, utoken);
        if (admins==null || admins.size()==0)
            return new DealerDeviceNumer(0,0,0,0,0);

        int dealerNum = admins.size();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.DAY_OF_MONTH, 1);

        calendar.add(Calendar.DATE, -1);
        Date etime = calendar.getTime();

        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date stime = calendar.getTime();

        //上月
        int fDealerDeviceNumLast = deviceBuyRecordMapper.fDealerDeviceNum(utoken, stime, etime);

        //本月
        int fDealerDeviceNumNow = deviceBuyRecordMapper.fDealerDeviceNum(utoken, etime, null);

        int sDealerDeviceNumLast = deviceBuyRecordMapper.sDealerDeviceNum(utoken, stime, etime);

        int sDealerDeviceNumNow = deviceBuyRecordMapper.sDealerDeviceNum(utoken, etime, null);

        return new DealerDeviceNumer(dealerNum,
                fDealerDeviceNumLast,sDealerDeviceNumLast,fDealerDeviceNumNow,sDealerDeviceNumNow);
    }


    @Override
    public int myDevicesNum(String utoken,String type,Date stime,Date etime){
        return deviceBuyRecordMapper.myDeviceNum(utoken,type,stime,etime);
    }

    @Override
    public int directDealerDeviceNum(String utoken, Date stime, Date etime){
        return deviceBuyRecordMapper.fDealerDeviceNum(utoken, stime, etime);
    }

    @Override
    public int distDeviceBuyNum(String utoken, Date stime, Date etime) {
        return deviceBuyRecordMapper.distDeviceBuyNum(utoken,stime,etime);
    }

    @Override
    public List<DistBuyRecord> monthDistBuyRecord(String utoken,int startSize, int pageSize) {
        return deviceBuyRecordMapper.monthDistBuyRecord(utoken, startSize, pageSize);
    }

    @Override
    public List<DistBuyDetail> monthDistBuyDetail(String utoken, String time,int startSize, int pageSize) {
        List<DistBuyDetail> distBuyDetails = deviceBuyRecordMapper.monthDistBuyDetail(utoken, time,startSize,pageSize);
        return distBuyDetails;
    }

    @Override
    public int countMonthDistBuyRecord(String utoken) {
        return deviceBuyRecordMapper.countMonthDistBuyRecord(utoken);
    }

    @Override
    public int countMonthDistBuyDetail(String utoken, String time) {
        return deviceBuyRecordMapper.countMonthDistBuyDetail(utoken,time);
    }

    @Override
    public int indirectDealerDeviceNum(String utoken, Date stime, Date etime){
        return deviceBuyRecordMapper.sDealerDeviceNum(utoken, stime, etime);
    }

    @Override
    public DivideBaseResult<?> dealerDeviceList(String phone, String utoken, Integer page, Integer pageSize) {

        Integer startSize = (page-1)*pageSize;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.DAY_OF_MONTH, 1);

        calendar.add(Calendar.DATE, -1);
        Date etime = calendar.getTime();

        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date stime = calendar.getTime();

        try {

            int count = deviceBuyRecordMapper.dealerCount(utoken);
            int pages = DividePageUtil.getPages(count, pageSize);

            List<DealerDeviceStatistics> dealerDeviceStatisticses =
                    deviceBuyRecordMapper.dealerDevices(utoken, stime, etime, startSize, pageSize);
            if(dealerDeviceStatisticses!=null && dealerDeviceStatisticses.size()>0) {
                for (DealerDeviceStatistics dealerDeviceStatistics : dealerDeviceStatisticses){
                    if (dealerDeviceStatistics.getAccount()!=null&&dealerDeviceStatistics.getAccount().length()>=11) {
                        StringBuilder stringBuilder = new StringBuilder(dealerDeviceStatistics.getAccount());
                        dealerDeviceStatistics.setAccount(stringBuilder.replace(3,7, "****").toString());
                    }
                    if(utoken.equals(dealerDeviceStatistics.getRecommend_token())){
                        //直接推荐
                        dealerDeviceStatistics.setRecommend_type("D");
                    }else {
                        dealerDeviceStatistics.setRecommend_type("I");
                    }
                }
            }

            DivideBaseResult<DealerDeviceStatistics> divideBaseResult = new DivideBaseResult<>();
            divideBaseResult.setPage(page);
            divideBaseResult.setPageSize(pageSize);
            divideBaseResult.setPages(pages);
            divideBaseResult.setList(dealerDeviceStatisticses);

            return divideBaseResult;
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public DivideBaseResult<?> dealerDeviceRecord(String phone, String utoken, Integer type, Integer startPage, Integer pageSize) {
        if (startPage==null)
            startPage=1;
        if (pageSize==null)
            pageSize=10;
        Integer startSize = (startPage-1)* pageSize;
        Integer count=0;
        List<DeviceBuyRecord> deviceBuyRecords = null;
        try {
            if (1 == type) {
                //组合采购
                count = deviceBuyRecordMapper.countDealerDeviceRecord(utoken);
                deviceBuyRecords = deviceBuyRecordMapper.selectDealerDeviceRecord(utoken, startSize, pageSize);
            } else if (2 == type) {
                //间接采购
                count = deviceBuyRecordMapper.countIndirectDealerDeviceRecord(utoken);
                deviceBuyRecords = deviceBuyRecordMapper.selectIndirectDealerDeviceRecord(utoken, startSize, pageSize);
            }

            Integer pages = DividePageUtil.getPages(count, pageSize);

            if (deviceBuyRecords!=null && deviceBuyRecords.size()>0){
                for(DeviceBuyRecord deviceBuyRecord: deviceBuyRecords){
                    if (deviceBuyRecord.getAccount()!=null&&deviceBuyRecord.getAccount().length()>=11) {
                        StringBuilder stringBuilder = new StringBuilder(deviceBuyRecord.getAccount());
                        deviceBuyRecord.setAccount(stringBuilder.replace(3,7, "****").toString());
                    }
                }
            }

            DivideBaseResult<DeviceBuyRecord> divideBaseResult = new DivideBaseResult<>();
            divideBaseResult.setPage(startPage);
            divideBaseResult.setPageSize(pageSize);
            divideBaseResult.setList(deviceBuyRecords);
            divideBaseResult.setPages(pages);
            //System.out.println(JSONObject.toJSONString(divideBaseResult));

            return divideBaseResult;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


}
