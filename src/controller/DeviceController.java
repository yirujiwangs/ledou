package controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import model.Admin;
import model.DeviceStatistics;
import model.base.BaseResult;
import model.dto.DeviceBuyStatistics;
import model.dto.DeviceUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.AdminService;
import service.DevicePriceService;
import service.DeviceService;
import service.UserService;
import utils.api.AdBaseAPI;
import utils.api.BaseAPI;
import utils.api.BeaconAPI;
import utils.common.Constant;
import utils.common.WechatConstansUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2015/12/3.
 */
@RestController
@RequestMapping(value = "/device")
public class DeviceController {

    @Resource
    private DeviceService deviceService;

    @Resource
    private UserService userService;

    @Autowired
    private DevicePriceService devicePriceService;

    @Autowired
    private AdminService adminService;
    /**
     * #1.1 获取设备管理指标
     *
     * @param session
     * @return
     */
    @RequestMapping("/deviceIndex.do")
    public BaseResult deviceIndex(HttpSession session) {
        Admin admin = (Admin) session.getAttribute("proxy");

        BaseResult baseResult = new BaseResult(-1, "设备指标获取失败");


        if (admin != null) {

            String proxyToken = admin.getProxy_token();
            if (proxyToken != null) {


                DeviceBuyStatistics deviceBuyStatistics = deviceService.deviceBuyStatics(proxyToken);

                if (deviceBuyStatistics != null) {
                    baseResult.setErrcode(BaseResult.RESULT_SUCCESS);
                    baseResult.setErrmsg("设备指标获取成功");
                    baseResult.setObject(deviceBuyStatistics);
                }
            }
        }
        return baseResult;
    }


    /*
    * @descr: #1.2 商户设备信息列表(devicesList.do)
     */
    @RequestMapping(value = "devicesList.do")
    public
    @ResponseBody
    String devicesList(HttpSession session, @RequestBody String param) throws UnsupportedEncodingException {
        Admin admin = (Admin) session.getAttribute(Constant.SESSION_KEY_PROXY);

        BaseResult baseResult = new BaseResult(-1, "商户设备信息列表获取失败");
        String pathUrl = "device/deviceList";
        try {
            if (admin != null) {
                String proxyToken = admin.getProxy_token();
                String ptoken = admin.getPtoken();
                Integer proxyId = admin.getId();

                if (proxyToken != null) {
                    JSONObject jsonObject = JSON.parseObject(param);

                    Integer startPage = jsonObject.getInteger("startPage");
                    Integer pageSize = jsonObject.getInteger("pageSize");

                    jsonObject.clear();
                    Integer pages = 1;
                    List<DeviceUserInfo> utokens = new ArrayList<>();
                    List<DeviceUserInfo> userInfoList = userService.getUserList(proxyId, startPage, pageSize, pages, utokens);

                    if (userInfoList.size() <= 0) {
                        jsonObject.put("pages", 1);
                        baseResult = new BaseResult(1, "暂无数据", jsonObject);
                        return JSON.toJSONString(baseResult);
                    }

                    jsonObject.put("proxyToken", proxyToken);
                    jsonObject.put("storeInfos", userInfoList);

                    String res = AdBaseAPI.executeResult(ptoken, jsonObject.toJSONString(), pathUrl);

                    if (res != null && !res.isEmpty()) {

                        jsonObject.put("pages", pages);
                        jsonObject.put("storeInfo", res);
                        return jsonObject.toString();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSON.toJSONString(baseResult);
    }


    /*
    * @descr: #1.3 设备搜索(deviceSearch.do)
    */
    @RequestMapping(value = "deviceSearch.do")
    public
    @ResponseBody
    String deviceSearch(HttpSession session, @RequestBody String param) {
        Admin admin = (Admin) session.getAttribute("proxy");

        BaseResult baseResult = new BaseResult(-1, "商户设备搜索失败");
        String pathUrl = "device/deviceSearch";

        if (admin != null) {

            String proxyToken = admin   .getProxy_token();
            String ptoken = admin.getPtoken();
            Integer proxyId = admin.getId();

            if (proxyToken != null) {

                JSONObject jsonObject = JSON.parseObject(param);

                Integer startPage = jsonObject.getInteger("startPage");
                Integer pageSize = jsonObject.getInteger("pageSize");
                String keyword = jsonObject.getString("keyword");
                int[] pages = new int[1];
                List<DeviceUserInfo> userInfoList = userService.searchUserList(proxyId, keyword, startPage, pageSize, pages);

                jsonObject.put("proxyToken", proxyToken);
                jsonObject.put("storeInfos", userInfoList);

                String res = AdBaseAPI.executeResult(ptoken, jsonObject.toJSONString(), pathUrl);

                if (res != null && !res.isEmpty()) {
                    jsonObject.clear();
                    jsonObject.put("pages", pages[0]);
                    jsonObject.put("storeInfo", res);

                    baseResult = new BaseResult(1, "商户设备搜索成功", jsonObject);
                }
            }
        }
        return JSON.toJSONString(baseResult);
    }

//********************************************************************************************//


    /*
    * @descr: #2.1 账号数据指标(accountDeviceIndex.do)
    */
    @RequestMapping(value = "accountDeviceIndex.do")
    public
    @ResponseBody
    String accountDeviceIndex(HttpSession session, @RequestBody String param) {
        Admin admin = (Admin) session.getAttribute("proxy");

        BaseResult baseResult = new BaseResult(-1, "账号数据指标失败");
        String pathUrl = "device/accountDeviceIndex";

        if (admin != null) {

            String proxyToken = admin.getProxy_token();
            String ptoken = admin.getPtoken();
            if (proxyToken != null) {
                JSONObject jsonObject = JSON.parseObject(param);
                String storeAccount = jsonObject.getString("storeAccount");
                DeviceUserInfo userInfo = userService.getUserByAccount(storeAccount);

                jsonObject.put("proxyToken", proxyToken);
                jsonObject.put("uToken", userInfo.getuToken());

                String res = AdBaseAPI.executeResult(ptoken, jsonObject.toJSONString(), pathUrl);

                if (res != null) {
                    if (!res.isEmpty()) {
                        baseResult = new BaseResult(1, "账号数据指标成功", res);
                    }
                }
            }
        }
        return JSON.toJSONString(baseResult);
    }


    /*
    * @descr: #2.2 账号设备信息列表（accountDeviceList.do）
    */
    @RequestMapping(value = "accountDeviceList.do")
    public
    @ResponseBody
    String accountDeviceList(HttpSession session, @RequestBody String param) {
        Admin admin = (Admin) session.getAttribute("proxy");

        BaseResult baseResult = new BaseResult(-1, "账号设备信息列表获取失败");
        String pathUrl = "device/accountDeviceList";

        if (admin != null) {

            String proxyToken = admin.getProxy_token();
            String ptoken = admin.getPtoken();
            if (proxyToken != null) {

                JSONObject jsonObject = JSON.parseObject(param);

                String storeAccount = jsonObject.getString("storeAccount");

                DeviceUserInfo userInfo = userService.getUserByAccount(storeAccount);

                jsonObject.put("proxyToken", proxyToken);
                jsonObject.put("storeInfo", userInfo);

                //System.out.println(jsonObject.toJSONString());

                String res = AdBaseAPI.executeResult(ptoken, jsonObject.toJSONString(), pathUrl);

                if (res != null && !res.isEmpty()) {
                    baseResult = new BaseResult(1, "账号设备信息列表获取成功", res);
                }
            }
        }
        return JSON.toJSONString(baseResult);
    }


    /*
    * @descr: #2.3 账号设备状态选择(accountDeviceStatus.do)
    */
    @RequestMapping(value = "accountDeviceStatus.do")
    public
    @ResponseBody
    String accountDeviceStatus(HttpSession session, @RequestBody String param) {
        Admin admin = (Admin) session.getAttribute("proxy");

        BaseResult baseResult = new BaseResult(-1, "账号设备状态选择失败");
        String pathUrl = "device/accountDeviceStatus";

        if (admin != null) {
            String proxyToken = admin.getProxy_token();
            String ptoken = admin.getPtoken();
            if (proxyToken != null) {
                JSONObject jsonObject = JSON.parseObject(param);
                String storeAccount = jsonObject.getString("storeAccount");
                DeviceUserInfo userInfo = userService.getUserByAccount(storeAccount);
                jsonObject.put("proxyToken", proxyToken);
                jsonObject.put("storeInfo", userInfo);
                String res = AdBaseAPI.executeResult(ptoken, jsonObject.toJSONString(), pathUrl);
                if (res != null && !res.isEmpty()) {
                    baseResult = new BaseResult(1, "账号设备状态选择成功", res);
                }
            }
        }
        return JSON.toJSONString(baseResult);
    }


    /*
    * @descr: #2.4 账号设备多级搜索(accountDeviceSearch.do)
    */
    @RequestMapping(value = "accountDeviceSearch.do")
    public
    @ResponseBody
    String accountDeviceSearch(HttpSession session, @RequestBody String param) {
        Admin admin = (Admin) session.getAttribute("proxy");

        BaseResult baseResult = new BaseResult(-1, "账号设备多级搜索失败");
        String pathUrl = "device/accountDeviceSearch";

        if (admin != null) {
            String proxyToken = admin.getProxy_token();
            String ptoken = admin.getPtoken();

            if (proxyToken != null) {
                JSONObject jsonObject = JSON.parseObject(param);
                String storeAccount = jsonObject.getString("storeAccount");
                DeviceUserInfo userInfo = userService.getUserByAccount(storeAccount);
                jsonObject.put("proxyToken", proxyToken);
                jsonObject.put("storeInfo", userInfo);
                String res = AdBaseAPI.executeResult(ptoken, jsonObject.toJSONString(), pathUrl);

                if (res != null && !res.isEmpty()) {
                    baseResult = new BaseResult(1, "账号设备多级搜索成功", res);
                }
            }
        }
        return JSON.toJSONString(baseResult);
    }


    /*
    * @descr: # 3.1 代理商设备分组列表
     */
    @RequestMapping(value = "proxyDeviceGroups.do")
    public
    @ResponseBody
    String getDeviceGroups(HttpSession session) {
            Admin admin = (Admin) session.getAttribute("proxy");
            BaseResult baseResult = new BaseResult(-1, "设备指标获取失败");
            String pathUrl = "device/proxyDeviceGroups";
            if (admin != null) {
                String proxyToken = admin.getProxy_token();
                String ptoken = admin.getPtoken();
                if (proxyToken != null) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("proxyToken", proxyToken);
                    String res = AdBaseAPI.executeResult(ptoken, jsonObject.toJSONString(), pathUrl);
                    if (res != null) {
                        if (!res.isEmpty()) {
                            baseResult = new BaseResult(1, "设备指标获取成功", JSON.parseObject(res));
                        }
                    }
                }
            }
            return JSON.toJSONString(baseResult);
    }


    /*
     * @desc:3.2 代理商设备信息列表（proxyDeviceInfoList.do）
     *
     */
    @RequestMapping(value = "proxyDeviceInfoList.do")
    public
    @ResponseBody
    String proxyDeviceInfoList(HttpSession session, @RequestBody String param) {
        Admin admin = (Admin) session.getAttribute(Constant.SESSION_KEY_PROXY);

        String pathUrl = "device/getOperator";

        BaseResult baseResult = new BaseResult(-1, "代理商设备信息获取失败");

        if (admin != null) {
            String proxyToken = admin.getProxy_token();
            String ptoken = admin.getPtoken();
            if (proxyToken != null) {
                JSONObject jsonObject = JSON.parseObject(param);
                jsonObject.put("proxyToken", proxyToken);
                String res = BeaconAPI.getProxyDeviceInfos(AdBaseAPI.executeResult(ptoken,
                        jsonObject.toJSONString(), pathUrl));
                if (res != null && !res.isEmpty()) {
                    baseResult = new BaseResult(1, "代理商设备信息获取成功", res);
                }
            }
        }
        //System.out.println(JSON.toJSONString(baseResult));
        return JSON.toJSONString(baseResult);
    }

    /**
     * 3.3 账号设备状态选择(proxyDeviceStatus.do)
     *
     * @param session
     * @param param
     * @return
     */
    @RequestMapping(value = "proxyDeviceStatus.do")
    public
    @ResponseBody
    String proxyDeviceStatus(HttpSession session, @RequestBody String param) {
        Admin admin = (Admin) session.getAttribute(Constant.SESSION_KEY_PROXY);

        BaseResult baseResult = new BaseResult(-1, "设备列表获取失败");
        String pathUrl = "device/proxyDeviceStatus";

        if (admin != null) {
            String proxyToken = admin.getProxy_token();
            String ptoken = admin.getPtoken();
            if (proxyToken != null) {
                JSONObject jsonObject = JSON.parseObject(param);
                jsonObject.put("proxyToken", proxyToken);
                String res = BeaconAPI.getProxyDeviceInfos(AdBaseAPI.executeResult(ptoken, jsonObject.toJSONString(), pathUrl));

                if (!res.isEmpty()) {
                    baseResult = new BaseResult(1, "设备获取成功", res);
                }
            }
        }

        return JSON.toJSONString(baseResult);
    }

    /**
     * 3.4 账号设备多级搜索
     *
     * @param session
     * @param param
     * @return
     */
    @RequestMapping(value = "proxyDeviceSearch.do")
    public
    @ResponseBody
    String proxyDeviceSearch(HttpSession session, @RequestBody String param) {
        Admin admin = (Admin) session.getAttribute("proxy");

        BaseResult baseResult = new BaseResult(-1, "设备多级搜索失败");
        String pathUrl = "device/proxyDeviceSearch";

        if (admin != null) {
            String proxyToken = admin.getProxy_token();
            String ptoken = admin.getPtoken();
            if (proxyToken != null) {
                JSONObject jsonObject = JSON.parseObject(param);
                jsonObject.put("proxyToken", proxyToken);
                String res = BeaconAPI.getProxyDeviceInfos(AdBaseAPI.executeResult(ptoken, jsonObject.toJSONString(), pathUrl));

                if (res != null && !res.isEmpty()) {
                    baseResult = new BaseResult(1, "设备多级搜索成功", res);
                }
            }
        }
        return JSON.toJSONString(baseResult);
    }


    /**
     * 3.5 创建设备分组
     *
     * @param session
     * @param param
     * @return
     */
    @RequestMapping(value = "proxyCreateGroup.do")
    public
    @ResponseBody
    String proxyCreateGroups(HttpSession session, @RequestBody String param) {
        Admin admin = (Admin) session.getAttribute("proxy");
        String pathurl = "device/proxyCreateGroup";
        BaseResult baseResult = new BaseResult(-1, "创建分组失败");
        String res;
        if (admin != null) {

            String proxyToken = admin.getProxy_token();
            String ptoken = admin.getPtoken();
            if (proxyToken != null) {
                JSONObject jsonObject = JSON.parseObject(param);
                jsonObject.put("ptoken", WechatConstansUtil.PTOKEN_LEDOUYA);
                jsonObject.put("proxyToken", proxyToken);
                jsonObject.put("proxyName", admin.getUsername());
                res = AdBaseAPI.executeResult(ptoken, jsonObject.toJSONString(), pathurl);

                if (res != null && !res.isEmpty()) {
                    JSONObject jsonObject1 = JSONObject.parseObject(res);
                    if (jsonObject1.containsKey("error")) {
                        baseResult = new BaseResult(-1, jsonObject1.getString("error"));
                    } else {
                        baseResult = new BaseResult(1, "创建分组成功", res);
                    }
                }
            }
        }

        return JSON.toJSONString(baseResult);
    }


    /*
    * @descr:3.6 删除设备分组
     */
    @RequestMapping(value = "proxyDeleteGroup.do")
    public
    @ResponseBody
    String proxyDeleteGroup(HttpSession session, @RequestBody String param) {
        Admin admin = (Admin) session.getAttribute("proxy");
        String pathurl = "device/proxyDeleteGroup";
        BaseResult baseResult = new BaseResult(-1, "删除设备分组失败");
        String res;
        if (admin != null) {

            String proxyToken = admin.getProxy_token();
            String ptoken = admin.getPtoken();
            if (proxyToken != null) {
                JSONObject jsonObject = JSON.parseObject(param);
                jsonObject.put("proxyToken", proxyToken);
                res = AdBaseAPI.executeResult(ptoken, jsonObject.toJSONString(), pathurl);
                if (!res.isEmpty()) {
                    baseResult = new BaseResult(1, "删除设备分组成功", res);
                }
            }
        }

        return JSON.toJSONString(baseResult);
    }


    /**
     * 3.7 设备选择分组(deviceChooseGroup.do)
     *
     * @param session
     * @param param
     * @return
     */
    @RequestMapping(value = "deviceChooseGroup.do")
    public
    @ResponseBody
    String deviceChooseGroup(HttpSession session, @RequestBody String param) {
        Admin admin = (Admin) session.getAttribute("proxy");
        String pathUrl = "device/deviceChooseGroup";
        BaseResult baseResult = new BaseResult(-1, "设备选择分组失败");
        String res;

        if (admin != null) {
            String proxyToken = admin.getProxy_token();
            String ptoken = admin.getPtoken();
            if (proxyToken != null) {
                JSONObject jsonObject = JSON.parseObject(param);
                jsonObject.put("proxyToken", proxyToken);
                res = AdBaseAPI.executeResult(ptoken, jsonObject.toJSONString(), pathUrl);
                if (res != null && !res.isEmpty()) {
                    baseResult = new BaseResult(BaseResult.RESULT_SUCCESS, "设备选择分组成功", res);
                }
            }
        }
        return JSON.toJSONString(baseResult);
    }


    /*
    * @descr : 3.8 设备修改备注(deviceRevRemark.do)
     */
    @RequestMapping(value = "deviceRevRemark.do")
    public
    @ResponseBody
    String deviceRevRemark(HttpSession session, @RequestBody String param) {
        Admin admin = (Admin) session.getAttribute("proxy");
        String pathurl = "device/deviceRevRemark";
        BaseResult baseResult = new BaseResult(-1, "设备修改备注失败");
        String res;

        if (admin != null) {

            String proxyToken = admin.getProxy_token();
            String ptoken = admin.getPtoken();
            if (proxyToken != null) {
                JSONObject jsonObject = JSON.parseObject(param);
                jsonObject.put("proxyToken", proxyToken);
                res = AdBaseAPI.executeResult(ptoken, jsonObject.toJSONString(), pathurl);
                if (res != null && !res.isEmpty()) {
                    baseResult = new BaseResult(1, "设备修改备注成功", res);
                }
            }
        }

        return JSON.toJSONString(baseResult);
    }

//************************************************************************************************//

    /*
     * 导出excel表
     */
    @RequestMapping(value = "excel.do")
    @ResponseBody
    public String getExcel(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/x-msdownload");
        response.setCharacterEncoding("utf-8");
        String urlweb = "http://" + request.getServerName() + ":" + request.getServerPort() + "/admin";
        String path = request.getServletContext().getRealPath("/");
        String url = deviceService.getExcel(path, urlweb);
        JSONObject json = new JSONObject();
        json.put("url", url);
        return json.toJSONString();
    }


    /**
     * 添加设备
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "add.do", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String add(HttpSession session, @RequestBody String param) {
        String username = session.getAttribute("userTag").toString();
        JSONObject jsonObject = deviceService.add(param, username);

        return jsonObject.toJSONString();
    }

    @RequestMapping(value = "update.do", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String update(HttpSession session, @RequestBody String param) {

        String username = session.getAttribute("userTag").toString();
        return deviceService.update(param, username).toJSONString();

    }

    /**
     * by id or type+time
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "search.do", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String search(@RequestBody String param) {
        return deviceService.search(param).toJSONString();
    }


    @RequestMapping(value = "delete.do", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String delete(HttpSession session, @RequestBody String param) {

        String username = session.getAttribute("userTag").toString();

        return deviceService.delete(param, username).toJSONString();
    }

    /**
     * @param param {"phoneNum":"111"}
     * @return {"",""}
     */
    @RequestMapping(value = "detail.do", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String detail(@RequestBody String param) {

        return deviceService.detail(param).toJSONString();
    }

    @RequestMapping(value = "updateRemark.do", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String updateRemark(@RequestBody String param) {
        JSONObject json = new JSONObject();
        json.put("remark", "");

        return json.toJSONString();
    }

    /**
     * 激活设备
     *
     * @return
     */
    @RequestMapping(value = "activate.do")
    @ResponseBody
    public String activate(@RequestBody String param) {
        return deviceService.activate(param);
    }

    /**
     * 绑定设备
     */
    @RequestMapping(value = "binding.do", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String binding(@RequestBody String param) {
        return deviceService.binding(param);
    }

    /**
     * 获取商户设备总览
     *
     * @return
     * @author yeran
     * @time 2016年8月17日17:22:45
     */
    @RequestMapping(value = "storeDS.do")
    public
    @ResponseBody
    String storeDeviceStatistics(@RequestBody String param) {
        JSONObject jsonObject = JSON.parseObject(param);
        String phoneNum = jsonObject.getString("phoneNum");

        jsonObject = new JSONObject();
        DeviceStatistics deviceStatistics = deviceService.storeDeviceStatistics(phoneNum);
        if (deviceStatistics != null) {
            jsonObject.put("errorcode", 1);
            jsonObject.put("deviceStatistics", deviceStatistics);
            return jsonObject.toString();
        }
        jsonObject.put("errorcode", -1);
        return jsonObject.toString();
    }

    /**
     * @param param
     * @return
     */
    @RequestMapping(value = "unbinding.do", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String unbinding(HttpSession session, @RequestBody String param) {
        Admin admin = (Admin) session.getAttribute(Constant.SESSION_KEY_PROXY);
        Integer id = admin.getId();
        return deviceService.unbinding(param, id);
    }


    @RequestMapping(value = "showunbind.do")
    @ResponseBody
    public String showUnbinding(HttpSession session, @RequestBody String param) {
        return deviceService.showUnbinding(session, param);
    }

    @RequestMapping(value = "preAdd.do")
    @ResponseBody
    public String preAdd(HttpSession session) {
        int corporationid = (int) session.getAttribute("userId");
        return deviceService.preAdd(corporationid);
    }

    /**
     * 搜索商家
     */
    @RequestMapping(value = "searchAccount.do", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String searchAccount(HttpSession session, @RequestBody String param) {
        int corporationid = (int) session.getAttribute("userId");
        return deviceService.searchAccount(corporationid, param);
    }


    /**
     * @param session
     * @param param
     * @return
     * @descr 搜索运营商未绑定设备
     */
    @RequestMapping(value = "searchUnbind.do", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String searchUnbind(HttpSession session, @RequestBody String param) {
        Integer corporationid = (Integer) session.getAttribute("userId");
        return deviceService.searchUnbind(corporationid, param).toJSONString();
    }

    /**
     * 针对未分配的设备添加备注
     *
     * @author miaolu
     */

    @RequestMapping(value = "revcomment.do", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String reviseComment(@RequestBody String param) {

        return deviceService.reviseComment(param);
    }


    /**
     *
     * 设置设备的返利包括激活返利（最大100）、激活码红包返利（最大20%）
     *
     * @param params
     * @return
     *
     */
    @RequestMapping("/deviceRebateSetting")
    public BaseResult deviceRebateSetting(HttpSession session,@RequestBody String params){
        Admin admin = (Admin) session.getAttribute(Constant.SESSION_KEY_PROXY);
        JSONObject jsonObject = JSONObject.parseObject(params);
        String proxyToken = admin.getProxy_token();
        jsonObject.put("proxyToken",proxyToken);

        BaseResult baseResult = AdBaseAPI.execute(jsonObject, BaseAPI.DEVICE_ACTIVATE_SETTING);

        return baseResult;
    }


    /**
     *
     * 根据设备序列号，获取设备分成设置信息
     *
     * @param session
     * @param aliveCode
     * @return
     *
     */
    @RequestMapping("/devicesRebateInfo")
    public BaseResult devicesRebateInfo(HttpSession session,@RequestParam(value = "code",required = true) String aliveCode){
        Admin admin = (Admin) session.getAttribute(Constant.SESSION_KEY_PROXY);
        String proxyToken = admin.getProxy_token();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("proxyToken",proxyToken);
        jsonObject.put("aliveCode",aliveCode);

        return AdBaseAPI.execute(jsonObject, BaseAPI.DEVICE_ACTIVATE_INFO);
    }

    /**
     *
     * 设备采购参数（单价、减免）
     *
     * @param session
     * @return
     *
     */
    @RequestMapping(value = "devicePrice.do", produces = "application/json;charset=UTF-8")
    public
    @ResponseBody
    String devicePrice(HttpSession session, @RequestBody String param) {
        Admin admin = (Admin) session.getAttribute("proxy");
        if (admin != null) {
            String proxyToken = admin.getProxy_token();

            if (proxyToken != null) {
                JSONObject jsonObject = JSON.parseObject(param);
                String proxyType = jsonObject.getString("proxyType");
                jsonObject.clear();
                int specialPolicyReduct = devicePriceService.specialPolicyReduct(proxyToken);
                if(specialPolicyReduct == -1){
                    if(admin.getPolicy()==1){
                        if(proxyType.equals("P")){
                            jsonObject.put("policyReduct",devicePriceService.policyReduct("old_dist_policy_reduct"));
                        }else if(proxyType.equals("M")){
                            jsonObject.put("policyReduct",devicePriceService.policyReduct("old_city_policy_reduct"));
                        }
                        jsonObject.put("buyPrice",devicePriceService.buyPrice("old_buy_price"));
                    }else{
                        if(proxyType.equals("P")){
                            jsonObject.put("policyReduct",devicePriceService.policyReduct("dist_policy_reduct"));
                        }else if(proxyType.equals("M")){
                            jsonObject.put("policyReduct",devicePriceService.policyReduct("city_policy_reduct"));
                        }
                        jsonObject.put("buyPrice",devicePriceService.buyPrice("buy_price"));
                    }
                }else{
                    if(admin.getPolicy()==1){
                        jsonObject.put("buyPrice",devicePriceService.buyPrice("old_buy_price"));
                    }else{
                        jsonObject.put("buyPrice",devicePriceService.buyPrice("buy_price"));
                    }
                    jsonObject.put("policyReduct",specialPolicyReduct);
                }


                return JSON.toJSONString(new BaseResult(1,"设备采购数据获取成功",jsonObject));
            }
        }
        return JSON.toJSONString(new BaseResult(-1, "设备采购数据获取失败"));
    }

    /**
     * 设备政策设置
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "devicePolicy.do", produces = "application/json;charset=UTF-8")
    public
    @ResponseBody
    String devicePolicy(@RequestBody String param) {
        JSONObject jsonObject = JSON.parseObject(param);
        String account = jsonObject.getString("account");
        Admin admin = adminService.getAdminByPhone(account);
        if(admin == null) return JSON.toJSONString(new BaseResult(-1, "不存在此代理商"));
        jsonObject.put("proxyToken",admin.getProxy_token());
        String pathurl = "device/devicePolicy";
        String res = AdBaseAPI.executeResult(null, jsonObject.toJSONString(), pathurl);
        if (res != null && !res.isEmpty()){
            JSONObject result = JSON.parseObject(res);
            return JSON.toJSONString(new BaseResult(result.getInteger("error"),result.getString("error_reason")));
        }
        return JSON.toJSONString(new BaseResult(-1, "设备政策修改失败"));
    }
}
