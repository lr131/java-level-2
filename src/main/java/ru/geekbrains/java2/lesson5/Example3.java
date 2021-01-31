package ru.geekbrains.java2.lesson5;

public class Example3 {
    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            new CustomThread(i).start();
        }
    }
}
