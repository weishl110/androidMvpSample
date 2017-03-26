package com.wei.androidmvpsample.ui;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.wei.androidmvpsample.R;
import com.wei.androidmvpsample.mvp.presenter.BasePresenter;

import butterknife.Bind;

/**
 * Created by ${wei} on 2017/3/25.
 */

public class SecondActivity extends BaseActivity {

    private static final String TAG = "SecondActivity";

    @Bind(R.id.tv2)
    TextView tv2;
    private String aClass;

    @Override
    public int layoutView() {
        return R.layout.activity_second;
    }

    @Override
    protected BasePresenter createpresenter() {
        return null;
    }

    @Override
    public void initWidget() {

        Intent intent = getIntent();
        aClass = getIntent().getStringExtra("class");
        String action = intent.getAction();

        tv2.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View view) {
        switch (view.getId()) {
            case R.id.tv2:
                setTest(1);
                android.util.Log.e(TAG, "widgetClick:  = "+aClass);
                Intent intent = getIntent();
                if (!TextUtils.isEmpty(aClass)) {
//                    intent.setClassName(getApplicationContext(), aClass);
                }
                startActivity(intent);
                break;
        }
    }
}
