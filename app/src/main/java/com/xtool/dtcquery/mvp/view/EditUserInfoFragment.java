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
import com.xtool.dtcquery.mvp.persenter.EditUserInfoPersenter;
import com.xtool.dtcquery.mvp.persenter.EditUserInfoPersenterImpl;

/**
 * Created by xtool on 2017/10/12.
 */

public class EditUserInfoFragment extends BaseFragment implements EditUserInfoView, View.OnClickListener {

    private Button btn_edit;
    private Button btn_cancel;
    private EditText et_cname;
    private EditText et_ctype;
    private EditText et_cproduct;
    private EditText et_cdisplacement;

    private EditUserInfoPersenter persenter;

    private UserDTO userDTO;

    public EditUserInfoFragment(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    @Override
    protected View initView(LayoutInflater inflater) {
        View inflate = inflater.inflate(R.layout.fragment_edituserinfo,null);
        btn_edit = (Button) inflate.findViewById(R.id.btn_edit);
        btn_cancel = (Button) inflate.findViewById(R.id.btn_cancel);
        et_cname = (EditText) inflate.findViewById(R.id.et_cname);
        et_ctype = (EditText) inflate.findViewById(R.id.et_ctype);
        et_cproduct = (EditText) inflate.findViewById(R.id.et_cproduct);
        et_cdisplacement = (EditText) inflate.findViewById(R.id.et_cdisplacement);

        btn_edit.setOnClickListener(this);
        btn_cancel.setOnClickListener(this);
        persenter = new EditUserInfoPersenterImpl(getActivity(),this);
        return inflate;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_edit:
                persenter.editUserInfo();
                break;
            case R.id.btn_cancel:
                persenter.switchToUserFragment();
                break;
        }
    }

    @Override
    public CarDTO getCarDTO() {
        CarDTO carDTO = new CarDTO();
        carDTO.setCname(et_cname.getText().toString().trim());
        carDTO.setCtype(et_ctype.getText().toString().trim());
        carDTO.setCproduct(et_cproduct.getText().toString().trim());
        carDTO.setCdisplacement(et_cdisplacement.getText().toString().trim());

        return carDTO;
    }

    @Override
    public UserDTO getUserDTO() {
        return userDTO;
    }

}
