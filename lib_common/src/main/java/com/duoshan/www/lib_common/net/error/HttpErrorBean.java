package com.duoshan.www.lib_common.net.error;

/**
 * author:  zhouchaoxiang
 * date:    2018/7/25
 * explain:
 */
public class HttpErrorBean extends Exception {
    /**
     * 未知错误
     */
    public static final int CODE_ERROR_UNKNOWN = 1000;
    /**
     * 解析错误
     */
    public static final int CODE_ERROR_PARSE = 1001;
    /**
     * 解析no content错误
     */
    public static final int CODE_ERROR_PARSE_EMPTY = 1007;
    /**
     * 网络错误
     */
    public static final int CODE_ERROR_NETWORD = 1002;
    /**
     * 协议出错
     */
    public static final int CODE_ERROR_HTTP = 1003;

    /**
     * 证书出错
     */
    public static final int CODE_ERROR_SSL = 1005;

    /**
     * 连接超时
     */
    public static final int CODE_ERROR_TIMEOUT = 1006;
    public static final int CODE_ERROR_LOGIN = -1000;
    public static final int CODE_ERROR_DATA_EMPTY = -2000;


    public HttpErrorBean(Throwable cause,int code) {
        super(cause);
        this.code = code;
    }

    public HttpErrorBean(String message, int code, long timestamp) {
        this.message = message;
        this.code = code;
        this.timestamp = timestamp;
    }

    /**
     * message : 库存记录模块库存记录找不到
     * code : 74002
     * timestamp : 1532506506092
     */

    private String message;
    private int code;
    private long timestamp;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
