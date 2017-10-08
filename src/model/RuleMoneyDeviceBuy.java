package model;

import java.util.Date;

public class RuleMoneyDeviceBuy {
    private Integer id;

    private String type;

    private Integer price;

    private Integer min_num;

    private String proxy_token;

    private String msg;

    private Date time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getMin_num() {
        return min_num;
    }

    public void setMin_num(Integer min_num) {
        this.min_num = min_num;
    }

    public String getProxy_token() {
        return proxy_token;
    }

    public void setProxy_token(String proxy_token) {
        this.proxy_token = proxy_token == null ? null : proxy_token.trim();
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg == null ? null : msg.trim();
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}