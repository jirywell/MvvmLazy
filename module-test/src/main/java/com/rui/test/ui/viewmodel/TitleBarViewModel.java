package com.rui.test.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;

import com.rui.mvvmlazy.base.BaseViewModel;
import com.rui.test.data.DiscoverRepository;

public class TitleBarViewModel extends BaseViewModel<DiscoverRepository> {
    public TitleBarViewModel(@NonNull Application application) {
        super(application);
    }

    @Override
    public void initData() {
        super.initData();
    }
}