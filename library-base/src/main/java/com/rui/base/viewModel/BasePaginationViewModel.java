package com.rui.base.viewModel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.rui.mvvmlazy.base.BaseModel;
import com.rui.mvvmlazy.base.BaseViewModel;
import com.rui.mvvmlazy.binding.command.BindingCommand;
import com.rui.mvvmlazy.binding.viewadapter.swiperefresh.SmartRefreshState;
import com.rui.mvvmlazy.http.ApiDisposableObserver;
import com.rui.mvvmlazy.http.BaseResponse;
import com.rui.mvvmlazy.http.PagingData;
import com.rui.mvvmlazy.http.ResponseThrowable;
import com.rui.mvvmlazy.utils.common.RxUtils;
import com.rui.mvvmlazy.widget.MultiStateView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

/**
 * 列表分页基类分装
 *
 * @param <M>
 * @param <T>
 */
public abstract class BasePaginationViewModel<M extends BaseModel, T> extends BaseViewModel<M> {
    public BasePaginationViewModel(@NonNull @NotNull Application application) {
        super(application);
    }

    /**
     * 分页数据类型
     */
    public enum ListType {
        NO_PAGING_INFO,//不包含分页信息
        WITH_PAGING_INFO,//包含分页信息
    }

    //多状态布局控制
    public MutableLiveData<MultiStateView.ViewState> viewState = new MutableLiveData<>(MultiStateView.ViewState.LOADING);
    //下拉刷新,分页加载状态控制
    public MutableLiveData<SmartRefreshState> smartRefreshState = new MutableLiveData<>();
    public int pageIndex = 1;
    public final int PAGE_SIZE = 10;

    public abstract BaseQuickAdapter<T, BaseViewHolder> getAdapter();

    public abstract ListType getDateListType();

    public BaseQuickAdapter<T, BaseViewHolder> mAdapter;

    /**
     * 未携带分页信息的请求
     *
     * @param pageIndex
     * @return
     */
    public Observable<BaseResponse<List<T>>> getHttpRequestNoPagingData(int pageIndex) {
        return null;
    }

    /**
     * 携带分页信息的请求
     *
     * @param pageIndex
     * @return
     */
    public Observable<BaseResponse<PagingData<T>>> getHttpRequestWithPagingData(int pageIndex) {
        return null;
    }

    /**
     * 重新加载数据
     */
    public BindingCommand reTryLoad = new BindingCommand(() -> {
        pageIndex = 1;
        getListData(pageIndex);
    });
    //加载更多数据
    public BindingCommand loadMore = new BindingCommand(() -> {
        getListData(pageIndex);
    });


    @Override
    public void initData() {
        super.initData();
        mAdapter = getAdapter();
        getListData(pageIndex);
    }

    public void getListData(int page) {
        switch (getDateListType()) {
            case NO_PAGING_INFO:
                getHttpRequestNoPagingData(page)
                        .compose(RxUtils.netTransformer(this))
                        .doOnSubscribe(disposable -> {
                            if (mAdapter.getItemCount() < 1) {
                                viewState.setValue(MultiStateView.ViewState.LOADING);
                            }
                        }).doFinally(() -> {
                }).subscribe(new ApiDisposableObserver<BaseResponse<List<T>>>() {
                    @Override
                    public void onResultOk(BaseResponse<List<T>> item) {
                        if (page == 1) {
                            if (item.result.size() > 0) {
                                mAdapter.setNewInstance(item.result);
                                viewState.setValue(MultiStateView.ViewState.CONTENT);
                            } else {
                                viewState.setValue(MultiStateView.ViewState.EMPTY);
                            }
                        } else {
                            mAdapter.addData(item.result);
                        }
                        if (item.result.size() == PAGE_SIZE) {
                            smartRefreshState.setValue(SmartRefreshState.LOAD_FINISH);
                            pageIndex++;
                        } else {
                            smartRefreshState.setValue(SmartRefreshState.LOAD_FINISH_NOMOREDATA);
                        }

                    }

                    @Override
                    public void onResultFailed(ResponseThrowable throwable) {
                        Log.i("error", throwable.message);
                        if (pageIndex == 1) {
                            viewState.setValue(MultiStateView.ViewState.ERROR);
                        } else {
                            smartRefreshState.setValue(SmartRefreshState.LOAD_FINISH);
                        }
                    }
                });
                break;
            case WITH_PAGING_INFO:
                getHttpRequestWithPagingData(page)
                        .compose(RxUtils.netTransformer(this))
                        .doOnSubscribe(disposable -> {
                            if (mAdapter.getItemCount() < 1) {
                                viewState.setValue(MultiStateView.ViewState.LOADING);
                            }
                        }).doFinally(() -> {
                }).subscribe(new ApiDisposableObserver<BaseResponse<PagingData<T>>>() {
                    @Override
                    public void onResultOk(BaseResponse<PagingData<T>> item) {
                        if (page == 1) {
                            if (item.result.getRecords().size() > 0) {
                                mAdapter.setNewInstance(item.result.getRecords());
                                viewState.setValue(MultiStateView.ViewState.CONTENT);
                            } else {
                                viewState.setValue(MultiStateView.ViewState.EMPTY);
                            }
                        } else {
                            mAdapter.addData(item.result.getRecords());
                        }
                        if (item.result.getPages() == PAGE_SIZE) {
                            smartRefreshState.setValue(SmartRefreshState.LOAD_FINISH);
                            pageIndex++;
                        } else {
                            smartRefreshState.setValue(SmartRefreshState.LOAD_FINISH_NOMOREDATA);
                        }

                    }

                    @Override
                    public void onResultFailed(ResponseThrowable throwable) {
                        Log.i("error", throwable.message);
                        if (pageIndex == 1) {
                            viewState.setValue(MultiStateView.ViewState.ERROR);
                        } else {
                            smartRefreshState.setValue(SmartRefreshState.LOAD_FINISH);
                        }
                    }
                });
                break;
        }
    }
}