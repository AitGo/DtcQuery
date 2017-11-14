package com.xtool.dtcquery.mvp.view;

import android.support.v4.app.Fragment;

import com.xtool.dtcquery.base.BaseFragmentView;

/**
 * Created by xtool on 2017/10/31.
 */

public interface InsertUserView extends BaseFragmentView{

    String getNewPassword();

    String getNewPassword2();

    String getUserName();

    String getSmsCode();

    void setSmsCode(String s);

    void setNewPassword(String s);

    void setNewPassword2(String s);

    void startCountDownTimer();

    void finishCountDownTimer();

}
