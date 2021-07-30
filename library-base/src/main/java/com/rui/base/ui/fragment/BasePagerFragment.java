package com.rui.base.ui.fragment;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.angcyo.tablayout.delegate2.ViewPager2Delegate;
import com.hjq.bar.TitleBar;
import com.rui.base.BR;
import com.rui.base.R;
import com.rui.base.databinding.BaseFragmentBasePagerBinding;
import com.rui.mvvmlazy.base.BaseFragment;
import com.rui.mvvmlazy.base.BaseViewModel;

import java.util.List;


/**
 * Created by zjr on 2020/7/17.
 * 抽取的二级BasePagerFragment
 */

public abstract class BasePagerFragment extends BaseFragment<BaseFragmentBasePagerBinding, BaseViewModel> {

    private List<Fragment> mFragments;
    private List<String> titlePager;

    protected abstract List<Fragment> pagerFragment();

    protected abstract List<String> pagerTitleString();

    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.base_fragment_base_pager;
    }


    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        mFragments = pagerFragment();
        titlePager = pagerTitleString();
        for (String s : titlePager) {
            TextView tabTextView = new TextView(requireContext());
            tabTextView.setText(s);
            tabTextView.setGravity(Gravity.CENTER);
            binding.tabLayout.addView(tabTextView);
        }
        //设置Adapter
        binding.viewPager.setAdapter(new FragmentStateAdapter(BasePagerFragment.this) {
            @Override
            public int getItemCount() {
                return mFragments.size();
            }

            @NonNull
            @Override
            public Fragment createFragment(int position) {
                return mFragments.get(position);
            }
        });
        binding.viewPager.setCurrentItem(0);
        ViewPager2Delegate viewPagerDelegate = new ViewPager2Delegate(binding.viewPager, binding.tabLayout);
        binding.tabLayout.setupViewPager(viewPagerDelegate);
    }

    @Override
    public void initViewObservable() {

    }

    @Override
    public void initTitleBar(TitleBar titleBar) {
        super.initTitleBar(titleBar);
        titleBar.setTitle("tablayout示例");
    }
}
