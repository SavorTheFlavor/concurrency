package com.me.concurrency.example.atomic;

import com.me.concurrency.annotation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by Administrator on 2018/8/11.
 */
@Slf4j
@ThreadSafe
public class AtomicReferenceExample {

    //An object reference that may be updated atomically.
    private static AtomicReference<Integer> count = new AtomicReference<>(0);

    public static void main(String[] args) {
        count.compareAndSet(0,2);
        count.compareAndSet(1,3);
        count.compareAndSet(2,5);
        count.compareAndSet(5,222);
        log.info("count:{}", count.get());
    }

}
