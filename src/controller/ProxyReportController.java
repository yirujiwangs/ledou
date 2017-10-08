package controller;

import com.alibaba.fastjson.JSONObject;
import model.Admin;
import model.ProxyAreaReport;
import model.ProxyAreaReportLog;
import model.base.BaseResult;
import model.base.DivideBaseResult;
import model.base.ProxyArea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import service.ProxyAreaService;
import utils.common.Constant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by yeran on 2017/5/2.
 */

@RequestMapping("/proxy/area")
@RestController
public class ProxyReportController {

    @Autowired
    private ProxyAreaService proxyAreaService;

    /**
     *
     * 获取报备信息
     *
     * @param params
     * @return
     */
    @RequestMapping("/reports")
    public BaseResult reports(@RequestBody String params){

        BaseResult baseResult = new BaseResult(BaseResult.RESULT_ERROR,"获取报备信息失败");
        JSONObject jsonObject = JSONObject.parseObject(params);

        Integer cid = jsonObject.getInteger("cid");
        Integer startPage = jsonObject.getInteger("startPage");
        Integer pageSize = jsonObject.getInteger("pageSize");

        Integer[] pages = new Integer[1];
        List<ProxyAreaReport> proxyAreaReports =  proxyAreaService.reports(startPage, pageSize, cid, pages);
        if (proxyAreaReports!=null) {
            baseResult.setErrcode(BaseResult.RESULT_SUCCESS);
            baseResult.setErrmsg("获取报备区域成功");

            DivideBaseResult<ProxyAreaReport> divideBaseResult = new DivideBaseResult<>();
            divideBaseResult.setPages(pages[0]);
            divideBaseResult.setPage(startPage);
            divideBaseResult.setPageSize(pageSize);
            divideBaseResult.setList(proxyAreaReports);

            baseResult.setObject(divideBaseResult);
        }

        return baseResult;
    }

    @RequestMapping("/report")
    public BaseResult report(HttpSession httpSession,@RequestBody String params) throws UnsupportedEncodingException {

        BaseResult baseResult = new BaseResult(BaseResult.RESULT_ERROR, "请求异常");
        JSONObject jsonObject = JSONObject.parseObject(params);

        Integer rid = jsonObject.getInteger("rid");
        String company = jsonObject.getString("company");
        String contact_name = jsonObject.getString("contact_name");
        String contact_way = jsonObject.getString("contact_way");
        String comment = jsonObject.getString("comment");

        Admin admin = (Admin) httpSession.getAttribute(Constant.SESSION_KEY_PROXY);
        if (admin==null||rid==null)
            return baseResult;

        String utoken = admin.getProxy_token();

        if(proxyAreaService.report(utoken,rid,company,contact_name,contact_way,comment)){
            baseResult.setErrcode(BaseResult.RESULT_SUCCESS);
            baseResult.setErrmsg("报备成功");
        }

        return baseResult;
    }


    @RequestMapping("/myReports")
    public BaseResult myReports(HttpSession httpSession,@RequestBody String params){
        BaseResult baseResult = new BaseResult(BaseResult.RESULT_ERROR,"获取我的报备失败");
        Admin admin = (Admin) httpSession.getAttribute(Constant.SESSION_KEY_PROXY);
        JSONObject jsonObject = JSONObject.parseObject(params);

        Integer startPage = jsonObject.getInteger("startPage");
        Integer pageSize = jsonObject.getInteger("pageSize");

        String utoken = admin.getProxy_token();

        Integer[] pages = new Integer[1];

        List<ProxyAreaReportLog> proxyAreas = proxyAreaService.myReports(utoken, startPage, pageSize, pages);

        if (proxyAreas!=null) {
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

}
