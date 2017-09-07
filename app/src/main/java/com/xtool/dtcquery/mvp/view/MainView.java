package com.xtool.dtcquery.mvp.view;

import com.xtool.dtcquery.bean.DtcCustom;

import java.util.List;

/**
 * Created by xtool on 2017/9/7.
 */

public interface MainView {

    void showProgressDialog();

    void dismissProgressDialog();

    void showListMeg(List<DtcCustom> dtcCustoms);

    String getDcode();

    void dismissListTitle();

    void showListTitle();
}
