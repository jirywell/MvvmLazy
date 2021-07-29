package com.rui.demo.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.rui.demo.data.DiscoverRepository;
import com.rui.demo.data.bean.CityInfo;

import java.util.ArrayList;
import java.util.List;

import com.rui.mvvmlazy.base.BaseViewModel;
import com.rui.mvvmlazy.binding.command.BindingAction;
import com.rui.mvvmlazy.binding.command.BindingCommand;
import com.rui.mvvmlazy.binding.command.BindingConsumer;
import com.rui.mvvmlazy.binding.viewadapter.spinner.IKeyAndValue;
import com.rui.mvvmlazy.utils.common.ToastUtils;

public class BindingViewModel extends BaseViewModel<DiscoverRepository> {
    public BindingViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<String> imgUrl = new MutableLiveData<>("http://video.hnbxwhy.com/ads/77b65f2b-e31d-4cfd-b6d9-39ee1f9dfc78.jpg");
    public MutableLiveData<List<IKeyAndValue>> data = new MutableLiveData<>();

    public BindingCommand clickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            ToastUtils.showShort("点到我了");
        }
    });
    public BindingCommand<Boolean> checkCommand = new BindingCommand<>(new BindingConsumer<Boolean>() {

        @Override
        public void call(Boolean aBoolean) {
            ToastUtils.showShort("选中" + aBoolean);
        }
    });
    public BindingCommand<Boolean> swichCommand = new BindingCommand<>(new BindingConsumer<Boolean>() {

        @Override
        public void call(Boolean aBoolean) {
            ToastUtils.showShort("开关" + aBoolean);
        }
    });
    public BindingCommand<IKeyAndValue> selectCommand = new BindingCommand<>(new BindingConsumer<IKeyAndValue>() {

        @Override
        public void call(IKeyAndValue cityInfo) {
            ToastUtils.showShort("选中"+cityInfo.getKey());
        }
    });
    @Override
    public void initData() {
        super.initData();
        List<IKeyAndValue> iKeyAndValues = new ArrayList<>();
        iKeyAndValues.add(new CityInfo("中国","111"));
        iKeyAndValues.add(new CityInfo("美国","122"));
        iKeyAndValues.add(new CityInfo("日本","133"));
        iKeyAndValues.add(new CityInfo("英国","143"));
        data.setValue(iKeyAndValues);

    }
}