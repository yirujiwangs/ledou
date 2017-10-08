package common.weixin;
/**
 * 与微信device对应的bean
 * @author lenovo
 *
 */
public class DeviceBean {
	//审核状态。0：审核未通过、1：审核中、2：审核已通过
	private int audit_status;
	//申请的批次ID
	private String apply_id;
	
	public int getAudit_status() {
		return audit_status;
	}
	public void setAudit_status(int audit_status) {
		this.audit_status = audit_status;
	}
	public String getApply_id() {
		return apply_id;
	}
	public void setApply_id(String apply_id) {
		this.apply_id = apply_id;
	}
	
	
}
