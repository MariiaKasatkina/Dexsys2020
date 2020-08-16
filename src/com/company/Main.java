package com.company;
import java.util.*;
import java.util.stream.IntStream;

public class Main {

    public static ArrayList<Integer> arrayX = new ArrayList<>();
    public static ArrayList<Integer> arrayS = new ArrayList<>();
    public static ArrayList<Integer> arrayM = new ArrayList<>();
    public static boolean anyMore = false;

    public static void initArray ()
    {
        Scanner input = new Scanner(System.in);
        System.out.print("Введите элементы массива: ");
        try {
            int[] inArray = Arrays.stream(input.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        for (Integer el : inArray)
            if (el % 3 == 0 || el % 7 == 0) {
                if (el % 3 == 0) {
                    arrayX.add(el);
                    if (el % 7 == 0) {
                        arrayS.add(el);
                        arrayM.add(el);
                    }
                }
                else arrayS.add(el);
            }
            else anyMore = true;
        }
        catch (NumberFormatException e) {
            System.out.println("Ошибка! Некорректно введен массив");
            System.out.println(e.getMessage());
        }
    }

    public static void printArray (ArrayList<Integer> array)
    {
        printArray(array, "");
    }

    public static void printArray (ArrayList<Integer> array, String type)
    {
        if (array.size() == 0)
            System.out.println("Список " + type + " пуст");
        else {
            Collections.sort(array);
            if (type != "")
                System.out.print("Список " + type + ":");
            for (Integer el : array)
                System.out.print(" " + el);
            System.out.println();
        }
    }

    public static void main(String[] args) {
        System.out.println("Для получения списка всех команд можно воспользоваться командой help\n");
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.print("Введите команду: ");
            switch (input.nextLine()) {
                case "init array":
                    initArray();
                    break;
                case "print":
                    printArray(arrayX, "X");
                    printArray(arrayS, "S");
                    printArray(arrayM, "M");
                    break;
                case "print X":
                    if (arrayX.size() == 0)
                        System.out.println("Список X пуст");
                    else {
                        printArray(arrayX, "X");
                    }
                    break;
                case "print S":
                    if (arrayS.size() == 0)
                        System.out.println("Список S пуст");
                    else {
                        printArray(arrayS, "S");
                    }
                    break;
                case "print M":
                    if (arrayM.size() == 0)
                        System.out.println("Список M пуст");
                    else {
                        printArray(arrayM, "M");
                    }
                    break;
                case "anyMore":
                    System.out.println(anyMore);
                    break;
                case "clear X":
                    arrayX.clear();
                    System.out.println("Список X отчищен");
                    break;
                case "clear S":
                    arrayS.clear();
                    System.out.println("Список S отчищен");
                    break;
                case "clear M":
                    arrayM.clear();
                    System.out.println("Список M отчищен");
                    break;
                case "merge":
                    ArrayList allNumbers = new ArrayList(arrayX.size() + arrayS.size() + arrayM.size());
                    allNumbers.addAll(arrayX);
                    allNumbers.addAll(arrayS);
                    allNumbers.addAll(arrayM);
                    //в задании не указано, нужно ли удалять одинаковые элементы, поэтому итоговый массив содержит все элементы из каждого списка
                    arrayX.clear();
                    arrayS.clear();
                    arrayM.clear();
                    anyMore = false;
                    System.out.print("Все списки отчищены.\nОбщий список:");
                    printArray(allNumbers);
                    break;
                case "help":
                    System.out.println("Список команд:");
                    System.out.println("init array - инициализация списков набором значений");
                    System.out.println("print - печать всех списков");
                    System.out.println("print type - печать конкретного списка, где type принимает значения X,S,M");
                    System.out.println("anyMore - выводит на экран были ли значения не вошедшие ни в один список");
                    System.out.println("clear type - чистка списка , где type принимает значения X,S,M");
                    System.out.println("merge -  слить все списки в один вывести на экран и очистить все списки");
                    System.out.println("help - вывод справки по командам");
                    System.out.println("exit - выход из программы");
                    break;
                case "exit":
                    input.close();
                    return;
                default:
                    System.out.println("Такой команды нет");
            }
        }
    }
}
