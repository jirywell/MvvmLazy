package com.rui.test.ui.viewmodel;

import android.app.Application;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.rui.mvvmlazy.base.BaseViewModel;
import com.rui.mvvmlazy.binding.viewadapter.recyclerview.DataBindingAdapter;
import com.rui.mvvmlazy.utils.common.ScreenUtil;
import com.rui.test.R;
import com.rui.test.data.DiscoverRepository;
import com.rui.test.data.bean.JokeInfo;
import com.rui.test.databinding.TestLayoutItemJokeBinding;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ListViewModel extends BaseViewModel<DiscoverRepository> {
    public MutableLiveData<Integer> type = new MutableLiveData<>(1);

    public ListViewModel(@NonNull Application application) {
        super(application);
    }

    @Override
    public void initData() {
        super.initData();
        List<JokeInfo> list1 = new ArrayList<>();
        list1.add(new JokeInfo("测试一下", "小明"));
        list1.add(new JokeInfo("测试一下", "小明"));
        list1.add(new JokeInfo("测试一下", "小明"));
        list1.add(new JokeInfo("测试一下", "小明"));
        list1.add(new JokeInfo("测试一下", "小明"));
        list1.add(new JokeInfo("测试一下", "小明"));
        list1.add(new JokeInfo("测试一下", "小明"));
        list1.add(new JokeInfo("测试一下", "小明"));
        list1.add(new JokeInfo("测试一下", "小明"));
        list1.add(new JokeInfo("测试一下", "小明"));
        list1.add(new JokeInfo("测试一下", "小明"));
        list1.add(new JokeInfo("测试一下", "小明"));
        list1.add(new JokeInfo("测试一下", "小明"));
        list1.add(new JokeInfo("测试一下", "小明"));
        list1.add(new JokeInfo("测试一下", "小明"));
        list1.add(new JokeInfo("测试一下", "小明"));
        list1.add(new JokeInfo("测试一下", "小明"));
        list1.add(new JokeInfo("测试一下", "小明"));
        list1.add(new JokeInfo("测试一下", "小明"));
        list1.add(new JokeInfo("测试一下", "小明"));
        lineAdapter.setNewInstance(list1);
        List<JokeInfo> list2 = new ArrayList<>();
        list2.add(new JokeInfo("测试一下", "小明"));
        list2.add(new JokeInfo("测试一下", "小明"));
        list2.add(new JokeInfo("测试一下", "小明"));
        list2.add(new JokeInfo("测试一下", "小明"));
        list2.add(new JokeInfo("测试一下", "小明"));
        list2.add(new JokeInfo("测试一下", "小明"));
        list2.add(new JokeInfo("测试一下", "小明"));
        list2.add(new JokeInfo("测试一下", "小明"));
        list2.add(new JokeInfo("测试一下", "小明"));
        list2.add(new JokeInfo("测试一下", "小明"));
        list2.add(new JokeInfo("测试一下", "小明"));
        list2.add(new JokeInfo("测试一下", "小明"));
        list2.add(new JokeInfo("测试一下", "小明"));
        list2.add(new JokeInfo("测试一下", "小明"));
        list2.add(new JokeInfo("测试一下", "小明"));
        list2.add(new JokeInfo("测试一下", "小明"));
        list2.add(new JokeInfo("测试一下", "小明"));
        list2.add(new JokeInfo("测试一下", "小明"));
        list2.add(new JokeInfo("测试一下", "小明"));
        grideAdapter.setNewInstance(list2);
        List<JokeInfo> list3 = new ArrayList<>();
        list3.add(new JokeInfo("测试一下", "小明"));
        list3.add(new JokeInfo("测试一下", "小明"));
        list3.add(new JokeInfo("测试一下", "小明"));
        list3.add(new JokeInfo("测试一下", "小明"));
        list3.add(new JokeInfo("测试一下", "小明"));
        list3.add(new JokeInfo("测试一下", "小明"));
        list3.add(new JokeInfo("测试一下", "小明"));
        list3.add(new JokeInfo("测试一下", "小明"));
        list3.add(new JokeInfo("测试一下", "小明"));
        list3.add(new JokeInfo("测试一下", "小明"));
        list3.add(new JokeInfo("测试一下", "小明"));
        list3.add(new JokeInfo("测试一下", "小明"));
        list3.add(new JokeInfo("测试一下", "小明"));
        list3.add(new JokeInfo("测试一下", "小明"));
        list3.add(new JokeInfo("测试一下", "小明"));
        list3.add(new JokeInfo("测试一下", "小明"));
        list3.add(new JokeInfo("测试一下", "小明"));
        list3.add(new JokeInfo("测试一下", "小明"));
        list3.add(new JokeInfo("测试一下", "小明"));
        staggeredAdapter.setNewInstance(list3);
    }

    public DataBindingAdapter<JokeInfo, TestLayoutItemJokeBinding> lineAdapter = new DataBindingAdapter<JokeInfo, TestLayoutItemJokeBinding>(R.layout.test_layout_item_joke) {
        @Override
        protected void convertItem(@NotNull BaseViewHolder holder, @Nullable TestLayoutItemJokeBinding binding, JokeInfo item) {
            binding.setEntity(item);
        }
    };
    public DataBindingAdapter<JokeInfo, TestLayoutItemJokeBinding> grideAdapter = new DataBindingAdapter<JokeInfo, TestLayoutItemJokeBinding>(R.layout.test_layout_item_joke) {
        @Override
        protected void convertItem(@NotNull BaseViewHolder holder, @Nullable TestLayoutItemJokeBinding binding, JokeInfo item) {
            binding.setEntity(item);
        }
    };
    public DataBindingAdapter<JokeInfo, TestLayoutItemJokeBinding> staggeredAdapter = new DataBindingAdapter<JokeInfo, TestLayoutItemJokeBinding>(R.layout.test_layout_item_joke) {
        @Override
        protected void convertItem(@NotNull BaseViewHolder holder, @Nullable TestLayoutItemJokeBinding binding, JokeInfo item) {
            binding.setEntity(item);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) binding.llParent.getLayoutParams();
            if (holder.getAdapterPosition() % 3 == 0) {
                layoutParams.height = ScreenUtil.sp2px(getContext(), 60);
            } else if (holder.getAdapterPosition() % 3 == 1) {
                layoutParams.height = ScreenUtil.sp2px(getContext(), 80);
            } else {
                layoutParams.height = ScreenUtil.sp2px(getContext(), 100);
            }
            binding.llParent.setLayoutParams(layoutParams);
        }
    };

    public void changeType(int typeValue) {
        type.setValue(typeValue);
    }
}