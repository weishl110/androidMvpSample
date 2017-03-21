package com.wei.androidmvpsample.net;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by ${wei} on 2017/3/21.
 */

public interface IApiService {

    @GET("user/userInfo")
    Observable<String> loadData(@Query("phone") String phone);

}
