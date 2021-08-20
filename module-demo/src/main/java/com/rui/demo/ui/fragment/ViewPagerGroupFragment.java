package com.rui.demo.ui.fragment;

import androidx.fragment.app.Fragment;

import com.rui.base.ui.fragment.BasePagerFragment;
import com.rui.demo.ui.fragment.tab.TabBar1Fragment;
import com.rui.demo.ui.fragment.tab.TabBar2Fragment;
import com.rui.demo.ui.fragment.tab.TabBar3Fragment;
import com.rui.demo.ui.fragment.tab.TabBar4Fragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Create Author：zjr
 * Create Date：2019/01/25
 * Description：ViewPager+Fragment的实现
 */

public class ViewPagerGroupFragment extends BasePagerFragment {
    @Override
    protected List<Fragment> pagerFragment() {
        List<Fragment> list = new ArrayList<>();
        list.add(TabBar1Fragment.newInstance());
        list.add(TabBar2Fragment.newInstance());
        list.add(TabBar3Fragment.newInstance());
        list.add(TabBar4Fragment.newInstance());
        return list;
    }

    @Override
    protected List<String> pagerTitleString() {
        List<String> list = new ArrayList<>();
        list.add("推荐");
        list.add("新闻");
        list.add("资讯");
        list.add("消息");
        return list;
    }
}
