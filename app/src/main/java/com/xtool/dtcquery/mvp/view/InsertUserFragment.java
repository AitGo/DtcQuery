package com.xtool.dtcquery.mvp.view;

import android.annotation.SuppressLint;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.xtool.dtcquery.R;
import com.xtool.dtcquery.base.BaseFragment;
import com.xtool.dtcquery.entity.InsertUserEvent;
import com.xtool.dtcquery.mvp.persenter.InsertUserPersenter;
import com.xtool.dtcquery.mvp.persenter.InsertUserPersenterImpl;
import com.xtool.dtcquery.utils.RxBus;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by xtool on 2017/10/31.
 */

public class InsertUserFragment extends BaseFragment implements InsertUserView,View.OnClickListener {

    private EditText et_uname;
    private EditText et_smscode;
    private EditText et_password;
    private EditText et_password2;
    private Button btn_regist;
    private Button btn_cancel;
    private Button btn_sendcode;

    private InsertUserPersenter persenter;
    private MyCountDownTimer myCountDownTimer;

    @Override
    protected View initView(LayoutInflater inflater) {
        View inflate = inflater.inflate(R.layout.fragment_insertuser,null);
        et_uname = (EditText) inflate.findViewById(R.id.et_uname);
        et_smscode = (EditText) inflate.findViewById(R.id.et_smscode);
        et_password = (EditText) inflate.findViewById(R.id.et_password);
        et_password2 = (EditText) inflate.findViewById(R.id.et_password2);
        btn_regist = (Button) inflate.findViewById(R.id.btn_regist);
        btn_cancel = (Button) inflate.findViewById(R.id.btn_cancel);
        btn_sendcode = (Button) inflate.findViewById(R.id.btn_sendcode);

        btn_regist.setOnClickListener(this);
        btn_cancel.setOnClickListener(this);
        btn_sendcode.setOnClickListener(this);

        myCountDownTimer = new MyCountDownTimer(60000,1000);
        persenter = new InsertUserPersenterImpl(getContext(),this);
        InsertUserEvent();
        return inflate;

    }

    private void InsertUserEvent() {
        RxBus.getInstance().tObservable(InsertUserEvent.class)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<InsertUserEvent>() {
                    @Override
                    public void accept(InsertUserEvent insertUserEvent) throws Exception {
                        persenter.regist();
                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_regist:
                persenter.checkSMSCode();
                break;
            case R.id.btn_cancel:
                finishCountDownTimer();
                persenter.switchLoginFragment();
                break;
            case R.id.btn_sendcode:
                persenter.sendSMSCode();
                break;

        }
    }

    private class MyCountDownTimer extends CountDownTimer {
        /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         */
        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @SuppressLint("SetTextI18n")
        @Override
        public void onTick(long millisUntilFinished) {
            btn_sendcode.setClickable(false);
            btn_sendcode.setText(millisUntilFinished/1000 + "s");
        }

        @Override
        public void onFinish() {
            btn_sendcode.setText(getString(R.string.send_again));
            btn_sendcode.setClickable(true);
        }
    }

    @Override
    public String getNewPassword() {
        return et_password.getText().toString().trim();
    }

    @Override
    public String getNewPassword2() {
        return et_password2.getText().toString().trim();
    }

    @Override
    public String getUserName() {
        return et_uname.getText().toString().trim();
    }

    @Override
    public String getSmsCode() {
        return et_smscode.getText().toString().trim();
    }

    @Override
    public void setSmsCode(String s) {
        et_smscode.setText(s);
    }

    @Override
    public void setNewPassword(String s) {
        et_password.setText(s);
    }

    @Override
    public void setNewPassword2(String s) {
        et_password2.setText(s);
    }

    @Override
    public void startCountDownTimer() {
        myCountDownTimer.start();
    }

    @Override
    public void finishCountDownTimer() {
        myCountDownTimer.cancel();
    }
}
