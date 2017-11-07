package com.xtool.dtcquery.mvp.persenter;

import android.content.Context;
import android.util.Log;

import com.xtool.dtcquery.entity.CarDTO;
import com.xtool.dtcquery.entity.UserDTO;
import com.xtool.dtcquery.mvp.model.EditUserInfoModel;
import com.xtool.dtcquery.mvp.model.EditUserInfoModelImpl;
import com.xtool.dtcquery.mvp.view.EditUserInfoView;
import com.xtool.dtcquery.mvp.view.UserFragment;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by xtool on 2017/10/12.
 */

public class EditUserInfoPersenterImpl implements EditUserInfoPersenter {

    private Context context;
    private EditUserInfoView view;
    private EditUserInfoModel model;

    public EditUserInfoPersenterImpl(Context context,EditUserInfoView view) {
        this.context = context;
        this.view = view;
        model = new EditUserInfoModelImpl();
    }

    @Override
    public void editUserInfo() {
        view.showProgressDialog();
        UserDTO userDTO = view.getUserDTO();

        CarDTO carDTO = view.getCarDTO();
//        CarDTO carDTO = new CarDTO();
//        carDTO.setCname("起亚k5");
//        carDTO.setCtype("东风");
//        carDTO.setCproduct("2015");
//        carDTO.setCdisplacement("1.8T");
//        userDTO.setUname("wang");
//        userDTO.setIslogin("login");
//        userDTO.setCarDTO(carDTO);

        model.editUserInfoByPost(userDTO)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<List<UserDTO>>() {
                    @Override
                    public void onNext(@NonNull List<UserDTO> userDTOs) {
                        view.switchFragment(new UserFragment(userDTOs.get(0)));
                        view.dismissProgressDialog();
                        view.showToast("修改信息成功");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                        view.dismissProgressDialog();
                        view.showToast("修改信息失败");
                    }

                    @Override
                    public void onComplete() {
                        view.dismissProgressDialog();
                    }
                });

    }

    @Override
    public void switchToUserFragment() {
        view.switchFragment(new UserFragment());
    }
}
