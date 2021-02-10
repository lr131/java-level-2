package ru.geekbrains.java2.lesson5;

import java.util.Arrays;

public class Main {
    private static final int SIZE = 10000000;
    private static final int HALF_SIZE = SIZE / 2;

    public static void main(String[] args) {
        runSimple();
        runThreads();
    }

    /**
     * Первый метод просто бежит по массиву и вычисляет значения.
     */
    private static void runSimple() {
        float[] array = new float[SIZE];
        Arrays.fill(array, 1.0f);

        long start = System.currentTimeMillis();
        fillArray(array);
        long end = System.currentTimeMillis();
        System.out.println("Method 1 work time: " + (end - start));
    }

    /**
     * Второй разбивает массив на два массива,
     * в двух потоках высчитывает новые значения
     * и потом склеивает эти массивы обратно в один.
     */
    private static void runThreads() {
        float[] mainArray = new float[SIZE];
        Arrays.fill(mainArray, 1.0f);

        long start = System.currentTimeMillis();

        float[] arrayStartHalf = new float[HALF_SIZE];
        float[] arrayEndHalf = new float[HALF_SIZE];

        System.arraycopy(mainArray, 0, arrayStartHalf, 0, HALF_SIZE);
        System.arraycopy(mainArray, HALF_SIZE, arrayEndHalf,0, HALF_SIZE);

        Thread thread1 = new Thread(() -> {
            fillArray(arrayStartHalf);
        });

        Thread thread2 = new Thread(() -> {
            fillArray(arrayEndHalf);
        });

        thread1.start();
        thread2.start();

        boolean alive;
        //пока жив хоть один поток, не склеивать
        do {
            alive = thread1.isAlive() || thread2.isAlive();
        } while (alive);

        System.arraycopy(arrayStartHalf, 0, mainArray, 0, HALF_SIZE);
        System.arraycopy(arrayEndHalf, 0, mainArray, HALF_SIZE, HALF_SIZE);

        long end = System.currentTimeMillis();
        System.out.println("Method 2 work time: " + (end - start));
    }

    /**
     * Заполняет массив значениями по формуле.
     *
     * @param array
     *        массив, значения которого нужно рассчитать.
     * @return массив с рассчитанными значениями.
     */
    private static float[] fillArray(float[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = (float)(array[i] *
                               Math.sin(0.2f + i / 5) *
                               Math.cos(0.2f + i / 5) *
                               Math.cos(0.4f + i / 2));
        }
        return array;
    }
}
