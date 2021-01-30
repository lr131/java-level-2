package ru.geekbrains.java2.lesson1;

/**
 * Интерфейс для взаимодействия с беговой дорожкой.
 *
 * @author Kristina Retivykh
 */
public interface Runnable {

    /**
     * Возвращает, может ли существо пробежать заданную дистанцию.
     *
     * @param distance
     *        дистанция для бега.
     * @return {@code true} может пробежать.
     *         {@code false} не может пробежать.
     */
    boolean canRun(int distance);

    /**
     * Возвращает, может ли существо пробежать заданную дистанцию.
     *
     * @param treadmill
     *        беговая дорожка.
     * @return {@code true} может пробежать.
     *         {@code false} не может пробежать.
     */
    default boolean canRun(Treadmill treadmill) {
        return canRun(treadmill.getDistance());
    }

    /**
     * Логгирует бег существа.
     *
     * @param treadmill
     *        беговая дорожка.
     * @param name
     *        имя существа.
     */
    default void run(Treadmill treadmill, String name) {
        if (canRun(treadmill)) {
            System.out.printf("%s run success.%n", name);
        } else {
            System.out.printf("%s run failure.(Treadmill l = %d)%n", name,
                    treadmill.getDistance());
        }
    }
}
