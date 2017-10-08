package model.divide;


import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by yeran on 2016/8/12.
 */
public class BaseDivideModel extends KeySearchModel{
    /**
     * 分页
     */
    @JSONField(serialize = false)
    public boolean limitValue;

    @JSONField(serialize = false)
    public int startPage=1;

    @JSONField(serialize = false)
    public int pageSize = 15;

    @JSONField(serialize = false)
    public int startSize=0;

    public void setDividePage(Integer startPage,Integer pageSize){
        limitValue=true;
        setPageSize(pageSize);
        setStartPage(startPage);
    }

    public int getStartPage() {
        return startPage;
    }

    public void setStartPage(Integer startPage) {
        if (startPage!=null) {
            this.startPage = startPage-1;
            this.startSize = this.startPage * pageSize;
        }
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        if (pageSize!=null)
        this.pageSize = pageSize;
    }

    public int getStartSize() {
        return startSize;
    }

    public void setStartSize(Integer startSize) {
        if (startSize!=null)
        this.startSize = startSize;
    }
}
