package model.dto;

import model.Admin;

/**
 * Created by yeran on 2017/5/3.
 */
public class DealerDeviceStatistics extends Admin{

    private String recommend_type;

    private int lastAmount;

    private int nowAmount;

    private int onceAmount;

    public int getLastAmount() {
        return lastAmount;
    }

    public void setLastAmount(int lastAmount) {
        this.lastAmount = lastAmount;
    }

    public int getNowAmount() {
        return nowAmount;
    }

    public void setNowAmount(int nowAmount) {
        this.nowAmount = nowAmount;
    }

    public int getOnceAmount() {
        return onceAmount;
    }

    public void setOnceAmount(int onceAmount) {
        this.onceAmount = onceAmount;
    }

    public String getRecommend_type() {
        return recommend_type;
    }

    public void setRecommend_type(String recommend_type) {
        this.recommend_type = recommend_type;
    }
}
