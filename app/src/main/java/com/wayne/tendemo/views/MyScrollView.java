package com.wayne.tendemo.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

import com.wayne.tendemo.MainActivity;

/**
 * Project:TenDemo
 * Author:wayne
 * Date:2016/6/1
 */
public class MyScrollView extends ScrollView {
    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (t-oldl>10)
        {
            MainActivity.mRadioGroup.setVisibility(INVISIBLE);
        }else if(t<oldl)
        {
            MainActivity.mRadioGroup.setVisibility(VISIBLE);
        }
    }
}
