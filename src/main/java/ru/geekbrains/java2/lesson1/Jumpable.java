package ru.geekbrains.java2.lesson1;

/**
 * Интерфейс для взаимодействия со стеной.
 *
 * @author Kristina Retivykh
 */
public interface Jumpable {

    /**
     * Возвращает, может ли существо запрыгнуть на заданную высоту.
     *
     * @param distance
     *        высота стены.
     * @return {@code true} может перепрыгнуть.
     *         {@code false} не может перепрыгнуть.
     */
    boolean canJump(int distance);

    /**
     * Возвращает, может ли существо запрыгнуть на заданную высоту.
     *
     * @param wall
     *        стена.
     * @return {@code true} может перепрыгнуть.
     *         {@code false} не может перепрыгнуть.
     */
    default boolean canJump(Wall wall) {
        return canJump(wall.getHeight());
    }

    /**
     * Логгирует прыжок существа.
     *
     * @param wall
     *        стена.
     * @param name
     *        имя существа.
     */
    default void jump(Wall wall, String name) {
        if (canJump(wall)) {
            System.out.printf("%s jump success.%n", name);
        } else {
            System.out.printf("%s jump failure. (Wall h = %d)%n", name,
                    wall.getHeight());
        }
    }
}
