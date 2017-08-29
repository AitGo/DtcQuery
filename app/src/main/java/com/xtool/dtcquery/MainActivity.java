package com.xtool.dtcquery;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.xtool.dtcquery.bean.DtcCustom;
import com.xtool.dtcquery.bean.PublicKey;
import com.xtool.dtcquery.http.ServiceFactory;
import com.xtool.dtcquery.http.TransformUtils;
import com.xtool.dtcquery.http.service.PostActivation;
import com.xtool.dtcquery.utils.RSAUtils;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.Subject;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";
    private Button btn_query;
    private EditText et_dcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        PublicKey.rsaPublicKey = RSAUtils.getPublicKeyFromAsset(this);
    }


    private void initView() {
        btn_query = (Button) findViewById(R.id.btn_query);
        et_dcode = (EditText) findViewById(R.id.et_dcode);

        btn_query.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_query:
                String dcode = et_dcode.getText().toString();
                DtcCustom dtc = new DtcCustom();
                dtc.setDcode(dcode);
                Gson gson = new Gson();
                String json = gson.toJson(dtc);
                Log.d(TAG, "Json: " + json);

                ServiceFactory.getInstance().createService(PostActivation.class)
                        .postActivation(dtc,"queryDtcByDcodeJson.action")
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
//                        .compose(TransformUtils.<List<DtcCustom>>defaultSchedulers())
                        .subscribe(new Subject<List<DtcCustom>>() {
                            @Override
                            public boolean hasObservers() {
                                return false;
                            }

                            @Override
                            public boolean hasThrowable() {
                                return false;
                            }

                            @Override
                            public boolean hasComplete() {
                                return false;
                            }

                            @Override
                            public Throwable getThrowable() {
                                return null;
                            }

                            @Override
                            protected void subscribeActual(Observer<? super List<DtcCustom>> observer) {

                            }

                            @Override
                            public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {

                            }

                            @Override
                            public void onNext(@io.reactivex.annotations.NonNull List<DtcCustom> dtcCustoms) {
                                Log.e(TAG, "onNext");
                                for (DtcCustom dtcCustom : dtcCustoms) {
                                    Log.e(TAG, dtcCustom.getDname());
                                }
                            }

                            @Override
                            public void onError(@io.reactivex.annotations.NonNull Throwable e) {
                                Log.e(TAG, "onError:");
                                e.printStackTrace();
                            }

                            @Override
                            public void onComplete() {
                                Log.e(TAG, "onCompleted");
                            }
                        });

                break;
        }


    }


}
