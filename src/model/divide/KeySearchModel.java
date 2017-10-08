package model.divide;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by yeran on 2016/8/16.
 *
 * ¹Ø¼ü´ÊËÑË÷
 */
public class KeySearchModel {
    @JSONField(serialize = false)
    public boolean isLikeValue;

    @JSONField(serialize = false)
    public String keyword;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
        isLikeValue=true;
    }
}
