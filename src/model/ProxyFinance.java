package model;

import java.util.Date;

public class ProxyFinance {
    private Integer id;

    private String phoneNum;

    private Double sum_income;

    private Double avaiable;

    private Double balanced;

    private Double balancing;

    private Date modifytime;

    private Date createtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum == null ? null : phoneNum.trim();
    }

    public Double getSum_income() {
        return sum_income;
    }

    public void setSum_income(Double sum_income) {
        this.sum_income = sum_income;
    }

    public Double getAvaiable() {
        return avaiable;
    }

    public void setAvaiable(Double avaiable) {
        this.avaiable = avaiable;
    }

    public Double getBalanced() {
        return balanced;
    }

    public void setBalanced(Double balanced) {
        this.balanced = balanced;
    }

    public Double getBalancing() {
        return balancing;
    }

    public void setBalancing(Double balancing) {
        this.balancing = balancing;
    }

    public Date getModifytime() {
        return modifytime;
    }

    public void setModifytime(Date modifytime) {
        this.modifytime = modifytime;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}