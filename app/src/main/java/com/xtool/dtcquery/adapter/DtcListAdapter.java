package com.xtool.dtcquery.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xtool.dtcquery.R;
import com.xtool.dtcquery.bean.DtcCustom;

import java.util.List;

/**
 * Created by xtool on 2017/9/2.
 */

public class DtcListAdapter extends BaseAdapter {
    private LayoutInflater mLayoutInflater;
    private Context context;
    private List<DtcCustom> dtcCustomList;

    public DtcListAdapter(Context context,List<DtcCustom> dtcCustomList) {
        this.context = context;
        this.dtcCustomList = dtcCustomList;
        mLayoutInflater = LayoutInflater.from(context);
    }

    public void setDtcCustomList(List<DtcCustom> dtcCustomList) {
        this.dtcCustomList = dtcCustomList;
    }

    @Override
    public int getCount() {
        return dtcCustomList.size();
    }

    @Override
    public Object getItem(int position) {
        return dtcCustomList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DtcListViewHolder viewHolder = null;

        if(convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.item_dtc,null);
            viewHolder = new DtcListViewHolder();
            viewHolder.m_dcode = (TextView) convertView.findViewById(R.id.tv_dcode);
            viewHolder.m_dname = (TextView) convertView.findViewById(R.id.tv_dname);
            viewHolder.m_dinfo = (TextView) convertView.findViewById(R.id.tv_dinfo);
            viewHolder.m_dcause = (TextView) convertView.findViewById(R.id.tv_dcause);
            viewHolder.m_dfix = (TextView) convertView.findViewById(R.id.tv_dfix);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (DtcListViewHolder) convertView.getTag();
        }
        viewHolder.m_dcode.setText(dtcCustomList.get(position).getDcode());
        viewHolder.m_dname.setText(dtcCustomList.get(position).getDname());
        viewHolder.m_dinfo.setText(dtcCustomList.get(position).getDinfo());
        viewHolder.m_dcause.setText(dtcCustomList.get(position).getDcause());
        viewHolder.m_dfix.setText(dtcCustomList.get(position).getDfix());

        return convertView;
    }

    class DtcListViewHolder {
        public TextView m_dcode;
        public TextView m_dname;
        public TextView m_dinfo;
        public TextView m_dcause;
        public TextView m_dfix;
    }
}
