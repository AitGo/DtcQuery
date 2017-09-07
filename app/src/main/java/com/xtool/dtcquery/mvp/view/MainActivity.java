package com.xtool.dtcquery.mvp.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.xtool.dtcquery.R;
import com.xtool.dtcquery.adapter.DtcListAdapter;
import com.xtool.dtcquery.bean.DtcCustom;
import com.xtool.dtcquery.mvp.persenter.MainPersenter;
import com.xtool.dtcquery.mvp.persenter.MainPersenterImpl;
import com.xtool.dtcquery.mvp.view.MainView;
import com.xtool.dtcquery.utils.RxBus;
import com.xtool.dtcquery.widget.ProgressDialog;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Consumer;


public class MainActivity extends Activity implements MainView,View.OnClickListener {

    private final String TAG = this.getClass().getSimpleName();
    private MainPersenter persenter;
    private Button btn_query;
    private EditText et_dcode;
    private ListView lv_dtc;
    private ProgressDialog progressDialog;
    private LinearLayout ll_list_title;
    private DtcListAdapter adapter;
    private List<DtcCustom> dtcCustomList = new ArrayList<DtcCustom>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        persenter = new MainPersenterImpl(this);
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
        ll_list_title = (LinearLayout) findViewById(R.id.ll_list_title);

        btn_query.setOnClickListener(this);
        adapter = new DtcListAdapter(this,dtcCustomList);
        lv_dtc.setAdapter(adapter);
        progressDialog = new ProgressDialog(this);

        dismissListTitle();
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
                persenter.query();
                break;
        }
    }

    @Override
    public void showProgressDialog() {
        progressDialog.show();
    }

    @Override
    public void dismissProgressDialog() {
        progressDialog.dismiss();
    }

    @Override
    public void showListMeg(List<DtcCustom> dtcCustoms) {
        dtcCustomList.clear();
        dtcCustomList.addAll(dtcCustoms);
        adapter.notifyDataSetChanged();
    }

    @Override
    public String getDcode() {
        return et_dcode.getText().toString();
    }

    @Override
    public void dismissListTitle() {
        ll_list_title.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showListTitle() {
        ll_list_title.setVisibility(View.VISIBLE);
    }
}
