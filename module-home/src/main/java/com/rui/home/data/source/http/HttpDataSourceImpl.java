package com.rui.home.data.source.http;


import com.rui.home.data.source.HttpDataSource;
import com.rui.home.data.source.http.service.HomeApiService;

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
}
