package com.xtool.dtcquery.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.xtool.dtcquery.adapter.BrvahDtcRecyclerAdapter;

/**
 * Created by xtool on 2017/10/28.
 */

public class SubCategory implements MultiItemEntity {

    private String dinfo;
    private String dcase;
    private String dfix;

    public SubCategory(String dinfo,String dcase,String dfix) {
        this.dinfo = dinfo;
        this.dcase = dcase;
        this.dfix = dfix;
    }

    @Override
    public int getItemType() {
        return BrvahDtcRecyclerAdapter.TYPE_SUBCATEGORY;
    }

    public String getDinfo() {
        return dinfo;
    }

    public void setDinfo(String dinfo) {
        this.dinfo = dinfo;
    }

    public String getDcase() {
        return dcase;
    }

    public void setDcase(String dcase) {
        this.dcase = dcase;
    }

    public String getDfix() {
        return dfix;
    }

    public void setDfix(String dfix) {
        this.dfix = dfix;
    }
}
