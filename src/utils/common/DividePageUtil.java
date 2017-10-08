package utils.common;

/**
 * Created by yeran on 2016/8/12.
 */
public class DividePageUtil {

    /**
     *
     * count = n*p - x    x代表未满的差额 x范围 [0-p-1）,代表页满以及页只有一个元素的情况
     *                    n代表页数；
     *                    p代表页包含item数量
     *                    则：n的最大值为：(c+x)/p
     *
     * @param count
     * @param pageSize
     * @return
     */
    public static int getPages(int count, Integer pageSize) {
        int pages = 1, pagesize = 15;
        if (pageSize != null)
            pagesize = pageSize;
        if (count > 0) {
            pages = (count + pagesize - 1) / pagesize;
        }
        return pages;
    }

    public static int getPages_v2(int count, Integer pageSize) {
        int pages = 1, pagesize = 15;
        if (pageSize != null)
            pagesize = pageSize;
        if (count > 0) {
            pages = (int) Math.ceil(count / pagesize);
        }
        return pages;
    }

    public static int getPages_v1(int count, Integer pageSize) {
        int pages = 1, pagesize = 15;
        if (pageSize != null)
            pagesize = pageSize;
        if (count > 0) {
            pages = count / pagesize;
            if (count % pagesize > 0)
                ++pages;
        }
        return pages;
    }
}
