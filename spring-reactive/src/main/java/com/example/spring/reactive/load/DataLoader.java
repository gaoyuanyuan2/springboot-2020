package com.example.spring.reactive.load;

import java.util.concurrent.TimeUnit;

public class DataLoader {
    public final void load() {
        long startTime = System.currentTimeMillis(); //开始时间
        doLoad(); //具体执行
        long costTime = System.currentTimeMillis() - startTime; //消耗时间
        System.out.println(" load()总耗时: " + costTime + "亳秒");
    }

    protected void doLoad() {
        loadConfigurations();
        loadUsers();
        loadOrders();
    }

    public void loadConfigurations() {
        try {
            TimeUnit.SECONDS.sleep(1);
            System.out.println(" [线程" + Thread.currentThread().getName() + "] loadConfigurations");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void loadUsers() {
        try {
            TimeUnit.SECONDS.sleep(2);
            System.out.println(" [线程" + Thread.currentThread().getName() + "] loadUsers");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void loadOrders() {
        try {
            System.out.println(" [线程" + Thread.currentThread().getName() + "] loadOrders");
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
