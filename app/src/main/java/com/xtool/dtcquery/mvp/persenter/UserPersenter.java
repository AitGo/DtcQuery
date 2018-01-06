package com.xtool.dtcquery.mvp.persenter;

import com.xtool.dtcquery.entity.UserDTO;

/**
 * Created by xtool on 2017/10/9.
 */

public interface UserPersenter {

    void logout();

    void switchToEditPassword();

    void switchToEditUserInfo();

    void initData();

    UserDTO getUserDTO(UserDTO userDTO);
}
