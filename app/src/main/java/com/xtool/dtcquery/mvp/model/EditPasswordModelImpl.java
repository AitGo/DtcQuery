package com.xtool.dtcquery.mvp.model;

import com.xtool.dtcquery.entity.UserDTO;
import com.xtool.dtcquery.http.PostActivation;
import com.xtool.dtcquery.http.ServiceFactory;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by xtool on 2017/10/9.
 */

public class EditPasswordModelImpl implements EditPasswordModel {
    @Override
    public Observable<List<UserDTO>> editPasswordByPost(UserDTO userDTO) {
        return ServiceFactory.getInstance().createService(PostActivation.class)
                .postEditPassword(userDTO);
    }
}
