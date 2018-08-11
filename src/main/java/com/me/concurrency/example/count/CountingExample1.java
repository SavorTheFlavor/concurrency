package com.me.concurrency.example.count;

import com.me.concurrency.annotation.ThreadNotSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
@ThreadNotSafe
public class CountingExample1 {

    //请求总数
    public static int clientTotal = 10000;
    //并发数
    public static int threadTotal = 50;

    private static int count = 0;

    //可以用来保证让代码只被执行一次
    private static AtomicBoolean isExecuted = new AtomicBoolean(false);

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for(int i=0; i<clientTotal; i++){
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    add();
                    onlyOnce();
                    semaphore.release();
                }catch (Exception e){
                    log.error(e.getMessage());
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("all tasks finished!!!!!!!!!!");
        log.debug("the final count value is " + count);
    }

    private static void onlyOnce() {
        if(isExecuted.compareAndSet(false, true)){
            log.info("once task has been executed!");
        }
    }

    private static void add() {
        count++;
    }
}
