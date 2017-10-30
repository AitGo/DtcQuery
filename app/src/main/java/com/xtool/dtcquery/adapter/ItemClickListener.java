package com.xtool.dtcquery.adapter;


import com.xtool.dtcquery.entity.RecyclerBean;

/**
 *
 * 父布局Item点击监听接口
 */

public interface ItemClickListener {
    /**
     * 展开子Item
     * @param bean
     */
    void onExpandChildren(RecyclerBean bean);

    /**
     * 隐藏子Item
     * @param bean
     */
    void onHideChildren(RecyclerBean bean);
}
