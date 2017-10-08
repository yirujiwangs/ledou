package MessageQueue;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * Created by yeran on 2016/10/29.
 *
 * 消息队列:内存维护（后期转为独立服务，使用BeansTalk或者KafKa实现）
 */
public class MessageQueue {
    /**
     * 队列优先级，0为最高，LEVEL值越高则优先级越低
     */
    public static final Integer MESSAGE_LEVEL = 0;

    /**
     * 普通消息队列
     */
    private static List<Queue> normalQueue;

    static {
        normalQueue = new ArrayList<>();
    }
}
