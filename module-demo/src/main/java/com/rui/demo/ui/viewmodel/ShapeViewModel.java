package com.rui.demo.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;

import com.rui.mvvmlazy.base.BaseViewModel;
import com.rui.demo.data.DiscoverRepository;

public class ShapeViewModel extends BaseViewModel<DiscoverRepository> {
    public ShapeViewModel(@NonNull Application application) {
        super(application);
    }

    @Override
    public void initData() {
        super.initData();
    }
}