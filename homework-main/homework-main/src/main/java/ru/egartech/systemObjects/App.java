package ru.egartech.systemObjects;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class App {


    public static void main(String[] args) {
        int countMessage = 6;
        // список для хранения сообщений из внешних систем
        List<BaseObjects> baseObjectsList = new ArrayList<>();
        // заполняем список генерируемыми данными
        for (int i = 0; i < countMessage; i++) {
            baseObjectsList.add(new ObjectsFactory().createObject(getCodeSystem()));
        }
        // выводим в консоль элементы списка
        for (int i = 0; i < baseObjectsList.size(); i++) {
            BaseObjects baseObjects = baseObjectsList.get(i);
            System.out.println("Элемент списка №" + (i + 1) + " " + baseObjects.getMarker());
            System.out.println("Название системы: " + baseObjects.nameSystem);
            System.out.println("Тип документа: " + baseObjects.codeTypeDocument);
            System.out.println("Название документа: " + baseObjects.nameDocument);
            System.out.println("Количество страниц документа: " + baseObjects.countPage + "\n");
        }
        registry(baseObjectsList);
    }
// ----------------------------------------------------------------------------------

    // список для хранения записей Реестра

    // тут надо реализовать хранение обработанных записей из систем А, B, C
    public static void registry(List<BaseObjects> baseObjects) {
        Map<String, BaseObjects> listRegistry = new Registry(baseObjects).execute();
        for (String key : listRegistry.keySet()) {
            System.out.println(listRegistry.get(key).nameSystem);
            System.out.println(listRegistry.get(key).nameDocument);
            validationClass(listRegistry.get(key));
            System.out.println("");
        }
    }

    public static void validationClass(BaseObjects baseObjects) {
        if (baseObjects instanceof objectFromSystemA) {
            System.out.println(((objectFromSystemA) baseObjects).status);
            System.out.println(((objectFromSystemA) baseObjects).info1_ObjectSystemA);
            System.out.println(((objectFromSystemA) baseObjects).info2_ObjectSystemA);
        }
    }

// ----------------------------------------------------------------------------------

    /**
     * Генерация кода системы-источника данных (случайное число от 1 до 3)
     */
    static Integer getCodeSystem() {
        int max = 3, min = 1;
        return new Random().nextInt(max - min + 1) + min;
    }
}

