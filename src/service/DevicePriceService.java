package service;

/**
 * Created by Administrator on 2017/8/12.
 */
public interface DevicePriceService {
    int buyPrice(String type);

    int policyReduct(String type);

    int specialPolicyReduct(String proxyToken);
}
