package com.xtool.dtcquery.mvp.model;

import android.content.Context;

import com.xtool.dtcquery.base.BaseModel;
import com.xtool.dtcquery.entity.DtcDTO;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by xtool on 2017/9/7.
 */

public interface MainModel extends BaseModel{

    Observable<List<DtcDTO>> GetDtcCustomByPost(DtcDTO dtcDTO);

    String getParamFromSP(Context context,String key);

    void setParamToSP(Context context,String key,String value);

}
