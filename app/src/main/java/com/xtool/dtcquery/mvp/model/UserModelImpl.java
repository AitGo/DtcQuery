package com.xtool.dtcquery.mvp.model;

import android.content.Context;

import com.xtool.dtcquery.base.BaseModelImpl;
import com.xtool.dtcquery.entity.UserDTO;
import com.xtool.dtcquery.http.PostActivation;
import com.xtool.dtcquery.http.ServiceFactory;
import com.xtool.dtcquery.utils.SPUtils;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by xtool on 2017/10/11.
 */

public class UserModelImpl extends BaseModelImpl implements UserModel {
    @Override
    public Observable<List<UserDTO>> userLogoutByPost(UserDTO userDTO) {
        return ServiceFactory.getInstance().createService(PostActivation.class)
                .postUserLogout(userDTO);
    }

}
