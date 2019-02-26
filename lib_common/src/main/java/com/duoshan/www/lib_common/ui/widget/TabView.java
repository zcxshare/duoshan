package com.duoshan.www.lib_common.ui.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.duoshan.www.lib_common.R;

import java.util.jar.Attributes;

import androidx.appcompat.widget.AppCompatTextView;

/**
 * author:  zhouchaoxiang
 * date:    2019/1/23
 * explain:
 */
public class TabView extends LinearLayout {

    private boolean isCenter = false;
    private Drawable mBackground;

    public TabView(Context context) {
        this(context, null);
    }

    public TabView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TabView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public TabView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TabView);
        isCenter = a.getBoolean(R.styleable.TabView_isCenter, false);
        a.recycle();
        setGravity(Gravity.CENTER);
        setOrientation(VERTICAL);
        addView(new ImageView(context, attrs, defStyleAttr, defStyleRes));
        addView(new TextView(context, attrs, defStyleAttr, defStyleRes));
        setClickable(true);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    public boolean isCenter() {
        return isCenter;
    }

    public void setCenter(boolean center) {
        isCenter = center;
    }
}
