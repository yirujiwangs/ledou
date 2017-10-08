package model;

import java.util.Date;

public class WxStatistics {
    private Integer id;

    private Date date;

    private Integer shakepv;

    private Integer shakeuv;

    private Integer clickuv;

    private Integer clickpv;

    private String descr;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getShakepv() {
        return shakepv;
    }

    public void setShakepv(Integer shakepv) {
        this.shakepv = shakepv;
    }

    public Integer getShakeuv() {
        return shakeuv;
    }

    public void setShakeuv(Integer shakeuv) {
        this.shakeuv = shakeuv;
    }

    public Integer getClickuv() {
        return clickuv;
    }

    public void setClickuv(Integer clickuv) {
        this.clickuv = clickuv;
    }

    public Integer getClickpv() {
        return clickpv;
    }

    public void setClickpv(Integer clickpv) {
        this.clickpv = clickpv;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr == null ? null : descr.trim();
    }
}