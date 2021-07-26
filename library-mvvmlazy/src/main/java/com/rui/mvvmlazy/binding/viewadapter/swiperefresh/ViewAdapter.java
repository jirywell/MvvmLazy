package com.rui.mvvmlazy.binding.viewadapter.swiperefresh;

import androidx.databinding.BindingAdapter;

import com.rui.mvvmlazy.binding.command.BindingCommand;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;


/**
 * Created by zjr on 2021/6/30
 * 基于SmartRefreshLayout下拉刷新,分页加载封装实现
 */
public class ViewAdapter {
    //是否禁止下拉刷新
    @BindingAdapter({"smartEnableRefresh"})
    public static void setEnableRefresh(SmartRefreshLayout smartRefreshLayout, final Boolean aBoolean) {
        if (aBoolean != null)
            smartRefreshLayout.setEnableRefresh(aBoolean);
    }

    //是否禁止加载更多
    @BindingAdapter({"smartEnableLoadMore"})
    public static void setEnableLoadMore(SmartRefreshLayout smartRefreshLayout, final Boolean aBoolean) {
        if (aBoolean != null)
            smartRefreshLayout.setEnableLoadMore(aBoolean);
    }

    //下拉刷新命令
    @BindingAdapter({"smartOnRefreshCommand"})
    public static void onRefreshCommand(SmartRefreshLayout smartRefreshLayout, final BindingCommand onRefreshCommand) {
        smartRefreshLayout.setOnRefreshListener(refreshLayout -> {
            if (onRefreshCommand != null) {
                onRefreshCommand.execute();
            }
        });
    }

    //下拉刷新命令
    @BindingAdapter(value = {"smartOnLoadMoreCommand", "smartEnableAutoLoadMore"}, requireAll = false)
    public static void onLoadMoreCommand(SmartRefreshLayout swipeRefreshLayout, final BindingCommand onLoadMoreCommand, Boolean aBoolean) {
        swipeRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            if (onLoadMoreCommand != null) {
                onLoadMoreCommand.execute();
            }
        });
        if (aBoolean != null)
            swipeRefreshLayout.setEnableAutoLoadMore(aBoolean);
    }

    //标记SmartRefreshLayout的状态
    @BindingAdapter({"smartRefreshState"})
    public static void smartRefreshState(SmartRefreshLayout swipeRefreshLayout, SmartRefreshState smartRefreshState) {
        if (smartRefreshState != null) {
            if (smartRefreshState.equals(SmartRefreshState.REFRESH_FINISH)) {
                swipeRefreshLayout.finishRefresh();
            } else if (smartRefreshState.equals(SmartRefreshState.LOAD_FINISH)) {
                swipeRefreshLayout.finishRefresh();
                swipeRefreshLayout.finishLoadMore();
            } else if (smartRefreshState.equals(SmartRefreshState.LOAD_FINISH_NOMOREDATA)) {
                swipeRefreshLayout.finishRefresh();
                swipeRefreshLayout.finishLoadMoreWithNoMoreData();
            }

        }
    }

}
