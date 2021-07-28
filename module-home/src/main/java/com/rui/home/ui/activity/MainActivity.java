package com.rui.home.ui.activity;


import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.rui.base.router.RouterActivityPath;
import com.rui.home.BR;
import com.rui.home.R;
import com.rui.home.databinding.HomeActivityMainBinding;
import com.rui.home.ui.viewmodel.MainViewModel;
import com.rui.mvvmlazy.base.BaseActivity;

@Route(path = RouterActivityPath.Main.PAGER_MAIN)
public class MainActivity extends BaseActivity<HomeActivityMainBinding, MainViewModel> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int initContentView() {
        return R.layout.home_activity_main;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }


    @Override
    public void initData() {
        super.initData();
    }


}
