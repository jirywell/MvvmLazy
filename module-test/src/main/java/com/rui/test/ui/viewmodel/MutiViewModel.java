package com.rui.test.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.rui.mvvmlazy.base.BaseViewModel;
import com.rui.mvvmlazy.binding.command.BindingCommand;
import com.rui.mvvmlazy.widget.MultiStateView;
import com.rui.test.data.DiscoverRepository;

public class MutiViewModel extends BaseViewModel<DiscoverRepository> {
    //多状态布局控制
    public MutableLiveData<MultiStateView.ViewState> viewState = new MutableLiveData<>();

    public MutiViewModel(@NonNull Application application) {
        super(application);
    }

    @Override
    public void initData() {
        super.initData();
    }


    /**
     * 重新加载数据
     */
    public BindingCommand reTryLoad = new BindingCommand(() -> {
        viewState.setValue(MultiStateView.ViewState.CONTENT);
    });
    public BindingCommand contentClick = new BindingCommand(() -> {
        viewState.setValue(MultiStateView.ViewState.CONTENT);
    });
    public BindingCommand loadingClick = new BindingCommand(() -> {
        viewState.setValue(MultiStateView.ViewState.LOADING);
    });
    public BindingCommand emptyClick = new BindingCommand(() -> {
        viewState.setValue(MultiStateView.ViewState.EMPTY);
    });
    public BindingCommand errorClick = new BindingCommand(() -> {
        viewState.setValue(MultiStateView.ViewState.ERROR);
    });
}