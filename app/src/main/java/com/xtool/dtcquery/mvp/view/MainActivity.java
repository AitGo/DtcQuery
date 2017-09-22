package com.xtool.dtcquery.mvp.view;

import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.xtool.dtcquery.R;
import com.xtool.dtcquery.adapter.DtcListAdapter;
import com.xtool.dtcquery.adapter.DtcRecyclerAdapter;
import com.xtool.dtcquery.base.BaseActivity;
import com.xtool.dtcquery.entity.DtcDTO;
import com.xtool.dtcquery.mvp.persenter.MainPersenter;
import com.xtool.dtcquery.mvp.persenter.MainPersenterImpl;
import com.xtool.dtcquery.utils.RxBus;
import com.xtool.dtcquery.widget.DividerGridItemDecoration;
import com.xtool.dtcquery.widget.PullUpLoadMoreListView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Consumer;


public class MainActivity extends BaseActivity implements MainView,View.OnClickListener {

    private final String TAG = this.getClass().getSimpleName();
    private MainPersenter persenter;
    private GridLayoutManager mLayoutManager;
    private int lastVisibleItem = 0;
    private FrameLayout fl_left;
    private DrawerLayout dl_left;
    private Button btn_query;
    private Button btn_left_menu;
    private EditText et_dcode;
    private android.support.v7.widget.RecyclerView recyclerView;
    private LinearLayout ll_list_title;
    private DtcRecyclerAdapter adapter;
    private List<DtcDTO> dtcDTOList = new ArrayList<DtcDTO>();
    private List<DtcDTO> dtcDTOList1 = new ArrayList<DtcDTO>();

    @Override
    public void init() {
        setContentView(R.layout.activity_main);
        initView();
        persenter = new MainPersenterImpl(this,this);
        LoginFragment loginFragment = new LoginFragment();
        transaction.add(R.id.fl_left,loginFragment,"login");
        transaction.show(loginFragment);
        transaction.commit();
    }

    private void initView() {
        fl_left = (FrameLayout) findViewById(R.id.fl_left);
        dl_left = (DrawerLayout) findViewById(R.id.dl_left);
        btn_query = (Button) findViewById(R.id.btn_query);
        btn_left_menu = (Button) findViewById(R.id.btn_left_menu);
        et_dcode = (EditText) findViewById(R.id.et_dcode);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_dtc);
        ll_list_title = (LinearLayout) findViewById(R.id.ll_list_title);

        btn_left_menu.setOnClickListener(this);
        btn_query.setOnClickListener(this);

        initRecyclerView();


        dismissListTitle();


        RxBus.getInstance().subscribe(String.class, new Consumer() {
            @Override
            public void accept(Object o) throws Exception {
//                if(o.toString().equals("发送事件1"))
                    Toast.makeText(getApplicationContext(),"1234",Toast.LENGTH_LONG).show();
            }
        });
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerGridItemDecoration(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        adapter = new DtcRecyclerAdapter(dtcDTOList1,this,false);
        mLayoutManager = new GridLayoutManager(this,1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState == RecyclerView.SCROLL_STATE_IDLE) {
                    persenter.onScrollStateChanged();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                persenter.onScrolled(mLayoutManager.findLastVisibleItemPosition());
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_query:
                persenter.query(0,6);
                break;
            case R.id.btn_left_menu:
                showDrawer();
                break;
        }
    }

    @Override
    public void showListMeg(List<DtcDTO> dtcDTOs) {
        dtcDTOList.clear();
        dtcDTOList.addAll(dtcDTOs);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showListMoreMeg(List<DtcDTO> dtcDTOs) {
        dtcDTOList.addAll(dtcDTOs);
        adapter.notifyDataSetChanged();
    }

    @Override
    public String getDcode() {
        return et_dcode.getText().toString();
    }

    @Override
    public void dismissListTitle() {
        dtcDTOList.clear();
        adapter.notifyDataSetChanged();
        ll_list_title.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showListTitle() {
        ll_list_title.setVisibility(View.VISIBLE);
    }

    @Override
    public void showDrawer() {
        dl_left.openDrawer(Gravity.LEFT);
    }

    @Override
    public void dismissDrawer() {
        dl_left.closeDrawer(Gravity.LEFT);
    }

    @Override
    public void switchFragment(Fragment fragment) {
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fl_left,fragment);
        transaction.commit();
    }

    @Override
    public void setLastVisibleItem(int item) {
        lastVisibleItem = item;
    }

    @Override
    public int getLastVisibleItem() {
        return lastVisibleItem;
    }

    @Override
    public DtcRecyclerAdapter getRecyclerAdatper() {
        if(adapter == null) {
            adapter = new DtcRecyclerAdapter(dtcDTOList1,this,false);
        }
        return adapter;
    }

//    @Override
//    public void onLoad() {
//
//        Log.e(TAG,"total: " + lv_dtc.getTotalItemCount());
//        Log.e(TAG,"lastVisibieItem: " + lv_dtc.getLastVisibieItem());
//        persenter.query(lv_dtc.getLastVisibieItem(),PullUpLoadMoreListView.PAGESIZE);
//        Toast.makeText(this,"onLoad",Toast.LENGTH_LONG).show();
//        lv_dtc.loadComplete();
//    }
}
