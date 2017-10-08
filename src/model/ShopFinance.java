package model;

/**
 * Created by yeran on 2016/8/6.
 *
 * 商家财务扩展类，包含了用户的账号信息
 */
public class ShopFinance extends Finance{
    private String storeName;
    private Double profit;

    public Double getProfit() {
        return profit;
    }

    public void setProfit(Double profit) {
        this.profit = profit;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }
}
