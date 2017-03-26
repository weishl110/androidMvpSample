package com.wei.androidmvpsample.mvp.presenter;

import com.wei.androidmvpsample.mvp.contract.MainContract;
import com.wei.androidmvpsample.mvp.model.BaseModel;
import com.wei.androidmvpsample.mvp.model.MainModel;
import com.wei.androidmvpsample.mvp.view.BaseView;

/**
 * Created by ${wei} on 2017/3/26.
 */

public class MainPresenter extends MainContract.Presenter {

    public MainPresenter() {
    }

    public MainPresenter(MainContract.View<String> baseView) {
        super(baseView);
    }

    @Override
    public void onstart() {

    }

    @Override
    protected BaseModel createModel() {
        return new MainModel(this);
    }

    @Override
    public BasePresenter loadData() {

        return this;
    }

    @Override
    public void resultCallback(BaseView baseView) {
        this.baseView = baseView;
    }
}
