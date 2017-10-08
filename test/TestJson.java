import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import dao.*;
import model.*;
import model.DeviceBuyRecord;
import model.base.BaseResult;
import model.base.DivideBaseResult;
import model.base.ProxyArea;
import model.base.ProxyAreaExample;
import model.dto.*;
import model.dto.ProxyBenefitRecord;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import service.*;
import utils.api.AdBaseAPI;
import utils.api.AdControlClient;
import utils.api.BeaconAPI;
import utils.api.wxUtil;
import utils.common.Constant;
import utils.common.DividePageUtil;
import utils.common.LogUtil;
import utils.finance.FinanceUtil;
import utils.func.AdminUtil;
import utils.func.ProxyAccountBuyOrderUtil;
import utils.func.ProxyFinanceRecordUtil;
import utils.secret.Secret;
import utils.secret.SecretCoder;

import java.io.*;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/conf/spring-mvc.xml","/conf/spring-mybatis.xml","/conf/spring.xml"})
public class TestJson {

    @Autowired
    private ProxyAreaService proxyAreaService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private DealerService dealerService;

    @Autowired
    private FinanceService financeService;

    @Autowired
    private ProxyBenefitRecordService proxyBenefitRecordService;

    @Autowired
    private ProxyAccountService proxyAccountService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private IncomeDetailsProxyMapper incomeDetailsProxyMapper;

    @Autowired
    private WxdevdataMapper wxdevdataMapper;

    @Autowired
    private TokenMapper tokenMapper;

    @Autowired
    private WxStatisticsMapper wxStatisticsMapper;

    @Autowired
    private DeviceMapper deviceMapper;

    @Autowired
    private WxUserMapper wxUserMapper;

    @Autowired
    private ProxyFinanceSettleRecordMapper proxyFinanceSettleRecordMapper;

    @Autowired
    private DeviceBuyRecordMapper deviceBuyRecordMapper;

    @Autowired
    private ProxyAccountBuyRecordMapper proxyAccountBuyrecordMapper;

    @Autowired
    private CommonAreaMapper commonAreaMapper;

    @Autowired
    private DepositMapper depositMapper;

    @Autowired
    private ProxyAreaMapper proxyAreaMapper;

    @Autowired
    private ProxyFinanceRecordMapper proxyFinanceRecordMapper;

    @Autowired
    private DevicePriceService devicePriceService;


    @Test
    public void testJson() {
        String res = "{'name':'jackson','age':100,'proxyList': [{'name':'jackson','age':100},{'name':'michael','age':51}]}";
        JSONArray jsonArray = JSON.parseObject(res).getJSONArray("proxyList");
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonTmp = jsonArray.getJSONObject(i);
//            ProxyAreaExample proxyAreaExample = new ProxyAreaExample();
//            proxyAreaExample.createCriteria().andUtokenEqualTo("2");
//            List<ProxyArea> proxyAreaList = proxyAreaMapper.selectByExample(proxyAreaExample);
//            ProxyArea proxyArea = proxyAreaList.get(0);
            ProxyArea proxyArea = proxyAreaService.proxyAreaByUtoken("15");
            String area_name = proxyArea.getArea_name();
            String area_rid = proxyArea.getArea_rid();
            Date start_date = proxyArea.getStart_date();
//            AdminExample adminExample = new AdminExample();
//            adminExample.createCriteria().andProxy_tokenEqualTo("2");
//            List<Admin> adminList = adminMapper.selectByExample(adminExample);
//            Admin adminTmp = adminList.get(0);
            Admin adminTmp = adminService.adminByUtoken("2");
            String username = adminTmp.getUsername();
            String phoneNum = adminTmp.getPhoneNum();
            String startDate = new SimpleDateFormat("yyyy-MM-dd").format(start_date);

            Map<String, Object> dataMap = new HashMap<String, Object>();
            dataMap.put("area_name", area_name);
            dataMap.put("area_rid", area_rid);
            dataMap.put("start_date", startDate);
            dataMap.put("username", username);
            dataMap.put("phoneNum", phoneNum);
            jsonTmp.putAll(dataMap);
        }
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonTmp = jsonArray.getJSONObject(i);
            //System.out.println(jsonTmp.get("test1"));
            //System.out.println(jsonTmp.get("test2"));
        }
        String result = JSON.toJSONString(jsonArray);
        System.out.println(result);

    }

    @Test
    public void testArray() {
        JSONObject jsonObject = new JSONObject();
//        ProxyAreaExample proxyAreaExample = new ProxyAreaExample();
//        proxyAreaExample.createCriteria().andSupertokenEqualTo("1");
//        List<ProxyArea> proxyAreaList = proxyAreaMapper.selectByExample(proxyAreaExample);
        List<ProxyArea> proxyAreaList = proxyAreaService.distProxyAreaByUtoken("1");
        String[] distProxyTokens = new String[proxyAreaList.size()];
        for (int i = 0; i < distProxyTokens.length; i++) {
            distProxyTokens[i] = proxyAreaList.get(i).getUtoken();
        }

        jsonObject.put("distProxyTokens", distProxyTokens);
        String result = JSON.toJSONString(jsonObject);
        System.out.println(result);
    }

    @Test
    public void testList() {
        List<FranchiseFee> franchiseFeeList = adminService.franchiseFeeList("2", 0, 12);
        String result = JSON.toJSONString(franchiseFeeList);
        System.out.println(result);
    }

    @Test
    public void testLogin() {
        Admin admin = adminService.loginCheck("17802929721", "123456");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", admin.getUsername());
        //���ش���ȼ�
        ProxyArea proxyArea = proxyAreaService.proxyAreaByUtoken(admin.getProxy_token());
        if (proxyArea != null) {
            String supertoken = proxyArea.getSupertoken();
            if (supertoken != null && !supertoken.isEmpty()) {
                jsonObject.put("proxyDegree", "city");
            } else {
                jsonObject.put("proxyDegree", "district");
            }
        } else {
            jsonObject.put("proxyDegree", "none");
        }
        String result = JSON.toJSONString(jsonObject);
        System.out.println(result);
    }

    @Test
    public void testPassword() {
        String pass = "123456";
        Secret secret = SecretCoder.encode(pass, SecretCoder.ENCODE_TYPE.PRIVATE);
        System.out.println(secret.getKey() + " " + secret.getResult());
        String key = secret.getKey();
        String result = secret.getResult();
        String passNow = SecretCoder.decode(result, SecretCoder.ENCODE_TYPE.PRIVATE, key);
        System.out.println(passNow);
        String truePass = "fluN+/zQUw8G8hmBG8nhyw==";
        AdminExample adminExample = new AdminExample();
        adminExample.createCriteria().andAccountEqualTo("17802929721");
        List<Admin> admins = adminMapper.selectByExample(adminExample);
        if (admins != null && admins.size() > 0) {
            Admin admin = admins.get(0);
            System.out.println(admin.getAccount());
            String true_password = SecretCoder.decode(admin.getPassword()
                    , SecretCoder.ENCODE_TYPE.PRIVATE, admin.getSecretkey());
            System.out.println(true_password);
        }
    }

    @Test
    public void testPhpData() {
        JSONObject jsonObject = null;
        StringBuffer buffer = new StringBuffer();
        InputStream inputStream = null;
        String requestUrl = "https://shaketest.ledouya.com/Agents/Advertise/advList";
        String requestMethod = "POST";
        String outputStr = null;
        try {
            URL url = new URL(requestUrl);
            HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();
            httpUrlConn.setDoOutput(true);
            httpUrlConn.setDoInput(true);
            httpUrlConn.setUseCaches(false);
            // ��������ʽ��GET/POST��
            httpUrlConn.setRequestMethod(requestMethod);
            if ("GET".equalsIgnoreCase(requestMethod))
                httpUrlConn.connect();

            // ����������Ҫ�ύʱ
            if (null != outputStr) {
                OutputStream outputStream = httpUrlConn.getOutputStream();
                // ע������ʽ����ֹ��������
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }
            //�����ص�������ת�����ַ���
            inputStream = httpUrlConn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            bufferedReader.close();
            inputStreamReader.close();
            // �ͷ���Դ
            inputStream.close();
            inputStream = null;
            httpUrlConn.disconnect();
            //jsonObject = JSONObject.fromObject(buffer.toString());
            jsonObject = JSON.parseObject(buffer.toString());
        } catch (ConnectException ce) {
            ce.printStackTrace();
            System.out.println("Weixin server connection timed out");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("http request error:{}");
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        System.out.println(jsonObject.toJSONString());
    }


    @Test
    public void testProxyKPI() {
        Admin admin = adminService.adminByUtoken("3869457006a84fa28886d4c1e546defb");
        BaseResult baseResult = new BaseResult(-1, "�豸ָ���ȡʧ��");
        String pathurl = "device/getActiveDevice";

        if (admin != null) {
            String proxyToken = admin.getProxy_token();
            String ptoken = admin.getPtoken();

            if (proxyToken != null) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("proxy_token", proxyToken);

                String res = AdBaseAPI.executeResult(ptoken, jsonObject.toJSONString(), pathurl);
                JSONObject resObj = JSON.parseObject(res);
                String dataString = JSON.toJSONString(resObj.get("object"));
                System.out.println(dataString);

//                List<ProxyArea> proxyAreaList = proxyAreaService.distProxyAreaByUtoken(proxyToken);
//                String[] distProxyTokens = new String[proxyAreaList.size()];
//                for (int i = 0; i < distProxyTokens.length; i++) {
//                    distProxyTokens[i] = proxyAreaList.get(i).getUtoken();
//                }
//                jsonObject.put("distProxyTokens", distProxyTokens);

                int dealerNum = proxyAreaService.dealerCountByUtoken(proxyToken);

                int distCountNum = proxyAreaService.distCountByUtoken(proxyToken);


                JSONObject backJSONObject = JSON.parseObject(dataString);

                backJSONObject.put("dealerNum", dealerNum);
                backJSONObject.put("distCountNum", distCountNum);

                if (res != null && !res.isEmpty()) {
                    System.out.println(JSON.toJSONString(new BaseResult(1, "成功", backJSONObject)));
                }
            }
        }
    }

    @Test
    public void testProxyList() {
        Admin admin = adminService.adminByUtoken("3869457006a84fa28886d4c1e546defb");

        String pathUrl = "device/getProxyDeviceList";
        JSONObject pageData = new JSONObject();
        pageData.put("startPage", 1);
        pageData.put("pageSize", 4);
        String param = JSON.toJSONString(pageData);

        if (admin != null) {
            String cityProxyToken = admin.getProxy_token();
            String ptoken = admin.getPtoken();
            if (cityProxyToken != null) {

                String data = proxyAreaService.distProxyAreaList(param, cityProxyToken);
                JSONObject jsonObject = JSON.parseObject(data);
                JSONArray proxyAreaList = jsonObject.getJSONArray("proxyAreaList");
                Integer pages = jsonObject.getInteger("pages");
                String[] distProxyTokens = new String[proxyAreaList.size()];
                for (int i = 0; i < distProxyTokens.length; i++) {
                    distProxyTokens[i] = (String) proxyAreaList.getJSONObject(i).get("utoken");
                }
                jsonObject.put("proxy_token", distProxyTokens);


                String res = AdBaseAPI.executeResult(ptoken, jsonObject.toJSONString(), pathUrl);

                JSONArray jsonArray = JSON.parseObject(res).getJSONArray("object");

                jsonObject.clear();
                jsonObject.put("pages", pages);

                for (int i = 0; i < jsonArray.size(); i++) {
                    JSONObject jsonTmp = jsonArray.getJSONObject(i);
                    String distProxyToken = jsonTmp.getString("proxy_token");

                    //����������+�������룩������ʱ�䡢���������ơ��������ֻ���
                    ProxyArea proxyArea = proxyAreaService.proxyAreaByUtoken(distProxyToken);
                    String area_name = proxyArea.getArea_name();
                    String area_rid = proxyArea.getArea_rid();
                    Date start_date = proxyArea.getStart_date();
                    String startDate = new SimpleDateFormat("yyyy-MM-dd").format(start_date);
                    Admin adminTmp = adminService.adminByUtoken(distProxyToken);
                    String username = adminTmp.getUsername();
                    String phoneNum = adminTmp.getPhoneNum();

                    Map<String, Object> dataMap = new HashMap<String, Object>();
                    dataMap.put("area_name", area_name);
                    dataMap.put("area_rid", area_rid);
                    dataMap.put("start_date", startDate);
                    dataMap.put("username", username);
                    dataMap.put("phoneNum", phoneNum);
                    jsonTmp.putAll(dataMap);
                    jsonTmp.remove("proxy_token");
                }
                jsonObject.put("data", jsonArray);

                if (res != null && !res.isEmpty()) {
                    System.out.println(JSON.toJSONString(new BaseResult(1, "��չ�����������б��ȡ�ɹ�", jsonObject)));
                }
            }
        }
        //System.out.println(JSON.toJSONString(new BaseResult(-1, "�¼������б��ȡʧ��")));
    }

    @Test
    public void testProxyDetailList() {

        Admin admin = adminService.adminByUtoken("3869457006a84fa28886d4c1e546defb");
        JSONObject pageData = new JSONObject();
        pageData.put("startPage", 0);
        pageData.put("pageSize", 10);
        String param = JSON.toJSONString(pageData);

        String pathUrl = "device/getProxyDevicesDetails";

        if (admin != null) {
            String proxyToken = admin.getProxy_token();
            String ptoken = admin.getPtoken();
            if (proxyToken != null) {
                JSONObject jsonObject = JSON.parseObject(param);
                jsonObject.put("proxy_token", proxyToken);
                String res = AdBaseAPI.executeResult(ptoken, jsonObject.toJSONString(), pathUrl);
                JSONObject object = JSON.parseObject(res);
                if(object.getString("object")==null){

                }
                JSONArray jsonArray = object.getJSONObject("object").getJSONArray("data");
                Integer items = object.getJSONObject("object").getInteger("item");
                if(!jsonArray.isEmpty()) {
                    for (int i = jsonArray.size() - 1; i >= 0; i--) {
                        System.out.println(jsonArray.getJSONObject(i).getString("date"));
                        if (jsonArray.getJSONObject(i).getString("date").equals("-1")) {
                            jsonArray.remove(i);
                        } else {
                            break;
                        }
                    }
                }
                object.clear();
                object.put("data",jsonArray);
                object.put("items",items);
                if (res != null && !res.isEmpty()) {
                    System.out.println(JSON.toJSONString(new BaseResult(1, "代理商成功", object)));
                }
            }
            //System.out.println(JSON.toJSONString(new BaseResult(-1, "��չ���������������ȡʧ��")));
        }
    }

    @Test
    public void testProxyDeviceSearch(){
        Admin admin = adminService.adminByUtoken("3869457006a84fa28886d4c1e546defb");
        JSONObject pageData = new JSONObject();
        pageData.put("startPage", 0);
        pageData.put("pageSize", 10);
        pageData.put("proxyType", "P");
//        pageData.put("groupId", 140);
//      pageData.put("status", 1); //1,2,3
//      pageData.put("is_code", 10); //0,1
//        pageData.put("is_code", 10);

        String param = JSON.toJSONString(pageData);

        BaseResult baseResult = new BaseResult(-1, "设备多级搜索失败");
        String pathUrl = "device/proxyDeviceSearch";

        if (admin != null) {
            String proxyToken = admin.getProxy_token();
            String ptoken = admin.getPtoken();
            if (proxyToken != null) {
                JSONObject jsonObject = JSON.parseObject(param);
                jsonObject.put("proxyToken", null);
                String res = BeaconAPI.getProxyDeviceInfos(AdBaseAPI.executeResult(ptoken, jsonObject.toJSONString(), pathUrl));

                if (res != null && !res.isEmpty()) {
                    baseResult = new BaseResult(1, "设备多级搜索成功", res);
                }
            }
        }
        System.out.println(JSON.toJSONString(baseResult));
    }

    @Test
    public void test(){
        Admin admin = adminService.adminByUtoken("3869457006a84fa28886d4c1e546defb");
        JSONObject pageData = new JSONObject();
        pageData.put("startPage", 0);
        pageData.put("pageSize", 10);
        //pageData.put("proxyType", "P");
        BaseResult baseResult = new BaseResult(-1, "设备指标获取失败");
        String pathUrl = "device/proxyDeviceGroups";
        if (admin != null) {
            String proxyToken = admin.getProxy_token();
            String ptoken = admin.getPtoken();
            if (proxyToken != null) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("proxyToken", null);
                String res = AdBaseAPI.executeResult(ptoken, jsonObject.toJSONString(), pathUrl);
                if (res != null) {
                    if (!res.isEmpty()) {
                        baseResult = new BaseResult(1, "设备指标获取成功", JSON.parseObject(res));
                    }
                }
            }
        }
        System.out.print(JSON.toJSONString(baseResult));
    }

    @Test
    public void testUserKPI(){
        Admin admin = adminService.adminByUtoken("3869457006a84fa28886d4c1e546defb");

        String pathurl = "device/getCommercialData";

        if (admin != null) {
            String proxyToken = admin.getProxy_token();
            String ptoken = admin.getPtoken();

            if (proxyToken != null) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("proxy_token", proxyToken);

                String res = AdBaseAPI.executeResult(ptoken, jsonObject.toJSONString(), pathurl);
                jsonObject.clear();
                jsonObject = JSON.parseObject(res);
                Object data = jsonObject.get("object");

                if (res != null && !res.isEmpty()){
                    System.out.println(JSON.toJSONString(new BaseResult(1, "商户关键指标成功", data)));
                }
            }
        }
    }

    @Test
    public void testUserlist(){
        Admin admin = adminService.adminByUtoken("3869457006a84fa28886d4c1e546defb");
        JSONObject pageData = new JSONObject();
        pageData.put("startPage", 1);
        pageData.put("pageSize", 10);
        String param = JSON.toJSONString(pageData);

        String pathUrl = "device/getStorelist";

        if (admin != null) {
            String proxy_token = admin.getProxy_token();
            String ptoken = admin.getPtoken();
            if (proxy_token != null) {
                JSONObject jsonObject = JSON.parseObject(param);
                jsonObject.put("proxy_token", proxy_token);

                String res = AdBaseAPI.executeResult(ptoken, jsonObject.toJSONString(), pathUrl);
                jsonObject.clear();
                jsonObject = JSON.parseObject(res);
                Object data = jsonObject.get("object");

                if (res != null && !res.isEmpty()) {
                    System.out.println(JSON.toJSONString(new BaseResult(1, "商户门店列表获取成功", data)));
                }
            }
        }
    }

    @Test
    public void testUserCerCheck(){
        Admin admin = adminService.adminByUtoken("3869457006a84fa28886d4c1e546defb");
        JSONObject pageData = new JSONObject();
        pageData.put("check_status", 0);
        pageData.put("store_id", 628);
        pageData.put("counter_pic","http://7xovjx.com2.z0.glb.qiniucdn.com/FiMu5aCODOZq_KmRiFi2xtF2H3pV");
        pageData.put("id_pic", "http://7xovjx.com2.z0.glb.qiniucdn.com/FiMu5aCODOZq_KmRiFi2xtF2H3pV");
        pageData.put("id_pic_other", "http://7xovjx.com2.z0.glb.qiniucdn.com/FiMu5aCODOZq_KmRiFi2xtF2H3pV");
        pageData.put("permit_pic", "http://7xovjx.com2.z0.glb.qiniucdn.com/FiMu5aCODOZq_KmRiFi2xtF2H3pV");
        pageData.put("group_pic", null);
        String param = JSON.toJSONString(pageData);

        String pathUrl = "device/submitCheckCer";
        if (admin != null) {
            String proxy_token = admin.getProxy_token();
            String ptoken = admin.getPtoken();
            if (proxy_token != null) {
                JSONObject jsonObject = JSON.parseObject(param);
                //jsonObject.put("proxy_token", null);

                String res = AdBaseAPI.executeResult(ptoken, jsonObject.toJSONString(), pathUrl);
                jsonObject.clear();
                jsonObject = JSON.parseObject(res);
                Integer data = jsonObject.getInteger("error");
                data = (data==0)?1:-1;
                String message = jsonObject.getString("error_reason");


                if (res != null && !res.isEmpty()) {
                    System.out.println(JSON.toJSONString(new BaseResult(data, message)));
                }
            }
        }
    }

    @Test
    public void testSaveDeviceAreaCode(){
        Admin admin = adminService.adminByUtoken("3869457006a84fa28886d4c1e546defb");
        JSONObject pageData = new JSONObject();
        pageData.put("area_code", 999999);
        pageData.put("store_id",123456);
        String param = JSON.toJSONString(pageData);

        String pathUrl = "device/saveDeviceAreaCode";
        if (admin != null) {
            String proxy_token = admin.getProxy_token();
            String ptoken = admin.getPtoken();
            if (proxy_token != null) {
                JSONObject jsonObject = JSON.parseObject(param);

                String res = AdBaseAPI.executeResult(ptoken, jsonObject.toJSONString(), pathUrl);

                if (res != null && !res.isEmpty()) {
                    System.out.println(JSON.toJSONString(new BaseResult(1, "商户门店列表获取成功", res)));
                }
            }
        }
    }

    @Test
    public void testUpdateCheckStatus(){
        Admin admin = adminService.adminByUtoken("3869457006a84fa28886d4c1e546defb");
        JSONObject pageData = new JSONObject();
        pageData.put("check_status", 3);
        pageData.put("store_id", 628);
        pageData.put("check_msg","123");
        String param = JSON.toJSONString(pageData);

        String pathUrl = "device/updateCheckStatus";
        if (admin != null) {
            String proxy_token = admin.getProxy_token();
            String ptoken = admin.getPtoken();
            if (proxy_token != null) {
                JSONObject jsonObject = JSON.parseObject(param);
                //jsonObject.put("proxy_token", null);

                String res = AdBaseAPI.executeResult(ptoken, jsonObject.toJSONString(), pathUrl);
                jsonObject.clear();
                jsonObject = JSON.parseObject(res);
                Integer data = jsonObject.getInteger("error");
                data = (data==0)?1:-1;
                String message = jsonObject.getString("error_reason");


                if (res != null && !res.isEmpty()) {
                    System.out.println(JSON.toJSONString(new BaseResult(data, message)));
                }
            }
        }
    }

    @Test
    public void testDate(){
        //Admin admin = adminService.adminByUtoken("3869457006a84fa28886d4c1e546defb");
        ProxyArea proxyArea = proxyAreaService.proxyAreaByUtoken("cd28c431f5eb4ef4a3804ceed87157fe");
        DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date myDate2 = dateFormat2.parse("2017-08-01 00:00:00");
            System.out.println(proxyArea.getCreatetime().after(myDate2));
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testFranchiseFeeRecord(){
        Admin admin = adminService.adminByUtoken("3869457006a84fa28886d4c1e546defb");
        JSONObject pageData = new JSONObject();
        pageData.put("startPage", 1);
        pageData.put("pageSize", 2);
        String param = JSON.toJSONString(pageData);
        if (admin != null) {
            JSONObject jsonObject = JSON.parseObject(param);
            String proxyToken = admin.getProxy_token();
            Integer startPage = jsonObject.getInteger("startPage");
            Integer pageSize = jsonObject.getInteger("pageSize");
            Integer startSize = (startPage-1)*pageSize;
            jsonObject.clear();
            if (proxyToken != null) {
                int dealerNum = proxyAreaService.dealerCountByUtoken(proxyToken);
                int pages = (int)Math.ceil(dealerNum / (double) pageSize);
                List<FranchiseFee> franchiseFeeList = adminService.franchiseFeeList(proxyToken, startSize, pageSize);
                jsonObject.put("franchiseFeeList",franchiseFeeList);
                jsonObject.put("pages",pages);
                if (jsonObject != null && !jsonObject.isEmpty()) {
                    System.out.println(JSON.toJSONString(new BaseResult(1, "加盟费记录获取成功", jsonObject)));
                }
            }
        }
    }

    @Test
    public void testDateDeviceList(){
        Admin admin = adminService.adminByUtoken("3869457006a84fa28886d4c1e546defb");
        JSONObject pageData = new JSONObject();
        pageData.put("startPage", 1);
        pageData.put("pageSize", 10);
        pageData.put("date",System.currentTimeMillis());
        String param = JSON.toJSONString(pageData);

        BaseResult baseResult = new BaseResult(-1, "设备广告收益详情失败");
        String pathUrl = "/finance/dateDeviceList";

        if (admin != null) {
            String proxyToken = admin.getProxy_token();
            String ptoken = admin.getPtoken();
            if (proxyToken != null) {
                JSONObject jsonObject = JSON.parseObject(param);
                jsonObject.put("proxyToken", null);
                String res = AdBaseAPI.executeResult(ptoken, jsonObject.toJSONString(), pathUrl);

                if (res != null && !res.isEmpty()) {
                    baseResult = new BaseResult(1, "设备广告收益详情成功", res);
                }
            }
        }
        System.out.println(JSON.toJSONString(baseResult));
    }

    @Test
    public void testDateDataIndex(){
        Admin admin = adminService.adminByUtoken("3869457006a84fa28886d4c1e546defb");
        JSONObject pageData = new JSONObject();
        pageData.put("startPage", 1);
        pageData.put("pageSize", 10);
        pageData.put("date",System.currentTimeMillis());
        String param = JSON.toJSONString(pageData);

        BaseResult baseResult = new BaseResult(-1, "数据指标失败");
        String pathUrl = "/finance/dateDataIndex";

        if (admin != null) {
            String proxyToken = admin.getProxy_token();
            String ptoken = admin.getPtoken();
            if (proxyToken != null) {
                JSONObject jsonObject = JSON.parseObject(param);
                jsonObject.put("proxyToken", proxyToken);

                System.out.println(proxyToken);
                String res = AdBaseAPI.executeResult(ptoken, jsonObject.toJSONString(), pathUrl);

                if (res != null && !res.isEmpty()) {
                    baseResult = new BaseResult(1, "数据指标成功", res);
                }
            }
        }
        System.out.println(JSON.toJSONString(baseResult));
    }

    @Test
    public void testStoreAdDeposit(){
        BaseResult baseResult = new BaseResult(BaseResult.RESULT_ERROR,"获取商户广告充值记录失败");
        JSONObject pageData = new JSONObject();
        pageData.put("startPage", 1);
        pageData.put("pageSize", 10);
        pageData.put("date",System.currentTimeMillis());
        String params = JSON.toJSONString(pageData);

        Admin admin = adminService.adminByUtoken("3869457006a84fa28886d4c1e546defb");
        String utoken = admin.getProxy_token();
        JSONObject jsonObject = JSONObject.parseObject(params);

        Integer startPage = jsonObject.getInteger("startPage");
        Integer pageSize = jsonObject.getInteger("pageSize");

        Integer[] pages = new Integer[1];
        List<StoreAdDeposit> storeAdDeposits = financeService.storeAdDeposit(utoken, startPage, pageSize, pages);

        if (storeAdDeposits!=null && storeAdDeposits.size()>0) {
            DivideBaseResult<StoreAdDeposit> storeAdDepositDivideBaseResult = new DivideBaseResult<>();
            storeAdDepositDivideBaseResult.setList(storeAdDeposits);
            storeAdDepositDivideBaseResult.setPage(startPage);
            storeAdDepositDivideBaseResult.setPages(pages[0]);
            storeAdDepositDivideBaseResult.setPageSize(pageSize);

            baseResult.setErrcode(BaseResult.RESULT_SUCCESS);
            baseResult.setErrmsg("获取商户广告充值记录成功");
            baseResult.setObject(storeAdDepositDivideBaseResult);
        }

        System.out.println(JSONObject.toJSONString(baseResult));
    }

    @Test
    public void testStatistics() {
        Admin admin = adminService.adminByUtoken("3869457006a84fa28886d4c1e546defb");
        String utoken = admin.getProxy_token();
        String phone = admin.getAccount();

        FinanceStatistics financeStatistics = financeService.allFinanceStatics_v2(phone, utoken);
        JSONObject jsonObject = new JSONObject();
        ProxyArea proxyArea = proxyAreaService.proxyAreaByUtoken(admin.getProxy_token());
        if (proxyArea != null) {
            String supertoken = proxyArea.getSupertoken();
            if (supertoken != null && !supertoken.isEmpty()) {
                //区级代理
                //商户营销总金额
                int merchatMarketTotal = proxyBenefitRecordService.proxyBenefitTotal(utoken);
                //商户营销分成奖励
                int proxyBenefitTotal = (int) FinanceUtil.Fen2Fen((double) merchatMarketTotal * 0.45);
                jsonObject.put("merchatMarketTotal", merchatMarketTotal);
                jsonObject.put("proxyBenefitTotal", proxyBenefitTotal);
                financeStatistics.setStoreAdDepositBenefit(
                        (int) FinanceUtil.Fen2Fen((double) financeStatistics.getStoreMarketingBenefit() * 0.06 * 0.45));
                jsonObject.put("financeStatistics", financeStatistics);
            } else {
                //市级代理
                List<ProxyArea> proxyAreaList = proxyAreaService.distProxyAreaByUtoken(utoken);
                if (proxyAreaList != null && !proxyAreaList.isEmpty()) {
                    int merchatMarketTotal = 0;
                    for (ProxyArea area : proxyAreaList) {
                        int merchatMarketCount = proxyBenefitRecordService.proxyBenefitTotal(area.getUtoken());
                        merchatMarketTotal += merchatMarketCount;
                    }
                    int proxyBenefitTotal = (int) FinanceUtil.Fen2Fen(merchatMarketTotal * 0.15);
                    //加盟区县总数
                    int distCountNum = proxyAreaList.size();
                    //加盟费奖励
                    double franchiseFee = FinanceUtil.Yuan2Yuan((double) distCountNum * 888 * 0.25);
                    jsonObject.put("merchatMarketTotal", merchatMarketTotal);
                    jsonObject.put("proxyBenefitTotal", proxyBenefitTotal);
                    jsonObject.put("distCountNum", distCountNum);
                    jsonObject.put("franchiseFee", franchiseFee);
                    financeStatistics.setStoreAdDepositBenefit(
                            (int) FinanceUtil.Fen2Fen((double) financeStatistics.getStoreMarketingBenefit() * 0.06 * 0.15));
                    jsonObject.put("financeStatistics", financeStatistics);
                }
            }
            System.out.println(JSON.toJSONString(new BaseResult(1, "收益明细关键信息获取成功", jsonObject)));
        }
    }

    @Test
    public void testproxyBenefitRecordService(){
        System.out.println(proxyBenefitRecordService.proxyBenefitTotal("3869457006a84fa28886d4c1e546defb"));
        System.out.println("111");
        Admin admin = adminService.adminByUtoken("3869457006a84fa28886d4c1e546defb");
        JSONObject pageData = new JSONObject();
        pageData.put("startPage", 1);
        pageData.put("pageSize", 2);
 //       pageData.put("date",System.currentTimeMillis());
        String params = JSON.toJSONString(pageData);
        String utoken = admin.getProxy_token();
        if(utoken!=null) {
            JSONObject jsonObject = JSONObject.parseObject(params);
            Integer startPage = jsonObject.getInteger("startPage");
            Integer pageSize = jsonObject.getInteger("pageSize");
            Integer startSize = (startPage - 1) * pageSize;
            List<ProxyBenefitRecord> proxyMonthBenefitList = proxyBenefitRecordService.proxyBenefitMonthList(utoken, startSize, pageSize);
            if(proxyMonthBenefitList!=null && !proxyMonthBenefitList.isEmpty()){
                int count = proxyBenefitRecordService.proxyBenefitRecordCount(utoken);
                int pages = DividePageUtil.getPages(count, pageSize);
                jsonObject.clear();
                jsonObject.put("proxyMonthBenefitList", proxyMonthBenefitList);
                jsonObject.put("pages", pages);
                System.out.println(JSON.toJSONString(new BaseResult(1, "商户营销分成奖励获取成功", jsonObject)));
            }
        }
        System.out.println(JSON.toJSONString(new BaseResult(-1, "商户营销分成奖励获取失败")));
    }

    @Test
    public void testProxyBenefitDetail(){
        Admin admin = adminService.adminByUtoken("3869457006a84fa28886d4c1e546defb");
        JSONObject pageData = new JSONObject();
        pageData.put("startPage", 1);
        pageData.put("pageSize", 2);
        pageData.put("createtime","2017-04");
        pageData.put("proxyType","P");
        String params = JSON.toJSONString(pageData);
        String utoken = admin.getProxy_token();
        if(utoken!=null) {
            JSONObject jsonObject = JSONObject.parseObject(params);
            Integer startPage = jsonObject.getInteger("startPage");
            Integer pageSize = jsonObject.getInteger("pageSize");
            String createtime = jsonObject.getString("createtime");
            String proxyType = jsonObject.getString("proxyType");
            Integer startSize = (startPage - 1) * pageSize;
            if(proxyType.equals("P")) {
                List<ProxyBenefitRecord> proxyDayBenefitList = proxyBenefitRecordService.proxyBenefitDayList(utoken, createtime, startSize, pageSize);
                if (proxyDayBenefitList != null && !proxyDayBenefitList.isEmpty()) {
                    int count = proxyBenefitRecordService.proxyBenefitRecordCount(utoken);
                    int pages = DividePageUtil.getPages(count, pageSize);
                    jsonObject.clear();
                    jsonObject.put("proxyDayBenefitList", proxyDayBenefitList);
                    jsonObject.put("pages", pages);
                    System.out.println(JSON.toJSONString(new BaseResult(1, "商户营销分成奖励详情获取成功", jsonObject)));
                }
            }else if(proxyType.equals("M")){
                List<ProxyArea> proxyAreaList = proxyAreaService.distProxyAreaByUtoken(utoken);
                JSONArray jsonArray = new JSONArray(proxyAreaList.size());
                for (ProxyArea proxyArea : proxyAreaList){
                    String distProxyToken = proxyArea.getUtoken();
                    String area_rid = proxyArea.getArea_rid();
                    JSONObject object = new JSONObject();
                    ProxyBenefitRecord distProxyBenefit = proxyBenefitRecordService.distProxyBenefitMonth(distProxyToken, createtime);
                    object.put("area_rid", area_rid);
                    object.put("distProxyBenefit",distProxyBenefit);
                    jsonArray.add(object);
                }
                System.out.println(JSON.toJSONString(new BaseResult(1, "商户营销分成奖励详情获取成功", jsonArray)));
            }else{
                System.out.println(JSON.toJSONString(new BaseResult(-1, "商户营销分成奖励详情获取失败")));
            }
        }
        System.out.println(JSON.toJSONString(new BaseResult(-1, "商户营销分成奖励详情获取失败")));
    }

    @Test
    public void testAdvStrategyDetail(){
        Admin admin = adminService.adminByUtoken("3869457006a84fa28886d4c1e546defb");

        BaseResult baseResult = new BaseResult(-1, "投放策略获取失败");
        JSONObject pageData = new JSONObject();
        pageData.put("advUUID","8224_5845B7B8578015845B7B857846");
        pageData.put("choose","area");
        pageData.put("proxyType","M");
        String param = JSON.toJSONString(pageData);

        String pathurl = "advertise/advStrategyDetail";

        if (admin != null) {
            String proxyToken = admin.getProxy_token();
            String ptoken = admin.getPtoken();
            if (proxyToken != null) {
                JSONObject jsonObject = JSON.parseObject(param);
                if(jsonObject.getString("choose")!=null && jsonObject.getString("choose").equals("area")){
                    JSONArray jsonArray = new JSONArray();
                    if(jsonObject.getString("proxyType")!=null &&jsonObject.getString("proxyType").equals("M")) {
                        List<ProxyArea> proxyAreaList = proxyAreaService.distProxyAreaByUtoken(proxyToken);
                        if(proxyAreaList!=null && !proxyAreaList.isEmpty()){
                            for (ProxyArea proxyArea : proxyAreaList){
                                JSONObject object = new JSONObject();
                                object.put("area_rid",proxyArea.getArea_rid());
                                object.put("area_name",proxyArea.getArea_name());
                                jsonArray.add(object);
                            }
                        }
                        baseResult = new BaseResult(1,"投放区域获取成功",jsonArray);
                    }else{
                        ProxyArea proxyArea = proxyAreaService.proxyAreaByUtoken(proxyToken);
                        JSONObject object = new JSONObject();
                        object.put("area_rid",proxyArea.getArea_rid());
                        object.put("area_name",proxyArea.getArea_name());
                        jsonArray.add(object);
                        baseResult = new BaseResult(1,"投放区域获取成功",jsonArray);
                    }
                }
                else{
                    jsonObject.put("proxyToken", proxyToken);

                    String res = AdBaseAPI.executeResult(ptoken, jsonObject.toJSONString(), pathurl);

                    if (res != null) {
                        if (!res.isEmpty()) {
                            baseResult = new BaseResult(1, "投放策略获取成功", res);
                        }
                    }
                }
            }
        }
        System.out.println(JSON.toJSONString(baseResult));
    }

    @Test
    public void testMonthAdminCnt(){
        AdminExample adminExample = new AdminExample();
        int adminCnt = adminMapper.countByExample(null);
        System.out.println(adminCnt);
        //上月新增代理商
        Calendar calendar = Calendar.getInstance();
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

        adminExample.createCriteria().andCreateTimeBetween(sdate, edate);
        int monthAdminCnt = adminMapper.countByExample(adminExample);
        System.out.println(monthAdminCnt);
    }

    @Test
    public void testFinanceManage(){
        JSONObject pageData = new JSONObject();
        pageData.put("startPage", 1);
        pageData.put("pageSize", 10);
        pageData.put("createtime","2017-04");
        pageData.put("proxyType","P");
        String param = JSON.toJSONString(pageData);

        JSONObject json = JSON.parseObject(param);
        Integer startPage = json.getInteger("startPage");
        Integer pageSize = json.getInteger("pageSize");
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
            ProxyArea proxyArea = proxyAreaService.proxyAreaByUtoken(proxy_token);
            if(proxyArea!=null){
                if(proxyArea.getSupertoken()!=null) {
                    //区级代理
                    int merchatMarketTotal = proxyBenefitRecordService.proxyBenefitTotal(proxy_token);
                    int proxyBenefitTotal = (int) FinanceUtil.Fen2Fen((double) merchatMarketTotal * 0.45);
                    jsonTmp.put("proxyBenefitTotal", proxyBenefitTotal);
                }else {
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
            }else{
                jsonTmp.put("proxyBenefitTotal",0);
            }

            jsons.add(jsonTmp);
            jsonTmp = new JSONObject();
            //jsonTmp.clear();
        }
        json.put("accountFinanceList", jsons);
        System.out.println(JSON.toJSON(json));
    }

    @Test
    public void testMonthDeviceActiveCnt(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date monthStart = calendar.getTime();
        System.out.println(monthStart);
        int monthDeviceActiveCnt = wxdevdataMapper.countDeviceByDate(monthStart);
        System.out.println(monthDeviceActiveCnt);
        //json.put("monthDeviceActiveCnt",monthDeviceActiveCnt);
    }

    @Test
    public void testKPI(){
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
        json.put("monthDeviceActiveCnt", monthDeviceActiveCnt);


        //代理商总数
        AdminExample adminExample = new AdminExample();
        int adminCnt = adminMapper.countByExample(null);
        json.put("adminCnt",adminCnt);

        //上月新增代理商
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

        adminExample.createCriteria().andCreateTimeBetween(sdate, edate);
        int monthAdminCnt = adminMapper.countByExample(adminExample);
        json.put("monthAdminCnt", monthAdminCnt);


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
        wxUtil.getwxStatistics(wxStatisticsMapper, wxdevdataMapper, tokenMapper);


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
        json.put("activeNum", activeNum);
        System.out.println(json);

    }

    @Test
    public void testSQL() {

        long stime = 0, etime = 0;
        Calendar calendar = Calendar.getInstance();
        String startMonth = "2017-03";
        String endMonth = "2017-08";
        if (startMonth != null && endMonth != null) {

            ProxyFinanceSettleRecordExample proxyFinanceSettleRecordExample = new ProxyFinanceSettleRecordExample();
            proxyFinanceSettleRecordExample.setOrderByClause("etime DESC limit 0,1");
            List<ProxyFinanceSettleRecord> proxyFinanceSettleRecords = proxyFinanceSettleRecordMapper.selectByExample(proxyFinanceSettleRecordExample);
            long lastEndTime = proxyFinanceSettleRecords.get(0).getEtime().getTime();
            try {
                stime = new SimpleDateFormat("yyyy-MM").parse(startMonth).getTime();
                etime = new SimpleDateFormat("yyyy-MM").parse(endMonth).getTime();
                if (stime < lastEndTime) {
                    calendar.setTimeInMillis(lastEndTime);
                    calendar.add(Calendar.DATE, 1);
                    calendar.set(Calendar.HOUR_OF_DAY, 0);
                    calendar.set(Calendar.MINUTE, 0);
                    calendar.set(Calendar.SECOND, 0);
                    stime = calendar.getTimeInMillis();
                }
                calendar.setTimeInMillis(etime);
                calendar.add(Calendar.MONTH, 1);
                calendar.add(Calendar.DATE, -1);
                calendar.set(Calendar.HOUR_OF_DAY, 23);
                calendar.set(Calendar.MINUTE, 59);
                calendar.set(Calendar.SECOND, 59);
                etime = calendar.getTimeInMillis();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            System.out.println(format.format(stime));
            System.out.println(format.format(etime));
        }
    }

    @Test
    public void testDeviceList(){
        int startPage = 1;
        int pageSize = 10;
        String utoken = "3869457006a84fa28886d4c1e546defb";
        DeviceBuyRecordExample deviceBuyRecordExample = new DeviceBuyRecordExample();
        deviceBuyRecordExample.setDividePage(startPage, pageSize);
        DeviceBuyRecordExample.Criteria criterial = deviceBuyRecordExample.createCriteria().andUtokenEqualTo(utoken);
        List<String> except = new ArrayList<String>();
        except.add("P");
        except.add("D");
        criterial.andStatusNotIn(except);
        deviceBuyRecordExample.setOrderByClause("id");
        List<DeviceBuyRecord> deviceBuyRecordList = deviceBuyRecordMapper.selectByExample(deviceBuyRecordExample);
        for (int i = 0; i < deviceBuyRecordList.size(); i++) {
            System.out.println(deviceBuyRecordList.get(i).getReason()+"---"+deviceBuyRecordList.get(i).getId()
                    +"--"+deviceBuyRecordList.get(i).getStatus());
        }
    }


    @Test
    public void testDeviceManage(){

        Integer startPage = 1;
        Integer pageSize = 8;

        JSONObject json = new JSONObject();

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
        System.out.println(json);
    }

    @Test
    public void testPowderRedList(){
        Admin admin = adminService.adminByUtoken("3869457006a84fa28886d4c1e546defb");
        JSONObject pageData = new JSONObject();
        pageData.put("startPage", 1);
        pageData.put("pageSize", 8);
        String param = JSON.toJSONString(pageData);

        String pathurl = "red/powderRedList";

        if (admin != null) {
            String proxyToken = admin.getProxy_token();
            String ptoken = admin.getPtoken();

            if (proxyToken != null) {
                JSONObject jsonObject = JSON.parseObject(param);
                jsonObject.put("proxy_token", proxyToken);
                String res = AdBaseAPI.executeResult(ptoken, jsonObject.toJSONString(), pathurl);
                if (res != null && !res.isEmpty()){
                    System.out.println(res);
                }
            }
        }
    }

    @Test
    public void testSelectSubByAreaCode(){
        List<String> list = commonAreaMapper.selectSubByAreaCode("210200");
        System.out.println(list.toString());
    }


    @Test
    public void testAccountManageSearch(){
        JSONObject pageData = new JSONObject();
        pageData.put("startPage", 1);
        pageData.put("pageSize", 8);
        pageData.put("startTime", "2017-05-04");
        pageData.put("endTime", "2017-07-01");
        pageData.put("keyword", "123124");
        pageData.put("areaCode", "1");
        String param = JSON.toJSONString(pageData);

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
            System.out.println(json);
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

        System.out.println(json);
    }

    @Test
    public void testFinanceManageSearch(){
        JSONObject pageData = new JSONObject();
        pageData.put("startPage", 1);
        pageData.put("pageSize", 8);
        pageData.put("keyword", "");
        String param = JSON.toJSONString(pageData);
        JSONObject json = JSON.parseObject(param);
        String keyword = json.getString("keyword");
        Integer startPage = json.getInteger("startPage");
        Integer pageSize = json.getInteger("pageSize");


        json = new JSONObject();
        int[] pages = new int[1];
        List<Admin> admins = AdminUtil.corporationAccountSearch(pages, keyword, startPage, pageSize, adminMapper);

        if (admins.size() == 0) {
            json.put("corporationInfo", new JSONArray());
            System.out.println(json);
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
            if(proxyAreaList != null & !proxyAreaList.isEmpty()) {
                if(proxyAreaList.get(0).getSupertoken()!=null) {
                    //区级代理
                    int merchatMarketTotal = proxyBenefitRecordService.proxyBenefitTotal(proxy_token);
                    int proxyBenefitTotal = (int) FinanceUtil.Fen2Fen((double) merchatMarketTotal * 0.45);
                    jsonTmp.put("proxyBenefitTotal", proxyBenefitTotal);
                }else{
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
            }else {
                jsonTmp.put("proxyBenefitTotal",0);
            }

            jsons.add(jsonTmp);
            jsonTmp = new JSONObject();
        }
        json.put("accountFinanceList", jsons);

        System.out.println(json);
    }

    @Test
    public void testGetStoreFinanceDetails(){
        JSONObject pageData = new JSONObject();
        pageData.put("startPage", 1);
        pageData.put("pageSize", 8);
        pageData.put("startTime", "2017-02-01");
        pageData.put("endTime", "2017-07-01");
        pageData.put("phone", "18444444444");
        String param = JSON.toJSONString(pageData);
        JSONObject jsonObject = JSON.parseObject(param);
        Admin admin = adminService.adminByUtoken("3869457006a84fa28886d4c1e546defb");
        if (admin != null) {
            String phone = jsonObject.getString("phone");
            if (phone == null) {
                JSONObject json = new JSONObject();
                json.put("errorcode", -1);
                json.put("errormsg", "账号不存在，请检查账号电话");
                System.out.println(json.toString());
            }
            Integer startPage = jsonObject.getInteger("startPage");
            Integer pageSize = jsonObject.getInteger("pageSize");
            String keyword = jsonObject.getString("keyword");
            List<ShopFinance> shopFinances = financeService.getBelongShopsFinanceInfo(phone, startPage, pageSize, keyword);

            jsonObject = new JSONObject();
            if (startPage != null && startPage == 1) {
                //获取总页数
                int pages = financeService.countBelongShopsFinanceInfo(phone, keyword);
                jsonObject.put("pages", DividePageUtil.getPages(pages, pageSize));
            }
            jsonObject.put("storeDeposits", shopFinances);
            System.out.println(jsonObject.toString());
        }
    }

    @Test
    public void testPublicAuthorize(){
        Admin admin = adminService.adminByUtoken("3869457006a84fa28886d4c1e546defb");

        JSONObject pageData = new JSONObject();
        //pageData.put("appid", 1);
        String param = JSON.toJSONString(pageData);

        String sessionid = "123456789";
        String pathurl = "red/publicAuthorize";

        if (admin != null) {
            String proxyToken = admin.getProxy_token();
            String ptoken = admin.getPtoken();
            if (proxyToken != null) {
                JSONObject jsonObject = JSON.parseObject(param);
                jsonObject.put("proxyToken",proxyToken);
                jsonObject.put("sessionid",sessionid);
                String res = AdBaseAPI.executeResult(ptoken, jsonObject.toJSONString(), pathurl);
                if (res != null && !res.isEmpty()){
                    System.out.println(res);
                }
            }
        }
    }


    @Test
    public void testBigRedList(){
        Admin admin = adminService.adminByUtoken("3869457006a84fa28886d4c1e546defb");
        JSONObject pageData = new JSONObject();
        pageData.put("currentPage", "1");
        pageData.put("pageSize", "5");
        String param = JSON.toJSONString(pageData);

        String pathurl = "red/bigRedList";

        if (admin != null) {
            String proxy_token = admin.getProxy_token();
            String ptoken = admin.getPtoken();

            if (proxy_token != null) {
                JSONObject jsonObject = JSON.parseObject(param);
                jsonObject.put("proxy_token",proxy_token);
                String res = AdBaseAPI.executeResult(ptoken, jsonObject.toJSONString(), pathurl);
                if (res != null && !res.isEmpty()){
                    System.out.println(res);
                }
            }
        }
    }

    @Test
    public void testDistBuyRecordList(){
        Admin admin = adminService.adminByUtoken("3869457006a84fa28886d4c1e546defb");
        JSONObject pageData = new JSONObject();
        pageData.put("startPage", "1");
        pageData.put("pageSize", "3");
        String param = JSON.toJSONString(pageData);
        if (admin != null) {
            JSONObject jsonObject = JSON.parseObject(param);
            String proxyToken = admin.getProxy_token();
            Integer startPage = jsonObject.getInteger("startPage");
            Integer pageSize = jsonObject.getInteger("pageSize");
            Integer startSize = (startPage-1)*pageSize;
            jsonObject.clear();
            if (proxyToken != null) {
                int records = dealerService.countMonthDistBuyRecord(proxyToken);
                int pages = (int)Math.ceil(records / (double) pageSize);
                List<DistBuyRecord> distBuyRecordList = dealerService.monthDistBuyRecord(proxyToken, startSize, pageSize);
                for(DistBuyRecord distBuyRecord : distBuyRecordList){
                    int distDeviceBuyNum = distBuyRecord.getDistDeviceBuyNum();
                    distBuyRecord.setDistDeviceBuyBenefit(benefitDistDeviceBuy(distDeviceBuyNum));
                }
                jsonObject.put("distBuyRecordList", distBuyRecordList);
                jsonObject.put("pages",pages);
                if (jsonObject != null && !jsonObject.isEmpty()) {
                    System.out.println(JSON.toJSONString(new BaseResult(1, "下级（区县）代理采购记录获取成功", jsonObject)));
                }
            }
        }
    }
    private int benefitDistDeviceBuy(Integer device_buy_amount_dist) {
        int distDeviceBuyBenefit = 0;
        if(device_buy_amount_dist<=500){
            distDeviceBuyBenefit =  device_buy_amount_dist *5800;
        }else if(device_buy_amount_dist<=1000){
            distDeviceBuyBenefit =  device_buy_amount_dist *6800;
        }else{
            distDeviceBuyBenefit =  device_buy_amount_dist *7800;
        }
        return distDeviceBuyBenefit;
    }

    @Test
    public void testDistBuyRecordDetail(){
        Admin admin = adminService.adminByUtoken("3869457006a84fa28886d4c1e546defb");
        JSONObject pageData = new JSONObject();
        pageData.put("times", "2017-08");
        pageData.put("startPage", "1");
        pageData.put("pageSize", "3");
        String param = JSON.toJSONString(pageData);
        if (admin != null) {
            JSONObject jsonObject = JSON.parseObject(param);
            String proxyToken = admin.getProxy_token();
            String time = jsonObject.getString("times");
            Integer startPage = jsonObject.getInteger("startPage");
            Integer pageSize = jsonObject.getInteger("pageSize");
            Integer startSize = (startPage-1)*pageSize;
            jsonObject.clear();
            if (proxyToken != null) {
                int records = dealerService.countMonthDistBuyDetail(proxyToken, time);
                int pages = (int)Math.ceil(records / (double) pageSize);
                List<DistBuyDetail> distBuyDetailList = dealerService.monthDistBuyDetail(proxyToken, time,startSize,pageSize);
                jsonObject.put("distBuyDetailList", distBuyDetailList);
                jsonObject.put("pages",pages);
                if (jsonObject != null && !jsonObject.isEmpty()) {
                    System.out.println(JSON.toJSONString(new BaseResult(1, "下级（区县）代理采购记录详情获取成功", jsonObject)));
                }
            }
        }
    }

    @Test
    public void testDateRedDataIndex(){

        Admin admin = adminService.adminByUtoken("3869457006a84fa28886d4c1e546defb");
        BaseResult baseResult = new BaseResult(-1, "辖区内品牌红包相关数据获取失败");
        String pathUrl = "/finance/dateRedDataIndex";

        if (admin != null) {
            String proxyToken = admin.getProxy_token();
            String ptoken = admin.getPtoken();
            if (proxyToken != null) {
                JSONObject jsonObject = new JSONObject();
                List<String> utokens = new ArrayList<>();
                List<ProxyArea> proxyAreaList = proxyAreaService.distProxyAreaByUtoken(proxyToken);
                if(proxyAreaList!=null && !proxyAreaList.isEmpty()){
                    for(ProxyArea proxyArea : proxyAreaList){
                        utokens.add(proxyArea.getUtoken());
                    }
                }
                jsonObject.put("proxy_token", utokens);
                String res = AdBaseAPI.executeResult(ptoken, jsonObject.toJSONString(), pathUrl);
                if (res != null && !res.isEmpty()) {
                    JSONObject object = JSON.parseObject(res);
                    JSONArray list = object.getJSONObject("object").getJSONArray("list");
                    if(list!=null && !list.isEmpty()){
                        for (int i = 0; i < list.size(); i++) {
                            JSONObject obj = list.getJSONObject(i);
                            String token = obj.getString("proxyToken");
                            obj.put("proxyName",adminService.adminByUtoken(token).getUsername());
                        }
                    }
                    baseResult = new BaseResult(object.getInteger("error"), "辖区内品牌红包相关数据获取成功", object.getJSONObject("object"));
                }
            }
        }
        System.out.println(JSON.toJSONString(baseResult));
    }

    @Test
    public void testFee(){
        int distDeviceBuyNum =0;
        distDeviceBuyNum = dealerService.distDeviceBuyNum("3869457006a84fa28886d4c1e546defb",null,null);
        System.out.println(distDeviceBuyNum);
    }



}
