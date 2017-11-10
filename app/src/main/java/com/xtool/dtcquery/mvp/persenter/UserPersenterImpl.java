package com.xtool.dtcquery.mvp.persenter;

import android.content.Context;

import com.xtool.dtcquery.R;
import com.xtool.dtcquery.entity.UserDTO;
import com.xtool.dtcquery.mvp.model.LogoutModel;
import com.xtool.dtcquery.mvp.model.LogoutModelImpl;
import com.xtool.dtcquery.mvp.view.EditPasswordFragment;
import com.xtool.dtcquery.mvp.view.EditUserInfoFragment;
import com.xtool.dtcquery.mvp.view.LoginFragment;
import com.xtool.dtcquery.mvp.view.UserView;
import com.xtool.dtcquery.utils.SPUtils;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

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

    private UserDTO getUser() {
        UserDTO userDTO = new UserDTO();
        userDTO.setIslogin("logout");
        userDTO.setUname(view.getUser().getUname());
        return userDTO;
    }

    @Override
    public void logout() {
        view.showProgressDialog();
        //访问接口，改变登录状态
        logoutModel.userLogoutByPost(getUser())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<List<UserDTO>>() {
                    @Override
                    public void onNext(@NonNull List<UserDTO> list) {
                        //修改缓存
                        SPUtils.setParam(context, "uname", "");
                        SPUtils.setParam(context, "cname", "");
                        SPUtils.setParam(context, "ctype", "");
                        SPUtils.setParam(context, "cproduct", "");
                        SPUtils.setParam(context, "cdisplacement", "");
                        //成功后切换界面
                        view.switchFragment(new LoginFragment());
                        view.dismissProgressDialog();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        view.showToast(context.getString(R.string.logout_fail));
                        view.dismissProgressDialog();
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void switchToEditPassword() {
        view.switchFragment(new EditPasswordFragment());
    }

    @Override
    public void switchToEditUserInfo() {
        view.switchFragment(new EditUserInfoFragment(view.getUser()));
    }


}
