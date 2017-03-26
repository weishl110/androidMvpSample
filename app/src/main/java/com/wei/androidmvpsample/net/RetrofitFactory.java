package com.wei.androidmvpsample.net;

import android.os.Environment;

import com.wei.androidmvpsample.BuildConfig;
import com.wei.androidmvpsample.GloBalContext;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ${wei} on 2017/3/25.
 * <p>
 * 如果baseUrl多，可以让此类为抽象类，由子类实现返回baseUrl
 */

public class RetrofitFactory {


    //http://103.235.228.69:2020/xire-app-user/banner/getBannerInfo/0?user_id=T000003652&vt_user_id=VTU000001032&regist_id=T000003652&user_type=3&app_version=2.3.6
    public static String baseUrl = "http://103.235.228.69:2020/";
    public static Retrofit retrofit;

    /**
     * 默认的IP和port
     *
     * @return
     */
    public static IApiService createIApiService() {
        return createIApiService(baseUrl);
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    /**
     * @param baseUrl IP + port
     * @return
     */
    public static IApiService createIApiService(String baseUrl) {
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
     * 创建okhttpClient,添加设置信息,缓存设置,请求消息头
     */
    private static OkHttpClient createOkhttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        //缓存路径
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
                .addInterceptor(new AuthInterceptor());
//                .addInterceptor(new ResponseInterceptor())
//                .addNetworkInterceptor(new MyNetWorkInterceptor());
        return builder.build();
    }


    /**
     * 请求拦截器
     */
    private static class AuthInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request origin = chain.request();

            android.util.Log.e("interceptor", "intercept: url = " + origin.method()+"   url 11111= "+origin.url().toString());
            //创建需要的请求header
            Headers.Builder newHeaders = new Headers.Builder();

            //向请求头写入Content-Type参数，设置参数搁置为application/json
//            newHeaders.add("Content-Type", "application/json;charset=UTF-8");
//            Request.Builder newRequest = origin.newBuilder()
//                    .headers(newHeaders.build());

            return chain.proceed(origin);
        }
    }


    /**
     * 如果不用缓存，可以不设置 请求体中添加默认的请求信息等都在interceptor中添加
     * 设置网络缓存
     */
    public static class MyNetWorkInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Headers headers = request.headers();
            Response proceed = chain.proceed(request);
            return null;
        }
    }

    /**
     * 相应拦截器
     */
    private static class ResponseInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Response response = chain.proceed(request);
            ResponseBody body = response.body();
            return chain.proceed(request);
        }
    }
}
