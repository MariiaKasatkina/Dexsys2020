package com.company;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {

    public static List arrayX = new ArrayList();
    public static List arrayS = new ArrayList();
    public static List arrayM = new ArrayList();
    public static boolean anyMore = false;

    enum Command {
        INIT_ARRAY,
        PRINT,
        PRINT_X,
        PRINT_S,
        PRINT_M,
        ANYMORE,
        CLEAR_X,
        CLEAR_S,
        CLEAR_M,
        MERGE,
        HELP,
        EXIT;
    }

    public static void merge() {
        List allNumbers = new ArrayList();
        allNumbers.addAll(arrayX);
        allNumbers.addAll(arrayS);
        arrayX.clear();
        arrayS.clear();
        arrayM.clear();
        anyMore = false;
        System.out.print("Все списки отчищены.\nОбщий список:");
        allNumbers.stream().sorted().forEach(x -> System.out.print(" " + x));
        System.out.println();
    }

    public static void getHelp () {
        System.out.println("Список команд:");
        System.out.println("init_array - инициализация списков набором значений");
        System.out.println("print - печать всех списков");
        System.out.println("print_type - печать конкретного списка, где type принимает значения X,S,M");
        System.out.println("anyMore - выводит на экран были ли значения не вошедшие ни в один список");
        System.out.println("clear_type - чистка списка , где type принимает значения X,S,M");
        System.out.println("merge -  слить все списки в один вывести на экран и очистить все списки");
        System.out.println("help - вывод справки по командам");
        System.out.println("exit - выход из программы");
    }

    public static void initArray ()
    {
        Scanner input = new Scanner(System.in);
        System.out.print("Введите элементы массива: ");
        try {
            int[] inArray = Arrays.stream(input.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            arrayX.addAll(Arrays.stream(inArray).filter(x -> x%3 == 0).boxed().collect(Collectors.toList()));
            arrayS.addAll(Arrays.stream(inArray).filter(x -> x%7 == 0).boxed().collect(Collectors.toList()));
            arrayM.addAll(Arrays.stream(inArray).filter(x -> x%21 == 0).boxed().collect(Collectors.toList()));

            if (Arrays.stream(inArray).filter(x -> (x%3 != 0 && x%7 != 0)).count()>0)
                anyMore = true;
        }
        catch (NumberFormatException e) {
            System.out.println("Ошибка! Некорректно введен массив");
            System.out.println(e.getMessage());
        }
    }

    public static void printArray (List array, String type)
    {
        if (array.size() == 0)
            System.out.println("Список " + type + " пуст");
        else {
            System.out.print("Список " + type + ":");
            array.stream().sorted().forEach(x -> System.out.print(" " + x));
            System.out.println();
        }
    }

    public static void main(String[] args) {
        System.out.println("Для получения списка всех команд можно воспользоваться командой help\n");
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.print("Введите команду: ");
            Command command = Command.HELP;
            try {
                command = Command.valueOf(input.nextLine().toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Такой команды нет");
            }

            switch (command) {
                case INIT_ARRAY:
                    initArray();
                    break;
                case PRINT:
                    printArray(arrayX, "X");
                    printArray(arrayS, "S");
                    printArray(arrayM, "M");
                    break;
                case PRINT_X:
                    printArray(arrayX, "X");
                    break;
                case PRINT_S:
                    printArray(arrayS, "S");
                    break;
                case PRINT_M:
                    printArray(arrayM, "M");
                    break;
                case ANYMORE:
                    System.out.println(anyMore);
                    break;
                case CLEAR_X:
                    arrayX.clear();
                    System.out.println("Список X отчищен");
                    break;
                case CLEAR_S:
                    arrayS.clear();
                    System.out.println("Список S отчищен");
                    break;
                case CLEAR_M:
                    arrayM.clear();
                    System.out.println("Список M отчищен");
                    break;
                case MERGE:
                    merge();
                    break;
                case HELP:
                    getHelp();
                    break;
                case EXIT:
                    input.close();
                    return;
                default:
                    System.out.println("Такой команды нет");
            }
        }
    }
}
