package com.duoshan.www.duoshan;

/**
 * author:  zhouchaoxiang
 * date:    2019/2/11
 * explain:
 */
public class Handler {
    MessageQueue mQueue;
    private final Looper mLooper;

    /**
     * 主线程里面创建
     */
    public Handler() {
        mLooper = Looper.myLooper();
        mQueue = mLooper.mQueue;
    }

    /**
     * 发送消息
     */
    public void sendMessage(Message msg) {
        msg.target = this;
        mQueue.enqueueMessage(msg);
    }

    public void dispathMessage(Message msg) {
        handlerMessage(msg);
    }

    public void handlerMessage(Message msg) {
    }
}
