package com.wei.androidmvpsample.mvp.presenter;

import com.wei.androidmvpsample.mvp.model.BaseModel;
import com.wei.androidmvpsample.mvp.view.BaseView;

/**
 * Created by ${wei} on 2017/3/21.
 */

public abstract class BasePresenter<T, V extends BaseView<T>> implements Presenter<T> {

    protected BaseView baseView;
    protected BaseModel model;

    public BasePresenter() {
    }

    public BasePresenter(V baseView) {
        this.baseView = baseView;
        model = createModel();
    }

    /**
     * 子类创建model
     */
    protected abstract BaseModel createModel();

    public abstract void resultCallback(BaseView baseView);

    /**
     * 数据加载
     */
    public abstract BasePresenter loadData();

    protected void detachView() {
        if (model != null) {
            model.onDestory();
        }
        baseView = null;
    }

    /**
     * 用于反注册
     */
    public void onDestory() {
        detachView();
    }

    @Override
    public void onSuccess(T data) {
        if (baseView != null) {
            baseView.success(data);
        }
    }

    @Override
    public void onError(Exception e) {
        if (baseView != null) {
            baseView.onError(e);
        }
    }
}
