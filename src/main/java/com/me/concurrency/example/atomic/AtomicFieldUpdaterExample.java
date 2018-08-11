package com.me.concurrency.example.atomic;

import com.me.concurrency.annotation.ThreadSafe;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * Created by Administrator on 2018/8/11.
 */
@Slf4j
@ThreadSafe
public class AtomicFieldUpdaterExample {

    //原子性更新AtomicFieldUpdaterExample类的count字段
    private static AtomicIntegerFieldUpdater<AtomicFieldUpdaterExample> fieldUpdater =
            AtomicIntegerFieldUpdater.newUpdater(AtomicFieldUpdaterExample.class, "count");

    @Getter // get()
    public volatile int count = 1000;

    public static void main(String[] args) {
        AtomicFieldUpdaterExample example = new AtomicFieldUpdaterExample();
        fieldUpdater.getAndIncrement(example);
        fieldUpdater.getAndIncrement(example);
        fieldUpdater.getAndIncrement(example);
        fieldUpdater.getAndIncrement(example);
        log.info("count:{}", example.getCount());
    }

}
