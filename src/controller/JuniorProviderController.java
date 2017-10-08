package controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import dao.DeviceBuyRecordMapper;
import model.Admin;
import model.base.BaseResult;
import model.base.ProxyArea;
import model.dto.AdminCity;
import model.dto.FranchiseFee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.AdminService;
import service.DealerService;
import service.ProxyAreaService;
import utils.api.AdBaseAPI;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * Created by xwl on 2017/7/19.
 * 代理分级（市、区）
 */

@Controller
@RequestMapping("/juniorProvider")
public class JuniorProviderController {



    @Autowired
    private ProxyAreaService proxyAreaService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private DealerService dealerService;



    /**
     * 扩展服务商关键指标
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "proxyKPI.do", produces = "application/json;charset=UTF-8")
    public
    @ResponseBody
    String proxyKPI(HttpSession session) {
        Admin admin = (Admin) session.getAttribute("proxy");

        String pathurl = "device/getActiveDevice";

        if (admin != null) {
            String proxyToken = admin.getProxy_token();
            String ptoken = admin.getPtoken();

            if (proxyToken != null) {
                JSONObject jsonObject = new JSONObject();
                List<ProxyArea> proxyAreaList = proxyAreaService.distProxyAreaByUtoken(proxyToken);
                if(proxyAreaList!=null) {
                    String[] distProxyTokens = new String[proxyAreaList.size()];
                    for (int i = 0; i < distProxyTokens.length; i++) {
                        distProxyTokens[i] = proxyAreaList.get(i).getUtoken();
                    }
                    jsonObject.put("proxy_token", proxyAreaList);
                }else {
                    jsonObject.put("proxy_token", null);
                }
                String res = AdBaseAPI.executeResult(ptoken, jsonObject.toJSONString(), pathurl);
                JSONObject resObj = JSON.parseObject(res);
                String dataString = JSON.toJSONString(resObj.get("object"));

                //下级服务商总数
                int dealerNum = proxyAreaService.dealerCountByUtoken(proxyToken);

                //区县总数
                int distCountNum = proxyAreaService.distCountByUtoken(proxyToken);

                Calendar calendar = Calendar.getInstance();
                calendar.setTime(new Date());
                calendar.set(Calendar.HOUR_OF_DAY, 0);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.DAY_OF_MONTH, 1);

                long stime = calendar.getTimeInMillis();

                Date sDate = new Date(stime);

                //本月设备采购数
//                int monthDeviceBuy = dealerService.myDevicesNum(proxyToken,null,sDate,null);

                //累计设备采购数
                //int totalDeviceBuy = dealerService.myDevicesNum(proxyToken,null,null,null);


                JSONObject backJSONObject = JSON.parseObject(dataString);

                backJSONObject.put("dealerNum",dealerNum);
                backJSONObject.put("distCountNum",distCountNum);
                //backJSONObject.put("monthDeviceBuy",monthDeviceBuy);
                //backJSONObject.put("totalDeviceBuy",totalDeviceBuy);

                if (res != null && !res.isEmpty()){
                    return JSON.toJSONString(new BaseResult(1,"扩展服务商关键指标成功",backJSONObject));
                }
            }
        }
        return JSON.toJSONString(new BaseResult(-1, "扩展服务商关键指标失败"));
    }

    /**
     * 扩展服务商数据列表
     *
     * @param session
     * @param param
     * @return
     */
    @RequestMapping(value = "proxyList.do", produces = "application/json;charset=UTF-8")
    public
    @ResponseBody
    String proxyList(HttpSession session, @RequestBody String param) {
        Admin admin = (Admin) session.getAttribute("proxy");

        String pathUrl = "device/getProxyDeviceList";

        if (admin != null) {
            String cityProxyToken = admin.getProxy_token();
            String ptoken = admin.getPtoken();
            if (cityProxyToken != null) {

                String data = proxyAreaService.distProxyAreaList(param, cityProxyToken);
                JSONObject jsonObject = JSON.parseObject(data);
                JSONArray proxyAreaList = jsonObject.getJSONArray("proxyAreaList");
                Integer pages = jsonObject.getInteger("pages");
                String[] distProxyTokens = new String[proxyAreaList.size()];
                for(int i=0;i<distProxyTokens.length;i++){
                    distProxyTokens[distProxyTokens.length-1-i] = (String)proxyAreaList.getJSONObject(i).get("utoken");
                }
                jsonObject.put("proxy_token", distProxyTokens);


                String res = AdBaseAPI.executeResult(ptoken, jsonObject.toJSONString(), pathUrl);

                JSONArray jsonArray = JSON.parseObject(res).getJSONArray("object");

                jsonObject.clear();
                jsonObject.put("pages",pages);

                for (int i = 0; i < jsonArray.size(); i++) {
                    JSONObject jsonTmp = jsonArray.getJSONObject(i);
                    String distProxyToken = jsonTmp.getString("proxy_token");

                    ProxyArea proxyArea = proxyAreaService.proxyAreaByUtoken(distProxyToken);
                    String area_name = proxyArea.getArea_name();
                    String area_rid = proxyArea.getArea_rid();
                    Date start_date = proxyArea.getStart_date();
                    String startDate = new SimpleDateFormat("yyyy-MM-dd").format(start_date);
                    Admin adminTmp = adminService.adminByUtoken(distProxyToken);
                    String username = adminTmp.getUsername();
                    String phoneNum = adminTmp.getPhoneNum();

                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(new Date());
                    calendar.set(Calendar.HOUR_OF_DAY, 0);
                    calendar.set(Calendar.MINUTE, 0);
                    calendar.set(Calendar.SECOND, 0);
                    calendar.set(Calendar.DAY_OF_MONTH, 1);

                    long stime = calendar.getTimeInMillis();

                    Date sDate = new Date(stime);

                    //本月设备采购数
                    //int monthDeviceBuy = dealerService.myDevicesNum(distProxyToken,null,sDate,null);

                    //累计设备采购数
                    //int totalDeviceBuy = dealerService.myDevicesNum(distProxyToken,null,null,null);

                    Map<String,Object> dataMap = new HashMap<String,Object>();
                    dataMap.put("area_name",area_name);
                    dataMap.put("area_rid",area_rid);
                    dataMap.put("start_date",startDate);
                    dataMap.put("username",username);
                    dataMap.put("phoneNum",phoneNum);
                    //dataMap.put("monthDeviceBuy",monthDeviceBuy);
                    //dataMap.put("totalDeviceBuy",totalDeviceBuy);
                    jsonTmp.putAll(dataMap);
                    jsonTmp.remove("proxy_token");
                }
                jsonObject.put("data",jsonArray);

                if (res != null && !res.isEmpty()) {
                    return JSON.toJSONString(new BaseResult(1,"扩展服务商数据列表获取成功",jsonObject));
                }
            }
        }
        return JSON.toJSONString(new BaseResult(-1, "扩展服务商数据列表获取失败"));
    }

    /**
     * 扩展服务商数据详情
     *
     * @param session
     * @param param
     * @return
     */
    @RequestMapping(value = "proxyDetailList.do", produces = "application/json;charset=UTF-8")
    public
    @ResponseBody
    String proxyDetailList(HttpSession session, @RequestBody String param) {
        Admin admin = (Admin) session.getAttribute("proxy");

        String pathUrl = "device/getProxyDevicesDetails";

        if (admin != null) {
            String proxyToken = admin.getProxy_token();
            String ptoken = admin.getPtoken();
            if (proxyToken != null) {
                JSONObject jsonObject = JSON.parseObject(param);
                String phoneNum = jsonObject.getString("phoneNum");
                String proxy_token = adminService.cutAdmin(phoneNum).getProxy_token();
                jsonObject.put("proxy_token", proxy_token);
                String res = AdBaseAPI.executeResult(ptoken, jsonObject.toJSONString(), pathUrl);
                JSONObject object = JSON.parseObject(res);

                if(object.getString("object")==null){
                    return JSON.toJSONString(new BaseResult(-1,"暂无数据详情"));
                }
                JSONArray jsonArray = object.getJSONObject("object").getJSONArray("data");
                Integer pages = object.getJSONObject("object").getInteger("pages");
/*                if(!jsonArray.isEmpty()) {
                    for (int i = 0; i<jsonArray.size();i++) {
                        String start = jsonArray.getJSONObject(i).getString("time");
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
                        Date stime = null;
                        try {
                            stime = format.parse(start);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(stime);
                        calendar.add(Calendar.MONTH, 1);
                        Date etime = calendar.getTime();
                        int monthDeviceBuy = dealerService.myDevicesNum(proxy_token,null,stime,etime);
                        jsonArray.getJSONObject(i).put("monthDeviceBuy",monthDeviceBuy);
                    }
                }*/
                object.clear();
                object.put("data",jsonArray);
                object.put("pages",pages);
                if (res != null && !res.isEmpty()) {
                    return JSON.toJSONString(new BaseResult(1,"扩展服务商数据详情获取成功",object));
                }
            }
        }
        return JSON.toJSONString(new BaseResult(-1,"扩展服务商数据详情获取失败"));
    }



}
