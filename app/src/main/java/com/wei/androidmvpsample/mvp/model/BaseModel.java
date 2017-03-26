package com.wei.androidmvpsample.mvp.model;


import com.wei.androidmvpsample.mvp.presenter.BasePresenter;
import com.wei.androidmvpsample.net.IApiService;
import com.wei.androidmvpsample.net.OkhttpRequest;
import com.wei.androidmvpsample.net.RetrofitFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by ${wei} on 2017/3/26.
 */

public abstract class BaseModel<P extends BasePresenter> {
    protected static final ExecutorService executorService = Executors.newFixedThreadPool(5);
    protected final P presenter;
    protected final OkhttpRequest okhttpRequest;
    private final IApiService iApiService;

    public BaseModel(P presenter) {
        this.presenter = presenter;
        okhttpRequest = OkhttpRequest.getInstance();
        iApiService = RetrofitFactory.createIApiService();
    }

    public abstract void start();

    /**
     * 网络请求，读取数据库
     */
    public abstract void loadData();

    /**
     * 在线程池中操作，发生在子线程
     */
    public void newThread(Runnable runnable) {
        executorService.execute(runnable);
    }

    public void onDestory() {
        if (okhttpRequest != null) {
            okhttpRequest.onUnSubscribe();
        }
    }
}
