package com.wei.androidmvpsample.net;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by ${wei} on 2017/3/21.
 */

public interface IApiService {
    @GET("xire-app-user/banner/getBannerInfo/0")
    Observable<String> getBannerData();

}
