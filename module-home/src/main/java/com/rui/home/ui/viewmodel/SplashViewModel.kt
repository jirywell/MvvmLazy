package com.rui.home.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;

import com.rui.home.data.HomeRepository;
import com.rui.mvvmlazy.base.BaseViewModel;

public class SplashViewModel extends BaseViewModel<HomeRepository> {
    public SplashViewModel(@NonNull Application application) {
        super(application);
    }

    @Override
    public void initData() {
        super.initData();
    }
}