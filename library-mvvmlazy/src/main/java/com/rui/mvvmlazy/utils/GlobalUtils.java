package com.rui.mvvmlazy.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresPermission;

import com.rui.mvvmlazy.base.AppManager;
import com.rui.mvvmlazy.utils.app.ProcessUtils;
import com.rui.mvvmlazy.utils.app.ServiceUtils;

import static android.Manifest.permission.KILL_BACKGROUND_PROCESSES;

/**
 * Created by zjr on 2020/5/14.
 * 全局工具类
 */
public final class GlobalUtils {
    /**
     * 主线程Handler
     */
    private static final Handler MAIN_HANDLER = new Handler(Looper.getMainLooper());
    @SuppressLint("StaticFieldLeak")
    private static Context context;

    private GlobalUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 初始化工具类
     *
     * @param context 上下文
     */
    public static void init(@NonNull final Context context) {
        GlobalUtils.context = context.getApplicationContext();
    }

    /**
     * 获取ApplicationContext
     *
     * @return ApplicationContext
     */
    public static Context getContext() {
        if (context != null) {
            return context;
        }
        throw new NullPointerException("should be initialized in application");
    }

    //===================全局Handler========================//

    /**
     * 获取主线程的Handler
     *
     * @return 主线程Handler
     */
    public static Handler getMainHandler() {
        return MAIN_HANDLER;
    }

    /**
     * 在主线程中执行
     *
     * @param runnable
     * @return
     */
    public static boolean runOnUiThread(Runnable runnable) {
        return getMainHandler().post(runnable);
    }

    /**
     * 退出程序
     */
    @RequiresPermission(KILL_BACKGROUND_PROCESSES)
    public static void exitApp() {
        AppManager.getAppManager().appExit();
        ServiceUtils.stopAllRunningService(getContext());
        ProcessUtils.killBackgroundProcesses(getContext().getPackageName());
        System.exit(0);
    }

}