package com.xtool.dtcquery.mvp.persenter;

import android.content.Context;
import android.util.Log;

import com.xtool.dtcquery.R;
import com.xtool.dtcquery.entity.UserDTO;
import com.xtool.dtcquery.mvp.model.EditPasswordModel;
import com.xtool.dtcquery.mvp.model.EditPasswordModelImpl;
import com.xtool.dtcquery.mvp.view.EditPasswordFragment;
import com.xtool.dtcquery.mvp.view.EditPasswordView;
import com.xtool.dtcquery.mvp.view.LoginFragment;
import com.xtool.dtcquery.mvp.view.UserFragment;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by xtool on 2017/10/9.
 */

public class EditPasswordPersenterImpl implements EditPasswordPersenter {

    private Context context;
    private EditPasswordView view;

    private EditPasswordModel model;

    public EditPasswordPersenterImpl(Context context, EditPasswordView view) {
        this.context = context;
        this.view = view;
        model = new EditPasswordModelImpl();
    }

    /**
     *
     * @return null输入有误
     */
    private String getNewPassword() {
        if(view.getNewPassword() != null && view.getNewPassword2() != null) {
            if(view.getNewPassword().equals(view.getNewPassword2())) {
                return view.getNewPassword();
            }
        }
        return null;
    }

    @Override
    public void editPassword() {
        view.showProgressDialog();
        if (getNewPassword() != null) {
            UserDTO userDTO = new UserDTO();
            userDTO.setUname(view.getUserName());
            userDTO.setUpassword(getNewPassword());
            userDTO.setIslogin("login");
            model.editPasswordByPost(userDTO)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(new DisposableObserver<List<UserDTO>>() {
                        @Override
                        public void onNext(@NonNull List<UserDTO> userDTOs) {
                            view.dismissProgressDialog();
                            view.showToast(context.getString(R.string.edit_success));
                            view.switchFragment(new UserFragment(userDTOs.get(0)));
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                            e.printStackTrace();
                            view.showToast(context.getString(R.string.edit_fail));
                            view.dismissProgressDialog();
                        }

                        @Override
                        public void onComplete() {
                            view.dismissProgressDialog();
                        }
                    });
        }else {
            //输入有误，重新输入
            view.showToast(context.getString(R.string.edit_again));
            view.setNewPassword("");
            view.setNewPassword2("");
            view.dismissProgressDialog();
        }
    }

    @Override
    public void switchLoginFragment() {
        view.switchFragment(new UserFragment());
    }
}
