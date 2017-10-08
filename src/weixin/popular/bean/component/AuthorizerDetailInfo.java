package weixin.popular.bean.component;

import java.util.Calendar;

public class AuthorizerDetailInfo {
	private int id;
	private String authorizer_appid;
	private String component_appid;
	private String nick_name;
	private String head_img;
	private String service_type_info;
	private String verify_type_info;
	private String user_name;
	private String alias;
	private String qrcode_url;
	private boolean open_store;
	private boolean open_scan;
	private boolean open_pay;
	private boolean open_card;
	private boolean open_shake;
	private Calendar createtime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAuthorizer_appid() {
		return authorizer_appid;
	}
	public void setAuthorizer_appid(String authorizer_appid) {
		this.authorizer_appid = authorizer_appid;
	}
	public String getComponent_appid() {
		return component_appid;
	}
	public void setComponent_appid(String component_appid) {
		this.component_appid = component_appid;
	}
	public String getNick_name() {
		return nick_name;
	}
	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}
	public String getHead_img() {
		return head_img;
	}
	public void setHead_img(String head_img) {
		this.head_img = head_img;
	}
	public String getService_type_info() {
		return service_type_info;
	}
	public void setService_type_info(String service_type_info) {
		this.service_type_info = service_type_info;
	}
	public String getVerify_type_info() {
		return verify_type_info;
	}
	public void setVerify_type_info(String verify_type_info) {
		this.verify_type_info = verify_type_info;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getQrcode_url() {
		return qrcode_url;
	}
	public void setQrcode_url(String qrcode_url) {
		this.qrcode_url = qrcode_url;
	}
	public boolean isOpen_store() {
		return open_store;
	}
	public void setOpen_store(boolean open_store) {
		this.open_store = open_store;
	}
	public boolean isOpen_scan() {
		return open_scan;
	}
	public void setOpen_scan(boolean open_scan) {
		this.open_scan = open_scan;
	}
	public boolean isOpen_pay() {
		return open_pay;
	}
	public void setOpen_pay(boolean open_pay) {
		this.open_pay = open_pay;
	}
	public boolean isOpen_card() {
		return open_card;
	}
	public void setOpen_card(boolean open_card) {
		this.open_card = open_card;
	}
	public boolean isOpen_shake() {
		return open_shake;
	}
	public void setOpen_shake(boolean open_shake) {
		this.open_shake = open_shake;
	}
	public Calendar getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Calendar createtime) {
		this.createtime = createtime;
	}
	public AuthorizerDetailInfo() {
		super();
	}
	public AuthorizerDetailInfo(String authorizer_appid, String component_appid, String nick_name, String head_img,
			String user_name, String alias, String qrcode_url) {
		super();
		this.authorizer_appid = authorizer_appid;
		this.component_appid = component_appid;
		this.nick_name = nick_name;
		this.head_img = head_img;
		this.user_name = user_name;
		this.alias = alias;
		this.qrcode_url = qrcode_url;
	}
}
