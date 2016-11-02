package com.example.zhouwei.yunmusicui;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by zhouwei on 16/10/31.
 */

public class MyAdapter extends RecyclerView.Adapter {
    public static final int TYPE_HEADER = 0;
    public static final  int TYPE_CONTENT = 1;
    private List<String> mData;
    private ImageView mHeaderView;
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == TYPE_CONTENT){
            return  new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,null));
        }else{
            return new HeaderViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.header_layout,null));
        }
    }

    public ImageView getHeaderView() {
        return mHeaderView;
    }

    public void setData(List<String> data) {
        mData = data;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
       if(getItemViewType(position) == TYPE_CONTENT){
           MyViewHolder myViewHolder = (MyViewHolder) holder;
           myViewHolder.mTextView.setText(mData.get(position - 1));
       }else{
           Log.i("zhouwei","初始化。。。。。");
           mHeaderView = ((HeaderViewHolder)holder).headerImage;
       }
    }

    @Override
    public int getItemCount() {
        return mData == null ? 1 : 1 + mData.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? TYPE_HEADER : TYPE_CONTENT;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView mTextView;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.item_name);
        }
    }

    public static class HeaderViewHolder extends RecyclerView.ViewHolder{
        public ImageView headerImage;
        public HeaderViewHolder(View itemView) {
            super(itemView);
            headerImage = (ImageView) itemView.findViewById(R.id.header_image);
        }
    }
}
