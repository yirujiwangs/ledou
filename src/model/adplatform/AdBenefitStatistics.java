package model.adplatform;

/**
 * Created by yeran on 2016/10/22.
 *
 * ���ƽ̨��������
 */
public class AdBenefitStatistics {

    private int todayBenefit =0;

    private int monthBenefit=0;

    private int totalBenefit=0;

    public AdBenefitStatistics(int todayBenefit, int monthBenefit, int totalBenefit) {
        this.todayBenefit = todayBenefit;
        this.monthBenefit = monthBenefit;
        this.totalBenefit = totalBenefit;
    }

    public int getTodayBenefit() {
        return todayBenefit;
    }

    public void setTodayBenefit(int todayBenefit) {
        this.todayBenefit = todayBenefit;
    }

    public int getMonthBenefit() {
        return monthBenefit;
    }

    public void setMonthBenefit(int monthBenefit) {
        this.monthBenefit = monthBenefit;
    }

    public int getTotalBenefit() {
        return totalBenefit;
    }

    public void setTotalBenefit(int totalBenefit) {
        this.totalBenefit = totalBenefit;
    }
}
