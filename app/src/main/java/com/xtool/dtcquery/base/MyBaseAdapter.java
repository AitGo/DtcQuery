package com.xtool.dtcquery.base;

import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by xtool on 2017/9/7.
 */

public abstract class MyBaseAdapter extends BaseAdapter {
    private List list;

    public MyBaseAdapter(List list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
