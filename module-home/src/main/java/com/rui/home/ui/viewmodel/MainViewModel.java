package com.rui.home.ui.viewmodel;

import android.app.Application;
import android.view.View;

import androidx.annotation.NonNull;

import com.kongzue.dialogx.dialogs.MessageDialog;
import com.kongzue.dialogx.interfaces.OnDialogButtonClickListener;
import com.rui.home.data.HomeRepository;
import com.rui.mvvmlazy.base.BaseViewModel;

public class MainViewModel extends BaseViewModel<HomeRepository> {
    public MainViewModel(@NonNull Application application) {
        super(application);
    }

    @Override
    public void initData() {
        super.initData();
        MessageDialog.show("提示","哈哈哈哈哈哈哈哈哈哈").setCancelButtonClickListener(new OnDialogButtonClickListener<MessageDialog>() {
            @Override
            public boolean onClick(MessageDialog baseDialog, View v) {
                return false;
            }
        });
    }
}