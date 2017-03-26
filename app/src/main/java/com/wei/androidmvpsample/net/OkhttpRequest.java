package com.wei.androidmvpsample.net;

import android.text.TextUtils;

import com.wei.androidmvpsample.bean.ResponseResult;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by ${wei} on 2017/3/20.
 */

public class OkhttpRequest {

    private static final String TAG = "OkhttpRequest";
    private CompositeSubscription compositeSubscription;
    private Observable mObservable;
    private static OkhttpRequest instance;
    private final static Object obj = new Object();

    private OkhttpRequest() {
    }

    public static OkhttpRequest getInstance() {
        if (instance == null) {
            synchronized (obj) {
                if (instance == null) {
                    instance = new OkhttpRequest();
                }
            }
        }
        return instance;

    }

    /**
     * 反注册，以免造成内存泄漏
     */
    public void onUnSubscribe() {
        if (compositeSubscription != null && compositeSubscription.hasSubscriptions() && compositeSubscription.isUnsubscribed()) {
            compositeSubscription.unsubscribe();
        }
    }


    public <T> void url(Observable observable, ResultCallback<T> callback) {

        if (observable == null) {
            throw new IllegalArgumentException("observable 不能为空");
        }

        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseResult<T>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        android.util.Log.e(TAG, "onError:  = ");
                    }

                    @Override
                    public void onNext(ResponseResult<T> responseResult) {
                        android.util.Log.e(TAG, "onNext:  = ");
                    }
                });
    }

    public <T> void callback(final ResultCallback<T> callback) {
        android.util.Log.e(TAG, "callback:  = dddd");
        if (mObservable != null)
            if (compositeSubscription == null) {
                compositeSubscription = new CompositeSubscription();
            }
        android.util.Log.e(TAG, "callback:  = ");
        compositeSubscription.add(mObservable.subscribe(new Subscriber<ResponseResult<T>>() {
            @Override
            public void onCompleted() {
                android.util.Log.e(TAG, "onCompleted:  = ");
            }

            @Override
            public void onError(Throwable e) {
                android.util.Log.e(TAG, "onError:  = ");
                if (callback != null) {
                    callback.onError(new MyException(e.getMessage()));
                }
            }

            @Override
            public void onNext(ResponseResult<T> response) {
                android.util.Log.e(TAG, "onNext:  = " + response.getCode());
                if (callback != null) {
                    if (TextUtils.equals(response.getCode(), "200")) {
                        callback.response(response.getData());
                    } else {
                        callback.onError(new MyException(response.getErrorInfo(), Integer.valueOf(response.getCode())));
                    }
                }
            }
        }));
    }

    private <T> Observable.Transformer<ResponseResult<T>, T> parsingResult() {
        return new Observable.Transformer<ResponseResult<T>, T>() {
            @Override
            public Observable<T> call(final Observable<ResponseResult<T>> observable) {
                return observable.flatMap(new Func1<ResponseResult<T>, Observable<T>>() {
                    @Override
                    public Observable<T> call(ResponseResult<T> tResponseResult) {
                        if (tResponseResult != null && TextUtils.equals(tResponseResult.getCode(), "200")) {
                            createData(tResponseResult.getData());
                        } else {
                            return Observable.error(new MyException(tResponseResult.getErrorInfo()));
                        }
                        return null;
                    }
                }).subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io())
                        .subscribeOn(AndroidSchedulers.mainThread())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    private <T> Observable<T> createData(final T data) {
        return Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                try {
                    subscriber.onNext(data);
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });
    }
}
