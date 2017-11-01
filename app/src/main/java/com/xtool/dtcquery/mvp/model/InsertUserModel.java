package com.xtool.dtcquery.mvp.model;

import com.xtool.dtcquery.entity.UserDTO;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by xtool on 2017/10/31.
 */

public interface InsertUserModel {
    Observable<List<UserDTO>> postInsertUser(UserDTO userDTO);
}
