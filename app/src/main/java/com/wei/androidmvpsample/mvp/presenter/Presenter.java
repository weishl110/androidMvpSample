package com.wei.androidmvpsample.mvp.presenter;

/**
 * Created by ${wei} on 2017/3/26.
 */

public interface Presenter<T> {

    void onstart();

    void onSuccess(T data);

    void onError(Exception e);
}
