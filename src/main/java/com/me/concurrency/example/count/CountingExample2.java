package com.me.concurrency.example.count;

import com.me.concurrency.annotation.ThreadNotSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@ThreadNotSafe
public class CountingExample2 {

    //请求总数
    public static int clientTotal = 10000;
    //并发数
    public static int threadTotal = 50;

    /*
    主要通过CAS实现
    public final int getAndAddInt(Object var1, long var2, int var4) {
        int var5;
        do {
            //var1: AtomicInteger对象
            //var2: AtomicInteger对象的Integer的值
            //var4: 1 (increase --> +1)
            //从底层获取对象var1原来的值
            var5 = this.getIntVolatile(var1, var2); //c语言实现
            //如果var2和var5相等，则var1对象的值更新为var5 + var4，否则更新var2和var5的值(&var2=...)，继续做比较
        } while(!this.compareAndSwapInt(var1, var2, var5, var5 + var4));
        return var5;
    }
     */
    private static AtomicInteger count = new AtomicInteger(0);

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
        count.incrementAndGet();
        //count.getAndIncrement();
    }
}
