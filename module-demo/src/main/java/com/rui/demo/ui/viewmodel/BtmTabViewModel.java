package com.rui.demo.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;

import com.rui.demo.data.DiscoverRepository;
import com.rui.mvvmlazy.base.BaseViewModel;

public class BtmTabViewModel extends BaseViewModel<DiscoverRepository> {
    public BtmTabViewModel(@NonNull Application application) {
        super(application);
    }

    @Override
    public void initData() {
        super.initData();
    }
}