package com.xtool.dtcquery.mvp.model;

import com.xtool.dtcquery.entity.UserDTO;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by xtool on 2017/9/14.
 */

public interface LoginModel {
    Observable<List<UserDTO>> GetUserCustomByPost(UserDTO userDTO);
}
