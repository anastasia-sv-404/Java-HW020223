import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        System.out.println("Реализуйте структуру телефонной книги с помощью HashMap, учитывая, что 1 человек может\n" +
                "иметь несколько телефонов.");
        item1();

        System.out.println();
        System.out.println();

        System.out.println("Пусть дан список сотрудников. Написать программу, которая найдет и выведет повторяющиеся " +
                "имена с количеством повторений. Отсортировать по убыванию популярности. " +
                "Для сортировки использовать TreeMap.");
        item2();
    }

    static void item1() {
        Map<String, ArrayList<Integer>> phoneBook = new HashMap<>();
        addContact(phoneBook, "Иван", 123456);
        addContact(phoneBook, "Елена", 123456);
        addContact(phoneBook, "Елена", 77788);
        addContact(phoneBook, "Семен", 4568);
        addContact(phoneBook, "Семен", 78963);
        addContact(phoneBook, "Семен", 451789);
        System.out.println(phoneBook);
    }//Реализуйте структуру телефонной книги с помощью HashMap, учитывая, что 1 человек может иметь несколько телефонов.

    static void item2() {
        String str = "Иван Иванов, Светлана Петрова, Кристина Белова, Анна Мусина, Анна Крутова, Иван Юрин, \n" +
                "Петр Лыков, Павел Чернов, Петр Чернышов, Мария Федорова, Марина Светлова, Мария Савина, \n" +
                "Мария Рыкова, Марина Лугова, Анна Владимирова, Иван Мечников, Петр Петин, Иван Ежов";

        System.out.println("Список сотрудников: " + str);
        ArrayList<String> listNames = getNames(str);
        Map<String, Integer> namesCount = getNamesCount(listNames);
        Map<String, Integer> repeatNames = getRepeatNames(namesCount);
        TreeMap<Integer, ArrayList<String>> sortedNames = getSortedRepNames(repeatNames);

//        System.out.println(namesCount);
//        System.out.println(repeatNames);
        System.out.println(sortedNames.descendingMap());
    }

    static void addContact(Map<String, ArrayList<Integer>> phoneBook, String name, int number) {
        ArrayList<Integer> list = new ArrayList<>();
        for (var entry : phoneBook.entrySet()) {
            if (entry.getKey().equals(name)) {
                list = entry.getValue();
                list.add(number);
                phoneBook.put(name, list);
                return;
            }
        }
        list.add(number);
        phoneBook.put(name, list);
    }

    static ArrayList<String> getNames(String str) {
        str = str.replace(", ", " ");
        String[] list = str.split(" ");
        ArrayList<String> listNames = new ArrayList<>();
        for (int i = 0; i < list.length - 1; i += 2) {
            listNames.add(list[i]);
        }
        return listNames;
    }

    static Map<String, Integer> getNamesCount(ArrayList<String> listNames) {
        Map<String, Integer> map = new HashMap<>();
        int count = 1;
        for (String name : listNames) {
            if (map.containsKey(name)) {
                count = map.get(name) + 1;
                map.put(name, count);
            }
            count = 1;
            map.putIfAbsent(name, count);
        }
        return map;
    }

    static Map<String, Integer> getRepeatNames(Map<String, Integer> map) {
        Map<String, Integer> repeatNames = new HashMap<>();
        for (var item : map.entrySet()) {
            if (item.getValue() != 1) {
                repeatNames.put(item.getKey(), item.getValue());
            }
        }
        return repeatNames;
    }

    static TreeMap<Integer, ArrayList<String>> getSortedRepNames(Map<String, Integer> repeatNames) {
        TreeMap<Integer, ArrayList<String>> sortedNames = new TreeMap<>();

        for (var item : repeatNames.entrySet()) {
            if (sortedNames.containsKey(item.getValue())) {
                ArrayList<String> name = sortedNames.get(item.getValue());
                name.add(String.valueOf(item.getKey()));
            } else {
                ArrayList<String> nameFirst = new ArrayList<>();
                nameFirst.add(item.getKey());
                sortedNames.put(item.getValue(), nameFirst);
            }
        }
        return sortedNames;
    }
}