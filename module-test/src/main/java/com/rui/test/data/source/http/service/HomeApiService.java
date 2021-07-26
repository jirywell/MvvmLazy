package com.rui.test.data.source.http.service;


import com.rui.test.data.bean.JokeInfo;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import com.rui.mvvmlazy.http.BaseResponse;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * ******************************
 * *@Author
 * *date ：
 * *description:接口服务类
 * *******************************
 */
public interface HomeApiService {
    @GET("getJoke")
    Observable<BaseResponse<List<JokeInfo>>> getJoke(@Query("page") int page, @Query("count") int count, @Query("type") String type);
}
