package com.rui.demo.ui.fragment;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.lxj.xpopup.impl.LoadingPopupView;
import com.lxj.xpopup.interfaces.OnConfirmListener;
import com.lxj.xpopup.interfaces.OnInputConfirmListener;
import com.lxj.xpopup.interfaces.OnSelectListener;
import com.lxj.xpopupext.listener.CityPickerListener;
import com.lxj.xpopupext.listener.CommonPickerListener;
import com.lxj.xpopupext.listener.TimePickerListener;
import com.lxj.xpopupext.popup.TimePickerPopup;
import com.rui.base.utils.DialogUtils;
import com.rui.demo.BR;
import com.rui.demo.R;
import com.rui.demo.databinding.TestFragmentDialogBinding;
import com.rui.demo.ui.viewmodel.DialogViewModel;
import com.rui.mvvmlazy.base.BaseFragment;
import com.rui.mvvmlazy.utils.common.ToastUtils;

import java.util.ArrayList;
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


    public class Presenter {
        public void msgClick() {
            DialogUtils.showConfirmDialog("哈哈", "床前明月光，疑是地上霜；举头望明月，低头思故乡。", new OnConfirmListener() {
                @Override
                public void onConfirm() {
                    ToastUtils.showShort("click confirm");
                }
            }, null);

        }

        public void inputDialogClick() {
            DialogUtils.showInputDialog("我是标题", null, new OnInputConfirmListener() {
                @Override
                public void onConfirm(String text) {
                    ToastUtils.showShort(text);
                }
            });
        }

        public void hintDialogClick(int type) {
            switch (type) {
                case 1:
                    DialogUtils.showHintFinish("完成");
                    break;
                case 2:
                    DialogUtils.showHintError("错误");

                    break;
                case 3:
                    DialogUtils.showHintWarning("警告");

                    break;
                default:
                    break;
            }
        }

        public void loadingDialogClick() {
            LoadingPopupView loadingPopup = DialogUtils.showLoading("加载中");
            loadingPopup.postDelayed(new Runnable() {
                @Override
                public void run() {
                    loadingPopup.setTitle("加载中长度变化啊");
                    loadingPopup.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            loadingPopup.setTitle("");
                            DialogUtils.cancelLoading();
                        }
                    }, 2000);
                }
            }, 2000);
        }

        public void spinnerDialogClick(View v) {
            DialogUtils.showAttachList(v, new String[]{"分享", "编辑", "分享", "编辑"
                    },
                    null,
                    new OnSelectListener() {
                        @Override
                        public void onSelect(int position, String text) {
                            ToastUtils.showShort("click " + text);
                        }
                    });

        }

        public void commentDialogClick() {
            DialogUtils.showCommentDialog(new OnInputConfirmListener() {
                @Override
                public void onConfirm(String text) {
                    ToastUtils.showShort(text);
                }
            });
        }

        public void btmListDialogClick() {
            DialogUtils.showBtmListDialog("请选择一项", new String[]{"条目1", "条目2", "条目3", "条目4", "条目5", "条目6", "条目7"},
                    new OnSelectListener() {
                        @Override
                        public void onSelect(int position, String text) {
                            ToastUtils.showShort("click " + text);
                        }
                    });
        }

        public void btmListCheckDialogClick() {
            DialogUtils.showBtmListDialog("标题可以没有", new String[]{"条目1", "条目2", "条目3", "条目4", "条目5"},
                    null, 2,
                    new OnSelectListener() {
                        @Override
                        public void onSelect(int position, String text) {
                            ToastUtils.showShort("click " + text);
                        }
                    });
        }

        public void dateDialogClick() {
            DialogUtils.showTimeDialog(TimePickerPopup.Mode.YMD,new TimePickerListener() {
                @Override
                public void onTimeChanged(Date date) {

                }

                @Override
                public void onTimeConfirm(Date date, View view) {
                    //点击确认时间
                    ToastUtils.showShort("选择的时间：" + date.toLocaleString());
                }
            });
        }

        public void cityDialogClick() {
            DialogUtils.showCityDialog(new CityPickerListener() {
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
        }

        public void norListDialogClick() {
            ArrayList<String> list = new ArrayList<>();
            list.add("小猫");
            list.add("小狗");
            list.add("小羊");
            list.add("小鸡");
            list.add("小鸭");
            DialogUtils.showNormalListDialog(list, new CommonPickerListener() {
                @Override
                public void onItemSelected(int index, String data) {
                    ToastUtils.showShort("选中的是 " + data);
                }
            });
        }
    }
}