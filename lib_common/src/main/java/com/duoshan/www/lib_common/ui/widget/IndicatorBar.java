package com.duoshan.www.lib_common.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * author:  zhouchaoxiang
 * date:    2019/2/12
 * explain: 
 */
public    class IndicatorBar extends ImageView {
    private int mCurrentIndex;
    public IndicatorBar(Context context) {
        super(context);
    }

    public IndicatorBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public IndicatorBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public int getCurrentIndex() {
        return mCurrentIndex;
    }

    public void setCurrentIndex(int currentIndex) {
        mCurrentIndex = currentIndex;
    }
}
