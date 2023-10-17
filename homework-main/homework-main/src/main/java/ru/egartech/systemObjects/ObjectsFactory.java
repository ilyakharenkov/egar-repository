package ru.egartech.systemObjects;

public class ObjectsFactory {
    public BaseObjects createObject(Integer codeSystem) {
        return switch (codeSystem) {
            case 1 -> new objectFromSystemA("1", "info1_ObjectSystemA", "info1_ObjectSystemA");
            case 2 -> new objectFromSystemB();
            case 3 -> new objectFromSystemC();
            default -> throw new RuntimeException("нет данных для обработки");
        };

    }
}
