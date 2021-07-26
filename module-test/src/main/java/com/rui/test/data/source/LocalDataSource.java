package com.rui.test.data.source;

import androidx.lifecycle.LiveData;

import com.rui.test.data.source.local.db.Person;

import java.util.List;

/**
 * Created by zjr on 2019/3/26.
 */
public interface LocalDataSource {
    /**
     * 保存用户名
     */
    void saveUserName(String userName);

    /**
     * 保存用户密码
     */

    void savePassword(String password);

    /**
     * 获取用户名
     */
    String getUserName();

    /**
     * 获取用户密码
     */
    String getPassword();
    void insertWords(Person... words) ;
    void updateWords(Person... words);

    void deleteWords(Person... words);

    void deleteAllWords(Person... words);


    LiveData<List<Person>> getAllWordsLive();
}
