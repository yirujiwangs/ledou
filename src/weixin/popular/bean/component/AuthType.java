package weixin.popular.bean.component;

/**
 * 用户授权的目的类
 * 
 * @author yeran
 *
 */
public class AuthType {
	
	public static final String AUTH_TYPE_SHOP_BUND="1001";
	
	private String type;
	
	private String msg;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	

}
