package com.xtool.dtcquery.mvp.persenter;

import com.xtool.dtcquery.base.BaseFragment;

/**
 * Created by xtool on 2017/9/7.
 */

public interface MainPersenter {

    void query(Integer s,Integer ps);

    int getPAGE_COUNT();

    void loadMore(int s ,int ps);

    void showDrawer();

    void initSMS();

    void unregristSMS();

    BaseFragment initLeftFragment();

    boolean checkLogin();
}
