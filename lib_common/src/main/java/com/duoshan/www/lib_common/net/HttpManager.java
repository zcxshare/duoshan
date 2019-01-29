package com.duoshan.www.lib_common.net;

import android.text.TextUtils;
import android.util.Log;

import com.duoshan.www.lib_common.net.interceptor.HttpLogInterceptor;

import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.fastjson.FastJsonConverterFactory;

/**
 * author:  zhouchaoxiang
 * date:    2018/11/1
 * explain:
 */
public class HttpManager {
    private static String mBaseUrl = "";
    private static long mWriteTimeout = 5;//单位s
    private static long mReadTimeout = 5;
    private final Retrofit mRetrofit;

    private HttpManager() {
        mRetrofit = getRetrofit(mBaseUrl);
    }

    public static class Instance {
        private static final HttpManager instance = new HttpManager();
    }

    private Retrofit getRetrofit(String baseUrl) {
        return getRetrofit(baseUrl, getDefaultClient());
    }

    private Retrofit getRetrofit(String baseUrl, OkHttpClient client) {
        return getRetrofit(baseUrl, client, FastJsonConverterFactory.create());
    }

    /**
     * @param factory 解析工厂
     */
    private Retrofit getRetrofit(String baseUrl, OkHttpClient client, Converter.Factory factory) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(factory)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public <T> T getApi(final Class<T> service) {
        if (null == mRetrofit) {
            throw new NullPointerException("retrofit为null");
        }
        return mRetrofit.create(service);
    }

    public OkHttpClient getDefaultClient() {
        return getOkhttpClient(mWriteTimeout, mReadTimeout);
    }

    public OkHttpClient getOkhttpClient(Interceptor... interceptors) {
        return getOkhttpClient(mWriteTimeout, mReadTimeout, interceptors);
    }

    /**
     * @param writeTimeout 写超时时间单位秒
     * @param readTimeout  读超时时间单位秒
     * @param interceptors 添加额外拦截器
     */
    public OkHttpClient getOkhttpClient(long writeTimeout, long readTimeout, Interceptor... interceptors) {
        HttpLogInterceptor logInterceptor = new HttpLogInterceptor(new HttpLogInterceptor.Logger() {
            @Override
            public void log(@NonNull String message) {
                if (!TextUtils.isEmpty(message) && !message.contains("users/token")) {
                    Log.i("http", message);
                }
            }
        });
        logInterceptor.setLevel(HttpLogInterceptor.Level.BODY);
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .writeTimeout(writeTimeout, TimeUnit.SECONDS)
                .readTimeout(readTimeout, TimeUnit.SECONDS)
                .addInterceptor(logInterceptor);
        for (Interceptor interceptor : interceptors) {
            builder.addInterceptor(interceptor);
        }
        return builder.build();
    }

    public static <RESPONSE> Observable<RESPONSE> requestHandler(Observable<RESPONSE> response) {
        return response.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static String getmBaseUrl() {
        return mBaseUrl;
    }

    public static void setmBaseUrl(String mBaseUrl) {
        HttpManager.mBaseUrl = mBaseUrl;
    }

    public static long getmWriteTimeout() {
        return mWriteTimeout;
    }

    public static void setmWriteTimeout(long mWriteTimeout) {
        HttpManager.mWriteTimeout = mWriteTimeout;
    }

    public static long getmReadTimeout() {
        return mReadTimeout;
    }

    public static void setmReadTimeout(long mReadTimeout) {
        HttpManager.mReadTimeout = mReadTimeout;
    }
}
