package com.xtool.dtcquery.mvp.view;

import android.support.v4.app.Fragment;
import android.widget.TextView;

import com.xtool.dtcquery.base.BaseFragmentView;
import com.xtool.dtcquery.entity.UserDTO;

/**
 * Created by xtool on 2017/9/20.
 */

public interface UserView extends BaseFragmentView{

    UserDTO getUser();

    void setUnameText(String s);

    void setCnameText(String s);

    void setCtypeText(String s);

    void setCproductText(String s);

    void setCdisplacementText(String s);

}
