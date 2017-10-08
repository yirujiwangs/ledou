package utils.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yeran on 2016/10/3.
 * <p/>
 * Log 日志记录工具类
 */
public class LogUtil {

    static Map<Class, Logger> loggerMap = new HashMap<>();

    public enum LogType {
        SUCCESS, INFO, ERROR, WARN
    }

    /**
     * 日志记录
     *
     * @param cls
     * @param logType
     * @param msg
     */
    public static void log(Class cls, LogType logType, String msg) {
        log(cls, logType, msg, "");
    }

    /**
     * 日志记录
     *
     * @param cls
     * @param logType
     * @param title
     * @param object
     */
    public static void log(final Class cls,final LogType logType,final String title,final Object object) {
       /* if (cls == null)
            cls = LogUtil.class;
        if (logType == null)
            logType = LogType.INFO;
        if (title == null)
            title = "";
        if (object == null)
            object = "";*/
        ThreadPoolUtil.addPool(new Runnable() {
            @Override
            public void run() {
                if (loggerMap.get(cls) == null) {
                    Logger logger = LoggerFactory.getLogger(cls);
                    loggerMap.put(cls, logger);
                }

                switch (logType) {
                    case SUCCESS:
                        loggerMap.get(cls).info("/--success--/" + title, object.toString());
                        break;
                    case INFO:
                        loggerMap.get(cls).info("/--info--/" + title, object);
                        break;
                    case ERROR:
                        loggerMap.get(cls).error("/--error--/" + title, object);
                        break;
                    case WARN:
                        loggerMap.get(cls).warn("/--warn--/" + title, object);
                        break;
                    default:
                        loggerMap.get(cls).info("/--info--/" + title, object);
                }
            }
        });
    }
}
