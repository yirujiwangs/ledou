package model;

public class DevicePrice {
    private String type;

    private Integer price;

    private String comment;

    private String proxy_token;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    public String getProxy_token() {
        return proxy_token;
    }

    public void setProxy_token(String proxy_token) {
        this.proxy_token = proxy_token == null ? null : proxy_token.trim();
    }
}