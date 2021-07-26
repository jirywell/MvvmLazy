package com.rui.mvvmlazy.http;

import androidx.lifecycle.LifecycleObserver;

import com.rui.mvvmlazy.utils.common.KLog;
import com.rui.mvvmlazy.utils.common.ToastUtils;
import com.rui.mvvmlazy.utils.GlobalUtils;

import io.reactivex.rxjava3.observers.DisposableObserver;

/**
 * *******************************
 * *@Author
 * *date ：
 * *description:统一的Code封装处理。该类仅供参考，实际业务逻辑, 根据需求来定义，
 * *******************************59
 */
public abstract class ApiDisposableObserver<T> extends DisposableObserver<T> implements LifecycleObserver {
    public abstract void onResultOk(T t);

    /**
     * 所有异常同意处理
     *
     * @param throwable
     */
    public abstract void onResultFailed(ResponseThrowable throwable);

    @Override
    public void onComplete() {

    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        if (e instanceof ResponseThrowable) {
            ResponseThrowable rError = (ResponseThrowable) e;
            ToastUtils.showShort(rError.message);
            onResultFailed(rError);
            return;
        }
        //其他全部甩锅网络异常
        ToastUtils.showShort("网络异常");
    }

    @Override
    public void onStart() {
        super.onStart();
        // if  NetworkAvailable no !   must to call onCompleted
        if (!NetworkUtil.isNetworkAvailable(GlobalUtils.getContext())) {
            KLog.d("无网络，读取缓存数据");
            onComplete();
        }
    }

    @Override
    public void onNext(Object o) {
        BaseResponse baseResponse = (BaseResponse) o;
//        if (baseResponse.data == null) {
//            baseResponse.data = new BaseResponse.DataContent();
//            baseResponse.data.status = CodeRule.CODE_502;
//            baseResponse.data.msg = "数据异常";
//        }
        switch (baseResponse.code) {
            case CodeRule.CODE_200:
                //请求成功, 正确的操作方式
                onResultOk((T) baseResponse);
                break;
//            case CodeRule.CODE_1:
//                    break;
//            case CodeRule.CODE_400:
//                //不作处理
//                break;
//            case CodeRule.CODE_220:
//                // 请求成功, 正确的操作方式, 并消息提示
//                onResult((T) baseResponse);
//                break;
//            case CodeRule.CODE_300:
//                //请求失败，不打印Message
//                KLog.e("请求失败");
//                ToastUtils.showShort("错误代码:", baseResponse.data.status);
//                break;
//            case CodeRule.CODE_330:
//                //请求失败，打印Message
////                ToastUtils.showShort(baseResponse.data.msg);
//                break;
//            case CodeRule.CODE_500:
//                //服务器内部异常
////                ToastUtils.showShort("错误代码:", baseResponse.data.status);
//                doErrorResult(baseResponse);
//                break;
//            case CodeRule.CODE_503:
//                //参数为空
////                KLog.e("参数为空");
//                break;
//            case CodeRule.CODE_502:
//                //没有数据
//                KLog.e("没有数据");
//                break;
//            case CodeRule.CODE_510:
//                //无效的Token，提示跳入登录页
//                ToastUtils.showShort("token已过期，请重新登录");
//                //关闭所有页面
//                AppManager.getAppManager().finishAllActivity();
//                //跳入登录界面
//                //*****该类仅供参考，实际业务Code, 根据需求来定义，******//
//                break;
//            case CodeRule.CODE_530:
//                ToastUtils.showShort("请先登录");
//                break;
//            case CodeRule.CODE_551:
//                ToastUtils.showShort("错误代码:", baseResponse.data.status);
//                break;
            default:
                doErrorResult(baseResponse);
//                ToastUtils.showShort("错误代码:", baseResponse.data.status);
                if (baseResponse.message == null || baseResponse.message.equals("")) {
                    ToastUtils.showShort(baseResponse.message);
                } else {
                    ToastUtils.showShort(baseResponse.message);
                }

                break;
        }
    }

    private void doErrorResult(BaseResponse baseResponse) {
        ResponseThrowable responseThrowable = new ResponseThrowable(new Exception(baseResponse.message), baseResponse.code);
        responseThrowable.message = baseResponse.message;
        onResultFailed(responseThrowable);
    }

    public static final class CodeRule {
        //请求成功, 正确的操作方式
        static final int CODE_200 = 200;
        static final int CODE_0 = 0;
        static final int CODE_1 = 1;
        //请求成功, 消息提示
        static final int CODE_220 = 220;
        //请求失败，不打印Message
        static final int CODE_300 = 300;
        //请求失败，打印Message
        static final int CODE_330 = 330;
        static final int CODE_400 = 400;
        //服务器内部异常
        static final int CODE_500 = 500;
        //参数为空
        static final int CODE_503 = 503;
        //没有数据
        static final int CODE_502 = 502;
        //无效的Token
        static final int CODE_510 = 510;
        //未登录
        static final int CODE_530 = 530;
        //请求的操作异常终止：未知的页面类型
        static final int CODE_551 = 551;
    }
}