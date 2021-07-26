package com.rui.mvvmlazy.utils.common;

import com.rui.mvvmlazy.base.BaseModel;
import com.rui.mvvmlazy.http.ExceptionHandle;
import com.trello.rxlifecycle4.LifecycleProvider;
import com.trello.rxlifecycle4.LifecycleTransformer;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableTransformer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

import com.rui.mvvmlazy.base.BaseViewModel;

/**
 * Created by zjr on 2020/6/19.
 * 有关Rx的工具类
 */
public class RxUtils {
    /**
     * 网络请求转换
     */
    public static <T, M extends BaseModel> ObservableTransformer<T, T> netTransformer(@NonNull BaseViewModel<M> baseViewModel) {
        return upstream -> {
            return //绑定页面生命周期
                    upstream.compose(bindToLifecycle(baseViewModel.getLifecycleProvider()))
                            //请求与ViewModel周期同步,ViewModel销毁取消注册
                            .doOnSubscribe(baseViewModel)
                            //线程调度
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            //异常处理
                            .onErrorResumeNext(new HttpResponseFunc<>());
        };
    }


    /**
     * 生命周期绑定
     *
     * @param lifecycle Fragment
     */
    public static <T, E> LifecycleTransformer<T> bindToLifecycle(@NonNull LifecycleProvider<E> lifecycle) {
        return lifecycle.bindToLifecycle();
    }

    /**
     * 线程调度器
     */
    public static <T> ObservableTransformer<T, T> schedulersTransformer() {
        return upstream -> upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static <T> ObservableTransformer<T, T> exceptionTransformer() {

        return observable -> observable.onErrorResumeNext(new HttpResponseFunc<>());
    }

    private static class HttpResponseFunc<T> implements Function<Throwable, Observable<T>> {
        @Override
        public Observable<T> apply(Throwable t) {
            return Observable.error(ExceptionHandle.handleException(t));
        }
    }

}
