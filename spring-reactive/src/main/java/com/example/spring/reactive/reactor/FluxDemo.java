package com.example.spring.reactive.reactor;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.TimeUnit;

public class FluxDemo {
    public static void main(String[] args) throws InterruptedException {
        printIn("运行！");
        Flux.just("A", "B", "C") //发布A->B->C
                .publishOn(Schedulers.elastic())// 线程池切换
                .map(value -> "+" + value) //转换
//                .subscribe(
//                        FluxDemo::printIn,//数据消费 = onNext(T)
//                        FluxDemo::printIn,//异常处理 = onError(Throwable)
//                        () -> {
//                            printIn("完成操作！");
//                        },//完成回调 = onComplete
//                        subscription -> {
//                            subscription.request(Integer.MAX_VALUE);// n
//                           请求元素的数量
//                            subscription.cancel();//取消 上游传输数据到下游
//                        }//被压 = onSubscribe(Subscription)
//                )//执行
                .subscribe(new Subscriber<String>() {
                    private Subscription subscription;
                    private int count = 0;

                    @Override
                    public void onSubscribe(Subscription s) {
                        subscription = s;
                        subscription.request(1);
                    }

                    @Override
                    public void onNext(String s) {
                        if (count == 2) {
                            throw new RuntimeException("故意抛异常! ");
                        }
                        printIn(s);
                        count++;
                        subscription.request(1);
                    }

                    @Override
                    public void onError(Throwable t) {
                        printIn(t.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        printIn("完成操作！");
                    }
                })
        ;
        TimeUnit.SECONDS.sleep(1);
    }

    private static void printIn(Object object) {
        String threadName = Thread.currentThread().getName();
        System.out.println(" [线程:" + threadName + "]" + object);
    }
}
