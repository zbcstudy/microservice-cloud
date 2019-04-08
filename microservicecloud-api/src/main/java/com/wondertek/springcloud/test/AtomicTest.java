package com.wondertek.springcloud.test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * Created by win on 2019/4/8.
 */
public class AtomicTest {

    private static AtomicInteger atomicInteger = new AtomicInteger(100);
    private static AtomicStampedReference atomicStampedReference = new AtomicStampedReference(100, 1);

    public static void main(String[] args) {

        //atomicInteger
        Thread thread1 = new Thread(()->{
            atomicInteger.compareAndSet(100, 110);
            atomicInteger.compareAndSet(110, 100);
        });

        Thread thread2 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("atomicInteger:" + atomicInteger.compareAndSet(100, 120));
        });
        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //atomicStampedReference
        Thread thread3 = new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int stamp = atomicStampedReference.getStamp();
            atomicStampedReference.compareAndSet(100, 110, stamp, stamp + 1);
            atomicStampedReference.compareAndSet(110, 100, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);

        });

        Thread thread4 = new Thread(()->{
            int stamp = atomicStampedReference.getStamp();
            System.out.println("stamp: " + stamp);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("atomicStampedReference: " + atomicStampedReference.compareAndSet(100, 120, stamp, stamp + 1));
        });

        thread3.start();
        thread4.start();
    }

}
