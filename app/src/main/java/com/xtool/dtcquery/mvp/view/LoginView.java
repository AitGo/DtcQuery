package com.xtool.dtcquery.mvp.view;

import android.support.v4.app.Fragment;

import com.xtool.dtcquery.entity.UserDTO;

/**
 * Created by xtool on 2017/9/14.
 */

public interface LoginView {
    String getUname();

    String getUpassword();

    void showProgressDialog();

    void dismissProgressDialog();

    void showToast(String msg);

    void switchFragment(Fragment fragment);

    void switchUserFragment(UserDTO userDTO);

}
