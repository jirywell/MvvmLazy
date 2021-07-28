package com.rui.test.ui.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.rui.mvvmlazy.base.BaseFragment;
import com.rui.test.BR;
import com.rui.test.R;
import com.rui.test.databinding.TestFragmentTitlebarBinding;
import com.rui.test.ui.viewmodel.TitleBarViewModel;

public class TitleBarFragment extends BaseFragment<TestFragmentTitlebarBinding, TitleBarViewModel> {


    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.test_fragment_titlebar;
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