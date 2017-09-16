package com.xtool.dtcquery.base;

/**
 * Created by xtool on 2017/9/13.
 */

public interface BaseView {
    void showProgressDialog();

    void dismissProgressDialog();

    void showToast(String msg);
}
