package ru.geekbrains.java2.lesson1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Object[] obstacles =initObstacleArray(8); //массив препятствий
        Object[] entities = initEntityArray(); //массив участников

        startSprint(entities, obstacles);
    }

    private static void startSprint(Object[] entities, Object[] obstacles){
        for (Object entity : entities) {
            if (entity instanceof Cat) {
                ((Cat) entity).overcomeObstacleArray(obstacles);
                ((Cat) entity).printResultSprint();
            } else if (entity instanceof Person) {
                ((Person) entity).overcomeObstacleArray(obstacles);
                ((Person) entity).printResultSprint();
            } else if (entity instanceof Robot) {
                ((Robot) entity).overcomeObstacleArray(obstacles);
                ((Robot) entity).printResultSprint();
            }
        }
    }

    private static Object[] initObstacleArray(int count){
        Random random = new Random();
        Object[] obstacles = new Object[count];
        for (int i = 0; i < count; i++) {
            if (random.nextInt(count) % 3 == 1) {
                obstacles[i] = new Wall(random.nextInt(50));
            } else {
                obstacles[i] = new Treadmill(random.nextInt(100));
            }
        }
        return obstacles;
    }

    private static Object[] initEntityArray(){
        Random random = new Random();
        int randomIndex;
        List nameList = new ArrayList<>(Arrays.asList("Crookshanks",
                "Mrs. Norris", "Galadriel", "Frodo", "Darth Vader", "Skywalker",
                "Fluffy", "Weasley", "Marvolo", "Riddle", "Filch", "Gandalf",
                "Gimli", "Aragorn", "Elendil", "Hedwig", "Salazar", "Godric",
                "Dracula", "Graf", "Woof"));

        Object[] entities = new Object[(random.nextInt(nameList.size()-5)) + 5];
        for (int i = 0; i < entities.length; i++) {
            randomIndex = random.nextInt(nameList.size());
            if (i % 3 == 0) {
                entities[i] = new Cat((String) nameList.get(randomIndex));
            } else if (i % 2 == 0) {
                entities[i] = new Person((String) nameList.get(randomIndex));
            } else {
                entities[i] = new Robot((String) nameList.get(randomIndex));
            }
            nameList.remove(randomIndex); //Чтобы имена не повторялись
        }
        return entities;
    }
}
