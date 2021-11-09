package com.rui.base.base;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.startup.Initializer;

import com.alibaba.android.arouter.launcher.ARouter;
import com.rui.base.BuildConfig;
import com.rui.base.R;
import com.rui.mvvmlazy.utils.common.KLog;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.zzhoujay.richtext.RichText;

import java.util.Collections;
import java.util.List;

/**
 * ******************************
 * *@Author
 * *date ：2020/6/2 11:59
 * *description:基础库初始化
 * *******************************
 */
public class BaseModuleInit implements Initializer {
    //static 代码段可以防止内存泄露
    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator((context, layout) -> {
            layout.setPrimaryColorsId(R.color.white, android.R.color.black);//全局设置主题颜色
            return new ClassicsHeader(context).setEnableLastTime(false);
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator((context, layout) -> {
            //指定为经典Footer，默认是 BallPulseFooter
            return new ClassicsFooter(context).setDrawableSize(20);
        });
    }


    @NonNull
    @Override
    public Object create(@NonNull Context context) {
        //开启打印日志
        KLog.init(true);
        //初始化阿里路由框架
        if (BuildConfig.DEBUG) {
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init((Application) context); // 尽可能早，推荐在Application中初始化
        RichText.initCacheDir(context);
        KLog.d("基础层初始化");
        return null;
    }

    @NonNull
    @Override
    public List<Class<? extends Initializer<?>>> dependencies() {
        return Collections.emptyList();
    }
}
