package com.xtool.dtcquery.mvp.view;

import android.support.v4.app.Fragment;

import com.xtool.dtcquery.entity.UserDTO;

/**
 * Created by xtool on 2017/9/20.
 */

public interface UserView {

    void switchFragment(Fragment fragment);

    UserDTO getUser();
}
