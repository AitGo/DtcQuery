package com.xtool.dtcquery.mvp.persenter;

import android.content.Context;

import com.xtool.dtcquery.mvp.model.LoginModelImpl;
import com.xtool.dtcquery.mvp.view.LoginFragment;
import com.xtool.dtcquery.mvp.view.LoginView;
import com.xtool.dtcquery.mvp.view.UserView;

/**
 * Created by xtool on 2017/10/9.
 */

public class UserPersenterImpl implements UserPersenter {

    private UserView view;
    private Context context;


    public UserPersenterImpl(Context context, UserView view) {
        this.view = view;
        this.context = context;
    }

    @Override
    public void logout() {
        view.switchFragment(new LoginFragment());
    }
}
