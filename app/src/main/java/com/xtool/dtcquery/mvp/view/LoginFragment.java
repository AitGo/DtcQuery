package com.xtool.dtcquery.mvp.view;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.xtool.dtcquery.R;
import com.xtool.dtcquery.base.BaseFragment;
import com.xtool.dtcquery.entity.UserDTO;
import com.xtool.dtcquery.mvp.persenter.LoginPersenter;
import com.xtool.dtcquery.mvp.persenter.LoginPersenterImpl;

/**
 * Created by xtool on 2017/9/14.
 */

public class LoginFragment extends BaseFragment implements View.OnClickListener,LoginView{

    private Button btn_login;
    private Button btn_cancel;
    private Button btn_register;
    private EditText et_uname;
    private EditText et_upassword;

    private LoginPersenter persenter;

    @Override
    protected View initView(LayoutInflater inflater) {
        View inflate = inflater.inflate(R.layout.fragment_login, null);
        btn_login = (Button) inflate.findViewById(R.id.btn_login);
        btn_cancel = (Button) inflate.findViewById(R.id.btn_cancel);
        btn_register = (Button) inflate.findViewById(R.id.btn_register);
        et_uname = (EditText) inflate.findViewById(R.id.et_uname);
        et_upassword = (EditText) inflate.findViewById(R.id.et_upassword);

        btn_login.setOnClickListener(this);
        btn_cancel.setOnClickListener(this);
        btn_register.setOnClickListener(this);

        persenter = new LoginPersenterImpl(getContext(),this);
        return inflate;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                persenter.login();
                break;
            case R.id.btn_cancel:

                break;
            case R.id.btn_register:

                break;
        }

    }

    @Override
    public String getUname() {
        return et_uname.getText().toString().trim();
    }

    @Override
    public String getUpassword() {
        return et_upassword.getText().toString().trim();
    }

    @Override
    public void switchUserFragment() {

    }

    @Override
    public void switchUserFragment(UserDTO userDTO) {
        ((MainActivity)getActivity()).switchFragment(new UserFragment(userDTO));
    }
}
