package com.duoshan.www.duoshan;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * author:  zhouchaoxiang
 * date:    2019/2/11
 * explain:
 */
public class MessageQueue {
    Message[] items;
    //入队出队索引
    private int putIndex;
    private int takeIndex;
    //计数器
    int count;
    //互斥锁
    private Lock mLock;
    private Condition notEmpty;
    private Condition notFull;

    public MessageQueue() {
        //队列大小
        items = new Message[50];
        mLock = new ReentrantLock();
        notEmpty = mLock.newCondition();
        notFull = mLock.newCondition();
    }

    public void enqueueMessage(Message msg) {
        try {
            mLock.lock();
            while (count == items.length) {
                try {
                    notFull.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            items[putIndex] = msg;
            //循环取值
            putIndex = (++putIndex == items.length) ? 0 : putIndex;
            count++;
            notEmpty.signalAll();
        } finally {
            mLock.unlock();
        }
    }

    public Message next() {
        Message msg = null;
        try {
            mLock.lock();
            while (count == 0) {
                try {
                    notEmpty.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            msg = items[takeIndex];
            items[takeIndex] = null;
            takeIndex = (++takeIndex == items.length) ? 0 : takeIndex;
            count--;
            notFull.signalAll();
        } finally {
            mLock.unlock();
        }
        return msg;
    }
}
