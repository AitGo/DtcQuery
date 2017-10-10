package com.xtool.dtcquery.mvp.view;

import android.support.v4.app.Fragment;

/**
 * Created by xtool on 2017/10/9.
 */

public interface EditPasswordView {

    String getNewPassword();

    String getNewPassword2();

    String getUserName();

    void setNewPassword(String s);

    void setNewPassword2(String s);

    void showProgressDialog();

    void dismissProgressDialog();

    void showToast(String msg);

    void switchFragment(Fragment fragment);
}
