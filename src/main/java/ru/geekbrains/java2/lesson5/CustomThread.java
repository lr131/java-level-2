package ru.geekbrains.java2.lesson5;

import java.util.concurrent.TimeUnit;

public class CustomThread extends Thread {
    private int num;
    private boolean running;

    public CustomThread(int num) {
        this.num = num;
        running = true;
    }

    public void stopThread(){
        running = false;
    }

    @Override
    public void run() {
        while (running) {
            System.out.println(num);
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //синхронизация на уровне экземпляра класса
    public synchronized void foo() {
        System.out.println("*");
    }
}
