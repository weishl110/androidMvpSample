package com.wei.androidmvpsample.mvp.model;

import com.wei.androidmvpsample.mvp.contract.MainContract;
import com.wei.androidmvpsample.net.MyException;

/**
 * Created by ${wei} on 2017/3/26.
 */

public class MainModel extends MainContract.Model {

    public MainModel(MainContract.Presenter presenter) {
        super(presenter);
    }

    @Override
    public void start() {

    }

    @Override
    public void loadData() {
        presenter.onSuccess("123");

        presenter.onError(new MyException("网络异常了。。。。"));
    }
}
