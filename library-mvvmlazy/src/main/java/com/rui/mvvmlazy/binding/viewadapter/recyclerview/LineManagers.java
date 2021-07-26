package com.rui.mvvmlazy.binding.viewadapter.recyclerview;

import android.graphics.Color;
import android.util.TypedValue;

import androidx.recyclerview.widget.RecyclerView;

import com.fondesa.recyclerviewdivider.DividerDecoration;
import com.fondesa.recyclerviewdivider.StaggeredDividerDecoration;

/**
 * Created by zjr on 2020/6/16.
 */
public class LineManagers {
    protected LineManagers() {
    }

    public interface LineManagerFactory {
        void create(RecyclerView recyclerView);
    }

    //瀑布流分割线
    public static LineManagerFactory staggeredDivider() {
        return staggeredDivider(Color.parseColor("#E6E6E6"), 1);
    }

    //瀑布流分割线
    public static LineManagerFactory staggeredDivider(int color, int size) {
        return new LineManagerFactory() {
            @Override
            public void create(RecyclerView recyclerView) {
                StaggeredDividerDecoration.builder(recyclerView.getContext())
                        .color(color)
                        .size(size, TypedValue.COMPLEX_UNIT_DIP)
                        .hideSideDividers()
                        .build()
                        .addTo(recyclerView);

            }
        };
    }

    //线型分割线 ,Grid分割线
    public static LineManagerFactory divider() {

        return divider(Color.parseColor("#E6E6E6"), 1);
    }

    //线型分割线 ,Grid分割线
    public static LineManagerFactory divider(int color, int size) {
        return new LineManagerFactory() {
            @Override
            public void create(RecyclerView recyclerView) {
                DividerDecoration.builder(recyclerView.getContext())
                        .color(color)
                        .size(size, TypedValue.COMPLEX_UNIT_DIP)
//                        .showFirstDivider()
//                        .showLastDivider()
//                        .showSideDividers()
                        .build()
                        .addTo(recyclerView);
            }
        };
    }
}
