package model.dto;

/**
 * Created by yeran on 2017/5/5.
 */
public class DeviceBuyRecord extends model.DeviceBuyRecord{
    private String account;
    private String username;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
