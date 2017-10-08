package service.Impl;

import dao.DeliveryAddressMapper;
import model.DeliveryAddress;
import model.DeliveryAddressExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.DeliveryService;

import java.util.List;

/**
 * Created by yeran on 2017/4/28.
 */

@Service
public class DeliveryServiceImpl implements DeliveryService {

    @Autowired
    DeliveryAddressMapper deliveryAddressMapper;

    @Override
    public DeliveryAddress getDeliveryByDid(Integer dId, String account) {
        if (dId==null || account==null)
            return null;

        DeliveryAddressExample deliveryAddressExample = new DeliveryAddressExample();
        deliveryAddressExample.createCriteria().andD_idEqualTo(dId).andAccountEqualTo(account);
        List<DeliveryAddress> deliveryAddresses =  deliveryAddressMapper.selectByExample(deliveryAddressExample);

        if (deliveryAddresses!=null && deliveryAddresses.size()>0){
            return deliveryAddresses.get(0);
        }

        return null;
    }
}
