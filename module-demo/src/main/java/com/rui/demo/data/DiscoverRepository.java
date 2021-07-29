package com.rui.demo.data;


import androidx.lifecycle.LiveData;

import com.rui.base.network.RetrofitClient;
import com.rui.demo.data.bean.JokeInfo;
import com.rui.demo.data.source.HttpDataSource;
import com.rui.demo.data.source.LocalDataSource;
import com.rui.demo.data.source.http.HttpDataSourceImpl;
import com.rui.demo.data.source.http.service.HomeApiService;
import com.rui.demo.data.source.local.LocalDataSourceImpl;
import com.rui.demo.data.source.local.db.Person;
import com.rui.mvvmlazy.base.BaseModel;
import com.rui.mvvmlazy.http.BaseResponse;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;


/**
 * ******************************
 * *@Author
 * *date ：
 * *description:MVVM的Model层，统一模块的数据仓库，包含网络数据和本地数据（一个应用可以有多个Repositor）
 * *******************************
 */
public class DiscoverRepository extends BaseModel implements HttpDataSource, LocalDataSource {
    private final HttpDataSource mHttpDataSource;
    private final LocalDataSource mLocalDataSource;

    public DiscoverRepository() {
        super();
        //网络数据源
        mHttpDataSource = HttpDataSourceImpl.getInstance(RetrofitClient.getInstance().create(HomeApiService.class));
        //本地数据源
        mLocalDataSource = LocalDataSourceImpl.getInstance();
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

    @Override
    public void insertWords(Person... words) {
        mLocalDataSource.insertWords(words);
    }

    @Override
    public void updateWords(Person... words) {
        mLocalDataSource.updateWords(words);
    }

    @Override
    public void deleteWords(Person... words) {
        mLocalDataSource.deleteWords(words);
    }

    @Override
    public void deleteAllWords(Person... words) {
        mLocalDataSource.deleteAllWords(words);
    }

    @Override
    public LiveData<List<Person>> getAllWordsLive() {
        return mLocalDataSource.getAllWordsLive();
    }


    @Override
    public Observable<BaseResponse<List<JokeInfo>>> getJoke(int page, int count, String type) {
        return mHttpDataSource.getJoke(page, count, type);
    }
}
