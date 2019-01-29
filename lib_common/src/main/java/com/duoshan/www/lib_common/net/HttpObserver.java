package com.duoshan.www.lib_common.net;

import com.duoshan.www.lib_common.net.error.HttpErrorBean;
import com.duoshan.www.lib_common.net.error.HttpErrorHandle;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/**
 * author:  zhouchaoxiang
 * date:    2018/4/19
 * explain:
 */
public abstract class HttpObserver<RESPONCE> implements Observer<RESPONCE> {

    private String text;
    private boolean isShowDialog;
//    private InitPresenter mPresenter;

//    public HttpObserver(InitPresenter presenter, String text) {
//        this(presenter, true, text);
//    }

//    public HttpObserver(InitPresenter presenter, boolean isShowDialog, String text) {
//        this.isShowDialog = isShowDialog;
//        mPresenter = presenter;
//        this.text = text;
//    }

    @Override
    public void onSubscribe(final Disposable d) {
//        if (isShowDialog) {
//            Dialog dialog = DialogManager.showLoadingDialog(text);
//            dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
//                @Override
//                public void onDismiss(DialogInterface dialog) {
//                    d.dispose();
//                }
//            });
//        }
        SubscriptionManager.getInstance().add(d);
//        if (null != mPresenter) {
//            mPresenter.addDisposable(d);
//        }
    }

    @Override
    public void onNext(RESPONCE value) {
        if (null == value) {
            onFail(new HttpErrorBean("结果为空", 0, System.currentTimeMillis()));
            return;
        }
        onSuccess(value);
    }

    public abstract void onSuccess(RESPONCE value);

    @Override
    public void onError(Throwable e) {
        HttpErrorBean errorBean = HttpErrorHandle.handleException(e);
        onFail(errorBean);
    }

    public abstract void onFail(HttpErrorBean bean);

    @Override
    public void onComplete() {
//        if (isShowDialog)
//            DialogManager.hideLoadingDialog();
//        long endTime = System.currentTimeMillis();
//        MyToast.showCenterToastShort("用时：" + (endTime - mStartTime) + " ms");
//        onCompleted();
    }
}
