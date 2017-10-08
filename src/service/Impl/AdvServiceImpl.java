package service.Impl;

import com.alibaba.fastjson.JSON;
import utils.api.AdBaseResult;
import utils.api.AdBaseAPI;
import com.alibaba.fastjson.JSONObject;
import dao.AdvOrderRecordMapper;
import model.AdvOrderRecord;
import model.AdvOrderRecordExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.AdvService;
import utils.common.LogUtil;
import utils.finance.Alipay;
import utils.finance.AlipayNotify;
import utils.finance.FinanceUtil;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/5.
 */

@Service
public class AdvServiceImpl implements AdvService {

    @Autowired
    private AdvOrderRecordMapper advOrderRecordMapper;


    @Override
    public AdvOrderRecord addAdvOrder(AdvOrderRecord advOrderRecord) {
        try {
            AdvOrderRecordExample advOrderRecordExample = new AdvOrderRecordExample();
            advOrderRecordExample.createCriteria().andAdvUuidEqualTo(advOrderRecord.getAdvUuid())
                    .andTradeStatusEqualTo(AdvOrderRecord.TRADE_STATUS_WAITTING);

            List<AdvOrderRecord> advOrderRecords = advOrderRecordMapper.selectByExample(advOrderRecordExample);
            //System.out.println(JSON.toJSONString(advOrderRecords));
            if (advOrderRecords == null || advOrderRecords.size() == 0) {
                String out_trade_no = FinanceUtil.outTradeNo();
                advOrderRecord.setOurTradeNo(out_trade_no);
                //System.out.println(JSONObject.toJSONString(advOrderRecord));
                int id = advOrderRecordMapper.insertSelective(advOrderRecord);
                advOrderRecord.setId(id);
                return advOrderRecord;
            } else {
                AdvOrderRecord orderRecord = advOrderRecords.get(0);
                advOrderRecord.setOurTradeNo(orderRecord.getOurTradeNo());
                return orderRecord;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 支付回调，如果支付成功，向广告平台发送上架事件,同时将支付金额发给广告平台，广告平台增加充值记录
     *
     * @param req
     * @return
     */
    @Override
    public String advPayNotify(HttpServletRequest req) {

        String param = req.getParameter("extra_common_param");
        /*代理商token*/
        String customerParam = "";

        System.out.println("extra_common_param: " + param);
        String[] str = param.split("#");

        if (str.length >= 3) {
            String advUuid = str[1];
            String type = str[2];
            //System.out.println("advUuid" + advUuid);
            boolean payStatus = insertRecord(req, advUuid,customerParam);
            if (payStatus) {
                Double fee = FinanceUtil.Yuan2Fen(Double.parseDouble(req
                        .getParameter("total_fee")));
                JSONObject json = new JSONObject();

                AdvOrderRecordExample advOrderRecordExample = new AdvOrderRecordExample();
                advOrderRecordExample.createCriteria().andAdvUuidEqualTo(advUuid);
                String proxyToken = "";
                List<AdvOrderRecord> advOrderRecordList = advOrderRecordMapper.selectByExample(advOrderRecordExample);
                if(advOrderRecordList!=null&&!advOrderRecordList.isEmpty()){
                    proxyToken = advOrderRecordList.get(0).getProxyToken();
                }
                json.put("proxyToken",proxyToken);
                json.put("advUUID", advUuid);
                json.put("price", fee);
                json.put("status", AdvOrderRecord.AD_STATE_PAYED);
                json.put("type",type);

                //更新广告平台的广告投放信息
                String result = AdBaseAPI.executeResult(customerParam, json.toJSONString(), "advertise/updatePayStatus");
                if (result != null && !result.isEmpty()) {
                    AdBaseResult adBaseResult = JSONObject.parseObject(result, AdBaseResult.class);
                    if (adBaseResult.success()) {
                        LogUtil.log(AdvServiceImpl.class, LogUtil.LogType.SUCCESS, "更新广告状态成功-uuid" + advUuid);
                    }
                }
            }
        }
        return "success";
    }

    @Override
    public Integer getPayStatus(String advUUID) {
        AdvOrderRecordExample advOrderRecordExample = new AdvOrderRecordExample();
        advOrderRecordExample.createCriteria().andAdvUuidEqualTo(advUUID);

        return advOrderRecordMapper.selectByExample(advOrderRecordExample).get(0).getTradeStatus();
    }

    @Override
    public boolean insertRecord(HttpServletRequest req, String advUuid,String proxy_token) {

        // 新建充值记录
        AdvOrderRecordExample advOrderRecordExample = new AdvOrderRecordExample();
        advOrderRecordExample.createCriteria().andAdvUuidEqualTo(advUuid);
                //.andProxyTokenEqualTo(proxy_token);
        AdvOrderRecord advOrderRecord = advOrderRecordMapper.selectByExample(advOrderRecordExample).get(0);

        if (advOrderRecord.getTradeStatus() == 1) {
            return false;
        }

        Map<String, String> toGet = new HashMap<>();
        Map<String, String[]> paraAli = req.getParameterMap();
        for (Iterator<String> it = paraAli.keySet().iterator(); it.hasNext(); ) {
            String name = it.next();
            String[] values = paraAli.get(name);
            toGet.put(name, values[0]);
        }
        String trade_no = req.getParameter("trade_no");// 支付宝交易流水号
        String out_trade_no = req.getParameter("out_trade_no"); // 我司交易订单号
        String trade_status = req.getParameter("trade_status");
        String seller = req.getParameter("seller_id");
        String buyer_email = req.getParameter("buyer_email");

        // 总订单钱数，单位元，转化为分
        Double fee = FinanceUtil.Yuan2Fen(Double.parseDouble(req
                .getParameter("total_fee")));
        // 返回参数中取交易创建时间
        String date_time = req.getParameter("gmt_create");
        Timestamp stamp = Timestamp.valueOf(date_time);
        String remark = req.getParameter("body"); // 备注字段
        String cause = ""; // 失败或成功的原因

        advOrderRecord.setTradetime(stamp);
        advOrderRecord.setOurTradeNo(out_trade_no);
        advOrderRecord.setAlipayTradeNo(trade_no);
        advOrderRecord.setRemark(remark);
        advOrderRecord.setAdvFee(fee);
        advOrderRecord.setAlipayAccountNum(buyer_email);

        // 验证是支付宝
        if (AlipayNotify.verify(toGet) && seller.equals(Alipay.seller_id)) {
            if (trade_status.equals("TRADE_SUCCESS")
                    || trade_status.equals("TRADE_FINISHED")) {
                try {
                    advOrderRecord.setCause(cause);
                    advOrderRecord.setTradeStatus(1);
                    advOrderRecordMapper.updateByPrimaryKey(advOrderRecord);

                    LogUtil.log(AdvServiceImpl.class, LogUtil.LogType.SUCCESS
                            , "代理商支付成功#" + "代理商token:"
                            + advOrderRecord.getProxyToken()
                            + "订单号："
                            + advOrderRecord.getOurTradeNo());
                    return true;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        cause = "支付宝支付未完成";
        advOrderRecord.setCause(cause);
        advOrderRecord.setTradeStatus(2);
        advOrderRecordMapper.updateByPrimaryKey(advOrderRecord);
        LogUtil.log(AdminServiceImpl.class, LogUtil.LogType.ERROR, "代理商支付失败#" + "代理商token:" + advOrderRecord.getProxyToken() + "订单号：" + advOrderRecord.getOurTradeNo());
        return false;
    }



}



