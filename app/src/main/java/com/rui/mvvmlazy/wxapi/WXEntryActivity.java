package com.rui.mvvmlazy.wxapi;

import android.app.Activity;
import android.os.Bundle;

import com.rui.base.base.BaseModuleInit;
import com.rui.base.interfaces.ShareListener;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;


import com.rui.mvvmlazy.bus.RxBus;

/**
 * 微信回调处理
 */
public class WXEntryActivity extends Activity implements IWXAPIEventHandler {
    private ShareListener shareListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //注册微信回调
        BaseModuleInit.iwxapi.handleIntent(getIntent(), this);
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {
        //Log.e("---接收到信息--",baseResp.openId);
        RxBus.getDefault().post(baseResp);
        finish();
    }

}
