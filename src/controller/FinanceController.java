package controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import model.*;
import model.base.BaseResult;
import model.base.DivideBaseResult;
import model.base.ProxyArea;
import model.dto.*;
import model.dto.ProxyBenefitRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.*;
import utils.api.AdBaseAPI;
import utils.api.AdvCommission;
import utils.api.BaseAPI;
import utils.common.Constant;
import utils.common.DividePageUtil;
import utils.common.LogUtil;
import utils.finance.FinanceUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lenovo on 2015/12/3.
 * <p/>
 * 主要功能：
 * 提供代理商查看自己当前的财务状况：
 * 总资产（累计收益）
 * 可用资产（余额）
 * 已结算金额（已经处理）
 * 待结算金额（申请中）
 * <p/>
 * 收益明细：
 * 显示每个商家的财务信息
 * <p/>
 * 代理商的收益详情：“今日收益”、“本月收益”、“累计收益”
 */

@RestController
@RequestMapping(value = "/finance")
public class FinanceController {
    @Resource
    private FinanceService financeService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private ProxyAreaService proxyAreaService;

    @Autowired
    private ProxyBenefitRecordService proxyBenefitRecordService;

    @Autowired
    private DealerService dealerService;


    @RequestMapping(value = "index.do")
    @ResponseBody
    public String financeManage() {
        return financeService.financeManage().toJSONString();
    }

    /**
     * 充值申请
     *
     * @param
     * @return
     */
    @RequestMapping(value = "deposit.do")
    public String depositApply() {

        return financeService.depositApply().toJSONString();
    }

    /**
     * 充值确认
     *
     * @return
     */
    @RequestMapping(value = "rechargeConfirm.do", produces = "application/json;charset=UTF-8")
    public String chargeConfirm(HttpSession session, @RequestBody String param) {

        String username = session.getAttribute("userTag").toString();

        return financeService.rechargeConfirm(param, username).toJSONString();
    }

    /**
     * 提现确认
     */
    @RequestMapping(value = "withdrawnConfirm.do")
    public String cashConfirm(HttpSession session, @RequestBody String param) {

        String username = session.getAttribute("userTag").toString();
        return financeService.recashConfirm(param, username).toJSONString();
    }


    /**
     *
     * 收益明细，核心统计信息（关键指标）
     *
     * @param httpSession
     * @return
     */
    @RequestMapping("/statistics")
    public String statistics(HttpSession httpSession,@RequestBody String params){
        Admin admin = (Admin) httpSession.getAttribute("proxy");
        String utoken = admin.getProxy_token();
        String phone = admin.getAccount();
        if(utoken!=null){
            FinanceStatistics financeStatistics = financeService.allFinanceStatics_v2(phone, utoken);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("financeStatistics",financeStatistics);
            return JSON.toJSONString(new BaseResult(1,"收益明细关键信息获取成功",jsonObject));
        }
        return JSON.toJSONString(new BaseResult(-1, "收益明细关键信息获取失败"));

    }

    /**
     *商户营销分成奖励列表
     *
     *
     * @param httpSession
     * @param params
     * @return
     */
    @RequestMapping("proxyBenefitRecord.do")
    public String proxyBenefitRecord(HttpSession httpSession,@RequestBody String params){

        Admin admin = (Admin) httpSession.getAttribute(Constant.SESSION_KEY_PROXY);
        String utoken = admin.getProxy_token();
        if(utoken!=null) {
            JSONObject jsonObject = JSONObject.parseObject(params);
            Integer startPage = jsonObject.getInteger("startPage");
            Integer pageSize = jsonObject.getInteger("pageSize");
            Integer startSize = (startPage - 1) * pageSize;
            List<ProxyBenefitRecord> proxyMonthBenefitList = proxyBenefitRecordService.proxyBenefitMonthList(utoken, startSize, pageSize);
            if(proxyMonthBenefitList!=null && !proxyMonthBenefitList.isEmpty()){
                int count = proxyBenefitRecordService.proxyBenefitRecordCount(utoken);
                int pages = DividePageUtil.getPages(count,pageSize);
                jsonObject.clear();
                jsonObject.put("proxyMonthBenefitList", proxyMonthBenefitList);
                jsonObject.put("pages",pages);
                return JSON.toJSONString(new BaseResult(1,"商户营销分成奖励获取成功",jsonObject));
            }
        }
        return JSON.toJSONString(new BaseResult(-1, "商户营销分成奖励获取失败"));

    }

    /**
     *商户营销分成奖励详情
     *
     *
     * @param httpSession
     * @param params
     * @return
     */
    @RequestMapping("proxyBenefitDetail.do")
    public String proxyBenefitDetail(HttpSession httpSession,@RequestBody String params){

        Admin admin = (Admin) httpSession.getAttribute(Constant.SESSION_KEY_PROXY);
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
                    return JSON.toJSONString(new BaseResult(1, "商户营销分成奖励详情获取成功", jsonObject));
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
                return JSON.toJSONString(new BaseResult(1, "商户营销分成奖励详情获取成功", jsonArray));
            }else{
                return JSON.toJSONString(new BaseResult(-1,"商户营销分成奖励详情获取失败"));
            }
        }
        return JSON.toJSONString(new BaseResult(-1,"商户营销分成奖励详情获取失败"));

    }

    /**
     * 加盟费记录（市级代理）
     *
     * @param session
     * @param param
     * @return
     */
    @RequestMapping(value = "franchiseFeeRecordList.do", produces = "application/json;charset=UTF-8")
    public
    @ResponseBody
    String franchiseFeeRecordList(HttpSession session, @RequestBody String param) {
        Admin admin = (Admin) session.getAttribute("proxy");
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
                jsonObject.put("franchiseFeeList", franchiseFeeList);
                jsonObject.put("pages",pages);
                if (!jsonObject.isEmpty()) {
                    return JSON.toJSONString(new BaseResult(1,"加盟费记录获取成功",jsonObject));
                }
            }
        }
        return JSON.toJSONString(new BaseResult(-1,"加盟费记录获取失败"));
    }

    /**
     * 下级（区县）代理采购记录（市级代理）
     *
     * @param session
     * @param param
     * @return
     */
    @RequestMapping(value = "distBuyRecordList.do", produces = "application/json;charset=UTF-8")
    public
    @ResponseBody
    String distBuyRecordList(HttpSession session, @RequestBody String param) {
        Admin admin = (Admin) session.getAttribute("proxy");
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
                    return JSON.toJSONString(new BaseResult(1,"下级（区县）代理采购记录获取成功",jsonObject));
                }
            }
        }
        return JSON.toJSONString(new BaseResult(-1,"下级（区县）代理采购记录获取失败"));
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

    /**
     * 下级（区县）代理采购记录详情（市级代理）
     *
     * @param session
     * @param param
     * @return
     */
    @RequestMapping(value = "distBuyRecordDetail.do", produces = "application/json;charset=UTF-8")
    public
    @ResponseBody
    String distBuyRecordDetail(HttpSession session, @RequestBody String param) {
        Admin admin = (Admin) session.getAttribute("proxy");
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
                List<DistBuyDetail> distBuyDetailList = dealerService.monthDistBuyDetail(proxyToken, time, startSize, pageSize);
                jsonObject.put("distBuyDetailList", distBuyDetailList);
                jsonObject.put("pages",pages);
                if (jsonObject != null && !jsonObject.isEmpty()) {
                    return JSON.toJSONString(new BaseResult(1,"下级（区县）代理采购记录详情获取成功",jsonObject));
                }
            }
        }
        return JSON.toJSONString(new BaseResult(-1,"下级（区县）代理采购记录详情获取失败"));
    }


    /**
     *
     * 商户广告充值记录详情
     *
     * @param httpSession
     * @param params
     * @return
     */
    @RequestMapping("/storeAdDepositRecordDetail.do")
    public String storeAdDepositRecordDetail(HttpSession httpSession,@RequestBody String params){
        BaseResult baseResult = new BaseResult(BaseResult.RESULT_ERROR,"获取商户广告充值记录详情失败");

        Admin admin = (Admin) httpSession.getAttribute(Constant.SESSION_KEY_PROXY);
        String utoken = admin.getProxy_token();
        String ptoken = admin.getPtoken();
        String pathurl = "finance/storeAdDepositRecordDetail";
        if(utoken!=null){
            JSONObject jsonObject = JSONObject.parseObject(params);
            jsonObject.put("utoken", utoken);
            String res = AdBaseAPI.executeResult(ptoken,jsonObject.toJSONString(),pathurl);
            if(res!=null && !res.isEmpty()){
                return res;
            }
        }
        return JSONObject.toJSONString(baseResult);

    }

    /**
     *
     * 商户广告金、自有设备品牌红包（新）
     *
     * @param httpSession
     * @param params
     * @return
     */
    @RequestMapping("/getMonthFinance.do")
    public String getMonthFinance(HttpSession httpSession,@RequestBody String params){
        BaseResult baseResult = new BaseResult(BaseResult.RESULT_ERROR,"获取商户广告充值记录失败");

        Admin admin = (Admin) httpSession.getAttribute(Constant.SESSION_KEY_PROXY);
        String proxy_token = admin.getProxy_token();
        String ptoken = admin.getPtoken();
        String pathurl = "finance/getMonthFinance";
        if(proxy_token!=null){
            JSONObject jsonObject = JSONObject.parseObject(params);
            jsonObject.put("proxy_token", proxy_token);
            String res = AdBaseAPI.executeResult(ptoken,jsonObject.toJSONString(),pathurl);
            if(res!=null && !res.isEmpty()){
                return res;
            }
        }
        return JSONObject.toJSONString(baseResult);

    }




    /**
     *
     * 商户广告充值记录（按天显示）（旧版）
     *
     * @param httpSession
     * @param params
     * @return
     */
    @RequestMapping("/storeAdDeposit")
    public String getStoreAdDeposit(HttpSession httpSession,@RequestBody String params){
        BaseResult baseResult = new BaseResult(BaseResult.RESULT_ERROR,"获取商户广告充值记录失败");

        Admin admin = (Admin) httpSession.getAttribute(Constant.SESSION_KEY_PROXY);
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

        return JSONObject.toJSONString(baseResult);

    }

    @RequestMapping(value = "/storeFinanceStatistics.do")
    public
    String getStoreFinanceStatistics(HttpSession httpSession, @RequestBody String param) {
        Admin admin = (Admin) httpSession.getAttribute("proxy");
        String phone;
        JSONObject jsonObject = JSONObject.parseObject(param);
        if (admin == null) {
            phone = jsonObject.getString("phone");
        } else
            phone = admin.getPhoneNum();
        jsonObject = new JSONObject();

        if (phone != null) {
            try {
                StoreFinanceStatistics moneys = financeService.storeFinanceStatistics(phone);
                if (moneys != null) {
                    jsonObject.put("errorcode", 1);
                    jsonObject.put("statistics", moneys);

                    return jsonObject.toString();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        jsonObject.put("errorcode", -1);
        return jsonObject.toString();
    }


    /**
     * 获取代理商下属商户总体财务状况 包括 此商户对代理商抽成金额(元)
     *
     * @author yeran
     * @time 2016年8月8日20:09:17
     */
    @RequestMapping(value = "storeDeposits.do")
    public String getStoreFinanceDetails(HttpSession httpSession, @RequestBody String param, HttpServletResponse response) throws IOException {
        JSONObject jsonObject = JSON.parseObject(param);
        String phone;
        Admin admin = (Admin) httpSession.getAttribute("proxy");
        if (admin != null) {
            phone = admin.getPhoneNum();
        } else {
            phone = jsonObject.getString("phone");
        }
        if (phone == null) {
            JSONObject json = new JSONObject();
            json.put("errorcode", -1);
            json.put("errormsg", "账号不存在，请检查账号电话");
            return json.toString();
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
        return jsonObject.toString();
    }


    /**
     * 获取代理商下属所有店铺的充值记录
     *
     * @author yeran
     * @time 2016年8月8日14:22:05
     */
    @RequestMapping(value = "depositDetails.do", produces = "application/json;charset=UTF-8")
    public
    String depositDetails(HttpSession httpSession
            , @RequestBody String param) throws IOException {
        Admin admin = (Admin) httpSession.getAttribute("proxy");
        JSONObject jsonObject = JSON.parseObject(param);
        String phone;
        if (admin != null) {
            phone = admin.getPhoneNum();
        } else {
            phone = jsonObject.getString("phone");
        }
        if (phone == null) {
            jsonObject = new JSONObject();
            jsonObject.put("errorcode", -1);
            jsonObject.put("errormsg", "缺少必要参数");
            return jsonObject.toString();
        }
        try {
            Date startDate = null, endDate = null;

            String startDateS = jsonObject.getString("startTime");
            if (startDateS != null && startDateS.trim().length() >= 10) {
                startDate = new SimpleDateFormat("yyyy-MM-dd").parse(startDateS);
            }
            String endDateS = jsonObject.getString("endTime");
            if (endDateS != null && endDateS.trim().length() >= 10) {
                endDate = new SimpleDateFormat("yyyy-MM-dd").parse(endDateS);
            }

            Integer startPage = jsonObject.getInteger("startPage");
            Integer pageSize = jsonObject.getInteger("pageSize");

            String keyword = jsonObject.getString("keyword");

            List<DepositWithTaxes> depositWithTaxes = financeService.getDetailDepositWithTaxes(phone, startDate, endDate, startPage, pageSize, keyword);

            jsonObject = new JSONObject();
            if (startPage != null && startPage == 1) {
                //获取总页数
                int pages = financeService.countDetailDepositWithTaxes(phone, startDate, endDate, keyword);
                jsonObject.put("pages", DividePageUtil.getPages(pages, pageSize));
            }

            jsonObject.put("depositWithTaxes", depositWithTaxes);
            return jsonObject.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 获取代理商下属所有店铺的广告提成记录
     *
     * @author yeran
     * @time 2016年8月8日14:22:05
     */
    @RequestMapping(value = "CommissionList.do", produces = "application/json;charset=UTF-8")
    public
    String CommissionDetails(@RequestBody String param) throws IOException {

        return AdvCommission.advCommissionList(param);
    }


    /**
     * 获取商户所有的充值记录
     *
     * @author yeran
     * @time 2016年8月9日11:40:13
     */
    @RequestMapping(value = "getStoreDepositDetail.do")
    public
    @ResponseBody
    String getStoreDepositDetail(@RequestBody String param) throws ParseException {
        JSONObject jsonObject = JSON.parseObject(param);

        //商户电话
        String phone = jsonObject.getString("phone");
        if (phone != null && phone.trim().length() == 11) {
            Date startDate = null, endDate = null;

            String startDateS = jsonObject.getString("startTime");
            if (startDateS != null && startDateS.trim().length() >= 10) {
                startDate = new SimpleDateFormat("yyyy-MM-dd").parse(startDateS);
            }
            String endDateS = jsonObject.getString("endTime");
            if (endDateS != null && endDateS.trim().length() >= 10) {
                endDate = new SimpleDateFormat("yyyy-MM-dd").parse(endDateS);
            }

            Integer startPage = jsonObject.getInteger("startPage");
            Integer pageSize = jsonObject.getInteger("pageSize");

            String keyword = jsonObject.getString("keyword");

            List<DepositWithTaxes> depositWithTaxes = financeService.getStoreDetailDepositWithTaxes(phone, startDate, endDate, startPage, pageSize, keyword);

            jsonObject = new JSONObject();
            if (startPage != null && startPage == 1) {
                //获取总页数
                int pages = financeService.countStoreDetailDepositWithTaxes(phone, startDate, endDate, keyword);
                jsonObject.put("pages", DividePageUtil.getPages(pages, pageSize));
            }
            jsonObject.put("depositWithTaxes", depositWithTaxes);
            return jsonObject.toString();
        }

        return null;
    }





    /**
     * 获取代理商充值收益概览
     * 今日
     * 本月
     * 累计
     *
     * @author yeran
     * @time 2016年8月8日14:56:44
     */
    @RequestMapping(value = "getBenefitStatistics.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public
    @ResponseBody
    String getBenefitStatistics(HttpSession httpSession, @RequestBody String param) throws IOException {
        Admin admin = (Admin) httpSession.getAttribute("proxy");
        JSONObject jsonObject = JSON.parseObject(param);
        String phone;
        if (admin != null) {
            phone = admin.getPhoneNum();
        } else {
            phone = jsonObject.getString("phone");
        }
        try {
            Double[] benefits = financeService.getStoreProxyBenefitByTime(phone);
            jsonObject = new JSONObject();
            jsonObject.put("todayBenefit", benefits[0]);
            jsonObject.put("monthBenefit", benefits[1]);
            jsonObject.put("totalBenefit", benefits[2]);
            return jsonObject.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    /**
     * 获取代理商广告提成收益概览
     * 今日
     * 本月
     * 累计
     *
     * @author miaolu
     * @time 2016年10月3日14:56:44
     */
    @RequestMapping(value = "getCommissionBenefit.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public
    @ResponseBody
    String getCommissionBenefit(String param) {
        return AdvCommission.getBenefitStatistics(param);
    }

    /**
     * @param param account代表商家电话号码
     * @return 流水详情
     */
    @RequestMapping(value = "detail.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String detail(@RequestBody String param) {
        return financeService.userHistory(param).toJSONString();
    }

    @RequestMapping(value = "searchByTradeNo.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String searchByTradeNo(@RequestBody String param) {
        return financeService.searchByTradeNo(param).toJSONString();
    }

    @RequestMapping(value = "detailSearch.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String searchByClause(@RequestBody String param) {

        return financeService.searchByClause(param).toJSONString();
    }


    /**
     * 获取代理商所有的收益总览
     * 包括商户充值收益以及广告奖励收益（）
     *
     * @return
     */
    @RequestMapping(value = "advBenefitIndex.do")
    public
    @ResponseBody
    String advBenefitIndex(HttpSession session) {
        Admin admin = (Admin) session.getAttribute("proxy");

        BaseResult baseResult = new BaseResult(-1, "广告提成收益获取失败");
        String pathUrl = "/finance/advBenefitIndex";
        String phone = "";
        String res = "";
        JSONObject jsonObject = new JSONObject();

        if (admin != null) {
            String proxyToken = admin.getProxy_token();
            String ptoken = admin.getPtoken();
            phone = admin.getPhoneNum();
            if (proxyToken != null) {
                jsonObject.put("proxyToken", proxyToken);
                jsonObject.put("openid", admin.getOpenid());
                res = AdBaseAPI.executeResult(ptoken, jsonObject.toJSONString(), pathUrl);
            }
        }
        if (res != null && !res.isEmpty()) {
            jsonObject = JSON.parseObject(res);
            try {
                JSONObject jsonBenefit = new JSONObject();
                Double[] benefits = financeService.getStoreProxyBenefitByTime(phone);
                Double todayBenefit = benefits[0];
                Double monthBenefit = benefits[1];
                Double totalBenefit = benefits[2];

                //System.out.println("Double[] : " +JSONObject.toJSONString(benefits));
                if (jsonObject.getDouble("todayBenefit") != null) {
                    todayBenefit += jsonObject.getDouble("todayBenefit");
                }
                if (jsonObject.getDouble("monthBenefit") != null) {
                    monthBenefit += jsonObject.getDouble("monthBenefit");
                }
                if (jsonObject.getDouble("totalBenefit") != null) {
                    totalBenefit += jsonObject.getDouble("totalBenefit");
                }

                jsonBenefit.put("todayBenefit",todayBenefit );
                jsonBenefit.put("monthBenefit",monthBenefit );
                jsonBenefit.put("totalBenefit",totalBenefit );

                baseResult = new BaseResult(1, "广告提成收益获取成功", jsonBenefit);
            } catch (Exception e) {
                LogUtil.log(FinanceController.class, LogUtil.LogType.ERROR,e.getMessage());
                baseResult = new BaseResult(1, "广告提成收益获取失败");
            }
        }
        return JSON.toJSONString(baseResult);
    }


    /**
     * 获取代理商从购买设备开始到现在一共的天数，计算出分页数量
     *
     * @param session
     * @param param
     * @return
     * @throws ParseException
     */
    @RequestMapping("/proxyDeviceBenefitPages.do")
    public @ResponseBody String proxyDeviceBenefitPages(HttpSession session,@RequestBody String param) throws ParseException {
        Admin admin = (Admin) session.getAttribute("proxy");
        BaseResult baseResult = new BaseResult(-1, "代理商首次购买设备时间获取失败");
        String pathUrl = "finance/advBenefitFirstDay";
        if (admin != null) {
            String proxyToken = admin.getProxy_token();
            String ptoken = admin.getPtoken();

            JSONObject jsonObject =JSON.parseObject(param);
            Integer pageSize = jsonObject.getInteger("pageSize");
            if (proxyToken != null) {

                Integer type = jsonObject.getInteger("type");
                List<String> utokens = new ArrayList<>();
                if (1==type){
                    utokens.add(proxyToken);
                }else if(2==type){
                    utokens = adminService.dealerTokens(proxyToken);
                }
                jsonObject.put("proxyToken", proxyToken);
                jsonObject.put("utokens",utokens);

                String res = AdBaseAPI.executeResult(ptoken, jsonObject.toJSONString(), pathUrl);
                jsonObject = JSON.parseObject(res);
                String firstDay = jsonObject.getString("firstDay");
                if (firstDay!=null) {
                    Date date = new SimpleDateFormat("yyyy-MM-dd").parse(firstDay);
                    long days = (new Date().getTime()-date.getTime())/(1000*3600*24);

                    int pages = DividePageUtil.getPages((int)days,pageSize);
                    baseResult.setErrcode(BaseResult.RESULT_SUCCESS);
                    baseResult.setErrmsg("获取成功");
                    baseResult.setObject(pages);
                }
            }
        }
        return JSONObject.toJSONString(baseResult);
    }


    /**
     * 3.2 广告提成信息列表(advBenefitList.do)
     *
     * @param session
     * @param param
     * @return
     */
    @RequestMapping(value = "advBenefitList.do")
    public
    @ResponseBody
    String advBenefitList(HttpSession session, @RequestBody String param) {
        Admin admin = (Admin) session.getAttribute(Constant.SESSION_KEY_PROXY);
        BaseResult baseResult = new BaseResult(-1, "广告提成信息列表失败");
        String pathUrl = "/finance/advBenefitList";

        if (admin != null) {
            String proxyToken = admin.getProxy_token();
            String ptoken = admin.getPtoken();
            if (proxyToken != null) {
                JSONObject jsonObject = JSON.parseObject(param);

                Integer type = jsonObject.getInteger("type");
                Integer benefitType = jsonObject.getInteger("benefitType");
                List<String> utokens = new ArrayList<>();
                if(1==benefitType) {
                    if (1 == type) {
                        //自有设备品牌红包
                        utokens.add(proxyToken);
                    } else if (2 == type) {
                        //直接推荐设备品牌红包
                        utokens = adminService.dealerTokens(proxyToken);
                    }
                }else if(2==benefitType){
                    //辖区设备品牌红包
                    List<ProxyArea> proxyAreaList = proxyAreaService.distProxyAreaByUtoken(proxyToken);
                    if(proxyAreaList!=null && !proxyAreaList.isEmpty()){
                        for(ProxyArea proxyArea : proxyAreaList){
                            utokens.add(proxyArea.getUtoken());
                        }
                    }
                    utokens.add(proxyToken);
                }

                jsonObject.put("utokens",utokens);
                jsonObject.put("proxyToken", proxyToken);
                String res = AdBaseAPI.executeResult(ptoken, jsonObject.toJSONString(), pathUrl);

                if (res != null && !res.isEmpty()) {
                    baseResult = new BaseResult(1, "广告提成信息列表成功", res);
                }
            }
        }
        return JSON.toJSONString(baseResult);
    }


    /**
     * 3.3 数据指标(dateDataIndex.do)
     *
     * @param session
     * @param param
     * @return
     */
    @RequestMapping(value = "dateDataIndex.do")
    public
    @ResponseBody
    String dateDataIndex(HttpSession session, @RequestBody String param) {
        Admin admin = (Admin) session.getAttribute(Constant.SESSION_KEY_PROXY);

        BaseResult baseResult = new BaseResult(-1, "数据指标失败");
        String pathUrl = "/finance/dateDataIndex";

        if (admin != null) {
            String proxyToken = admin.getProxy_token();
            String ptoken = admin.getPtoken();
            if (proxyToken != null) {
                JSONObject jsonObject = JSON.parseObject(param);
                jsonObject.put("proxyToken", proxyToken);

                String res = AdBaseAPI.executeResult(ptoken, jsonObject.toJSONString(), pathUrl);

                if (res != null && !res.isEmpty()) {
                    baseResult = new BaseResult(1, "数据指标成功", res);
                }
            }
        }
        return JSON.toJSONString(baseResult);
    }

    /**
     * 3.4 设备广告收益详情(dateDeviceList.do)
     *
     * @param session
     * @param param
     * @return
     */
    @RequestMapping(value = "dateDeviceList.do")
    public
    @ResponseBody
    String dateDeviceList(HttpSession session, @RequestBody String param) {
        Admin admin = (Admin) session.getAttribute("proxy");

        BaseResult baseResult = new BaseResult(-1, "设备广告收益详情失败");
        String pathUrl = "/finance/dateDeviceList";

        if (admin != null) {
            String proxyToken = admin.getProxy_token();
            String ptoken = admin.getPtoken();
            if (proxyToken != null) {
                JSONObject jsonObject = JSON.parseObject(param);
                jsonObject.put("proxyToken", proxyToken);
                String res = AdBaseAPI.executeResult(ptoken, jsonObject.toJSONString(), pathUrl);

                if (res != null && !res.isEmpty()) {
                    baseResult = new BaseResult(1, "设备广告收益详情成功", res);
                }
            }
        }
        return JSON.toJSONString(baseResult);
    }

    /**
     * 辖区内品牌红包相关数据（指标+列表）（dateRedDataIndex.do）
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "dateRedDataIndex.do")
    public
    @ResponseBody
    String dateRedDataIndex(HttpSession session, @RequestBody String param) {
        Admin admin = (Admin) session.getAttribute(Constant.SESSION_KEY_PROXY);

        BaseResult baseResult = new BaseResult(-1, "辖区内品牌红包相关数据获取失败");
        String pathUrl = "/finance/dateRedDataIndex";

        if (admin != null) {
            String proxyToken = admin.getProxy_token();
            String ptoken = admin.getPtoken();
            if (proxyToken != null) {
                JSONObject jsonObject = JSON.parseObject(param);
                List<String> utokens = new ArrayList<>();
                List<ProxyArea> proxyAreaList = proxyAreaService.distProxyAreaByUtoken(proxyToken);
                if(proxyAreaList!=null && !proxyAreaList.isEmpty()){
                    for(ProxyArea proxyArea : proxyAreaList){
                        utokens.add(proxyArea.getUtoken());
                    }
                }
                utokens.add(proxyToken);
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
        return JSON.toJSONString(baseResult);
    }

    /**
     * 3.5 搜索设备序列号(searchDeviceNo.do)
     *
     * @param session
     * @param param
     * @return
     */
    @RequestMapping(value = "searchDeviceNo.do")
    public
    @ResponseBody
    String searchDeviceNo(HttpSession session, @RequestBody String param) {
        Admin admin = (Admin) session.getAttribute(Constant.SESSION_KEY_PROXY);

        BaseResult baseResult = new BaseResult(-1, "搜索设备序列号失败");
        String pathUrl = "/finance/searchDeviceNo";

        if (admin != null) {

            String proxyToken = admin.getProxy_token();
            String ptoken = admin.getPtoken();
            if (proxyToken != null) {
                JSONObject jsonObject = JSON.parseObject(param);
                jsonObject.put("proxyToken", proxyToken);
                String res = AdBaseAPI.executeResult(ptoken, jsonObject.toJSONString(), pathUrl);

                if (res != null && !res.isEmpty()) {
                    baseResult = new BaseResult(1, "搜索设备序列号成功", res);
                }
            }
        }
        return JSON.toJSONString(baseResult);
    }


    /**
     *
     * 设备激活记录（旧版）
     *
     * @param session
     * @param params
     * @return
     */
    @RequestMapping("deviceActivateRecord")
    public BaseResult deviceActivateRecord(HttpSession session, @RequestBody String params){
        Admin admin = (Admin) session.getAttribute(Constant.SESSION_KEY_PROXY);

        String proxyToken = admin.getProxy_token();
        JSONObject jsonObject = JSONObject.parseObject(params);
        jsonObject.put("proxyToken",proxyToken);

        BaseResult baseResult = AdBaseAPI.execute(jsonObject, BaseAPI.DEVICE_ACTIVATE_RECORD);

        return baseResult;

    }

    /**
     *
     * 设备激活记录（新）
     *
     * @param httpSession
     * @param params
     * @return
     */
    @RequestMapping("/monthDeviceList.do")
    public String monthDeviceList(HttpSession httpSession,@RequestBody String params){
        BaseResult baseResult = new BaseResult(BaseResult.RESULT_ERROR,"获取商户广告充值记录失败");

        Admin admin = (Admin) httpSession.getAttribute(Constant.SESSION_KEY_PROXY);
        String proxy_token = admin.getProxy_token();
        String ptoken = admin.getPtoken();
        String pathurl = "device/monthDeviceList";
        if(proxy_token!=null){
            JSONObject jsonObject = JSONObject.parseObject(params);
            jsonObject.put("proxy_token",proxy_token);
            String res = AdBaseAPI.executeResult(ptoken,jsonObject.toJSONString(),pathurl);
            if(res!=null && !res.isEmpty()){
                return res;
            }
        }
        return JSONObject.toJSONString(baseResult);

    }


    /**
     *
     * 代理商指定某一天的设备激活统计
     *
     * @param session
     * @param params
     * @return
     */
    @RequestMapping("dateDeviceActivateIndex")
    public BaseResult dateDeviceActivateIndex(HttpSession session, @RequestBody String params){
        Admin admin = (Admin) session.getAttribute(Constant.SESSION_KEY_PROXY);

        String proxyToken = admin.getProxy_token();
        JSONObject jsonObject = JSONObject.parseObject(params);
        jsonObject.put("proxyToken",proxyToken);

        BaseResult baseResult = AdBaseAPI.execute(jsonObject, BaseAPI.DEVICE_ACTIVATE_INDEX_DAY);

        return baseResult;

    }

    /**
     *
     * 代理商指定某一天的设备激活列表
     *
     * @param session
     * @param params
     * @return
     */
    @RequestMapping("dateDeviceActivateRecord")
    public BaseResult dateDeviceActivateRecord(HttpSession session, @RequestBody String params){
        Admin admin = (Admin) session.getAttribute(Constant.SESSION_KEY_PROXY);

        String proxyToken = admin.getProxy_token();
        JSONObject jsonObject = JSONObject.parseObject(params);
        jsonObject.put("proxyToken",proxyToken);

        BaseResult baseResult = AdBaseAPI.execute(jsonObject, BaseAPI.DEVICE_ACTIVATE_RECORD_DAY);

        return baseResult;

    }

}
