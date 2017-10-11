package com.xtool.dtcquery.mvp.persenter;

import android.content.Context;

import com.xtool.dtcquery.mvp.model.LoginModelImpl;
import com.xtool.dtcquery.mvp.model.LogoutModel;
import com.xtool.dtcquery.mvp.model.LogoutModelImpl;
import com.xtool.dtcquery.mvp.view.EditPasswordFragment;
import com.xtool.dtcquery.mvp.view.LoginFragment;
import com.xtool.dtcquery.mvp.view.LoginView;
import com.xtool.dtcquery.mvp.view.UserView;

/**
 * Created by xtool on 2017/10/9.
 */

public class UserPersenterImpl implements UserPersenter {

    private UserView view;
    private Context context;
    private LogoutModel logoutModel;


    public UserPersenterImpl(Context context, UserView view) {
        this.view = view;
        this.context = context;
        logoutModel = new LogoutModelImpl();
    }

    @Override
    public void logout() {
        //访问接口，改变登录状态

        //成功后切换界面
        view.switchFragment(new LoginFragment());


    }

    @Override
    public void switchToEditPassword() {
        view.switchFragment(new EditPasswordFragment(view.getUser()));
    }
}
