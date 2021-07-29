package com.rui.demo.data.source.http;


import com.rui.demo.data.bean.JokeInfo;
import com.rui.demo.data.source.HttpDataSource;
import com.rui.demo.data.source.http.service.HomeApiService;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import com.rui.mvvmlazy.http.BaseResponse;

/**
 * Created by zjr on 2019/3/26.
 */
public class HttpDataSourceImpl implements HttpDataSource {
    private HomeApiService apiService;
    private volatile static HttpDataSourceImpl INSTANCE = null;

    public static HttpDataSourceImpl getInstance(HomeApiService apiService) {
        if (INSTANCE == null) {
            synchronized (HttpDataSourceImpl.class) {
                if (INSTANCE == null) {
                    INSTANCE = new HttpDataSourceImpl(apiService);
                }
            }
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    private HttpDataSourceImpl(HomeApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public Observable<BaseResponse<List<JokeInfo>>> getJoke(int page, int count, String type) {
        return apiService.getJoke(page, count, type);
    }

}
