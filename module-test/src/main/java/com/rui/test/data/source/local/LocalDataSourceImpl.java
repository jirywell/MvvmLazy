package com.rui.test.data.source.local;


import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.rui.test.data.source.LocalDataSource;
import com.rui.test.data.source.local.db.Person;
import com.rui.test.data.source.local.db.PersonDao;
import com.rui.test.data.source.local.db.PersonDatabase;
import com.rui.mvvmlazy.utils.data.SPUtils;
import com.rui.mvvmlazy.utils.GlobalUtils;

import java.util.List;

/**
 * 本地数据源，可配合Room框架使用
 * Created by zjr on 2019/3/26.
 */
public class LocalDataSourceImpl implements LocalDataSource {
    private volatile static LocalDataSourceImpl INSTANCE = null;
    private PersonDao wordDao;

    public static LocalDataSourceImpl getInstance() {
        if (INSTANCE == null) {
            synchronized (LocalDataSourceImpl.class) {
                if (INSTANCE == null) {
                    INSTANCE = new LocalDataSourceImpl();
                }
            }
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    private LocalDataSourceImpl() {
        //数据库Helper构建
        PersonDatabase wordDatabase = PersonDatabase.getDatabase(GlobalUtils.getContext());
        wordDao = wordDatabase.getWordDao();
    }

    @Override
    public void saveUserName(String userName) {
        SPUtils.getInstance().put("UserName", userName);
    }

    @Override
    public void savePassword(String password) {
        SPUtils.getInstance().put("password", password);
    }

    @Override
    public String getUserName() {
        return SPUtils.getInstance().getString("UserName");
    }

    @Override
    public String getPassword() {
        return SPUtils.getInstance().getString("password");
    }

    @Override
    public void insertWords(Person... words) {
        new InsertAsyncTask(wordDao).execute(words);
    }

    @Override
    public void updateWords(Person... words) {
        new UpdateAsyncTask(wordDao).execute(words);
    }

    @Override
    public void deleteWords(Person... words) {
        new DeleteAsyncTask(wordDao).execute(words);
    }

    @Override
    public void deleteAllWords(Person... words) {
        new DeleteAllAsyncTask(wordDao).execute();
    }

    @Override
    public LiveData<List<Person>> getAllWordsLive() {
        return wordDao.getAllWordsLive();
    }


    static class InsertAsyncTask extends AsyncTask<Person, Void, Void> {
        private PersonDao wordDao;

        InsertAsyncTask(PersonDao wordDao) {
            this.wordDao = wordDao;
        }

        @Override
        protected Void doInBackground(Person... words) {
            wordDao.insertWords(words);
            return null;
        }

    }

    static class UpdateAsyncTask extends AsyncTask<Person, Void, Void> {
        private PersonDao wordDao;

        UpdateAsyncTask(PersonDao wordDao) {
            this.wordDao = wordDao;
        }

        @Override
        protected Void doInBackground(Person... words) {
            wordDao.updateWords(words);
            return null;
        }

    }

    static class DeleteAsyncTask extends AsyncTask<Person, Void, Void> {
        private PersonDao wordDao;

        DeleteAsyncTask(PersonDao wordDao) {
            this.wordDao = wordDao;
        }

        @Override
        protected Void doInBackground(Person... words) {
            wordDao.deleteWords(words);
            return null;
        }

    }

    static class DeleteAllAsyncTask extends AsyncTask<Void, Void, Void> {
        private PersonDao wordDao;

        DeleteAllAsyncTask(PersonDao wordDao) {
            this.wordDao = wordDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            wordDao.deleteAllWords();
            return null;
        }

    }
}
