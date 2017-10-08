package model;

import model.divide.BaseDivideModel;

import java.util.Date;
import java.util.Objects;

public class Admin extends BaseDivideModel {
    public static final Integer PROXY_LEVEL_NORMAL = 1;

    public static final Integer PROXY_LEVEL_SUPER = 2;

    private Integer signway;

    private Integer franchise_fee;

    private Integer policy;

    private Integer shield;

    public Integer getShield() {
        return shield;
    }

    public void setShield(Integer shield) {
        this.shield = shield;
    }

    public Integer getPolicy() {
        return policy;
    }

    public void setPolicy(Integer policy) {
        this.policy = policy;
    }

    public Integer getFranchise_fee() {
        return franchise_fee;
    }

    public void setFranchise_fee(Integer franchise_fee) {
        this.franchise_fee = franchise_fee;
    }

    private Integer id;

    private String account;

    private String password;

    private String username;

    private String phoneNum;

    private Integer level;

    private String descr;

    private String status;

    private String proxy_token;

    private String recommend_phone;

    private String recommend_token;

    private String openid;

    private String ptoken;

    private String secretkey;

    private Date createTime;

    public Admin() {
    }

    public Admin(Integer id, String account, String password, String username, String phoneNum, Integer level, String descr, String status, String proxy_token, String openid, Date createTime,Integer signway,Integer franchise_fee) {
        this.id = id;
        this.account = account;
        this.password = password;
        this.username = username;
        this.phoneNum = phoneNum;
        this.level = level;
        this.descr = descr;
        this.status = status;
        this.proxy_token = proxy_token;
        this.openid = openid;
        this.createTime = createTime;
        this.signway = signway;
        this.franchise_fee = franchise_fee;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum == null ? null : phoneNum.trim();
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr == null ? null : descr.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getProxy_token() {
        return proxy_token;
    }

    public void setProxy_token(String proxy_token) {
        this.proxy_token = proxy_token == null ? null : proxy_token.trim();
    }

    public String getRecommend_token() {
        return recommend_token;
    }

    public void setRecommend_token(String recommend_token) {
        this.recommend_token = recommend_token;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public String getPtoken() {
        return ptoken;
    }

    public void setPtoken(String ptoken) {
        this.ptoken = ptoken == null ? null : ptoken.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getSecretkey() {
        return secretkey;
    }

    public void setSecretkey(String secretkey) {
        this.secretkey = secretkey;
    }

    public String getRecommend_phone() {
        return recommend_phone;
    }

    public void setRecommend_phone(String recommend_phone) {
        this.recommend_phone = recommend_phone;
    }

    public Integer getSignway() {
        return signway;
    }

    public void setSignway(Integer signway) {
        this.signway = signway;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Admin admin = (Admin) o;
        return Objects.equals(id, admin.id) &&
                Objects.equals(account, admin.account) &&
                Objects.equals(password, admin.password) &&
                Objects.equals(username, admin.username) &&
                Objects.equals(phoneNum, admin.phoneNum) &&
                Objects.equals(level, admin.level) &&
                Objects.equals(descr, admin.descr) &&
                Objects.equals(status, admin.status) &&
                Objects.equals(proxy_token, admin.proxy_token) &&
                Objects.equals(openid, admin.openid) &&
                Objects.equals(ptoken, admin.ptoken) &&
                Objects.equals(createTime, admin.createTime)&&
                Objects.equals(signway,admin.signway)&&
                Objects.equals(franchise_fee,admin.franchise_fee);

    }

    @Override
    public int hashCode() {
        return Objects.hash(id, account, password, username, phoneNum, level, descr, status, proxy_token, openid, ptoken, createTime,signway,franchise_fee);
    }


}