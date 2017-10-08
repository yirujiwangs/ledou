package service.Impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import dao.*;
import model.*;
import model.base.BaseResult;
import model.base.CommonArea;
import model.base.ProxyArea;
import model.base.ProxyAreaExample;
import model.dto.AdminCity;
import model.dto.FinanceStatistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;
import service.*;
import utils.api.AdBaseAPI;
import utils.api.AdControlClient;
import utils.beanstalkd.BeanstalkException;
import utils.beanstalkd.BeanstalkUtil;
import utils.common.DividePageUtil;
import utils.common.JobConstatnt;
import utils.common.LogUtil;
import utils.common.WechatConstansUtil;
import utils.finance.FinanceUtil;
import utils.func.AdminUtil;
import utils.func.ProxyAccountBuyOrderUtil;
import utils.func.ProxyFinanceRecordUtil;
import utils.secret.Secret;
import utils.secret.SecretCoder;
import utils.serialnum.SerialNumCreater;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/8/15.
 */
@Service
public class SupervisorServiceImpl implements SupervisorService {


    @Autowired
    private WxdevdataMapper wxdevdataMapper;

    @Autowired
    private TokenMapper tokenMapper;

    @Autowired
    private DepositMapper depositMapper;

    @Autowired
    private WxUserMapper wxUserMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private DeviceMapper deviceMapper;

    @Autowired
    private FinanceMapper financeMapper;

    @Autowired
    private WxStatisticsMapper wxStatisticsMapper;

    @Autowired
    private AdminService adminService;
    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private ProxyAccountBuyRecordMapper proxyAccountBuyrecordMapper;

    @Autowired
    private ProxyFinanceMapper proxyFinanceMapper;

    @Autowired
    private ProxyFinanceRecordMapper proxyFinanceRecordMapper;

    @Autowired
    private ProxyAccountService proxyAccountService;

    @Autowired
    private AreaService areaService;

    @Autowired
    private ProxyFinanceSettleRecordMapper proxyFinanceSettleRecordMapper;

    @Autowired
    private DealerService dealerService;

    @Autowired
    private IncomeDetailsProxyMapper incomeDetailsProxyMapper;

    @Autowired
    private ProxyFinanceService proxyFinanceService;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Autowired
    private ProxyAreaService proxyAreaService;

    @Autowired
    private ProxyAreaMapper proxyAreaMapper;

    @Autowired
    private CommonAreaMapper commonAreaMapper;

    @Autowired
    private ProxyBenefitRecordService proxyBenefitRecordService;

    /**
     * @param param
     * @return
     * @descr 获取管理面板关键指标与互动数据
     */
    @Override
    public JSONObject overview(String param) {
//        JSONObject json = JSON.parseObject(param);

        JSONObject json = new JSONObject();

/*        //获取用户总数
        WxUserExample wxUserExample = new WxUserExample();
        wxUserExample.createCriteria().andIdIsNotNull();

        int wxUserCount = wxUserMapper.countByExample(wxUserExample);
        json.put("wxUserCnt", wxUserCount);*/

        //获取设备总数
        DeviceExample deviceExample = new DeviceExample();
        deviceExample.createCriteria().andIdIsNotNull();
        int deviceCnt = deviceMapper.countByExample(deviceExample);
        json.put("deviceCnt", deviceCnt);

        //累计激活设备数
        deviceExample.clear();
        deviceExample.createCriteria().andStatusEqualTo("激活");
        int activeDeviceCnt = deviceMapper.countByExample(deviceExample);
        json.put("activeDeviceCnt", activeDeviceCnt);

        //本月平均设备活跃数
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date monthStart = calendar.getTime();
        int monthDeviceActiveCnt = wxdevdataMapper.countDeviceByDate(monthStart);
        json.put("monthDeviceActiveCnt",monthDeviceActiveCnt);


        //代理商总数
        int adminCnt = adminMapper.countByExample(null);
        json.put("adminCnt",adminCnt);

        //市级代理总数
        AdminExample adminExample = new AdminExample();
        adminExample.createCriteria().andLevelEqualTo(1);
        int cityAdminCnt = adminMapper.countByExample(adminExample);
        json.put("cityAdminCnt", cityAdminCnt);

        //区县级代理总数
        adminExample = new AdminExample();
        adminExample.createCriteria().andLevelEqualTo(0);
        int distAdminCnt = adminMapper.countByExample(adminExample);
        json.put("distAdminCnt", distAdminCnt);


        calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        //月份-1
        calendar.add(Calendar.DATE, -1);
        Date edate = calendar.getTime();

        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date sdate = calendar.getTime();

        //上月新增代理商
        adminExample = new AdminExample();
        adminExample.createCriteria().andCreateTimeBetween(sdate, edate);
        int monthAdminCnt = adminMapper.countByExample(adminExample);
        json.put("monthAdminCnt",monthAdminCnt);

        //上月新增代理商（市级）
        adminExample = new AdminExample();
        adminExample.createCriteria().andCreateTimeBetween(sdate, edate).andLevelEqualTo(1);
        int monthCityAdminCnt = adminMapper.countByExample(adminExample);
        json.put("monthCityAdminCnt",monthCityAdminCnt);
        //上月新增代理商（区县级）
        adminExample = new AdminExample();
        adminExample.createCriteria().andCreateTimeBetween(sdate,edate).andLevelEqualTo(0);
        int monthDistAdminCnt = adminMapper.countByExample(adminExample);
        json.put("monthDistAdminCnt",monthDistAdminCnt);


       /* //根据storeType获取商户总数
        UserExample userExample = new UserExample();
        userExample.createCriteria().andStoretypeEqualTo("PLATFORM");
        userExample.or().andStoretypeEqualTo("NORMAL");
        int userCnt = userMapper.countByExample(userExample);
        json.put("userCnt", userCnt);


        //根据表中sum_deposit字段统计总充值金额
        FinanceExample financeExample = new FinanceExample();
        financeExample.createCriteria().andSumdepositIsNotNull();
        List<Finance> financeList = financeMapper.selectByExample(financeExample);
        int totalSum = 0;
        for (Finance finance : financeList) {
            totalSum += finance.getSumdeposit();
        }

        double totalDesposit = FinanceUtil.Fen2Yuan((double) totalSum);

        json.put("totalDeposit", totalDesposit);*/
        //System.out.println("json1: " + json);
        //更新微信端数据
        //wxUtil.getwxStatistics(wxStatisticsMapper, wxdevdataMapper, tokenMapper);

        //根据wx_statistics表与wxdevdata获取微信摇周边数据
/*        WxStatisticsExample wxStatisticsExample = new WxStatisticsExample();
        wxStatisticsExample.createCriteria().andIdIsNotNull();
        List<WxStatistics> wxStatisticsList = wxStatisticsMapper.selectByExample(wxStatisticsExample);

        int shakeYestUv = 0, shakeYestPv = 0, shakeTotalUv = 0, shakeTotalPv = 0;

        for (int i = 0; i < wxStatisticsList.size(); i++) {
            //shakeTotalPv += wxStatisticsList.get(i).getShakepv();
            shakeTotalUv += wxStatisticsList.get(i).getShakeuv();

            if (i == wxStatisticsList.size() - 1) {
                //shakeYestPv = wxStatisticsList.get(i).getShakepv();
                shakeYestUv = wxStatisticsList.get(i).getShakeuv();
            }
        }

        //json.put("shakeYestPv", shakeYestPv);
        json.put("shakeYestUv", shakeYestUv);
        //json.put("shakeTotalPv", shakeTotalPv);
        json.put("shakeTotalUv", shakeTotalUv);*/

        String pathUrl = "Wxuserinfo/getUserData";
        String res = AdBaseAPI.executeResult(null, "", pathUrl);
        JSONObject jsonObject = JSON.parseObject(res);
        Integer usersNum = jsonObject.getJSONObject("object").getInteger("usersNum");
        Integer yestedayActiveNum = jsonObject.getJSONObject("object").getInteger("yestedayActiveNum");
        Integer activeNum = jsonObject.getJSONObject("object").getInteger("activeNum");
        json.put("wxUserCnt", usersNum);
        json.put("shakeYestUv", yestedayActiveNum);
        json.put("shakeTotalUv", activeNum);
        return json;
    }


    /**
     * @param param
     * @return
     * @descr 管理面板趋势图数据
     */
    @Override
    public JSONObject tendencyChart(String param) {
        JSONObject json = JSON.parseObject(param);

        String showIndex = json.getString("showIndex");
        Date startTime = json.getDate("startTime");
        Date endTime = json.getDate("endTime");

        WxStatisticsExample wxStatisticsExample = new WxStatisticsExample();
        wxStatisticsExample.createCriteria().andDateBetween(startTime, endTime);
        List<WxStatistics> wxStatisticsList = wxStatisticsMapper.selectByExample(wxStatisticsExample);

        json = new JSONObject();
        JSONArray jsons = new JSONArray();
        for (int i = 0; i < wxStatisticsList.size(); i++) {
            switch (showIndex) {
                case "shakeuv":
                    json.put("shakeuv", wxStatisticsList.get(i).getShakeuv());
                    break;
                case "shakepv":
                    json.put("shakepv", wxStatisticsList.get(i).getShakepv());
                    break;
                case "clickuv":
                    json.put("clickuv", wxStatisticsList.get(i).getClickuv());
                    break;
                case "clickpv":
                    json.put("clickpv", wxStatisticsList.get(i).getClickpv());
                    break;
            }
            json.put("recordTime", wxStatisticsList.get(i).getDate());
            jsons.add(json);
            json = new JSONObject();

        }
        json.put("tendencyRecord", jsons);
        return json;
    }


/**********************************************************************************************************************/


    /**
     * @param param
     * @return
     * @descr 账号管理主页面
     */
    @Override
    public JSONObject accountManage(String param) {
        try {
            JSONObject json = JSON.parseObject(param);
            Integer startPage = json.getInteger("startPage");
            Integer pageSize = json.getInteger("pageSize");

            json = new JSONObject();

            //获取普通商户数
            UserExample userExample = new UserExample();
            userExample.createCriteria().andStoretypeEqualTo("NORMAL");
            int normalNum = userMapper.countByExample(userExample);
            json.put("normalNum", normalNum);

            //获取平台商户数
            userExample = new UserExample();
            userExample.createCriteria().andStoretypeEqualTo("PLATFORM");
            int platformNum = userMapper.countByExample(userExample);
            json.put("totalUserNum", platformNum + normalNum);


            //获取运营商户数以及分页数

            //分页 获取运营商信息列表
            Admin ad = new Admin();
            ad.setDividePage(startPage, pageSize);
            List<AdminCity> adminCities = adminMapper.selectAdminCity(ad);

            Integer corporationNum = adminMapper.countByExample(null);
            if (adminCities == null)
                return null;
            int pages = DividePageUtil.getPages(corporationNum , pageSize);

            json.put("corporationNum", corporationNum);
            json.put("pages", pages);

            JSONObject jsonTmp = new JSONObject();
            JSONArray jsons = new JSONArray();
            for (AdminCity adminCity : adminCities) {
                int totalUser, avblUser,
                        totalNormalUser = 0, avblNormalUser = 0,
                        totalPlatformUser = 0, avblPlatformUser = 0;

                jsonTmp.put("account", adminCity.getAccount());
                jsonTmp.put("accountName", adminCity.getUsername());
                jsonTmp.put("phoneNum", adminCity.getPhoneNum());
                jsonTmp.put("remark", adminCity.getDescr());
                jsonTmp.put("status", adminCity.getStatus());
                jsonTmp.put("createTime", adminCity.getCreateTime());
                jsonTmp.put("corporationid", adminCity.getId());
                jsonTmp.put("openId", adminCity.getOpenid());
                jsonTmp.put("cityName", adminCity.getCityName());
                jsonTmp.put("cityCode", adminCity.getCityCode());
                jsonTmp.put("password",SecretCoder.decode(adminCity.getPassword(), SecretCoder.ENCODE_TYPE.PRIVATE, adminCity.getSecretkey()));
                jsonTmp.put("policy",adminCity.getPolicy());
                int level = adminCity.getLevel();
                if(level == 0){
                    jsonTmp.put("proxyType","P");
                }else if(level == 1){
                    jsonTmp.put("proxyType","M");
                }

                userExample = new UserExample();
                userExample.createCriteria().andCorporationidEqualTo(adminCity.getId());
                List<User> userList = userMapper.selectByExample(userExample);

                for (User user : userList) {
                    if (user.getStoretype().equals("NORMAL")) {
                        totalNormalUser++;
                    }
                    if (user.getStoretype().equals("PLATFORM")) {
                        totalPlatformUser++;
                    }
                }

                ProxyAccountCount proxyAccountCount = proxyAccountService.getCertainProxyAccountCountInfo(adminCity.getPhoneNum());

                if (proxyAccountCount != null) {
                    avblNormalUser = proxyAccountCount.getNormal_num_total() - proxyAccountCount.getNormal_num_used();
                    avblPlatformUser = proxyAccountCount.getPlatform_num_total() - proxyAccountCount.getPlatform_num_used();
                }
                totalUser = totalNormalUser + totalPlatformUser;
                avblUser = avblNormalUser + avblPlatformUser;

                jsonTmp.put("totalUser", totalUser);
                jsonTmp.put("avblUser", avblUser);

                jsons.add(jsonTmp);
                jsonTmp = new JSONObject();
            }
            json.put("corporationInfo", jsons);
            return json;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * @param param
     * @return
     * @descr 模糊搜索运营商账号（账号管理页面）
     */
    @Override
    public JSONObject accountManageSearch(String param) {

        JSONObject json = JSON.parseObject(param);
        String keyword = json.getString("keyword");
        Date startTime = json.getDate("startTime");
        Date endTime = json.getDate("endTime");
        Integer startPage = json.getInteger("startPage");
        Integer pageSize = json.getInteger("pageSize");
        String areaCode = json.getString("areaCode");


        json = new JSONObject();
        int[] pages = new int[1];
        List<Admin> admins =
                AdminUtil.corporationAccountSearch(pages, keyword, startTime, endTime, startPage, pageSize, areaCode, adminMapper, proxyAreaMapper, commonAreaMapper);

        if (admins.size() == 0) {
            json.put("corporationInfo", new JSONArray());
            return json;
        }

        json.put("pages", pages);

        //将获得的账号信息列表显示
        JSONObject jsonTmp = new JSONObject();
        JSONArray jsons = new JSONArray();

        for (int i = 0; i < admins.size(); i++) {

            int totalUser, avblUser, totalNormalUser = 0,
                    avblNormalUser = 0, totalPlatformUser = 0,
                    avblPlatformUser = 0;

            jsonTmp.put("account", admins.get(i).getAccount());
            jsonTmp.put("accountName", admins.get(i).getUsername());
            jsonTmp.put("phoneNum", admins.get(i).getPhoneNum());
            jsonTmp.put("remark", admins.get(i).getDescr());
            jsonTmp.put("status", admins.get(i).getStatus());
            jsonTmp.put("createTime", admins.get(i).getCreateTime());
            jsonTmp.put("corporationid", admins.get(i).getId());

            List<AdminCity> adminCities = adminMapper.selectAdminCity(admins.get(i));
            AdminCity adminCity=null;
            if(adminCities!=null && adminCities.size()>0) {
                adminCity = adminCities.get(0);
                jsonTmp.put("openId", adminCity.getOpenid());
                jsonTmp.put("cityName", adminCity.getCityName());
                jsonTmp.put("cityCode", adminCity.getCityCode());
                int level = adminCity.getLevel();
                if(level == 0){
                    jsonTmp.put("proxyType","P");
                }else if(level == 1){
                    jsonTmp.put("proxyType","M");
                }
            }
            UserExample userExample = new UserExample();
            userExample.createCriteria().andCorporationidEqualTo(admins.get(i).getId());
            List<User> userList = userMapper.selectByExample(userExample);

            for (User user : userList) {
                if (user.getStoretype().equals("NORMAL")) {
                    totalNormalUser++;
                    if (!user.getStatus().equals("false")) {
                        avblNormalUser++;
                    }
                }

                if (user.getStoretype().equals("PLATFORM")) {
                    totalPlatformUser++;
                    if (!user.getStatus().equals("false")) {
                        avblPlatformUser++;
                    }
                }
            }

            totalUser = totalNormalUser + totalPlatformUser;
            avblUser = avblNormalUser + avblPlatformUser;

            jsonTmp.put("totalUser", totalUser);
            jsonTmp.put("avblUser", avblUser);
            jsonTmp.put("totalNormalUser", totalNormalUser);
            jsonTmp.put("avblNormalUser", avblNormalUser);
            jsonTmp.put("totalPlatformUser", totalPlatformUser);
            jsonTmp.put("avblPlatformUser", avblPlatformUser);


            jsons.add(jsonTmp);
            jsonTmp = new JSONObject();
        }
        json.put("corporationInfo", jsons);

        return json;


    }


    /**
     * @param param
     * @return
     * @descr 创建运营商账号（账号管理页面）
     */
    @Override
    public JSONObject insertAccount(String param) {
        BaseResult baseResult;
        JSONObject json = JSON.parseObject(param);
        //电话
        String account = json.getString("account");
        String password = json.getString("password");
        Integer areaCode = json.getInteger("areaCode");
        String remark = json.getString("remark");
        String accountName = json.getString("accountName");
        Integer account_num = json.getInteger("account_num");
        String ptoken = json.getString("ptoken");
        String recommentPhone = json.getString("recommentPhone");
        Integer signway = json.getInteger("signway");
        String proxyType = json.getString("proxyType");
        Integer franchise_fee = json.getInteger("franchiseFee");
        Integer policy = json.getInteger("policy");

        if (ptoken == null || ptoken.isEmpty()) {
            ptoken = WechatConstansUtil.PTOKEN_LEDOUYA;
        }

        LogUtil.log(ProxyAccountServiceImpl.class, LogUtil.LogType.INFO, "创建运营商账号，phone=" + account);
        try {
            if (adminService.exitAdmin(account)) {
                baseResult = new BaseResult(-1, "此账号已被使用");
                LogUtil.log(ProxyAccountServiceImpl.class, LogUtil.LogType.WARN, "此账号已被使用，phone=" + account);
                return (JSONObject) JSON.toJSON(baseResult);
            }
            ProxyAreaExample proxyAreaExample = new ProxyAreaExample();
            proxyAreaExample.createCriteria().andArea_ridEqualTo("" + areaCode);
            List<ProxyArea> proxyAreaList = proxyAreaMapper.selectByExample(proxyAreaExample);
            if(proxyAreaList!=null && !proxyAreaList.isEmpty()){
                baseResult = new BaseResult(-1, "该区域已存在代理");
                LogUtil.log(ProxyAccountServiceImpl.class, LogUtil.LogType.WARN, "该区域已存在代理，area_rid=" + areaCode);
                return (JSONObject) JSON.toJSON(baseResult);
            }

            //设置代理商区域属性
            CommonArea commonArea = areaService.getCommonAreaById(areaCode);
            if (commonArea == null) {
                baseResult = new BaseResult(-1, "代理区域不存在");
                LogUtil.log(ProxyAccountServiceImpl.class, LogUtil.LogType.WARN, "代理区域不存在，phone=" + account);
                return (JSONObject) JSON.toJSON(baseResult);
            }


           /* 密码二次加密*/
            Secret secret = SecretCoder.encode(password, SecretCoder.ENCODE_TYPE.PRIVATE);
            password = secret.getResult();
            String proxy_token = SerialNumCreater.ProxyTokenCreate();

            Admin admin = new Admin();
            admin.setAccount(account);
            admin.setPhoneNum(account);
            admin.setPassword(password);
            admin.setSecretkey(secret.getKey());
            admin.setDescr(remark);
            admin.setUsername(accountName);
            admin.setStatus("true");
            admin.setProxy_token(proxy_token);
            admin.setPtoken(ptoken);
            admin.setSignway(signway);
            admin.setFranchise_fee(franchise_fee);
            admin.setPolicy(policy);

            //代理等级
            if(proxyType.equals("P")){
                admin.setLevel(0);
            }else if(proxyType.equals("M")){
                admin.setLevel(1);
            }

            if(recommentPhone!=null){
                Admin admin1 = adminService.getAdminByPhone(recommentPhone);
                if (admin1!=null){
                    String recommentToken = admin1.getProxy_token();
                    admin.setRecommend_token(recommentToken);
                    admin.setRecommend_phone(recommentPhone);
                }
            }


            int flag = adminMapper.insertSelective(admin);
            if (flag > 0) {
                //增加代理商区域信息--proxy_area
                //System.out.println("添加代理商");
                String areaName = commonArea.getFullname();
                if (!areaService.createProxyArea(areaCode, proxy_token,areaName,proxyType,policy)) {
                    baseResult = new BaseResult(-1, "创建代理区域失败");
                    return (JSONObject) JSON.toJSON(baseResult);
                }
                 /*创建代理商账户信息*/
                proxyAccountService.initProxyInfo(admin.getId());

                ProxyFinance proxyFinance = new ProxyFinance();
                proxyFinance.setPhoneNum(account);
                proxyFinance.setBalancing(0.0);
                proxyFinance.setSum_income(0.0);
                proxyFinance.setBalanced(0.0);
                proxyFinance.setAvaiable(0.0);
                proxyFinanceMapper.insertSelective(proxyFinance);

               /*创建代理商库存表*/
                if (proxyAccountService.initProxyAccount(account_num, admin.getAccount(), proxy_token)) {
                    //System.out.println("创建账号库存成功");
                    baseResult = new BaseResult(1, "创建代理区域成功");
                    return (JSONObject) JSON.toJSON(baseResult);
                }

            } else {
                baseResult = new BaseResult(-1, "创建代理区域失败");
                return (JSONObject) JSON.toJSON(baseResult);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (JSONObject) JSON.toJSON(new BaseResult(-1, "创建代理区域失败"));
    }


    /**
     * @param param
     * @return
     * @descr 更新备注信息(账号管理页面)
     */
    @Override
    public JSONObject updateRemark(String param) {
        JSONObject json = JSON.parseObject(param);

        String account = json.getString("account");
        String remark = json.getString("remark");
        Integer policy = json.getInteger("policy");

        if (account == null || remark == null) {
            json = new JSONObject();
            json.put("flag", false);
            return json;
        }

        AdminExample adminExample = new AdminExample();
        adminExample.createCriteria().andAccountEqualTo(account);
        Admin admin = adminMapper.selectByExample(adminExample).get(0);
        admin.setDescr(remark);
        admin.setPolicy(policy);
        int flag = adminMapper.updateByPrimaryKey(admin);
        json = new JSONObject();
        json.put("flag", false);
        if (flag > 0) {
            json.put("flag", true);
        }
        return json;
    }


    /**
     * @param param
     * @return
     * @descr 按照账号状态进行列表
     */
    @Override
    public JSONObject accountListByStatus(String param) {
        JSONObject json = JSON.parseObject(param);
        Integer startPage = json.getInteger("startPage");
        Integer pageSize = json.getInteger("pageSize");
        String status = json.getString("status");


        json = new JSONObject();


        //获取相应状态下运营商户数页数
        AdminExample adminExample = new AdminExample();
        adminExample.createCriteria().andStatusEqualTo(status);
        int corporationNum = adminMapper.countByExample(adminExample);
        int pages = (int) Math.ceil(corporationNum / (double) pageSize);
        json.put("pages", pages);


        //分页 获取相应状态下运营商信息列表
        adminExample = new AdminExample();
        adminExample.createCriteria().andStatusEqualTo(status);
        adminExample.setDividePage(startPage, pageSize);
        List<Admin> admins = adminMapper.selectByExample(adminExample);

        JSONObject jsonTmp = new JSONObject();
        JSONArray jsons = new JSONArray();
        for (int i = 0; i < admins.size(); i++) {

            int totalUser = 0, avblUser = 0, totalNormalUser = 0, avblNormalUser = 0, totalPlatformUser = 0, avblPlatformUser = 0;

            jsonTmp.put("account", admins.get(i).getAccount());
            jsonTmp.put("accountName", admins.get(i).getUsername());
            jsonTmp.put("phoneNum", admins.get(i).getPhoneNum());
            jsonTmp.put("remark", admins.get(i).getDescr());
            jsonTmp.put("status", admins.get(i).getStatus());
            jsonTmp.put("createTime", admins.get(i).getCreateTime());
            jsonTmp.put("corporationid", admins.get(i).getId());

            UserExample userExample = new UserExample();
            userExample.createCriteria().andCorporationidEqualTo(admins.get(i).getId());
            List<User> userList = userMapper.selectByExample(userExample);

            for (User user : userList) {
                if (user.getStoretype().equals("NORMAL")) {
                    totalNormalUser++;
                    if (!user.getStatus().equals("false")) {
                        avblNormalUser++;
                    }
                }

                if (user.getStoretype().equals("PLATFORM")) {
                    totalPlatformUser++;
                    if (!user.getStatus().equals("false")) {
                        avblPlatformUser++;
                    }
                }
            }

            totalUser = totalNormalUser + totalPlatformUser;
            avblUser = avblNormalUser + avblPlatformUser;

            jsonTmp.put("totalUser", totalUser);
            jsonTmp.put("avblUser", avblUser);
            jsonTmp.put("totalNormalUser", totalNormalUser);
            jsonTmp.put("avblNormalUser", avblNormalUser);
            jsonTmp.put("totalPlatformUser", totalPlatformUser);
            jsonTmp.put("avblPlatformUser", avblPlatformUser);


            jsons.add(jsonTmp);
            jsonTmp = new JSONObject();
        }
        json.put("corporationInfo", jsons);

        return json;
    }


    /**
     * @param param
     * @return
     * @descr 账号订单信息列表(账号订单页面)
     */
    @Override
    public JSONObject accountOrderList(String param) {
        JSONObject json = JSON.parseObject(param);
        Integer startPage = json.getInteger("startPage");
        Integer pageSize = json.getInteger("pageSize");
        Integer state = json.getInteger("state");

        BaseResult baseResult = new BaseResult(-1,"账号订单信息查询错误");
        json = new JSONObject();

        //获取分页数
        ProxyAccountBuyRecordExample proxyAccountBuyrecordExample = new ProxyAccountBuyRecordExample();
        if (state != null){
            proxyAccountBuyrecordExample.createCriteria().andStateEqualTo(state);
        }

        int pages = DividePageUtil.getPages(proxyAccountBuyrecordMapper.countByExample(proxyAccountBuyrecordExample), pageSize);
        json.put("pages", pages);

        proxyAccountBuyrecordExample.setDividePage(startPage, pageSize);
        proxyAccountBuyrecordExample.setOrderByClause("createtime DESC");
        List<ProxyAccountBuyRecord> prList = proxyAccountBuyrecordMapper.selectByExample(proxyAccountBuyrecordExample);

        if(prList!=null && prList.size()>0) {
            //获取账号订单详细列表
            JSONArray jsons = new JSONArray();
            JSONObject jsonTmp;

            for (int i = 0; i < prList.size(); i++) {
                jsonTmp = new JSONObject();
                //获得订单号
                jsonTmp.put("orderNo", prList.get(i).getOut_trade_no());
                String out_trade_no = prList.get(i).getOut_trade_no();
                if(out_trade_no != null && out_trade_no.trim().length()>0 ) {
                    //获取订单时间、订单交易金额
                    DepositExample depositExample = new DepositExample();
                    depositExample.createCriteria().andOut_trade_noEqualTo(prList.get(i).getOut_trade_no());
                    List<Deposit> depositList = depositMapper.selectByExample(depositExample);
                    if(depositList!=null && !depositList.isEmpty()){
                        Deposit deposit = depositList.get(0);
                        jsonTmp.put("time", deposit.getDate_time());
                        jsonTmp.put("tradeFee", FinanceUtil.Fen2Yuan(deposit.getFee()));
                    }else {
                        jsonTmp.put("time", prList.get(i).getCreatetime());
                        jsonTmp.put("tradeFee", 0);
                    }
                }

                jsonTmp.put("state", prList.get(i).getState());

//                jsonTmp.put("time", prList.get(i).getCreatetime());

                //获取账号类型、账号个数
                Integer normalNum = prList.get(i).getNormal_num_pay();
                Integer platformNum = prList.get(i).getPlatform_num_pay();

//                Double tradeFee = prList.get(i).getFee();
//                jsonTmp.put("tradeFee", FinanceUtil.Fen2Yuan(tradeFee));

                if (normalNum != 0) {
                    jsonTmp.put("userType", "normal");
                    jsonTmp.put("userNum", normalNum);
                } else if (platformNum != 0) {
                    jsonTmp.put("userType", "platform");
                    jsonTmp.put("userNum", platformNum);
                }
                //System.out.println(JSON.toJSONString(prList));

                Admin admin = adminService.cutAdmin(prList.get(i).getProxy_phone());
                if(admin!=null) {
                    //获取运营商账号及名称
                    jsonTmp.put("account", admin.getAccount());
                    jsonTmp.put("accountName", admin.getUsername());
                }else{
                    jsonTmp.put("account", null);
                    jsonTmp.put("accountName", null);
                }
                jsons.add(jsonTmp);
            }
            json.put("accountOrders", jsons);
            return json;
        }
        return JSON.parseObject(JSON.toJSONString(baseResult));
    }


    /**
     * @param param
     * @return
     * @descr 订单信息模糊查询（账号订单页面）
     */
    @Override
    public JSONObject accountOrderSearch(String param) {

        JSONObject json = JSON.parseObject(param);
        String keyword = json.getString("keyword");
        Date startTime = json.getDate("startTime");
        Date endTime = json.getDate("endTime");
        Integer startPage = json.getInteger("startPage");
        Integer pageSize = json.getInteger("pageSize");
        Integer state = json.getInteger("state");

        json = new JSONObject();

        int[] pages = new int[1];
        List<ProxyAccountBuyRecord> prList = ProxyAccountBuyOrderUtil.accountOrderSearch(keyword, startTime, endTime, startPage, pageSize, state, pages,adminMapper, proxyAccountBuyrecordMapper);

        json.put("pages", pages[0]);

        //获取账号订单详细列表
        JSONArray jsons = new JSONArray();
        JSONObject jsonTmp = new JSONObject();
        for (int i = 0; i < prList.size(); i++) {

            //获得订单号、订单时间、订单交易金额、订单状态
            jsonTmp.put("orderNo", prList.get(i).getOut_trade_no());
//            jsonTmp.put("time", prList.get(i).getCreatetime());
//            jsonTmp.put("tradeFee", prList.get(i).getFee());
            jsonTmp.put("state", prList.get(i).getState());

            String out_trade_no = prList.get(i).getOut_trade_no();
            if(out_trade_no != null && out_trade_no.trim().length()>0 ) {
                //获取订单时间、订单交易金额
                DepositExample depositExample = new DepositExample();
                depositExample.createCriteria().andOut_trade_noEqualTo(prList.get(i).getOut_trade_no());
                List<Deposit> depositList = depositMapper.selectByExample(depositExample);
                if(depositList!=null && !depositList.isEmpty()){
                    Deposit deposit = depositList.get(0);
                    jsonTmp.put("time", deposit.getDate_time());
                    jsonTmp.put("tradeFee", FinanceUtil.Fen2Yuan(deposit.getFee()));
                } else {
                    jsonTmp.put("time", prList.get(i).getCreatetime());
                    jsonTmp.put("tradeFee", 0);
                }
            }

            //获取账号类型、账号个数
            Integer normalNum = prList.get(i).getNormal_num_pay();
            Integer platformNum = prList.get(i).getPlatform_num_pay();

            if (normalNum != 0) {
                jsonTmp.put("userType", "normal");
                jsonTmp.put("userNum", normalNum);
            } else if (platformNum != 0) {
                jsonTmp.put("userType", "platform");
                jsonTmp.put("userNum", platformNum);
            } else {
                jsonTmp = new JSONObject();
                jsonTmp.put("flag", false);
                return jsonTmp;
            }

            //获取运营商账号及名称
            String accountPhone = prList.get(i).getProxy_phone();
            AdminExample adminExample = new AdminExample();
            adminExample.createCriteria().andPhoneNumEqualTo(accountPhone);
            Admin admin = adminMapper.selectByExample(adminExample).get(0);
            jsonTmp.put("account", admin.getAccount());
            jsonTmp.put("accountName", admin.getUsername());

            jsons.add(jsonTmp);
            jsonTmp = new JSONObject();

        }

        json.put("accountOrders", jsons);

        return json;
    }


/***********************************************************************************************************************/


    /**
     * @param param
     * @return
     * @descr 设备管理页面
     */
    @Override
    public JSONObject deviceManage(String param) {

        JSONObject json = JSON.parseObject(param);
        Integer startPage = json.getInteger("startPage");
        Integer pageSize = json.getInteger("pageSize");

        json = new JSONObject();

        BaseResult baseResult = AdControlClient.deviceStatisticTotal(null);
        if (BaseResult.RESULT_SUCCESS == baseResult.getErrcode()) {
            String str = baseResult.getObject().toString();
            JSONObject jsonObject = JSONObject.parseObject(str);
            Integer enableDevices = jsonObject.getInteger("enableDevices");
            Integer totalDevices = jsonObject.getInteger("totalDevices");
            Integer workingDevicesYesterday = jsonObject.getInteger("workingDevicesYesterday");

            Integer workingDevToday = jsonObject.getInteger("workingDevToday");
            Integer activeDevToday = jsonObject.getInteger("activeDevToday");
            Integer activeDevYesterday = jsonObject.getInteger("activeDevYesterday");


            json.put("devTotalNum", totalDevices);
            json.put("devUsedNum", enableDevices);
            json.put("workingDevYesterday", workingDevicesYesterday);
            json.put("workingDevToday", workingDevToday);
            json.put("activeDevToday", activeDevToday);
            json.put("activeDevYesterday", activeDevYesterday);

        } else {
            //失败
            LogUtil.log(this.getClass(), LogUtil.LogType.ERROR, "设备总数获取失败");
        }

        //获取分页数及分页中的运营商信息
        AdminExample adminExample = new AdminExample();
        adminExample.createCriteria().andIdIsNotNull();
        int pages = DividePageUtil.getPages(adminMapper.countByExample(adminExample), pageSize);
        json.put("pages", pages);
        Admin admin = new Admin();
        admin.setPageSize(pageSize);
        admin.setStartPage(startPage);
        List<AdminCity> admins = adminMapper.selectAdminCity(admin);

        List<String> utokens = new ArrayList<>();
        for (int i = 0; i < admins.size(); i++) {
            utokens.add(admins.get(i).getProxy_token());
        }

        //累计采购，累计激活
        //昨日活跃，昨日激活

        JSONArray jsons = new JSONArray();
        JSONObject jsonTmp;

        baseResult = AdControlClient.deviceStatisticProxy(null,utokens);
        if (BaseResult.RESULT_SUCCESS == baseResult.getErrcode()) {
            String str = baseResult.getObject().toString();
            JSONArray jsonArray = JSONObject.parseArray(str);

            if (jsonArray!=null && jsonArray.size()>0) {

                for (int i = 0; i < jsonArray.size(); i++) {

                    JSONObject jsonObject = (JSONObject) jsonArray.get(i);

                    Integer totalDevices = jsonObject.getInteger("totalDevices");
                    Integer enableDevices = jsonObject.getInteger("enableDevices");
                    Integer enableDevicesYesterday = jsonObject.getInteger("enableDevicesYesterday");
                    Integer workingDevicesYesterday = jsonObject.getInteger("workingDevicesYesterday");

                    Integer enableDevicesToday = jsonObject.getInteger("enableDevicesToday");
                    Integer workingDevicesToday = jsonObject.getInteger("workingDevicesToday");

                    jsonTmp = new JSONObject();

                    jsonTmp.put("corporationid", admins.get(i).getId());
                    jsonTmp.put("account", admins.get(i).getAccount());
                    jsonTmp.put("accountName", admins.get(i).getUsername());
                    jsonTmp.put("accountArea", admins.get(i).getCityName());
                    jsonTmp.put("remark", admins.get(i).getDescr());

                    jsonTmp.put("remark", admins.get(i).getDescr());
                    jsonTmp.put("totalDevices", totalDevices);
                    jsonTmp.put("enableDevices", enableDevices);
                    jsonTmp.put("enableDevicesYesterday", enableDevicesYesterday);
                    jsonTmp.put("workingDevicesYesterday", workingDevicesYesterday);

                    jsonTmp.put("enableDevicesToday", enableDevicesToday);
                    jsonTmp.put("workingDevicesToday", workingDevicesToday);

                    jsons.add(jsonTmp);

                }
            }

        } else {
            //失败
            LogUtil.log(this.getClass(), LogUtil.LogType.ERROR, "设备总数获取失败");
        }

        json.put("devInfo", jsons);
        return json;
    }


    /**
     * @param param
     * @return
     * @descr 模糊搜索运营商账号（设备管理页面）
     */
    @Override
    public JSONObject deviceManageSearch(String param) {
        JSONObject json = JSON.parseObject(param);
        String keyword = json.getString("keyword");
        Date startTime = json.getDate("startTime");
        Date endTime = json.getDate("endTime");
        Integer startPage = json.getInteger("startPage");
        Integer pageSize = json.getInteger("pageSize");


        json = new JSONObject();
        int[] pages = new int[1];
        List<Admin> admins = AdminUtil.corporationAccountSearch(pages, keyword, startTime, endTime, startPage, pageSize, adminMapper);

        if (admins.size() == 0) {
            json.put("pages",0);
            json.put("devInfo", new JSONArray());
            return json;
        }

        //int pages = (int) Math.ceil(admins.size() / (double) pageSize);
        json.put("pages", pages[0]);

        //将获得的账号信息列表显示
        JSONObject jsonTmp = new JSONObject();
        JSONArray jsons = new JSONArray();
        for (int i = 0; i < admins.size(); i++) {
            jsonTmp.put("account", admins.get(i).getAccount());
            jsonTmp.put("accountName", admins.get(i).getUsername());
            ProxyArea proxyArea = proxyAreaService.proxyAreaByUtoken(admins.get(i).getProxy_token());
            if(proxyArea!=null){
                jsonTmp.put("accountArea", proxyArea.getArea_name());
            }else{
                jsonTmp.put("accountArea", "未知区域");
            }
            jsonTmp.put("remark", admins.get(i).getDescr());
            List<String> utokens = new ArrayList<>();
            utokens.add(admins.get(i).getProxy_token());
            BaseResult baseResult = AdControlClient.deviceStatisticProxy(null,utokens);
            jsonTmp.put("totalDevices", 0);
            jsonTmp.put("enableDevices", 0);
            jsonTmp.put("enableDevicesYesterday", 0);
            jsonTmp.put("workingDevicesYesterday", 0);

            jsonTmp.put("enableDevicesToday", 0);
            jsonTmp.put("workingDevicesToday", 0);
            if (BaseResult.RESULT_SUCCESS == baseResult.getErrcode()) {
                String str = baseResult.getObject().toString();
                JSONArray jsonArray = JSONObject.parseArray(str);
                if (jsonArray!=null && jsonArray.size()>0) {
                    JSONObject jsonObject = (JSONObject) jsonArray.get(0);

                    Integer totalDevices = jsonObject.getInteger("totalDevices");
                    Integer enableDevices = jsonObject.getInteger("enableDevices");
                    Integer enableDevicesYesterday = jsonObject.getInteger("enableDevicesYesterday");
                    Integer workingDevicesYesterday = jsonObject.getInteger("workingDevicesYesterday");

                    Integer enableDevicesToday = jsonObject.getInteger("enableDevicesToday");
                    Integer workingDevicesToday = jsonObject.getInteger("workingDevicesToday");
                    jsonTmp.put("totalDevices", totalDevices);
                    jsonTmp.put("enableDevices", enableDevices);
                    jsonTmp.put("enableDevicesYesterday", enableDevicesYesterday);
                    jsonTmp.put("workingDevicesYesterday", workingDevicesYesterday);

                    jsonTmp.put("enableDevicesToday", enableDevicesToday);
                    jsonTmp.put("workingDevicesToday", workingDevicesToday);
                }
            }
/*            int corporationId = admins.get(i).getId();
            //运营商设备数
            DeviceExample deviceExample = new DeviceExample();
            deviceExample.createCriteria().andWeixinidEqualTo(corporationId);
            jsonTmp.put("devNum", deviceMapper.countByExample(deviceExample));

            //已绑定设备数
            deviceExample = new DeviceExample();
            deviceExample.createCriteria().andWeixinidEqualTo(corporationId).andPhoneNotEqualTo("");
            jsonTmp.put("devBindedNum", deviceMapper.countByExample(deviceExample));

            //已使用设备数
            deviceExample = new DeviceExample();
            deviceExample.createCriteria().andWeixinidEqualTo(corporationId).andStatusNotEqualTo("未使用");
            jsonTmp.put("devUsedNum", deviceMapper.countByExample(deviceExample));

            jsonTmp.put("remark", admins.get(i).getDescr());*/

            jsons.add(jsonTmp);
            jsonTmp = new JSONObject();
        }

        json.put("devInfo", jsons);
        return json;
    }


    /**
     * @return
     * @descr 未分配设备列表页面
     */
    @Override
    public JSONObject unbindDevManage(String param) {
        JSONObject json = JSON.parseObject(param);
        Integer startPage = json.getInteger("startPage");
        Integer pageSize = json.getInteger("pageSize");

        json = new JSONObject();

        DeviceExample deviceExample = new DeviceExample();
        deviceExample.createCriteria().andWeixinidEqualTo(-1);
        deviceExample.setDividePage(startPage, pageSize);

        List<Device> devices = deviceMapper.selectByExample(deviceExample);

        int pages = (int) Math.ceil(devices.size() / (double) pageSize);
        json.put("pages", pages);

        //获得未分配设备列表
        JSONObject jsonTmp = new JSONObject();
        JSONArray jsons = new JSONArray();
        for (Device device : devices) {
            jsonTmp.put("serialNum", device.getSerialNum());
            jsonTmp.put("deviceId", device.getIbeaconId());
            jsonTmp.put("createTime", device.getCreateTime());
            jsonTmp.put("status", device.getStatus());
            jsonTmp.put("remark", device.getDescr());

            jsons.add(jsonTmp);
            jsonTmp = new JSONObject();
        }

        json.put("unbindDevInfo", jsons);

        return json;
    }


    /**
     * @param param
     * @return
     * @descr 修改未分配设备的备注信息
     */
    @Override
    public JSONObject updateUnbindDevRemark(String param) {
        JSONObject json = JSON.parseObject(param);
        String serialNum = json.getString("serialNum");
        String remark = json.getString("remark");

        json = new JSONObject();
        json.put("flag", false);
        if (serialNum == null || remark == null) {
            return json;
        }

        DeviceExample deviceExample = new DeviceExample();
        deviceExample.createCriteria().andSerialNumEqualTo(serialNum);
        Device device = deviceMapper.selectByExample(deviceExample).get(0);

        device.setDescr(remark);
        int flag = deviceMapper.updateByPrimaryKeySelective(device);

        if (flag > 0) {
            json.put("flag", true);
        }

        return json;
    }


/**********************************************************************************************************************/

    /**
     * @param param
     * @return
     * @descr 财务管理页面
     */
    @Override
    public JSONObject financeManage(String param) {
        JSONObject json = JSON.parseObject(param);
        Integer startPage = json.getInteger("startPage");
        Integer pageSize = json.getInteger("pageSize");

        try {
            json = new JSONObject();

            FinanceStatistics financeStatistics = incomeDetailsProxyMapper.financeStatistic(null);
            if (financeStatistics!=null) {
                json.put("combinationDeviceBenefit", FinanceUtil.Fen2Yuan((double) financeStatistics.getCombinationDeviceBenefit()));
                json.put("indirectDeviceBenefit", FinanceUtil.Fen2Yuan((double) financeStatistics.getIndirectDeviceBenefit()));
                json.put("combinationAdBenefit", FinanceUtil.Fen2Yuan((double) financeStatistics.getCombinationAdBenefit()));
                json.put("storeAdDepositBenefit", FinanceUtil.Fen2Yuan((double)financeStatistics.getStoreAdDepositBenefit()));
            }else {
                json.put("combinationDeviceBenefit", 0);
                json.put("indirectDeviceBenefit", 0);
                json.put("combinationAdBenefit", 0);
                json.put("storeAdDepositBenefit", 0);
            }

            //获取分页数
            AdminExample adminExample = new AdminExample();
            adminExample.createCriteria().andIdIsNotNull();
            int pages = (int) Math.ceil(adminMapper.countByExample(adminExample) / (double) pageSize);
            json.put("pages", pages);

            //运营商财务信息列表
            Admin admin = new Admin();
            admin.setPageSize(pageSize);
            admin.setStartPage(startPage);
            List<AdminCity> admins = adminMapper.selectAdminCity(admin);

            JSONArray jsons = new JSONArray();
            JSONObject jsonTmp = new JSONObject();

            for (int i = 0; i < admins.size(); i++) {
                //jsonTmp.put("corporationid", admins.get(i).getId());
                jsonTmp.put("account", admins.get(i).getAccount());
                jsonTmp.put("accountName", admins.get(i).getUsername());
                jsonTmp.put("accountArea", admins.get(i).getCityName());
                jsonTmp.put("remark", admins.get(i).getDescr());

                String proxy_token = admins.get(i).getProxy_token();
                //获取代理商累计收益
                financeStatistics = incomeDetailsProxyMapper.financeStatistic(proxy_token);
                if (financeStatistics != null) {
                    //jsonTmp.put("combinationDeviceBenefit", FinanceUtil.Fen2Yuan((double) financeStatistics.getCombinationDeviceBenefit()));
                    //jsonTmp.put("indirectDeviceBenefit", FinanceUtil.Fen2Yuan((double) financeStatistics.getIndirectDeviceBenefit()));
                    //广告收益
                    jsonTmp.put("combinationAdBenefit", FinanceUtil.Fen2Yuan((double) financeStatistics.getCombinationAdBenefit()));
                    //充值收益
                    jsonTmp.put("storeAdDepositBenefit", FinanceUtil.Fen2Yuan((double) financeStatistics.getStoreAdDepositBenefit()));
                } else {
                    //jsonTmp.put("combinationDeviceBenefit", 0);
                    //jsonTmp.put("indirectDeviceBenefit", 0);
                    jsonTmp.put("combinationAdBenefit", 0);
                    jsonTmp.put("storeAdDepositBenefit", 0);
                }
                if(admins.get(i).getLevel()==0) {
                    //区级代理
                    int merchatMarketTotal = proxyBenefitRecordService.proxyBenefitTotal(proxy_token);
                    int proxyBenefitTotal = (int) FinanceUtil.Fen2Fen((double) merchatMarketTotal * 0.45);
                    jsonTmp.put("proxyBenefitTotal", proxyBenefitTotal);
                }else if(admins.get(i).getLevel()==1){
                    //市级代理
                    List<ProxyArea> proxyAreaList = proxyAreaService.distProxyAreaByUtoken(proxy_token);
                    if (proxyAreaList != null && !proxyAreaList.isEmpty()) {
                        int merchatMarketTotal = 0;
                        for (ProxyArea area : proxyAreaList) {
                            int merchatMarketCount = proxyBenefitRecordService.proxyBenefitTotal(area.getUtoken());
                            merchatMarketTotal += merchatMarketCount;
                        }
                        int proxyBenefitTotal = (int) FinanceUtil.Fen2Fen(merchatMarketTotal * 0.15);
                        jsonTmp.put("proxyBenefitTotal", proxyBenefitTotal);
                    }else{
                        jsonTmp.put("proxyBenefitTotal",0);
                    }
                }
                jsons.add(jsonTmp);
                //存放新对象
                jsonTmp = new JSONObject();
            }
            json.put("accountFinanceList", jsons);
        }catch (Exception e){
            e.printStackTrace();
        }

        return json;
    }


    /**
     * @param param
     * @return
     * @descr 模糊搜索运营商账号（财务管理页面）
     */
    @Override
    public JSONObject financeManageSearch(String param) {
        JSONObject json = JSON.parseObject(param);
        String keyword = json.getString("keyword");
        Integer startPage = json.getInteger("startPage");
        Integer pageSize = json.getInteger("pageSize");


        json = new JSONObject();
        int[] pages = new int[1];
        List<Admin> admins = AdminUtil.corporationAccountSearch(pages, keyword, startPage, pageSize, adminMapper);

        if (admins.size() == 0) {
            json.put("corporationInfo", new JSONArray());
            return json;
        }

        json.put("pages", pages[0]);

        //将获得的账号信息列表显示
        JSONObject jsonTmp = new JSONObject();
        JSONArray jsons = new JSONArray();

        for (int i = 0; i < admins.size(); i++) {
            jsonTmp.put("account", admins.get(i).getAccount());
            jsonTmp.put("accountName", admins.get(i).getUsername());
            jsonTmp.put("remark", admins.get(i).getDescr());
            ProxyAreaExample proxyAreaExample = new ProxyAreaExample();
            proxyAreaExample.createCriteria().andUtokenEqualTo(admins.get(i).getProxy_token());
            List<ProxyArea> proxyAreaList = proxyAreaMapper.selectByExample(proxyAreaExample);
            if(proxyAreaList != null & !proxyAreaList.isEmpty()) {
                jsonTmp.put("accountArea", proxyAreaList.get(0).getArea_name());
            }else{
                jsonTmp.put("accountArea","不存在");
            }
            String proxy_token = admins.get(i).getProxy_token();
            //获取代理商累计收益
            FinanceStatistics financeStatistics = incomeDetailsProxyMapper.financeStatistic(proxy_token);
            if (financeStatistics != null) {
                //广告收益
                jsonTmp.put("combinationAdBenefit", FinanceUtil.Fen2Yuan((double) financeStatistics.getCombinationAdBenefit()));
                //充值收益
                jsonTmp.put("storeAdDepositBenefit", FinanceUtil.Fen2Yuan((double) financeStatistics.getStoreAdDepositBenefit()));
            } else {
                jsonTmp.put("combinationAdBenefit", 0);
                jsonTmp.put("storeAdDepositBenefit", 0);
            }
            if(admins.get(i).getLevel()==0) {
                //区级代理
                int merchatMarketTotal = proxyBenefitRecordService.proxyBenefitTotal(proxy_token);
                int proxyBenefitTotal = (int) FinanceUtil.Fen2Fen((double) merchatMarketTotal * 0.45);
                jsonTmp.put("proxyBenefitTotal", proxyBenefitTotal);
            }else if(admins.get(i).getLevel()==1){
                //市级代理
                List<ProxyArea> distProxyAreaList = proxyAreaService.distProxyAreaByUtoken(proxy_token);
                if (distProxyAreaList != null && !distProxyAreaList.isEmpty()) {
                    int merchatMarketTotal = 0;
                    for (ProxyArea area : distProxyAreaList) {
                        int merchatMarketCount = proxyBenefitRecordService.proxyBenefitTotal(area.getUtoken());
                        merchatMarketTotal += merchatMarketCount;
                    }
                    int proxyBenefitTotal = (int) FinanceUtil.Fen2Fen(merchatMarketTotal * 0.15);
                    jsonTmp.put("proxyBenefitTotal", proxyBenefitTotal);
                }else{
                    jsonTmp.put("proxyBenefitTotal",0);
                }
            }

   /*         FinanceExample financeExample = new FinanceExample();

            String accountPhone = admins.get(i).getPhoneNum();

            financeExample.createCriteria().andPhonenumEqualTo(accountPhone);
            List<Finance> finaceList = financeMapper.selectByExample(financeExample);

            //获取运营商 商户资产、累计支出、累计充值
            int accountSum = 0, accountSumExpense = 0, accountSumDeposit = 0;
            if (finaceList != null) {
                for (int j = 0; j < finaceList.size(); j++) {
                    accountSum += finaceList.get(j).getSum();
                    accountSumExpense += finaceList.get(j).getSumexpense();
                    accountSumDeposit += finaceList.get(j).getSumdeposit();
                }
            }

            //获取商户累计收益
            Double accountIncome = 0.0;
            ProxyFinance proxyFinance = null;
            ProxyFinanceExample proxyFinanceExample = new ProxyFinanceExample();
            proxyFinanceExample.createCriteria().andPhoneNumEqualTo(accountPhone);
            if (proxyFinanceMapper.selectByExample(proxyFinanceExample).size() != 0) {
                proxyFinance = proxyFinanceMapper.selectByExample(proxyFinanceExample).get(0);
                accountIncome = FinanceUtil.Fen2Yuan(proxyFinance.getSum_income());
            }


            jsonTmp.put("accountSum", FinanceUtil.Fen2Yuan((double) accountSum));
            jsonTmp.put("accountSumExpense", FinanceUtil.Fen2Yuan((double) accountSumExpense));
            jsonTmp.put("accountSumDeposit", FinanceUtil.Fen2Yuan((double) accountSumDeposit));
            jsonTmp.put("accountSumIncome", accountIncome);*/


            jsons.add(jsonTmp);
            jsonTmp = new JSONObject();
        }
        json.put("accountFinanceList", jsons);


        return json;
    }


    /**
     * @param param
     * @return
     * @descr 结算中心页面
     */
    @Override
    public JSONObject settlementCenter(String param) {
        JSONObject json = JSON.parseObject(param);
        Integer startPage = json.getInteger("startPage");
        Integer pageSize = json.getInteger("pageSize");
        Integer tradeState = json.getInteger("tradeState");

        json = new JSONObject();

        ProxyFinanceExample proxyFinanceExample = new ProxyFinanceExample();
        proxyFinanceExample.createCriteria().andIdIsNotNull();
        List<ProxyFinance> proxyFinanceList = proxyFinanceMapper.selectByExample(proxyFinanceExample);


        //数据指标：当前余额，累计收益，已结算金额，待结算金额
        int totalAvaiable = 0, totalIncome = 0, totalBalanced = 0, totalBalancing = 0;

        for (int i = 0; i < proxyFinanceList.size(); i++) {
            totalAvaiable += proxyFinanceList.get(i).getAvaiable();
            totalIncome += proxyFinanceList.get(i).getSum_income();
            totalBalanced += proxyFinanceList.get(i).getBalanced();
            totalBalancing += proxyFinanceList.get(i).getBalancing();
        }

        json.put("totalAvaiable", totalAvaiable);
        json.put("totalIncome", totalIncome);
        json.put("totalBalanced", totalBalanced);
        json.put("totalBalancing", totalBalancing);


        //获取分页数
        ProxyFinanceRecordExample proxyFinanceRecordExample = new ProxyFinanceRecordExample();

        if (tradeState != null) {
            proxyFinanceRecordExample.createCriteria().andIdIsNotNull().andStateEqualTo(tradeState);
        } else {
            proxyFinanceRecordExample.createCriteria().andIdIsNotNull();
        }

        int pages = (int) Math.ceil(proxyFinanceRecordMapper.countByExample(proxyFinanceRecordExample) / (double) pageSize);
        json.put("pages", pages);

        //运营商财务信息列表

        proxyFinanceRecordExample = new ProxyFinanceRecordExample();

        if (tradeState != null) {
            proxyFinanceRecordExample.createCriteria().andStateEqualTo(tradeState);
        } else {
            proxyFinanceRecordExample.createCriteria().andIdIsNotNull();
        }

        proxyFinanceRecordExample.setDividePage(startPage, pageSize);
        List<ProxyFinanceRecord> pfrList = proxyFinanceRecordMapper.selectByExample(proxyFinanceRecordExample);


        JSONArray jsons = new JSONArray();
        JSONObject jsonTmp = new JSONObject();
        //根据proxy_finance_record 获取交易订单id、交易时间、结算金额、结算状态
        for (int i = 0; i < pfrList.size(); i++) {
            jsonTmp.put("tradeId", pfrList.get(i).getId());
            jsonTmp.put("tradeTime", pfrList.get(i).getCreatetime());
            jsonTmp.put("tradeFee", pfrList.get(i).getFee());
            jsonTmp.put("tradeState", pfrList.get(i).getState());

            AdminExample adminExample = new AdminExample();
            adminExample.createCriteria().andPhoneNumEqualTo(pfrList.get(i).getProxy_phone());

            if (adminMapper.selectByExample(adminExample).size() != 0) {
                Admin admin = adminMapper.selectByExample(adminExample).get(0);
                jsonTmp.put("account", admin.getAccount());
                jsonTmp.put("accountName", admin.getUsername());
                ProxyAreaExample proxyAreaExample = new ProxyAreaExample();
                proxyAreaExample.createCriteria().andUtokenEqualTo(admin.getProxy_token());
                List<ProxyArea> proxyAreaList = proxyAreaMapper.selectByExample(proxyAreaExample);
                String area_rid = proxyAreaList.get(0).getArea_rid();
                String area_name = proxyAreaList.get(0).getArea_name();
                jsonTmp.put("area_name",area_name);
                jsonTmp.put("area_rid",area_rid);
            } else {
                jsonTmp.put("account", "");
                jsonTmp.put("accountName", "");
            }


            jsons.add(jsonTmp);
            jsonTmp = new JSONObject();
        }

        json.put("tradeOrderList", jsons);
        return json;
    }


    /**
     * @param param
     * @return
     * @descr 结算中心订单模糊查询
     */
    @Override
    public JSONObject settlementSearch(String param) {
        JSONObject json = JSON.parseObject(param);
        String keyword = json.getString("keyword");
        Date startTime = json.getDate("startTime");
        Date endTime = json.getDate("endTime");
        Integer startPage = json.getInteger("startPage");
        Integer pageSize = json.getInteger("pageSize");

        json = new JSONObject();

        //数据指标：当前余额，累计收益，已结算金额，待结算金额
        int totalAvaiable = 0, totalIncome = 0, totalBalanced = 0, totalBalancing = 0;
        ProxyFinance proxyFinance = proxyFinanceMapper.selectByKeywords(startTime, endTime, keyword);
        if(proxyFinance!=null){
            totalAvaiable += proxyFinance.getAvaiable();
            totalIncome += proxyFinance.getSum_income();
            totalBalanced += proxyFinance.getBalanced();
            totalBalancing += proxyFinance.getBalancing();
        }

        json.put("totalAvaiable", totalAvaiable);
        json.put("totalIncome", totalIncome);
        json.put("totalBalanced", totalBalanced);
        json.put("totalBalancing", totalBalancing);

        int[] pages = new int[1];
        List<ProxyFinanceRecord> pfrList = ProxyFinanceRecordUtil.settlementOrderSearch(keyword, startTime, endTime, startPage, pageSize, pages, adminMapper, proxyFinanceRecordMapper);
        json.put("pages", pages);

        //获取账号订单详细列表
        JSONArray jsons = new JSONArray();
        JSONObject jsonTmp = new JSONObject();

        for (int i = 0; i < pfrList.size(); i++) {
            jsonTmp.put("tradeId", pfrList.get(i).getId());
            jsonTmp.put("tradeTime", pfrList.get(i).getCreatetime());
            jsonTmp.put("tradeFee", pfrList.get(i).getFee());
            jsonTmp.put("tradeState", pfrList.get(i).getState());

            AdminExample adminExample = new AdminExample();
            adminExample.createCriteria().andPhoneNumEqualTo(pfrList.get(i).getProxy_phone());

            if (adminMapper.selectByExample(adminExample).size() != 0) {
                Admin admin = adminMapper.selectByExample(adminExample).get(0);
                jsonTmp.put("account", admin.getAccount());
                jsonTmp.put("accountName", admin.getUsername());
                ProxyAreaExample proxyAreaExample = new ProxyAreaExample();
                proxyAreaExample.createCriteria().andUtokenEqualTo(admin.getProxy_token());
                List<ProxyArea> proxyAreaList = proxyAreaMapper.selectByExample(proxyAreaExample);
                String area_rid = proxyAreaList.get(0).getArea_rid();
                String area_name = proxyAreaList.get(0).getArea_name();
                jsonTmp.put("area_name",area_name);
                jsonTmp.put("area_rid",area_rid);
            } else {
                jsonTmp.put("account", "");
                jsonTmp.put("accountName", "");
            }

            jsons.add(jsonTmp);
            jsonTmp = new JSONObject();
        }

        json.put("tradeOrderList", jsons);
        return json;
    }


    /**
     * @param param
     * @return
     * @descr 申请结算处理(结算中心页面)
     */
    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public JSONObject financeOrderProcess(String param) {
        JSONObject json = JSON.parseObject(param);

        Integer tradeState = json.getInteger("tradeState");
        Integer tradeId = json.getInteger("tradeId");

        json = new JSONObject();
        json.put("flag", false);

        if (tradeId == null || tradeState == null) {
            return json;
        }

        ProxyFinanceRecordExample proxyFinanceRecordExample = new ProxyFinanceRecordExample();
        proxyFinanceRecordExample.createCriteria().andIdEqualTo(tradeId);

        ProxyFinanceRecord pfr;
        if (proxyFinanceRecordMapper.selectByExample(proxyFinanceRecordExample).size() > 0) {
            pfr = proxyFinanceRecordMapper.selectByExample(proxyFinanceRecordExample).get(0);
            synchronized (this) {
                ProxyFinance proxyFinance = proxyFinanceMapper.selectByPhone(pfr.getProxy_phone());
                if (proxyFinance != null) {
                    Double money = pfr.getFee();
                    if (tradeState == 1) {
                        //完成结算
                        proxyFinance.setBalancing(proxyFinance.getBalancing() - money);
                        proxyFinance.setBalanced(proxyFinance.getBalanced() + money);
                    } else if (tradeState == 2) {
                        //拒绝结算
                        proxyFinance.setBalancing(proxyFinance.getBalancing() - money);
                        proxyFinance.setAvaiable(proxyFinance.getAvaiable() + money);
                    }

                    proxyFinanceMapper.updateByPrimaryKey(proxyFinance);
                }
            }

            pfr.setState(tradeState);

            int flag = proxyFinanceRecordMapper.updateByPrimaryKeySelective(pfr);
            if (flag > 0) {
                json.put("flag", true);
            }
        } else {
            return json;
        }
        return json;
    }


    /**
     * @param param
     * @return
     * @descr 结算账号详情(结算中心页面)
     */
    @Override
    public JSONObject settlemnetOrderDetail(String param) {

        JSONObject json = JSON.parseObject(param);
        Integer tradeId = json.getInteger("tradeId");
        json = new JSONObject();
        ProxyFinanceRecord proxyFinanceRecord = proxyFinanceRecordMapper.selectByPrimaryKey(tradeId);


        //获取运营商账号及名称
        AdminExample adminExample = new AdminExample();
        adminExample.createCriteria().andPhoneNumEqualTo(proxyFinanceRecord.getProxy_phone());

        if (adminMapper.selectByExample(adminExample).size() == 0) {
            json.put("flag", false);
            return json;
        }

        Admin admin = adminMapper.selectByExample(adminExample).get(0);
        json.put("account", admin.getAccount());
        json.put("accountName", admin.getUsername());

        if (proxyFinanceRecord.getAlipay_account_num() != null) {
            json.put("settlementType", "alipay");
            json.put("alipayAccountName", proxyFinanceRecord.getAlipay_account_name());
            json.put("alipayAccount", proxyFinanceRecord.getAlipay_account_num());
        } else if (proxyFinanceRecord.getBank_account_num() != null) {
            json.put("settlementType", "bankAccount");
            json.put("bankAccountName", proxyFinanceRecord.getBank_account_name());
            json.put("bankAccount", proxyFinanceRecord.getBank_account_num());
            json.put("bankName", proxyFinanceRecord.getBank_name());
        } else {
            json.put("flag", false);
            return json;
        }

        return json;
    }

    @Override
    public boolean settleProxyIncome(String utoken,String startMonth,String endMonth)throws ParseException{
        //获取到结算区间，指定月初到月末，默认为上月初-上月末
        long etime,stime;
        Calendar calendar = Calendar.getInstance();
        if(startMonth!=null && endMonth!=null){

            stime = new SimpleDateFormat("yyyy-MM").parse(startMonth).getTime();
            etime = new SimpleDateFormat("yyyy-MM").parse(endMonth).getTime();
            calendar.setTimeInMillis(etime);
            calendar.add(Calendar.MONTH,1);
            calendar.add(Calendar.DATE,-1);
            calendar.set(Calendar.HOUR_OF_DAY, 23);
            calendar.set(Calendar.MINUTE, 59);
            calendar.set(Calendar.SECOND, 59);
            etime= calendar.getTimeInMillis();
        }else {
            calendar.setTime(new Date());
            calendar.set(Calendar.HOUR_OF_DAY, 23);
            calendar.set(Calendar.MINUTE, 59);
            calendar.set(Calendar.SECOND, 59);
            calendar.set(Calendar.DAY_OF_MONTH, 1);

            calendar.add(Calendar.DATE, -1);
            etime = calendar.getTimeInMillis();

            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            stime = calendar.getTimeInMillis();
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("stime",stime);
        jsonObject.put("etime", etime);
        jsonObject.put("utoken", utoken);

        try {

            //保存本次任务记录
            ProxyFinanceSettleRecord proxyFinanceSettleRecord = new ProxyFinanceSettleRecord();
            proxyFinanceSettleRecord.setStime(new Date(stime));
            proxyFinanceSettleRecord.setEtime(new Date(etime));
            //  proxyFinanceSettleRecord.setJob_id(jobId + "");
            if(proxyFinanceSettleRecordMapper.insertSelective(proxyFinanceSettleRecord)>0){
                jsonObject.put("id",proxyFinanceSettleRecord.getId());

                //System.out.println("---"+proxyFinanceSettleRecord.getId());

                Long jobId = BeanstalkUtil.put(jsonObject, JobConstatnt.TUBE_PROXY_SETTLE_FINANCE);
                if (jobId>0) {
                    proxyFinanceSettleRecord.setJob_id(jobId + "");
                    proxyFinanceSettleRecordMapper.updateByPrimaryKeySelective(proxyFinanceSettleRecord);
                    return true;
                }
            }


        } catch (BeanstalkException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public synchronized boolean handleSettleProxyIncome(Integer id, String uToken, Long stime, Long etime) {
        //处理资金结算任务，需要加锁
        //严格事务控制，对单个代理商的资金结算作为最小单元进行控制

        //查询任务处理状态
       ProxyFinanceSettleRecord proxyFinanceSettleRecord =  proxyFinanceSettleRecordMapper.selectByPrimaryKey(id);
        if (proxyFinanceSettleRecord!=null){
            if ("P".equals(proxyFinanceSettleRecord.getStatus())){
                //  任务待处理
                //获取平台代理商

                List<Admin> admins = adminService.getAllProxy();
                if (admins!=null && admins.size()>0){
                    //执行各个代理商的资金结算
                    //整个过程根据不同指标，分为：设备购买、设备购买奖励、广告投放金额、广告奖励、商户充值金额、商户充值奖励

                    for(Admin admin:admins) {
                        final String utoken = admin.getProxy_token();
                        final String phone = admin.getAccount();

                        if (uToken!=null){
                            if (!utoken.equals(uToken)){
                                continue;
                            }
                        }

                        java.sql.Date sDate = new  java.sql.Date(stime);
                        java.sql.Date eDate = new  java.sql.Date(etime);

                        System.out.println(""+sDate+"--"+eDate);


                        //我的设备采购量
          //              int myDeviceNum = dealerService.myDevicesNum(uToken,null, sDate, eDate);

          //              System.out.println("mydeciceNum=="+myDeviceNum);

                        try {
                            //需要判断这个月份的数据是否已经被清算过
                            //执行查询
                            IncomeDetailsProxyExample incomeDetailsProxyExample = new IncomeDetailsProxyExample();
                            incomeDetailsProxyExample.createCriteria()
                                    .andUtokenEqualTo(utoken).andRecord_timeEqualTo(sDate);

                            List<IncomeDetailsProxy> incomeDetailsProxies = incomeDetailsProxyMapper.selectByExample(incomeDetailsProxyExample);
                            if(incomeDetailsProxies!=null && incomeDetailsProxies.size()>0){
                                LogUtil.log(this.getClass(), LogUtil.LogType.ERROR,"此代理商"+utoken+"在："+sDate.toString()+"时间的奖励已经被清算过！");
                                continue;
                            }

                            //我的设备采购量
                            int myDeviceNum = dealerService.myDevicesNum(utoken,null, sDate, eDate);

                            System.out.println("mydeciceNum=="+myDeviceNum);

                            //直接下级代理设备采购量
                            int directDeviceNum = dealerService.directDealerDeviceNum(utoken, sDate, eDate);
                            //间接代理设备采购量
                            int indirectDeviceNum = dealerService.indirectDealerDeviceNum(utoken, sDate, eDate);

                            //我的广告金额,普通红包收益
                            int moneyAdNormalSelf=0,adRebateBenefit=0;
                            List<String> utokens = new ArrayList<>();
                            utokens.add(utoken);
                            BaseResult baseResult = AdControlClient.adBenefit(null, utokens, sDate, eDate);
                            if (BaseResult.RESULT_SUCCESS == baseResult.getErrcode()) {
                                JSONObject jsonObject = JSON.parseObject(baseResult.getObject().toString());
                                moneyAdNormalSelf =jsonObject.getInteger("money");
                                adRebateBenefit = jsonObject.getInteger("pmoney");
                            }else {
                                //失败
                                LogUtil.log(this.getClass(), LogUtil.LogType.ERROR,"代理商本人广告金额清算出现问题："+utoken);
                                continue;
                            }

                            //直接代理商的广告金额
                            int moneyAdNormalDealerDirect=0;
                            utokens.clear();
                            List<String> utokenss = adminService.dealerTokens(utoken);
                            if(utokenss!=null && utokenss.size()>0) {
                                utokens.addAll(utokenss);
                                baseResult = AdControlClient.adBenefit(null, utokens, sDate, eDate);
                                if (BaseResult.RESULT_SUCCESS == baseResult.getErrcode()) {
                                    JSONObject jsonObject = JSON.parseObject(baseResult.getObject().toString());
                                    moneyAdNormalDealerDirect = jsonObject.getInteger("money");
                                } else {
                                    LogUtil.log(this.getClass(), LogUtil.LogType.ERROR, "直接代理商广告金额清算出现问题：" + utoken);
                                    continue;
                                }
                            }

                            //下级（区县）设备品牌红包、设备品牌红包收益
                            int dist_device_red = 0;
                            int benefit_dist_device_red = 0;
                            utokens.clear();
//                            List<ProxyArea> areas = proxyAreaService.distProxyAreaByUtoken(utoken);
//                            for(ProxyArea proxyArea :areas){
//                                utokens.add(proxyArea.getUtoken());
//                            }
                            utokens.add(utoken);
                            if(utokens!=null && utokens.size()>0) {
                                //benefitType: 1代表自有设备品牌红包，2代表辖区设备品牌红包
                                baseResult = AdControlClient.adBenefit(null, utokens, sDate, eDate,2);
                                if (BaseResult.RESULT_SUCCESS == baseResult.getErrcode()) {
                                    JSONObject jsonObject = JSON.parseObject(baseResult.getObject().toString());
                                    dist_device_red = jsonObject.getInteger("money");
                                    benefit_dist_device_red = jsonObject.getInteger("pmoney");
                                } else {
                                    LogUtil.log(this.getClass(), LogUtil.LogType.ERROR, "设备品牌红包金额清算出现问题：" + utoken);
                                    continue;
                                }
                            }

                            //下级（区县）商户次年服务费总金额、收益
                            int dist_merchant_market = 0;
                            int benefit_dist_merchant_market = 0;

                            //商户广告金充值总金额
                            int moneyAdDeposit = 0;
                            baseResult = AdControlClient.storeAdDepositSum(null, utoken, sDate, eDate);
                            if (BaseResult.RESULT_SUCCESS == baseResult.getErrcode()) {
                                moneyAdDeposit = (int) baseResult.getObject();
                            }else{
                                LogUtil.log(this.getClass(), LogUtil.LogType.ERROR,"商户广告金充值总金额清算出现问题："+utoken);
                                continue;
                            }

                            //代理商设备激活情况（上月），激活收益
                            //接口：finance/deviceActivatedMoney
                            //示例参数：{"proxyToken":"d6077adb440649fe94e6891343c14868","stime":"1483280977","etime":"1496327377"}
                            int deviceActivatedNum = 0,deviceActivatedBenefit=0;
                            baseResult = AdControlClient.deviceBenefit(utoken, sDate, eDate);
                            if (BaseResult.RESULT_SUCCESS == baseResult.getErrcode()) {
                                JSONObject jsonObject = JSON.parseObject(baseResult.getObject().toString());
                                if (jsonObject!=null) {
                                    deviceActivatedNum = jsonObject.getInteger("num");
                                    deviceActivatedBenefit = jsonObject.getInteger("benefit");
                                }
                            } else {
                                LogUtil.log(this.getClass(), LogUtil.LogType.ERROR, "直接代理商广告金额清算出现问题：" + utoken);
                                continue;
                            }

                            //代理等级（市、区县）
                            String proxyType = "P"; //  区县级
                            int level = admin.getLevel();
                            if(level==0){
                                proxyType = "P";
                            }else if(level==1){
                                proxyType = "M";
                            }

                            //商户服务费总金额
                            int merchant_market = 0;

                            //merchant_market = proxyBenefitRecordService.proxyBenefitTotal(utoken,sDate,eDate);

                            //加盟区域数、加盟费奖励收益
                            int franchise_districts =0;
                            int benefit_franchise_districts =0;
                            if(proxyType.equals("M")) {//市级代理执行加盟费收益计算
                                List<ProxyArea> proxyAreaList = proxyAreaService.distProxyAreaByUtoken(utoken, sDate, eDate);
                                if (proxyAreaList != null && !proxyAreaList.isEmpty()) {
                                    franchise_districts = proxyAreaList.size();
                                    for (ProxyArea proxyArea : proxyAreaList) {
                                        String distAdminUtoken = proxyArea.getUtoken();
                                        Admin distAdmin = adminService.getAdminByProxyToken(distAdminUtoken);
                                        if (distAdmin.getShield() == 0) {
                                            benefit_franchise_districts += distAdmin.getFranchise_fee();
                                        }
                                    }
                                }
                                benefit_franchise_districts = benefitFranchiseDistricts(benefit_franchise_districts, proxyType);
                            }
                            //下级（区县）代理设备采购数
                            int distDeviceBuyNum = 0;
                            if(proxyType.equals("M")) {
                                distDeviceBuyNum = dealerService.distDeviceBuyNum(utoken, sDate, eDate);
                            }

                            IncomeDetailsProxy incomeDetailsProxy = new IncomeDetailsProxy();
                            incomeDetailsProxy.setUtoken(utoken);
                            incomeDetailsProxy.setDevice_buy_amount_dealer_direct(directDeviceNum);
                            incomeDetailsProxy.setDevice_buy_amount_self(myDeviceNum);
                            incomeDetailsProxy.setDevice_buy_amount_dealer_indirect(indirectDeviceNum);

                            incomeDetailsProxy.setMoney_ad_normal_self(moneyAdNormalSelf);
                            incomeDetailsProxy.setMoney_ad_normal_dealer_direct(moneyAdNormalDealerDirect);
                            incomeDetailsProxy.setMoney_deposit_store(moneyAdDeposit);

                            incomeDetailsProxy.setDevice_activated(deviceActivatedNum);
                            incomeDetailsProxy.setBenefit_device_activated(deviceActivatedBenefit);

                            incomeDetailsProxy.setBenefit_rebate_ad(adRebateBenefit);

                            incomeDetailsProxy.setRecord_time(sDate);

                            incomeDetailsProxy.setMerchant_market(merchant_market);

                            incomeDetailsProxy.setFranchise_districts(franchise_districts);

                            incomeDetailsProxy.setDevice_buy_amount_dist(distDeviceBuyNum);

                            incomeDetailsProxy.setBenefit_franchise_districts(benefit_franchise_districts);

                            incomeDetailsProxy.setDist_device_red(dist_device_red);

                            incomeDetailsProxy.setBenefit_dist_device_red(benefit_dist_device_red);

                            incomeDetailsProxy.setDist_merchant_market(dist_merchant_market);

                            incomeDetailsProxy.setBenefit_dist_merchant_market(benefit_dist_merchant_market);


                            //执行收益计算
                            final IncomeDetailsProxy incomeDetailsProxy1 = benefit(incomeDetailsProxy,proxyType);

                            int policy = admin.getPolicy();

                            if(policy==1){
                                //老政策
                                incomeDetailsProxy1.setBenefit_franchise_districts(0);
                                incomeDetailsProxy1.setBenefit_device_buy_dist(0);
                                incomeDetailsProxy1.setBenefit_merchant_market(0);
                                incomeDetailsProxy1.setBenefit_dist_merchant_market(0);
                                incomeDetailsProxy1.setBenefit_dist_device_red(0);
                            }

                            //开启事务
                            boolean result =  transactionTemplate.execute(new TransactionCallback<Boolean>() {
                                @Override
                                public Boolean doInTransaction(TransactionStatus transactionStatus) {

                                    //执行插入
                                    int rows = incomeDetailsProxyMapper.insertSelective(incomeDetailsProxy1);
                                    if (rows > 0) {
                                        int income = incomeDetailsProxy1.getBenefit_ad_normal_self()
                                                + incomeDetailsProxy1.getBenefit_ad_normal_direct()
                                                + incomeDetailsProxy1.getBenefit_device_self()
                                                + incomeDetailsProxy1.getBenefit_device_dealer_direct()
                                                + incomeDetailsProxy1.getBenefit_deivce_dealer_indirect()
                                                + incomeDetailsProxy1.getBenefit_deposit_store()
                                                + incomeDetailsProxy1.getBenefit_device_activated()
                                                + incomeDetailsProxy1.getBenefit_rebate_ad()
                                                + incomeDetailsProxy1.getBenefit_merchant_market()
                                                + incomeDetailsProxy1.getBenefit_franchise_districts()
                                                + incomeDetailsProxy1.getBenefit_device_buy_dist()
                                                + incomeDetailsProxy1.getBenefit_dist_device_red()
                                                + incomeDetailsProxy1.getBenefit_dist_merchant_market();

                                        //执行此代理商收益总和增加
                                        ProxyFinance proxyFinance = proxyFinanceService.selectProxyFinanceByPhone(phone);
                                        if (proxyFinance != null) {
                                            proxyFinance.setSum_income(FinanceUtil.Yuan2Fen(proxyFinance.getSum_income()) + income);
                                            proxyFinance.setAvaiable(FinanceUtil.Yuan2Fen(proxyFinance.getAvaiable()) + income);
                                            proxyFinance.setBalanced(FinanceUtil.Yuan2Fen(proxyFinance.getBalanced()));
                                            proxyFinance.setBalancing(FinanceUtil.Yuan2Fen(proxyFinance.getBalancing()));
                                            //执行收益表更新
                                            rows = proxyFinanceMapper.updateByPrimaryKey(proxyFinance);
                                            if (rows > 0) {
                                                // 代理商收益更新成功
                                                return true;
                                            }
                                        }
                                    }
                                    return false;
                                }
                            });

                            if(result){
                                LogUtil.log(this.getClass(), LogUtil.LogType.SUCCESS, "代理商收益更新成功" + utoken);
                            }else {
                                LogUtil.log(this.getClass(), LogUtil.LogType.ERROR, "代理商收益更新失败" + utoken);
                            }

                        }catch (Exception e){
                            e.printStackTrace();
                        }

                    }
                    return true;
                }
            }
        }


        return false;
    }

    /**
     *
     * 针对金额的代理商收益计算
     *
     * @param incomeDetailsProxy
     * @return
     */
    private IncomeDetailsProxy benefit(IncomeDetailsProxy incomeDetailsProxy,String proxyType){

        //设备采购收益
        int selfDevices = incomeDetailsProxy.getDevice_buy_amount_self();
        int directDevices = incomeDetailsProxy.getDevice_buy_amount_dealer_direct();
        int inDirectDevices = incomeDetailsProxy.getDevice_buy_amount_dealer_indirect();

        int storeDepositMoney = incomeDetailsProxy.getMoney_deposit_store();

        int adSelfGrantMoney = incomeDetailsProxy.getMoney_ad_normal_self();
        int adDirectGrantMoney = incomeDetailsProxy.getMoney_ad_normal_dealer_direct();

        int combineDevices = selfDevices+ directDevices;
        int benefitSelfDevices = benefitDirectDevicePurchase(combineDevices, selfDevices);
        int benefitDirectDevices = benefitDirectDevicePurchase(combineDevices, directDevices);
        int benefitIndirectDevice = benefitIndirectDevicePurchase(inDirectDevices);
        int benefitStoreDeposit = benefitStoreDeposit(storeDepositMoney);
        int benefitAdSelfGrantMoney = benefitAd(adSelfGrantMoney, combineDevices);
        int benefitAdDirectGrantMoney = benefitAd(adDirectGrantMoney, combineDevices);

        //商户服务费奖励
        int benefit_merchant_market = benefitMerchatMarket(incomeDetailsProxy.getMerchant_market(),proxyType);

        //下级（区县）代理商采购奖励
        int distDeviceBuyBenefit = benefitDistDeviceBuy(incomeDetailsProxy.getDevice_buy_amount_dist());


        incomeDetailsProxy.setBenefit_device_self(benefitSelfDevices);
        incomeDetailsProxy.setBenefit_device_dealer_direct(benefitDirectDevices);
        incomeDetailsProxy.setBenefit_deivce_dealer_indirect(benefitIndirectDevice);
        incomeDetailsProxy.setBenefit_deposit_store(benefitStoreDeposit);
        incomeDetailsProxy.setBenefit_ad_normal_self(benefitAdSelfGrantMoney);
        incomeDetailsProxy.setBenefit_ad_normal_direct(benefitAdDirectGrantMoney);
        incomeDetailsProxy.setBenefit_ad_normal_indirect(0);

        //商户服务费暂不使用
        incomeDetailsProxy.setBenefit_merchant_market(0);

        incomeDetailsProxy.setBenefit_device_buy_dist(distDeviceBuyBenefit);

        return incomeDetailsProxy;
    }


    /**
     * 下级（区县）代理设备采购奖励
     * @param device_buy_amount_dist
     * @return
     */
    private int benefitDistDeviceBuy(Integer device_buy_amount_dist) {
        int distDeviceBuyBenefit;
        if(device_buy_amount_dist<=500){
            distDeviceBuyBenefit =  device_buy_amount_dist *5800;
        }else if(device_buy_amount_dist<=1000){
            distDeviceBuyBenefit =  device_buy_amount_dist *6800;
        }else{
            distDeviceBuyBenefit =  device_buy_amount_dist *7800;
        }
        return distDeviceBuyBenefit;
    }


    /**
     *
     * 加盟费收益规则
     *
     * @param benefit_franchise_districts
     * @param proxyType
     * @return
     */
    private int benefitFranchiseDistricts(Integer benefit_franchise_districts, String proxyType) {
        if(proxyType.equals("P")){
            //区县级代理
            return 0;
        }else if(proxyType.equals("M")){
            //市级代理
            return (int)(FinanceUtil.Fen2Fen((double) benefit_franchise_districts * 0.5));
        }else{
            //其他
            return 0;
        }
    }

    /**
     * 商户服务费奖励规则（暂不使用）
     * @param merchant_market
     * @param proxyType
     * @return
     */
    private int benefitMerchatMarket(Integer merchant_market,String proxyType) {
        if(proxyType.equals("P")){
            //区县级代理
            return (int) FinanceUtil.Fen2Fen((double) merchant_market * 0.45);
        }else if(proxyType.equals("M")){
            //市级代理
            return (int) FinanceUtil.Fen2Fen((double) merchant_market * 0.15);
        }else{
            //其他
            return 0;
        }
    }


    /**
     *
     * 组合采购奖励，单位分
     *
     * @param nums
     * @param trueNums
     * @return
     */
    private int benefitDirectDevicePurchase(int nums,int trueNums){
        int benefitScore = 0;
        if (nums<200){
            benefitScore = 0;
        }else if (nums<300){
            benefitScore =  3;
        }else if(nums < 500){
            benefitScore = 4;
        }else if (nums<1000){
            benefitScore = 6;
        }else if(nums<5000){
            benefitScore = 9;
        }else if(nums<10000){
            benefitScore = 12;
        }else {
            benefitScore = 15;
        }

        return benefitScore * 100 * trueNums;
    }

    private int benefitIndirectDevicePurchase(int nums){
        int benefitScore = 0;
        if (nums<500){
            benefitScore = 1;

        }else if (nums<1000){
            benefitScore =  2;

        }else if(nums < 2000){
            benefitScore = 3;

        }else if (nums<5000){
            benefitScore = 4;

        }else if(nums<1000){
            benefitScore = 5;

        }else {
            benefitScore = 6;
        }

        return benefitScore * 100 * nums;
    }

    /**
     * 单位分 商户红包充值
     *
     * @param moneys
     * @return
     */
    private int benefitStoreDeposit(int moneys){
        float benefitScore = 0.04f;
        return  Math.round(moneys * benefitScore);
    }

    /**
     *
     * 获取代理商广告抽成
     *
     * @param moneys
     *               广告发放总金额
     * @param nums
     *              组合设备采购量
     * @return
     */
    private int benefitAd(int moneys,int nums){
        float benefitScore = 0;
        if (nums<10000){
            benefitScore = 0.005f;

        }else if (nums<30000){
            benefitScore =  0.01f;

        }else if(nums < 100000){
            benefitScore = 0.015f;

        }else{
            benefitScore = 0.02f;
        }

        return Math.round(benefitScore * moneys);
    }



}
