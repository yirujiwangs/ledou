package model.base;

import com.alibaba.fastjson.annotation.JSONField;
import utils.common.DividePageUtil;

import java.util.List;

/**
 * Created by yeran on 2017/2/22.
 * <p/>
 * 分页基础类
 */
public class DivideBaseResult<T> {

    /**
     * 当前页
     */
    private Integer page = 1;

    /**
     * 每页数量
     */
    private Integer pageSize = 15;

    /**
     * 总页数
     */
    private Integer pages = 1;

    /**
     * 总数量
     */
    @JSONField(serialize = false)
    private Integer counts = 0;

    private List<T> list;

    public DivideBaseResult() {
    }

    public DivideBaseResult(Integer page, Integer pageSize, Integer counts, List<T> list) {
        this.page = page;
        this.pageSize = pageSize;
        this.counts = counts;
        this.list = list;

        this.pages = DividePageUtil.getPages(counts, pageSize);
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public Integer getCounts() {
        return counts;
    }

    public void setCounts(Integer counts) {
        this.counts = counts;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
