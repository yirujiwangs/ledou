package model;

import java.util.Date;

public class History {
    private String id;

    private Date createTime;

    private String username;

    private String phonenum;

    private String type;

    private String content;

    private String adminname;

    private String adminaccount;
    
    public History() {
		super();
		// TODO Auto-generated constructor stub
	}
    
	public History(String id, Date createTime, String username,
			String phonenum, String type, String content, String adminname,
			String adminaccount) {
		super();
		this.id = id;
		this.createTime = createTime;
		this.username = username;
		this.phonenum = phonenum;
		this.type = type;
		this.content = content;
		this.adminname = adminname;
		this.adminaccount = adminaccount;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum == null ? null : phonenum.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getAdminname() {
        return adminname;
    }

    public void setAdminname(String adminname) {
        this.adminname = adminname == null ? null : adminname.trim();
    }

    public String getAdminaccount() {
        return adminaccount;
    }

    public void setAdminaccount(String adminaccount) {
        this.adminaccount = adminaccount == null ? null : adminaccount.trim();
    }
}