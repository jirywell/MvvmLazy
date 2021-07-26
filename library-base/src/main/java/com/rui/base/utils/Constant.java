package com.rui.base.utils;

import android.os.Environment;

/**
 * *******************************
 * *@Author
 * *date ：
 * *description:常量类
 * *******************************
 */
public class Constant {
    public static String LOGIN_BY_ACCOUNT_AUTH = "Basic QVBQX1pCWlM6Ymxz";

    /*手机正则表达式*/
    public static final String TELRegex = "[1]\\d{10}";//"[1]"代表第1位为数字1，"\\d{12}"代表后面是可以是0～9的数字，有10位0
    // SDCard路径
    public static final String SD_PATH = Environment.getExternalStorageDirectory().getAbsolutePath();
    //当前获得编码的秒
    public static int CURRENT_TIME = 0;
    public static final String baseUrl = "https://api.apiopen.top";
    public static final String IMAGE_URL = "https://ufileuat.niceloo.com/api/file/download?action=view&path=";
    public static final String CTYPE = "D.ADMIN.WEB";
}
