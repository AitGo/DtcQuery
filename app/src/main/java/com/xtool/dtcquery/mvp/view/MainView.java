package com.xtool.dtcquery.mvp.view;

import android.support.v4.app.Fragment;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.xtool.dtcquery.adapter.BrvahDtcRecyclerAdapter;
import com.xtool.dtcquery.adapter.DtcRecyclerAdapter;
import com.xtool.dtcquery.base.BaseView;
import com.xtool.dtcquery.entity.DtcDTO;

import java.util.List;

/**
 * Created by xtool on 2017/9/7.
 */

public interface MainView extends BaseView{

    String getDcode();

    void showDrawer();

    void closeDrawer();

    void switchFragment(Fragment fragment);

    BrvahDtcRecyclerAdapter getRecyclerAdatper();

    List<MultiItemEntity> getDtcList();

}
