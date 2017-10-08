package model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import java.util.Date;
import java.util.Objects;

public class ProxyAccountBuyRecord {
    private Integer id;

    private String out_trade_no;

    private Integer normal_num_pay;

    private Integer platform_num_pay;

    private Integer state;

    private String proxy_phone;

    private Date modifytime;

    private Date createtime;

    private Integer fee;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no == null ? null : out_trade_no.trim();
    }

    public Integer getNormal_num_pay() {
        return normal_num_pay;
    }

    public void setNormal_num_pay(Integer normal_num_pay) {
        this.normal_num_pay = normal_num_pay;
    }

    public Integer getPlatform_num_pay() {
        return platform_num_pay;
    }

    public void setPlatform_num_pay(Integer platform_num_pay) {
        this.platform_num_pay = platform_num_pay;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getProxy_phone() {
        return proxy_phone;
    }

    public void setProxy_phone(String proxy_phone) {
        this.proxy_phone = proxy_phone == null ? null : proxy_phone.trim();
    }

    public Date getModifytime() {
        return modifytime;
    }

    public void setModifytime(Date modifytime) {
        this.modifytime = modifytime;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getFee() {
        return fee;
    }

    public void setFee(Integer fee) {
        this.fee = fee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProxyAccountBuyRecord record = (ProxyAccountBuyRecord) o;

        if (id != null ? !id.equals(record.id) : record.id != null) return false;
        if (out_trade_no != null ? !out_trade_no.equals(record.out_trade_no) : record.out_trade_no != null)
            return false;
        if (normal_num_pay != null ? !normal_num_pay.equals(record.normal_num_pay) : record.normal_num_pay != null)
            return false;
        if (platform_num_pay != null ? !platform_num_pay.equals(record.platform_num_pay) : record.platform_num_pay != null)
            return false;
        if (state != null ? !state.equals(record.state) : record.state != null) return false;
        if (proxy_phone != null ? !proxy_phone.equals(record.proxy_phone) : record.proxy_phone != null) return false;
        if (modifytime != null ? !modifytime.equals(record.modifytime) : record.modifytime != null) return false;
        if (createtime != null ? !createtime.equals(record.createtime) : record.createtime != null) return false;
        return !(fee != null ? !fee.equals(record.fee) : record.fee != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (out_trade_no != null ? out_trade_no.hashCode() : 0);
        result = 31 * result + (normal_num_pay != null ? normal_num_pay.hashCode() : 0);
        result = 31 * result + (platform_num_pay != null ? platform_num_pay.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (proxy_phone != null ? proxy_phone.hashCode() : 0);
        result = 31 * result + (modifytime != null ? modifytime.hashCode() : 0);
        result = 31 * result + (createtime != null ? createtime.hashCode() : 0);
        result = 31 * result + (fee != null ? fee.hashCode() : 0);
        return result;
    }
}