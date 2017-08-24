package com.xtool.dtcquery;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.xtool.dtcquery.bean.Dtc;
import com.xtool.dtcquery.bean.DtcCustom;
import com.xtool.dtcquery.converter.JsonConverterFactory;
import com.xtool.dtcquery.converter.JsonRequestBodyConverter;
import com.xtool.dtcquery.http.HttpMethods;
import com.xtool.dtcquery.service.PostActivation;
import com.xtool.dtcquery.utils.RSAUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Path;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";
    private Button btn_query;
    private EditText et_dcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

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
                UUID.randomUUID();

                Gson gson = new Gson();
                String json = gson.toJson(dtc);
                OkHttpClient okHttpClient = new OkHttpClient.Builder()
                        .addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)).build();
                Log.d(TAG, "Json: " + json);
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(PostActivation.BSERURL)
                        .client(okHttpClient)
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                            .addConverterFactory( GsonConverterFactory.create())
                        .addConverterFactory(JsonConverterFactory.create(this))
                        .build();


                PostActivation postActivation = retrofit.create(PostActivation.class);

                postActivation.postActivation(dtc, "queryDtcByDcodeJson.action")
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<List<DtcCustom>>() {
                            @Override
                            public void onCompleted() {
                                Log.e(TAG, "onCompleted");
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.e(TAG, "onError:");
                                e.printStackTrace();
                            }

                            @Override
                            public void onNext(List<DtcCustom> dtcCustoms) {
                                Log.e(TAG, "onNext");
                                for (DtcCustom dtcCustom : dtcCustoms) {
                                    Log.e(TAG, dtcCustom.getDname());
                                }
                            }
                        });



                Subscriber<List<DtcCustom>> subscriber = new Subscriber<List<DtcCustom>>() {

                    @Override
                    public void onCompleted() {
                        Log.e(TAG, "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError:");
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(List<DtcCustom> dtcCustoms) {
                        Log.e(TAG, "onNext");
                        for (DtcCustom dtcCustom : dtcCustoms) {
                            Log.e(TAG, dtcCustom.getDname());
                        }
                    }

                };

                HttpMethods.getInstance().getDtcCustom( HttpMethods.getInstance().(dtc, "queryDtcByDcodeJson.action"),subscriber);
                break;
        }


    }


}
