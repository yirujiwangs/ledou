package model;

public class Token {
    private Integer id;

    private String accesstoken;

    private Integer expiresin;

    private long firstgettime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccesstoken() {
        return accesstoken;
    }

    public void setAccesstoken(String accesstoken) {
        this.accesstoken = accesstoken == null ? null : accesstoken.trim();
    }

    public Integer getExpiresin() {
        return expiresin;
    }

    public void setExpiresin(Integer expiresin) {
        this.expiresin = expiresin;
    }

    public long getFirstgettime() {
        return firstgettime;
    }

    public void setFirstgettime(long firstgettime) {
        this.firstgettime = firstgettime;
    }
}