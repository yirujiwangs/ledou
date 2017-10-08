package api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import model.Admin;

import model.base.ProxyArea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.AdminService;
import service.AdvService;
import service.ProxyAreaService;
import utils.common.StrUtil;

import java.util.List;

/**
 * Created by yeran on 2016/10/4.
 *
 * 对外提供的代理商信息控制器
 *                    查询下属所有代理商基本信息
 *
 *
 */
@Controller
@RequestMapping("/api/proxy")
public class ProxyController {

    @Autowired
    AdminService adminService;

    @Autowired
    AdvService advService;

    @Autowired
    ProxyAreaService proxyAreaService;

    @RequestMapping(value = "/get/{ptoken}/info")
   public @ResponseBody String getProxyInfo(@PathVariable String  ptoken){
        JSONObject json = new JSONObject();
        List<Admin> admins = adminService.getAllProxy();
        json.put("proxyList",admins);
       return json.toJSONString();
   }

    @RequestMapping(value = "/get/{ptoken}/{proxy_token}/info")
    public @ResponseBody String getCertainProxyInfo(@PathVariable String  ptoken,@PathVariable String  proxy_token){
        JSONObject json = new JSONObject();
        Admin admin = adminService.getProxy(proxy_token);
        if(admin.getId()>0){
            json.put("proxy",admin);
            return json.toJSONString();
        }

        json.put("proxy","null");
        return null;
    }

    @RequestMapping(value = "/update/{ptoken}/{proxy_token}/info")
    public @ResponseBody String updateProxyInfo(@PathVariable String  ptoken,@PathVariable String  proxy_token,
                                                @RequestBody String param){

        return null;
    }


    @RequestMapping(value = "/get/{ptoken}/{proxy_token}/payStatus")
    public @ResponseBody String getPayStatus(@PathVariable String  ptoken,@PathVariable String  proxy_token,
                                                @RequestBody String param){

        JSONObject jsonObject = JSON.parseObject(param);
        String advUUID = jsonObject.getString("advUUID");
        Integer status = advService.getPayStatus(advUUID);
        if(status != null && status== 1){
            jsonObject.put("flag", "true");


        }
        else {
            jsonObject.put("flag","false");
        }
        return jsonObject.toJSONString();
    }

    /**
     * 代理商等级、政策信息
     * @param ptoken
     * @param proxyToken
     * @param param
     * @return
     */
    @RequestMapping(value = "/get/{ptoken}/{proxyToken}/proxyLevel")
    public @ResponseBody String getProxyLevel(@PathVariable String  ptoken,@PathVariable String  proxyToken,
                                             @RequestBody String param){

        JSONObject jsonObject = JSON.parseObject(param);
        Admin admin = adminService.getAdminByProxyToken(proxyToken);
        jsonObject.clear();
        jsonObject.put("flag", "false");
        if(admin!=null) {
            jsonObject.put("flag","true");
            jsonObject.put("proxyLevel", admin.getLevel());
            jsonObject.put("policy", admin.getPolicy());

            ProxyArea proxyArea = proxyAreaService.proxyAreaByUtoken(proxyToken);

            if(proxyArea !=null) {

                String upProxyToken = proxyArea.getSupertoken();
                if((admin.getLevel() == 1) && (StrUtil.isEmpty(upProxyToken))){//市级代理，上级代理是其本身
                    upProxyToken = proxyToken;
                }
                jsonObject.put("upProxyToken",upProxyToken);
            }
            jsonObject.put("time", System.currentTimeMillis()/1000);
        }else {
            jsonObject.put("flag", "false");
        }

        return jsonObject.toJSONString();
    }




}
