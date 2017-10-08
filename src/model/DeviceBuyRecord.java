package model;

import java.util.Date;

public class DeviceBuyRecord {

    public static final String BUY_SUCCESS="N";
    public static final String BUY_WAIT="P";
    public static final String BUY_FAILED="D";

    private Integer id;

    private String utoken;

    private String partner_trade_no;

    private String device_type;

    private Integer amount;

    private Integer rule_id;

    private Integer original_price;

    private Integer policy_reduct;

    public Integer getOriginal_price() {
        return original_price;
    }

    public void setOriginal_price(Integer original_price) {
        this.original_price = original_price;
    }

    public Integer getPolicy_reduct() {
        return policy_reduct;
    }

    public void setPolicy_reduct(Integer policy_reduct) {
        this.policy_reduct = policy_reduct;
    }

    private Integer shield;



    public Integer getShield() {
        return shield;
    }

    public void setShield(Integer shield) {
        this.shield = shield;
    }

    private Integer unit_price;

    private Integer total_fee;

    private String status;

    private String type_sec;

    private String comment;

    private String logistic_no;

    private String consignee;

    private String contact_way;

    private String delivery_area;

    private String delivery_address;

    private Date time;

    private String reason;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
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
        this.utoken = utoken == null ? null : utoken.trim();
    }

    public String getPartner_trade_no() {
        return partner_trade_no;
    }

    public void setPartner_trade_no(String partner_trade_no) {
        this.partner_trade_no = partner_trade_no == null ? null : partner_trade_no.trim();
    }

    public String getDevice_type() {
        return device_type;
    }

    public void setDevice_type(String device_type) {
        this.device_type = device_type == null ? null : device_type.trim();
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getRule_id() {
        return rule_id;
    }

    public void setRule_id(Integer rule_id) {
        this.rule_id = rule_id;
    }

    public Integer getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(Integer unit_price) {
        this.unit_price = unit_price;
    }

    public Integer getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(Integer total_fee) {
        this.total_fee = total_fee;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getType_sec() {
        return type_sec;
    }

    public void setType_sec(String type_sec) {
        this.type_sec = type_sec == null ? null : type_sec.trim();
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    public String getLogistic_no() {
        return logistic_no;
    }

    public void setLogistic_no(String logistic_no) {
        this.logistic_no = logistic_no == null ? null : logistic_no.trim();
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee == null ? null : consignee.trim();
    }

    public String getContact_way() {
        return contact_way;
    }

    public void setContact_way(String contact_way) {
        this.contact_way = contact_way == null ? null : contact_way.trim();
    }

    public String getDelivery_area() {
        return delivery_area;
    }

    public void setDelivery_area(String delivery_area) {
        this.delivery_area = delivery_area == null ? null : delivery_area.trim();
    }

    public String getDelivery_address() {
        return delivery_address;
    }

    public void setDelivery_address(String delivery_address) {
        this.delivery_address = delivery_address == null ? null : delivery_address.trim();
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}