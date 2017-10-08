package model;

import java.util.Date;

public class AdvOrderRecord {

    /**
     * 支付成功
     */
    public static final String AD_STATE_PAYED="PAYED";
    /**
     * 上架/正常
     */
    public static final String AD_STATE_NORMAL="N";
    /**
     * 下架
     */
    public static final String AD_STATE_DOWN="PM";
    /**
     * 待审核
     */
    public static final String AD_STATE_UP="UP";
    /**
     * 等待支付
     */
    public static final String AD_STATE_WG="WG";

    /**
     * 审核失败
     */
    public static final String AD_STATE_ERR="ERR";

    public static final Integer TRADE_STATUS_WAITTING = 0;
    public static final Integer TRADE_STATUS_SUCCESS = 1;
    public static final Integer TRADE_STATUS_FAILED = 2;
    public static final Integer TRADE_STATUS_DELETED = -1;
    private Integer id;

    private String proxyPhone;

    private String proxyToken;

    private String advUuid;

    private Double advFee;

    private Integer advType;

    private String ourTradeNo;

    private String alipayTradeNo;

    private String alipayAccountName;

    private String alipayAccountNum;

    private Integer tradeStatus;

    private String remark;

    private String cause;

    private Date tradetime;

    private Date creattime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProxyPhone() {
        return proxyPhone;
    }

    public void setProxyPhone(String proxyPhone) {
        this.proxyPhone = proxyPhone == null ? null : proxyPhone.trim();
    }

    public String getProxyToken() {
        return proxyToken;
    }

    public void setProxyToken(String proxyToken) {
        this.proxyToken = proxyToken == null ? null : proxyToken.trim();
    }

    public String getAdvUuid() {
        return advUuid;
    }

    public void setAdvUuid(String advUuid) {
        this.advUuid = advUuid == null ? null : advUuid.trim();
    }

    public Double getAdvFee() {
        return advFee;
    }

    public void setAdvFee(Double advFee) {
        this.advFee = advFee;
    }

    public Integer getAdvType() {
        return advType;
    }

    public void setAdvType(Integer advType) {
        this.advType = advType;
    }

    public String getOurTradeNo() {
        return ourTradeNo;
    }

    public void setOurTradeNo(String ourTradeNo) {
        this.ourTradeNo = ourTradeNo == null ? null : ourTradeNo.trim();
    }

    public String getAlipayTradeNo() {
        return alipayTradeNo;
    }

    public void setAlipayTradeNo(String alipayTradeNo) {
        this.alipayTradeNo = alipayTradeNo == null ? null : alipayTradeNo.trim();
    }

    public String getAlipayAccountName() {
        return alipayAccountName;
    }

    public void setAlipayAccountName(String alipayAccountName) {
        this.alipayAccountName = alipayAccountName == null ? null : alipayAccountName.trim();
    }

    public String getAlipayAccountNum() {
        return alipayAccountNum;
    }

    public void setAlipayAccountNum(String alipayAccountNum) {
        this.alipayAccountNum = alipayAccountNum == null ? null : alipayAccountNum.trim();
    }

    public Integer getTradeStatus() {
        return tradeStatus;
    }

    public void setTradeStatus(Integer tradeStatus) {
        this.tradeStatus = tradeStatus;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause == null ? null : cause.trim();
    }

    public Date getTradetime() {
        return tradetime;
    }

    public void setTradetime(Date tradetime) {
        this.tradetime = tradetime;
    }

    public Date getCreattime() {
        return creattime;
    }

    public void setCreattime(Date creattime) {
        this.creattime = creattime;
    }
}