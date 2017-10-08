package controller;

import com.alibaba.fastjson.JSONObject;
import model.Admin;
import model.base.BaseResult;
import model.base.DivideBaseResult;
import model.dto.DealerDeviceNumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.DealerService;
import utils.common.Constant;

import javax.servlet.http.HttpSession;

/**
 * Created by yeran on 2017/2/22.
 * <p/>
 * 拓展服务商控制器
 */
@RestController
@RequestMapping("/dealer")
public class DealerController {

    @Autowired
    DealerService dealerService;

    /**
     * 获取经销商列表
     * 请求方式为GET
     * 获取数据格式为JSON
     *
     * @param httpSession
     * @return BaseResult
     */
    @RequestMapping(name = "/", method = RequestMethod.GET, produces = "application/json")
    public BaseResult dealerList(HttpSession httpSession
            , @RequestParam(value = "page", defaultValue = "1") Integer page
            , @RequestParam(value = "pageSize", defaultValue = "15") Integer pageSize) {
        BaseResult baseResult = new BaseResult();
        Admin admin = (Admin) httpSession.getAttribute(Constant.SESSION_KEY_PROXY);
        String phone = admin.getPhoneNum();
        DivideBaseResult<?> dealers = dealerService.dealerList(phone, page, pageSize);

        if (dealers != null) {
            baseResult.setErrcode(BaseResult.RESULT_SUCCESS);
            baseResult.setErrmsg("获取经销商列表成功");
            baseResult.setObject(dealers);

        }

        return baseResult;

    }

    /**
     * 获取拓展设备信息
     * @param httpSession
     * @return
     */
    @RequestMapping("/device/statistics")
    public BaseResult dealerDeviceStatistics(HttpSession httpSession){
        Admin admin = (Admin) httpSession.getAttribute(Constant.SESSION_KEY_PROXY);
        String phone = admin.getPhoneNum();
        String utoken = admin.getProxy_token();
        DealerDeviceNumer dealerDeviceNumer = dealerService.dealerDeviceStatistics(phone, utoken);

        return new BaseResult(BaseResult.RESULT_SUCCESS,"获取拓展设备信息成功",dealerDeviceNumer);
    }

    /**
     * 获取代理商列表
     * @param httpSession
     * @param param
     * @return
     */
    @RequestMapping("/device/list")
    public String dealerDeviceList(HttpSession httpSession,@RequestBody String param){
        BaseResult baseResult = new BaseResult(BaseResult.RESULT_ERROR, "获取列表失败");

        Admin admin = (Admin) httpSession.getAttribute(Constant.SESSION_KEY_PROXY);
        String phone = admin.getPhoneNum();
        String utoken = admin.getProxy_token();

        JSONObject jsonObject = JSONObject.parseObject(param);
        Integer startPage = jsonObject.getInteger("startPage");
        Integer pageSize = jsonObject.getInteger("pageSize");

        DivideBaseResult<?> divideBaseResult = dealerService.dealerDeviceList(phone, utoken, startPage, pageSize);

        baseResult.setErrcode(BaseResult.RESULT_SUCCESS);
        baseResult.setErrmsg("获取下级代理商成功");
        baseResult.setObject(divideBaseResult);

        return JSONObject.toJSONString(baseResult);
    }


    /**
     *
     *
     * 下级代理商采购记录
     *
     * @param httpSession
     * @param params
     * @return
     */
    @RequestMapping("/device/record")
    public String  dealerDeviceBuyRecord(HttpSession httpSession,@RequestBody String params){
        BaseResult baseResult = new BaseResult(BaseResult.RESULT_ERROR, "获取列表失败");

        Admin admin = (Admin) httpSession.getAttribute(Constant.SESSION_KEY_PROXY);
        String phone = admin.getPhoneNum();
        String utoken = admin.getProxy_token();

        JSONObject jsonObject = JSONObject.parseObject(params);
        Integer startPage = jsonObject.getInteger("startPage");
        Integer pageSize = jsonObject.getInteger("pageSize");
        Integer type = jsonObject.getInteger("type");

        //System.out.println(startPage+"-"+type);
        DivideBaseResult<?> divideBaseResult = dealerService.dealerDeviceRecord(phone, utoken, type, startPage, pageSize);

        baseResult.setErrcode(BaseResult.RESULT_SUCCESS);
        baseResult.setErrmsg("获取下级代理商成功");
        baseResult.setObject(divideBaseResult);

        return JSONObject.toJSONString(baseResult);
    }


}
