package model;

import javax.sql.rowset.spi.SyncResolver;
import java.util.Date;
import java.util.Objects;

public class ProxyFinanceRecord {
    /**
     * 代理商抽成
     * */
    public static final int PROXY_FINANCE_RECORD_DEPOSIT=0X1;

    /**
     * 代理商结算
     * */
    public static final int PROXY_FINANCE_RECORD_REFUND=0X2;

    /**
     * 代理商结算成功
     * */
    public static final int PROXY_FINANCE_DEPOSIT_FINISHED=0X1;

    /**
     * 代理商正在结算
     * */
    public static final int PROXY_FINANCE_DEPOSIT_WAITING=0X0;


    private Integer id;

    private Integer type;

    private Double fee;

    private String proxy_phone;

    private Integer state;

    private String deposit_out_trade_no;

    private String deposit_userphone;

    private  String contract_phone;
    private String bank_account_name;
    private String bank_account_num;
    private String bank_name;
    private String alipay_account_name;
    private String alipay_account_num;

    private String contract_name;

    private Date createtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public String getProxy_phone() {
        return proxy_phone;
    }

    public void setProxy_phone(String proxy_phone) {
        this.proxy_phone = proxy_phone == null ? null : proxy_phone.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getDeposit_out_trade_no() {
        return deposit_out_trade_no;
    }

    public void setDeposit_out_trade_no(String deposit_out_trade_no) {
        this.deposit_out_trade_no = deposit_out_trade_no == null ? null : deposit_out_trade_no.trim();
    }

    public String getDeposit_userphone() {
        return deposit_userphone;
    }

    public void setDeposit_userphone(String deposit_userphone) {
        this.deposit_userphone = deposit_userphone;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getContract_phone() {
        return contract_phone;
    }

    public void setContract_phone(String contract_phone) {
        this.contract_phone = contract_phone;
    }

    public String getBank_account_name() {
        return bank_account_name;
    }

    public void setBank_account_name(String bank_account_name) {
        this.bank_account_name = bank_account_name;
    }

    public String getBank_account_num() {
        return bank_account_num;
    }

    public void setBank_account_num(String bank_account_num) {
        this.bank_account_num = bank_account_num;
    }

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public String getAlipay_account_name() {
        return alipay_account_name;
    }

    public void setAlipay_account_name(String alipay_account_name) {
        this.alipay_account_name = alipay_account_name;
    }

    public String getAlipay_account_num() {
        return alipay_account_num;
    }

    public void setAlipay_account_num(String alipay_account_num) {
        this.alipay_account_num = alipay_account_num;
    }

    public String getContract_name() {
        return contract_name;
    }

    public void setContract_name(String contract_name) {
        this.contract_name = contract_name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProxyFinanceRecord that = (ProxyFinanceRecord) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(type, that.type) &&
                Objects.equals(fee, that.fee) &&
                Objects.equals(proxy_phone, that.proxy_phone) &&
                Objects.equals(state, that.state) &&
                Objects.equals(deposit_out_trade_no, that.deposit_out_trade_no) &&
                Objects.equals(deposit_userphone, that.deposit_userphone) &&
                Objects.equals(contract_phone, that.contract_phone) &&
                Objects.equals(bank_account_name, that.bank_account_name) &&
                Objects.equals(bank_account_num, that.bank_account_num) &&
                Objects.equals(bank_name, that.bank_name) &&
                Objects.equals(alipay_account_name, that.alipay_account_name) &&
                Objects.equals(alipay_account_num, that.alipay_account_num) &&
                Objects.equals(contract_name, that.contract_name) &&
                Objects.equals(createtime, that.createtime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, fee, proxy_phone, state, deposit_out_trade_no, deposit_userphone, contract_phone, bank_account_name, bank_account_num, bank_name, alipay_account_name, alipay_account_num, contract_name, createtime);
    }

}