package com.xtool.dtcquery.mvp.view;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.xtool.dtcquery.R;
import com.xtool.dtcquery.base.BaseFragment;
import com.xtool.dtcquery.mvp.persenter.EditPasswordPersenter;
import com.xtool.dtcquery.mvp.persenter.EditPasswordPersenterImpl;

/**
 * Created by xtool on 2017/10/9.
 */

public class EditPasswordFragment extends BaseFragment implements View.OnClickListener,EditPasswordView {

    private EditText et_newpassword;
    private EditText et_newpassword2;

    private Button btn_edit;

    private EditPasswordPersenter persenter;

    @Override
    protected View initView(LayoutInflater inflater) {
        View inflate = inflater.inflate(R.layout.fragment_editpassword,null);
        et_newpassword = (EditText) inflate.findViewById(R.id.et_newpassword);
        et_newpassword2 = (EditText) inflate.findViewById(R.id.et_newpassword2);
        btn_edit = (Button) inflate.findViewById(R.id.btn_edit);

        btn_edit.setOnClickListener(this);

        persenter = new EditPasswordPersenterImpl(getContext(),this);
        return inflate;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_edit:

                break;
        }
    }
}
