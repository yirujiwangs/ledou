package model.dto;

import java.util.Objects;

/**
 * Created by Administrator on 2016/10/7.
 */
public class DeviceUserInfo {
    private String storeAccount;
    private String storeName;
    private Integer deviceNum;
    private Integer groupNum;
    private String uToken;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeviceUserInfo that = (DeviceUserInfo) o;
        return Objects.equals(storeAccount, that.storeAccount) &&
                Objects.equals(storeName, that.storeName) &&
                Objects.equals(deviceNum, that.deviceNum) &&
                Objects.equals(groupNum, that.groupNum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(storeAccount, storeName, deviceNum, groupNum);
    }

    public String getStoreAccount() {
        return storeAccount;
    }

    public void setStoreAccount(String storeAccount) {
        this.storeAccount = storeAccount;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Integer getDeviceNum() {
        return deviceNum;
    }

    public void setDeviceNum(Integer deviceNum) {
        this.deviceNum = deviceNum;
    }

    public Integer getGroupNum() {
        return groupNum;
    }

    public void setGroupNum(Integer groupNum) {
        this.groupNum = groupNum;
    }

    public String getuToken() {
        return uToken;
    }

    public void setuToken(String uToken) {
        this.uToken = uToken;
    }
}
