package com.xtool.dtcquery;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.xtool.dtcquery.adapter.DtcListAdapter;
import com.xtool.dtcquery.bean.DtcCustom;
import com.xtool.dtcquery.bean.Message;
import com.xtool.dtcquery.bean.User;
import com.xtool.dtcquery.http.ServiceFactory;
import com.xtool.dtcquery.http.PostActivation;
import com.xtool.dtcquery.utils.RxBus;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;


public class MainActivity extends Activity implements View.OnClickListener {

    private static final String TAG = "MainActivity";
    private Button btn_query;
    private EditText et_dcode;
    private ListView lv_dtc;
    private DtcListAdapter adapter;
    private List<DtcCustom> dtcCustomList = new ArrayList<DtcCustom>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.getInstance().unSubscribe();
    }

    private void initView() {
        btn_query = (Button) findViewById(R.id.btn_query);
        et_dcode = (EditText) findViewById(R.id.et_dcode);
        lv_dtc = (ListView) findViewById(R.id.lv_dtc);

        btn_query.setOnClickListener(this);
        adapter = new DtcListAdapter(this,dtcCustomList);
        lv_dtc.setAdapter(adapter);

        RxBus.getInstance().subscribe(String.class, new Consumer() {
            @Override
            public void accept(Object o) throws Exception {
                if(o.toString().equals("发送事件"))
                    Toast.makeText(getApplicationContext(),"1234",Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_query:
                RxBus.getInstance().send("发送事件");
                String dcode = et_dcode.getText().toString();
                DtcCustom dtc = new DtcCustom();
                dtc.setDcode(dcode);
                ServiceFactory.getInstance().createService(PostActivation.class)
                        .postActivation(dtc,"queryDtcByDcodeJson.action")
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableObserver<List<DtcCustom>>() {
                            @Override
                            public void onNext(@NonNull List<DtcCustom> dtcCustoms) {
                                Log.e(TAG,"onNext");
                                for(DtcCustom dtcCustom : dtcCustoms) {
                                    Log.e(TAG,dtcCustom.getDname());
                                }
                                dtcCustomList  = dtcCustoms;
                                adapter.setDtcCustomList(dtcCustomList);
                                adapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {
                                Log.e(TAG,"onError");
                                e.printStackTrace();
                            }

                            @Override
                            public void onComplete() {
                                Log.e(TAG,"onComplete");
                            }
                        });

                break;
        }


    }


}
