package com.rui.demo.ui.fragment;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.core.AttachPopupView;
import com.lxj.xpopup.impl.LoadingPopupView;
import com.lxj.xpopup.interfaces.OnConfirmListener;
import com.lxj.xpopup.interfaces.OnInputConfirmListener;
import com.lxj.xpopup.interfaces.OnSelectListener;
import com.lxj.xpopupext.listener.CityPickerListener;
import com.lxj.xpopupext.listener.CommonPickerListener;
import com.lxj.xpopupext.listener.TimePickerListener;
import com.lxj.xpopupext.popup.CityPickerPopup;
import com.lxj.xpopupext.popup.CommonPickerPopup;
import com.lxj.xpopupext.popup.TimePickerPopup;
import com.rui.base.view.CustomEditTextBottomPopup;
import com.rui.demo.BR;
import com.rui.demo.R;
import com.rui.demo.databinding.TestFragmentDialogBinding;
import com.rui.demo.ui.viewmodel.DialogViewModel;
import com.rui.mvvmlazy.base.BaseFragment;
import com.rui.mvvmlazy.utils.common.ToastUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class DialogFragment extends BaseFragment<TestFragmentDialogBinding, DialogViewModel> {


    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.test_fragment_dialog;
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

    LoadingPopupView loadingPopup;

    public class Presenter {
        public void msgClick() {
            new XPopup.Builder(getContext())
                    .hasNavigationBar(false)
                    .isDestroyOnDismiss(true)
                    .asConfirm("哈哈", "床前明月光，疑是地上霜；举头望明月，低头思故乡。",
                            "取消", "确定",
                            new OnConfirmListener() {
                                @Override
                                public void onConfirm() {
                                    ToastUtils.showShort("click confirm");
                                }
                            }, null, false).show();
        }

        public void inputDialogClick() {
            new XPopup.Builder(getContext())
                    .hasStatusBarShadow(false)
                    //.dismissOnBackPressed(false)
                    .isDestroyOnDismiss(true) //对于只使用一次的弹窗，推荐设置这个
                    .autoOpenSoftInput(true)
                    .asInputConfirm("我是标题", null, null, "我是默认Hint文字",
                            new OnInputConfirmListener() {
                                @Override
                                public void onConfirm(String text) {
                                    ToastUtils.showShort(text);
                                }
                            })
                    .show();
        }

        public void loadingDialogClick() {
            if (loadingPopup == null) {
                loadingPopup = (LoadingPopupView) new XPopup.Builder(getContext())
                        .dismissOnBackPressed(false)
                        .asLoading("加载中")
                        .show();
            } else {
                loadingPopup.show();
            }
            loadingPopup.postDelayed(new Runnable() {
                @Override
                public void run() {
                    loadingPopup.setTitle("加载中长度变化啊");
                    loadingPopup.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            loadingPopup.setTitle("");
                        }
                    }, 2000);
                }
            }, 2000);
//                loadingPopup.smartDismiss();
//                loadingPopup.dismiss();
            loadingPopup.delayDismissWith(6000, new Runnable() {
                @Override
                public void run() {
                    ToastUtils.showShort("我消失了！！！");
                }
            });
        }

        public void spinnerDialogClick(View v) {
            AttachPopupView attachPopupView = new XPopup.Builder(getContext())
                    .hasShadowBg(false)
                    .isClickThrough(true)
//                            .isDestroyOnDismiss(true) //对于只使用一次的弹窗，推荐设置这个
//                        .isDarkTheme(true)
//                        .popupAnimation(PopupAnimation.ScrollAlphaFromTop) //NoAnimation表示禁用动画
                    .isCenterHorizontal(true) //是否与目标水平居中对齐
//                        .offsetY(60)
//                        .offsetX(80)
//                        .popupPosition(PopupPosition.Top) //手动指定弹窗的位置
//                        .popupWidth(500)
                    .atView(v)  // 依附于所点击的View，内部会自动判断在上方或者下方显示
                    .asAttachList(new String[]{"分享", "编辑", "分享", "编辑"
                            },
                            null,
//                                new int[]{R.mipmap.ic_launcher_round, R.mipmap.ic_launcher_round},
                            new OnSelectListener() {
                                @Override
                                public void onSelect(int position, String text) {
                                    ToastUtils.showShort("click " + text);
                                }
                            }, 0, 0/*, Gravity.LEFT*/);
            ;
            attachPopupView.show();
        }

        public void commentDialogClick() {
            new XPopup.Builder(getContext())
                    .hasStatusBarShadow(false)
                    .isDestroyOnDismiss(true) //对于只使用一次的弹窗，推荐设置这个
                    .autoOpenSoftInput(true)
                    .asCustom(new CustomEditTextBottomPopup(getContext(), new OnInputConfirmListener() {
                        @Override
                        public void onConfirm(String text) {
                            ToastUtils.showShort(text);
                        }
                    }))
                    .show();
        }

        public void btmListDialogClick() {
            new XPopup.Builder(getContext())
                    .hasShadowBg(true)
//                            .hasBlurBg(true)
//                            .isDestroyOnDismiss(true) //对于只使用一次的弹窗，推荐设置这个
                    .asBottomList("请选择一项", new String[]{"条目1", "条目2", "条目3", "条目4", "条目5", "条目6", "条目7"},
                            new OnSelectListener() {
                                @Override
                                public void onSelect(int position, String text) {
                                    ToastUtils.showShort("click " + text);
                                }
                            }).show();
        }

        public void btmListCheckDialogClick() {
            new XPopup.Builder(getContext())
                    .isDestroyOnDismiss(true) //对于只使用一次的弹窗，推荐设置这个
                    .asBottomList("标题可以没有", new String[]{"条目1", "条目2", "条目3", "条目4", "条目5"},
                            null, 2,
                            new OnSelectListener() {
                                @Override
                                public void onSelect(int position, String text) {
                                    ToastUtils.showShort("click " + text);
                                }
                            })
                    .show();
        }

        public void dateDialogClick() {
            Calendar date = Calendar.getInstance();
            date.set(2000, 5, 1);
            Calendar date2 = Calendar.getInstance();
            date2.set(2020, 5, 1);
            TimePickerPopup popup = new TimePickerPopup(getContext())
//                        .setDefaultDate(date)  //设置默认选中日期
//                        .setYearRange(1990, 1999) //设置年份范围
//                        .setDateRange(date, date2) //设置日期范围
                    .setTimePickerListener(new TimePickerListener() {
                        @Override
                        public void onTimeChanged(Date date) {
                            //时间改变
                        }

                        @Override
                        public void onTimeConfirm(Date date, View view) {
                            //点击确认时间
                            ToastUtils.showShort("选择的时间：" + date.toLocaleString());
                        }
                    });

            new XPopup.Builder(getContext())
                    .asCustom(popup)
                    .show();
        }

        public void cityDialogClick() {
            CityPickerPopup popup = new CityPickerPopup(getContext());
            popup.setCityPickerListener(new CityPickerListener() {
                @Override
                public void onCityConfirm(String province, String city, String area, View v) {
                    Log.e("tag", province + " - " + city + " - " + area);
                    ToastUtils.showShort(province + " - " + city + " - " + area);
                }

                @Override
                public void onCityChange(String province, String city, String area) {
                    Log.e("tag", province + " - " + city + " - " + area);
                    ToastUtils.showShort(province + " - " + city + " - " + area);
                }
            });
            new XPopup.Builder(getContext())
                    .asCustom(popup)
                    .show();
        }

        public void norListDialogClick() {
            CommonPickerPopup popup = new CommonPickerPopup(getContext());
            ArrayList<String> list = new ArrayList<>();
            list.add("小猫");
            list.add("小狗");
            list.add("小羊");
            popup.setPickerData(list)
                    .setCurrentItem(1);
            popup.setCommonPickerListener(new CommonPickerListener() {
                @Override
                public void onItemSelected(int index, String data) {
                    ToastUtils.showShort("选中的是 " + data);
                }
            });
            new XPopup.Builder(getContext())
                    .asCustom(popup)
                    .show();
        }
    }
}