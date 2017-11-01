package com.xtool.dtcquery.mvp.view;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.xtool.dtcquery.R;
import com.xtool.dtcquery.base.BaseFragment;
import com.xtool.dtcquery.mvp.persenter.InsertUserPersenter;
import com.xtool.dtcquery.mvp.persenter.InsertUserPersenterImpl;

/**
 * Created by xtool on 2017/10/31.
 */

public class InsertUserFragment extends BaseFragment implements InsertUserView,View.OnClickListener {

    private EditText et_uname;
    private EditText et_password;
    private EditText et_password2;
    private Button btn_regist;
    private Button btn_cancel;

    private InsertUserPersenter persenter;

    @Override
    protected View initView(LayoutInflater inflater) {
        View inflate = inflater.inflate(R.layout.fragment_insertuser,null);
        et_uname = (EditText) inflate.findViewById(R.id.et_uname);
        et_password = (EditText) inflate.findViewById(R.id.et_password);
        et_password2 = (EditText) inflate.findViewById(R.id.et_password2);
        btn_regist = (Button) inflate.findViewById(R.id.btn_regist);
        btn_cancel = (Button) inflate.findViewById(R.id.btn_cancel);

        btn_regist.setOnClickListener(this);
        btn_cancel.setOnClickListener(this);

        persenter = new InsertUserPersenterImpl(getContext(),this);
        return inflate;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_regist:
                persenter.regist();
                break;
            case R.id.btn_cancel:
                persenter.switchLoginFragment();
                break;
        }
    }

    @Override
    public String getNewPassword() {
        return et_password.getText().toString();
    }

    @Override
    public String getNewPassword2() {
        return et_password2.getText().toString();
    }

    @Override
    public String getUserName() {
        return et_uname.getText().toString();
    }

    @Override
    public void setNewPassword(String s) {
        et_password.setText(s);
    }

    @Override
    public void setNewPassword2(String s) {
        et_password2.setText(s);
    }
}
