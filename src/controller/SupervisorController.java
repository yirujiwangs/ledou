package controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import model.Admin;
import model.DeviceBuyRecord;
import model.ProxyAreaReportLog;
import model.base.BaseResult;
import model.base.DivideBaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.AdminService;
import service.DeviceService;
import service.ProxyAreaService;
import service.SupervisorService;
import utils.common.Constant;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/8/15.
 */

@RestController
@RequestMapping("/supervisor")
public class SupervisorController {

    @Resource
    private SupervisorService supervisorService;

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private ProxyAreaService proxyAreaService;

    @Autowired
    private AdminService adminService;


    /**
     * @return
     * @descr 获取管理面板关键指标与互动数据
     */
    @RequestMapping(value = "index.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String overview() {
        return supervisorService.overview("test").toJSONString();
    }


    /**
     * @param param
     * @return
     * @descr 管理面板趋势图数据
     */
    @RequestMapping(value = "tendencychart.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String tendencyChart(@RequestBody String param) {
        return supervisorService.tendencyChart(param).toJSONString();
    }


/***********************************************************************************************************************/


    /**
     * @param param
     * @return
     * @descr 账号管理页面
     */
    @RequestMapping(value = "accountManage.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String accountManage(@RequestBody String param) {
        return supervisorService.accountManage(param).toJSONString();
    }


    /**
     * @param param
     * @return
     * @descr 模糊搜索运营商账号（账号管理页面）
     */
    @RequestMapping(value = "accountManageSearch.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String accountManageSearch(@RequestBody String param) {
        return supervisorService.accountManageSearch(param).toJSONString();
    }


    /**
     * @param param
     * @return'
     * @descr 创建运营商账号（账号管理页面）
     */
    @RequestMapping(value = "insertAccount.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String insertAccount(HttpSession httpSession, @RequestBody String param) {
        String sup_account = (String) httpSession.getAttribute(Constant.SESSION_KEY_SUPERVISOR_ACCOUNT);
        BaseResult baseResult = new BaseResult(-1, "创建代理商失败");
        if (sup_account != null)
            return supervisorService.insertAccount(param).toJSONString();

        return JSON.toJSONString(baseResult);
    }


    /**
     * @param param
     * @return
     * @descr 更新备注信息(账号管理页面)
     */
    @RequestMapping(value = "updateRemark.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String updateRemark(@RequestBody String param) {
        return supervisorService.updateRemark(param).toJSONString();
    }


    /**
     * @param session
     * @param param
     * @return
     * @descr 详情：跳转至该运营商的账号管理页面(账号管理页面)
     */
    @RequestMapping(value = "corporationInfoDetail.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String corporationInfoDetail(HttpSession session, @RequestBody String param) {
        JSONObject json = JSON.parseObject(param);
        Integer corporationId = json.getInteger("corporationid");
        String phone = json.getString("phone");
        session.setAttribute("userId", corporationId);

        json = new JSONObject();
        json.put("flag", false);
        if (corporationId != null) {
            Admin admin = new Admin();
            admin.setId(corporationId);
            admin.setPhoneNum(phone);

            session.setAttribute("proxy", admin);
            json.put("flag", true);
        }
        return json.toJSONString();
    }


    /**
     * @param param
     * @return
     * @descr 按照账号状态进行列表(账号管理页面)
     */
    @RequestMapping(value = "accountListByStatus.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String accountListByStatus(@RequestBody String param) {
        return supervisorService.accountListByStatus(param).toJSONString();
    }


    /**
     * @param param
     * @return
     * @descr 账号订单信息列表(账号订单页面)
     */
    @RequestMapping(value = "accountOrderList.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String accountOrderList(@RequestBody String param) {
        return supervisorService.accountOrderList(param).toJSONString();
    }


    /**
     * @param param
     * @return
     * @descr 订单信息模糊查询（账号订单页面）
     */
    @RequestMapping(value = "accountOrderSearch.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String accountOrderSearch(@RequestBody String param) {
        return supervisorService.accountOrderSearch(param).toJSONString();
    }


/***********************************************************************************************************************/

    /**
     * 获取所有代理商订单
     *
     * @param session
     * @param param
     * @return
     * @throws ParseException
     */
    @RequestMapping("/record.do")
    public BaseResult orderRecord(HttpSession session, @RequestBody String param) throws ParseException {
        BaseResult baseResult = new BaseResult(BaseResult.RESULT_ERROR, "获取列表失败");

        JSONObject jsonObject = JSONObject.parseObject(param);
        Integer startPage = jsonObject.getInteger("startPage");
        Integer pageSize = jsonObject.getInteger("pageSize");
        String searchKey = jsonObject.getString("keyword");
        String startTime = jsonObject.getString("startTime");
        String endTime = jsonObject.getString("endTime");
        String tradeState = jsonObject.getString("tradeState");


        if (startPage == null)
            startPage = 1;
        if (pageSize == null)
            pageSize = 10;
        if (tradeState != null && tradeState.length() == 0)
            tradeState = null;
        if (searchKey != null && searchKey.length() == 0)
            searchKey = null;

        Integer[] pages = new Integer[1];
        List<model.dto.DeviceBuyRecord> deviceBuyRecords = deviceService.deviceBuyRecord(startPage,
                pageSize, startTime, endTime, searchKey, tradeState, pages);

        baseResult.setErrcode(BaseResult.RESULT_SUCCESS);
        baseResult.setErrmsg("获取列表成功");
        DivideBaseResult<model.dto.DeviceBuyRecord> divideBaseResult = new DivideBaseResult<>();
        divideBaseResult.setPages(pages[0]);
        divideBaseResult.setPage(startPage);
        divideBaseResult.setPageSize(pageSize);
        divideBaseResult.setList(deviceBuyRecords);

        baseResult.setObject(divideBaseResult);

        return baseResult;

    }

    /**
     * 获取代理商报备信息
     *
     * @param param
     * @return
     */
    @RequestMapping("/proxyReports")
    public BaseResult proxyReports(@RequestBody String param) {
        BaseResult baseResult = new BaseResult(BaseResult.RESULT_ERROR, "获取列表失败");

        JSONObject jsonObject = JSONObject.parseObject(param);
        Integer startPage = jsonObject.getInteger("startPage");
        Integer pageSize = jsonObject.getInteger("pageSize");
        Integer rid = jsonObject.getInteger("rid");
        //String startTime = jsonObject.getString("startTime");
        //String endTime = jsonObject.getString("endTime");

        if (startPage == null)
            startPage = 1;
        if (pageSize == null)
            pageSize = 10;
        Integer[] pages = new Integer[1];

        List<ProxyAreaReportLog> proxyAreas = proxyAreaService.proxyReports(rid, startPage, pageSize, pages);

        if (proxyAreas != null) {
            baseResult.setErrcode(BaseResult.RESULT_SUCCESS);
            baseResult.setErrmsg("获取我的报备成功");

            DivideBaseResult<ProxyAreaReportLog> divideBaseResult = new DivideBaseResult<>();
            divideBaseResult.setPages(pages[0]);
            divideBaseResult.setPage(startPage);
            divideBaseResult.setPageSize(pageSize);
            divideBaseResult.setList(proxyAreas);

            baseResult.setObject(divideBaseResult);
        }

        return baseResult;
    }


    /**
     * @param param
     * @return
     * @descr 设备管理页面
     */
    @RequestMapping(value = "deviceManage.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String deviceManage(@RequestBody String param) {
        return supervisorService.deviceManage(param).toJSONString();
    }


    /**
     * @param param
     * @return
     * @descr 模糊搜索运营商账号（设备管理页面）
     */
    @RequestMapping(value = "deviceManageSearch.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String deviceManageSearch(@RequestBody String param) {
        return supervisorService.deviceManageSearch(param).toJSONString();
    }


    /**
     * @param session
     * @param param
     * @return
     * @descr 详情：跳入对应运营商的设备管理页面
     */
    @RequestMapping(value = "deviceInfoDetail.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String deviceInfoDetail(HttpSession session, @RequestBody String param) {
        JSONObject json = JSON.parseObject(param);
        Integer corporationId = json.getInteger("corporationid");
        json = new JSONObject();
        json.put("flag", false);
        if (corporationId != null) {
            session.setAttribute("userId", corporationId);
            json.put("flag", true);
            //return json.toJSONString();
        }
        return json.toJSONString();
    }

    /**
     * @return
     * @descr 未分配设备列表页面
     */
    @RequestMapping(value = "unbindDevManage.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String unbindDevManage(@RequestBody String param) {
        return supervisorService.unbindDevManage(param).toJSONString();
    }


    /**
     * @param param
     * @return
     * @descr 修改未分配设备的备注信息
     */
    @RequestMapping(value = "updateUnbindDevRemark.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String updateUnbindDevRemark(@RequestBody String param) {
        return supervisorService.updateUnbindDevRemark(param).toJSONString();
    }


/**********************************************************************************************************************/


    /**
     * @param param
     * @return
     * @descr 财务管理主页面
     */
    @RequestMapping(value = "financeManage.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String financeManage(@RequestBody String param) {
        return supervisorService.financeManage(param).toJSONString();
    }


    /**
     * @param param
     * @return
     * @descr 模糊搜索运营商账号（财务管理页面）
     */
    @RequestMapping(value = "financeManageSearch.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String financeManageSearch(@RequestBody String param) {
        return supervisorService.financeManageSearch(param).toJSONString();
    }


    /**
     * @param session
     * @param param
     * @return
     * @descr 详情:跳入对应运营商的财务管理页面
     */
    @RequestMapping(value = "accountDetail.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String accountDetail(HttpSession session, @RequestBody String param) {
        JSONObject json = JSON.parseObject(param);
        Integer corporationId = json.getInteger("corporationid");

        json = new JSONObject();
        json.put("flag", false);
        if (corporationId != null) {

            Admin admin = new Admin();
            admin.setId(corporationId);
            session.setAttribute("proxy", admin);
            json.put("flag", true);
        }
        return json.toJSONString();
    }


    /**
     * 清算代理商收益
     *
     * @return
     */
    @RequestMapping(value = "settleProxyIncome.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public BaseResult settleProxyIncome(HttpSession httpSession, @RequestBody String param) throws ParseException {
        BaseResult baseResult = new BaseResult(BaseResult.RESULT_ERROR, "所选时间跨度已在清算队列中，请耐心等待！");
        String sup_account = (String) httpSession.getAttribute(Constant.SESSION_KEY_SUPERVISOR_ACCOUNT);
        if (sup_account == null) {
            return baseResult;
        }

        //事务执行
        JSONObject jsonObject = JSONObject.parseObject(param);
        String startMonth = jsonObject.getString("startMonth");//month of year : 2017-05
        String endMonth = jsonObject.getString("endMonth");
        String utoken = jsonObject.getString("utoken");
        boolean result = supervisorService.settleProxyIncome(utoken, startMonth, endMonth);
        if (result) {
            baseResult.setErrcode(BaseResult.RESULT_SUCCESS);
            baseResult.setErrmsg("执行清算任务已经推送至队列，预计5-10分钟内完成清算");
        }
        return baseResult;
    }


    /**
     * @param param
     * @return
     * @descr 结算中心主页面
     */
    @RequestMapping(value = "settlementCenter.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String settlementCenter(@RequestBody String param) {
        return supervisorService.settlementCenter(param).toJSONString();
    }


    /**
     * @param param
     * @return
     * @descr 结算中心订单查询
     */
    @RequestMapping(value = "settlementSearch.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String settlementSearch(@RequestBody String param) {
        return supervisorService.settlementSearch(param).toJSONString();
    }

    /**
     * @param param
     * @return
     * @descr 申请结算处理(结算中心页面)
     */
    @RequestMapping(value = "financeOrderProcess.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String financeOrderProcess(@RequestBody String param) {
        return supervisorService.financeOrderProcess(param).toJSONString();
    }


    /**
     * @param param
     * @return
     * @descr 结算账号详情(结算中心页面)
     */
    @RequestMapping(value = "settlemnetOrderDetail.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String settlemnetOrderDetail(@RequestBody String param) {
        return supervisorService.settlemnetOrderDetail(param).toJSONString();
    }


    /**
     * 修改代理商区域报备状态
     *
     * @param params
     * @return
     */
    @RequestMapping("/updateReportStatus")
    public BaseResult updateReportStatus(@RequestBody String params) {
        BaseResult baseResult = new BaseResult(BaseResult.RESULT_ERROR, "修改报备状态失败");
        JSONObject jsonObject = JSONObject.parseObject(params);

        Integer id = jsonObject.getInteger("id");
        String status = jsonObject.getString("status");
        if (proxyAreaService.updateReportStatus(id, status)) {
            baseResult.setErrcode(BaseResult.RESULT_SUCCESS);
            baseResult.setErrmsg("修改报备状态成功");
        }

        return baseResult;
    }

    @RequestMapping("/insertDevOrder")
    public BaseResult insertDeviceOrder(@RequestBody String params) throws ParseException {
        BaseResult baseResult = new BaseResult(BaseResult.RESULT_ERROR, "插入设备购买信息失败");
        //
        JSONObject jsonObject = JSONObject.parseObject(params);
        String phone = jsonObject.getString("phone");
        String time = jsonObject.getString("time");
        String deviceType = jsonObject.getString("deviceType");
        Integer deviceAmount = jsonObject.getInteger("amount");
        String address =  jsonObject.getString("address");
        String contact =  jsonObject.getString("contact");
        String consignee =  jsonObject.getString("name");
        Integer unitPrice =  jsonObject.getInteger("unitPrice");
        Integer policyReduct =  jsonObject.getInteger("policyReduct");
        Integer originalPrice =  jsonObject.getInteger("buyPrice");
        String reason = jsonObject.getString("reason");

        if (deviceAmount<=0 || phone==null||consignee==null || time==null)
            return baseResult;

        Integer ruleId = 1;
        if (unitPrice==null)
            unitPrice = 25800;
        if (policyReduct==null)
            policyReduct = 0;
        if(originalPrice==null)
            originalPrice = unitPrice;
        Integer totalPrice = unitPrice * deviceAmount;

        Admin admin = adminService.getAdminByPhone(phone);
        String utoken;
        if (admin!=null){
            utoken = admin.getProxy_token();
        }else{
            utoken = phone;
        }

        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(time);

        DeviceBuyRecord deviceBuyRecord =  deviceService.deviceOrder(utoken, deviceType,
                deviceAmount,
                originalPrice,
                policyReduct,
                unitPrice,
                totalPrice,
                ruleId,
                address, contact, consignee,"N","SYSTEM",date,reason);

        if (deviceBuyRecord!=null){
            //
            baseResult.setErrcode(BaseResult.RESULT_SUCCESS);
            baseResult.setErrmsg("后台添加订单成功");
        }

        return baseResult;
    }


}
