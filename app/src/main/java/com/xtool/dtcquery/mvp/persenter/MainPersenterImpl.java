package com.xtool.dtcquery.mvp.persenter;

import android.util.Log;

import com.xtool.dtcquery.bean.DtcCustom;
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

    public MainPersenterImpl(MainView view) {
        this.view = view;
        model = new MainModelImpl();
    }
    @Override
    public void query() {
        view.showProgressDialog();
        RxBus.getInstance().send("发送事件");
        String dcode = view.getDcode();
        model.GetDtcCustomByPost(dcode)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<List<DtcCustom>>() {
                    @Override
                    public void onNext(@NonNull List<DtcCustom> dtcCustomList) {
                        Log.e(TAG,"onNext");
                        doNext(dtcCustomList);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(TAG,"onError");
                        view.dismissProgressDialog();
                        view.dismissListTitle();
                    }

                    @Override
                    public void onComplete() {
                        Log.e(TAG,"onComplete");
                    }
                });
    }

    private void doNext(List<DtcCustom> dtcCustomList) {
        if(dtcCustomList.size() > 0) {
            view.showListMeg(dtcCustomList);
            view.showListTitle();
        }else {
            view.dismissListTitle();
        }
        view.dismissProgressDialog();
    }
}
