package model.dto;

/**
 * Created by yeran on 2017/5/9.
 */
public class DeviceBuyStatistics {

    private Integer activeDevicesThisMonth;
    private Integer activeDevicesAll;

    private Integer deviceAmountThisMonth;

    private Integer deviceAmountAll;

    private Integer activeDeviceLeft;

    private Integer normalDeviceLeft;

    public DeviceBuyStatistics() {
    }

    public DeviceBuyStatistics(Integer activeDevicesThisMonth, Integer activeDevicesAll, Integer deviceAmountThisMonth, Integer deviceAmountAll, Integer activeDeviceLeft, Integer normalDeviceLeft) {
        this.activeDevicesThisMonth = activeDevicesThisMonth;
        this.activeDevicesAll = activeDevicesAll;
        this.deviceAmountThisMonth = deviceAmountThisMonth;
        this.deviceAmountAll = deviceAmountAll;
        this.activeDeviceLeft = activeDeviceLeft;
        this.normalDeviceLeft = normalDeviceLeft;
    }

    public Integer getActiveDevicesThisMonth() {
        return activeDevicesThisMonth;
    }

    public void setActiveDevicesThisMonth(Integer activeDevicesThisMonth) {
        this.activeDevicesThisMonth = activeDevicesThisMonth;
    }

    public Integer getActiveDevicesAll() {
        return activeDevicesAll;
    }

    public void setActiveDevicesAll(Integer activeDevicesAll) {
        this.activeDevicesAll = activeDevicesAll;
    }

    public Integer getDeviceAmountThisMonth() {
        return deviceAmountThisMonth;
    }

    public void setDeviceAmountThisMonth(Integer deviceAmountThisMonth) {
        this.deviceAmountThisMonth = deviceAmountThisMonth;
    }

    public Integer getDeviceAmountAll() {
        return deviceAmountAll;
    }

    public void setDeviceAmountAll(Integer deviceAmountAll) {
        this.deviceAmountAll = deviceAmountAll;
    }

    public Integer getActiveDeviceLeft() {
        return activeDeviceLeft;
    }

    public void setActiveDeviceLeft(Integer activeDeviceLeft) {
        this.activeDeviceLeft = activeDeviceLeft;
    }

    public Integer getNormalDeviceLeft() {
        return normalDeviceLeft;
    }

    public void setNormalDeviceLeft(Integer normalDeviceLeft) {
        this.normalDeviceLeft = normalDeviceLeft;
    }
}
