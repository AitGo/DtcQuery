package com.xtool.dtcquery.mvp.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.xtool.dtcquery.R;
import com.xtool.dtcquery.base.BaseFragment;

/**
 * Created by xtool on 2018/1/5.
 */

public class MessageFragment extends BaseFragment {

    private RecyclerView messageList;

    @Override
    protected View initView(LayoutInflater inflater) {
        View inflate = inflater.inflate(R.layout.fragment_message, null);
        messageList = (RecyclerView) inflate.findViewById(R.id.recycler_message);
        return null;
    }
}
