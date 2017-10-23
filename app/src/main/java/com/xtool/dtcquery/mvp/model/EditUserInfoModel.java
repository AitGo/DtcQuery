package com.xtool.dtcquery.mvp.model;

import com.xtool.dtcquery.entity.UserDTO;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by xtool on 2017/10/12.
 */

public interface EditUserInfoModel {
    Observable<List<UserDTO>> editUserInfoByPost(UserDTO userDTO);
}
