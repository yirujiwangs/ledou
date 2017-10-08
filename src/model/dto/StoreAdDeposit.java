package model.dto;

/**
 * Created by yeran on 2017/5/5.
 */
public class StoreAdDeposit {

    private String nickname;
    private String headimgurl;

    private Integer fee;

    private String comment;

    private String time;

    private String type_sec;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public Integer getFee() {
        return fee;
    }

    public void setFee(Integer fee) {
        this.fee = fee;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getType_sec() {
        return type_sec;
    }

    public void setType_sec(String type_sec) {
        this.type_sec = type_sec;
    }
}
