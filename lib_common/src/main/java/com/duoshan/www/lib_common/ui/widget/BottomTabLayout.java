package com.duoshan.www.lib_common.ui.widget;


import android.animation.ArgbEvaluator;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.duoshan.www.lib_common.R;
import com.duoshan.www.lib_common.model.FloatRect;
import com.duoshan.www.lib_common.utils.PixelUtils;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

/**
 * author:  zhouchaoxiang
 * date:    2019/1/23
 * explain:
 */
public class BottomTabLayout extends LinearLayout {
    private static final String TAG = "MainActivity";
    private static final int mIndicatorWidth = 20;
    private static final int mIndicatorHeight = 6;
    private static final int mIndicatorBottomMargin = 12;
    private List<Rect> childMoveLayout;//子控件移动的距离
    private List<ChildCache> childCache = new ArrayList<>();//缓存
    private IndicatorBar mIndicatorBar;
    private TabLayoutOnPageChangeListener mPageChangeListener;
    private ViewPager mViewPager;
    private Drawable mDrawable;
    private Pair<Integer, Integer> mRingColor;
    private Pair<Integer, Integer> mSolidColor;
    private ArgbEvaluator mArgbEvaluator = new ArgbEvaluator();//颜色动态生成类
    private Drawable mBottomRingDrawable;

    public BottomTabLayout(Context context) {
        this(context, null);
    }

    public BottomTabLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BottomTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mIndicatorBar = new IndicatorBar(context);
        mDrawable = getResources().getDrawable(R.drawable.shape_indicator, null);
        mBottomRingDrawable = getResources().getDrawable(R.drawable.shape_home_bottom_ring);
    }

    @Override
    public void onViewAdded(View child) {
        childCache.add(new ChildCache());
    }

    @Override
    protected void onAttachedToWindow() {
        Log.i(TAG, "onAttachedToWindow: ");
        super.onAttachedToWindow();
        addView(mIndicatorBar);
        mIndicatorBar.setImageDrawable(mDrawable);
        ViewGroup.LayoutParams layoutParams = mIndicatorBar.getLayoutParams();
        if (layoutParams instanceof LinearLayout.LayoutParams) {
            ((LayoutParams) layoutParams).bottomMargin = mIndicatorBottomMargin;
            mIndicatorBar.setLayoutParams(layoutParams);
        }
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.i(TAG, "onMeasure: ");
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        final int childWidthMeasureSpec = MeasureSpec.makeMeasureSpec(mIndicatorWidth, MeasureSpec.EXACTLY);
        final int childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(mIndicatorHeight, MeasureSpec.EXACTLY);
        mIndicatorBar.measure(childWidthMeasureSpec, childHeightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        Log.i(TAG, "onSizeChanged: ");
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        Log.i(TAG, "onLayout: ");
        super.onLayout(changed, l, t, r, b);
        int childTop;
        int childLeft = 0;
        int totalChildCount = getChildCount();
        for (int i = 0; i < totalChildCount; i++) {
            View child = getChildAt(i);
            if (child == null || child.getVisibility() == GONE) {
                continue;
            }
            childCache.get(i).childInitLayout.set(child.getLeft(), child.getTop(), child.getRight(), child.getBottom());
            if (i == 0) {
                selectTab(0);
            }
            int childWidth = child.getMeasuredWidth();
            int childHeight = child.getMeasuredHeight();
            int groupHeight = getMeasuredHeight();
//            setChildFrame(child, childLeft, 0, childWidth, childHeight);
            childLeft += childWidth;
        }

    }

    public void setImageRingColorResource(@ColorRes int startColor, @ColorRes int endColor) {
        mRingColor = new Pair<>(ContextCompat.getColor(getContext(), startColor), ContextCompat.getColor(getContext(), endColor));
    }

    public void setImageRingColor(@ColorInt int startColor, @ColorInt int endColor) {
        mRingColor = new Pair<>(startColor, endColor);
    }

    public void setImageSolidColorResource(@ColorRes int startColor, @ColorRes int endColor) {
        mSolidColor = new Pair<>(ContextCompat.getColor(getContext(), startColor), ContextCompat.getColor(getContext(), endColor));
    }

    public void setImageSolidColor(@ColorInt int startColor, @ColorInt int endColor) {
        mSolidColor = new Pair<>(startColor, endColor);
    }

    /**
     * 将{@link ViewPager}绑定到{@link BottomTabLayout}
     *
     * @param viewPager 绑定的viewpager
     */
    public void setupWithViewPager(ViewPager viewPager) {
        mViewPager = viewPager;
    }

    /**
     * 使用多闪特效
     */
    public void initManyFlash(ViewPager viewPager) {
        setImageRingColor(0xffffffff, 0x66ffffff);
        setImageSolidColor(0x00ffffff, 0xffffffff);
        int textMovePx = PixelUtils.dp2px(getContext(), 50);
        int imageMovePx = PixelUtils.dp2px(getContext(), 100);
        setChildMoveLayout(new Rect(-textMovePx, 0, -textMovePx, 0),
                new Rect(0, -imageMovePx, 0, -imageMovePx),
                new Rect(textMovePx, 0, textMovePx, 0));
        addPagerChangeListener(viewPager);
    }

    private void addPagerChangeListener(ViewPager viewPager) {
        if (mViewPager != null) {
            if (mPageChangeListener != null) {
                mViewPager.removeOnPageChangeListener(mPageChangeListener);
            }
        }
        if (viewPager != null) {
            mViewPager = viewPager;
            if (mPageChangeListener == null) {
                mPageChangeListener = new TabLayoutOnPageChangeListener(this);
            }
            viewPager.addOnPageChangeListener(mPageChangeListener);
        }
    }

    public void selectTab(int position) {
        Log.i("aaa", "selectTab: ");
        mIndicatorBar.setCurrentIndex(position);
        Rect indexLayout = getIndexBarLayout(position);
        mIndicatorBar.layout(indexLayout.left, indexLayout.top, indexLayout.right, indexLayout.bottom);
    }

    public Rect getIndexBarLayout(int index) {
        final int childWidth = mIndicatorBar.getMeasuredWidth();
        final int childHeight = mIndicatorBar.getMeasuredHeight();
        int centerX = childCache.get(index).getCenterX();
        ViewGroup.LayoutParams layoutParams = mIndicatorBar.getLayoutParams();
        int bottomMargin = 0;
        if (layoutParams instanceof LayoutParams) {
            bottomMargin = ((LayoutParams) layoutParams).bottomMargin;
        }
        return new Rect(centerX - childWidth / 2, getHeight() - childHeight - bottomMargin, centerX + childWidth / 2, getHeight() - bottomMargin);
    }

    /**
     * 偏移索引指示器
     *
     * @param position viewpager显示的索引的前一个索引
     * @param offset   偏移量
     * @param hide     是否渐变隐藏
     */
    public void offsetIndex(int position, float offset, boolean hide) {
        if (position > childCache.size() - 1) return;
        int currentX = childCache.get(position).getCenterX();
        int currentY = childCache.get(position).getCenterY();
        int targetX = childCache.get(position + 1).getCenterX();
        int targetY = childCache.get(position + 1).getCenterY();
        float offsetPixelsX = (targetX - currentX) * offset;
        float offsetPixelsY = (targetY - currentY) * offset;
        Rect indexLayout = getIndexBarLayout(position);
        mIndicatorBar.layout(indexLayout.left + (int) offsetPixelsX, indexLayout.top + (int) offsetPixelsY, indexLayout.right + (int) offsetPixelsX, indexLayout.bottom + (int) offsetPixelsY);
    }

    /**
     * 调整tab位置
     *
     * @param position viewpager显示的索引的前一个索引
     * @param offset   偏移量
     */
    public void offsetTab(int position, float offset, int positionOffsetPixels) {
        for (int i = 0; i < getChildCount() - 1; i++) {//去除最后一个指示器
            TabView childView = (TabView) getChildAt(i);
            if (childView.isCenter()) {
                offsetImageView(i, position, offset);
            } else if (childView instanceof TabView) {
                offsetTextView(i, position, offset);
            }
        }
    }

    /**
     * @param index    子view的索引
     * @param position viewpager显示的索引的前一个索引
     * @param offset   偏移量
     */
    private void offsetImageView(int index, int position, float offset) {
        if (position != index && position != index - 1) return;
        if (mRingColor == null || mSolidColor == null) {
            throw new RuntimeException("请先设置mRingColor和mSolidColor");
        }
        if (mBottomRingDrawable instanceof GradientDrawable) {
            int ringColor;
            int solidColor;
            Rect offsetLayout = null;
            Rect initLayout = null;
            if (childMoveLayout.size() > index) {
                offsetLayout = childMoveLayout.get(index);
                initLayout = childCache.get(index).childInitLayout;
            }
            if (position == index - 1) {
                ringColor = (Integer) mArgbEvaluator.evaluate(offset, mRingColor.first, mRingColor.second);
                solidColor = (Integer) mArgbEvaluator.evaluate(offset, mSolidColor.first, mSolidColor.second);
                if (offsetLayout != null && initLayout != null) {
                    getChildAt(index).layout(initLayout.left + (int) (offsetLayout.left * offset), initLayout.top + (int) (offsetLayout.top * offset),
                            initLayout.right + (int) (offsetLayout.right * offset), initLayout.bottom + (int) (offsetLayout.bottom * offset));
                }
            } else {
                ringColor = (Integer) mArgbEvaluator.evaluate(offset, mRingColor.second, mRingColor.first);
                solidColor = (Integer) mArgbEvaluator.evaluate(offset, mSolidColor.second, mSolidColor.first);
                if (offsetLayout != null && initLayout != null) {
                    getChildAt(index).layout(initLayout.left + (int) (offsetLayout.left * (1 - offset)), initLayout.top + (int) (offsetLayout.top * (1 - offset)),
                            initLayout.right + (int) (offsetLayout.right * (1 - offset)), initLayout.bottom + (int) (offsetLayout.bottom * (1 - offset)));
                }
            }
            float density = getResources().getDisplayMetrics().density;
            ((GradientDrawable) mBottomRingDrawable).setStroke((int) (density * 6 + 0.5f), ringColor);
            ((GradientDrawable) mBottomRingDrawable).setColor(solidColor);
        }
    }

    /**
     * @param index    子view的索引
     * @param position viewpager显示的索引的前一个索引
     * @param offset   偏移量
     */
    private void offsetTextView(int index, int position, float offset) {
        Rect offsetLayout = null;
        Rect initLayout = null;
        if (childMoveLayout.size() > index) {
            offsetLayout = childMoveLayout.get(index);
            initLayout = childCache.get(index).childInitLayout;
        }
        if (offsetLayout != null && initLayout != null) {
            TabView child = (TabView) getChildAt(position);
            if (child.isCenter()) {
                getChildAt(index).layout(initLayout.left + (int) (offsetLayout.left * (1 - offset)), initLayout.top + (int) (offsetLayout.top * (1 - offset)),
                        initLayout.right + (int) (offsetLayout.right * (1 - offset)), initLayout.bottom + (int) (offsetLayout.bottom * (1 - offset)));
            } else {
                getChildAt(index).layout(initLayout.left + (int) (offsetLayout.left * offset), initLayout.top + (int) (offsetLayout.top * offset),
                        initLayout.right + (int) (offsetLayout.right * offset), initLayout.bottom + (int) (offsetLayout.bottom * offset));
            }
        }
    }

    public void restoreChildLayout(int index) {
        View child = getChildAt(index);
        Rect childInitLayout = childCache.get(index).childInitLayout;
        child.layout(childInitLayout.left, childInitLayout.top, childInitLayout.right, childInitLayout.bottom);
    }

    public List<Rect> getChildMoveLayout() {
        return childMoveLayout;
    }

    public void setChildMoveLayout(Rect... childMoveLayout) {
        this.childMoveLayout = Arrays.asList(childMoveLayout);
    }

    class ChildCache {
        Rect childInitLayout = new Rect();//初始化布局
        FloatRect childDevRect = new FloatRect();//误差保存
        Rect currentLayout = new Rect();//当前处于的位置

        public int getCenterX() {
            return (childInitLayout.right + childInitLayout.left) / 2;
        }

        public int getCenterY() {
            return (childInitLayout.top + childInitLayout.bottom) / 2;
        }
    }

    public static class TabLayoutOnPageChangeListener implements ViewPager.OnPageChangeListener {

        private final WeakReference<BottomTabLayout> mTablayoutRef;

        public TabLayoutOnPageChangeListener(BottomTabLayout tabLayout) {
            mTablayoutRef = new WeakReference<>(tabLayout);
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            BottomTabLayout tabLayout = mTablayoutRef.get();
            if (tabLayout == null) return;
            tabLayout.offsetTab(position, positionOffset, positionOffsetPixels);
            tabLayout.offsetIndex(position, positionOffset, true);

        }

        @Override
        public void onPageSelected(int position) {
            BottomTabLayout tabLayout = mTablayoutRef.get();
            if (tabLayout == null) return;
            tabLayout.selectTab(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }
    }
}
