package com.rui.demo.data.source;


import com.rui.demo.data.bean.JokeInfo;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import com.rui.mvvmlazy.http.BaseResponse;
import retrofit2.http.Query;

/**
 * Created by zjr on 2019/3/26.
 */
public interface HttpDataSource {
    Observable<BaseResponse<List<JokeInfo>>> getJoke(@Query("page") int page, @Query("count") int count, @Query("type") String type);


}
