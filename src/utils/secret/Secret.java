package utils.secret;

/**
 * Created by yeran on 2016/10/27.
 */
public class Secret {
    private String key;
    private String result;

    public Secret() {
    }

    public Secret(String key, String result) {
        this.key = key;
        this.result = result;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
