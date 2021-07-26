package com.rui.base.utils;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 创建日期： 2020/6/22 15:10
 * 作者：     lei
 * 文件名称： DialogUtils
 * 类说明：
 */

public class DialogUtils {
    private Dialog dialog;
    private AlertDialog mProccessDialog;
    private Context mContext;

    private DialogUtils() {
    }

    private static volatile DialogUtils instance;

    public static DialogUtils getInstance() {
        if (instance == null) {
            synchronized (DialogUtils.class) {
                if (instance == null) {
                    instance = new DialogUtils();
                }
            }
        }
        return instance;
    }

    /**
     * 单项选择器
     *
     * @param context
     * @param textView 赋值控件
     * @param list     数据源
     */
//    public void showOptionDialog(Context context, final TextView textView, final List<String> list, final SingleOption singleOption) {
//        OptionsPickerBuilder optionsPickerBuilder = new OptionsPickerBuilder(context, new OnOptionsSelectListener() {
//            @Override
//            public void onOptionsSelect(int options1, int options2, int options3, View v) {
//                if (textView != null) {
//                    textView.setText(list.get(options1));
//                }
//                if (singleOption != null) {
//                    singleOption.selectedOption(list.get(options1), options1);
//                }
//
//            }
//        });
//        optionsPickerBuilder.setTitleText("");
//        optionsPickerBuilder.setCancelColor(context.getResources().getColor(R.color.apptheme));
//        optionsPickerBuilder.setDividerColor(context.getResources().getColor(R.color.color_ff));
//        optionsPickerBuilder.setCancelText("取消");
//        optionsPickerBuilder.setSubmitText("确认");
//        optionsPickerBuilder.setSubmitColor(context.getResources().getColor(R.color.apptheme));
//        optionsPickerBuilder.setBgColor(context.getResources().getColor(R.color.color_ff));
//        optionsPickerBuilder.setItemVisibleCount(3);
//        optionsPickerBuilder.setSubCalSize(15);
//        optionsPickerBuilder.setContentTextSize(21);
//        optionsPickerBuilder.setTitleBgRes(context.getResources().getDrawable(R.drawable.shape_dialog_time_btn1));
//        optionsPickerBuilder.isDialog(false);
//        optionsPickerBuilder.setLineSpacingMultiplier(3f);
//        OptionsPickerView build = optionsPickerBuilder.build();
//        build.setPicker(list);
//        build.show();
//    }
//
//    /**
//     * 年月时间选择器
//     * 时间Dialog
//     */
//    public void showTimeDialog(Context context, final OnTimeSelectListener onTimeSelectListener) {
//
//        TimePickerBuilder timePickerBuilder = new TimePickerBuilder(context, onTimeSelectListener);
//        timePickerBuilder.setType(new boolean[]{true, true, true, true, true, false});
//        timePickerBuilder.setTitleColor(context.getResources().getColor(R.color.color_27));
//        timePickerBuilder.setTitleSize(15);
//        timePickerBuilder.setTitleText("选择时间");
//        timePickerBuilder.setCancelColor(context.getResources().getColor(R.color.themeRedColor));
//        timePickerBuilder.setDividerColor(context.getResources().getColor(R.color.color_ff));
//        timePickerBuilder.setCancelText("取消");
//        timePickerBuilder.setSubmitColor(context.getResources().getColor(R.color.themeRedColor));
//        timePickerBuilder.setBgColor(context.getResources().getColor(R.color.color_ff));
//        timePickerBuilder.setItemVisibleCount(3);
//        timePickerBuilder.setSubCalSize(15);
//        timePickerBuilder.setContentTextSize(18);
//        timePickerBuilder.setTitleBgRes(context.getResources().getDrawable(R.drawable.shape_dialog_time_btn1));
//        timePickerBuilder.isDialog(false);
//        timePickerBuilder.setLineSpacingMultiplier(3f);
//        timePickerBuilder.setSubmitText("完成");
//        TimePickerView build = timePickerBuilder.build();
//        build.setDate(Calendar.getInstance());
//        build.show();
//    }

    /**
     * 获取当前时间年月
     */
    @SuppressLint("SimpleDateFormat")
    public static String getTimeYM(Date date) {
        return new SimpleDateFormat("yyyy年MM月").format(date);
    }

}
