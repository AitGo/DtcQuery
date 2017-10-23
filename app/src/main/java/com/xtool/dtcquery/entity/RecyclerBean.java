package com.xtool.dtcquery.entity;

/**
 * Created by xtool on 2017/10/23.
 */

public class RecyclerBean {

    public static final int PARENT_ITEM = 0;
    public static final int CHILD_ITEM = 1;
    public static final int FOOT_ITEM = 2;

    private boolean hasMore = false;
    private boolean fadeTips = false;

    private int type;
    private boolean isExpand;

    private RecyclerBean childBean;
    private String id;

    private String dcode;
    private String dname;
    private String dinfo;
    private String dcause;
    private String dfix;

    public boolean isHasMore() {
        return hasMore;
    }

    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }

    public boolean isFadeTips() {
        return fadeTips;
    }

    public void setFadeTips(boolean fadeTips) {
        this.fadeTips = fadeTips;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isExpand() {
        return isExpand;
    }

    public void setExpand(boolean expand) {
        isExpand = expand;
    }

    public RecyclerBean getChildBean() {
        return childBean;
    }

    public void setChildBean(RecyclerBean childBean) {
        this.childBean = childBean;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getDinfo() {
        return dinfo;
    }

    public void setDinfo(String dinfo) {
        this.dinfo = dinfo;
    }

    public String getDcause() {
        return dcause;
    }

    public void setDcause(String dcause) {
        this.dcause = dcause;
    }

    public String getDfix() {
        return dfix;
    }

    public void setDfix(String dfix) {
        this.dfix = dfix;
    }
}
