package com.xtool.dtcquery.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;

import com.xtool.dtcquery.utils.RxBus;
import com.xtool.dtcquery.widget.ProgressDialog;

/**
 * Created by xtool on 2017/9/13.
 */

public abstract class BaseActivity extends FragmentActivity implements BaseView {

    private ProgressDialog progressDialog;
    public FragmentManager fragmentManager;
    public FragmentTransaction transaction;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progressDialog = new ProgressDialog(this);
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.getInstance().unSubscribe();
    }

    public abstract void init();

    @Override
    public void showProgressDialog() {
        progressDialog.show();
    }

    @Override
    public void dismissProgressDialog() {
        progressDialog.dismiss();
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
    }
}
