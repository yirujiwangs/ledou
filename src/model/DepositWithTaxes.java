package model;

import model.divide.BaseDivideModel;

import java.util.Date;

/**
 * Created by yeran on 2016/8/7.
 *
 * 携带具体抽成利率的充值类
 */
public class DepositWithTaxes extends BaseDivideModel{

    private String shopName;

    private String out_trade_no;

    private String phoneNum;

    private String trade_no;

    private Double fee;

    private Date date;

    private Date date_time;

    private String remark;

    private String result;

    private String cause;

    private double proxy_taxes_money;

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getTrade_no() {
        return trade_no;
    }

    public void setTrade_no(String trade_no) {
        this.trade_no = trade_no;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate_time() {
        return date_time;
    }

    public void setDate_time(Date date_time) {
        this.date_time = date_time;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public double getProxy_taxes_money() {
        return proxy_taxes_money;
    }

    public void setProxy_taxes_money(double proxy_taxes_money) {
        this.proxy_taxes_money = proxy_taxes_money;
    }
}
