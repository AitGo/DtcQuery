package com.xtool.dtcquery.mvp.persenter;

import android.content.Context;

import com.xtool.dtcquery.mvp.model.EditPasswordModel;
import com.xtool.dtcquery.mvp.model.EditPasswordModelImpl;
import com.xtool.dtcquery.mvp.view.EditPasswordFragment;
import com.xtool.dtcquery.mvp.view.EditPasswordView;

/**
 * Created by xtool on 2017/10/9.
 */

public class EditPasswordPersenterImpl implements EditPasswordPersenter {

    private Context context;
    private EditPasswordView view;

    private EditPasswordModel model;

    public EditPasswordPersenterImpl(Context context, EditPasswordView view) {
        this.context = context;
        this.view = view;
        model = new EditPasswordModelImpl();
    }

    @Override
    public void editPassword() {

    }
}
