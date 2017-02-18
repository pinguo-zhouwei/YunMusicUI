package com.example.zhouwei.yunmusicui;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private Toolbar mToolbar;
    private ImageView mHeaderBg;
    private RecyclerView mRecyclerView;
    private MyAdapter mMyAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_layout);
         initView();
    }

    private void initView(){
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setBackgroundColor(Color.TRANSPARENT);
        mToolbar.inflateMenu(R.menu.toolbar_right_menu);
        mToolbar.setTitle(R.string.title);
        mToolbar.setTitleTextColor(Color.WHITE);

        mHeaderBg = (ImageView) findViewById(R.id.header_image);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mMyAdapter = new MyAdapter();
        mMyAdapter.setData(getData());
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mMyAdapter);
        // toolbar 的高
        int toolbarHeight = mToolbar.getLayoutParams().height;
        Log.i(TAG,"toolbar height:"+toolbarHeight);
        final int headerBgHeight = toolbarHeight + getStatusBarHeight(this);
        Log.i(TAG,"headerBgHeight:"+headerBgHeight);
        ViewGroup.LayoutParams params =  mHeaderBg.getLayoutParams();
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        params.height = headerBgHeight;

        mHeaderBg.setImageAlpha(0);

        StatusBarUtils.setTranslucentImageHeader(this,0,mToolbar);

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                View headerView = null;

                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int firstVisibleItem = manager.findFirstVisibleItemPosition();
                if(firstVisibleItem == 0){
                    headerView = recyclerView.getChildAt(firstVisibleItem);
                }
                float alpha = 0;
                if(headerView == null){
                    alpha = 1;// 如果headerView 为null ,说明已经到达header滑动的最大高度了
                }else{
                    alpha = Math.abs(headerView.getTop()) * 1.0f / headerView.getHeight();
                    Log.i(TAG,"alpha:"+alpha + "top :"+headerView.getTop() + " height: "+headerView.getHeight());
                }

                Drawable drawable = mHeaderBg.getDrawable();
                if(drawable!=null){
                    drawable.mutate().setAlpha((int) (alpha * 255));
                    mHeaderBg.setImageDrawable(drawable);
                }
            }
        });
    }


    public List<String> getData(){
        List<String> list = new ArrayList<>();
        for(int i=0;i<100;i++){
            list.add("item :"+i);
        }
        return list;
    }

    private static int getStatusBarHeight(Context context) {
        // 获得状态栏高度
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        return context.getResources().getDimensionPixelSize(resourceId);
    }

}
