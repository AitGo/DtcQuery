package com.xtool.dtcquery.mvp.model;

import com.xtool.dtcquery.entity.UserDTO;
import com.xtool.dtcquery.http.PostActivation;
import com.xtool.dtcquery.http.ServiceFactory;
import com.xtool.dtcquery.mvp.persenter.InsertUserPersenter;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by xtool on 2017/10/31.
 */

public class InsertUserModelImpl implements InsertUserModel {
    @Override
    public Observable<List<UserDTO>> postInsertUser(UserDTO userDTO) {
        return ServiceFactory.getInstance().createService(PostActivation.class)
                .postInsertUser(userDTO);
    }
}
