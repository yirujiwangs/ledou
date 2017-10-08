package model;

import java.util.Date;

public class User {
    private Integer id;

    private String utoken;

    private String phonenum;

    private String storename;

    private String passwd;

    private String storetype;

    private String status;

    private String weixinstatus;

    private Integer maxgroupnum;

    private Integer maxstorenum;

    private Integer platformid;

    private Integer userinfoid;

    private String wxAppid;

    private Date createtime;

    private Date lastmodifytime;

    private Integer level;

    private Integer corporationid;

    private storeTypeEnum storeTypeEnum;

    private String descr;

    public enum storeTypeEnum {
        UNINIT,
        NORMAL,
        PLATFORM,
        CONTROLCENTER
    }
    
    
    
    
    
    
    
    
    
    
    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((corporationid == null) ? 0 : corporationid.hashCode());
		result = prime * result
				+ ((createtime == null) ? 0 : createtime.hashCode());
		result = prime * result + ((descr == null) ? 0 : descr.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((lastmodifytime == null) ? 0 : lastmodifytime.hashCode());
		result = prime * result + ((level == null) ? 0 : level.hashCode());
		result = prime * result
				+ ((maxgroupnum == null) ? 0 : maxgroupnum.hashCode());
		result = prime * result
				+ ((maxstorenum == null) ? 0 : maxstorenum.hashCode());
		result = prime * result + ((passwd == null) ? 0 : passwd.hashCode());
		result = prime * result
				+ ((phonenum == null) ? 0 : phonenum.hashCode());
		result = prime * result
				+ ((platformid == null) ? 0 : platformid.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result
				+ ((storeTypeEnum == null) ? 0 : storeTypeEnum.hashCode());
		result = prime * result
				+ ((storename == null) ? 0 : storename.hashCode());
		result = prime * result
				+ ((storetype == null) ? 0 : storetype.hashCode());
		result = prime * result
				+ ((userinfoid == null) ? 0 : userinfoid.hashCode());
		result = prime * result + ((utoken == null) ? 0 : utoken.hashCode());
		result = prime * result
				+ ((weixinstatus == null) ? 0 : weixinstatus.hashCode());
		result = prime * result + ((wxAppid == null) ? 0 : wxAppid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (corporationid == null) {
			if (other.corporationid != null)
				return false;
		} else if (!corporationid.equals(other.corporationid))
			return false;
		if (createtime == null) {
			if (other.createtime != null)
				return false;
		} else if (!createtime.equals(other.createtime))
			return false;
		if (descr == null) {
			if (other.descr != null)
				return false;
		} else if (!descr.equals(other.descr))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastmodifytime == null) {
			if (other.lastmodifytime != null)
				return false;
		} else if (!lastmodifytime.equals(other.lastmodifytime))
			return false;
		if (level == null) {
			if (other.level != null)
				return false;
		} else if (!level.equals(other.level))
			return false;
		if (maxgroupnum == null) {
			if (other.maxgroupnum != null)
				return false;
		} else if (!maxgroupnum.equals(other.maxgroupnum))
			return false;
		if (maxstorenum == null) {
			if (other.maxstorenum != null)
				return false;
		} else if (!maxstorenum.equals(other.maxstorenum))
			return false;
		if (passwd == null) {
			if (other.passwd != null)
				return false;
		} else if (!passwd.equals(other.passwd))
			return false;
		if (phonenum == null) {
			if (other.phonenum != null)
				return false;
		} else if (!phonenum.equals(other.phonenum))
			return false;
		if (platformid == null) {
			if (other.platformid != null)
				return false;
		} else if (!platformid.equals(other.platformid))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (storeTypeEnum != other.storeTypeEnum)
			return false;
		if (storename == null) {
			if (other.storename != null)
				return false;
		} else if (!storename.equals(other.storename))
			return false;
		if (storetype == null) {
			if (other.storetype != null)
				return false;
		} else if (!storetype.equals(other.storetype))
			return false;
		if (userinfoid == null) {
			if (other.userinfoid != null)
				return false;
		} else if (!userinfoid.equals(other.userinfoid))
			return false;
		if (utoken == null) {
			if (other.utoken != null)
				return false;
		} else if (!utoken.equals(other.utoken))
			return false;
		if (weixinstatus == null) {
			if (other.weixinstatus != null)
				return false;
		} else if (!weixinstatus.equals(other.weixinstatus))
			return false;
		if (wxAppid == null) {
			if (other.wxAppid != null)
				return false;
		} else if (!wxAppid.equals(other.wxAppid))
			return false;
		return true;
	}

	
	
	
	
	
	
	@Override
	public String toString() {
		return "User [id=" + id + ", utoken=" + utoken + ", phonenum="
				+ phonenum + ", storename=" + storename + ", passwd=" + passwd
				+ ", storetype=" + storetype + ", status=" + status
				+ ", weixinstatus=" + weixinstatus + ", maxgroupnum="
				+ maxgroupnum + ", maxstorenum=" + maxstorenum
				+ ", platformid=" + platformid + ", userinfoid=" + userinfoid
				+ ", wxAppid=" + wxAppid + ", createtime=" + createtime
				+ ", lastmodifytime=" + lastmodifytime + ", level=" + level
				+ ", corporationid=" + corporationid + ", storeTypeEnum="
				+ storeTypeEnum + ", descr=" + descr + "]";
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

    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum == null ? null : phonenum.trim();
    }

    public String getStorename() {
        return storename;
    }

    public void setStorename(String storename) { 
        this.storename = storename == null ? null : storename.trim();
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd == null ? null : passwd.trim();
    }

    public String getStoretype() {
        return storetype;
    }

    public void setStoretype(String storetype) {
        this.storetype = storetype == null ? null : storetype.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getWeixinstatus() {
        return weixinstatus;
    }

    public void setWeixinstatus(String weixinstatus) {
        this.weixinstatus = weixinstatus == null ? null : weixinstatus.trim();
    }

    public Integer getMaxgroupnum() {
        return maxgroupnum;
    }

    public void setMaxgroupnum(Integer maxgroupnum) {
        this.maxgroupnum = maxgroupnum;
    }

    public Integer getMaxstorenum() {
        return maxstorenum;
    }

    public void setMaxstorenum(Integer maxstorenum) {
        this.maxstorenum = maxstorenum;
    }

    public Integer getPlatformid() {
        return platformid;
    }

    public void setPlatformid(Integer platformid) {
        this.platformid = platformid;
    }

    public Integer getUserinfoid() {
        return userinfoid;
    }

    public void setUserinfoid(Integer userinfoid) {
        this.userinfoid = userinfoid;
    }

    public String getWxAppid() {
        return wxAppid;
    }

    public void setWxAppid(String wxAppid) {
        this.wxAppid = wxAppid == null ? null : wxAppid.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getLastmodifytime() {
        return lastmodifytime;
    }

    public void setLastmodifytime(Date lastmodifytime) {
        this.lastmodifytime = lastmodifytime;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getCorporationid() {
        return corporationid;
    }

    public void setCorporationid(Integer corporationid) {
        this.corporationid = corporationid;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr == null ? null : descr.trim();
    }

    public storeTypeEnum getStoreTypeEnum() {
        if (storeTypeEnum == null) {
            storeTypeEnum = storeTypeEnum.valueOf(storetype);
        }
        return storeTypeEnum;
    }

    public void setStoreTypeEnum(storeTypeEnum storeTypeEnum) {
        this.storeTypeEnum = storeTypeEnum;
        storetype = storeTypeEnum.name();
    }
}