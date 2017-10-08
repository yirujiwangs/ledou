package model.dto;

import model.Admin;
import model.divide.BaseDivideModel;

import java.util.Date;

/**
 * Created by XWL on 2017/7/21.
 */
public class FranchiseFee extends BaseDivideModel {

    private String account;
    private String phoneNum;
    private String areaName;
    private Date startDate;
    private String userName;
    private int signway;

    public int getSignway() {
        return signway;
    }

    public void setSignway(int signway) {
        this.signway = signway;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
