package controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import model.Admin;
import model.base.BaseResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import utils.api.AdBaseAPI;

import javax.servlet.http.HttpSession;

/**
 * Created by XWL on 2017/7/26.
 */
@Controller
@RequestMapping("/manageMerchant")
public class ManageMerchantController {

    /**
     * 商户关键指标
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "userKPI.do", produces = "application/json;charset=UTF-8")
    public
    @ResponseBody
    String userKPI(HttpSession session) {
        Admin admin = (Admin) session.getAttribute("proxy");

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
                    return JSON.toJSONString(new BaseResult(1,"商户关键指标获取成功",data));
                }
            }
        }
        return JSON.toJSONString(new BaseResult(-1, "商户关键指标获取失败"));
    }

    /**
     * 商户门店数据列表
     *
     * @param session
     * @param param
     * @return
     */
    @RequestMapping(value = "userList.do", produces = "application/json;charset=UTF-8")
    public
    @ResponseBody
    String userList(HttpSession session, @RequestBody String param) {
        Admin admin = (Admin) session.getAttribute("proxy");

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
                    return JSON.toJSONString(new BaseResult(1,"商户门店列表获取成功",data));
                }
            }
        }
        return JSON.toJSONString(new BaseResult(-1, "商户门店列表获取失败"));
    }

    /**
     * 商户门店证书审核
     *
     * @param session
     * @param param
     * @return
     */
    @RequestMapping(value = "userCerCheck.do", produces = "application/json;charset=UTF-8")
    public
    @ResponseBody
    String userCerCheck(HttpSession session, @RequestBody String param) {
        Admin admin = (Admin) session.getAttribute("proxy");

        String pathUrl = "device/submitCheckCer";

        if (admin != null) {
            String proxy_token = admin.getProxy_token();
            String ptoken = admin.getPtoken();
            if (proxy_token != null) {
                JSONObject jsonObject = JSON.parseObject(param);
                jsonObject.put("proxy_token", proxy_token);

                String res = AdBaseAPI.executeResult(ptoken, jsonObject.toJSONString(), pathUrl);
                jsonObject.clear();
                jsonObject = JSON.parseObject(res);
                Integer data = jsonObject.getInteger("error");
                data = (data==0)?1:-1;
                String message = jsonObject.getString("error_reason");


                if (res != null && !res.isEmpty()) {
                    return JSON.toJSONString(new BaseResult(data, message));
                }
            }
        }
        return JSON.toJSONString(new BaseResult(-1, "商户门店证书审核失败"));
    }

}
