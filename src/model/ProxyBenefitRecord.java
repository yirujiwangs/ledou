package model;

import java.util.Date;

public class ProxyBenefitRecord {
    private Integer id;

    private String proxy_token;

    private Double year_service_benefit;

    private Double ledouke_benefit;

    private Double live_circle_benefit;

    private Double lescene_benefit;

    private Date createtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProxy_token() {
        return proxy_token;
    }

    public void setProxy_token(String proxy_token) {
        this.proxy_token = proxy_token == null ? null : proxy_token.trim();
    }

    public Double getYear_service_benefit() {
        return year_service_benefit;
    }

    public void setYear_service_benefit(Double year_service_benefit) {
        this.year_service_benefit = year_service_benefit;
    }

    public Double getLedouke_benefit() {
        return ledouke_benefit;
    }

    public void setLedouke_benefit(Double ledouke_benefit) {
        this.ledouke_benefit = ledouke_benefit;
    }

    public Double getLive_circle_benefit() {
        return live_circle_benefit;
    }

    public void setLive_circle_benefit(Double live_circle_benefit) {
        this.live_circle_benefit = live_circle_benefit;
    }

    public Double getLescene_benefit() {
        return lescene_benefit;
    }

    public void setLescene_benefit(Double lescene_benefit) {
        this.lescene_benefit = lescene_benefit;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}