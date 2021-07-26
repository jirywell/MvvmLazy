package com.rui.base.utils;

import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.rui.base.R;
import com.rui.base.entity.BannerInfo;
import com.youth.banner.adapter.BannerAdapter;

import java.util.List;

/**
 * 自定义布局，图片
 */
public class ImageAdapter extends BannerAdapter<BannerInfo, ImageHolder> {

    public ImageAdapter(List<BannerInfo> mDatas) {
        //设置数据，也可以调用banner提供的方法,或者自己在adapter中实现
        super(mDatas);
    }

    //更新数据
    public void updateData(List<BannerInfo> data) {
        //这里的代码自己发挥，比如如下的写法等等
        mDatas.addAll(data);
        notifyDataSetChanged();
    }


    //创建ViewHolder，可以用viewType这个字段来区分不同的ViewHolder
    @Override
    public ImageHolder onCreateHolder(ViewGroup parent, int viewType) {
        ImageView imageView = new ImageView(parent.getContext());
        //注意，必须设置为match_parent，这个是viewpager2强制要求的
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        imageView.setLayoutParams(params);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        return new ImageHolder(imageView);
    }

    @Override
    public void onBindView(ImageHolder holder, BannerInfo data, int position, int size) {
        ViewGroup.LayoutParams params = holder.imageView.getLayoutParams();
//        val width=ScreenUtil.getScreenWidth(context)-ScreenUtil.dip2px(context,20f)
//        val height= BigDecimal(width.toString()).divide(BigDecimal("2.09"),2, RoundingMode.HALF_UP).toInt()
        Glide.with(holder.imageView.getContext()).asDrawable().load(Constant.IMAGE_URL + data.bannerPath).placeholder(R.drawable.ic_def_loading).apply(
                RequestOptions.bitmapTransform(
                        new RoundedCorners(10)
                ).override(params.width, params.height))
                .into(holder.imageView);
    }

}
