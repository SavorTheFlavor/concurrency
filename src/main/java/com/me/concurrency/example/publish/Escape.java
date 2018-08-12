package com.me.concurrency.example.publish;

import com.me.concurrency.annotation.NotRecommended;
import com.me.concurrency.annotation.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NotThreadSafe
@NotRecommended
public class Escape {

    private int thisCanBeEscape = 0;

    public Escape () {
        new InnerClass();
    }

    private class InnerClass {
        //在Escape对象还没初始化完成的时候引用该对象
        public InnerClass() {
            log.info("{}", Escape.this.thisCanBeEscape);
        }
    }

    public static void main(String[] args) {
        new Escape();
    }
}
