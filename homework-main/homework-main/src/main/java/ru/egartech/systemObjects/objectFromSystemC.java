package ru.egartech.systemObjects;

/**
 * Служба занятости населения
 * */
public class objectFromSystemC extends BaseObjects {

    public objectFromSystemC() {
        nameSystem = "System C";
        codeTypeDocument = "3001";
        nameDocument = "Справка о статистике за сентябрь 2023 г.";
        countPage = 2;
    }

    @Override
    public String getMarker() {
        return "objectFromSystemC";
    }
}
