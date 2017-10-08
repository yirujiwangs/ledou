package controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import dao.AdvOrderRecordMapper;
import model.Admin;
import model.AdvOrderRecord;
import model.base.BaseResult;
import model.base.ProxyArea;
import model.dto.AdminCity;
import model.dto.WithdrawalInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import service.AdvService;
import service.ProxyAreaService;
import utils.InterfaceDataTest.InterfaceData;
import utils.api.AdBaseAPI;
import utils.api.AdBaseResult;
import utils.api.AdControlClient;
import utils.api.AdvPayClient;
import utils.common.AdvertiseUtil;
import utils.common.Constant;
import utils.common.LogUtil;
import utils.common.WechatConstansUtil;
import utils.finance.FinanceUtil;
import utils.net.RedirectUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;


/**
 * Created by Miaolu on 2016/10/3.
 */


@Controller
@RequestMapping(value = "/advertise")
public class AdvertiseController {

    @Autowired
    AdvOrderRecordMapper advOrderRecordMapper;

    @Autowired
    AdvService advService;

    @Autowired
    ProxyAreaService proxyAreaService;

    private final boolean InterfaceTest = InterfaceData.SELFTEST_ADVERTISE;

    public static HashMap<Integer, WithdrawalInfo> withdrawalInfoHashMap = new HashMap<>();

    /**
     * 获取广告列表
     * @param session
     * @param param
     * @return
     */
    @RequestMapping(value = "/advlist.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public
    @ResponseBody
    String advList(HttpSession session, @RequestBody String param) {
        Admin admin = (Admin) session.getAttribute(Constant.SESSION_KEY_PROXY);
        BaseResult baseResult = new BaseResult(-1, "广告列表获取失败");
        String pathUrl = "advertise/advlist";
        if (admin != null) {
            String proxyToken = admin.getProxy_token();
            String ptoken = admin.getPtoken();
            if (proxyToken != null) {
                LogUtil.log(AdvertiseController.class, LogUtil.LogType.SUCCESS, "查询广告列表", "proxyToken=" + proxyToken);

                JSONObject jsonObject = JSON.parseObject(param);
                jsonObject.put("proxyToken", proxyToken);
                jsonObject.put("ptoken", WechatConstansUtil.PTOKEN_LEDOUYA);
                String type = jsonObject.getString("type");
                if(type.equals("surprise")){
                    pathUrl = "advertise/leSurpriseRedList";
                }
                String res;
                if (InterfaceTest) {
                    res = InterfaceData.No_1_1_advList;
                } else {
                    res = AdBaseAPI.executeResult(ptoken, jsonObject.toJSONString(), pathUrl);
                }
                if (res == null) {
                    baseResult = new BaseResult(-1, "错误", null);
                    return JSON.toJSONString(baseResult);
                }

                if (res.equals("HttpResponseError") || res.equals("HttpRespenseEmpty")) {
                    baseResult = new BaseResult(-1, "错误", null);
                    return JSON.toJSONString(baseResult);
                }

                JSONArray jsonArray;

                try {
                    jsonArray = JSON.parseObject(res).getJSONArray("advlist");

                    if (jsonArray == null) {
                        baseResult = new BaseResult(-1, "Json为空或未知错误", res);
                        return JSON.toJSONString(baseResult);
                    }
                } catch (Exception e) {
                    //System.out.println("exception json: " + res);
                    baseResult = new BaseResult(-1, "Json为空或者格式有误", null);
                    return JSON.toJSONString(baseResult);
                }

                for (int i = 0; i < jsonArray.size(); i++) {
                    JSONObject jsonTmp = jsonArray.getJSONObject(i);
                    WithdrawalInfo withdrawalInfo = JSONObject.toJavaObject(jsonTmp.getJSONObject("withdrawal"), WithdrawalInfo.class);
                    withdrawalInfoHashMap.put(withdrawalInfo.getId(), withdrawalInfo);
                }
                if (!withdrawalInfoHashMap.isEmpty()) {
                    baseResult = new BaseResult(1, "广告列表获取成功", res);
                } else {
                    baseResult = new BaseResult(-1, "获取失败（计费模板格式有误）", null);
                }
            }
        }
        //System.out.println(JSON.toJSONString(baseResult));
        return JSON.toJSONString(baseResult);
    }


    /**
     * 1.2 广告投放策略详情(/advStrategyDetail.do)
     *
     * @param session
     * @param param
     * @return
     */
    @RequestMapping(value = "advStrategyDetail.do", produces = "application/json;charset=UTF-8")
    public
    @ResponseBody
    String advStrategyDetail(HttpSession session, @RequestBody String param) {
        Admin admin = (Admin) session.getAttribute("proxy");

        BaseResult baseResult = new BaseResult(-1, "投放策略获取失败");
        String pathurl = "advertise/advStrategyDetail";

        if (admin != null) {
            String proxyToken = admin.getProxy_token();
            String ptoken = admin.getPtoken();
            if (proxyToken != null) {
                JSONObject jsonObject = JSON.parseObject(param);
                if(jsonObject.getString("choose")!=null && jsonObject.getString("choose").equals("area")){
                    JSONArray jsonArray = new JSONArray();
                    List<AdminCity> adminCityList = proxyAreaService.distAreaByUtoken(proxyToken);
                    JSONObject object = new JSONObject();
                    try {
                        ProxyArea proxyArea = proxyAreaService.proxyAreaByUtoken(proxyToken);

                        object.put("area_rid", proxyArea.getArea_rid());
                        object.put("area_name",  proxyArea.getArea_name());
                        jsonArray.add(object);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    if (adminCityList != null && !adminCityList.isEmpty()) {
                        //市级代理
                        for (AdminCity adminCity : adminCityList) {
                            object = new JSONObject();
                            object.put("area_rid", adminCity.getCityCode());
                            object.put("area_name", adminCity.getCityName());

                            jsonArray.add(object);

                        }
                    }
                    baseResult = new BaseResult(1, "投放区域获取成功", jsonArray);
                }
                else{
                    jsonObject.put("proxyToken", proxyToken);
                    String advUUID = jsonObject.getString("advUUID");
                    if(advUUID!=null && !advUUID.isEmpty()){
                        String type = jsonObject.getString("type");
                        if(type.equals("surprise")) {
                            pathurl = "advertise/leSurpriseRedStrategy";
                        }
                    }

                    String res = AdBaseAPI.executeResult(ptoken, jsonObject.toJSONString(), pathurl);

                    if (res != null) {
                        if (!res.isEmpty()) {
                            baseResult = new BaseResult(1, "投放策略获取成功", res);
                        }
                    }
                }
            }
        }
        return JSON.toJSONString(baseResult);

    }

    /**
     * 投放区域选择
     * @param session
     * @param param
     * @return
     */
    @RequestMapping(value = "advDistrictDetail.do", produces = "application/json;charset=UTF-8")
    public
    @ResponseBody
    String advDistrictDetail(HttpSession session, @RequestBody String param) {
        Admin admin = (Admin) session.getAttribute("proxy");
        if (admin != null) {
            String proxyToken = admin.getProxy_token();
            String ptoken = admin.getPtoken();
                JSONObject jsonObject = JSON.parseObject(param);
                String proxyType = jsonObject.getString("proxyType");
                if(proxyType.equals("P")){
                    //区级代理
                    jsonObject.clear();
                    ProxyArea proxyArea = proxyAreaService.proxyAreaByUtoken(proxyToken);
                    jsonObject.put("area_rid",proxyArea.getArea_rid());
                    jsonObject.put("area_name",proxyArea.getArea_name());
                }else if(proxyType.equals("M")){
                    //市级代理
                    jsonObject.clear();
                    List<ProxyArea> proxyAreaList = proxyAreaService.distProxyAreaByUtoken(proxyToken);
                    JSONArray jsonArray = new JSONArray();
                    for(ProxyArea proxyArea : proxyAreaList) {
                        JSONObject object = new JSONObject();
                        object.put("area_rid",proxyArea.getArea_rid());
                        object.put("area_name",proxyArea.getArea_name());
                        jsonArray.add(object);
                    }
                    jsonObject.put("proxyAreaList",jsonArray);
                }
            return JSON.toJSONString(new BaseResult(1,"投放区域信息获取成功",jsonObject));
        }
        return JSON.toJSONString(new BaseResult(-1,"投放区域信息获取失败"));

    }



    /*
    * @descr: 1.3 更新投放策略(/updateAdvStrategy.do)
    */
    @RequestMapping(value = "updateAdvStrategy.do")
    public
    @ResponseBody
    String updateAdvStrategy(HttpSession session, @RequestBody String param) {
        Admin admin = (Admin) session.getAttribute("proxy");

        BaseResult baseResult = new BaseResult(-1, "投放策略获取失败");
        String pathUrl = "advertise/updateAdvStrategy";

        if (admin != null) {
            String proxyToken = admin.getProxy_token();
            String ptoken = admin.getPtoken();
            if (proxyToken != null) {
                JSONObject jsonObject = JSON.parseObject(param);
                jsonObject.put("proxyToken", proxyToken);
                String res = AdBaseAPI.executeResult(ptoken, jsonObject.toJSONString(), pathUrl);

                if (res != null && !res.isEmpty()) {
                    baseResult = new BaseResult(1, "投放策略获取成功", res);
                }
            }
        }
        return JSON.toJSONString(baseResult);
    }


    /**
     * 1.4 更新广告状态(/updateAdvStatus.do)
     *
     * @param session
     * @param param
     * @return
     */
    @RequestMapping(value = "updateAdvStatus.do")
    public
    @ResponseBody
    String updateAdvStatus(HttpSession session, @RequestBody String param) {
        Admin admin = (Admin) session.getAttribute("proxy");
        BaseResult baseResult = new BaseResult(-1, "系统错误");
        if (admin != null) {
            String pathUrl = "advertise/updateAdvStatus";
            String proxyToken = admin.getProxy_token();
            String ptoken = admin.getPtoken();
            if (proxyToken != null) {
                JSONObject jsonObject = JSON.parseObject(param);
                jsonObject.put("proxyToken", proxyToken);
                String res = AdBaseAPI.executeResult(ptoken, jsonObject.toJSONString(), pathUrl);

                if (res != null && !res.isEmpty()) {
                    AdBaseResult adBaseResult = JSONObject.parseObject(res, AdBaseResult.class);
                    if (adBaseResult.success()) {
                        baseResult = new BaseResult(1, adBaseResult.getError_reason());
                    } else {
                        baseResult = new BaseResult(-1, adBaseResult.getError_reason());
                    }
                }
            }
        }
        return JSON.toJSONString(baseResult);
    }


    /**
     * 执行删除
     * 广告状态必须是下架、待审核、审核未通过、待付款
     *
     * @return
     */
    @RequestMapping("delLeAdv.do")
    public
    @ResponseBody
    String delAd(@RequestBody String param, HttpSession httpSession) {
        if (param != null) {
            Admin admin = (Admin) httpSession.getAttribute(Constant.SESSION_KEY_PROXY);
            String proxy_token = admin.getProxy_token();
            String ptoken = admin.getPtoken();
            JSONObject jsonObject = JSONObject.parseObject(param);
            if (jsonObject != null && jsonObject.containsKey("advUUID")) {
                BaseResult baseResult = AdControlClient.delAdByUuid(ptoken, proxy_token, jsonObject.getString("advUUID"));
                return JSONObject.toJSONString(baseResult);
            }
        }
        return JSONObject.toJSONString(new BaseResult(-1, "请求失败"));
    }


    /**
     * 1.5 广告订单支付(/orderPay.do)
     *
     * @param param
     * @param session
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "orderPay.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String orderPay(@RequestBody String param, HttpSession session, RedirectAttributes redirectAttributes) {
        Admin admin = (Admin) session.getAttribute("proxy");

        String pathUrl = "advertise/orderPay";

        if (admin != null) {

            String proxyToken = admin.getProxy_token();
            String ptoken = admin.getPtoken();
            if (proxyToken != null) {
                JSONObject jsonObject = JSON.parseObject(param);
                jsonObject.put("proxyToken", proxyToken);
                String res = AdBaseAPI.executeResult(ptoken, jsonObject.toJSONString(), pathUrl);

                redirectAttributes.addFlashAttribute("json", res);
                return "redirect:/advertise/advPay.do";
            }
        }

        return "web/views/400";
    }

    /**
     * 更新广告信息
     * @param session
     * @param param
     * @return
     */
    @RequestMapping("update.do")
    public
    @ResponseBody
    String updateLeAdInfo(HttpSession session,@RequestBody String param) {
        Admin admin = (Admin) session.getAttribute(Constant.SESSION_KEY_PROXY);
        BaseResult baseResult = new BaseResult(-1, "更新广告信息失败");
        if (admin != null) {
            String proxyToken = admin.getProxy_token();
            String proxyName = admin.getUsername();
            String ptoken = admin.getPtoken();
            if (proxyToken != null) {
                if (param!=null) {
                    JSONObject jsonObject = JSONObject.parseObject(param);
                    jsonObject.put("proxyToken", proxyToken);
                    jsonObject.put("proxyName",proxyName);
                    String type = jsonObject.getString("type");
                    //String pathUrl = "advertise/updateLeAdv";
                    String pathUrl = "advertise/leAdvPost";
                    if(type.equals("surprise")){
                        pathUrl = "advertise/leSurpriseRed";
                    }
                    String res = AdBaseAPI.executeResult(ptoken, jsonObject.toJSONString(), pathUrl);
                    if (res != null) {
                        AdBaseResult adBaseResult = JSONObject.parseObject(res, AdBaseResult.class);
                        if (adBaseResult != null && adBaseResult.success()) {
                            baseResult.setErrcode(BaseResult.RESULT_SUCCESS);
                            baseResult.setErrmsg(adBaseResult.getError_reason());
                        }
                    }
                }
            }
        }

        return JSONObject.toJSONString(baseResult);
    }


    /**
     * 获取计费模板@descr: 2.1( /getWithdrawls.do)
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "getWithdrawls.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getWithdrawls(HttpSession session) {
        Admin admin = (Admin) session.getAttribute("proxy");

        BaseResult baseResult = new BaseResult(-1, "获取计费模板失败");
        String pathurl = "advertise/getWithdrawls";

        if (admin != null) {
            String proxyToken = admin.getProxy_token();
            String ptoken = admin.getPtoken();
            if (proxyToken != null) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("proxyToken", proxyToken);
                String res;
                if (InterfaceTest) {
                    res = InterfaceData.No_2_1_getWithdrawls;
                } else {
                    res = AdBaseAPI.executeResult(ptoken, jsonObject.toJSONString(), pathurl);
                }

                JSONArray jsonArray = JSON.parseArray(res);

                if (jsonArray != null) {
                    for (int i = 0; i < jsonArray.size(); i++) {
                        JSONObject jsonTmp = jsonArray.getJSONObject(i);

                        WithdrawalInfo withdrawalInfo = jsonTmp.getObject("withdrawal", WithdrawalInfo.class);
                        if (withdrawalInfo.getOnce_money() == null)
                            withdrawalInfo.setOnce_money(0);
                        withdrawalInfoHashMap.put(withdrawalInfo.getId(), withdrawalInfo);
                    }
                }

                if (!withdrawalInfoHashMap.isEmpty()) {
                    baseResult = new BaseResult(1, "获取计费模板成功", res);
                } else {
                    baseResult = new BaseResult(-1, "获取计费模板失败（json格式有问题）", null);
                }
            }
        }

        return JSON.toJSONString(baseResult);
    }


    /**
     * 2.2 广告发布(/advpost.do)
     * 提交广告申请
     * <p/>
     * {"advStrategyList":[{"groupName":"乐豆呀测试","proxyName":null,"groupId":15,"proxyToken":"abc"}
     * ,{"groupName":"乐豆呀测试","proxyName":null,"groupId":20,"proxyToken":"abc"}],
     * "storeName":"大家好"
     * ,"pic":"http://7xovjx.com2.z0.glb.qiniucdn.com/457704094883457086.jpg?imageView2/2/w/640/h/427/interlace/0/q/100"
     * ,"logo":"http://7xovjx.com2.z0.glb.qiniucdn.com/200505156113530110.png?imageView2/2/w/120/h/120/interlace/0/q/100"
     * ,"advUrl":"http://www.ledouya.com"
     * ,"beginTime":"2016-10-13 00:10:00",
     * "endTime":"2016-10-14 23:10:00","touNum":"100","withdrawalId":"8"}
     *
     * @param session
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "leAdvPost.do", method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String picAdvPost(HttpSession session,RedirectAttributes redirectAttributes,@RequestBody String param) {
        Admin admin = (Admin) session.getAttribute("proxy");
        if (admin != null) {
            String proxyToken = admin.getProxy_token();
            String proxyPhone = admin.getPhoneNum();
            String proxyName = admin.getUsername();
            String ptoken = admin.getPtoken();
            if (proxyToken != null) {
                String decode="";
                try {
                    decode = URLDecoder.decode(param, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    return "web/views/400";
                }

                String substringAfter = org.apache.commons.lang.StringUtils.substringAfter(decode, "formData=");
                JSONObject jsonObject = JSON.parseObject(substringAfter);
                jsonObject.put("proxyName",proxyName);
                jsonObject.put("proxyToken",proxyToken);

                String preFee = jsonObject.getString("preFee");
                String type = jsonObject.getString("type");
                String res;
                if (InterfaceTest) {
                    JSONObject jsonObject1 = new JSONObject();
                    String time = "" + System.currentTimeMillis();
                    String uuid = "abcdef" + time.substring(5, time.length() - 1);
                    jsonObject1.put("advUuid", uuid);
                    jsonObject1.put("totalFee", 1);
                    res = jsonObject1.toJSONString();
                } else {
                    String pathUrl = "advertise/leAdvPost";
                    if(type.equals("surprise")){
                        pathUrl = "advertise/leSurpriseRed";
                    }
                    res = AdBaseAPI.executeResult(ptoken, jsonObject.toJSONString(), pathUrl);
                }
                try {
                    if (res == null||res.isEmpty()) {
                        return "web/views/400";
                    }
                    JSONObject result = JSONObject.parseObject(res);

                    String advUuid = result.getJSONObject("object").getString("advUuid");
                   /* 本uuid广告的费用，单位分*/
                    Integer totalFee = result.getJSONObject("object").getInteger("totalFee");
                    if (advUuid != null && !advUuid.isEmpty()) {
                        /*前端上传的计算费用*/
                        if (totalFee != null && preFee != null && totalFee == FinanceUtil.Yuan2Fen(Double.parseDouble(preFee))) {
                            AdvOrderRecord advOrderRecord = new AdvOrderRecord();
                            advOrderRecord.setProxyPhone(proxyPhone);
                            advOrderRecord.setProxyToken(proxyToken);
                            advOrderRecord.setAdvFee((double) totalFee);
                            advOrderRecord.setAdvType(0);
                            advOrderRecord.setAdvUuid(advUuid);
                            AdvOrderRecord orderRecord = advService.addAdvOrder(advOrderRecord);

                            double pay_fee = FinanceUtil.Fen2Yuan((double) totalFee);
                            //判断advUuid是否存在及是否支付，如果不存在，在表中创建记录
                            if (orderRecord != null) {
                                String info = orderRecord.getId() + "#" + advUuid + "#" +type;
                                JSONObject jsonTmp = AdvPayClient.aliPayParam(pay_fee, info, advOrderRecord.getOurTradeNo());
                                redirectAttributes.addFlashAttribute("json", jsonTmp);
                                return RedirectUtil.redirect("/proxyFinance/toDeposit.do");
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return "web/views/400";
                }
            }
        }
        return "web/views/400";
    }


    /**
     * 重新支付
     *
     * @param session
     * @param advUuid
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "leAdvRePay.do")
    public String advRePay(HttpSession session, @RequestParam("advUUID") String advUuid,
                           @RequestParam("type") String type, RedirectAttributes redirectAttributes) {
        try {
            //System.out.println("repay--------advUuid=" + advUuid);
            Admin admin = (Admin) session.getAttribute(Constant.SESSION_KEY_PROXY);
            if (admin != null) {
                String proxyToken = admin.getProxy_token();
                AdvOrderRecord advOrderRecord = new AdvOrderRecord();
                advOrderRecord.setAdvUuid(advUuid);
                AdvOrderRecord orderRecord = advService.addAdvOrder(advOrderRecord);

                if (orderRecord != null) {
                    double pay_fee = FinanceUtil.Fen2Yuan(orderRecord.getAdvFee());
                    String info = orderRecord.getId() + "#" + advUuid +"#" + type;
                    JSONObject jsonTmp = AdvPayClient.aliPayParam(pay_fee, info, advOrderRecord.getOurTradeNo());
                    redirectAttributes.addFlashAttribute("json", jsonTmp);
                    return RedirectUtil.redirect("/proxyFinance/toDeposit.do");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "web/views/400";
    }


    /**
     * 广告费用支付(/advPay.do)
     *
     * @param param
     * @param session
     * @returnA
     * @descr:
     */
    @RequestMapping(value = "leAdvPay.do")
    public String advPay(@RequestBody(required = false) String param, HttpSession session
            , RedirectAttributes redirectAttributes
            , @ModelAttribute(value = "json") JSONObject json1) {
        try {
            LogUtil.log(AdvertiseController.class, LogUtil.LogType.INFO, "投放广告-开始支付宝支付");

            JSONObject json = json1;
            if (json == null || json.size() <= 0)
                json = JSON.parseObject(param);
            Admin admin = (Admin) session.getAttribute("proxy");

            //System.out.println(json.toJSONString());

            if (admin != null) {
                String proxyPhone = admin.getPhoneNum();
                String proxyToken = admin.getProxy_token();
                String advUuid = json.getString("advUUID");
                Integer advType = json.getInteger("advType");
                Integer withdrawalId = json.getInteger("withdrawalId");
                Integer touNum = json.getInteger("touNum");

                Integer totalFee = json.getInteger("totalFee");
                if (totalFee == null) {
                    //根据模板从新计算费用
                    WithdrawalInfo withdrawalInfo = withdrawalInfoHashMap.get(withdrawalId);
                    if (withdrawalInfo != null)
                        totalFee = AdvertiseUtil.calculateAdvBudget(withdrawalInfo.getType(),
                                withdrawalInfo.getOnce_money(), touNum);
                    if (totalFee == null) {
                        return "web/views/400";
                    }
                }
                if (InterfaceTest) {
                    totalFee = 1;
                }

                AdvOrderRecord advOrderRecord = new AdvOrderRecord();
                advOrderRecord.setProxyPhone(proxyPhone);
                advOrderRecord.setProxyToken(proxyToken);
                advOrderRecord.setAdvFee((double) totalFee);
                advOrderRecord.setAdvType(advType);
                advOrderRecord.setAdvUuid(advUuid);
                AdvOrderRecord orderRecord = advService.addAdvOrder(advOrderRecord);

                double pay_fee = FinanceUtil.Fen2Yuan((double) totalFee);
                //判断advUuid是否存在及是否支付，如果不存在，在表中创建记录
                if (orderRecord != null) {
                    String info = orderRecord.getId() + "#" + advUuid;
                    JSONObject jsonTmp = AdvPayClient.aliPayParam(pay_fee, info, advOrderRecord.getOurTradeNo());

                    redirectAttributes.addFlashAttribute("json", jsonTmp);
                    return RedirectUtil.redirect("/proxyFinance/toDeposit.do");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "web/views/400";
    }


    /**
     * 充值回调接口，更新代理商广告投放状态
     *
     * @param req
     * @return
     */
    @RequestMapping(value = "leAdvPayNotify.do")
    public
    @ResponseBody
    String advPayNotify(HttpServletRequest req) {
        return advService.advPayNotify(req);
    }

    /**
     * 惊喜红包数据详情
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "leSurpriseRedData.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public
    @ResponseBody
    String bigRedData(HttpSession session,@RequestBody String param) {
        Admin admin = (Admin) session.getAttribute("proxy");

        String pathurl = "advertise/leSurpriseRedData";

        if (admin != null) {
            String proxyToken = admin.getProxy_token();
            String ptoken = admin.getPtoken();

            if (proxyToken != null) {
                JSONObject jsonObject = JSON.parseObject(param);
                jsonObject.put("proxyToken",proxyToken);
                String res = AdBaseAPI.executeResult(ptoken, jsonObject.toJSONString(), pathurl);
                if (res != null && !res.isEmpty()){
                    return res;
                }
            }
        }
        return JSON.toJSONString(new BaseResult(-1, "惊喜红包数据详情获取失败"));
    }

}
