package com.wei.androidmvpsample.mvp.contract;

import com.wei.androidmvpsample.mvp.model.BaseModel;
import com.wei.androidmvpsample.mvp.presenter.BasePresenter;
import com.wei.androidmvpsample.mvp.view.BaseView;

/**
 * Created by ${wei} on 2017/3/26.
 */

public interface MainContract {

    abstract static class Model extends BaseModel<Presenter> {
        public Model(Presenter presenter) {
            super(presenter);
        }
    }

    abstract static class Presenter extends BasePresenter<String, View<String>> {
        public Presenter() {
        }

        public Presenter(View<String> baseView) {
            super(baseView);
        }
    }


    interface View<String> extends BaseView<String> {
    }

}
