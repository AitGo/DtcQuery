package com.xtool.dtcquery.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xtool.dtcquery.R;
import com.xtool.dtcquery.base.MyBaseAdapter;
import com.xtool.dtcquery.entity.DtcDTO;

import java.util.List;

/**
 * Created by xtool on 2017/9/2.
 */

public class DtcListAdapter extends MyBaseAdapter {
    private LayoutInflater mLayoutInflater;
    private List<DtcDTO> dtcDTOList;

    public DtcListAdapter(Context context,List<DtcDTO> dtcDTOList) {
        super(dtcDTOList);
        this.dtcDTOList = dtcDTOList;
        mLayoutInflater = LayoutInflater.from(context);
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
        viewHolder.m_dcode.setText(dtcDTOList.get(position).getDcode());
        viewHolder.m_dname.setText(dtcDTOList.get(position).getDname());
        viewHolder.m_dinfo.setText(dtcDTOList.get(position).getDinfo());
        viewHolder.m_dcause.setText(dtcDTOList.get(position).getDcause());
        viewHolder.m_dfix.setText(dtcDTOList.get(position).getDfix());

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
