package ru.egartech.systemObjects;

public abstract class BaseObjects {
    // название системы
    String nameSystem;
    // код документа по классификатору
    String codeTypeDocument;
    // название документа
    String nameDocument;
    // количество страниц документа
    Integer countPage;

    // маркер объекта
    public abstract String getMarker();
}
