package controller.pay;

import model.Admin;
import model.PayOrders;
import model.base.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import service.PayService;
import utils.common.Constant;

import javax.servlet.http.HttpSession;

/**
 * Created by yeran on 2017/5/2.
 */

@RestController
@RequestMapping("/pay")
public class PayOrderController {

    @Autowired
    private PayService payService;

    /**
     *
     * 根据订单号获取支付状态
     *
     * @param outTradeNo
     * @return
     *
     */
    @RequestMapping("/state")
    public BaseResult checkPayState( HttpSession httpSession,@RequestParam(value = "no") String outTradeNo){
        BaseResult baseResult = new BaseResult(BaseResult.RESULT_ERROR, "请求异常");

        Admin admin = (Admin) httpSession.getAttribute(Constant.SESSION_KEY_PROXY);
        if (admin==null||outTradeNo==null)
            return baseResult;

        String utoken = admin.getProxy_token();

        PayOrders payOrders = payService.getOrderByOutTradeNo(outTradeNo, utoken);
        if (payOrders!=null) {
            baseResult.setErrcode(BaseResult.RESULT_SUCCESS);
            if (PayOrders.PAYED.equals(payOrders.getStatus())) {
                baseResult.setErrmsg("OK");
            } else if (PayOrders.PREPAY.equals(payOrders.getStatus())) {
                baseResult.setErrmsg("WAIT");
            } else {
                baseResult.setErrmsg("FAILED");
            }
        }
        return baseResult;
    }
}
