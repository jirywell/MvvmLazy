package com.rui.mvvmlazy.binding.viewadapter.image;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;

/**
 * Created by 赵继瑞 on 2020/6/18.
 */
public final class ViewAdapter {


    /**
     * 加载普通图片
     *
     * @param imageView
     * @param url
     * @param placeholderRes
     */
    @BindingAdapter(value = {"bindImgUrl", "placeholderRes", "centerCrop"}, requireAll = false)
    public static void bindImgUrl(ImageView imageView, String url, Integer placeholderRes, Boolean centerCrop) {
        //使用Glide框架加载图片
        if (centerCrop == null || centerCrop) {
            Glide.with(imageView.getContext()).asDrawable()
                    .load(url).centerCrop()
                    .apply(new RequestOptions().placeholder(placeholderRes == null ? 0 : placeholderRes))
                    .into(imageView);
        } else {
            Glide.with(imageView.getContext()).asDrawable()
                    .load(url)
                    .apply(new RequestOptions().placeholder(placeholderRes == null ? 0 : placeholderRes))
                    .into(imageView);
        }
    }


    /**
     * 加载圆形图片
     *
     * @param imageView
     * @param url
     * @param placeholderRes
     */
    @BindingAdapter(value = {"bindCircleImgUrl", "placeholderRes"}, requireAll = false)
    public static void bindCircleImgUrl(ImageView imageView, String url, Integer placeholderRes) {
        Glide.with(imageView.getContext()).asDrawable().load(url).placeholder(placeholderRes == null ? 0 : placeholderRes).skipMemoryCache(false)
                .dontAnimate().centerCrop().diskCacheStrategy(DiskCacheStrategy.ALL)
                .apply(RequestOptions.bitmapTransform(new CircleCrop())).into(imageView);
    }


    /**
     * 加载圆角图片
     *
     * @param imageView
     * @param url
     * @param placeholderRes
     */
    @BindingAdapter(value = {"bindCornersImgUrl", "placeholderRes", "bindCorners"}, requireAll = false)
    public static void bindCornersImgUrl(ImageView imageView, String url, Integer placeholderRes, Integer corners) {
        Glide.with(imageView.getContext()).asDrawable().load(url).placeholder(placeholderRes == null ? 0 : placeholderRes)
                .apply(RequestOptions.bitmapTransform(new GlideRoundTransform(imageView.getContext(), corners == null ? 10 : corners))).skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);
    }

}

