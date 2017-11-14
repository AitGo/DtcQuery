package com.xtool.dtcquery.mvp.model;

import android.content.Context;

import com.xtool.dtcquery.base.BaseModel;
import com.xtool.dtcquery.base.BaseModelImpl;
import com.xtool.dtcquery.entity.DtcDTO;
import com.xtool.dtcquery.http.PostActivation;
import com.xtool.dtcquery.http.ServiceFactory;
import com.xtool.dtcquery.utils.SPUtils;

import io.reactivex.Observable;


/**
 * Created by xtool on 2017/9/7.
 */

public class MainModelImpl extends BaseModelImpl implements MainModel {

    public MainModelImpl() {

    }
    @Override
    public Observable GetDtcCustomByPost(DtcDTO dtcDTO) {

        return ServiceFactory.getInstance().createService(PostActivation.class)
                .postDtcQuery(dtcDTO);
    }

}
