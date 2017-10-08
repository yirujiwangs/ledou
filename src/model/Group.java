package model;

public class Group {
    private Integer id;

    private String groopname;

    private String phonenum;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGroopname() {
        return groopname;
    }

    public void setGroopname(String groopname) {
        this.groopname = groopname == null ? null : groopname.trim();
    }

    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum == null ? null : phonenum.trim();
    }
}