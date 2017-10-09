package com.xtool.dtcquery.mvp.view;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xtool.dtcquery.R;
import com.xtool.dtcquery.base.BaseFragment;
import com.xtool.dtcquery.entity.UserDTO;
import com.xtool.dtcquery.mvp.persenter.UserPersenter;
import com.xtool.dtcquery.mvp.persenter.UserPersenterImpl;

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

        btn_logout.setOnClickListener(this);

        persenter = new UserPersenterImpl(getContext(),this);
        initData();
        return inflate;
    }

    private void initData() {
        tv_uname.setText(userDTO.getUname());
        tv_cname.setText(userDTO.getCarDTO().getCname());
        tv_ctype.setText(userDTO.getCarDTO().getCtype());
        tv_cproduct.setText(userDTO.getCarDTO().getCproduct());
        tv_cdisplacement.setText(userDTO.getCarDTO().getCdisplacement());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_logout:
                persenter.logout();
                break;
        }
    }

    @Override
    public void switchFragment(Fragment fragment) {
        ((MainActivity)getActivity()).switchFragment(fragment);
    }
}
