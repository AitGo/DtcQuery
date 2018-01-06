package com.xtool.dtcquery.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.xtool.dtcquery.R;
import com.xtool.dtcquery.entity.Category;
import com.xtool.dtcquery.entity.DtcDTO;
import com.xtool.dtcquery.entity.SubCategory;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xtool on 2017/10/30.
 */

public class BrvahDtcRecyclerAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity,BaseViewHolder> {

    public static final int TYPE_CATEGORY = 0;
    public static final int TYPE_SUBCATEGORY = 1;
    private Animation operatingAnim;

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public BrvahDtcRecyclerAdapter(List<MultiItemEntity> data) {
        super(data);
        addItemType(TYPE_CATEGORY,R.layout.item_parent);
        addItemType(TYPE_SUBCATEGORY,R.layout.item_child);
    }

    @Override
    protected void convert(final BaseViewHolder helper, MultiItemEntity item) {
        switch (helper.getItemViewType()) {
            case TYPE_CATEGORY:
                final Category category = (Category) item;
                helper.setText(R.id.tv_dcode,category.getDcode())
                        .setText(R.id.tv_dname,category.getDname());
                helper.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = helper.getLayoutPosition();
                        if(category.isExpanded()) {
                            collapse(pos);
//                            helper.getView(R.id.expend).setRotation(0);
                        }else {
                            expand(pos);
//                            helper.getView(R.id.expend).setRotation(90);
                        }
                    }
                });
                break;
            case TYPE_SUBCATEGORY:
                SubCategory subCategory = (SubCategory) item;
                helper.setText(R.id.tv_dinfo,subCategory.getDinfo())
                        .setText(R.id.tv_dcause,subCategory.getDcase())
                        .setText(R.id.tv_dfix,subCategory.getDfix());
                break;
        }
    }

    /**
     * DtcDTO转MultiItemEntity
     * @param dtcList
     * @return
     */
    public static List<MultiItemEntity> getMultiItemList(List<DtcDTO> dtcList) {
        ArrayList<MultiItemEntity> multiItemEntities = new ArrayList<>();
        for(int i = 0; i < dtcList.size(); i++) {
            Category category = new Category(dtcList.get(i).getDcode(),dtcList.get(i).getDname());
            SubCategory subCategory = new SubCategory(dtcList.get(i).getDinfo(),
                    dtcList.get(i).getDcause(),dtcList.get(i).getDfix());
            category.addSubItem(subCategory);
            multiItemEntities.add(category);
        }
        return multiItemEntities;
    }
}
