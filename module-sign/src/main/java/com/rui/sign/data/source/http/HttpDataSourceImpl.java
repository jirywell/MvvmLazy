package com.rui.sign.data.source.http;


import com.rui.sign.data.source.HttpDataSource;
import com.rui.sign.data.source.http.service.SignApiService;

/**
 * Created by zjr on 2019/3/26.
 */
public class HttpDataSourceImpl implements HttpDataSource {
    private SignApiService apiService;
    private volatile static HttpDataSourceImpl INSTANCE = null;

    public static HttpDataSourceImpl getInstance(SignApiService apiService) {
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

    private HttpDataSourceImpl(SignApiService apiService) {
        this.apiService = apiService;
    }
}
