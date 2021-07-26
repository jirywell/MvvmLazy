package com.rui.test.ui.fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.navigation.fragment.NavHostFragment;

import com.rui.mvvmlazy.base.BaseFragment;
import com.rui.mvvmlazy.http.DownLoadManager;
import com.rui.mvvmlazy.http.download.ProgressCallBack;
import com.rui.mvvmlazy.utils.common.KLog;
import com.rui.mvvmlazy.utils.common.ToastUtils;
import com.rui.test.BR;
import com.rui.test.R;
import com.rui.test.databinding.TestFragmentHomeBinding;
import com.rui.test.ui.viewmodel.TestViewModel;
import com.tbruyelle.rxpermissions3.RxPermissions;

import okhttp3.ResponseBody;

/**
 * 测试入口页面
 */
public class TestFragment extends BaseFragment<TestFragmentHomeBinding, TestViewModel> {

    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.test_fragment_home;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        super.initData();
        binding.setPresenter(new Presenter());
    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();

    }

    /**
     * 封装布局中的点击事件儿;
     */
    public class Presenter {
        public void bindingClick() {
            NavHostFragment
                    .findNavController(TestFragment.this)
                    .navigate(R.id.home_action_testfragment_to_bindingfragment);
        }

        public void listClick() {
            NavHostFragment
                    .findNavController(TestFragment.this)
                    .navigate(R.id.home_action_testfragment_to_home_paginationfragment);
        }

        public void rvMultiClick() {
            NavHostFragment
                    .findNavController(TestFragment.this)
                    .navigate(R.id.home_action_testfragment_to_home_listfragment);
        }

        public void errorClick() {
            Integer.parseInt("你好");
        }

        public void permissionClick() {
            requestCameraPermissions();
        }

        public void dataBaseClick() {
            NavHostFragment
                    .findNavController(TestFragment.this)
                    .navigate(R.id.home_action_testfragment_to_home_roomsamplefragment);
        }
    }

    /**
     * 请求相机权限
     */
    @SuppressLint("CheckResult")
    private void requestCameraPermissions() {
        ToastUtils.showShort("请求相机权限");
        //请求打开相机权限
        RxPermissions rxPermissions = new RxPermissions(getActivity());
        rxPermissions.request(Manifest.permission.CAMERA)
                .subscribe(aBoolean -> {
                    if (aBoolean) {
                        ToastUtils.showShort("相机权限已经打开，直接跳入相机");
                    } else {
                        ToastUtils.showShort("权限被拒绝");
                    }
                });
    }

    private void downFile(String url) {
        String destFileDir = getActivity().getApplication().getCacheDir().getPath();
        KLog.e("destFileDir--" + destFileDir);
        String destFileName = System.currentTimeMillis() + ".apk";
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setTitle("正在下载...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        DownLoadManager.getInstance().load(url, new ProgressCallBack<ResponseBody>(destFileDir, destFileName) {
            @Override
            public void onStart() {
                super.onStart();
                KLog.e("下载--onStart");
            }

            @Override
            public void onSuccess(ResponseBody responseBody) {
                KLog.e("下载--onSuccess");
                ToastUtils.showShort("文件下载完成！");
            }

            @Override
            public void progress(final long progress, final long total) {
                KLog.e("下载--progress");
                progressDialog.setMax((int) total);
                progressDialog.setProgress((int) progress);
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                ToastUtils.showShort("文件下载失败！");
                progressDialog.dismiss();
            }

            @Override
            public void onCompleted() {
                progressDialog.dismiss();
                KLog.e("下载--onCompleted");
            }
        });
    }
}
