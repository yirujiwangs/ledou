package model;

import model.divide.BaseDivideModel;

public class Finance extends BaseDivideModel{
    private Integer id;

    private String phonenum;

    private Double sum=0.0;

    private Double available=0.0;

    private Double freeze=0.0;

    private Double sumexpense=0.0;

    private Double sumdeposit=0.0;

    private Double sumrefund=0.0;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum == null ? null : phonenum.trim();
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public Double getAvailable() {
        return available;
    }

    public void setAvailable(Double available) {
        this.available = available;
    }

    public Double getFreeze() {
        return freeze;
    }

    public void setFreeze(Double freeze) {
        this.freeze = freeze;
    }

    public Double getSumexpense() {
        return sumexpense;
    }

    public void setSumexpense(Double sumexpense) {
        this.sumexpense = sumexpense;
    }

    public Double getSumdeposit() {
        return sumdeposit;
    }

    public void setSumdeposit(Double sumdeposit) {
        this.sumdeposit = sumdeposit;
    }

    public Double getSumrefund() {
        return sumrefund;
    }

    public void setSumrefund(Double sumrefund) {
        this.sumrefund = sumrefund;
    }
}