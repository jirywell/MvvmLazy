package com.rui.base.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;

import androidx.core.content.ContextCompat;

import com.alibaba.android.arouter.launcher.ARouter;
import com.kongzue.dialogx.DialogX;
import com.kongzue.dialogx.style.IOSStyle;
import com.kongzue.dialogx.util.InputInfo;
import com.kongzue.dialogx.util.TextInfo;
import com.rui.base.BuildConfig;
import com.rui.base.R;
import com.rui.mvvmlazy.utils.common.KLog;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshFooter;
import com.scwang.smart.refresh.layout.api.RefreshHeader;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.DefaultRefreshFooterCreator;
import com.scwang.smart.refresh.layout.listener.DefaultRefreshHeaderCreator;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.zzhoujay.richtext.RichText;

/**
 * ******************************
 * *@Author
 * *date ：2020/6/2 11:59
 * *description:基础库初始化
 * *******************************
 */
public class BaseModuleInit implements IModuleInit {
    public static IWXAPI iwxapi;
    public static Application sInstance;
    public static Handler mHandler;

    @Override
    public boolean onInitAhead(Application application) {
        sInstance = application;
        mHandler = new Handler();
        //开启打印日志
        KLog.init(true);
        //初始化阿里路由框架
        if (BuildConfig.DEBUG) {
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(application); // 尽可能早，推荐在Application中初始化
        initDialogx(application);
        registerActivityLifecycle(application);
        RichText.initCacheDir(application);
        KLog.e("基础层初始化 -- onInitAhead");
        return false;
    }

    /**
     * 配置dialogx框架
     *
     * @param application
     */
    public void initDialogx(Application application) {
        DialogX.init(application);
//        DialogX.implIMPLMode= DialogX.IMPL_MODE.DIALOG_FRAGMENT;
        //开启调试模式，在部分情况下会使用 Log 输出日志信息
        DialogX.DEBUGMODE = true;

//设置主题样式
//        DialogX.globalStyle = MaterialStyle.style();
        DialogX.globalStyle = IOSStyle.style();

//设置亮色/暗色（在启动下一个对话框时生效）
        DialogX.globalTheme = DialogX.THEME.LIGHT;

//设置对话框最大宽度（单位为像素）
        DialogX.dialogMaxWidth = 1920;

//设置 InputDialog 自动弹出键盘
        DialogX.autoShowInputKeyboard = true;

//限制 PopTip 一次只显示一个实例（关闭后可以同时弹出多个 PopTip）
        DialogX.onlyOnePopTip = true;
        //设置对话框默认按钮文本字体样式
        DialogX.buttonTextInfo = new TextInfo().setFontColor(ContextCompat.getColor(application, R.color.text_major));

//设置对话框默认确定按钮文字样式
        DialogX.okButtonTextInfo = new TextInfo().setFontColor(ContextCompat.getColor(application, R.color.text_major));

//设置对话框默认标题文字样式
        DialogX.titleTextInfo = new TextInfo().setFontColor(ContextCompat.getColor(application, R.color.text_major));

//设置对话框默认内容文字样式
        DialogX.messageTextInfo = new TextInfo().setFontColor(ContextCompat.getColor(application, R.color.text_def));

//设置默认 WaitDialog 和 TipDialog 文字样式
        DialogX.tipTextInfo = new TextInfo().setFontColor(ContextCompat.getColor(application, R.color.text_def));

//设置默认输入框文字样式
        DialogX.inputInfo = new InputInfo().setTextInfo(new TextInfo().setFontColor(ContextCompat.getColor(application, R.color.text_def)));

//设置默认底部菜单、对话框的标题文字样式
        DialogX.menuTitleInfo = new TextInfo().setFontColor(ContextCompat.getColor(application, R.color.text_major));

//设置默认底部菜单文本样式
        DialogX.menuTextInfo = new TextInfo().setFontColor(ContextCompat.getColor(application, R.color.text_def));

//设置默认对话框背景颜色（值为ColorInt，为-1不生效）
        DialogX.backgroundColor = Color.WHITE;

//设置默认对话框默认是否可以点击外围遮罩区域或返回键关闭，此开关不影响提示框（TipDialog）以及等待框（TipDialog）
        DialogX.cancelable = true;

//设置默认提示框及等待框（WaitDialog、TipDialog）默认是否可以关闭
        DialogX.cancelableTipDialog = false;

//设置默认 PopTip 文本样式
        DialogX.popTextInfo = new TextInfo().setFontColor(ContextCompat.getColor(application, R.color.text_def));
    }

    //static 代码段可以防止内存泄露
    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                layout.setPrimaryColorsId(R.color.white, android.R.color.black);//全局设置主题颜色
                return new ClassicsHeader(context).setEnableLastTime(false);
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                return new ClassicsFooter(context).setDrawableSize(20);
            }
        });
    }

    public static Application getInstance() {
        return sInstance;
    }

    private void registerActivityLifecycle(Application application) {
        application.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {
//                MobclickAgent.onResume(activity);
            }

            @Override
            public void onActivityPaused(Activity activity) {
//                MobclickAgent.onPause(activity);
            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });
    }


    @Override
    public boolean onInitLow(Application application) {
        KLog.e("基础层初始化 -- onInitLow");
        return false;
    }

}
