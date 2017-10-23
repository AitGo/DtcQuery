package com.xtool.dtcquery.mvp.model;

import com.xtool.dtcquery.entity.UserDTO;
import com.xtool.dtcquery.http.PostActivation;
import com.xtool.dtcquery.http.ServiceFactory;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by xtool on 2017/10/11.
 */

public class LogoutModelImpl implements LogoutModel {
    @Override
    public Observable<List<UserDTO>> userLogoutByPost(UserDTO userDTO) {
        return ServiceFactory.getInstance().createService(PostActivation.class)
                .postUserLogout(userDTO);
    }
}
