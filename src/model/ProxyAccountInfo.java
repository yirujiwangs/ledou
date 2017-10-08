package model;

import java.util.Date;

public class ProxyAccountInfo {
    private Integer id;

    private Integer account_id;

    private String nickname;

    private String company_name;

    private String company_num;

    private String id_num;

    private String logourl;

    private String address;

    private String contract_name;

    private String contract_phone;

    private String email;

    private String wx_name;

    private String wx_qr_url;

    private String bank_account_name;

    private String bank_account_num;

    private String bank_name;

    private String alipay_account_name;

    private String alipay_account_num;

    private String label_ids;

    private String file_path;

    private String contract_path;

    private Date modifytime;

    private Date createtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAccount_id() {
        return account_id;
    }

    public void setAccount_id(Integer account_id) {
        this.account_id = account_id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name == null ? null : company_name.trim();
    }

    public String getCompany_num() {
        return company_num;
    }

    public void setCompany_num(String company_num) {
        this.company_num = company_num == null ? null : company_num.trim();
    }

    public String getId_num() {
        return id_num;
    }

    public void setId_num(String id_num) {
        this.id_num = id_num == null ? null : id_num.trim();
    }

    public String getLogourl() {
        return logourl;
    }

    public void setLogourl(String logourl) {
        this.logourl = logourl == null ? null : logourl.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getContract_name() {
        return contract_name;
    }

    public void setContract_name(String contract_name) {
        this.contract_name = contract_name == null ? null : contract_name.trim();
    }

    public String getContract_phone() {
        return contract_phone;
    }

    public void setContract_phone(String contract_phone) {
        this.contract_phone = contract_phone == null ? null : contract_phone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getWx_name() {
        return wx_name;
    }

    public void setWx_name(String wx_name) {
        this.wx_name = wx_name == null ? null : wx_name.trim();
    }

    public String getWx_qr_url() {
        return wx_qr_url;
    }

    public void setWx_qr_url(String wx_qr_url) {
        this.wx_qr_url = wx_qr_url == null ? null : wx_qr_url.trim();
    }

    public String getBank_account_name() {
        return bank_account_name;
    }

    public void setBank_account_name(String bank_account_name) {
        this.bank_account_name = bank_account_name == null ? null : bank_account_name.trim();
    }

    public String getBank_account_num() {
        return bank_account_num;
    }

    public void setBank_account_num(String bank_account_num) {
        this.bank_account_num = bank_account_num == null ? null : bank_account_num.trim();
    }

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name == null ? null : bank_name.trim();
    }

    public String getAlipay_account_name() {
        return alipay_account_name;
    }

    public void setAlipay_account_name(String alipay_account_name) {
        this.alipay_account_name = alipay_account_name == null ? null : alipay_account_name.trim();
    }

    public String getAlipay_account_num() {
        return alipay_account_num;
    }

    public void setAlipay_account_num(String alipay_account_num) {
        this.alipay_account_num = alipay_account_num == null ? null : alipay_account_num.trim();
    }

    public String getLabel_ids() {
        return label_ids;
    }

    public void setLabel_ids(String label_ids) {
        this.label_ids = label_ids == null ? null : label_ids.trim();
    }

    public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path == null ? null : file_path.trim();
    }

    public String getContract_path() {
        return contract_path;
    }

    public void setContract_path(String contract_path) {
        this.contract_path = contract_path == null ? null : contract_path.trim();
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
}