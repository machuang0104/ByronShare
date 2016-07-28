package com.byron.library.log;

import com.byron.library.AppConfig;
import com.byron.library.ConfigLog;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

/**
 * Created by sinyoo on 2016/7/15.
 */
public class LogUtil {
    public static void Http(String msg) {
        d(ConfigLog.TAG_HTTP, msg);
    }

    public static void Activity(String msg) {
        d(ConfigLog.TAG_ACTIVITY, msg);
    }

    public static void Fragment(String msg) {
        d(ConfigLog.TAG_FRAGMENT, msg);
    }

    /**
     * 数据库
     */
    public static void DB(String msg) {
        d(ConfigLog.TAG_DB, msg);
    }

    /**
     * 事件
     */
    public static void Event(String msg) {
        d(ConfigLog.TAG_EVENT, msg);
    }

    /**
     * 广播
     */
    public static void Broad(String msg) {
        d(ConfigLog.TAG_BROADCAST, msg);
    }

    /**
     * 融云日志
     */
    public static void Rong(String msg) {
        d(ConfigLog.TAG_RONG, msg);
    }

    /**
     * Priority constant for the println method; use Logger.v.
     */
    public static final int VERBOSE = 2;

    /**
     * Priority constant for the println method; use Logger.d.
     */
    public static final int DEBUG = 3;

    /**
     * Priority constant for the println method; use Logger.i.
     */
    public static final int INFO = 4;

    /**
     * Priority constant for the println method; use Logger.w.
     */
    public static final int WARN = 5;

    /**
     * Priority constant for the println method; use Logger.e.
     */
    public static final int ERROR = 6;
    /**
     * Priority constant for the println method.
     */
    public static final int ASSERT = 7;
    private static HashMap<String, ILogger> loggerHashMap = new HashMap<String, ILogger>();
    private static final ILogger defaultLogger = new PrintToLogCatLogger();

    // ============跟踪日志 begin===============================

    public static void d(String tag, Object object, String message) {
        d(tag, "track:" + object.getClass().getSimpleName() + "-->" + message);
    }

    public static void e(String tag, Object object, String message) {
        e(tag, "track:" + object.getClass().getSimpleName() + "-->" + message);
    }

    public static void i(String tag, Object object, String message) {
        i(tag, "track:" + object.getClass().getSimpleName() + "-->" + message);
    }

    public static void v(String tag, Object object, String message) {
        v(tag, "track:" + object.getClass().getSimpleName() + "-->" + message);
    }

    public static void w(String tag, Object object, String message) {
        w(tag, "track:" + object.getClass().getSimpleName() + "-->" + message);
    }

    // ============跟踪日志 end===============================
    public static void d(Object object, String message) {

        printLoger(DEBUG, object, message);

    }

    public static void e(Object object, String message) {

        printLoger(ERROR, object, message);

    }

    public static void i(Object object, String message) {

        printLoger(INFO, object, message);

    }

    public static void v(Object object, String message) {

        printLoger(VERBOSE, object, message);

    }

    public static void w(Object object, String message) {

        printLoger(WARN, object, message);

    }

    public static void d(String tag, String message) {

        printLoger(DEBUG, tag, message);

    }

    public static void e(String tag, String message) {

        printLoger(ERROR, tag, message);

    }

    public static void i(String tag, String message) {

        printLoger(INFO, tag, message);

    }

    public static void v(String tag, String message) {

        printLoger(VERBOSE, tag, message);

    }

    public static void w(String tag, String message) {

        printLoger(WARN, tag, message);

    }

    public static void println(int priority, String tag, String message) {
        printLoger(priority, tag, message);
    }

    private static void printLoger(int priority, Object object, String message) {
        Class<?> cls = object.getClass();
        String tag = cls.getName();
        String arrays[] = tag.split("\\.");
        tag = arrays[arrays.length - 1];
        printLoger(priority, tag, message);
    }

    private static void printLoger(int priority, String tag, String message) {
        if (AppConfig.IS_DEBUG) {
            printLoger(defaultLogger, priority, tag, message);
            Iterator<Entry<String, ILogger>> iter = loggerHashMap.entrySet()
                    .iterator();
            while (iter.hasNext()) {
                Entry<String, ILogger> entry = iter.next();
                ILogger logger = entry.getValue();
                if (logger != null) {
                    printLoger(logger, priority, tag, message);
                }
            }
        }
    }

    private static void printLoger(ILogger logger, int priority, String tag,
                                   String message) {

        switch (priority) {
            case VERBOSE:
                logger.v(tag, message);
                break;
            case DEBUG:
                logger.d(tag, message);
                break;
            case INFO:
                logger.i(tag, message);
                break;
            case WARN:
                logger.w(tag, message);
                break;
            case ERROR:
                logger.e(tag, message);
                break;
            default:
                break;
        }
    }
}
