package model;

/**
 * Created by yeran on 2016/8/6.
 *
 * �̼Ҳ�����չ�࣬�������û����˺���Ϣ
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
