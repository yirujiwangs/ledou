package model;

import java.util.Date;

public class ProxyAccountCount {
    private Integer id;

    private Integer normal_num_total;

    private Integer platform_num_total;

    private Integer normal_num_used;

    private Integer platform_num_used;

    private Integer normal_num_active;

    private Integer platform_num_active;

    private Integer normal_num_freezeing;

    private Integer platform_num_freezeing;


    private String proxy_phone;

    private Date createtime;

    public ProxyAccountCount() {
    }

    public ProxyAccountCount(Integer normal_num_total, Integer platform_num_total, Integer normal_num_used, Integer platform_num_used, Integer normal_num_active, Integer platform_num_active, Integer normal_num_freezeing, Integer platform_num_freezeing, String proxy_phone) {
        this.normal_num_total = normal_num_total;
        this.platform_num_total = platform_num_total;
        this.normal_num_used = normal_num_used;
        this.platform_num_used = platform_num_used;
        this.normal_num_active = normal_num_active;
        this.platform_num_active = platform_num_active;
        this.normal_num_freezeing = normal_num_freezeing;
        this.platform_num_freezeing = platform_num_freezeing;
        this.proxy_phone = proxy_phone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNormal_num_total() {
        return normal_num_total;
    }

    public void setNormal_num_total(Integer normal_num_total) {
        this.normal_num_total = normal_num_total;
    }

    public Integer getPlatform_num_total() {
        return platform_num_total;
    }

    public void setPlatform_num_total(Integer platform_num_total) {
        this.platform_num_total = platform_num_total;
    }

    public Integer getNormal_num_used() {
        return normal_num_used;
    }

    public void setNormal_num_used(Integer normal_num_used) {
        this.normal_num_used = normal_num_used;
    }

    public Integer getPlatform_num_used() {
        return platform_num_used;
    }

    public void setPlatform_num_used(Integer platform_num_used) {
        this.platform_num_used = platform_num_used;
    }

    public Integer getNormal_num_active() {
        return normal_num_active;
    }

    public void setNormal_num_active(Integer normal_num_active) {
        this.normal_num_active = normal_num_active;
    }

    public Integer getPlatform_num_active() {
        return platform_num_active;
    }

    public void setPlatform_num_active(Integer platform_num_active) {
        this.platform_num_active = platform_num_active;
    }

    public Integer getNormal_num_freezeing() {
        return normal_num_freezeing;
    }

    public void setNormal_num_freezeing(Integer normal_num_freezeing) {
        this.normal_num_freezeing = normal_num_freezeing;
    }

    public Integer getPlatform_num_freezeing() {
        return platform_num_freezeing;
    }

    public void setPlatform_num_freezeing(Integer platform_num_freezeing) {
        this.platform_num_freezeing = platform_num_freezeing;
    }


    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getProxy_phone() {
        return proxy_phone;
    }

    public void setProxy_phone(String proxy_phone) {
        this.proxy_phone = proxy_phone;
    }
}