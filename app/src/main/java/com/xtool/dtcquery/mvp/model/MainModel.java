package com.xtool.dtcquery.mvp.model;

import com.xtool.dtcquery.bean.DtcCustom;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by xtool on 2017/9/7.
 */

public interface MainModel {

    Observable<List<DtcCustom>> GetDtcCustomByPost(String dcode);
}
