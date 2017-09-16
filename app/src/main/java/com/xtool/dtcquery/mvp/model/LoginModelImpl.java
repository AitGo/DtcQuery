package com.xtool.dtcquery.mvp.model;

import com.xtool.dtcquery.entity.UserDTO;
import com.xtool.dtcquery.http.PostActivation;
import com.xtool.dtcquery.http.ServiceFactory;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by xtool on 2017/9/14.
 */

public class LoginModelImpl implements LoginModel {

    @Override
    public Observable<List<UserDTO>> GetUserCustomByPost(UserDTO userDTO) {
        return ServiceFactory.getInstance().createService(PostActivation.class)
                .postUserLogin(userDTO);
    }
}
