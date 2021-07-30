package com.rui.demo.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.rui.base.ui.viewModel.BasePaginationViewModel;
import com.rui.mvvmlazy.binding.viewadapter.recyclerview.DataBindingAdapter;
import com.rui.mvvmlazy.http.BaseResponse;
import com.rui.demo.R;
import com.rui.demo.data.DiscoverRepository;
import com.rui.demo.data.bean.JokeInfo;
import com.rui.demo.databinding.TestLayoutItemJokeBinding;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public class PaginationViewModel extends BasePaginationViewModel<DiscoverRepository, JokeInfo> {
    public PaginationViewModel(@NonNull Application application) {
        super(application);
    }

    public DataBindingAdapter<JokeInfo, TestLayoutItemJokeBinding> myAdapter = new DataBindingAdapter<JokeInfo, TestLayoutItemJokeBinding>(R.layout.test_layout_item_joke) {
        @Override
        protected void convertItem(@NotNull BaseViewHolder holder, @Nullable TestLayoutItemJokeBinding binding, JokeInfo item) {
            binding.setEntity(item);
        }
    };

    @Override
    public BaseQuickAdapter<JokeInfo, BaseViewHolder> getAdapter() {
        return myAdapter;
    }

    /**
     * 接口数据格式通常有两种类型
     * 1.通常有不包含分页信息的,也就是data直接返回list
     * 2. 携带分页信息的,data包含分页信息和数据list
     * 根据接口数据类型选择解析方式
     * @return
     */
    @Override
    public ListType getDateListType() {
        return ListType.NO_PAGING_INFO;
    }

    @Override
    public Observable<BaseResponse<List<JokeInfo>>> getHttpRequestNoPagingData(int pageIndex) {
        return model.getJoke(pageIndex, 10, "video");
    }

}