package com.xtool.dtcquery.mvp.persenter;

import android.content.Context;

import com.xtool.dtcquery.R;
import com.xtool.dtcquery.entity.CarDTO;
import com.xtool.dtcquery.entity.UserDTO;
import com.xtool.dtcquery.mvp.model.UserModel;
import com.xtool.dtcquery.mvp.model.UserModelImpl;
import com.xtool.dtcquery.mvp.view.EditPasswordFragment;
import com.xtool.dtcquery.mvp.view.EditUserInfoFragment;
import com.xtool.dtcquery.mvp.view.LoginFragment;
import com.xtool.dtcquery.mvp.view.UserView;
import com.xtool.dtcquery.utils.SPUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cn.jpush.android.api.JPushInterface;
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
    private UserModel model;


    public UserPersenterImpl(Context context, UserView view) {
        this.view = view;
        this.context = context;
        model = new UserModelImpl();
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
        model.userLogoutByPost(getUser())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<List<UserDTO>>() {
                    @Override
                    public void onNext(@NonNull List<UserDTO> list) {
                        Set<String> tags = new HashSet<String>();
                        tags.add("logout");
                        JPushInterface.setTags(context,1, tags);
                        JPushInterface.deleteAlias(context,1);
                        //修改缓存
                        model.setParamToSP(context,"uname","");
                        model.setParamToSP(context,"cname","");
                        model.setParamToSP(context,"ctype","");
                        model.setParamToSP(context,"cproduct","");
                        model.setParamToSP(context,"cdisplacement","");
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

    @Override
    public void initData() {
        view.setUnameText(model.getParamFromSP(context,"uname"));
        view.setCnameText(model.getParamFromSP(context,"cname"));
        view.setCtypeText(model.getParamFromSP(context,"ctype"));
        view.setCproductText(model.getParamFromSP(context,"cproduct"));
        view.setCdisplacementText(model.getParamFromSP(context,"cdisplacement"));
    }

    @Override
    public UserDTO getUserDTO(UserDTO userDTO) {
        if(userDTO == null) {
            userDTO = new UserDTO();
            CarDTO carDTO = new CarDTO();
            carDTO.setCname(model.getParamFromSP(context,"cname"));
            carDTO.setCtype(model.getParamFromSP(context,"ctype"));
            carDTO.setCproduct(model.getParamFromSP(context,"cproduct"));
            carDTO.setCdisplacement(model.getParamFromSP(context,"cdisplacement"));
            userDTO.setCarDTO(carDTO);
            userDTO.setUname(model.getParamFromSP(context,"uname"));
        }
        return userDTO;
    }


}
