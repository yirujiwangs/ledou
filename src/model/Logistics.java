package model;

import java.util.Date;

public class Logistics {
    private Integer id;

    private String logistic_company;

    private String logistic_no;

    private String status;

    private Date time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogistic_company() {
        return logistic_company;
    }

    public void setLogistic_company(String logistic_company) {
        this.logistic_company = logistic_company == null ? null : logistic_company.trim();
    }

    public String getLogistic_no() {
        return logistic_no;
    }

    public void setLogistic_no(String logistic_no) {
        this.logistic_no = logistic_no == null ? null : logistic_no.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}