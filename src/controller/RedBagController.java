package controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import model.Admin;
import model.AdvOrderRecord;
import model.base.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import service.AdvService;
import utils.api.AdBaseAPI;
import utils.api.AdvPayClient;
import utils.finance.FinanceUtil;
import utils.net.RedirectUtil;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * Created by XWL on 2017/8/2.
 */
@Controller
@RequestMapping("/red")
public class RedBagController {

    @Autowired
    AdvService advService;


    /**
     * 吸粉红包（红包列表）
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "powderRedList.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public
    @ResponseBody
    String tpowderRedLis(HttpSession session,@RequestBody String param) {
        Admin admin = (Admin) session.getAttribute("proxy");

        String pathurl = "red/powderRedList";

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
        return JSON.toJSONString(new BaseResult(-1, "吸粉红包（红包列表）获取失败"));
    }

    /**
     * 吸粉红包（详情）
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "powderRedDetail.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public
    @ResponseBody
    String powderRedDetail(HttpSession session,@RequestBody String param) {
        Admin admin = (Admin) session.getAttribute("proxy");

        String pathurl = "red/powderRedDetail";

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
        return JSON.toJSONString(new BaseResult(-1, "吸粉红包（详情）获取失败"));
    }

    /**
     * 吸粉红包（操作）
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "updatePowderRedStatus.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public
    @ResponseBody
    String updatePowderRedStatus(HttpSession session,@RequestBody String param) {
        Admin admin = (Admin) session.getAttribute("proxy");

        String pathurl = "red/updatePowderRedStatus";

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
        return JSON.toJSONString(new BaseResult(-1, "吸粉红包（操作）失败"));
    }

    /**
     * 吸粉红包（公众号）
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "userlist.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public
    @ResponseBody
    String userlist(HttpSession session,@RequestBody String param) {
        Admin admin = (Admin) session.getAttribute("proxy");

        String pathurl = "red/userlist";

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
        return JSON.toJSONString(new BaseResult(-1, "吸粉红包（公众号）获取失败"));
    }

    /**
     * 吸粉红包（授权列表）
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "publicnum.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public
    @ResponseBody
    String publicnum(HttpSession session,@RequestBody String param) {
        Admin admin = (Admin) session.getAttribute("proxy");

        String pathurl = "red/publicnum";

        if (admin != null) {
            String proxyToken = admin.getProxy_token();
            String ptoken = admin.getPtoken();

            if (proxyToken != null) {
                JSONObject jsonObject = JSON.parseObject(param);
                jsonObject.put("proxyToken",proxyToken);
                String res = AdBaseAPI.executeResult(ptoken, jsonObject.toJSONString(), pathurl);
                if (res != null && !res.isEmpty()){
                    JSONObject object = JSON.parseObject(res);
                    object.put("proxyToken",proxyToken);
                    return JSON.toJSONString(object);
                }
            }
        }
        return JSON.toJSONString(new BaseResult(-1, "吸粉红包（授权列表）获取失败"));
    }

    /**
     * 吸粉红包（投放区域设备分组）
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "deviceGroup.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public
    @ResponseBody
    String deviceGroup(HttpSession session,@RequestBody String param) {
        Admin admin = (Admin) session.getAttribute("proxy");

        String pathurl = "red/deviceGroup";

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
        return JSON.toJSONString(new BaseResult(-1, "吸粉红包（投放区域设备分组）获取失败"));
    }

    /**
     * 吸粉红包（提交）
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "addPowderRed.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String addPowderRed(HttpSession session,RedirectAttributes redirectAttributes,@RequestBody String param) {
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
                String json = org.apache.commons.lang.StringUtils.substringBefore(substringAfter, "&");
                JSONObject jsonObject = JSON.parseObject(json);
                jsonObject.put("proxyName",proxyName);
                jsonObject.put("proxyToken",proxyToken);

                String preFee = jsonObject.getString("preFee");
                String res;

                String pathUrl = "red/editPowderRed";
                res = AdBaseAPI.executeResult(ptoken, jsonObject.toJSONString(), pathUrl);

                try {
                    if (res == null||res.isEmpty()) {
                        return "web/views/400";
                    }
                    JSONObject result = JSONObject.parseObject(res);
                    if(result.getInteger("error")==-1){
                        return "web/views/400";
                    }
                    String advUuid = result.getJSONObject("object").getString("serialNum");
                    Integer totalFee = result.getJSONObject("object").getInteger("totalFee");
                    if (advUuid != null && !advUuid.isEmpty()) {
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
                                String info = orderRecord.getId() + "#" + advUuid +"#"+"fans";
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
     * 吸粉红包（编辑）
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "editPowderRed.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public
    @ResponseBody
    String editPowderRed(HttpSession session,@RequestBody String param) {
        Admin admin = (Admin) session.getAttribute("proxy");

        String pathurl = "red/editPowderRed";

        if (admin != null) {
            String proxyToken = admin.getProxy_token();
            String ptoken = admin.getPtoken();
            String proxyName = admin.getUsername();

            if (proxyToken != null) {
                JSONObject jsonObject = JSON.parseObject(param);
                jsonObject.put("proxyToken",proxyToken);
                jsonObject.put("proxyName",proxyName);
                String res = AdBaseAPI.executeResult(ptoken, jsonObject.toJSONString(), pathurl);
                if (res != null && !res.isEmpty()){
                    return res;
                }
            }
        }
        return JSON.toJSONString(new BaseResult(-1, "吸粉红包（计费模板）获取失败"));
    }

    /**
     * 吸粉红包（计费模板）
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "getWithdrawls.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public
    @ResponseBody
    String getWithdrawls(HttpSession session,@RequestBody String param) {
        Admin admin = (Admin) session.getAttribute("proxy");

        String pathurl = "red/getWithdrawls";

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
        return JSON.toJSONString(new BaseResult(-1, "吸粉红包（计费模板）获取失败"));
    }


    /**
     * 吸粉红包（公众号下拉列表）
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "publicmsg.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public
    @ResponseBody
    String publicmsg(HttpSession session,@RequestBody String param) {
        Admin admin = (Admin) session.getAttribute("proxy");

        String pathurl = "red/publicmsg";

        if (admin != null) {
            String proxyToken = admin.getProxy_token();
            String ptoken = admin.getPtoken();

            if (proxyToken != null) {
                JSONObject jsonObject = JSON.parseObject(param);
                jsonObject.put("proxyToken", proxyToken);
                String res = AdBaseAPI.executeResult(ptoken, jsonObject.toJSONString(), pathurl);
                if (res != null && !res.isEmpty()){
                    return res;
                }
            }
        }
        return JSON.toJSONString(new BaseResult(-1, "吸粉红包（公众号下拉列表）获取失败"));
    }


    /**
     * 吸粉红包取消授权
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "authorize.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public
    @ResponseBody
    String authorize(HttpSession session,@RequestBody String param) {
        Admin admin = (Admin) session.getAttribute("proxy");

        String pathurl = "red/authorize";

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
        return JSON.toJSONString(new BaseResult(-1, "吸粉红包取消授权失败"));
    }


}
