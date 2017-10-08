package model.dto;

/**
 * Created by Administrator on 2016/10/7.
 */
public class WithdrawalInfo {
    private Integer id;
    private String type;
    private Integer once_money;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getOnce_money() {
        return once_money;
    }

    public void setOnce_money(Integer once_money) {
        this.once_money = once_money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
