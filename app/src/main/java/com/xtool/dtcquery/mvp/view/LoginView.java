package com.xtool.dtcquery.mvp.view;

import android.support.v4.app.Fragment;

import com.xtool.dtcquery.base.BaseFragmentView;
import com.xtool.dtcquery.entity.UserDTO;

/**
 * Created by xtool on 2017/9/14.
 */

public interface LoginView extends BaseFragmentView{
    String getUname();

    String getUpassword();

    void switchUserFragment(UserDTO userDTO);

    void closeDrawer();

}
