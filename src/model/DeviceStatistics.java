package model;

/**
 * Created by yeran on 2016/8/17.
 */
public class DeviceStatistics {

    private int totalNum;
    private int usingNum;
    private int unUsingNum;
    private int groupNum;


    public DeviceStatistics() {
    }

    public DeviceStatistics(int totalNum, int usingNum, int unUsingNum, int groupNum) {
        this.totalNum = totalNum;
        this.usingNum = usingNum;
        this.unUsingNum = unUsingNum;
        this.groupNum = groupNum;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public int getUsingNum() {
        return usingNum;
    }

    public void setUsingNum(int usingNum) {
        this.usingNum = usingNum;
    }

    public int getUnUsingNum() {
        return unUsingNum;
    }

    public void setUnUsingNum(int unUsingNum) {
        this.unUsingNum = unUsingNum;
    }

    public int getGroupNum() {
        return groupNum;
    }

    public void setGroupNum(int groupNum) {
        this.groupNum = groupNum;
    }
}
