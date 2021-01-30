package ru.geekbrains.java2.lesson1;

/**
 * Сущность класса Беговая дорожка.
 *
 * @author Kristina Retivykh
 */
public class Treadmill {
    private int distance;

    /**
     * Создает {@link Treadmill}
     *
     * @param distance
     *        длина забега
     */
    public Treadmill(int distance) {
        this.distance = distance;
    }

    /**
     * Возвращает дистанцию забега на беговой дорожке.
     *
     * @return дистанция забега на беговой дорожке.
     */
    public int getDistance() {
        return distance;
    }
}
