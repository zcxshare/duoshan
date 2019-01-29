package com.duoshan.www.duoshan.ui.activity;

import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;

import com.duoshan.www.duoshan.R;
import com.duoshan.www.duoshan.ui.adapter.MainVpAdapter;
import com.duoshan.www.duoshan.ui.fragment.MessageFragment;
import com.duoshan.www.duoshan.viewmodel.MainViewModel;
import com.duoshan.www.lib_common.ui.fragment.MyFragment;
import com.duoshan.www.lib_common.ui.widget.BottomTagBar;
import com.duoshan.www.lib_common.ui.widget.TagView;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity<MainViewModel> implements MessageFragment.OnFragmentInteractionListener {
    private static final String TAG = "MainActivity";
    public static final String FRAGMENT_MSG = "message_fragment";
    private static final String FRAGMENT_WORLD = "message2_fragment";

    @BindView(R.id.vp_home)
    ViewPager mVpHome;
    @BindView(R.id.tag_msg)
    TagView mTagMsg;
    @BindView(R.id.aciv_center)
    AppCompatImageView mAcivCenter;
    @BindView(R.id.tag_world)
    TagView mTagWorld;
    @BindView(R.id.btb_bottom_bar)
    BottomTagBar mBtbBottomBar;
    @BindView(R.id.cl_home)
    FrameLayout mClHome;
    private List<MyFragment> mFragments;
    private float mScale0;
    private float mScale2;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        initVp();
    }

    private void initVp() {
        initScale();
        mFragments = new ArrayList<>(3);
        MessageFragment f1 = new MessageFragment();
//        f1.getView().setBackgroundResource(R.color.colorAccent);
        mFragments.add(f1);
        MessageFragment f2 = new MessageFragment();
//        f2.getView().setBackgroundResource(R.color.yellow);
        mFragments.add(f2);
        MessageFragment f3 = new MessageFragment();
//        f3.getView().setBackgroundResource(R.color.blue);
        mFragments.add(f3);
        MainVpAdapter mainVpAdapter = new MainVpAdapter(getSupportFragmentManager(), mFragments);
        mVpHome.setAdapter(mainVpAdapter);
    }

    private void initScale() {
        getWindow().getDecorView().getViewTreeObserver().addOnGlobalLayoutListener(() -> {
            int Vpwidth = mVpHome.getWidth();
            int width = mBtbBottomBar.getChildAt(0).getWidth();
            mScale0 = Math.round((width / 3f)*10 / Vpwidth)/10f;
//        width = mBtbBottomBar.getChildAt(1).getWidth();
            width = mBtbBottomBar.getChildAt(2).getWidth();
            mScale2 = Math.round((width / 3f)*10 / Vpwidth)/10f;

        });
    }

    @Override
    protected void initEvent() {
        mVpHome.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            private int mLastPixel;

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.i(TAG, "onPageScrolled: position:" + position + "---positionOffset:" + positionOffset + "---positionOffsetPixels:" + positionOffsetPixels);
                if (positionOffsetPixels == 0) return;
                int dif = positionOffsetPixels - mLastPixel;
                int Vpwidth = mVpHome.getWidth()*3;
                int width = mBtbBottomBar.getChildAt(0).getWidth();
                int div = Math.round(dif * width/Vpwidth);
                mBtbBottomBar.setChangeChildLayout(0,div,0,div,0 );
                mBtbBottomBar.setChangeChildLayout(2,-div,0,-div,0 );
//                mBtbBottomBar.setChangeChildLayout(1, dif, 0, dif, 0);
                mLastPixel = positionOffsetPixels;
            }

            @Override
            public void onPageSelected(int position) {
                Log.i(TAG, "onPageSelected: position:" + position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Log.i(TAG, "onPageScrollStateChanged: state:" + state);

            }
        });
    }

    @OnClick({R.id.tag_msg, R.id.aciv_center, R.id.tag_world})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tag_msg:
                break;
            case R.id.aciv_center:
                break;
            case R.id.tag_world:
                break;
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
