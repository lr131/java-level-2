package ru.geekbrains.java2.lesson5;

import java.util.concurrent.TimeUnit;

public class Example2 {
    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[4];
        Object monitor  = new Object();
        long start = System.currentTimeMillis();

        for (int i = 0; i < 4; i++) {
            threads[i] = new Thread(new IncrementTask(monitor));
            System.out.println(threads[i].getName() + " " + threads[i].getState());
            threads[i].start();
//            threads[i].join(); //ожидает, пока поток закончится
            System.out.println(threads[i].getName() + " " + threads[i].getState());
        }
        boolean alive = true;
        while (alive) {
            boolean live = false;
            for (Thread thread: threads) {
                //логическое или, такая запись
                live |= thread.isAlive();
                System.out.println(thread.getName() + " " +thread.getState());
                TimeUnit.MILLISECONDS.sleep(150);
            }
            alive = live;
        }
        long end = System.currentTimeMillis();
        System.out.println(IncrementTask.cnt.get() + "\ntime: " + (end - start));

//        //Не в потоках
//        long start2 = System.currentTimeMillis();
//        long sum = 0;
//        for (int i = 0; i < 40000000; i++) {
//            sum++;
//        }
//        long end2 = System.currentTimeMillis();
//        System.out.println(sum + "\ntime: " + (end2 - start2));
    }
}
