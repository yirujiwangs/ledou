package service.Impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import dao.*;
import model.*;
import model.dto.DeviceUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.AdminService;
import service.FinanceService;
import service.ProxyAccountService;
import service.UserService;
import utils.api.BaseAPI;
import utils.common.*;
import utils.finance.FinanceUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private User_infoMapper userInfoMapper;
    @Autowired
    private HistoryMapper historyMapper;

    @Autowired
    private DepositMapper depositMapper;

    @Autowired
    private FinanceMapper financeMapper;

    @Autowired
    private ProxyAccountService proxyAccountService;


    @Autowired
    private AdminService adminService;

    @Autowired
    private FinanceService financeService;

//    @Autowired
//    private StoreAccountRuleMapper storeAccountRuleMapper;

    @Resource
    private StoreAccountRuleCache storeAccountRuleCache;

    @Override
    public List<String> getStoreNamesByPhones(List<String> phones) {
        if (phones != null && phones.size() > 0) {
            return userMapper.selectStoreNamesByPhones(phones);
        }
        return null;
    }

    @Override
    public List<String> getUsersPhoneByProxyPhone(String phone) {
        if (phone != null && !phone.trim().isEmpty()) {
            Admin admin = adminService.getAdminByPhone(phone);
            if (admin != null) {
                UserExample userExample = new UserExample();
                userExample.createCriteria().andCorporationidEqualTo(admin.getId());
                return userMapper.selectPhoneByExample(userExample);
            }
        }

        return null;
    }

    @Override
    public int countUsersByProxyPhone(String phone, String keyword) {
        Admin admin = adminService.getAdminByPhone(phone);
        if (admin != null) {
            UserExample userExample = new UserExample();
            UserExample.Criteria criteria = userExample.createCriteria().andCorporationidEqualTo(admin.getId());
            if (keyword != null)
                criteria.andLike(new String[]{"phonenum", "storename"}, keyword);
            return userMapper.countByExample(userExample);
        }
        return 0;
    }

    @Override
    public List<User> getUsersByProxyPhone(String phone, Integer startPage, Integer pageSize, String keyword) {
        if (phone != null && !phone.trim().isEmpty()) {
            Admin admin = adminService.getAdminByPhone(phone);
            if (admin != null) {
                UserExample userExample = new UserExample();
                UserExample.Criteria criteria = userExample.createCriteria().andCorporationidEqualTo(admin.getId());
                if (keyword != null)
                    criteria.andLike(new String[]{"phonenum", "storename"}, keyword);
                userExample.setDividePage(startPage, pageSize);
                return userMapper.selectByExample(userExample);
            }
        }
        return null;
    }

    /**
     * 代理商下属所有商户信息
     * @param session
     * @param param
     * @return
     */
    public JSONObject accountManage(HttpSession session, String param) {
        JSONObject json = JSON.parseObject(param);

        Integer startPage = json.getInteger("startPage");
        Integer pageSize = json.getInteger("pageSize");

        Integer corporationId = (Integer) session.getAttribute("userId");
        if (corporationId == null) {
            corporationId = json.getInteger("corporationid");
        }

        UserExample userExample = new UserExample();
        userExample.createCriteria().andCorporationidEqualTo(corporationId);
        int totalUserNum = userMapper.countByExample(userExample);

        UserExample userExample1 = new UserExample();
        userExample1.createCriteria().andStoretypeEqualTo("NORMAL").andCorporationidEqualTo(corporationId);
        int commonUserNum = userMapper.countByExample(userExample1);

        UserExample userExample2 = new UserExample();

        userExample2.createCriteria().andStoretypeEqualTo("PLATFORM").andCorporationidEqualTo(corporationId);
        int adNum = userMapper.countByExample(userExample2);


        JSONArray jsons = new JSONArray();

        json = new JSONObject();
        json.put("name", "商户总数");
        json.put("number", totalUserNum);
        jsons.add(json);

        json = new JSONObject();
        json.put("name", "普通类型");
        json.put("number", commonUserNum);
        jsons.add(json);

        json = new JSONObject();
        json.put("name", "平台类型");
        json.put("number", adNum);
        jsons.add(json);

        int pages = DividePageUtil.getPages(totalUserNum, pageSize);

        userExample.setDividePage(startPage, pageSize);
        List<User> users = userMapper.selectByExample(userExample);
        json = new JSONObject();
        json.put("pages", pages);
        json.put("amount", jsons);

        json.put("allUser", jsonUtil.userList2json(users, userInfoMapper));

        return json;
    }

    /**
     * 此方法未使用！！！！！！！！！！！！！！！！
     *
     * @param id
     * @param propertyName
     * @param value
     * @return
     */

    public JSONObject updateByProperty(String id, String propertyName, String value) {

        JSONObject json = new JSONObject();
        json.put("flag", false);
        User user = userMapper.selectByPrimaryKey(Integer.parseInt(id));
        if (!propertyName.equalsIgnoreCase("remark")) {

            if (new CommonUtil<User>().updateByPropertyName(user, propertyName, value)) {
                userMapper.updateByPrimaryKey(user);
                json.put("flag", true);
            }

        } else {
            User_info userInfo = userInfoMapper.selectByPrimaryKey(user.getUserinfoid());
            if (new CommonUtil<User_info>().updateByPropertyName(userInfo, propertyName, value)) {
                userInfoMapper.updateByPrimaryKey(userInfo);
                json.put("flag", true);
            }
        }
        return json;
    }

    @Override
    public int canBuyUserThenBuy(String phone, int normal_num, int platform_num) {
        //执行等待支付账户的数量判断
        try {
            int id = proxyAccountService.addProxyAccountBuyRecord(phone, normal_num, platform_num);
            return id;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public double getValue(int normal_num, int playform_num) {
        if (normal_num > -1 && playform_num > -1) {
            StoreAccountRule storeAccountRule_normal = storeAccountRuleCache.getStoreAccountRuleByType(StoreAccountRule.STORE_TYPE_NORMAL);

            double normal_total = FinanceUtil.Fen2Yuan(normal_num * storeAccountRule_normal.getAccount_fee());
            storeAccountRule_normal = storeAccountRuleCache.getStoreAccountRuleByType(StoreAccountRule.STORE_TYPE_PLATFORM);
            double platform_total = FinanceUtil.Fen2Yuan(playform_num * storeAccountRule_normal.getAccount_fee());

            //System.out.println(normal_total + "--" + platform_total);

            return normal_total;
        }

        return 0;
    }

    @Override
    public List<String> getUsersPhoneByProxyPhone(String phone, String keyword) {
        if (phone != null && !phone.trim().isEmpty()) {
            Admin admin = adminService.getAdminByPhone(phone);
            if (admin != null) {
                UserExample userExample = new UserExample();
                UserExample.Criteria criteria = userExample.createCriteria().andCorporationidEqualTo(admin.getId());
                if (keyword != null)
                    criteria.andLike(new String[]{"phonenum", "storename"}, keyword);
                return userMapper.selectPhoneByExample(userExample);
            }
        }

        return null;
    }


    @Override
    public JSONObject updateByModel(String param) {
        JSONObject json = JSON.parseObject(param);
        String phoneNum = json.getString("account");
        UserExample userExample = new UserExample();
        userExample.createCriteria().andPhonenumEqualTo(phoneNum);
        List<User> users = userMapper.selectByExample(userExample);
        if(users==null||users.size()<=0){
            json.put("flag", false);
            return json;
        }
        User user = users.get(0);
        user.setStorename(json.getString("storeName"));
        user.setMaxgroupnum(json.getInteger("maxGroupNum"));
        String storeType = json.getString("storeType");
        if (storeType.equalsIgnoreCase("普通类型")) {
            storeType = "NORMAL";
        } else {
            storeType = "PLATFORM";
        }

        user.setStoretype(storeType);
        user.setMaxstorenum(json.getInteger("maxStoreNum"));
        userMapper.updateByPrimaryKeySelective(user);
        User_info userInfo = userInfoMapper.selectByPrimaryKey(user.getUserinfoid());

        String remark = json.getString("descr");
        if (userInfo.getRemark() == null || !userInfo.getRemark().equalsIgnoreCase(remark)) {
            userInfo.setRemark(remark);
            userInfoMapper.updateByPrimaryKeySelective(userInfo);
        }

        History history = new History(StrUtil.getUUID(), new Date(),
                user.getStorename(), user.getPhonenum(), "账号操作", "修改用户信息", phoneNum, "");
        historyMapper.insert(history);

        json = new JSONObject();
        json.put("flag", true);
        return json;
    }

    public JSONObject delete(String account, String name) {
        JSONObject json = new JSONObject();
        json.put("flag", false);
        UserExample userExample = new UserExample();
        userExample.createCriteria().andPhonenumEqualTo(account);
        User user = userMapper.selectByExample(userExample).get(0);
        int id = user.getId();
        userInfoMapper.deleteByPrimaryKey(userMapper.selectByPrimaryKey(id).getUserinfoid());
        userMapper.deleteByPrimaryKey(id);

        History history = new History(StrUtil.getUUID(), new Date(),
                user.getStorename(), user.getPhonenum(), "账号操作", "删除用户", name, "");
        historyMapper.insert(history);

        json.put("flag", true);
        return json;
    }


    public JSONObject changeStatus(Integer id, String status) {
        JSONObject json = new JSONObject();
        json.put("flag", false);
        UserExample userExample = new UserExample();
        userExample.createCriteria().andIdEqualTo(id);
        User user = userMapper.selectByExample(userExample).get(0);
        //System.out.println("user pre:" + user);
        if ("true".equals(status))
            status = "NORMAL";
        else if ("false".equals(status))
            status = "NOTACTIVITION";

        user.setStatus(status);
        //System.out.println("user post:" + user);
        userExample = new UserExample();
        userExample.createCriteria().andIdEqualTo(id);
        int flag = userMapper.updateByPrimaryKey(user);

        //System.out.println("flag: " + flag);
        json = new JSONObject();
        if (flag > 0) {
            json.put("flag", true);
        } else {
            json.put("flag", true);
        }
        return json;


    }

    @Override
    public JSONObject add(String param, Integer corporationId, String phonenum) {
        JSONObject json = JSON.parseObject(param);
        String phone = json.getString("phoneNum");
        //用户存在判断
        UserExample userExample = new UserExample();
        userExample.createCriteria().andPhonenumEqualTo(phone);
        List<User> users = userMapper.selectByExample(userExample);
        if (users != null && users.size() > 0) {
            json.put("errorcode", -1);
            json.put("errormsg", "此账号已存在");
            json.put("flag", false);
            return json;
        }

        String storeType;

        switch (json.getString("storeType")) {
            case "未初始化":
                storeType = "UNINIT";
                break;
            case "普通类型":
                storeType = "NORMAL";
                break;
            case "广告类型":
                storeType = "PLATFORM";
                break;
            default:
                storeType = "UNINIT";
                break;
        }

        //代理商账号库存判断
        ProxyAccountCount proxyAccountCount = proxyAccountService.getCertainProxyAccountCountInfo(phonenum);
        if (proxyAccountCount != null) {
            int available = 0;
            if ("NORMAL".equals(storeType))
                available = proxyAccountCount.getNormal_num_total() - proxyAccountCount.getNormal_num_used();
            else if ("PLATFORM".equals(storeType))
                available = proxyAccountCount.getPlatform_num_total() - proxyAccountCount.getPlatform_num_used();

            if (available <= 0) {
                json.put("errorcode", -1);
                json.put("errormsg", "可用账号数量不足，请先购买");
                json.put("flag", false);
                return json;
            }

            User_info userInfo = new User_info();
            userInfo.setRemark(json.getString("descr"));

            userInfoMapper.insert(userInfo);
            int userInfoId = userInfoMapper.getLastId();


//            User user = new User(0, phone, json.getString("storeName"), json.getString("passwd"),      //id , phoneNum,storeName,passwd
//                    storeType, "true", "0", json.getInteger("maxGroupNum"), json.getInteger("maxStoreNum"), null, userInfoId,   //storeType,status,weixinStatus,maxGroupNum,maxStoreNum,platformId,userInfoId,
//                    new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()),                   //createTime,lastModifyTime
//                    0, corporationId, json.getString("descr"));                                                             //level,corporationId,descr

            User user = new User();
            user.setPhonenum(phone);
            user.setStorename(json.getString("storeName"));
            user.setPasswd(json.getString("passwd"));
            user.setStoretype(storeType);
            user.setStatus("true");
            user.setWeixinstatus("0");
            user.setMaxgroupnum(json.getInteger("maxGroupNum"));
            user.setMaxstorenum(json.getInteger("maxStoreNum"));
            user.setUserinfoid(userInfoId);
            user.setLastmodifytime(new Timestamp(System.currentTimeMillis()));
            user.setLevel(0);
            user.setCorporationid(corporationId);
            user.setDescr(json.getString("descr"));

            int flag = userMapper.insertSelective(user);

            json = new JSONObject();
            if (flag > 0) {
                //减少可用账号数量
                try {
                    //创建用户财务信息
                    Finance finance = new Finance();
                    finance.setPhonenum(phone);
                    //System.out.println("phone" + finance.getPhonenum());
                    financeMapper.insertSelective(finance);

                    proxyAccountService.consumeProxyAccountCount(proxyAccountCount, storeType, 1);
                    json.put("errormsg", "创建成功");
                    json.put("flag", true);
                    return json;
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
        json.put("flag", false);
        json.put("errormsg", "创建失败");
        return json;
    }


    @Override
    public String notifyAdduserService(HttpServletRequest req) {
        //商家信息
        String param = req.getParameter("extra_common_param");
        JSONObject jsonObject = JSON.parseObject(param);
        String phoneNum = jsonObject.getString("phoneNum");
        int corporationId = jsonObject.getInteger("corporationId");
        if (financeService.insertDesport(req, phoneNum)) {
            //保存商家信息
            try {
                add(param, corporationId, phoneNum);
                return "success";
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "fail";
    }

    @Override
    public List<String> storeUserUtokens(Integer proxyId, Integer startPage, Integer pageSize, Integer pages){
        if(proxyId == null){
            return null;
        }
        UserExample userExample = new UserExample();
        userExample.createCriteria().andCorporationidEqualTo(proxyId).andUtokenIsNotNull();

        List<String> utokens = new ArrayList<>();
        List<User> userList;

        pages = (int) Math.ceil(userMapper.countByExample(userExample)/(double)pageSize);

        userExample.setDividePage(startPage,pageSize);
        userList = userMapper.selectByExample(userExample);

        for(User user:userList){
            utokens.add(user.getUtoken());
        }
        return utokens;
    }


    @Override
    public List<DeviceUserInfo> getUserList(Integer proxyId, Integer startPage, Integer pageSize, Integer pages,List<DeviceUserInfo> utokens) {
        if(proxyId == null){
            return null;
        }

        UserExample userExample = new UserExample();
        userExample.createCriteria().andCorporationidEqualTo(proxyId);

        List<DeviceUserInfo> duiList = new ArrayList<DeviceUserInfo>();
        List<User> userList;

        pages = (int) Math.ceil(userMapper.countByExample(userExample)/(double)pageSize);
        userExample.setDividePage(startPage,pageSize);
        userList = userMapper.selectByExample(userExample);

        for(User user:userList){
            DeviceUserInfo dui = new DeviceUserInfo();
            dui.setStoreAccount(user.getPhonenum());
            dui.setStoreName(user.getStorename());
            dui.setuToken(user.getUtoken());

         //   utokens.add(user.getUtoken());

            duiList.add(dui);
        }
        return duiList;
    }


    @Override
    public List<DeviceUserInfo> searchUserList(Integer proxyId,String keyword, Integer startPage, Integer pageSize, int[] pages) {

        UserExample userExample = new UserExample();

        if(keyword != null&&!keyword.isEmpty()){
            keyword = "%"+keyword+"%";

            userExample.or().andPhonenumLike(keyword).andCorporationidEqualTo(proxyId);
            userExample.or().andStorenameLike(keyword).andCorporationidEqualTo(proxyId);
        }else{
            userExample.createCriteria().andCorporationidEqualTo(proxyId);
        }



        List<DeviceUserInfo> duiList = new ArrayList<>();
        List<User> userList;

        pages[0] = (int) Math.ceil(userMapper.countByExample(userExample)/(double)pageSize);

        userExample.setDividePage(startPage,pageSize);
        userList = userMapper.selectByExample(userExample);

        for(User user:userList){
            DeviceUserInfo dui = new DeviceUserInfo();
            dui.setStoreAccount(user.getPhonenum());
            dui.setStoreName(user.getStorename());
            dui.setuToken(user.getUtoken());

            duiList.add(dui);
        }
        return duiList;
    }




    @Override
    public DeviceUserInfo getUserByAccount(String storeAccount) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andPhonenumEqualTo(storeAccount);

        User user = userMapper.selectByExample(userExample).get(0);

        DeviceUserInfo dui = new DeviceUserInfo();
        dui.setuToken(user.getUtoken());
        dui.setStoreAccount(user.getPhonenum());
        dui.setStoreName(user.getStorename());

        return  dui;
    }


    @Override
    public String userQrCodeUrl(String phoneNum,String proxyToken){

        if (phoneNum!= null) {
            String wxBindQrUrl = BaseQr.qrUrl(BaseAPI.baseUrl
                    + "/wx/" + Constant.LEDOUYA_PTOKEN + "/" + phoneNum+"_"+proxyToken + "/userAuthCode.do");
            return wxBindQrUrl;
        }
        return null;
    }

    @Override
    public boolean bindToken(String phoneNum, String uToken){
        if(uToken==null||uToken.isEmpty()){
            return false;
        }
        UserExample userExample = new UserExample();
        userExample.createCriteria().andPhonenumEqualTo(phoneNum);
        List<User> users = userMapper.selectByExample(userExample);
        if (users!=null&&users.size()>0){
            User user = users.get(0);
            if(user != null){
                user.setUtoken(uToken);
                if(userMapper.updateByPrimaryKey(user)>0){
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * 获取商户信息通过手机号
     * @param phoneNum
     * @return
     */
    @Override
    public User getUserByPhone(String phoneNum){
        if (phoneNum!=null){
            UserExample userExample = new UserExample();
            userExample.createCriteria().andPhonenumEqualTo(phoneNum);
            List<User> users = userMapper.selectByExample(userExample);
            if (users!=null&&users.size()>0){
              return users.get(0);
            }
        }
        return null;
    }
}

