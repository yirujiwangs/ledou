package service;

import model.DeliveryAddress;

/**
 *
 * Created by yeran on 2017/4/28.
 *
 * 收货地址服务
 *
 */
public interface DeliveryService {

    /**
     *
     * 根据dId和account获取收货信息
     *
     * @param dId
     * @param account
     *
     * @return
     *
     */
    DeliveryAddress getDeliveryByDid(Integer dId,String account);

}
