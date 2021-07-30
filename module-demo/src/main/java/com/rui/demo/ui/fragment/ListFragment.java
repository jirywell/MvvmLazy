package com.rui.demo.ui.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.rui.demo.BR;
import com.rui.demo.R;
import com.rui.demo.databinding.TestFragmentListBinding;
import com.rui.demo.ui.viewmodel.ListViewModel;
import com.rui.mvvmlazy.base.BaseFragment;

public class ListFragment extends BaseFragment<TestFragmentListBinding, ListViewModel> {


    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.test_fragment_list;
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