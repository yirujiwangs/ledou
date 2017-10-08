package weixin.popular.bean.component;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import weixin.popular.util.AdaptorCDATA;
/**
 *
 * 推送 component_verify_ticket协议 或 取消授权通知 XML 数据
 * @author LiYi
 *
 */
@XmlRootElement(name="xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class ComponentReceiveXML {

	@XmlElement(name="AppId")
	private String appid;

	@XmlElement(name="CreateTime")
	private Integer createTime ;

	@XmlElement(name="InfoType")
	@XmlJavaTypeAdapter(value = AdaptorCDATA.class)
	private String infoType;

	@XmlElement(name="ComponentVerifyTicket")
	@XmlJavaTypeAdapter(value = AdaptorCDATA.class)
	private String componentVerifyTicket;


	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public Integer getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Integer createTime) {
		this.createTime = createTime;
	}

	public String getInfoType() {
		return infoType;
	}

	public void setInfoType(String infoType) {
		this.infoType = infoType;
	}

	public String getComponentVerifyTicket() {
		return componentVerifyTicket;
	}

	public void setComponentVerifyTicket(String componentVerifyTicket) {
		this.componentVerifyTicket = componentVerifyTicket;
	}

}
