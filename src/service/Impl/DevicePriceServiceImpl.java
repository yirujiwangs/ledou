package service.Impl;

import dao.DevicePriceMapper;
import model.DevicePrice;
import model.DevicePriceExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.DevicePriceService;

import java.util.List;

/**
 * Created by XWL on 2017/8/12.
 */
@Service
public class DevicePriceServiceImpl implements DevicePriceService {

    @Autowired
    private DevicePriceMapper devicePriceMapper;

    @Override
    public int buyPrice(String type) {
        DevicePriceExample devicePriceExample = new DevicePriceExample();
        devicePriceExample.createCriteria().andTypeEqualTo(type);
        List<DevicePrice> devicePrices = devicePriceMapper.selectByExample(devicePriceExample);
        return devicePrices.get(0).getPrice();
    }

    @Override
    public int policyReduct(String type) {
        DevicePriceExample devicePriceExample = new DevicePriceExample();
        devicePriceExample.createCriteria().andTypeEqualTo(type);
        List<DevicePrice> devicePrices = devicePriceMapper.selectByExample(devicePriceExample);
        return devicePrices.get(0).getPrice();
    }


    @Override
    public int specialPolicyReduct(String proxyToken) {
        DevicePriceExample devicePriceExample = new DevicePriceExample();
        devicePriceExample.createCriteria().andProxy_tokenEqualTo(proxyToken);
        List<DevicePrice> devicePrices = devicePriceMapper.selectByExample(devicePriceExample);
        if(devicePrices!=null&&!devicePrices.isEmpty()) {
            return devicePrices.get(0).getPrice();
        }else{
            return -1;
        }
    }
}
