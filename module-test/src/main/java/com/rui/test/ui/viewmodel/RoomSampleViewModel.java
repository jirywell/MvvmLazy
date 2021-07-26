package com.rui.test.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.rui.test.data.DiscoverRepository;
import com.rui.test.data.source.local.db.Person;
import com.rui.mvvmlazy.base.BaseViewModel;

import java.util.List;


/**
 * Create Date：2021/01/01
 * 实现Room数据的基本操作
 * zjr
 */
public class RoomSampleViewModel extends BaseViewModel<DiscoverRepository> {

    public RoomSampleViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Person>> getAllWordsLive() {

        return model.getAllWordsLive();
    }

    public void insertWords(Person... words) {
        model.insertWords(words);
    }

    public void updateWords(Person... words) {
        model.updateWords(words);
    }

    public void deleteWords(Person... words) {
        model.deleteWords(words);
    }

    public void deleteAllWords() {
        model.deleteAllWords();
    }

}
