package com.duoshan.www.lib_common.ui.widget;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

/**
 * author:  zhouchaoxiang
 * date:    2019/1/23
 * explain:
 */
public class BottomTagBar extends LinearLayout {
    private int mTagHeight = 100;
    private int mIndex;
    private Rect[] childDevRect;//保存误差

    public BottomTagBar(Context context) {
        this(context, null);
    }

    public BottomTagBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BottomTagBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        childDevRect = new Rect[getChildCount()];
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        /*int childCount = getChildCount();
        int childWidth = groupWidth / childCount;
        int childHeight = mTagHeight;
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            if (child == null || child.getVisibility() == GONE) {
                continue;
            }
            child.measure(childWidth, childHeight);
        }*/
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        int childTop;
        int childLeft = 0;
        int totalChildCount = getChildCount();
        for (int i = 0; i < totalChildCount; i++) {
            View child = getChildAt(i);
            if (child == null || child.getVisibility() == GONE) {
                continue;
            }
            int childWidth = child.getMeasuredWidth();
            int childHeight = child.getMeasuredHeight();
            int groupHeight = getMeasuredHeight();
//            setChildFrame(child, childLeft, 0, childWidth, childHeight);
            childLeft += childWidth;
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    public void setChangeChildLayout(int index, float left, float top, float right, float bottom) {
        if (childDevRect == null) childDevRect = new Rect[getChildCount()];
        childDevRect[index].set(left, top, right, bottom);
        View child = getChildAt(index);
        child.layout(child.getLeft() - left, child.getTop() - top, child.getRight() - right, child.getBottom() - bottom);
//        postInvalidate();
    }

}
