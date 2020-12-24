package com.example.spring.reactive.load;

import java.util.concurrent.CompletableFuture;

public class ChainDataLoader extends DataLoader {

    protected void doLoad() {
        CompletableFuture
                .runAsync(super::loadConfigurations)
                .thenRun(super::loadUsers)
                .thenRun(super::loadOrders)
                .whenComplete((result, throwable) -> { //完成时回调
                    System.out.println(" [线程" + Thread.currentThread().getName() + "]加载完成");
                }).join(); //等待完成
    }

    public static void main(String[] args) {
        new ChainDataLoader().load();
    }

}
