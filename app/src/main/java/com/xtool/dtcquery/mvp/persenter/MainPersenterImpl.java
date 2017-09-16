package com.xtool.dtcquery.mvp.persenter;

import android.content.Context;
import android.util.Log;

import com.xtool.dtcquery.R;
import com.xtool.dtcquery.entity.DtcDTO;
import com.xtool.dtcquery.mvp.model.MainModel;
import com.xtool.dtcquery.mvp.model.MainModelImpl;
import com.xtool.dtcquery.mvp.view.MainView;
import com.xtool.dtcquery.utils.RxBus;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by xtool on 2017/9/7.
 */

public class MainPersenterImpl implements MainPersenter {

    private final String TAG = this.getClass().getSimpleName();
    private MainView view;
    private MainModel model;
    private Context context;

    public MainPersenterImpl(Context context,MainView view) {
        this.view = view;
        this.context = context;
        model = new MainModelImpl();
    }

    @Override
    public void query(final Integer s, Integer ps) {
        view.showProgressDialog();
        RxBus.getInstance().send("发送事件");
        String dcode = view.getDcode();
        DtcDTO dtcDTO = setDcodeToDtcCustom(dcode, s, ps);

        model.GetDtcCustomByPost(dtcDTO)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<List<DtcDTO>>() {
                    @Override
                    public void onNext(@NonNull List<DtcDTO> dtcDTOList) {
                        Log.e(TAG,"onNext");
                        doNext(dtcDTOList);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(TAG,"onError: "+ e.getMessage());
                        doError();
                    }

                    @Override
                    public void onComplete() {
                        Log.e(TAG,"onComplete");
                    }
                });
    }

    private DtcDTO setDcodeToDtcCustom(String dcode, Integer s, Integer ps) {

        DtcDTO dtcDTO = new DtcDTO();
        dtcDTO.setDcode(dcode);
        dtcDTO.setS(s);
        dtcDTO.setPs(ps);
        return dtcDTO;
    }

    private void doError() {
        view.dismissProgressDialog();
        view.dismissListTitle();
        view.showToast(context.getString(R.string.nointernet));
    }

    private void doNext(List<DtcDTO> dtcDTOList) {
        if(dtcDTOList.size() > 0) {
            view.showListMeg(dtcDTOList);
            view.showListTitle();
        }else {
            view.dismissListTitle();
            view.showToast(context.getString(R.string.nodcode));
        }
        view.dismissProgressDialog();
    }
}
