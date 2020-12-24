package com.example.spring.web.servlet.controller;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Random;
import java.util.concurrent.*;

@RestController
@EnableScheduling
public class HelloWorldAsyncController {

    private final BlockingQueue<DeferredResult<String>> queue =
            new ArrayBlockingQueue<DeferredResult<String>>(5);

    private final Random random = new Random();

    @Scheduled(fixedDelay = 5000)
    public void process() throws InterruptedException {
        DeferredResult<String> result = null;
        do {
            result = queue.take();
            long timeout = random.nextInt(100);
            //模拟等待时间，RPC或者DB查询
            TimeUnit.MILLISECONDS.sleep(timeout);
            result.setResult("Hello World!");
            println("执行计算结果，消耗: " + timeout + " ms。");
        } while (result != null);
    }

    // 异步操作
    @GetMapping("/callable-hello-world")
    public Callable<String> callableHelloWorld() {
        final long startTime = System.currentTimeMillis();
        println("Hello World!");
        return () -> {
            long costTime = System.currentTimeMillis() - startTime;
            println("执行计算结果，消耗:" + costTime + "ms.");
            return "Hello World!";
        };
    }

    // 异步操作
    @GetMapping("/completion-stage")
    public CompletionStage<String> completionStage() {
        final long startTime = System.currentTimeMillis();
        println("Hello World!");
        return  CompletableFuture.supplyAsync(()->{
            long costTime = System.currentTimeMillis() - startTime;
            println("执行计算结果，消耗:" + costTime + "ms.");
            return "Hello World!";
        });
    }

    // 异步操作
    @GetMapping("/hello-world")
    public DeferredResult<String> helloWorld() {
        DeferredResult<String> result = new DeferredResult<>(50L);// 时间不准确
//        result.setResult("Hello World!");
        println("Hello World!");
        queue.offer(result);
        result.onCompletion(() -> {
            println("执行结束");
//            System.out.println("HelloWorldAsyncController执行结束");
        });
        result.onTimeout(() -> {
            println("执行超时");
        });
        return result;
    }

    private static void println(Object object) {
        String name = Thread.currentThread().getName();
        System.out.println("HelloWorldAsyncController[" + name + "]:" + object);
    }
}
