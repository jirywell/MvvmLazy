package com.rui.demo;

import android.app.Application;

import com.rui.base.base.IModuleInit;

import com.rui.mvvmlazy.utils.common.KLog;

/**
 * Created by zjr on 2018/6/21 0021.
 */

public class ModuleInit implements IModuleInit {
    @Override
    public boolean onInitAhead(Application application) {
        KLog.e("工作模块初始化 -- onInitAhead");
        return false;
    }

    @Override
    public boolean onInitLow(Application application) {
        KLog.e("工作模块初始化 -- onInitLow");
        return false;
    }
}
