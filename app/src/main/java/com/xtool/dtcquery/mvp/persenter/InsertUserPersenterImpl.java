package com.xtool.dtcquery.mvp.persenter;

import android.content.Context;

import com.xtool.dtcquery.entity.UserDTO;
import com.xtool.dtcquery.mvp.model.InsertUserModel;
import com.xtool.dtcquery.mvp.model.InsertUserModelImpl;
import com.xtool.dtcquery.mvp.view.InsertUserView;
import com.xtool.dtcquery.mvp.view.LoginFragment;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by xtool on 2017/10/31.
 */

public class InsertUserPersenterImpl implements InsertUserPersenter {

    private Context context;
    private InsertUserView view;
    private InsertUserModel model;

    public InsertUserPersenterImpl(Context context,InsertUserView view) {
        this.context = context;
        this.view = view;
        model = new InsertUserModelImpl();
    }

    @Override
    public void switchLoginFragment() {
        view.switchFragment(new LoginFragment());
    }

    @Override
    public void regist() {
        view.showProgressDialog();
        if (checkEdit() != null) {
            UserDTO userDTO = new UserDTO();
            userDTO.setUname(view.getUserName());
            userDTO.setUpassword(view.getNewPassword());
            userDTO.setIslogin("logout");
            model.postInsertUser(userDTO)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(new DisposableObserver<List<UserDTO>>() {

                        @Override
                        public void onNext(@NonNull List<UserDTO> userDTOs) {
                            view.showToast("注册成功！");

                            switchLoginFragment();
                            view.dismissProgressDialog();
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                            view.showToast("注册失败！");
                            switchLoginFragment();
                            view.dismissProgressDialog();
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }


    }

    /**
     *
     * @return null输入有误
     */
    private String checkEdit() {
        if(view.getNewPassword() != null && view.getNewPassword2() != null && view.getUserName() != null) {
            if(view.getNewPassword().equals(view.getNewPassword2())) {
                return view.getNewPassword();
            }
        }
        return null;
    }
}
