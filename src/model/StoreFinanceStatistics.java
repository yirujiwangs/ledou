package model;

/**
 * Created by yeran on 2016/8/16.
 */
public class StoreFinanceStatistics {
    private double totalmoney;
    private double totalexpense;
    private double totaldeposit;

    public StoreFinanceStatistics() {
    }

    public StoreFinanceStatistics(double totalmoney, double totalexpense, double totaldeposit) {
        this.totalmoney = totalmoney;
        this.totalexpense = totalexpense;
        this.totaldeposit = totaldeposit;
    }

    public double getTotalmoney() {
        return totalmoney;
    }

    public void setTotalmoney(double totalmoney) {
        this.totalmoney = totalmoney;
    }

    public double getTotalexpense() {
        return totalexpense;
    }

    public void setTotalexpense(double totalexpense) {
        this.totalexpense = totalexpense;
    }

    public double getTotaldeposit() {
        return totaldeposit;
    }

    public void setTotaldeposit(double totaldeposit) {
        this.totaldeposit = totaldeposit;
    }
}
