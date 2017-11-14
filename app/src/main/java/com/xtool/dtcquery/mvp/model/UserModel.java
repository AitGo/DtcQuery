package com.xtool.dtcquery.mvp.model;

import android.content.Context;

import com.xtool.dtcquery.base.BaseModel;
import com.xtool.dtcquery.entity.UserDTO;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by xtool on 2017/10/11.
 */

public interface UserModel extends BaseModel{

    Observable<List<UserDTO>> userLogoutByPost(UserDTO userDTO);
}
