package ru.egartech.systemObjects;

/**
 * Региональный центр обработки данных
 * */
public class objectFromSystemB extends BaseObjects {

    public objectFromSystemB() {
        nameSystem = "System B";
        codeTypeDocument = "2001";
        nameDocument = "Справка о количестве обработанных пакетов из центрального архива";
        countPage = 28;
    }

    @Override
    public String getMarker() {
        return "objectFromSystemB";
    }
}
