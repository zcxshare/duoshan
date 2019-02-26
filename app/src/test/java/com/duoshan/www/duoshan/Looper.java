package com.duoshan.www.duoshan;


/**
 * author:  zhouchaoxiang
 * date:    2019/2/11
 * explain:
 */
public final class Looper {
    static final ThreadLocal<Looper> sThreadLocal = new ThreadLocal<>();
    //一个消息队列对应一个消息队列
    MessageQueue mQueue;

    /**
     * looper初始化
     */
    public static void prepare() {
        if (sThreadLocal.get() != null) {
            throw new RuntimeException("Only one Looper may be created per thread");
        }
        sThreadLocal.set(new Looper());
    }

    public static Looper myLooper() {
        return sThreadLocal.get();
    }

    public static void loop() {
        Looper looper = myLooper();
        if (looper == null) {
            throw new RuntimeException("No Looper");
        }
        MessageQueue queue = looper.mQueue;
        for (; ; ) {
            Message msg = queue.next();
            if (msg == null) {
                continue;
            }
            //转发
            msg.target.dispathMessage(msg);
        }
    }
}
