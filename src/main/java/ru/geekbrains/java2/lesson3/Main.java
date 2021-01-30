package ru.geekbrains.java2.lesson3;

import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {

        System.out.println("Run task 1:\n");
        countUpWordFrequency();
        System.out.println("Run task 2:\n");
        createAndTestPhonebook();
    }
    
    /**
     * 1. Создать массив с набором слов 
     * (10-20 слов, должны встречаться повторяющиеся). 
     * Найти и вывести список уникальных слов, из которых состоит массив 
     * (дубликаты не считаем). 
     * Посчитать сколько раз встречается каждое слово.
     */
    private static void countUpWordFrequency(){
        String str = "\"Shoo!\" said Mr.Dursley loudly" +
                "the cat didn't move " +
                "was this normal cat behavior himself " +
                "Mr.Dursley wondered trying to pull himself";
        String[] words = str.split(" +");

        TreeMap<String, Integer> resultMap = new TreeMap<>();

        for (String word : words) {
            resultMap.put(word, (resultMap.getOrDefault(word, 0) + 1));
        }

        System.out.println(resultMap);
    }


    /**
     * 2. Написать простой класс Телефонный Справочник, который хранит в себе 
     * список фамилий и телефонных номеров. В этот телефонный справочник 
     * с помощью метода add() можно добавлять записи. 
     * С помощью метода get() искать номер телефона по фамилии. 
     * Следует учесть, что под одной фамилией может быть несколько телефонов 
     * (в случае однофамильцев), тогда при запросе такой фамилии 
     * должны выводиться все телефоны.
     *
     * Желательно как можно меньше добавлять своего, чего нет в задании 
     * (т.е. не надо в телефонную запись добавлять еще дополнительные поля 
     * (имя, отчество, адрес), делать взаимодействие с пользователем через 
     * консоль и т.д.). Консоль желательно не использовать 
     * (в том числе Scanner), тестировать просто из метода main() 
     * прописывая add() и get().
     */
    private static void createAndTestPhonebook(){
        Phonebook ph = new Phonebook();
        
        //initPhonebook
        ph.add("Ivanov", "9015664488");
        ph.add("Ivanov", "9015664489");
        ph.add("Ivanov", "9015664490");
        ph.add("Petrov", "9025664490");
        ph.add("Petrov", "9025664491");
        ph.add("Smith", "9015664477");
        ph.add("Smith", "9035664490");
        ph.add("Potter", "904WWW4490");
        ph.add("Malfoy", "9090664490");
        ph.add("Snape", "9085664490");
        ph.add("Byron", "9085664490", "987456321", "6786");

        System.out.println(ph);
        System.out.println(ph.get("Ivanov"));
        System.out.println(ph.get("Snape"));
        System.out.println(ph.get("Rowling"));
    }
}
