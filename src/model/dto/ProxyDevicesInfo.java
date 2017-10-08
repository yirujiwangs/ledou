package model.dto;

import java.util.Date;
import java.util.Objects;

/**
 * Created by Administrator on 2016/10/6.
 */
public class ProxyDevicesInfo {
    private String iBeaconID;
    private String groupName;
    private String remark;
    private String status;
    private String dtoken;
    private String storeName;
    private String storeAddress;
    private Integer deviceType=0;
    private Integer activatePercent=0;
    private Integer redPercent=0;
    private Date   createTime;


    public ProxyDevicesInfo() {
    }

    public ProxyDevicesInfo(String iBeaconID, String groupName, String remark, String status, String dtoken, String storeName, String storeAddress, Integer deviceType, Date createTime) {
        this.iBeaconID = iBeaconID;
        this.groupName = groupName;
        this.remark = remark;
        this.status = status;
        this.dtoken = dtoken;
        this.storeName = storeName;
        this.storeAddress = storeAddress;
        this.deviceType = deviceType;
        this.createTime = createTime;
    }

    public void setiBeaconID(String iBeaconID) {
        this.iBeaconID = iBeaconID;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getiBeaconID() {
        return iBeaconID;
    }

    public String getGroupName() {
        return groupName;
    }

    public String getRemark() {
        return remark;
    }

    public String getStatus() {
        return status;
    }

    public Date getCreateTime() {
        return createTime;
    }


    public String getDtoken() {
        return dtoken;
    }

    public void setDtoken(String dtoken) {
        this.dtoken = dtoken;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }

    public Integer getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(Integer deviceType) {
        this.deviceType = deviceType;
    }

    public Integer getActivatePercent() {
        return activatePercent;
    }

    public void setActivatePercent(Integer activatePercent) {
        this.activatePercent = activatePercent;
    }

    public Integer getRedPercent() {
        return redPercent;
    }

    public void setRedPercent(Integer redPercent) {
        this.redPercent = redPercent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProxyDevicesInfo that = (ProxyDevicesInfo) o;
        return Objects.equals(iBeaconID, that.iBeaconID) &&
                Objects.equals(groupName, that.groupName) &&
                Objects.equals(remark, that.remark) &&
                Objects.equals(status, that.status) &&
                Objects.equals(createTime, that.createTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iBeaconID, groupName, remark, status, createTime);
    }

    @Override
    public String toString() {
        return "ProxyDevicesInfo{" +
                "iBeaconID='" + iBeaconID + '\'' +
                ", groupName='" + groupName + '\'' +
                ", remark='" + remark + '\'' +
                ", status=" + status +
                ", createTime=" + createTime +
                '}';
    }
}
