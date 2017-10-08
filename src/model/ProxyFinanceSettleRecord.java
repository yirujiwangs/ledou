package model;

import java.util.Date;

public class ProxyFinanceSettleRecord {
    private Integer id;

    private Date stime;

    private Date etime;

    private String job_id;

    private String status;

    private Integer proxy_amount;

    private Integer proxy_price_total;

    private Date time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getStime() {
        return stime;
    }

    public void setStime(Date stime) {
        this.stime = stime;
    }

    public Date getEtime() {
        return etime;
    }

    public void setEtime(Date etime) {
        this.etime = etime;
    }

    public String getJob_id() {
        return job_id;
    }

    public void setJob_id(String job_id) {
        this.job_id = job_id == null ? null : job_id.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Integer getProxy_amount() {
        return proxy_amount;
    }

    public void setProxy_amount(Integer proxy_amount) {
        this.proxy_amount = proxy_amount;
    }

    public Integer getProxy_price_total() {
        return proxy_price_total;
    }

    public void setProxy_price_total(Integer proxy_price_total) {
        this.proxy_price_total = proxy_price_total;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}