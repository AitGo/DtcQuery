package com.xtool.dtcquery.mvp.view;

import android.support.v4.app.Fragment;

import com.xtool.dtcquery.entity.DtcDTO;

import java.util.List;

/**
 * Created by xtool on 2017/9/7.
 */

public interface MainView {

    void showProgressDialog();

    void dismissProgressDialog();

    void showListMeg(List<DtcDTO> dtcDTOs);

    void showListMoreMeg(List<DtcDTO> dtcDTOs);

    String getDcode();

    void dismissListTitle();

    void showListTitle();

    void showToast(String msg);

    void showDrawer();

    void dismissDrawer();

    void switchFragment(Fragment fragment);

}
