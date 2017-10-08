package model;

import java.util.Date;

public class ProxyAreaReport {
    private Integer id;

    private String name;

    private Integer rid;

    private Integer pid;

    private String province;

    private String city;

    private String fullname;

    private Integer province_rid;

    private Integer city_rid;

    private Integer area_level;

    private Double lat;

    private Double lng;

    private String state;

    private Integer report_num;

    private Integer last_time;

    private Date createtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname == null ? null : fullname.trim();
    }

    public Integer getProvince_rid() {
        return province_rid;
    }

    public void setProvince_rid(Integer province_rid) {
        this.province_rid = province_rid;
    }

    public Integer getCity_rid() {
        return city_rid;
    }

    public void setCity_rid(Integer city_rid) {
        this.city_rid = city_rid;
    }

    public Integer getArea_level() {
        return area_level;
    }

    public void setArea_level(Integer area_level) {
        this.area_level = area_level;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public Integer getReport_num() {
        return report_num;
    }

    public void setReport_num(Integer report_num) {
        this.report_num = report_num;
    }

    public Integer getLast_time() {
        return last_time;
    }

    public void setLast_time(Integer last_time) {
        this.last_time = last_time;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}