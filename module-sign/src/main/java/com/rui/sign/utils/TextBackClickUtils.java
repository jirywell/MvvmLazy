package com.rui.sign.utils;

import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.rui.mvvmlazy.base.BaseApplication;

/**
 * Author ：zjr
 * Date: 2020/6/4 00049:58
 * Description: 设置text背景色和点击事件工具类
 */

public class TextBackClickUtils {
    public static String  defaultColorString = "#000000";

    public static void setBackClick(TextView  tv,SpannableStringBuilder spannableStringBuilder,String colorString, int start, int end,TextClickListener   textClickListener) {
        setTextClick(tv, spannableStringBuilder, start, end, textClickListener);
        setTextBackGround(tv, spannableStringBuilder, start, end, colorString);
    }

    /**
     * 设置textView 部分内容的点击事件
     * @param tv    TextView  控件
     * @param spannableStringBuilder   显示的文字
     * @param start                     开始位置
     * @param end                       结束位置
     * @param textClickListener           监听类
     */
    public static void   setTextClick(TextView  tv,SpannableStringBuilder spannableStringBuilder,int  start,int  end,TextClickListener textClickListener){
        //设置部分文字点击事件
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                textClickListener.onClick(widget,spannableStringBuilder.subSequence(start,end));
            }

            //去除连接下划线
            @Override
            public void updateDrawState(TextPaint ds) {
                /**set textColor**/
                ds.setColor(ds.linkColor);
                /**Remove the underline**/
                ds.setUnderlineText(false);
            }

        };
        ((TextView)tv).setHighlightColor(BaseApplication.getInstance().getResources().getColor(android.R.color.transparent));
        spannableStringBuilder.setSpan(clickableSpan, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv.setText(spannableStringBuilder);
    }

    /**
     *   设置tv 的背景色
     * @param tv                        TextView  控件
     * @param spannableStringBuilder     显示的文字
     * @param start                         开始位置
     * @param end                           结束位置
     * @param colorString                   设置字体颜色
     */

    public static  void  setTextBackGround(TextView  tv,SpannableStringBuilder spannableStringBuilder,int  start,int  end,String colorString){
        //设置部分文字颜色
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(Color.parseColor(colorString==null||colorString.isEmpty()?defaultColorString:colorString));
        spannableStringBuilder.setSpan(foregroundColorSpan, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        //配置给TextView
        tv.setMovementMethod(LinkMovementMethod.getInstance());
        tv.setText(spannableStringBuilder);
    }

    public interface TextClickListener{
        abstract void  onClick(View  view,CharSequence  charSequence);
    }
}
