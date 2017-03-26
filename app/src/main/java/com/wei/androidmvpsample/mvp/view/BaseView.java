package com.wei.androidmvpsample.mvp.view;

/**
 * Created by ${wei} on 2017/3/26.
 */

public interface BaseView<T> {
    void success(T t);

    void onError(Exception e);
}
