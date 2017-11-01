package com.xtool.dtcquery.base;

import android.support.v4.app.Fragment;

/**
 * Created by xtool on 2017/11/1.
 */

public interface BaseFragmentView {

    void showProgressDialog();

    void dismissProgressDialog();

    void showToast(String msg);

    void switchFragment(Fragment fragment);
}
