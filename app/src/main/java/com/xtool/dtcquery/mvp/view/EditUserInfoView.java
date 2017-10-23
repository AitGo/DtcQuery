package com.xtool.dtcquery.mvp.view;

import android.support.v4.app.Fragment;

import com.xtool.dtcquery.entity.CarDTO;
import com.xtool.dtcquery.entity.UserDTO;

/**
 * Created by xtool on 2017/10/12.
 */

public interface EditUserInfoView {

    CarDTO getCarDTO();

    UserDTO getUserDTO();

    void showProgressDialog();

    void dismissProgressDialog();

    void showToast(String msg);

    void switchFragment(Fragment fragment);
}
