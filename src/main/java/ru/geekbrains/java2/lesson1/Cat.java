package ru.geekbrains.java2.lesson1;

/**
 * Сущность класса Кот.
 *
 * @author Kristina Retivykh
 */
public class Cat implements Runnable, Jumpable {

    private static final int DEFAULTMAXRUN = 100;
    private static final int DEFAULTMAXJUMP = 50;

    private String name;
    private int maxDistanceJump;
    private int maxDistanceRun;
    private String resultSprint;

    /**
     * Создаёт {@link Cat}.
     * Значения для максимальной дистанции бега и максимальной высоты прыжка
     * установлены по-умолчанию
     *
     * @param name
     *        имя кота
     */
    public Cat(String name) {
        this.name = name;
        this.maxDistanceJump = DEFAULTMAXRUN;
        this.maxDistanceRun = DEFAULTMAXJUMP;
    }

    /**
     * Создаёт {@link Cat}.
     *
     * @param name
     *        имя кота.
     * @param maxRun
     *        максимальная дистанция, которую может пробежать кот.
     * @param maxJump
     *        максимальная высота, на которую кот может запрыгнуть.
     */
    public Cat (String name, int maxRun, int maxJump) {
        this.name = name;
        setMaxDistanceRun(maxRun);
        setMaxDistanceJump(maxJump);
    }

    /**
     * Получает имя кота.
     *
     * @return имя кота.
     */
    public String getName() {
        return name;
    }

    /**
     * Получает максимально допустимую дистанцию пробежки кота.
     *
     * @return максимально допустимая дистанция пробежки кота.
     */
    public int getMaxDistanceRun() {
        return maxDistanceRun;
    }

    /**
     * Устанавливает максимально допустимую дистанцию бега кота.
     *
     * @param maxDistanceRun
     *        максимально допустимая дистанция бега кота.
     */
    public void setMaxDistanceRun(int maxDistanceRun) {
        if (maxDistanceRun < 0) {
            System.out.println("Value must be positive or zero.");
        } else {
            this.maxDistanceRun = maxDistanceRun;
        }
    }

    /**
     * Получает максимально допустимую высоту прыжка кота.
     *
     * @return максимально допустимая высота прыжка кота.
     */
    public int getMaxDistanceJump() {
        return maxDistanceJump;
    }

    /**
     * Устанавливает максимально допустимую высоту прыжка кота.
     *
     * @param maxDistanceJump
     *        максимально допустимая высота прыжка кота.
     */
    public void setMaxDistanceJump(int maxDistanceJump) {
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
        System.out.println(String.format("Cat named %s. It has %s sprint. " +
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
