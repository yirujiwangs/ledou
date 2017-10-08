package service;


import com.alibaba.fastjson.JSONObject;
import model.User;
import model.dto.DeviceUserInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public interface UserService {

    String notifyAdduserService(HttpServletRequest req);

    List<String> getStoreNamesByPhones(List<String> phones);

    List<String> getUsersPhoneByProxyPhone(String phone);

    int countUsersByProxyPhone(String phone,String keyword);

    List<User> getUsersByProxyPhone(String phone, Integer startPage, Integer pageSize,String keyword);

    JSONObject accountManage(HttpSession session, String param);
    JSONObject updateByModel(String param);
    JSONObject delete(String account,String name);


    JSONObject changeStatus(Integer id, String status);

    JSONObject add(String param, Integer corporationId, String phonenum);
    
    JSONObject updateByProperty(String id ,String propertyName , String value);


    int canBuyUserThenBuy(String phone,int normal_num,int plaform_num);


    double getValue(int normal_num,int playform_num);

    List<String> getUsersPhoneByProxyPhone(String phone, String keyword);


    List<String> storeUserUtokens(Integer proxyId, Integer startPage, Integer pageSize, Integer pages);

    List<DeviceUserInfo> getUserList(Integer proxyId, Integer startPage, Integer pageSize, Integer pages,List<DeviceUserInfo> utokens);

    List<DeviceUserInfo> searchUserList(Integer proxyId,String keyword, Integer startPage, Integer pageSize, int[] pages);

    DeviceUserInfo getUserByAccount(String storeAccount);


    /**
     * 生成商户绑定二维码
     * @param phoneNum
     * @return
     */
    String userQrCodeUrl(String phoneNum,String proxyToken);

    boolean bindToken(String phoneNum, String uToken);

    User getUserByPhone(String phoneNum);
}