package com.duoshan.www.duoshan;


import java.util.UUID;

/**
 * author:  zhouchaoxiang
 * date:    2019/2/12
 * explain: 
 */
public    class HandlerTest   {
    public static void main(String[] args) {
        Looper.prepare();
        Handler handler = new Handler(){
            @Override
            public void handlerMessage(Message msg) {
                System.out.println(Thread.currentThread().getName()+"---"+msg.obj);
            }
        };
        for (int i = 0; i < 10; i++) {
            new Thread(){
                @Override
                public void run() {
                    while (true) {
                        Message message = new Message();
                        message.what = 1;
                        synchronized (UUID.class) {
                            message.obj = Thread.currentThread().getName() + " send Message :" + UUID.randomUUID();
                        }
                        System.out.println("send:"+message);
                        handler.sendMessage(message);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }.start();
        }
        Looper.loop();
    }
}
