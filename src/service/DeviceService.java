package service;

import com.alibaba.fastjson.JSONObject;
import model.DeviceBuyRecord;
import model.DeviceStatistics;
import model.dto.DeviceBuyStatistics;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * Created by lenovo on 2015/12/4.
 */
public interface DeviceService {

    JSONObject deviceManage(Integer corporationid,String param);
    
    JSONObject add(String param,String nameString);
    
    JSONObject update(String param,String nameString);
    
    JSONObject delete(String param,String nameString);
    
    JSONObject search(String param);

    JSONObject searchUnbind(Integer corporationid ,String param);

    DeviceStatistics storeDeviceStatistics(String storePhone);
    JSONObject detail(String param);
    
    String getExcel(String path,String urlweb);
    
    String activate(String param);
    
    String binding(String param);

    String unbinding(String param, Integer id);

    String showUnbinding(HttpSession session,String param);
    
    String preAdd(int corporationid);
    
    String searchAccount(int corporationid,String param);

    String reviseComment(String param);

    /**
     *
     * 获取代理商设备采购记录
     *
     * @param utoken
     *              代理商token
     * @param startPage
     *              分页起始页
     * @param pageSize
     *              每页数量
     * @param tradeNo
     *              订单号搜索
     * @return
     */
    List<DeviceBuyRecord> deviceBuyRecord(String utoken, Integer startPage, Integer pageSize, String tradeNo, Integer[] pages);

    DeviceBuyRecord deviceOrder(String utoken,String deviceType ,Integer amount,
                                Integer originalPrice,Integer policyReduct,
                                Integer unitPrice,Integer totalPrice,Integer ruleId
                                ,String address,String contact,
                                String consignee,String status,String typeSec,Date time,String reason);

    @Transactional(readOnly = false,rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    boolean deviceBuyResult(String out_trade_no, boolean result);


    /**
     *
     * 获取所有代理商采购记录
     *
     * @param startPage
     * @param pageSize
     * @param startTime
     * @param endTime
     * @param searchKey
     * @param pages
     * @return
     */
    List<model.dto.DeviceBuyRecord> deviceBuyRecord(Integer startPage, Integer pageSize, String startTime, String endTime, String searchKey,String tradeState, Integer[] pages) throws ParseException;

    @Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    boolean updateDeviceOrderLogisticState(String tradeNo, String status, String logistic_company, String logistic_no,String reason);


    DeviceBuyStatistics deviceBuyStatics(String  utoken);


}
