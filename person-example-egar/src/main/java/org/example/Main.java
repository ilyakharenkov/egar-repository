package org.example;

import java.util.Scanner;

public class Main {

    private static final Di di = new Di();

    public static void main(String[] args) {
        viewConsole();
    }

    private static void viewConsole() {
        Scanner scanner = new Scanner(System.in);
        boolean b = true;
        viewHead();
        while (b) {
            switch (scanner.next()) {
                case "1" -> {
                    di.personController.getPersons();
                    viewHead();
                }
                case "2" -> {
                    di.personController.getAverageTimeOfDay();
                    viewHead();
                }
                case "3" -> {
                    di.personController.getPersonAndCard();
                    viewHead();
                }
                case "4" -> {
                    b = false;
                }
            }
        }
        scanner.close();
    }

    private static void viewHead() {
        System.out.println("Введите 1 для отображения списка сотрудников");
        System.out.println("Введите 2 для отображения среднего времени работы в день");
        System.out.println("Введите 3 для отображения отпусков");
        System.out.println("Введите 4 для выхода");


    }


}