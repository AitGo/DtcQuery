package com.xtool.dtcquery.mvp.persenter;

import android.content.Context;
import android.util.Log;

import com.xtool.dtcquery.R;
import com.xtool.dtcquery.entity.UserDTO;
import com.xtool.dtcquery.mvp.model.LoginModel;
import com.xtool.dtcquery.mvp.model.LoginModelImpl;
import com.xtool.dtcquery.mvp.view.EditPasswordFragment;
import com.xtool.dtcquery.mvp.view.InsertUserFragment;
import com.xtool.dtcquery.mvp.view.LoginView;
import com.xtool.dtcquery.utils.SPUtils;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by xtool on 2017/9/14.
 */

public class LoginPersenterImpl implements LoginPersenter {

    private final String TAG = this.getClass().getSimpleName();
    private LoginView view;
    private Context context;
    private LoginModel model;

    public LoginPersenterImpl(Context context,LoginView view) {
        this.view = view;
        this.context = context;
        model = new LoginModelImpl();
    }

    @Override
    public void login() {
        if(isEditEmpty()) {
            view.showToast(context.getString(R.string.empty_uname_upassword));
        }else {
            view. showProgressDialog();
            String uname = view.getUname();
            String upassword = view.getUpassword();
            UserDTO userDTO = new UserDTO();
            userDTO.setUname(uname);
            userDTO.setUpassword(upassword);
            userDTO.setIslogin("login");
            model.GetUserCustomByPost(userDTO)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(new DisposableObserver<List<UserDTO>>() {
                        @Override
                        public void onNext(@NonNull List<UserDTO> userDTOs) {
                            Log.e(TAG,"onNext");
                            //缓存user信息
                            SPUtils.setParam(context,"uname",userDTOs.get(0).getUname());
                            if(userDTOs.get(0).getCarDTO() != null) {
                                SPUtils.setParam(context,"cname",userDTOs.get(0).getCarDTO().getCname());
                                SPUtils.setParam(context,"ctype",userDTOs.get(0).getCarDTO().getCtype());
                                SPUtils.setParam(context,"cproduct",userDTOs.get(0).getCarDTO().getCproduct());
                                SPUtils.setParam(context,"cdisplacement",userDTOs.get(0).getCarDTO().getCdisplacement());
                            }else {
                                SPUtils.setParam(context,"cname","");
                                SPUtils.setParam(context,"ctype","");
                                SPUtils.setParam(context,"cproduct","");
                                SPUtils.setParam(context,"cdisplacement","");
                            }

                            view.switchUserFragment(userDTOs.get(0));
                            view.dismissProgressDialog();

                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                            Log.e(TAG,"onError");
                            e.printStackTrace();
                            view.showToast(context.getString(R.string.login_fail));
                            view.dismissProgressDialog();
                        }

                        @Override
                        public void onComplete() {
                            Log.e(TAG,"onComplete");
                        }
                    });
        }

    }

    /**
     *
     * @return false不为空 true为空
     */
    private boolean isEditEmpty() {
        boolean isEmpty = false;
        if(view.getUname().equals("") || view.getUname() == null)
            isEmpty = true;
        if(view.getUpassword().equals("") || view.getUpassword() == null)
            isEmpty = true;
        return isEmpty;
    }


    @Override
    public void closeDrawer() {
        view.closeDrawer();
    }

    @Override
    public void switchInsertUser() {
        view.switchFragment(new InsertUserFragment());
    }
}
