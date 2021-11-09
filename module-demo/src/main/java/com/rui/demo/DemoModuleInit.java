package com.rui.demo;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.startup.Initializer;

import com.rui.mvvmlazy.utils.common.KLog;

import java.util.Collections;
import java.util.List;

/**
 * ******************************
 * *@Author
 * *date ：2020/6/2 11:59
 * *description:基础库初始化
 * *******************************
 */
public class DemoModuleInit implements Initializer {

    @NonNull
    @Override
    public Object create(@NonNull Context context) {
        KLog.d("demo组件初始化");
        return null;
    }

    @NonNull
    @Override
    public List<Class<? extends Initializer<?>>> dependencies() {
        return Collections.emptyList();
    }
}
