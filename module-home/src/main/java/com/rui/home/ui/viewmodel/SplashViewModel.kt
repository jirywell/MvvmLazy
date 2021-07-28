package com.rui.home.ui.viewmodel

import android.app.Application
import android.os.Handler
import android.text.SpannableStringBuilder
import android.view.View
import com.alibaba.android.arouter.launcher.ARouter
import com.kongzue.dialogx.dialogs.MessageDialog
import com.rui.base.router.RouterActivityPath
import com.rui.home.R
import com.rui.home.data.HomeRepository
import com.rui.home.utils.TextBackClickUtils
import com.rui.mvvmlazy.base.BaseViewModel
import com.rui.mvvmlazy.utils.common.ToastUtils
import com.rui.mvvmlazy.utils.data.SPUtils

class SplashViewModel(application: Application) : BaseViewModel<HomeRepository?>(application) {
    override fun initData() {
        super.initData()
        if (!SPUtils.getInstance().getBoolean("showWelDialog", false)) {
            val content = getApplication<Application>().getString(R.string.home_welcome_dialog)
            val verification = SpannableStringBuilder()
            verification.append(content)
            val dialog = MessageDialog("用户协议和隐私政策", verification, "同意", "暂不使用")
                .setOkButtonClickListener { baseDialog, v ->
                    baseDialog.dismiss()
                    SPUtils.getInstance().put("showWelDialog", true)
                    Handler().postDelayed({
                        ARouter.getInstance().build(RouterActivityPath.Test.TESTPAGER)
                            .navigation()
                        finish()
                    }, 1500)
                    false
                }
                .setCancelButtonClickListener { baseDialog, v ->
                    baseDialog.dismiss()
                    finish()
                    false
                }
            dialog.show()
            val tvTip = dialog.dialogImpl.txtDialogTip
            TextBackClickUtils.setBackClick(
                tvTip,
                verification,
                "#2D6BF5",
                content.indexOf("《用户服务协议》"),
                content.indexOf("《用户服务协议》") + 8
            ) { view: View?, charSequence: CharSequence? ->
                ToastUtils.showShort("跳转《用户服务协议》")
            }
            TextBackClickUtils.setBackClick(
                tvTip,
                verification,
                "#2D6BF5",
                content.indexOf("《隐私政策》"),
                content.indexOf("《隐私政策》") + 6
            ) { view: View?, charSequence: CharSequence? ->
                ToastUtils.showShort("跳转《隐私政策》")
            }

        } else {
            Handler().postDelayed({
                ARouter.getInstance().build(RouterActivityPath.Test.TESTPAGER).navigation()
                finish()
            }, 1500)
        }
    }
}