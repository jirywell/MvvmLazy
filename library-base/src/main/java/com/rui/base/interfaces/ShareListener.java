package com.rui.base.interfaces;

/**
 * 分享回调
 */
public interface ShareListener {
    void onComplete(int platForm, String result);

    void error(String result);

    void cancel(String result);

    void posterClick();
}
