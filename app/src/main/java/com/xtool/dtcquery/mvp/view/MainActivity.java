package com.xtool.dtcquery.mvp.view;

import android.animation.LayoutTransition;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
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
import com.xtool.dtcquery.R;
import com.xtool.dtcquery.adapter.BrvahDtcRecyclerAdapter;
import com.xtool.dtcquery.adapter.DtcRecyclerAdapter;
import com.xtool.dtcquery.base.BaseActivity;
import com.xtool.dtcquery.entity.CarDTO;
import com.xtool.dtcquery.entity.DtcDTO;
import com.xtool.dtcquery.entity.UserDTO;
import com.xtool.dtcquery.mvp.persenter.MainPersenter;
import com.xtool.dtcquery.mvp.persenter.MainPersenterImpl;
import com.xtool.dtcquery.utils.RxBus;
import com.xtool.dtcquery.utils.SPUtils;
import com.xtool.dtcquery.widget.DividerGridItemDecoration;

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
    private RecyclerView recyclerView;
    private BrvahDtcRecyclerAdapter adapter;
    private List<MultiItemEntity> dtcDTOList = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    @Override
    public void init() {
        setContentView(R.layout.activity_main);
        initView();
        persenter = new MainPersenterImpl(this,this);
        initLeftFragment();
        initRecyclerView();
    }

    private void initLeftFragment() {
        //判断是否登录，显示不同fragment
        String uname = (String) SPUtils.getParam(this,"uname","");
        if(uname != null && !uname.equals("")) {
            CarDTO carDTO = new CarDTO();
            UserDTO userDTO = new UserDTO();
            carDTO.setCname((String) SPUtils.getParam(this,"cname",""));
            carDTO.setCtype((String) SPUtils.getParam(this,"ctype",""));
            carDTO.setCproduct((String) SPUtils.getParam(this,"cproduct",""));
            carDTO.setCdisplacement((String) SPUtils.getParam(this,"cdisplacement",""));
            userDTO.setCarDTO(carDTO);
            userDTO.setUname(uname);

            UserFragment userFragment = new UserFragment(userDTO);
            transaction.add(R.id.fl_left,userFragment,"user");
            transaction.show(userFragment);
            transaction.commit();
        }else {
            LoginFragment loginFragment = new LoginFragment();
            transaction.add(R.id.fl_left,loginFragment,"login");
            transaction.show(loginFragment);
            transaction.commit();
        }
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


//        RxBus.getInstance().subscribe(String.class, new Consumer() {
//            @Override
//            public void accept(Object o) throws Exception {
//                if(o.toString().equals("发送事件1"))
//                    Toast.makeText(getApplicationContext(),"1234",Toast.LENGTH_LONG).show();
//            }
//        });


    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerGridItemDecoration(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        adapter = new BrvahDtcRecyclerAdapter(dtcDTOList);
        adapter.openLoadAnimation();
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                persenter.loadMore(adapter.getLoadMoreViewPosition(),persenter.getPAGE_COUNT());
            }
        });
        mLayoutManager = new GridLayoutManager(this,1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_query:
                dtcDTOList.clear();
                adapter.setNewData(dtcDTOList);
                persenter.query(0,persenter.getPAGE_COUNT());
                break;
            case R.id.btn_left_menu:
                showDrawer();
                break;
        }
    }

    @Override
    public void showListMeg(List<DtcDTO> dtcDTOs) {
        dtcDTOList.clear();
        dtcDTOList.addAll(BrvahDtcRecyclerAdapter.getMultiItemList(dtcDTOs));
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showListMoreMeg(List<DtcDTO> dtcDTOs) {
        dtcDTOList.addAll(BrvahDtcRecyclerAdapter.getMultiItemList(dtcDTOs));
        adapter.notifyDataSetChanged();
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
    public BrvahDtcRecyclerAdapter getRecyclerAdatper() {
        if(adapter == null) {
            adapter = new BrvahDtcRecyclerAdapter(dtcDTOList);
        }
        return adapter;
    }
}
