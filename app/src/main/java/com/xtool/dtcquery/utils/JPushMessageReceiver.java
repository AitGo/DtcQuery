package com.xtool.dtcquery.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import cn.jpush.android.api.JPushInterface;


/**
 * Created by xtool on 2018/1/5.
 */

public class JPushMessageReceiver extends BroadcastReceiver {
    private String TAG = "JPush";
    public static final String KEY_TITLE = "title";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_EXTRAS = "extras";
    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            Bundle bundle = intent.getExtras();
            if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
                Log.e(TAG, "cn.jpush.android.intent.NOTIFICATION_OPENED");
                //打开自定义的Activity
                String messge = bundle.getString(JPushInterface.EXTRA_ALERT);
                String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
                String title = bundle.getString(JPushInterface.EXTRA_TITLE);
                StringBuilder showMsg = new StringBuilder();
                showMsg.append(KEY_MESSAGE + " : " + messge + "\n");
                showMsg.append(KEY_EXTRAS + " : " + extras + "\n");
                showMsg.append(KEY_TITLE + " : " + title );
                Log.e("msg", showMsg + "");
                String s = printBundle(bundle);
                Log.e("s",s);
            }else if("cn.jpush.android.intent.REGISTRATION".equals(intent.getAction())) {
                Log.e(TAG,"cn.jpush.android.intent.REGISTRATION");
            }else if("cn.jpush.android.intent.MESSAGE_RECEIVED".equals(intent.getAction())) {
                Log.e(TAG,"cn.jpush.android.intent.MESSAGE_RECEIVED");
            }else if("cn.jpush.android.intent.NOTIFICATION_RECEIVED".equals(intent.getAction())) {
                Log.e(TAG,"cn.jpush.android.intent.NOTIFICATION_RECEIVED");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 打印所有的 intent extra 数据
    private static String printBundle(Bundle bundle) {
        StringBuilder sb = new StringBuilder();
        for (String key : bundle.keySet()) {
            if (key.equals(JPushInterface.EXTRA_NOTIFICATION_ID)) {
                sb.append("\nkey:" + key + ", value:" + bundle.getInt(key));
            } else if (key.equals(JPushInterface.EXTRA_CONNECTION_CHANGE)) {
                sb.append("\nkey:" + key + ", value:" + bundle.getBoolean(key));
            } else {
                sb.append("\nkey:" + key + ", value:" + bundle.getString(key));
            }
        }
        return sb.toString();
    }

}
