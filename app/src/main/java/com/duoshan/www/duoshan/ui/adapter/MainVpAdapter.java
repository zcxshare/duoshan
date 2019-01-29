package com.duoshan.www.duoshan.ui.adapter;

import com.duoshan.www.lib_common.ui.adapter.MyFragmentPagerAdapter;
import com.duoshan.www.lib_common.ui.fragment.MyFragment;

import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

/**
 * author:  zhouchaoxiang
 * date:    2019/1/26
 * explain:
 */
public class MainVpAdapter extends MyFragmentPagerAdapter {

    private final List<MyFragment> mFragments;

    public MainVpAdapter(FragmentManager fm, List<MyFragment> fragments) {
        super(fm);
        mFragments = fragments;
    }

    @Override
    public int getCount() {

        return mFragments == null ? 0 : mFragments.size();
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

}
