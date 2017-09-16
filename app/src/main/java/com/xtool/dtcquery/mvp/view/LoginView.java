package com.xtool.dtcquery.mvp.view;

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

    void switchUserFragment();

    void switchUserFragment(UserDTO userDTO);

}
