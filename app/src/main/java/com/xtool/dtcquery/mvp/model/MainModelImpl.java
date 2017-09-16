package com.xtool.dtcquery.mvp.model;

import com.xtool.dtcquery.entity.DtcDTO;
import com.xtool.dtcquery.http.PostActivation;
import com.xtool.dtcquery.http.ServiceFactory;

import io.reactivex.Observable;


/**
 * Created by xtool on 2017/9/7.
 */

public class MainModelImpl implements MainModel{

    public MainModelImpl() {

    }
    @Override
    public Observable GetDtcCustomByPost(DtcDTO dtcDTO) {

        return ServiceFactory.getInstance().createService(PostActivation.class)
                .postDtcQuery(dtcDTO);
    }


}
