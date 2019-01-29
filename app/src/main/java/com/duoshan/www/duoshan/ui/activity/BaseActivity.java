package com.duoshan.www.duoshan.ui.activity;

import com.duoshan.www.lib_common.ui.activity.MyActivity;
import com.duoshan.www.lib_common.viewmodel.MyViewModel;

import butterknife.ButterKnife;


/**
 * author:  zhouchaoxiang
 * date:    2019/1/28
 * explain:
 */
public abstract class BaseActivity<VM extends MyViewModel> extends MyActivity<VM> {


    @Override
    protected void initAfter() {
//        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
