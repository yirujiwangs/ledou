package model;

import java.util.Date;

public class StoreAccountRule {
    public static final int STORE_TYPE_NORMAL = 0X0;
    public static final int STORE_TYPE_PLATFORM = 0X1;


    private Integer id;

    private Integer store_type;

    private Double account_fee;

    private String msg;

    private Date createtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStore_type() {
        return store_type;
    }

    public void setStore_type(Integer store_type) {
        this.store_type = store_type;
    }

    public Double getAccount_fee() {
        return account_fee;
    }

    public void setAccount_fee(Double account_fee) {
        this.account_fee = account_fee;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg == null ? null : msg.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}