package com.wei.androidmvpsample.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.wei.androidmvpsample.mvp.presenter.BasePresenter;

import butterknife.ButterKnife;

/**
 * Created by ${wei} on 2017/3/25.
 */

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements View.OnClickListener {

    public String TAG = "test_" + getClass().getSimpleName();

    protected P presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = String.format("%s%s", "test", getClass().getSimpleName());
        setContentView(layoutView());
        //注解
        ButterKnife.bind(this);
        presenter = createpresenter();
        initWidget();

    }

    /**
     * 子类返回布局
     */
    public abstract int layoutView();

    /**
     * 返回子类需要的prsenter
     */
    protected abstract P createpresenter();

    /**
     * 初始化布局id
     */
    public abstract void initWidget();


    /**
     * 点击事件
     */
    public abstract void widgetClick(View view);

    @Override
    public void onClick(View v) {
        widgetClick(v);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        if (presenter != null) {
            presenter.onDestory();
        }
    }

    @Override
    public void finish() {
        super.finish();
    }

    public void startActivity(Class<? extends BaseActivity> clazz, Bundle bundle) {
        Intent intent = new Intent();
        intent.putExtras(bundle);
        intent.setClass(getApplicationContext(), clazz);
        Intent newIntent = new Intent();
        intent.putExtra("class", clazz.getName());
        if (test == 0) {
            newIntent.setAction("com.wei.login");
            newIntent.putExtras(intent);
            startActivity(newIntent);
        } else {
            startActivity(intent);
        }
    }

    public int test = 0;

    public int getTest() {
        return test;
    }

    public void setTest(int test) {
        this.test = this.test == 0 ? 1 : 0;
    }
}
