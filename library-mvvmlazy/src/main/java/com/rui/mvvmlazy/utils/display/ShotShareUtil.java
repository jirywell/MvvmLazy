package com.rui.mvvmlazy.utils.display;

import android.graphics.Bitmap;
import android.os.Environment;
import android.view.View;

import com.rui.mvvmlazy.utils.common.KLog;
import com.rui.mvvmlazy.utils.common.ToastUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * *******************************
 * *@Author  赵继瑞
 * *date ：2020/3/10 11:59
 * *description:截屏
 * *******************************
 */
public class ShotShareUtil {

    /**
     * 获取截屏
     **/
    public static String viewShot(View view) {
        String imagePath = null;
        int width = view.getWidth();
        int height = view.getHeight();
        //设置缓存
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bmp = view.getDrawingCache();
        if (bmp != null) {
            bmp = Bitmap.createBitmap(bmp, 0, 0, width, height);
            try {
                // 图片文件路径
                imagePath = Environment.getExternalStorageDirectory().getAbsolutePath()+ "/" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "share.png";
                KLog.e("====imagePath====" + imagePath);
                File file = new File(imagePath);
                FileOutputStream os = new FileOutputStream(file);
                bmp.compress(Bitmap.CompressFormat.PNG, 100, os);
                os.flush();
                os.close();
                ToastUtils.showShort("海报保存至" + imagePath);
                return imagePath;
            } catch (Exception e) {
                KLog.e("====screenshot:error====" + e.getMessage());
            }
        }
        return null;
    }


}

