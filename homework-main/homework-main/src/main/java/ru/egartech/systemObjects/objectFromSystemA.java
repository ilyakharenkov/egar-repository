package ru.egartech.systemObjects;

/**
 * Центральный архив
 * */
public class objectFromSystemA extends BaseObjects {

    String status;
    String info1_ObjectSystemA;
    String info2_ObjectSystemA;

    public objectFromSystemA() {
        nameSystem = "System A";
        codeTypeDocument = "1001";
        nameDocument = "Справка о количестве архивных документов категрии А";
        countPage = 12;
        status = "обработан";
        info1_ObjectSystemA = "дополнительная инфрмация 1";
        info2_ObjectSystemA = "дополнительная инфрмация 2";
    }

    public objectFromSystemA(String status, String info1_ObjectSystemA, String info2_ObjectSystemA) {
        this();
        this.status = status;
        this.info1_ObjectSystemA = info1_ObjectSystemA;
        this.info2_ObjectSystemA = info2_ObjectSystemA;
    }

    @Override
    public String getMarker() {
        return "objectFromSystemA";
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInfo1_ObjectSystemA() {
        return info1_ObjectSystemA;
    }

    public void setInfo1_ObjectSystemA(String info1_ObjectSystemA) {
        this.info1_ObjectSystemA = info1_ObjectSystemA;
    }

    public String getInfo2_ObjectSystemA() {
        return info2_ObjectSystemA;
    }

    public void setInfo2_ObjectSystemA(String info2_ObjectSystemA) {
        this.info2_ObjectSystemA = info2_ObjectSystemA;
    }
}
