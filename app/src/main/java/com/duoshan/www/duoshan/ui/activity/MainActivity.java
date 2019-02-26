package com.duoshan.www.duoshan.ui.activity;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.duoshan.www.duoshan.R;
import com.duoshan.www.duoshan.ui.adapter.MainVpAdapter;
import com.duoshan.www.duoshan.ui.fragment.MessageFragment;
import com.duoshan.www.duoshan.ui.fragment.WorldFragment;
import com.duoshan.www.duoshan.viewmodel.MainViewModel;
import com.duoshan.www.lib_common.ui.fragment.MyFragment;
import com.duoshan.www.lib_common.ui.widget.BottomTabLayout;
import com.duoshan.www.lib_common.ui.widget.TabView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

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
    TabView mTagMsg;
    @BindView(R.id.aciv_center)
    TabView mAcivCenter;
    @BindView(R.id.tag_world)
    TabView mTagWorld;
    @BindView(R.id.btb_bottom_bar)
    BottomTabLayout mBtbBottomBar;
    @BindView(R.id.cl_home)
    FrameLayout mClHome;
    private List<MyFragment> mFragments;
    private float mScale0;
    private float mScale2;
    private Drawable mBottomRingDrawable;

    @Override
    protected void initAfter() {
        Window window = getWindow();
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(0x00000000);  // transparent
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            int flags = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            window.addFlags(flags);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        initBottom();
        initVp();
    }

    private void initBottom() {
        mBtbBottomBar.initManyFlash(mVpHome);
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
        WorldFragment f3 = new WorldFragment();
//        f3.getView().setBackgroundResource(R.color.blue);
        mFragments.add(f3);
        MainVpAdapter mainVpAdapter = new MainVpAdapter(getSupportFragmentManager(), mFragments);
        mVpHome.setAdapter(mainVpAdapter);
    }

    private void initScale() {
    }

    @Override
    protected void initEvent() {
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
