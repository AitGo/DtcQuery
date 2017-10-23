package com.xtool.dtcquery.mvp.model;

import com.xtool.dtcquery.entity.UserDTO;
import com.xtool.dtcquery.http.PostActivation;
import com.xtool.dtcquery.http.ServiceFactory;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by xtool on 2017/10/12.
 */

public class EditUserInfoModelImpl implements EditUserInfoModel {
    @Override
    public Observable<List<UserDTO>> editUserInfoByPost(UserDTO userDTO) {
        return ServiceFactory.getInstance().createService(PostActivation.class)
                .postEditUserInfo(userDTO);
    }
}
