package model;

import java.util.Date;

public class IncomeDetailsProxy {
    private Integer id;

    private String utoken;

    private Integer device_buy_amount_self =0;

    private Integer device_buy_amount_dealer_direct =0;

    private Integer device_buy_amount_dealer_indirect =0;

    private Integer device_activated=0;

    private Integer money_ad_normal_self =0;

    private Integer money_ad_normal_dealer_direct =0;

    private Integer money_ad_normal_dealer_indirect =0;

    private Integer money_deposit_store =0;

    private Integer benefit_device_self =0;

    private Integer benefit_device_dealer_direct =0;

    private Integer benefit_deivce_dealer_indirect =0;

    private Integer benefit_ad_normal_self =0;

    private Integer benefit_ad_normal_indirect =0;

    private Integer benefit_ad_normal_direct =0;

    private Integer benefit_deposit_store =0;

    private Integer benefit_device_activated=0;

    private Integer benefit_rebate_ad=0;

    private String status;

    private Date record_time;

    private Date time;

    private Integer merchant_market;

    private Integer benefit_merchant_market;

    private Integer franchise_districts;

    private Integer benefit_franchise_districts;

    private Integer device_buy_amount_dist;

    public Integer getDist_device_red() {
        return dist_device_red;
    }

    public void setDist_device_red(Integer dist_device_red) {
        this.dist_device_red = dist_device_red;
    }

    public Integer getBenefit_dist_device_red() {
        return benefit_dist_device_red;
    }

    public void setBenefit_dist_device_red(Integer benefit_dist_device_red) {
        this.benefit_dist_device_red = benefit_dist_device_red;
    }

    public Integer getDist_merchant_market() {
        return dist_merchant_market;
    }

    public void setDist_merchant_market(Integer dist_merchant_market) {
        this.dist_merchant_market = dist_merchant_market;
    }

    public Integer getBenefit_dist_merchant_market() {
        return benefit_dist_merchant_market;
    }

    public void setBenefit_dist_merchant_market(Integer benefit_dist_merchant_market) {
        this.benefit_dist_merchant_market = benefit_dist_merchant_market;
    }

    private Integer benefit_device_buy_dist;

    private Integer dist_device_red;

    private Integer benefit_dist_device_red;

    private Integer dist_merchant_market;

    private Integer benefit_dist_merchant_market;


    public Integer getDevice_buy_amount_dist() {
        return device_buy_amount_dist;
    }

    public void setDevice_buy_amount_dist(Integer device_buy_amount_dist) {
        this.device_buy_amount_dist = device_buy_amount_dist;
    }

    public Integer getBenefit_device_buy_dist() {
        return benefit_device_buy_dist;
    }

    public void setBenefit_device_buy_dist(Integer benefit_device_buy_dist) {
        this.benefit_device_buy_dist = benefit_device_buy_dist;
    }

    public Integer getMerchant_market() {
        return merchant_market;
    }

    public void setMerchant_market(Integer merchant_market) {
        this.merchant_market = merchant_market;
    }

    public Integer getBenefit_merchant_market() {
        return benefit_merchant_market;
    }

    public void setBenefit_merchant_market(Integer benefit_merchant_market) {
        this.benefit_merchant_market = benefit_merchant_market;
    }

    public Integer getFranchise_districts() {
        return franchise_districts;
    }

    public void setFranchise_districts(Integer franchise_districts) {
        this.franchise_districts = franchise_districts;
    }

    public Integer getBenefit_franchise_districts() {
        return benefit_franchise_districts;
    }

    public void setBenefit_franchise_districts(Integer benefit_franchise_districts) {
        this.benefit_franchise_districts = benefit_franchise_districts;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUtoken() {
        return utoken;
    }

    public void setUtoken(String utoken) {
        this.utoken = utoken;
    }

    public Integer getDevice_buy_amount_self() {
        return device_buy_amount_self;
    }

    public void setDevice_buy_amount_self(Integer device_buy_amount_self) {
        this.device_buy_amount_self = device_buy_amount_self;
    }

    public Integer getDevice_buy_amount_dealer_direct() {
        return device_buy_amount_dealer_direct;
    }

    public void setDevice_buy_amount_dealer_direct(Integer device_buy_amount_dealer_direct) {
        this.device_buy_amount_dealer_direct = device_buy_amount_dealer_direct;
    }

    public Integer getDevice_buy_amount_dealer_indirect() {
        return device_buy_amount_dealer_indirect;
    }

    public void setDevice_buy_amount_dealer_indirect(Integer device_buy_amount_dealer_indirect) {
        this.device_buy_amount_dealer_indirect = device_buy_amount_dealer_indirect;
    }

    public Integer getMoney_ad_normal_self() {
        return money_ad_normal_self;
    }

    public void setMoney_ad_normal_self(Integer money_ad_normal_self) {
        this.money_ad_normal_self = money_ad_normal_self;
    }

    public Integer getMoney_ad_normal_dealer_direct() {
        return money_ad_normal_dealer_direct;
    }

    public void setMoney_ad_normal_dealer_direct(Integer money_ad_normal_dealer_direct) {
        this.money_ad_normal_dealer_direct = money_ad_normal_dealer_direct;
    }

    public Integer getMoney_ad_normal_dealer_indirect() {
        return money_ad_normal_dealer_indirect;
    }

    public void setMoney_ad_normal_dealer_indirect(Integer money_ad_normal_dealer_indirect) {
        this.money_ad_normal_dealer_indirect = money_ad_normal_dealer_indirect;
    }

    public Integer getBenefit_rebate_ad() {
        return benefit_rebate_ad;
    }

    public void setBenefit_rebate_ad(Integer benefit_rebate_ad) {
        this.benefit_rebate_ad = benefit_rebate_ad;
    }

    public Integer getMoney_deposit_store() {
        return money_deposit_store;
    }

    public void setMoney_deposit_store(Integer money_deposit_store) {
        this.money_deposit_store = money_deposit_store;
    }

    public Integer getBenefit_device_self() {
        return benefit_device_self;
    }

    public void setBenefit_device_self(Integer benefit_device_self) {
        this.benefit_device_self = benefit_device_self;
    }

    public Integer getBenefit_device_dealer_direct() {
        return benefit_device_dealer_direct;
    }

    public void setBenefit_device_dealer_direct(Integer benefit_device_dealer_direct) {
        this.benefit_device_dealer_direct = benefit_device_dealer_direct;
    }

    public Integer getBenefit_deivce_dealer_indirect() {
        return benefit_deivce_dealer_indirect;
    }

    public void setBenefit_deivce_dealer_indirect(Integer benefit_deivce_dealer_indirect) {
        this.benefit_deivce_dealer_indirect = benefit_deivce_dealer_indirect;
    }

    public Integer getBenefit_ad_normal_self() {
        return benefit_ad_normal_self;
    }

    public void setBenefit_ad_normal_self(Integer benefit_ad_normal_self) {
        this.benefit_ad_normal_self = benefit_ad_normal_self;
    }

    public Integer getBenefit_ad_normal_indirect() {
        return benefit_ad_normal_indirect;
    }

    public void setBenefit_ad_normal_indirect(Integer benefit_ad_normal_indirect) {
        this.benefit_ad_normal_indirect = benefit_ad_normal_indirect;
    }

    public Integer getBenefit_deposit_store() {
        return benefit_deposit_store;
    }

    public void setBenefit_deposit_store(Integer benefit_deposit_store) {
        this.benefit_deposit_store = benefit_deposit_store;
    }

    public Integer getBenefit_ad_normal_direct() {
        return benefit_ad_normal_direct;
    }

    public void setBenefit_ad_normal_direct(Integer benefit_ad_normal_direct) {
        this.benefit_ad_normal_direct = benefit_ad_normal_direct;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getRecord_time() {
        return record_time;
    }

    public void setRecord_time(Date record_time) {
        this.record_time = record_time;
    }

    public Integer getDevice_activated() {
        return device_activated;
    }

    public void setDevice_activated(Integer device_actived) {
        this.device_activated = device_actived;
    }

    public Integer getBenefit_device_activated() {
        return benefit_device_activated;
    }

    public void setBenefit_device_activated(Integer benefit_device_actived) {
        this.benefit_device_activated = benefit_device_actived;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}