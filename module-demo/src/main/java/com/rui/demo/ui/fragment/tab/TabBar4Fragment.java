package com.rui.demo.ui.fragment.tab;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.rui.demo.BR;
import com.rui.demo.R;
import com.rui.mvvmlazy.base.BaseFragment;

/**
 * Created by zjr on 2018/7/18.
 */

public class TabBar4Fragment extends BaseFragment {
    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.test_fragment_tab_bar_4;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    public static TabBar4Fragment newInstance() {

        Bundle args = new Bundle();

        TabBar4Fragment fragment = new TabBar4Fragment();
        fragment.setArguments(args);
        return fragment;
    }
}
