package model.dto;

/**
 * Created by Administrator on 2017/8/28.
 */
public class DistBuyRecord {
    private String times;
    private int distDeviceBuyNum;
    private int distDeviceBuyBenefit;

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public int getDistDeviceBuyNum() {
        return distDeviceBuyNum;
    }

    public void setDistDeviceBuyNum(int distDeviceBuyNum) {
        this.distDeviceBuyNum = distDeviceBuyNum;
    }

    public int getDistDeviceBuyBenefit() {
        return distDeviceBuyBenefit;
    }

    public void setDistDeviceBuyBenefit(int distDeviceBuyBenefit) {
        this.distDeviceBuyBenefit = distDeviceBuyBenefit;
    }
}
