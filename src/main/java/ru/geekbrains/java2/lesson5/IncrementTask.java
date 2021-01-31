package ru.geekbrains.java2.lesson5;

import java.util.concurrent.atomic.AtomicInteger;

public class IncrementTask implements Runnable {

    public static int counter = 0;

    public static AtomicInteger cnt = new AtomicInteger(0);

    private final Object monitor;

    public IncrementTask(Object monitor) {
        this.monitor = monitor;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000000; i++) {
            synchronized (monitor){
                counter++;
            }
            cnt.incrementAndGet();
        }
    }
}
