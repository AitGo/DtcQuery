package com.xtool.dtcquery.mvp.model;

import com.xtool.dtcquery.entity.DtcDTO;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by xtool on 2017/9/7.
 */

public interface MainModel {

    Observable<List<DtcDTO>> GetDtcCustomByPost(DtcDTO dtcDTO);


}
