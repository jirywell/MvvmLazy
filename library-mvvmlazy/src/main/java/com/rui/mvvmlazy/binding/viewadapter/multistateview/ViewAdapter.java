package com.rui.mvvmlazy.binding.viewadapter.multistateview;

import androidx.databinding.BindingAdapter;

import com.rui.mvvmlazy.binding.command.BindingCommand;
import com.rui.mvvmlazy.widget.MultiStateView;

/**
 * Created by zjr on 2020/6/18.
 */
public class ViewAdapter {
    @BindingAdapter("bindViewState")
    public static void bindViewState(MultiStateView multiStateView, MultiStateView.ViewState viewState) {
        if (viewState != null) {
            multiStateView.setViewState(viewState);
        }
    }

    @BindingAdapter("retryClick")
    public static void retryClick(MultiStateView multiStateView, BindingCommand clickCommand) {
        if (clickCommand != null) {
            multiStateView.setOnEmptyClickListener(v -> clickCommand.execute());
            multiStateView.setOnErrorClickListener(v -> clickCommand.execute());
        }
    }


}
