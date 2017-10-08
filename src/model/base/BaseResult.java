package model.base;

/**
 * Created by yeran on 2016/10/2.
 *
 * 基础返回类
 */
public class BaseResult {

    public static final Integer RESULT_ERROR=-1;
    public static final Integer RESULT_SUCCESS=1;
    public static final Integer RESULT_SPECIL=0;

    private int errcode;
    private String errmsg;
    private Object object;
    private long time;

    public BaseResult() {
        this.errcode = RESULT_ERROR;
        this.errmsg = "系统繁忙";
        this.time = System.currentTimeMillis();
    }

    public BaseResult(int errcode, String errmsg, Object object, long time) {
        this.errcode = errcode;
        this.errmsg = errmsg;
        this.object = object;
        this.time = time;
    }

    public BaseResult(int errcode, String errmsg) {
        this.errcode = errcode;
        this.errmsg = errmsg;
        this.time = System.currentTimeMillis();
    }

    public BaseResult(int errcode, String errmsg, Object object) {
        this.errcode = errcode;
        this.errmsg = errmsg;
        this.object = object;
        this.time = System.currentTimeMillis();
    }

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
