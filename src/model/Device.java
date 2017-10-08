package model;

import java.util.Date;

public class Device {
    private Integer id;

    private String ibeaconId;

    private Integer groupId;

    private Integer pageid;

    private String groopname;

    private Integer tagid;

    private String phone;

    private String storename;

    private String no;

    private String descr;

    private String type;

    private String remark;

    private String status;

    private Integer weixinId;

    private String uuid;

    private Integer major;

    private Integer minor;

    private Date createTime;
    
    private String serialNum;

    public Device(){

    }

    public Device(Integer id, String serialNum, Integer weixinId, String descr) {
        this.id = id;
        this.serialNum = serialNum;
        this.weixinId = weixinId;
        this.descr = descr;
    }

    public Device(String serialNum,String ibeaconId, Integer groupId, Integer pageid, String groopname, Integer tagid, String phone, String storename, String no, String descr, String type, String remark, String status, Integer weixinId, String uuid, Integer major, Integer minor, Date createTime) {
        this.serialNum=serialNum;
    	this.ibeaconId = ibeaconId;
        this.groupId = groupId;
        this.pageid = pageid;
        this.groopname = groopname;
        this.tagid = tagid;
        this.phone = phone;
        this.storename = storename;
        this.no = no;
        this.descr = descr;
        this.type = type;
        this.remark = remark;
        this.status = status;
        this.weixinId = weixinId;
        this.uuid = uuid;
        this.major = major;
        this.minor = minor;
        this.createTime = createTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIbeaconId() {
        return ibeaconId;
    }

    public void setIbeaconId(String ibeaconId) {
        this.ibeaconId = ibeaconId == null ? null : ibeaconId.trim();
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }
    
    public Integer getPageid() {
        return pageid;
    }

    public void setPageid(Integer pageid) {
        this.pageid = pageid;
    }

    public String getGroopname() {
        return groopname;
    }

    public void setGroopname(String groopname) {
        this.groopname = groopname == null ? null : groopname.trim();
    }

    public Integer getTagid() {
        return tagid;
    }

    public void setTagid(Integer tagid) {
        this.tagid = tagid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getStorename() {
        return storename;
    }

    public void setStorename(String storename) {
        this.storename = storename == null ? null : storename.trim();
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no == null ? null : no.trim();
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr == null ? null : descr.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Integer getWeixinId() {
        return weixinId;
    }

    public void setWeixinId(Integer weixinId) { this.weixinId = weixinId; }

    public String getUuid() { return uuid; }

    public void setUuid(String uuid) { this.uuid = uuid==null?null:uuid.trim(); }

    public Integer getMajor() {
        return major;
    }

    public void setMajor(Integer major) {
        this.major = major;
    }

    public Integer getMinor() {
        return minor;
    }

    public void setMinor(Integer minor) {
        this.minor = minor;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
	public String getSerialNum() {
		return serialNum;
	}
	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}


    @Override
    public String toString() {
        return "Device{" +
                "id=" + id +
                ", ibeaconId='" + ibeaconId + '\'' +
                ", descr='" + descr + '\'' +
                ", remark='" + remark + '\'' +
                ", status='" + status + '\'' +
                ", uuid='" + uuid + '\'' +
                ", major=" + major +
                ", minor=" + minor +
                ", createTime=" + createTime +
                ", serialNum='" + serialNum + '\'' +
                '}';
    }
}