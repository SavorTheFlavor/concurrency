package com.me.concurrency.example.singleton;

import com.me.concurrency.annotation.Recommended;
import com.me.concurrency.annotation.ThreadSafe;

/**
 * 枚举模式：最安全
 */
@ThreadSafe
@Recommended
public class SingletonUsingEnum {

    // 私有构造函数
    private SingletonUsingEnum() {

    }

    public static SingletonUsingEnum getInstance() {
        return Singleton.INSTANCE.getInstance();
    }

    private enum Singleton {
        INSTANCE;

        private SingletonUsingEnum singleton;

        // JVM保证这个方法绝对只调用一次
        Singleton() {
            singleton = new SingletonUsingEnum();
        }

        public SingletonUsingEnum getInstance() {
            return singleton;
        }
    }
}
