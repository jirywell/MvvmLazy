package com.rui.home.ui.activity;


import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.NonNull;

import com.alibaba.android.arouter.launcher.ARouter;
import com.gyf.immersionbar.BarHide;
import com.gyf.immersionbar.ImmersionBar;
import com.rui.base.router.RouterActivityPath;
import com.rui.home.BR;
import com.rui.home.R;
import com.rui.home.databinding.HomeActivitySplashBinding;
import com.rui.home.ui.viewmodel.SplashViewModel;
import com.rui.mvvmlazy.base.BaseActivity;

public class SplashActivity extends BaseActivity<HomeActivitySplashBinding, SplashViewModel> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int initContentView() {
        return R.layout.home_activity_splash;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }


    @Override
    public void initData() {
        super.initData();
        new Handler().postDelayed(() -> {
            ARouter.getInstance().build(RouterActivityPath.Test.TESTPAGER).navigation();
            finish();
        }, 2000);
    }

    @NonNull
    @Override
    protected ImmersionBar createStatusBarConfig() {
        return super.createStatusBarConfig()
                // 隐藏状态栏和导航栏
                .hideBar(BarHide.FLAG_HIDE_BAR);
    }
}
