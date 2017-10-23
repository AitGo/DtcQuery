package com.xtool.dtcquery.mvp.view;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.xtool.dtcquery.R;
import com.xtool.dtcquery.base.BaseFragment;
import com.xtool.dtcquery.entity.CarDTO;
import com.xtool.dtcquery.entity.UserDTO;
import com.xtool.dtcquery.mvp.persenter.EditPasswordPersenter;
import com.xtool.dtcquery.mvp.persenter.EditPasswordPersenterImpl;
import com.xtool.dtcquery.utils.SPUtils;

/**
 * Created by xtool on 2017/10/9.
 */

public class EditPasswordFragment extends BaseFragment implements View.OnClickListener,EditPasswordView {

    private EditText et_newpassword;
    private EditText et_newpassword2;

    private Button btn_edit;
    private Button btn_editcancel;

    private EditPasswordPersenter persenter;

    @Override
    protected View initView(LayoutInflater inflater) {
        View inflate = inflater.inflate(R.layout.fragment_editpassword,null);
        et_newpassword = (EditText) inflate.findViewById(R.id.et_newpassword);
        et_newpassword2 = (EditText) inflate.findViewById(R.id.et_newpassword2);
        btn_edit = (Button) inflate.findViewById(R.id.btn_edit);
        btn_editcancel = (Button) inflate.findViewById(R.id.btn_editcancel);
        btn_edit.setOnClickListener(this);
        btn_editcancel.setOnClickListener(this);

        persenter = new EditPasswordPersenterImpl(getContext(),this);
        return inflate;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_edit:
                persenter.editPassword();
                break;
            case R.id.btn_editcancel:
                persenter.switchLoginFragment();
                break;
        }
    }

    @Override
    public String getNewPassword() {
        return et_newpassword.getText().toString().trim();
    }

    @Override
    public String getNewPassword2() {
        return et_newpassword2.getText().toString().trim();
    }

    @Override
    public String getUserName() {
        return (String) SPUtils.getParam(getContext(),"uname","");
    }

    @Override
    public void setNewPassword(String s) {
        et_newpassword.setText(s);
    }

    @Override
    public void setNewPassword2(String s) {
        et_newpassword2.setText(s);
    }

    @Override
    public void switchFragment(Fragment fragment) {
        ((MainActivity)getActivity()).switchFragment(fragment);
    }
}
