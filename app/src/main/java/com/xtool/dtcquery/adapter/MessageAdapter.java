package com.xtool.dtcquery.adapter;

import android.os.AsyncTask;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xtool.dtcquery.R;

import java.util.List;

/**
 * Created by xtool on 2018/1/5.
 */

public class MessageAdapter extends BaseItemDraggableAdapter<String, BaseViewHolder> {


    public MessageAdapter(List<String> data) {
        super(R.layout.item_message,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }
}
