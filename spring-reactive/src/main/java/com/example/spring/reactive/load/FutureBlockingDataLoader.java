package com.example.spring.reactive.load;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureBlockingDataLoader extends DataLoader {
    protected void doLoad() { // 并行计算
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        //创建线程池
        runCompletely(executorService.submit(super::loadConfigurations));//耗时>=1s
        runCompletely(executorService.submit(super::loadUsers));//耗时>=2s
        runCompletely(executorService.submit(super::loadOrders));//耗时>=3s

        executorService.shutdown();
    }

    private void runCompletely(Future<?> future) {
        try {
            future.get();
        } catch (Exception e) {

        }
    }

    public static void main(String[] args) {
        new FutureBlockingDataLoader().load();
    }
}
