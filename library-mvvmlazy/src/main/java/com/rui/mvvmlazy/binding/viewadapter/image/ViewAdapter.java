package com.rui.mvvmlazy.binding.viewadapter.image;


import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.widget.ImageView;

import androidx.core.content.ContextCompat;
import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.rui.mvvmlazy.utils.common.ScreenUtil;


/**
 * Created by 赵继瑞 on 2020/6/18.
 */
public final class ViewAdapter {


    /**
     * 加载普通图片
     *
     * @param imageView      图片控件
     * @param url            图片链接  A file path, or a uri or url
     * @param placeholderRes 占位图
     * @param centerCrop     是否居中剪切,默认true
     */
    @BindingAdapter(value = {"bindImgUrl", "placeholderRes", "centerCrop"}, requireAll = false)
    public static void bindImgUrl(ImageView imageView, String url, Integer placeholderRes, Boolean centerCrop) {
        RequestBuilder<Drawable> requestBuilder = Glide.with(imageView.getContext()).asDrawable().load(url);
        if (centerCrop == null || centerCrop) {
            requestBuilder = requestBuilder.centerCrop();
        }
        requestBuilder.apply(new RequestOptions().placeholder(createDefPlaceHolder(imageView.getContext(), placeholderRes, 0)).override(imageView.getWidth(), imageView.getHeight()))
                .into(imageView);

    }


    /**
     * 加载圆形图片
     *
     * @param imageView      图片控件
     * @param url            图片链接  A file path, or a uri or url
     * @param placeholderRes 占位图
     */
    @BindingAdapter(value = {"bindCircleImgUrl", "placeholderRes"}, requireAll = false)
    public static void bindCircleImgUrl(ImageView imageView, String url, Integer placeholderRes) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(GradientDrawable.OVAL);
        gradientDrawable.setColor(Color.parseColor("#fff5f5f5"));
        Glide.with(imageView.getContext()).asDrawable().load(url).placeholder(placeholderRes == null ? gradientDrawable : ContextCompat.getDrawable(imageView.getContext(), placeholderRes)).skipMemoryCache(false).override(imageView.getWidth(), imageView.getHeight())
                .dontAnimate().centerCrop().diskCacheStrategy(DiskCacheStrategy.ALL)
                .apply(RequestOptions.bitmapTransform(new CircleCrop())).into(imageView);
    }

    /**
     * 加载圆角图片
     *
     * @param imageView      图片控件
     * @param url            图片链接  A file path, or a uri or url
     * @param placeholderRes 占位图
     * @param corners        圆角角度  Sp
     * @param centerCrop     是否居中剪切,默认true
     */
    @BindingAdapter(value = {"bindCornersImgUrl", "placeholderRes", "bindCorners", "centerCrop"}, requireAll = false)
    public static void bindCornersImgUrl(ImageView imageView, String url, Integer placeholderRes, Integer corners, Boolean centerCrop) {
        Glide.with(imageView.getContext()).asDrawable().load(url).placeholder(createDefPlaceHolder(imageView.getContext(), placeholderRes, corners == null ? 5f : corners)).override(imageView.getWidth(), imageView.getHeight())
                .apply(RequestOptions.bitmapTransform((centerCrop == null || centerCrop) ? new GlideRoundTransform(imageView.getContext(), corners == null ? 5 : corners)
                        : new RoundedCorners(ScreenUtil.dip2px(imageView.getContext(), corners == null ? 5f : corners)))).skipMemoryCache(false)
                .diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);

    }

    /**
     * 创建默认占位图
     *
     * @param context        上下文对象
     * @param placeholderRes 图片id
     * @return Drawable对象
     */
    public static Drawable createDefPlaceHolder(Context context, Integer placeholderRes, float radius) {
        if (placeholderRes != null)
            return ContextCompat.getDrawable(context, placeholderRes);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(GradientDrawable.RECTANGLE);
        gradientDrawable.setCornerRadius(ScreenUtil.dip2px(context, radius));
        gradientDrawable.setColor(Color.parseColor("#fff5f5f5"));
        return gradientDrawable;
    }

}


