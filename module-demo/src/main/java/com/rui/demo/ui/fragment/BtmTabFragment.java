package com.rui.demo.ui.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Lifecycle;

import com.angcyo.tablayout.DslTabLayoutConfig;
import com.rui.demo.BR;
import com.rui.demo.R;
import com.rui.demo.databinding.TestFragmentBtmtabBinding;
import com.rui.demo.ui.fragment.tab.TabBar1Fragment;
import com.rui.demo.ui.fragment.tab.TabBar2Fragment;
import com.rui.demo.ui.fragment.tab.TabBar3Fragment;
import com.rui.demo.ui.fragment.tab.TabBar4Fragment;
import com.rui.demo.ui.viewmodel.BtmTabViewModel;
import com.rui.mvvmlazy.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function4;

public class BtmTabFragment extends BaseFragment<TestFragmentBtmtabBinding, BtmTabViewModel> {


    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.test_fragment_btmtab;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        super.initData();
        List<Fragment> list = new ArrayList<>();
        list.add(TabBar1Fragment.newInstance());
        list.add(TabBar2Fragment.newInstance());
        list.add(TabBar3Fragment.newInstance());
        list.add(TabBar4Fragment.newInstance());
        binding.tabLayout.configTabLayoutConfig(new Function1<DslTabLayoutConfig, Unit>() {
            @Override
            public Unit invoke(DslTabLayoutConfig dslTabLayoutConfig) {
                dslTabLayoutConfig.setOnSelectIndexChange(new Function4<Integer, List<Integer>, Boolean, Boolean, Unit>() {
                    @Override
                    public Unit invoke(Integer fromIndex, List<Integer> selectIndexList, Boolean select, Boolean fromUser) {
                        int toIndex = selectIndexList.get(0);
                        showFragment(list.get(toIndex), fromIndex>=0?list.get(fromIndex):null);
                        return null;
                    }
                });
                return null;
            }
        });

    }

    private void showFragment(Fragment showFragment, Fragment hideFragment) {
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        if (showFragment.isAdded()) {
            transaction.show(showFragment);
            transaction.setMaxLifecycle(showFragment, Lifecycle.State.RESUMED);
        } else {
            transaction.add(R.id.frame_container_layout, showFragment);
        }
        if (hideFragment != null) {
            if (hideFragment.isAdded()) {
                transaction.hide(hideFragment);
                transaction.setMaxLifecycle(showFragment, Lifecycle.State.STARTED);
            }
        }
        transaction.commitAllowingStateLoss();
    }

}