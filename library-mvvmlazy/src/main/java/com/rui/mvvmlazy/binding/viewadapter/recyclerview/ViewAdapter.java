package com.rui.mvvmlazy.binding.viewadapter.recyclerview;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.rui.mvvmlazy.binding.command.BindingCommand;

/**
 * Created by 赵继瑞 on 2020/3/24
 */
public class ViewAdapter {
    //绑定adapter
    @BindingAdapter("bindAdapter")
    public static void bindAdapter(RecyclerView recyclerView, RecyclerView.Adapter adapter) {
        if (adapter!=null)
        recyclerView.setAdapter(adapter);
    }

    //设置分割线
    @BindingAdapter("lineManager")
    public static void lineManager(RecyclerView recyclerView, LineManagers.LineManagerFactory lineManagerFactory) {
        if (lineManagerFactory!=null)
        lineManagerFactory.create(recyclerView);
    }

    //设置布局管理器
    @BindingAdapter("layoutManager")
    public static void layoutManager(RecyclerView recyclerView, LayoutManagers.LayoutManagerFactory layoutManagerFactory) {
        if (layoutManagerFactory!=null)
        recyclerView.setLayoutManager(layoutManagerFactory.create(recyclerView));
    }

    //设置滑动监听
    @BindingAdapter(value = {"onScrollChangeCommand", "onScrollStateChangedCommand"}, requireAll = false)
    public static void onScrollChangeCommand(final RecyclerView recyclerView,
                                             final BindingCommand<ScrollDataWrapper> onScrollChangeCommand,
                                             final BindingCommand<Integer> onScrollStateChangedCommand) {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            private int state;

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (onScrollChangeCommand != null) {
                    onScrollChangeCommand.execute(new ScrollDataWrapper(dx, dy, state));
                }
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                state = newState;
                if (onScrollStateChangedCommand != null) {
                    onScrollStateChangedCommand.execute(newState);
                }
            }
        });
    }

    @BindingAdapter("itemAnimator")
    public static void setItemAnimator(RecyclerView recyclerView, RecyclerView.ItemAnimator animator) {
        if (animator!=null)
        recyclerView.setItemAnimator(animator);
    }

    //设置滑动禁止,开启
    @BindingAdapter("setNestedScrollingEnabled")
    public static void setNestedScrollingEnabled(RecyclerView recyclerView, Boolean aBoolean) {
        if (aBoolean!=null)
        recyclerView.setNestedScrollingEnabled(aBoolean);
    }


    public static class ScrollDataWrapper {
        public float scrollX;
        public float scrollY;
        public int state;

        public ScrollDataWrapper(float scrollX, float scrollY, int state) {
            this.scrollX = scrollX;
            this.scrollY = scrollY;
            this.state = state;
        }
    }
}
