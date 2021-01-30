package ru.geekbrains.java2.lesson1;

/**
 * Сущность класса Стена.
 *
 * @author Kristina Retivykh
 */
public class Wall {
    private int height;

    /**
     * Создаёт {@link Wall}.
     *
     * @param height
     *        высота стены.
     */
    public Wall(int height) {
        this.height = height;
    }

    /**
     * Возвращает высоту стены.
     * @return высота стены.
     */
    public int getHeight() {
        return height;
    }
}
