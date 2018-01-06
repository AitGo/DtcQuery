package com.xtool.dtcquery.mvp.persenter;

import android.content.Context;
import android.util.Log;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.xtool.dtcquery.R;
import com.xtool.dtcquery.adapter.BrvahDtcRecyclerAdapter;
import com.xtool.dtcquery.adapter.DtcRecyclerAdapter;
import com.xtool.dtcquery.base.BaseFragment;
import com.xtool.dtcquery.entity.CarDTO;
import com.xtool.dtcquery.entity.DismissDialogEvent;
import com.xtool.dtcquery.entity.DtcDTO;
import com.xtool.dtcquery.entity.InsertUserEvent;
import com.xtool.dtcquery.entity.RecyclerBean;
import com.xtool.dtcquery.entity.UserDTO;
import com.xtool.dtcquery.mvp.model.MainModel;
import com.xtool.dtcquery.mvp.model.MainModelImpl;
import com.xtool.dtcquery.mvp.view.LoginFragment;
import com.xtool.dtcquery.mvp.view.MainView;
import com.xtool.dtcquery.mvp.view.UserFragment;
import com.xtool.dtcquery.utils.RxBus;
import com.xtool.dtcquery.utils.SPUtils;

import java.util.List;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by xtool on 2017/9/7.
 */

public class MainPersenterImpl implements MainPersenter {

    private int PAGE_COUNT = 7;

    private final String TAG = this.getClass().getSimpleName();
    private MainView view;
    private MainModel model;
    private Context context;

    private EventHandler eventHandler;
    public int smsFlage = 0;//0:设置为初始化值 1：请求获取验证码 2：提交用户输入的验证码判断是否正确


    public MainPersenterImpl(Context context, MainView view) {
        this.view = view;
        this.context = context;
        model = new MainModelImpl();
    }

    @Override
    public void query(final Integer s, Integer ps) {
        view.showProgressDialog();
        List<MultiItemEntity> list = view.getDtcList();
        list.clear();
        view.getRecyclerAdatper().setNewData(list);
//        RxBus.getInstance().send("发送事件");
        String dcode = view.getDcode();
        DtcDTO dtcDTO = setDcodeToDtcCustom(dcode, s, ps);

        model.GetDtcCustomByPost(dtcDTO)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<List<DtcDTO>>() {
                    @Override
                    public void onNext(@NonNull List<DtcDTO> dtcDTOList) {
                        Log.e(TAG, "onNext");
                        if (dtcDTOList.size() > 0) {
                            view.getRecyclerAdatper().addData(BrvahDtcRecyclerAdapter.getMultiItemList(dtcDTOList));
                        } else {
                            view.showToast(context.getString(R.string.no_dcode));
                        }
                        view.dismissProgressDialog();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(TAG, "onError: " + e.getMessage());
                        doError();
                    }

                    @Override
                    public void onComplete() {
                        Log.e(TAG, "onComplete");
                    }
                });
    }

    @Override
    public int getPAGE_COUNT() {
        return PAGE_COUNT;
    }

    // 上拉加载时调用的更新RecyclerView的方法
    public void loadMore(int s, int ps) {
//        view.showProgressDialog();
        String dcode = view.getDcode();
        DtcDTO dtcDTO = setDcodeToDtcCustom(dcode, s, ps);

        model.GetDtcCustomByPost(dtcDTO)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<List<DtcDTO>>() {
                    @Override
                    public void onNext(@NonNull List<DtcDTO> dtcDTOList) {
                        Log.e(TAG, "onNext");
                        if (dtcDTOList.size() > 0) {
                            // 然后传给Adapter，并设置hasMore为true
                            view.getRecyclerAdatper().addData(BrvahDtcRecyclerAdapter.getMultiItemList(dtcDTOList));
                            view.getRecyclerAdatper().loadMoreComplete();
                        } else {
                            view.getRecyclerAdatper().loadMoreEnd(false);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                        view.getRecyclerAdatper().loadMoreFail();
                    }

                    @Override
                    public void onComplete() {
                        Log.e(TAG, "onComplete");
                    }
                });
    }

    @Override
    public void showDrawer() {
        view.showDrawer();
    }

    @Override
    public void initSMS() {
        // 创建EventHandler对象
        eventHandler = new EventHandler() {
            public void afterEvent(int event, int result, Object data) {
                if (result == SMSSDK.RESULT_COMPLETE) {
                    //回调完成
                    if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                        //提交验证码成功
                        RxBus.getInstance().send(context.getString(R.string.submit_smscode_success));
                        //执行注册
                        RxBus.getInstance().send(new InsertUserEvent());

                    } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                        //获取验证码成功
                        RxBus.getInstance().send(context.getString(R.string.get_smscode_success));
                    } else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {
                        //返回支持发送验证码的国家列表
                    }
                } else {
                    //此语句代表接口返回失败
                    RxBus.getInstance().send(context.getString(R.string.submit_smscode_fail));
                    RxBus.getInstance().send(new DismissDialogEvent());
                    ((Throwable) data).printStackTrace();
                }
            }
        };
        // 注册监听器
        SMSSDK.registerEventHandler(eventHandler);
    }

    @Override
    public void unregristSMS() {
        SMSSDK.unregisterEventHandler(eventHandler);
    }

    @Override
    public BaseFragment initLeftFragment() {
        BaseFragment fragment;
        //判断是否登录，显示不同fragment
        String uname = model.getParamFromSP(context,"uname");
        if (uname != null && !uname.equals("")) {
            CarDTO carDTO = new CarDTO();
            UserDTO userDTO = new UserDTO();
            carDTO.setCname(model.getParamFromSP(context,"cname"));
            carDTO.setCtype(model.getParamFromSP(context,"ctype"));
            carDTO.setCproduct(model.getParamFromSP(context,"cproduct"));
            carDTO.setCdisplacement(model.getParamFromSP(context,"cdisplacement"));
            userDTO.setCarDTO(carDTO);
            userDTO.setUname(uname);

            fragment = new UserFragment(userDTO);

        } else {
            fragment = new LoginFragment();
        }
        return fragment;
    }

    @Override
    public boolean checkLogin() {
        String uname = model.getParamFromSP(context,"uname");
        if(uname == null || uname.equals("")) {
            return false;
        }else {
            return true;
        }
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
        view.showToast(context.getString(R.string.no_internet));
    }

    private RecyclerBean getRecyclerBean(List<DtcDTO> dtcDTOList) {
        RecyclerBean recyclerBean = new RecyclerBean();
        for (int i = 0; i < dtcDTOList.size(); i++) {
            RecyclerBean childBean = new RecyclerBean();
            recyclerBean.setId(i + "");
            recyclerBean.setDcode(dtcDTOList.get(i).getDcode());
            recyclerBean.setDname(dtcDTOList.get(i).getDname());
            childBean.setDinfo(dtcDTOList.get(i).getDinfo());
            childBean.setDcause(dtcDTOList.get(i).getDcause());
            childBean.setDfix(dtcDTOList.get(i).getDfix());
            recyclerBean.setChildBean(childBean);
        }
        return recyclerBean;
    }

}
