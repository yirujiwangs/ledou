package service;

import model.base.DivideBaseResult;
import model.dto.DealerDeviceNumer;
import model.dto.DistBuyDetail;
import model.dto.DistBuyRecord;

import java.util.Date;
import java.util.List;

/**
 * Created by yeran on 2017/2/22.
 */
public interface DealerService {

    DivideBaseResult<?> dealerList(String phone,Integer page,Integer pageSize);

    DealerDeviceNumer dealerDeviceStatistics(String phone,String utoken);


    /**
     * 个人设备采购数量
     * @param utoken
     * @param type
     * @param stime
     * @param etime
     * @return
     */
    int myDevicesNum(String utoken,String type, Date stime, Date etime);

    /**
     * 直接下级代理商采购设备数量
     * @param utoken
     * @param stime
     * @param etime
     * @return
     */
    int directDealerDeviceNum(String utoken, Date stime, Date etime);

    /**
     * 下级（区县）代理商采购设备数量
     * @param utoken
     * @param stime
     * @param etime
     * @return
     */
    int distDeviceBuyNum(String utoken, Date stime, Date etime);


    /**
     * 下级（区县）代理商采购信息列表（按月统计）
     * @param utoken
     * @return
     */
    List<DistBuyRecord> monthDistBuyRecord(String utoken,int startSize,int pageSize);

    /**
     * 下级（区县）代理商采购信息详情（按月统计）
     * @param utoken
     * @return
     */
    List<DistBuyDetail> monthDistBuyDetail(String utoken,String time,int startSize, int pageSize);

    /**
     * 下级（区县）代理商采购信息（按月统计）总记录数
     * @param utoken
     * @return
     */
    int countMonthDistBuyRecord(String utoken);

    /**
     * 下级（区县）代理商采购详情（按月统计）总记录数
     * @param utoken
     * @return
     */
    int countMonthDistBuyDetail(String utoken,String time);

    /**
     * 间接代理商采购设备数量
     *
     * @param utoken
     * @param stime
     * @param etime
     * @return
     */
    int indirectDealerDeviceNum(String utoken, Date stime, Date etime);

    /**
     *
     * 查询下级代理商的设备购买记录
     *
     * @param phone
     * @param utoken
     * @param page
     * @param pageSize
     * @return
     */
    DivideBaseResult<?> dealerDeviceList(String phone,String utoken, Integer page, Integer pageSize);

    DivideBaseResult<?> dealerDeviceRecord(String phone, String utoken, Integer type, Integer startPage, Integer pageSize);
}
