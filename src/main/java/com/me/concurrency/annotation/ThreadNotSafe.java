package com.me.concurrency.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 只是用来标记是一个线程不安全的类
 */
// TYPE: Class, interface (including annotation type), or enum declaration
@Target(ElementType.TYPE)
/*
 SOURCE: Annotations are to be discarded by the compiler.
 CLASS: Annotations are to be recorded in the class file by the compiler
    but need not be retained by the VM at run time.  This is the default behavior
 RUNTIME: Annotations are to be recorded in the class file by the compiler and retained by the VM at run time, so they may be read reflectively.
 */
@Retention(RetentionPolicy.SOURCE)
public @interface ThreadNotSafe {
    String value() default "";
}
