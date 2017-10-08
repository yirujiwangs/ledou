package service;

import com.alibaba.fastjson.JSONObject;

import java.text.ParseException;

/**
 * Created by Administrator on 2016/8/15.
 */
public interface SupervisorService {

    JSONObject overview(String param);

    JSONObject tendencyChart(String param);

    JSONObject accountManage(String param);

    JSONObject accountManageSearch(String param);

    JSONObject insertAccount(String param);

    JSONObject updateRemark(String param);

    JSONObject accountListByStatus(String param);

    JSONObject accountOrderList(String param);

    JSONObject accountOrderSearch(String param);

    JSONObject deviceManage(String param);

    JSONObject deviceManageSearch(String param);

    JSONObject unbindDevManage(String param);

    JSONObject updateUnbindDevRemark(String param);

    JSONObject financeManage(String param);

    JSONObject financeManageSearch(String param);

    JSONObject settlementCenter(String param);

    JSONObject settlementSearch(String param);

    JSONObject financeOrderProcess(String param);

    JSONObject settlemnetOrderDetail(String param);


    /**
     * 发出清算代理商收益请求（默认上月）
     * @param utoken
     * @param startMonth
     * @param endMonth
     * @return
     * @throws ParseException
     */
    boolean settleProxyIncome(String utoken,String startMonth,String endMonth) throws ParseException;

    /**
     *
     * 处理清算代理商收益
     *
     * @param utoken
     * @param stime
     * @param etime
     * @return
     */
    boolean handleSettleProxyIncome(Integer id,String utoken,Long stime,Long etime);

}
