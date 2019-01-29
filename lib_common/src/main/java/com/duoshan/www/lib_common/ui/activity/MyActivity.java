package com.duoshan.www.lib_common.ui.activity;

import android.os.Bundle;

import com.duoshan.www.lib_common.viewmodel.MyViewModel;

import java.lang.reflect.ParameterizedType;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

/**
 * author:  zhouchaoxiang
 * date:    2019/1/22
 * explain:
 */
public abstract class MyActivity<VM extends MyViewModel> extends AppCompatActivity {

    private VM mViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        initBefore(savedInstanceState);
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initAfter();
        mViewModel = ViewModelProviders.of(this).get((Class<VM>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
        initView();
        initEvent();
    }

    protected void initAfter() {

    }

    private void initBefore(Bundle savedInstanceState) {
    }

    protected abstract int getLayoutId();

    protected abstract void initView();

    protected abstract void initEvent();


    public VM getViewModel() {
        return mViewModel;
    }
}
