package model.dto;

/**
 * Created by yeran on 2017/5/3.
 */
public class DealerDeviceNumer {

    /**
     * 下级拓展服务商数量
     */
    private int dealerNum =0;

    /**
     * 一级代理上月推荐采购量
     */
    private int fDealerDeviceNumLast=0;

    /**
     * 二级代理上月推荐采购量
     */
    private int sDealerDeviceNumLast=0;

    /**
     * 一级代理本月推荐采购量
     */
    private int fDealerDeviceNumNow=0;

    /**
     * 二级代理本月推荐采购量
     */
    private int sDealerDeviceNumNow=0;


    public DealerDeviceNumer() {
    }

    public DealerDeviceNumer(int dealerNum, int fDealerDeviceNumLast, int sDealerDeviceNumLast, int fDealerDeviceNumNow, int sDealerDeviceNumNow) {
        this.dealerNum = dealerNum;
        this.fDealerDeviceNumLast = fDealerDeviceNumLast;
        this.sDealerDeviceNumLast = sDealerDeviceNumLast;
        this.fDealerDeviceNumNow = fDealerDeviceNumNow;
        this.sDealerDeviceNumNow = sDealerDeviceNumNow;
    }

    public int getDealerNum() {
        return dealerNum;
    }

    public void setDealerNum(int dealerNum) {
        this.dealerNum = dealerNum;
    }

    public int getfDealerDeviceNumLast() {
        return fDealerDeviceNumLast;
    }

    public void setfDealerDeviceNumLast(int fDealerDeviceNumLast) {
        this.fDealerDeviceNumLast = fDealerDeviceNumLast;
    }

    public int getsDealerDeviceNumLast() {
        return sDealerDeviceNumLast;
    }

    public void setsDealerDeviceNumLast(int sDealerDeviceNumLast) {
        this.sDealerDeviceNumLast = sDealerDeviceNumLast;
    }

    public int getfDealerDeviceNumNow() {
        return fDealerDeviceNumNow;
    }

    public void setfDealerDeviceNumNow(int fDealerDeviceNumNow) {
        this.fDealerDeviceNumNow = fDealerDeviceNumNow;
    }

    public int getsDealerDeviceNumNow() {
        return sDealerDeviceNumNow;
    }

    public void setsDealerDeviceNumNow(int sDealerDeviceNumNow) {
        this.sDealerDeviceNumNow = sDealerDeviceNumNow;
    }
}
