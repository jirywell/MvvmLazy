package com.rui.demo.ui.activity;


import com.alibaba.android.arouter.facade.annotation.Route;
import com.hjq.bar.TitleBar;
import com.rui.base.router.RouterActivityPath;
import com.rui.demo.BR;
import com.rui.demo.R;
import com.rui.demo.databinding.TestActivityTestBinding;
import com.rui.demo.ui.viewmodel.TestViewModel;
import com.rui.mvvmlazy.base.BaseActivity;

@Route(path = RouterActivityPath.Test.TESTPAGER)
public class TestActivity extends BaseActivity<TestActivityTestBinding, TestViewModel> {


    @Override
    public int initContentView() {
        return R.layout.test_activity_test;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }


    @Override
    public void initData() {
        super.initData();
    }

    @Override
    public void initTitleBar(TitleBar titleBar) {
        super.initTitleBar(titleBar);
        titleBar.setTitle("demo测试");
    }
}
