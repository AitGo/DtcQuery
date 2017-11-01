package com.xtool.dtcquery.mvp.view;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xtool.dtcquery.R;
import com.xtool.dtcquery.base.BaseFragment;
import com.xtool.dtcquery.entity.CarDTO;
import com.xtool.dtcquery.entity.UserDTO;
import com.xtool.dtcquery.mvp.persenter.UserPersenter;
import com.xtool.dtcquery.mvp.persenter.UserPersenterImpl;
import com.xtool.dtcquery.utils.SPUtils;

/**
 * Created by xtool on 2017/9/14.
 */

public class UserFragment extends BaseFragment implements UserView, View.OnClickListener {

    private TextView tv_uname;
    private TextView tv_cname;
    private TextView tv_ctype;
    private TextView tv_cproduct;
    private TextView tv_cdisplacement;
    private Button btn_logout;
    private Button btn_editpassword;
    private Button btn_eidtuser;

    private UserDTO userDTO;

    private UserPersenter persenter;

    public UserFragment() {

    }

    public UserFragment(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    @Override
    protected View initView(LayoutInflater inflater) {
        View inflate = inflater.inflate(R.layout.fragment_user, null);
        tv_uname = (TextView) inflate.findViewById(R.id.tv_uname);
        tv_cname = (TextView) inflate.findViewById(R.id.tv_cname);
        tv_ctype = (TextView) inflate.findViewById(R.id.tv_ctype);
        tv_cproduct = (TextView) inflate.findViewById(R.id.tv_cproduct);
        tv_cdisplacement = (TextView) inflate.findViewById(R.id.tv_cdisplacement);
        btn_logout = (Button) inflate.findViewById(R.id.btn_logout);
        btn_editpassword = (Button) inflate.findViewById(R.id.btn_editpassword);
        btn_eidtuser = (Button) inflate.findViewById(R.id.btn_eidtuser);
        btn_logout.setOnClickListener(this);
        btn_editpassword.setOnClickListener(this);
        btn_eidtuser.setOnClickListener(this);

        persenter = new UserPersenterImpl(getContext(),this);
        initData();
        return inflate;
    }

    private void initData() {
        tv_uname.setText((String) SPUtils.getParam(getContext(),"uname",""));
        tv_cname.setText((String) SPUtils.getParam(getContext(),"cname",""));
        tv_ctype.setText((String) SPUtils.getParam(getContext(),"ctype",""));
        tv_cproduct.setText((String) SPUtils.getParam(getContext(),"cproduct",""));
        tv_cdisplacement.setText((String) SPUtils.getParam(getContext(),"cdisplacement",""));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_logout:
                persenter.logout();
                break;
            case R.id.btn_eidtuser:
                persenter.switchToEditUserInfo();
                break;
            case R.id.btn_editpassword:
                persenter.switchToEditPassword();
                break;
        }
    }

    @Override
    public UserDTO getUser() {
        if(userDTO == null) {
            userDTO = new UserDTO();
            CarDTO carDTO = new CarDTO();
            carDTO.setCname((String) SPUtils.getParam(getContext(),"cname",""));
            carDTO.setCtype((String) SPUtils.getParam(getContext(),"ctype",""));
            carDTO.setCproduct((String) SPUtils.getParam(getContext(),"cproduct",""));
            carDTO.setCdisplacement((String) SPUtils.getParam(getContext(),"cdisplacement",""));
            userDTO.setCarDTO(carDTO);
            userDTO.setUname((String) SPUtils.getParam(getContext(),"uname",""));
        }
        return userDTO;
    }
}
