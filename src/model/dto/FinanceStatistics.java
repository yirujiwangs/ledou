package model.dto;

/**
 * Created by yeran on 2017/5/4.
 */
public class FinanceStatistics {

    /**
     * 组合采购数量
     */
    private int combinationDeviceNum=0;

    /**
     * 间接采购数量
     */
    private int indirectDeviceNum=0;

    /**
     * 直接推荐广告金额
     */
    private int directAdPrice=0;

    /**
     * 自有设备广告金额
     */
    private int selfAdPrice=0;

    /**
     * 组合采购奖励收益
     */
    private int combinationDeviceBenefit=0;

    /**
     * 间接推荐采购奖励收益
     */
    private int indirectDeviceBenefit=0;

    /**
     * 直接推荐广告分成收益
     */
    private int directAdBenefit=0;

    /**
     * 自有采购设备广告分成收益
     */
    private int selfAdBenefit=0;


    /**
     *
     * 组合广告金额
     */
    private int combinationAdPrice =0;

    /**
     *
     * 组合广告收益
     */
    private int combinationAdBenefit =0;

    /**
     *
     * 商户充值广告金
     */
    private int storeAdDepositPrice =0;

    /**
     *
     * 商户充值广告金收益
     */
    private int storeAdDepositBenefit =0;

    /**
     * 激活设备数量
     */
    private int activatedDeviceNum=0;

    /**
     * 激活设备收益
     */
    private int activatedDeviceBenefit=0;


    /**
     * 自定义红包分成收益
     */
    private int rebateAdBenefit=0;

    /**
     * 商户服务费金额
     */
    private int storeMarketingPrice=0;

    /**
     * 商户服务费分成奖励
     */
    private int storeMarketingBenefit=0;

    /**
     * 加盟区域数量
     */
    private int totalFranchiseArea=0;

    /**
     * 加盟费奖励收益
     */
    private int totalFranchiseBenefit=0;

    /**
     * 下级（区县）代理采购数量
     */
    private int distDeviceBuyNum = 0;

    /**
     * 下级（区县）代理采购奖励
     */
    private int distDeviceBuyBenefit = 0;

    /**
     * 下级（区县）设备品牌红包
     */
    private int dist_device_red=0;

    /**
     * 下级（区县）设备品牌红包收益
     */
    private int benefit_dist_device_red = 0;

    /**
     * 下级（区县）商户次年服务费总金额
     */
    private int dist_merchant_market =0;

    /**
     * 下级（区县）商户次年服务费收益
     */
    private int benefit_dist_merchant_market =0;

    public int getDist_device_red() {
        return dist_device_red;
    }

    public void setDist_device_red(int dist_device_red) {
        this.dist_device_red = dist_device_red;
    }

    public int getBenefit_dist_device_red() {
        return benefit_dist_device_red;
    }

    public void setBenefit_dist_device_red(int benefit_dist_device_red) {
        this.benefit_dist_device_red = benefit_dist_device_red;
    }

    public int getDist_merchant_market() {
        return dist_merchant_market;
    }

    public void setDist_merchant_market(int dist_merchant_market) {
        this.dist_merchant_market = dist_merchant_market;
    }

    public int getBenefit_dist_merchant_market() {
        return benefit_dist_merchant_market;
    }

    public void setBenefit_dist_merchant_market(int benefit_dist_merchant_market) {
        this.benefit_dist_merchant_market = benefit_dist_merchant_market;
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

    public FinanceStatistics() {
    }

/*    public FinanceStatistics(int combinationDeviceNum, int indirectDeviceNum
            , int combinationDeviceBenefit, int indirectDeviceBenefit
            , int combinationAdPrice, int combinationAdBenefit
            , int storeAdDepositPrice, int storeAdDepositBenefit
            , int activatedDeviceNum, int activatedDeviceBenefit,int rebateAdBenefit) {
        this.combinationDeviceNum = combinationDeviceNum;
        this.indirectDeviceNum = indirectDeviceNum;
        this.combinationDeviceBenefit = combinationDeviceBenefit;
        this.indirectDeviceBenefit = indirectDeviceBenefit;
        this.combinationAdPrice = combinationAdPrice;
        this.combinationAdBenefit = combinationAdBenefit;
        this.storeAdDepositPrice = storeAdDepositPrice;
        this.storeAdDepositBenefit = storeAdDepositBenefit;

        this.activatedDeviceNum = activatedDeviceNum;
        this.activatedDeviceBenefit = activatedDeviceBenefit;

        this.rebateAdBenefit = rebateAdBenefit;
    }*/

    public int getStoreMarketingPrice() {
        return storeMarketingPrice;
    }

    public void setStoreMarketingPrice(int storeMarketingPrice) {
        this.storeMarketingPrice = storeMarketingPrice;
    }

    public int getStoreMarketingBenefit() {
        return storeMarketingBenefit;
    }

    public void setStoreMarketingBenefit(int storeMarketingBenefit) {
        this.storeMarketingBenefit = storeMarketingBenefit;
    }

    public int getTotalFranchiseArea() {
        return totalFranchiseArea;
    }

    public void setTotalFranchiseArea(int totalFranchiseArea) {
        this.totalFranchiseArea = totalFranchiseArea;
    }

    public int getTotalFranchiseBenefit() {
        return totalFranchiseBenefit;
    }

    public void setTotalFranchiseBenefit(int totalFranchiseBenefit) {
        this.totalFranchiseBenefit = totalFranchiseBenefit;
    }

    public int getCombinationDeviceNum() {
        return combinationDeviceNum;
    }

    public void setCombinationDeviceNum(int combinationDeviceNum) {
        this.combinationDeviceNum = combinationDeviceNum;
    }

    public int getIndirectDeviceNum() {
        return indirectDeviceNum;
    }

    public void setIndirectDeviceNum(int indirectDeviceNum) {
        this.indirectDeviceNum = indirectDeviceNum;
    }

    public int getDirectAdPrice() {
        return directAdPrice;
    }

    public void setDirectAdPrice(int directAdPrice) {
        this.directAdPrice = directAdPrice;
    }

    public int getSelfAdPrice() {
        return selfAdPrice;
    }

    public void setSelfAdPrice(int selfAdPrice) {
        this.selfAdPrice = selfAdPrice;
    }

    public int getCombinationDeviceBenefit() {
        return combinationDeviceBenefit;
    }

    public void setCombinationDeviceBenefit(int getCombinationDeviceBenefit) {
        this.combinationDeviceBenefit = getCombinationDeviceBenefit;
    }

    public int getIndirectDeviceBenefit() {
        return indirectDeviceBenefit;
    }

    public void setIndirectDeviceBenefit(int indirectDeviceBenefit) {
        this.indirectDeviceBenefit = indirectDeviceBenefit;
    }

    public int getDirectAdBenefit() {
        return directAdBenefit;
    }

    public void setDirectAdBenefit(int directAdBenefit) {
        this.directAdBenefit = directAdBenefit;
    }

    public int getSelfAdBenefit() {
        return selfAdBenefit;
    }

    public void setSelfAdBenefit(int selfAdBenefit) {
        this.selfAdBenefit = selfAdBenefit;
    }

    public int getCombinationAdPrice() {
        return combinationAdPrice;
    }

    public void setCombinationAdPrice(int combinationAdPrice) {
        this.combinationAdPrice = combinationAdPrice;
    }

    public int getCombinationAdBenefit() {
        return combinationAdBenefit;
    }

    public void setCombinationAdBenefit(int combinationAdBenefit) {
        this.combinationAdBenefit = combinationAdBenefit;
    }

    public int getStoreAdDepositPrice() {
        return storeAdDepositPrice;
    }

    public void setStoreAdDepositPrice(int storeAdDepositPrice) {
        this.storeAdDepositPrice = storeAdDepositPrice;
    }

    public int getStoreAdDepositBenefit() {
        return storeAdDepositBenefit;
    }

    public void setStoreAdDepositBenefit(int storeAdDepositBenefit) {
        this.storeAdDepositBenefit = storeAdDepositBenefit;
    }

    public int getActivatedDeviceNum() {
        return activatedDeviceNum;
    }

    public void setActivatedDeviceNum(int activatedDeviceNum) {
        this.activatedDeviceNum = activatedDeviceNum;
    }

    public int getActivatedDeviceBenefit() {
        return activatedDeviceBenefit;
    }

    public void setActivatedDeviceBenefit(int activatedDeviceBenefit) {
        this.activatedDeviceBenefit = activatedDeviceBenefit;
    }

    public int getRebateAdBenefit() {
        return rebateAdBenefit;
    }

    public void setRebateAdBenefit(int rebateAdBenefit) {
        this.rebateAdBenefit = rebateAdBenefit;
    }
}
