package ru.geekbrains.java2.lesson5;

import java.util.concurrent.TimeUnit;

public class Example1 {
    public static void main(String[] args) {
        Thread open = new Thread(() -> {
            while (true) {
                System.out.print("[");
                try {
                    TimeUnit.MILLISECONDS.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread close = new Thread(() -> {
            while (true) {
                System.out.print("]");
                try {
                    TimeUnit.MILLISECONDS.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        open.start();
        close.start();
    }
}
