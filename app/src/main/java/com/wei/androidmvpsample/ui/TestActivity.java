package com.wei.androidmvpsample.ui;

import android.view.View;

import com.wei.androidmvpsample.R;
import com.wei.androidmvpsample.mvp.presenter.BasePresenter;

/**
 * Created by ${wei} on 2017/3/25.
 */

public class TestActivity extends BaseActivity {

    @Override
    public int layoutView() {
        return R.layout.activity_test;
    }

    @Override
    protected BasePresenter createpresenter() {
        return null;
    }

    @Override
    public void initWidget() {
        android.util.Log.e(TAG, "initWidget: clazz = " + getIntent().getStringExtra("class"));
    }

    @Override
    public void widgetClick(View view) {

    }
}
