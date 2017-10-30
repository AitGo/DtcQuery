package com.xtool.dtcquery.entity;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.xtool.dtcquery.adapter.BrvahDtcRecyclerAdapter;

/**
 * Created by xtool on 2017/10/28.
 */

public class Category extends AbstractExpandableItem<SubCategory> implements MultiItemEntity {

    private String dcode;
    private String dname;

    public Category(String dcode,String dname) {
        this.dcode = dcode;
        this.dname = dname;

    }

    @Override
    public int getItemType() {
        return BrvahDtcRecyclerAdapter.TYPE_CATEGORY;
    }

    @Override
    public int getLevel() {
        return 1;
    }

    public String getDcode() {
        return dcode;
    }

    public void setDcode(String dcode) {
        this.dcode = dcode;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }
}
