package com.wei.androidmvpsample.ui;

import android.view.View;
import android.widget.TextView;

import com.wei.androidmvpsample.R;
import com.wei.androidmvpsample.bean.BannerBean;
import com.wei.androidmvpsample.mvp.contract.MainContract;
import com.wei.androidmvpsample.mvp.presenter.MainPresenter;
import com.wei.androidmvpsample.net.OkhttpRequest;
import com.wei.androidmvpsample.net.ResultCallback;
import com.wei.androidmvpsample.net.RetrofitFactory;

import java.util.List;

import butterknife.Bind;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View<String> {

    private static final String TAG = "MainActivity";

    @Bind(R.id.tv)
    TextView tv;
    @Bind(R.id.tv_set)
    TextView tv_set;

    @Override
    public int layoutView() {
        return R.layout.activity_main;
    }

    @Override
    protected MainPresenter createpresenter() {
        return new MainPresenter();
    }

    @Override
    public void initWidget() {
        tv.setOnClickListener(this);
        tv_set.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View view) {
        switch (view.getId()) {
            case R.id.tv:
                http_req();
//                android.util.Log.e(TAG, "widgetClick:  = w");
//                Bundle bundle = new Bundle();
//                bundle.putInt("test", 1);
//                startActivity(TestActivity.class, bundle);
                break;
            case R.id.tv_set:
                android.util.Log.e(TAG, "widgetClick:  = " + 2);
                setTest(1);
                break;
        }
    }

    private void http_req() {
     /*   presenter.loadData().resultCallback(new MainContract.View<String>() {
            @Override
            public void success(String s) {
                Log.e(TAG, "success: s = " + s);
            }

            @Override
            public void onError(Exception e) {
                Log.e(TAG, "onError: exception = " + e.getMessage());
            }
        });*/
        OkhttpRequest.getInstance().url(RetrofitFactory.createIApiService().getBannerData(),
                new ResultCallback<List<BannerBean>>() {
            @Override
            public void onError(Exception e) {

            }

            @Override
            public void response(List<BannerBean> bannerBeen) {

            }
        });
    }

    @Override
    public void success(String s) {

    }

    @Override
    public void onError(Exception e) {

    }
}