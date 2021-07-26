package com.rui.home.data;


import com.rui.base.network.RetrofitClient;
import com.rui.mvvmlazy.base.BaseModel;
import com.rui.home.data.source.HttpDataSource;
import com.rui.home.data.source.LocalDataSource;
import com.rui.home.data.source.http.HttpDataSourceImpl;
import com.rui.home.data.source.http.service.HomeApiService;
import com.rui.home.data.source.local.LocalDataSourceImpl;

/**
 * ******************************
 * *@Author
 * *date ：
 * *description:MVVM的Model层，统一模块的数据仓库，包含网络数据和本地数据（一个应用可以有多个Repositor）
 * *******************************
 */
public class HomeRepository extends BaseModel implements HttpDataSource, LocalDataSource {
    private final HttpDataSource mHttpDataSource;

    private final LocalDataSource mLocalDataSource;

    public HomeRepository() {
        super();
        //网络数据源
        this.mHttpDataSource = HttpDataSourceImpl.getInstance(RetrofitClient.getInstance().create(HomeApiService.class));
        //本地数据源
        this.mLocalDataSource = LocalDataSourceImpl.getInstance();

    }


    @Override
    public void saveUserName(String userName) {
        mLocalDataSource.saveUserName(userName);
    }

    @Override
    public void savePassword(String password) {
        mLocalDataSource.savePassword(password);
    }

    @Override
    public String getUserName() {
        return mLocalDataSource.getUserName();
    }

    @Override
    public String getPassword() {
        return mLocalDataSource.getPassword();
    }
}
