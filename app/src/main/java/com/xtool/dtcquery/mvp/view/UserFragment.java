package com.xtool.dtcquery.mvp.view;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.xtool.dtcquery.R;
import com.xtool.dtcquery.base.BaseFragment;
import com.xtool.dtcquery.entity.UserDTO;

/**
 * Created by xtool on 2017/9/14.
 */

public class UserFragment extends BaseFragment implements UserView{

    private TextView tv_uname;
    private TextView tv_cname;
    private TextView tv_ctype;
    private TextView tv_cproduct;
    private TextView tv_cdisplacement;

    private UserDTO userDTO;

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
}
