package com.duoshan.www.lib_common.net.error;

import android.net.ParseException;


//import com.alibaba.fastjson.JSON;

import org.json.JSONException;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;

//import okhttp3.ResponseBody;
//import retrofit2.HttpException;

/**
 * 错误帮助类
 * Created by Administrator on 2018/4/25.
 */

public class HttpErrorHandle {
    private static final int UNAUTHORIZED = 401;
    private static final int FORBIDDEN = 403;
    private static final int NOT_FOUND = 404;
    private static final int REQUEST_TIMEOUT = 408;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int BAD_GATEWAY = 502;
    private static final int SERVICE_UNAVAILABLE = 503;
    private static final int GATEWAY_TIMEOUT = 504;
    private static final int FAIL_QUEST = 406;//无法使用请求的内容特性来响应请求的网页
    private static final int BAD_REQUEST = 400;

    public static HttpErrorBean handleException(Throwable e) {
        HttpErrorBean ex = null;
        /*if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            ex = new HttpErrorBean(e, HttpErrorBean.CODE_ERROR_UNKNOWN);
            switch (httpException.code()) {
                case UNAUTHORIZED:
                    break;
                case FORBIDDEN:
                    ex.setMessage("服务器已经理解请求，但是拒绝执行它");
                    break;
                case NOT_FOUND:
                    ex.setMessage("服务器异常，请稍后再试");
                    break;
                case REQUEST_TIMEOUT:
                    ex.setMessage("请求超时");
                    break;
                case GATEWAY_TIMEOUT:
                case INTERNAL_SERVER_ERROR:
                    ex.setMessage("服务器遇到了一个未曾预料的状况，无法完成对请求的处理");
                    break;
                case BAD_REQUEST:
                    break;
                case BAD_GATEWAY:
                case SERVICE_UNAVAILABLE:
                case FAIL_QUEST:
                    ResponseBody body = ((HttpException) e).response().errorBody();
                    if (body == null) break;
                    try {
                        String message = body.string();
                        HttpErrorBean errorBean = JSON.parseObject(message, HttpErrorBean.class);
                        if (errorBean.getMessage() != null) {
                            ex.setMessage("服务器已经理解请求，但是拒绝执行它");
                        } else {
                            ex.setMessage("");
                        }
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    break;
                default:
                    ex.setMessage("网络错误");
                    break;
            }
            return ex;
        } else if (e instanceof com.alibaba.fastjson.JSONException || e instanceof JSONException || e instanceof ParseException) {
            ex = new HttpErrorBean(e, HttpErrorBean.CODE_ERROR_PARSE);
            ex.setMessage("解析错误");
            return ex;
        } else if (e instanceof ConnectException) {
            ex = new HttpErrorBean(e, HttpErrorBean.CODE_ERROR_NETWORD);
            ex.setMessage("连接失败");
            return ex;
        } else if (e instanceof SSLHandshakeException) {
            ex = new HttpErrorBean(e, HttpErrorBean.CODE_ERROR_SSL);
            ex.setMessage("证书验证失败");
            return ex;
        } else if (e instanceof SocketTimeoutException) {
            ex = new HttpErrorBean(e, HttpErrorBean.CODE_ERROR_TIMEOUT);
            //ex.setMessage("连接超时");
            ex.setMessage("当前网络连接不顺畅，请稍后再试！");
            return ex;
        } else if (e instanceof UnknownHostException) {
            ex = new HttpErrorBean(e, HttpErrorBean.CODE_ERROR_TIMEOUT);
            //ex.setMessage("连接超时");
            ex.setMessage("网络中断，请检查网络状态！");
            return ex;
        } else if (e instanceof SSLException) {
            ex = new HttpErrorBean(e, HttpErrorBean.CODE_ERROR_TIMEOUT);
            ex.setMessage("网络中断，请检查网络状态！");
            return ex;
        } else if (e instanceof java.io.EOFException) {
            ex = new HttpErrorBean(e, HttpErrorBean.CODE_ERROR_PARSE_EMPTY);
            ex.setMessage("1007");
            return ex;
        } else if (e instanceof NullPointerException) {
            ex = new HttpErrorBean(e, HttpErrorBean.CODE_ERROR_PARSE_EMPTY);
            ex.setMessage("数据为空，显示失败");
            return ex;
        } else if (e instanceof ServerException) {
            ServerException resultException = (ServerException) e;
            ex = new HttpErrorBean(resultException, resultException.code);
            ex.setMessage(resultException.message);
            return ex;
        } else {
            ex = new HttpErrorBean(e, HttpErrorBean.CODE_ERROR_UNKNOWN);
            ex.setMessage("未知错误");
            return ex;
        }*/
        return ex;

    }

    /**
     * 自定义错误
     */
    public class ServerException extends RuntimeException {
        public int code;
        public String message;

        public ServerException(int code, String message) {
            this.code = code;
            this.message = message;
        }
    }

}
