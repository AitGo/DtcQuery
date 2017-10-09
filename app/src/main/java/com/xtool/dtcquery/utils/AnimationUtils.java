package com.xtool.dtcquery.utils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

import org.w3c.dom.Text;

import static android.R.attr.type;

/**
 * Created by xtool on 2017/9/26.
 */

public class AnimationUtils {

    public static TranslateAnimation moveToViewBottom() {
        TranslateAnimation mHiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                0.0f, Animation.RELATIVE_TO_SELF, 1.0f);
        mHiddenAction.setDuration(500);
        return mHiddenAction;
    }

    public static TranslateAnimation moveToViewLocation() {
        TranslateAnimation mHiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
        mHiddenAction.setDuration(500);
        return mHiddenAction;
    }

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    public static ValueAnimator open(final View view) {

        ValueAnimator animator = ValueAnimator.ofInt(view.getLayoutParams().height,0);

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (Integer) animation.getAnimatedValue();
                //动态设置高度
                Log.e("ValueANimator",value+"");
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                layoutParams.height = value;
                view.setLayoutParams(layoutParams);
                //如果是关闭动画，当动画结束时view设置为GONE
//                if(type.equals("close")){
//                    if(value==0){
//                        view.setVisibility(View.GONE);
//                    }
//                }
            }
        });
        return animator;

    }


}
