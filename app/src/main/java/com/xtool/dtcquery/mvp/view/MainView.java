package com.xtool.dtcquery.mvp.view;

import android.support.v4.app.Fragment;

import com.xtool.dtcquery.adapter.BrvahDtcRecyclerAdapter;
import com.xtool.dtcquery.adapter.DtcRecyclerAdapter;
import com.xtool.dtcquery.base.BaseView;
import com.xtool.dtcquery.entity.DtcDTO;

import java.util.List;

/**
 * Created by xtool on 2017/9/7.
 */

public interface MainView extends BaseView{

    void showListMeg(List<DtcDTO> dtcDTOs);

    void showListMoreMeg(List<DtcDTO> dtcDTOs);

    String getDcode();

    void showDrawer();

    void closeDrawer();

    void dismissDrawer();

    void switchFragment(Fragment fragment);

    void setLastVisibleItem(int item);

    int getLastVisibleItem();

    BrvahDtcRecyclerAdapter getRecyclerAdatper();

}
