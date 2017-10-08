package service.Impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import dao.*;
import model.*;
import model.base.BaseResult;
import model.base.ProxyArea;
import model.dto.DeviceBuyStatistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import service.*;
import utils.api.AdControlClient;
import utils.common.*;
import utils.serialnum.OrderNumCreater;
import utils.serialnum.SerialNumCreater;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by lenovo on 2015/12/4.
 */
@Service
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private DeviceMapper deviceMapper;
    @Autowired
    private GroupMapper groupMapper;
    @Autowired
    private User_infoMapper userInfoMapper;
    @Autowired
    private HistoryMapper historyMapper;
    @Autowired
    private TokenMapper tokenMapper;

    @Autowired
    private DeviceBuyRecordMapper deviceBuyRecordMapper;

    @Autowired
    private RuleMoneyDeviceBuyMapper ruleMoneyDeviceBuyMapper;
    @Autowired
    private DealerService dealerService;

    @Autowired
    private LogisticsService logisticsService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private ProxyAreaService proxyAreaService;

    @Override
    public JSONObject deviceManage(Integer corporationId, String param) {
        try {
            JSONObject json = JSON.parseObject(param);

            Integer startPage = json.getInteger("startPage");
            Integer pageSize = json.getInteger("pageSize");

            if (corporationId == null) {
                corporationId = json.getInteger("corporationid");
            }

            DeviceExample deviceExample = new DeviceExample();
            deviceExample.createCriteria().andWeixinidEqualTo(corporationId);

            int totalDeviceNum = deviceMapper.countByExample(deviceExample);

            deviceExample = new DeviceExample();
            deviceExample.createCriteria().andWeixinidEqualTo(corporationId).andPhoneEqualTo("");
            int unmatchNum = deviceMapper.countByExample(deviceExample);

            deviceExample = new DeviceExample();
            deviceExample.createCriteria().andWeixinidEqualTo(corporationId).andStatusEqualTo("未使用");
            int unuseNum = deviceMapper.countByExample(deviceExample);

            deviceExample = new DeviceExample();
            deviceExample.createCriteria().andWeixinidEqualTo(corporationId).andStatusEqualTo("使用中");
            int usingNum = deviceMapper.countByExample(deviceExample);

            JSONArray jsons = new JSONArray();

            json = new JSONObject();
            json.put("name", "总设备数");
            json.put("number", totalDeviceNum);
            jsons.add(json);

            json = new JSONObject();
            json.put("name", "未分配设备数");
            json.put("number", unmatchNum);
            jsons.add(json);

            json = new JSONObject();
            json.put("name", "未使用设备数");
            json.put("number", unuseNum);
            jsons.add(json);

            json = new JSONObject();
            json.put("name", "使用中设备数");
            json.put("number", usingNum);
            jsons.add(json);

            json = new JSONObject();
            json.put("amount", jsons);

            UserExample userExample = new UserExample();

            userExample.createCriteria().andCorporationidEqualTo(corporationId);
            int pages = (int) Math.ceil(userMapper.countByExample(userExample) / (double) pageSize);
            json.put("pages", pages);

            userExample.setDividePage(startPage, pageSize);
            List<User> users = userMapper.selectByExample(userExample);
            json.put("allUser", jsonUtil.device2json(users, deviceMapper, groupMapper, userInfoMapper));
            return json;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 添加设备
     * 不与用户绑定
     *
     * @param param
     * @param nameString
     * @return
     */
    public JSONObject add(String param, String nameString) {
        JSONObject json;
        json = JSON.parseObject(param);
        String serialNum = json.getString("serialNum");
        String type = json.getString("type");
        //important
        Token token = tokenMapper.selectByPrimaryKey(19);

        try {
            json = weixinUtil.generateDeviceId(token, tokenMapper, serialNum);
        } catch (Exception e) {
            json = new JSONObject();
            json.put("flag", false);
            return json;
        }
        if (json != null) {
            String device_id = json.getString("device_id");
            int major = json.getInteger("major");
            int minor = json.getInteger("minor");
            String status = "未激活";
            String uuid = json.getString("uuid");

            Device device = new Device(serialNum, device_id,
                    0, -1, "", -1, "", "", "", "", type, "", status, -1, uuid, major, minor, new Date());

            json = new JSONObject();

            try {
                deviceMapper.insert(device);
                json.put("flag", true);
            } catch (Exception e) {
                json.put("flag", false);
            }
           /* if(deviceMapper.insert(device)!=0){
                History history=new History(StrUtil.getUUID(), new Date(), user.getStorename(),
	        			user.getPhonenum(), "设备操作", "添加设备", nameString, "");
	        	historyMapper.insert(history);
	        	json.put("flag",true);
	        }*/
        } else {
            json = new JSONObject();
            json.put("flag", false);
        }
        return json;
    }

    @Transactional
    public JSONObject update(String param, String nameString) {
        JSONObject json;
        json = JSON.parseObject(param);
        String phoneNum = json.getString("account");
        String serialNum = json.getString("ibeaconId");
        String type = json.getString("type");
        String remark = json.getString("remark");
        json = new JSONObject();
        json.put("flag", false);
        DeviceExample deviceExample = new DeviceExample();
        deviceExample.createCriteria().andPhoneEqualTo(phoneNum);
        Device device = deviceMapper.selectByExample(deviceExample).get(0);
        device.setId(device.getId());
        device.setSerialNum(serialNum);
        device.setType(type);
        device.setRemark(remark);
        if (deviceMapper.updateByPrimaryKeySelective(device) != 0) {
            History history = new History(StrUtil.getUUID(), new Date(), device.getStorename(),
                    device.getPhone(), "设备操作", "更新设备", nameString, "");
            historyMapper.insert(history);
            json.put("flag", true);
        }

        return json;
    }

    /**
     * @param param
     * @return
     */
    public JSONObject delete(String param, String nameString) {
        JSONObject json;
        json = JSON.parseObject(param);
        String ibeaconId = json.getString("ibeaconId");
        json = new JSONObject();
        json.put("flag", false);
        DeviceExample deviceExample = new DeviceExample();
        deviceExample.createCriteria().andIbeaconidEqualTo(ibeaconId);
        Device device;
        try {
            device = deviceMapper.selectByExample(deviceExample).get(0);
        } catch (Exception e) {

            return json;
        }
        if (deviceMapper.deleteByExample(deviceExample) != 0) {
            History history = new History(StrUtil.getUUID(), new Date(), device.getStorename(),
                    device.getPhone(), "设备操作", "删除设备", nameString, "");
            historyMapper.insert(history);
            json.put("flag", true);
        }

        return json;
    }


    /**
     * @param param flag
     * @return
     */
    public JSONObject search(String param) {
        JSONObject json;
        json = JSON.parseObject(param);
        String searchkey = json.getString("searchkey");
        String phoneNum = json.getString("account");

        List<Device> devices;
        if (searchkey != null) {

            searchkey = "%" + searchkey + "%";
            List<Device> devicesTmp;
            List<Integer> idList;

            //获取匹配searchkey字段的信息
            DeviceExample deviceExample = new DeviceExample();
            deviceExample.createCriteria().andPhoneEqualTo(phoneNum).andIbeaconidLike(searchkey);
            devicesTmp = deviceMapper.selectByExample(deviceExample);
            devices = devicesTmp;
            idList = new ArrayList<>();
            idList.add(-1);
            if (devices.size() > 0) {
                for (int i = 0; i < devices.size(); i++) {
                    idList.add(devices.get(i).getId());
                }
            }

            //获取匹配descr字段的信息
            deviceExample = new DeviceExample();
            deviceExample.createCriteria().andPhoneEqualTo(phoneNum).andRemarkLike(searchkey).andIdNotIn(idList);

            devicesTmp = deviceMapper.selectByExample(deviceExample);
            devices.addAll(devicesTmp);
            idList = new ArrayList<>();
            idList.add(-1);

            if (devicesTmp.size() > 0) {
                for (int i = 0; i < devices.size(); i++) {
                    idList.add(devices.get(i).getId());
                }
            }

            //获取匹配SerialNum字段的信息
            deviceExample = new DeviceExample();
            deviceExample.createCriteria().andPhoneEqualTo(phoneNum).andSerialNumLike(searchkey).andIdNotIn(idList);
            devicesTmp = deviceMapper.selectByExample(deviceExample);

            if (devicesTmp.size() > 0) {
                devices.addAll(devicesTmp);
                idList = new ArrayList<Integer>();
                idList.add(-1);
                for (int i = 0; i < devices.size(); i++) {
                    idList.add(devices.get(i).getId());
                }
            }

            //在已经获取设备信息列表中或得到符合设备状态与时间范围设备信息
            String status = json.getString("type");
            Date startTime = json.getDate("startTime");
            //System.out.println("startTime : " + startTime);
            Date endTime = json.getDate("endTime");
            //System.out.println("endTime : " + endTime);
            devicesTmp = new ArrayList<>();

            for (int i = 0; i < devices.size(); i++) {
                if (status.equals("all")) {
                    if (startTime == null && endTime == null) {
                        devicesTmp.add(devices.get(i));
                    } else if (startTime != null && endTime == null) {
                        if (devices.get(i).getCreateTime().after(startTime)) {
                            devicesTmp.add(devices.get(i));
                        }
                    } else if (startTime == null && endTime != null) {
                        if (devices.get(i).getCreateTime().before(endTime)) {
                            devicesTmp.add(devices.get(i));
                        }
                    } else if (startTime != null && endTime != null) {
                        if (devices.get(i).getCreateTime().after(startTime) && devices.get(i).getCreateTime().before(endTime)) {
                            devicesTmp.add(devices.get(i));
                        }
                    }
                } else {
                    if (startTime != null && endTime != null) {
                        if (devices.get(i).getStatus().equals(status) && devices.get(i).getCreateTime().after(startTime) &&
                                devices.get(i).getCreateTime().before(endTime)) {
                            devicesTmp.add(devices.get(i));
                        }
                    } else if (startTime != null && endTime == null) {
                        if (devices.get(i).getStatus().equals(status) && devices.get(i).getCreateTime().after(startTime)) {
                            devicesTmp.add(devices.get(i));
                        }
                    } else if (startTime == null && endTime != null) {
                        if (devices.get(i).getStatus().equals(status) && devices.get(i).getCreateTime().before(endTime)) {
                            devicesTmp.add(devices.get(i));
                        }
                    } else {
                        if (devices.get(i).getStatus().equals(status)) {
                            devicesTmp.add(devices.get(i));
                        }

                    }
                }

            }

            json = new JSONObject();
            json.put("general", devicesTmp);
            return json;
        } else {

            String status = json.getString("type");
            //System.out.println("status: " + status);


            Date startTime = json.getDate("startTime");
            //System.out.println("startTime : " + startTime);

            Date endTime = json.getDate("endTime");
            //System.out.println("endTime : " + endTime);

            DeviceExample deviceExample = new DeviceExample();
            DeviceExample.Criteria criteria = deviceExample.createCriteria();
            criteria.andPhoneEqualTo(phoneNum);
            if (!status.equalsIgnoreCase("all")) criteria.andStatusEqualTo(status);
            if (startTime != null) criteria.andCreatetimeGreaterThanOrEqualTo(startTime);
            if (endTime != null) criteria.andCreatetimeLessThanOrEqualTo(endTime);
            devices = deviceMapper.selectByExample(deviceExample);
            json = new JSONObject();
            json.put("general", devices);
            return json;
        }
    }


    @Override
    public JSONObject searchUnbind(Integer corporationid, String param) {
        JSONObject json;
        json = JSON.parseObject(param);
        String keyword = json.getString("keyword");

        List<Device> devices = null;
        if (keyword != null) {
            keyword = "%" + keyword + "%";
            List<Device> devicesTmp;
            List<Integer> idList;

            //获取匹配keyword字段的信息
            DeviceExample deviceExample = new DeviceExample();
            deviceExample.createCriteria().andWeixinidEqualTo(corporationid).andPhoneEqualTo("").andIbeaconidLike(keyword);
            devicesTmp = deviceMapper.selectByExample(deviceExample);
            devices = devicesTmp;
            idList = new ArrayList<>();
            idList.add(-1);
            if (devices.size() > 0) {
                for (int i = 0; i < devices.size(); i++) {
                    idList.add(devices.get(i).getId());
                }
            }

            //获取匹配descr字段的信息
            deviceExample = new DeviceExample();
            deviceExample.createCriteria().andWeixinidEqualTo(corporationid).andPhoneEqualTo("").andRemarkLike(keyword).andIdNotIn(idList);
            devicesTmp = deviceMapper.selectByExample(deviceExample);
            devices.addAll(devicesTmp);
            idList = new ArrayList<>();
            idList.add(-1);

            if (devicesTmp.size() > 0) {
                for (int i = 0; i < devices.size(); i++) {
                    idList.add(devices.get(i).getId());
                }
            }
            //获取匹配SerialNum字段的信息
            deviceExample = new DeviceExample();
            deviceExample.createCriteria().andWeixinidEqualTo(corporationid).andPhoneEqualTo("").andSerialNumLike(keyword).andIdNotIn(idList);
            devicesTmp = deviceMapper.selectByExample(deviceExample);

            if (devicesTmp.size() > 0) {
                devices.addAll(devicesTmp);
                idList = new ArrayList<>();
                idList.add(-1);
                for (int i = 0; i < devices.size(); i++) {
                    idList.add(devices.get(i).getId());
                }
            }

//            for (Device d : devices) {
//                System.out.println("device SerialNum :" + d.getId() + " " + d.getSerialNum() + " " +
//                        d.getPhone() + " " + d.getStatus() + " " + d.getIbeaconId());
//            }
        }

        json = new JSONObject();
        json.put("general", devices);
        return json;
    }


    @Override
    public DeviceStatistics storeDeviceStatistics(String storePhone) {
        DeviceExample deviceExample = new DeviceExample();
        deviceExample.createCriteria().andPhoneEqualTo(storePhone);
        int totalNum = deviceMapper.countByExample(deviceExample);
        deviceExample = new DeviceExample();
        deviceExample.createCriteria().andPhoneEqualTo(storePhone).andStatusEqualTo("未使用");
        int unUsingNum = deviceMapper.countByExample(deviceExample);
        int usingNum = totalNum - unUsingNum;

        GroupExample groupExample = new GroupExample();
        groupExample.createCriteria().andPhonenumEqualTo(storePhone);
        int groupNum = groupMapper.countByExample(groupExample);

        DeviceStatistics deviceStatistics = new DeviceStatistics(totalNum, usingNum, unUsingNum, groupNum);
        return deviceStatistics;
    }


    /**
     * @param param
     * @return
     */
    public JSONObject detail(String param) {
        JSONObject json = JSON.parseObject(param);

        Integer startPage = json.getInteger("startPage");
        Integer pageSize = json.getInteger("pageSize");

        DeviceExample deviceExample = new DeviceExample();
        deviceExample.createCriteria().andPhoneEqualTo(json.getString("phoneNum"));
        deviceExample.setDividePage(startPage, pageSize);
        List<Device> devices = deviceMapper.selectByExample(deviceExample);
        if (devices.isEmpty()) {
            json = new JSONObject();
            json.put("personalDevice", devices);
            return json;
        }
        JSONArray jsons = new JSONArray();
        for (Device device : devices) {
            json = new JSONObject();
            json.put("serialNum", device.getSerialNum());
            json.put("ibeaconId", device.getIbeaconId());
            json.put("weixinId", device.getWeixinId());
            json.put("uuid", device.getUuid());
            json.put("major", device.getMajor());
            json.put("minor", device.getMinor());
            json.put("type", device.getType());
            json.put("status", device.getStatus());
            json.put("createTime", device.getCreateTime());
            json.put("remark", device.getRemark());
            jsons.add(json);
        }
        int pages = DividePageUtil.getPages(devices.size(), pageSize);
        json = new JSONObject();
        json.put("personalDevice", jsons);
        json.put("pages", pages);
        return json;
    }


    @Override
    public String getExcel(String path, String urlweb) {
        List<User> users = userMapper.selectByExample(null);
        JSONArray arr = jsonUtil.device2json(users, deviceMapper, groupMapper, userInfoMapper);
        JSONArray arr2 = new JSONArray();
        String path2 = "";
        for (int i = 0; i < arr.size(); i++) {
            JSONObject one = arr.getJSONObject(i);
            String account = one.getString("account");
            String storeName = one.getString("storeName");
            DeviceExample deviceExample = new DeviceExample();
            deviceExample.createCriteria().andPhoneEqualTo(one.getString("account"));
            List<Device> devices = deviceMapper.selectByExample(deviceExample);
            for (Device device : devices) {
                JSONObject json2 = new JSONObject();
                json2.put("account", account);
                json2.put("storeName", storeName);
                json2.put("ibeaconId", device.getIbeaconId());
                json2.put("weixinId", device.getWeixinId());
                json2.put("uuid", device.getUuid());
                json2.put("major", device.getMajor());
                json2.put("minor", device.getMinor());
                json2.put("type", device.getType());
                json2.put("status", device.getStatus());
                json2.put("serialNum", device.getSerialNum());
                SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String time = sd.format(device.getCreateTime());
                json2.put("createTime", time);
                json2.put("remarkDevice", device.getRemark());
                arr2.add(json2);
            }
        }
        try {
            path2 = ExcelUtil.excelGet(arr2, path, urlweb);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return path2;
    }

    /**
     * 激活设备
     */
    @Override
    public String activate(String param) {
        JSONObject json;
        json = JSON.parseObject(param);
        String ibeaconId = json.getString("ibeaconId");
        int flag = deviceMapper.updateStatusById(ibeaconId);
        if (flag > 0) {
            json = new JSONObject();
            json.put("flag", true);
        } else {
            json.put("flag", false);
        }
        return json.toJSONString();
    }

    /**
     * 給已激活的设备绑定用户
     *
     * @return
     */
    @Override
    public String binding(String param) {
        JSONObject json;
        json = JSON.parseObject(param);

        JSONArray serialNums = json.getJSONArray("serialNums");//还需要一个序列号
        String accountPhone = json.getString("accountPhone");
        String remark = json.getString("remark");
        json = new JSONObject();
        try {
            UserExample userExample = new UserExample();
            userExample.createCriteria().andPhonenumEqualTo(accountPhone);
            List<User> users = userMapper.selectByExample(userExample);
            if (users != null && users.size() > 0) {
                User user = users.get(0);
                String storeName = user.getStorename();
                for (int i = 0; i < serialNums.size(); i++) {
                    DeviceExample deviceExample = new DeviceExample();
                    deviceExample.createCriteria().andSerialNumEqualTo(serialNums.getString(i));
                    Device device = deviceMapper.selectByExample(deviceExample).get(0);
                    device.setPhone(accountPhone);
                    device.setStorename(storeName);
                    device.setStatus("未使用");
                    device.setRemark(remark);
                    deviceMapper.updateByPrimaryKeySelective(device);
                }
                json.put("flag", true);
                return json.toString();
            }
        } catch (Exception e) {
            return json.toJSONString();
        }
        json.put("flag", false);
        return json.toJSONString();
    }

    @Override
    public String unbinding(String param, Integer id) {
        JSONObject json = JSON.parseObject(param);
        String serialNum = json.getString("serialNum");

        DeviceExample deviceExample = new DeviceExample();
        deviceExample.createCriteria().andSerialNumEqualTo(serialNum);
        List<Device> devices = deviceMapper.selectByExample(deviceExample);
        json = new JSONObject();
        if (devices != null && devices.size() > 0) {
            Device device = devices.get(0);
            device.setGroupId(0);
            device.setPageid(-1);
            device.setPhone("");
            device.setStorename("");
            device.setRemark("");
            if (id != null)
                device.setWeixinId(id);

            int flag = deviceMapper.updateByPrimaryKeySelective(device);
            if (flag > 0) {
                json.put("flag", true);
            } else {
                json.put("flag", false);
            }
        }
        return json.toJSONString();
    }


    @Override
    public String showUnbinding(HttpSession session, String param) {
        JSONObject json = JSON.parseObject(param);
        Integer corporationid = (Integer) session.getAttribute("userId");
        if (corporationid == null) {
            corporationid = json.getInteger("corporationid");
        }

        if (corporationid == null) {
            json = new JSONObject();
            json.put("errorcode", -1);
            json.put("errormsg", "账号不存在");
            return json.toString();
        }

        Integer startPage = json.getInteger("startPage");
        Integer pageSize = json.getInteger("pageSize");


        DeviceExample deviceExample = new DeviceExample();
        deviceExample.createCriteria().andPhoneEqualTo("").andWeixinidEqualTo(corporationid);

        int pages = (int) Math.ceil(deviceMapper.countByExample(deviceExample) / (double) pageSize);

        json = new JSONObject();
        json.put("pages", pages);

        deviceExample.setDividePage(startPage, pageSize);


        List<Device> devices = deviceMapper.selectByExample(deviceExample);
        json.put("generalResults", devices);
        return json.toJSONString();
    }

    @Override
    public String preAdd(int corporationid) {
        JSONObject json = new JSONObject();
        DeviceExample deviceExample = new DeviceExample();
        deviceExample.createCriteria().andWeixinidEqualTo(corporationid).andPhoneEqualTo("");
        List<Device> devices = deviceMapper.selectByExample(deviceExample);

        List<Device> devices1 = null;
        if (devices != null && devices.size() > 0) {
            devices1 = new ArrayList<>(devices.size());
            Device dev;
            for (Device device : devices) {
                dev = new Device(device.getId(), device.getSerialNum(), device.getWeixinId(), device.getDescr());
                devices1.add(dev);
            }
        }
        json.put("generalResults", devices1);

        return json.toJSONString();
    }

    @Override
    public String searchAccount(int corporationid, String param) {
        JSONObject json;
        json = JSON.parseObject(param);
        //账号名或者密码
        String value = "%" + json.getString("value") + "%";

        UserExample userExample1 = new UserExample();
        userExample1.createCriteria().andCorporationidEqualTo(corporationid).andPhonenumLike(value);
        List<User> users1 = userMapper.selectByExample(userExample1);
        List<User> users = users1;
        List<Integer> idList = new ArrayList<Integer>();
        idList.add(-1);
        if (users.size() > 0) {
            for (int i = 0; i < users.size(); i++) {
                idList.add(users.get(i).getId());
            }
        }

        UserExample userExample2 = new UserExample();
        userExample2.createCriteria().andCorporationidEqualTo(corporationid).andStorenameLike(value).andIdNotIn(idList);
        List<User> users2 = userMapper.selectByExample(userExample2);

        if (users2.size() > 0) {
            users.addAll(users2);
        }

        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            String phoneNum = user.getPhonenum();

            GroupExample groupExample = new GroupExample();
            groupExample.createCriteria().andPhonenumEqualTo(phoneNum);
            int groupNum = groupMapper.countByExample(groupExample);

            DeviceExample deviceExample = new DeviceExample();
            deviceExample.createCriteria().andPhoneEqualTo(phoneNum);
            int deviceNum = deviceMapper.countByExample(deviceExample);

            json = new JSONObject();
            json.put("account", phoneNum);
            json.put("storeName", user.getStorename());
            json.put("deviceNum", deviceNum);
            json.put("groupNum", groupNum);
            json.put("remark", userInfoMapper.selectByPrimaryKey(user.getUserinfoid()).getRemark());
            jsonArray.add(json);
        }
        json = new JSONObject();
        json.put("allUser", jsonArray);
        return json.toJSONString();
    }

    @Override
    public String reviseComment(String param) {
        JSONObject json = JSON.parseObject(param);
        String ibeaconId = json.getString("ibeaconId");

        DeviceExample deviceExample = new DeviceExample();
        deviceExample.createCriteria().andPhoneEqualTo(ibeaconId);

        Device device = deviceMapper.selectByExample(deviceExample).get(0);

        device.setDescr(json.getString("descr"));

        int flag = deviceMapper.updateByExample(device, deviceExample);

        if (flag > 0) {
            json = new JSONObject();
            json.put("flag", true);
        } else {
            json.put("flag", false);
        }
        return json.toJSONString();
    }

    @Override
    public List<DeviceBuyRecord> deviceBuyRecord(String utoken, Integer startPage, Integer pageSize, String tradeNo, Integer[] pages) {
        if (utoken == null || utoken.length() == 0) {
            return null;
        }
        DeviceBuyRecordExample deviceBuyRecordExample = new DeviceBuyRecordExample();
        deviceBuyRecordExample.setDividePage(startPage, pageSize);
        DeviceBuyRecordExample.Criteria criterial = deviceBuyRecordExample.createCriteria().andUtokenEqualTo(utoken);
        List<String> except = new ArrayList<String>();
        except.add("P");
        except.add("D");
        criterial.andStatusNotIn(except);
        deviceBuyRecordExample.setOrderByClause("id");

        if (tradeNo != null && tradeNo.length() > 0) {
            criterial.andPartner_trade_noLike(tradeNo);
            // criterial.andLike(new String[]{""},tradeNo);
        }

        pages[0] = DividePageUtil.getPages(deviceBuyRecordMapper.countByExample(deviceBuyRecordExample), pageSize);

        //return deviceBuyRecordMapper.selectByExample(deviceBuyRecordExample);
        List<DeviceBuyRecord> deviceBuyRecordList = deviceBuyRecordMapper.selectByExample(deviceBuyRecordExample);
//        for (int i = 0; i < deviceBuyRecordList.size(); i++) {
//            System.out.println(deviceBuyRecordList.get(i).getReason() + "---" + deviceBuyRecordList.get(i).getId()
//                    + "--" + deviceBuyRecordList.get(i).getStatus());
//        }
        return deviceBuyRecordList;
    }


    @Override
    public DeviceBuyRecord deviceOrder(String utoken,
                                       String deviceType,
                                       Integer amount, Integer originalPrice,
                                       Integer policyReduct,Integer unitPrice,
                                       Integer totalPrice, Integer ruleId,
                                       String address, String contact,
                                       String consignee,String status,
                                       String typeSec,Date time,String reason) {
        //先执行价格判断
        RuleMoneyDeviceBuy ruleMoneyDeviceBuy = ruleMoneyDeviceBuyMapper.selectByPrimaryKey(ruleId);

        if (ruleMoneyDeviceBuy == null) {
            return null;
        } else {
            if (!Objects.equals(unitPrice, ruleMoneyDeviceBuy.getPrice())) {
            //    return null;
            }
        }

        if (totalPrice != unitPrice * amount) {
            return null;
        }

        //执行订单保存，生成平台订单号
        String tradeNo = SerialNumCreater.serialNum(OrderNumCreater.class);
        String comment = "购买乐豆呀红包盒设备";

        DeviceBuyRecord deviceBuyRecord = new DeviceBuyRecord();
        deviceBuyRecord.setAmount(amount);
        deviceBuyRecord.setComment(comment);
        deviceBuyRecord.setConsignee(consignee);
        deviceBuyRecord.setContact_way(contact);
        deviceBuyRecord.setDelivery_address(address);
        deviceBuyRecord.setDevice_type(deviceType);
        deviceBuyRecord.setPartner_trade_no(tradeNo);
        deviceBuyRecord.setRule_id(ruleId);
        deviceBuyRecord.setUnit_price(unitPrice);
        deviceBuyRecord.setOriginal_price(originalPrice);
        deviceBuyRecord.setPolicy_reduct(policyReduct);
        deviceBuyRecord.setTotal_fee(totalPrice);
        deviceBuyRecord.setUtoken(utoken);
        deviceBuyRecord.setReason(reason);
        if (typeSec==null)
            typeSec= "WX";
        deviceBuyRecord.setType_sec(typeSec);
        //订单支付金额为0，直接支付成功
        if(totalPrice==0){
            deviceBuyRecord.setStatus("N");
        }

        if(status!=null){
            deviceBuyRecord.setStatus(status);
        }

        if (time!=null){
            deviceBuyRecord.setTime(time);
        }

        Admin admin = adminService.getAdminByProxyToken(utoken);
        ProxyArea proxyArea = proxyAreaService.proxyAreaByUtoken(utoken);
        if(admin!=null && proxyArea !=null){
            if(admin.getLevel()==0 && proxyArea.getCreatetime()==null){
                deviceBuyRecord.setShield(1);
            }
        }
        try {
            if (deviceBuyRecordMapper.insertSelective(deviceBuyRecord) > 0) {
                //设备订单生成成功
                return deviceBuyRecord;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }


    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public boolean deviceBuyResult(String out_trade_no, boolean result) {

        DeviceBuyRecordExample deviceBuyRecordExample = new DeviceBuyRecordExample();
        deviceBuyRecordExample.createCriteria().andPartner_trade_noLike(out_trade_no)
                .andStatusEqualTo("P");

        DeviceBuyRecord deviceBuyRecord = new DeviceBuyRecord();

        if (result) {
            //购买成功
            deviceBuyRecord.setStatus("N");
        } else {
            //购买失败
            deviceBuyRecord.setStatus("D");
        }

        int rows = deviceBuyRecordMapper.updateByExampleSelective(deviceBuyRecord, deviceBuyRecordExample);
        if (rows > 0)
            return true;
        return false;
    }

    @Override
    public List<model.dto.DeviceBuyRecord> deviceBuyRecord(Integer startPage, Integer pageSize,
                                                           String startTime, String endTime,
                                                           String searchKey, String tradeState, Integer[] pages) throws ParseException {

        if (startPage == null)
            startPage = 1;
        if (pageSize == null)
            pageSize = 10;
        Date stime = null, etime = null;
        if (startTime != null) {
            stime = new SimpleDateFormat("yyyy-MM-dd").parse(startTime);
        }

        if (endTime != null) {
            etime = new SimpleDateFormat("yyyy-MM-dd").parse(endTime);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(etime);
            calendar.set(Calendar.HOUR_OF_DAY, 23);
            calendar.set(Calendar.MINUTE, 59);
            calendar.set(Calendar.SECOND, 59);
            etime = calendar.getTime();
        }

        Integer startSize = (startPage - 1) * pageSize;

        try {
            pages[0] = DividePageUtil.getPages(deviceBuyRecordMapper.countDeviceRecord(tradeState, searchKey, stime, etime), pageSize);

            return deviceBuyRecordMapper.selectDeviceRecord(tradeState, searchKey, startSize, pageSize, stime, etime);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public DeviceBuyRecord getDeviceBuyRecord(String tradeNo, List<String> status) {
        DeviceBuyRecordExample deviceBuyRecordExample = new DeviceBuyRecordExample();
        DeviceBuyRecordExample.Criteria criteria = deviceBuyRecordExample.createCriteria().andPartner_trade_noEqualTo(tradeNo);
        if (status != null)
            criteria.andStatusIn(status);
        List<DeviceBuyRecord> deviceBuyRecords = deviceBuyRecordMapper.selectByExample(deviceBuyRecordExample);
        if (deviceBuyRecords != null && deviceBuyRecords.size() > 0) {
            return deviceBuyRecords.get(0);
        }
        return null;
    }


    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public boolean updateDeviceOrderLogisticState(String tradeNo, String status, String logistic_company, String logistic_no,String reason) {
        DeviceBuyRecord deviceBuyRecord = getDeviceBuyRecord(tradeNo, null);
        if (deviceBuyRecord == null)
            return false;

        deviceBuyRecord.setStatus(status);
        if (logistic_no != null) {
            deviceBuyRecord.setLogistic_no(logistic_no);
        }
        if(status.equals("D")) {
            deviceBuyRecord.setReason(deviceBuyRecord.getReason() + " 拒绝原因：" + reason);
        }
        if (deviceBuyRecordMapper.updateByPrimaryKeySelective(deviceBuyRecord) <= 0) {
            return false;
        }

        //插入订单
        if(!status.equals("D") && logistic_no!=null) {
            Logistics logistics = logisticsService.getNowLogisticByNo(logistic_no);
            if (logistics != null) {
                logistics.setLogistic_no(logistic_no);
                logistics.setStatus(status);
                logistics.setLogistic_company(logistic_company);
                if (logisticsService.updateLogisticInfo(logistics)) {
                    return true;
                }
            } else {
                //第一次新建订单
                logistics = new Logistics();
                logistics.setLogistic_no(logistic_no);
                logistics.setLogistic_company(logistic_company);
                logistics.setStatus(status);
                if (logisticsService.insertLogistic(logistics))
                    return true;
            }
        }

        return false;
    }

    /**
     * 整体计算规则：
     * 从广告平台获取本月之前的设备统计，包括激活码、普通设备的激活数量和累计采购数量
     * 从本平台获取本月采购统计
     * 进行叠加处理
     *
     * @param utoken
     * @return
     */
    @Override
    public DeviceBuyStatistics deviceBuyStatics(String utoken) {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.DAY_OF_MONTH, 1);

            long stime = calendar.getTimeInMillis();

            Date sDate = new Date(stime);

            //我的本月设备采购量
    //        int myDeviceNumthisMonth = dealerService.myDevicesNum(utoken, null, sDate, null);

            // int myDeviceNumAll = dealerService.myDevicesNum(utoken, null, null, null);

            //本月激活码设备总购买数
    //        int myActiveDeviceNumAll = dealerService.myDevicesNum(utoken, "S", sDate, null);

            //本月普通设备总购买数
            //     int myNormalDeviceNumAll = myDeviceNumthisMonth - myActiveDeviceNumAll;

            DeviceBuyStatistics deviceBuyStatistics = new DeviceBuyStatistics();
            //deviceBuyStatistics.setDeviceAmountThisMonth(myDeviceNumthisMonth);


            BaseResult baseResult = AdControlClient.deviceStatistic(null, utoken);
            if (BaseResult.RESULT_SUCCESS == baseResult.getErrcode()) {
                String str = baseResult.getObject().toString();
                JSONObject jsonObject = JSONObject.parseObject(str);
                Integer enableNormaDevices = jsonObject.getInteger("enableNormaDevices");
                Integer enableActiveDevices = jsonObject.getInteger("enableActiveDevices");
                Integer enableDevicesThisMonth = jsonObject.getInteger("enableDevicesThisMonth");
                Integer totalNormalDevices = jsonObject.getInteger("totalNormalDevices");
                Integer totalActiveDevices = jsonObject.getInteger("totalActiveDevices");
                Integer monthDeviceBuy = jsonObject.getInteger("monthDeviceBuy");

                deviceBuyStatistics.setDeviceAmountThisMonth(monthDeviceBuy);
                deviceBuyStatistics.setDeviceAmountAll(totalNormalDevices + totalActiveDevices);

                deviceBuyStatistics.setActiveDevicesThisMonth(enableDevicesThisMonth);
                deviceBuyStatistics.setActiveDevicesAll(enableNormaDevices + enableActiveDevices);

                deviceBuyStatistics.setActiveDeviceLeft(totalActiveDevices - enableActiveDevices);
                deviceBuyStatistics.setNormalDeviceLeft(totalNormalDevices - enableNormaDevices);

                return deviceBuyStatistics;
            } else {
                //失败
                LogUtil.log(this.getClass(), LogUtil.LogType.ERROR, "代理商本人设备指标出现问题：" + utoken);

            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return new DeviceBuyStatistics(0, 0, 0, 0, 0, 0);
    }

}
