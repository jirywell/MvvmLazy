package com.rui.home.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;

import com.rui.home.data.HomeRepository;
import com.rui.mvvmlazy.base.BaseViewModel;

public class MainViewModel extends BaseViewModel<HomeRepository> {
    public MainViewModel(@NonNull Application application) {
        super(application);
    }

    @Override
    public void initData() {
        super.initData();
    }
}