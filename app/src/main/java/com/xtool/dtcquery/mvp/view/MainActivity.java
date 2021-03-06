package com.xtool.dtcquery.mvp.view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.nineoldandroids.view.ViewHelper;
import com.xtool.dtcquery.R;
import com.xtool.dtcquery.adapter.BrvahDtcRecyclerAdapter;
import com.xtool.dtcquery.base.BaseActivity;
import com.xtool.dtcquery.base.BaseFragment;
import com.xtool.dtcquery.entity.DismissDialogEvent;
import com.xtool.dtcquery.entity.Key;
import com.xtool.dtcquery.mvp.persenter.InsertUserPersenter;
import com.xtool.dtcquery.mvp.persenter.MainPersenter;
import com.xtool.dtcquery.mvp.persenter.MainPersenterImpl;
import com.xtool.dtcquery.utils.RSAUtils;
import com.xtool.dtcquery.utils.RxBus;
import com.xtool.dtcquery.widget.DividerGridItemDecoration;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cn.jpush.android.api.JPushInterface;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class MainActivity extends BaseActivity implements MainView, View.OnClickListener {

    private final String TAG = this.getClass().getSimpleName();
    private MainPersenter persenter;
    private InsertUserPersenter insertUserPersenter;
    private GridLayoutManager mLayoutManager;
    private int lastVisibleItem = 0;
    private FrameLayout fl_left;
    private DrawerLayout dl_left;
    private Button btn_query;
    private Button btn_left_menu;
    private EditText et_dcode;
    private RecyclerView recyclerView;
    private BrvahDtcRecyclerAdapter adapter;
    private List<MultiItemEntity> dtcDTOList = new ArrayList<>();


    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    @Override
    public void init() {
        setContentView(R.layout.activity_main);
        persenter = new MainPersenterImpl(this, this);
        initJPush();
        initKey();
        initSMS();
        initView();
        initLeftFragment();
        initRecyclerView();
        RxBusEvent();
    }

    private void initJPush() {
        JPushInterface.init(getApplicationContext());
//        Set<String> tags = new HashSet<String>();
//        tags.add(persenter.checkLogin() ? "login" : "logout");
//        JPushInterface.setTags(this,1, tags);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        persenter.unregristSMS();
    }

    private void RxBusEvent() {
        RxBus.getInstance()
                .tObservable(String.class)
//                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
                    }
                });
        RxBus.getInstance()
                .tObservable(DismissDialogEvent.class)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<DismissDialogEvent>() {
                    @Override
                    public void accept(DismissDialogEvent dismissDialogEvent) throws Exception {
                        dismissProgressDialog();
                    }
                });
    }

    private void initKey() {
        try {
            Key.key = RSAUtils.getKey(this.getAssets().open("publicKey.cer"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initSMS() {
        persenter.initSMS();
    }

    private void initLeftFragment() {
        BaseFragment fragment = persenter.initLeftFragment();
        transaction.add(R.id.fl_left, fragment);
        transaction.show(fragment);
        transaction.commit();

    }

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    private void initView() {
        fl_left = (FrameLayout) findViewById(R.id.fl_left);
        dl_left = (DrawerLayout) findViewById(R.id.dl_left);
        btn_query = (Button) findViewById(R.id.btn_query);
        btn_left_menu = (Button) findViewById(R.id.btn_left_menu);
        et_dcode = (EditText) findViewById(R.id.et_dcode);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_dtc);

        btn_left_menu.setOnClickListener(this);
        btn_query.setOnClickListener(this);
        dl_left.setDrawerListener(listen);

    }

    DrawerLayout.DrawerListener listen = new DrawerLayout.DrawerListener() {

        @Override
        public void onDrawerSlide(View drawerView, float slideOffset) {

            View mContent = dl_left.getChildAt(0);
            View mMenu = drawerView;
            float scale = 1 - slideOffset;
            //改变DrawLayout侧栏透明度，若不需要效果可以不设置
            ViewHelper.setAlpha(mMenu, 0.6f + 0.4f * (1 - scale));
            ViewHelper.setTranslationX(mContent,
                    mMenu.getMeasuredWidth() * (1 - scale));
            ViewHelper.setPivotX(mContent, 0);
            ViewHelper.setPivotY(mContent, mContent.getMeasuredHeight() / 2);
            mContent.invalidate();
        }

        @Override
        public void onDrawerOpened(View drawerView) {

        }

        @Override
        public void onDrawerClosed(View drawerView) {

        }

        @Override
        public void onDrawerStateChanged(int newState) {

        }
    };


        private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerGridItemDecoration(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        adapter = new BrvahDtcRecyclerAdapter(dtcDTOList);
        adapter.openLoadAnimation();
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                persenter.loadMore(adapter.getLoadMoreViewPosition(), persenter.getPAGE_COUNT());
            }
        });
        mLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_query:
                dtcDTOList.clear();
                adapter.setNewData(dtcDTOList);
                persenter.query(0, persenter.getPAGE_COUNT());
                break;
            case R.id.btn_left_menu:
                persenter.showDrawer();
                break;
        }
    }

    @Override
    public String getDcode() {
        return et_dcode.getText().toString();
    }

    @Override
    public void showDrawer() {
        dl_left.openDrawer(Gravity.LEFT);
    }

    @Override
    public void closeDrawer() {
        dl_left.closeDrawer(Gravity.LEFT);
    }

    @Override
    public void switchFragment(Fragment fragment) {
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fl_left, fragment);
        transaction.commit();
    }

    @Override
    public BrvahDtcRecyclerAdapter getRecyclerAdatper() {
        if (adapter == null) {
            adapter = new BrvahDtcRecyclerAdapter(dtcDTOList);
        }
        return adapter;
    }

    @Override
    public List<MultiItemEntity> getDtcList() {
        return dtcDTOList;
    }
}
