package com.xtool.dtcquery.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;

import com.xtool.dtcquery.R;

/**
 * Created by xtool on 2017/9/11.
 */

public class PullUpLoadMoreListView extends ListView implements AbsListView.OnScrollListener,View.OnClickListener{

    public static int PAGESIZE = 3;
    private View view;
    private TextView tv_load;
    int totalItemCount;
    int lastVisibieItem;
    boolean isLoading = false;
    IloadListener iLoadListener;

    public PullUpLoadMoreListView(Context context) {
        super(context);
        initView(context);
    }

    public PullUpLoadMoreListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public PullUpLoadMoreListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public void initView(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.item_listfoot, null);
        tv_load = (TextView) view.findViewById(R.id.tv_load);
        tv_load.setVisibility(View.GONE);
        tv_load.setOnClickListener(this);
        this.addFooterView(view);
        this.setOnScrollListener(this);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if(totalItemCount == lastVisibieItem && scrollState == SCROLL_STATE_IDLE) {
            if(!isLoading) {
                isLoading = true;
                tv_load.setVisibility(View.VISIBLE);

            }
        }

    }

    public int getTotalItemCount() {
        return totalItemCount;
    }

    public void setTotalItemCount(int totalItemCount) {
        this.totalItemCount = totalItemCount;
    }

    public int getLastVisibieItem() {
        return lastVisibieItem;
    }

    public void setLastVisibieItem(int lastVisibieItem) {
        this.lastVisibieItem = lastVisibieItem;
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        this.lastVisibieItem = firstVisibleItem + visibleItemCount;
        this.totalItemCount = totalItemCount;
    }

    public void setInterface(IloadListener iLoadListener) {
        this.iLoadListener = iLoadListener;
    }

    @Override
    public void onClick(View v) {
        iLoadListener.onLoad();
    }

    // 加载更多数据的回调接口
    public interface IloadListener {
        public void onLoad();
    }

    // 加载完成通知隐藏
    public void loadComplete() {
        isLoading = false;
        tv_load.setVisibility(View.GONE);

    }
}
