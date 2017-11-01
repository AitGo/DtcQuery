package com.xtool.dtcquery.mvp.view;

import android.support.v4.app.Fragment;

import com.xtool.dtcquery.base.BaseFragmentView;
import com.xtool.dtcquery.entity.CarDTO;
import com.xtool.dtcquery.entity.UserDTO;

/**
 * Created by xtool on 2017/10/12.
 */

public interface EditUserInfoView extends BaseFragmentView{

    CarDTO getCarDTO();

    UserDTO getUserDTO();

}
