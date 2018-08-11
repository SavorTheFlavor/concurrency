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
public class VolatileExample {

    //请求总数
    public static int clientTotal = 10000;
    //并发数
    public static int threadTotal = 50;

    private static volatile int count = 0;


    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for(int i=0; i<clientTotal; i++){
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    add();
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

    private static void add() {
        count++;
        // three steps
        // 1.read count
        // 2. +1
        // 3.write count
        // so volatile is not atomic
        // 对于volatile修饰的变量，jvm虚拟机只是保证从主内存加载到线程工作内存的值是最新的
    }
}
