package com.rui.test.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;

import com.rui.test.data.DiscoverRepository;

import com.rui.mvvmlazy.base.BaseViewModel;

public class TestViewModel extends BaseViewModel<DiscoverRepository> {
    public TestViewModel(@NonNull Application application) {
        super(application);
    }

    @Override
    public void initData() {
        super.initData();
    }
}