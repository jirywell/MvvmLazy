package com.rui.test.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.rui.base.viewModel.BasePaginationViewModel;
import com.rui.mvvmlazy.binding.viewadapter.recyclerview.DataBindingAdapter;
import com.rui.mvvmlazy.http.BaseResponse;
import com.rui.test.R;
import com.rui.test.data.DiscoverRepository;
import com.rui.test.data.bean.JokeInfo;
import com.rui.test.databinding.TestLayoutItemJokeBinding;

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

    @Override
    public ListType getDateListType() {
        return ListType.NO_PAGING_INFO;
    }

    @Override
    public Observable<BaseResponse<List<JokeInfo>>> getHttpRequestNoPagingData(int pageIndex) {
        return model.getJoke(pageIndex, 10, "video");
    }

}