package service;

import model.Logistics;

/**
 * Created by yeran on 2017/4/28.
 *
 * 物流服务
 *
 */
public interface LogisticsService {


    /**
     *
     * 获取物流信息
     *
     * @param no
     *@return
     *
     */
    Logistics getNowLogisticByNo(String no);

    boolean updateLogisticInfo(Logistics logistics);

    boolean insertLogistic(Logistics logistics);
}
