package model;

import java.util.Date;

public class ProxyTaxesRule {
    private Integer id;

    private Integer proxy_level;

    private String userphone;

    private Integer taxesid;

    private Date createtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProxy_level() {
        return proxy_level;
    }

    public void setProxy_level(Integer proxy_level) {
        this.proxy_level = proxy_level;
    }

    public String getUserphone() {
        return userphone;
    }

    public void setUserphone(String userphone) {
        this.userphone = userphone == null ? null : userphone.trim();
    }

    public Integer getTaxesid() {
        return taxesid;
    }

    public void setTaxesid(Integer taxesid) {
        this.taxesid = taxesid;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}