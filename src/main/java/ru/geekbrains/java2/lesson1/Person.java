package ru.geekbrains.java2.lesson1;

/**
 * Сущность класса Человек.
 *
 * @author Kristina Retivykh
 */
public class Person implements Runnable, Jumpable {
    private static final int DEFAULTMAXRUN = 150;
    private static final int DEFAULTMAXJUMP = 25;

    private String name;
    private int maxDistanceJump;
    private int maxDistanceRun;
    private String resultSprint;

    /**
     * Создаёт {@link Person}.
     * Значения для максимальной дистанции бега и максимальной высоты прыжка
     * установлены по-умолчанию.
     *
     * @param name
     *        имя человека.
     */
    public Person(String name) {
        this.name = name;
        this.maxDistanceJump = DEFAULTMAXRUN;
        this.maxDistanceRun = DEFAULTMAXJUMP;
    }

    /**
     * Создаёт {@link Person}.
     *
     * @param name
     *        имя человека
     * @param maxRun
     *        максимальная дистанция, которую может пробежать человек
     * @param maxJump
     *        максимальная высота, на которую челвоек может запрыгнуть
     */
    public Person(String name, int maxRun, int maxJump) {
        this.name = name;
        setMaxDistanceRun(maxRun);
        setMaxDistanceJump(maxJump);
    }

    /**
     * Получает имя человека.
     *
     * @return имя человека.
     */
    public String getName () {
    return name;
    }

    /**
     * Получает максимально допустимую дистанцию пробежки человека.
     *
     * @return максимально допустимая дистанция пробежки человека.
     */
    public int getMaxDistanceRun () {
        return maxDistanceRun;
    }

    /**
     * Устанавливает максимально допустимую дистанцию бега человека.
     *
     * @param maxDistanceRun
     *        максимально допустимая дистанция бега человека.
     */
    public void setMaxDistanceRun ( int maxDistanceRun){
        if (maxDistanceRun < 0) {
            System.out.println("Value must be positive or zero.");
        } else {
            this.maxDistanceRun = maxDistanceRun;
        }
    }

    /**
     * Получает максимально допустимую высоту прыжка человека.
     *
     * @return максимально допустимая высота прыжка человека.
     */
    public int getMaxDistanceJump () {
        return maxDistanceJump;
    }

    /**
     * Устанавливает максимально допустимую высоту прыжка человека.
     *
     * @param maxDistanceJump
     *        максимально допустимая высота прыжка человека.
     */
    public void setMaxDistanceJump ( int maxDistanceJump){
        if (maxDistanceJump < 0) {
            System.out.println("Value must be positive or zero.");
        } else {
            this.maxDistanceJump = maxDistanceJump;
        }
    }

    /**
     * Получает результат прохождения полосы препятствий.
     *
     * @return resultSprint
     *         результат прохождения полосы препятствий
     */
    public String getResultSprint() {
        return resultSprint;
    }

    /**
     * Устанавливает результат прохождения полосы препятствий.
     *
     * @param resultSprint
     *        результат прохождения полосы препятствий
     */
    public void setResultSprint(String resultSprint) {
        this.resultSprint = resultSprint;
    }

    /**
     * Выводит результат прохождения полосы препятствий в консоль.
     */
    public void printResultSprint(){
        System.out.println(String.format("Person named %s. It has %s sprint. " +
                        "It max distance is %d and max jump is %d", getName(),
                getResultSprint(), getMaxDistanceRun(), getMaxDistanceJump()));
    }

    /**
     * Возвращает, преодолимо ли препятствие.
     *
     * @param obstacle
     *        объект препятствия
     * @return {@code true} препятствие преодолимо.
     *         {@code false} препятствие непреодолимо.
     */
    public boolean isOvercomeObstacle(Object obstacle) {
        if (obstacle instanceof Wall) {
            return canJump((Wall) obstacle);
        }
        return canRun((Treadmill) obstacle);
    }

    /**
     * Запускает прохождение полосы препятствий.
     *
     * @param obstacles
     *        массив препятствий.
     */
    void overcomeObstacleArray(Object[] obstacles){
        for (Object obstacle : obstacles) {
            overcome(obstacle);
            if (!isOvercomeObstacle(obstacle)) {
                setResultSprint("Failed");
                break;
            }
        }
        if (getResultSprint() == null) {
            setResultSprint("Success");
        }
    }

    private void overcome(Object obstacle) {
        if (obstacle instanceof Wall) {
            jump((Wall) obstacle, getName());
        } else {
            run((Treadmill) obstacle, getName());
        }
    }

    @Override
    public boolean canRun(int distance) {
        return distance <= getMaxDistanceRun();
    }

    @Override
    public boolean canJump(int distanse) {
        return distanse < getMaxDistanceJump();
    }
}