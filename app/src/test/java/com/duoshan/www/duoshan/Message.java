package com.duoshan.www.duoshan;
/**
 * author:  zhouchaoxiang
 * date:    2019/2/11
 * explain: 
 */
public    class Message   {
    Handler target;
    public int what;
    public Object obj;

    @Override
    public String toString() {
        return obj.toString();
    }
}
