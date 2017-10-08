package model;

import java.util.Date;

public class ProxyAreaReportLog {
    private Integer id;

    private String utoken;

    private String area_rid;

    private String area_name;

    private Date start_date;

    private Date end_date;

    private Integer proxy_level;

    private String status;

    private String company;

    private String contact_name;

    private String contact_way;

    private String comment;

    private Date createtime;

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
        this.utoken = utoken == null ? null : utoken.trim();
    }

    public String getArea_rid() {
        return area_rid;
    }

    public void setArea_rid(String area_rid) {
        this.area_rid = area_rid == null ? null : area_rid.trim();
    }

    public String getArea_name() {
        return area_name;
    }

    public void setArea_name(String area_name) {
        this.area_name = area_name == null ? null : area_name.trim();
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public Integer getProxy_level() {
        return proxy_level;
    }

    public void setProxy_level(Integer proxy_level) {
        this.proxy_level = proxy_level;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }

    public String getContact_name() {
        return contact_name;
    }

    public void setContact_name(String contact_name) {
        this.contact_name = contact_name == null ? null : contact_name.trim();
    }

    public String getContact_way() {
        return contact_way;
    }

    public void setContact_way(String contact_way) {
        this.contact_way = contact_way == null ? null : contact_way.trim();
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}