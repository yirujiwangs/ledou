package model.dto;

import model.divide.BaseDivideModel;

import java.util.Date;

/**
 * Created by Administrator on 2017/7/28.
 */
public class ProxyBenefitRecord extends BaseDivideModel{
    private String createDate;
    private int yearServiceBenefit;
    private int lodoukeBenefit;
    private int liveCircleBenefit;
    private int lesceneBenefit;
    private int totalBenefit;

    public int getTotalBenefit() {
        return totalBenefit;
    }

    public void setTotalBenefit(int totalBenefit) {
        this.totalBenefit = totalBenefit;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public int getYearServiceBenefit() {
        return yearServiceBenefit;
    }

    public void setYearServiceBenefit(int yearServiceBenefit) {
        this.yearServiceBenefit = yearServiceBenefit;
    }

    public int getLodoukeBenefit() {
        return lodoukeBenefit;
    }

    public void setLodoukeBenefit(int lodoukeBenefit) {
        this.lodoukeBenefit = lodoukeBenefit;
    }

    public int getLiveCircleBenefit() {
        return liveCircleBenefit;
    }

    public void setLiveCircleBenefit(int liveCircleBenefit) {
        this.liveCircleBenefit = liveCircleBenefit;
    }

    public int getLesceneBenefit() {
        return lesceneBenefit;
    }

    public void setLesceneBenefit(int lesceneBenefit) {
        this.lesceneBenefit = lesceneBenefit;
    }
}
