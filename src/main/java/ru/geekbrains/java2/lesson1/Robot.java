package ru.geekbrains.java2.lesson1;

/**
 * Сущность класса Робот.
 *
 * @author Kristina Retivykh
 */
public class Robot implements Runnable, Jumpable {

    private static final int DEFAULTMAXRUN = 250;
    private static final int DEFAULTMAXJUMP = 125;

    private String name;
    private int maxDistanceJump;
    private int maxDistanceRun;
    private String resultSprint;

    /**
     * Создаёт {@link Robot}.
     * Значения для максимальной дистанции бега и максимальной высоты прыжка
     * установлены по-умолчанию
     *
     * @param name
     *        имя робота.
     */
    public Robot (String name) {
        this.name = name;
        this.maxDistanceJump = DEFAULTMAXRUN;
        this.maxDistanceRun = DEFAULTMAXJUMP;
    }

    /**
     * Создаёт {@link Robot}.
     *
     * @param name
     *        имя робота
     * @param maxRun
     *        максимальная дистанция, которую может пробежать робот
     * @param maxJump
     *        максимальная высота, на которую робот может запрыгнуть
     */
    public Robot (String name, int maxRun, int maxJump) {
        this.name = name;
        setMaxDistanceRun(maxRun);
        setMaxDistanceJump(maxJump);
    }

    /**
     * Получает имя робота.
     *
     * @return имя робота.
     */
    public String getName () {
        return name;
    }

    /**
     * Получает максимально допустимую высоту прыжка робота.
     *
     * @return максимально допустимая высота прыжка робота.
     */
    public int getMaxDistanceJump () {
        return maxDistanceJump;
    }

    /**
     * Устанавливает максимально допустимую высоту прыжка робота.
     *
     * @param maxDistanceJump
     *        максимально допустимая высота прыжка робота.
     */
    public void setMaxDistanceJump ( int maxDistanceJump){
        if (maxDistanceJump < 0) {
            System.out.println("Value must be positive or zero.");
        } else {
            this.maxDistanceJump = maxDistanceJump;
        }
    }

    /**
     * Получает максимально допустимую дистанцию пробежки робота.
     *
     * @return максимально допустимая дистанция пробежки робота.
     */
    public int getMaxDistanceRun () {
        return maxDistanceRun;
    }

    /**
     * Устанавливает максимально допустимую дистанцию бега робота.
     *
     * @param maxDistanceRun
     *        максимально допустимая дистанция бега робота.
     */
    public void setMaxDistanceRun ( int maxDistanceRun){
        if (maxDistanceRun < 0) {
            System.out.println("Value must be positive or zero.");
        } else {
            this.maxDistanceRun = maxDistanceRun;
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
        System.out.println(String.format("Robot named %s. It has %s sprint. " +
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
    public void overcomeObstacleArray(Object[] obstacles){
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
