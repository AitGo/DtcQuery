package com.xtool.dtcquery.widget;

import android.content.Context;
import android.os.Bundle;
import android.view.WindowManager;

import com.xtool.dtcquery.R;


/**
 * Created by xtool on 2017/9/7.
 */

public class ProgressDialog extends android.app.ProgressDialog {
    public ProgressDialog(Context context) {
        super(context);
    }

    public ProgressDialog(Context context, int theme) {
        super(context, theme);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(getContext());
    }

    private void init(Context context) {
        setCancelable(false);
        setCanceledOnTouchOutside(false);
        setContentView(R.layout.layout_progress);//loading的xml文件
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().setAttributes(params);
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void dismiss() {
        if (this.isShowing()) {
            super.dismiss();
        }
    }
}
