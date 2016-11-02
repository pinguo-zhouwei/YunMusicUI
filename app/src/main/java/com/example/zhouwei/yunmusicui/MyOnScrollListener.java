package com.example.zhouwei.yunmusicui;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by zhouwei on 16/11/1.
 */

public class MyOnScrollListener extends RecyclerView.OnScrollListener {
    private View mSrcView;
    private AlphaUpdateCallback mUpdateCallback;

    public MyOnScrollListener(View view,AlphaUpdateCallback callback) {
        mSrcView = view;
        mUpdateCallback = callback;
    }
    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
    }


    public interface AlphaUpdateCallback{
        public void onAlphaChanged(float alpha);
    }
}
