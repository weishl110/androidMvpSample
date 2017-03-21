package com.wei.androidmvpsample.net;

import android.os.Environment;

import com.wei.androidmvpsample.BuildConfig;
import com.wei.androidmvpsample.GloBalContext;
import com.wei.androidmvpsample.bean.ResultBean;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ${wei} on 2017/3/20.
 */

public class OkhttpRequest {

    private OkhttpRequest instance = null;

    private static String baseUrl = "";
    private static Retrofit retrofit;

    /**
     * 默认的IP和port
     *
     * @return
     */
    public IApiService createIApiService() {
        return createIApiService(baseUrl);
    }

    /**
     * @param baseUrl IP + port
     * @return
     */
    public IApiService createIApiService(String baseUrl) {
        if (retrofit == null) {
            Retrofit.Builder builder = new Retrofit.Builder();
            builder.baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(createOkhttpClient());
            retrofit = builder.build();
        }
        return retrofit.create(IApiService.class);
    }


    /**
     * 创建okhttpClient,添加设置信息
     */
    private OkHttpClient createOkhttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        File sdCardPath;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            sdCardPath = new File(Environment.getExternalStorageDirectory().getAbsoluteFile(), "cacheData");
        } else {
            sdCardPath = new File(GloBalContext.context.getCacheDir(), "cacheData");
        }
        //在debug模式下添加log信息
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
            builder.addInterceptor(loggingInterceptor);
        }
        //添加网络缓存
        Cache cache = new Cache(sdCardPath, 10 * 1024 * 1024);
        builder.cache(cache)
                //添加超时时间
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .addNetworkInterceptor(new MyNetWorkInterceptor());
        return builder.build();
    }

    public <T> void url(Observable observable, final ResultCallback<T> callback) {
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResultBean<T>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(new MyException(e.getMessage()));
                    }

                    @Override
                    public void onNext(ResultBean<T> resultBean) {
                        callback.response(resultBean.getDate());
                    }
                });
    }

    /**
     * 如果不用缓存，可以不设置 请求体中添加默认的请求信息等都在interceptor中添加
     * 设置网络缓存
     */
    public static class MyNetWorkInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Response proceed = chain.proceed(request);
            return null;
        }
    }
}
