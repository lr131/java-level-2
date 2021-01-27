package ru.geekbrains.java2.lesson3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

/**
 * Сущность класса Телефонный Справочник.
 *
 * @author Kristina Retivykh
 */

public class Phonebook {

    TreeMap<String, ArrayList<String>> personList;

    /**
     * Создает {@link Phonebook}
     */
    public Phonebook() {
        this.personList = new TreeMap();
    }

    /**
     * Добавляет в справочник запись или обновляет существующую.
     * 
     * @param family
     *        фамилия абонента.
     * @param phone
     *        номер телефона абонента.
     */
    public void add(String family, String phone) {
        ArrayList<String> phoneArr = personList.getOrDefault(
                family, new ArrayList<>()
        );
        phoneArr.add(phone);
        this.personList.put(family, phoneArr);
    }

    /**
     * Добавляет в справочник запись или обновляет существующую.
     * 
     * @param family
     *        фамилия абонента.
     * @param phones
     *        массив телефонов абонента.
     */
    public void add(String family, String...phones) {
        List<String> newPhones = Arrays.asList(phones);
        ArrayList<String> phoneArr = personList.getOrDefault(
                family, new ArrayList<String>()
        );
        phoneArr.addAll(newPhones);
        this.personList.put(family, phoneArr);
    }

    /**
     * Ищет абонента в справочнике по фамилии.
     * 
     * @param family
     *        фамилия абонента.
     * @return список телефонных номеров, связанных с этой фамилией.
     */
    public ArrayList<String> get(String family) {
        return this.personList.getOrDefault(family, null);
    }

    /**
     * Возвращает строковое представление экземпляра класса 
     * Телефонный Справочник.
     * 
     * @return строковое представление экземпляра класса Телефонный Справочник.
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        Object[] keys = this.personList.keySet().toArray();
        for (Object key : keys) {
            s.append("{")
             .append(key)
             .append(": ")
             .append(personList.get(key).toString())
             .append("}\n");
        }
        return s.toString();
    }
}
