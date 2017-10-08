package model;

import java.util.Date;

public class Deposit {
    private String out_trade_no;

    private String phoneNum;

    private String trade_no;


    private Double fee;

    private Date date;

    private Date date_time;

    private String remark;

    private String result;

    private String cause;

    private Integer taxesid;

    private Double taxes;

    private Integer proxy_taxesid;

    private Double proxy_taxes;

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no == null ? null : out_trade_no.trim();
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum == null ? null : phoneNum.trim();
    }

    public String getTrade_no() {
        return trade_no;
    }

    public void setTrade_no(String trade_no) {
        this.trade_no = trade_no == null ? null : trade_no.trim();
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
        this.remark = remark == null ? null : remark.trim();
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result == null ? null : result.trim();
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause == null ? null : cause.trim();
    }


    public Integer getTaxesid() {
        return taxesid;
    }

    public void setTaxesid(Integer taxesid) {
        this.taxesid = taxesid;
    }

    public Integer getProxy_taxesid() {
        return proxy_taxesid;
    }

    public void setProxy_taxesid(Integer proxy_taxesid) {
        this.proxy_taxesid = proxy_taxesid;
    }

    public Double getTaxes() {
        return taxes;
    }

    public void setTaxes(Double taxes) {
        this.taxes = taxes;
    }

    public Double getProxy_taxes() {
        return proxy_taxes;
    }

    public void setProxy_taxes(Double proxy_taxes) {
        this.proxy_taxes = proxy_taxes;
    }
}