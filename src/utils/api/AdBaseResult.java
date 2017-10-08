package utils.api;

/**
 * Created by yeran on 2016/10/18.
 *
 * 广告平台基础结果类
 */
public class AdBaseResult {

    public static final Integer RESULT_ERROR=-1;
    public static final Integer RESULT_SUCCESS=0;

    private Integer error=-1;
    private String error_reason;
    private Object object;

    public AdBaseResult() {
    }

    public AdBaseResult(Integer error, String error_reason) {
        this.error = error;
        this.error_reason = error_reason;
    }

    public AdBaseResult(Integer error) {

        this.error = error;
    }

    public AdBaseResult(Integer error, String error_reason, Object object) {
        this.error = error;
        this.error_reason = error_reason;
        this.object = object;
    }

    public Integer getError() {
        return error;
    }

    public void setError(Integer error) {
        this.error = error;
    }

    public String getError_reason() {
        return error_reason;
    }

    public void setError_reason(String error_reason) {
        this.error_reason = error_reason;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }


    public boolean success(){
        return RESULT_SUCCESS.intValue() == this.error;
    }
}
