package org.example.entity;

import java.util.ArrayList;
import java.util.List;

public class Person {


    private Long id;
    private String name;
    private Integer age;
    private Card card = new Card();
    private List<Document> listDocument = new ArrayList<>();

    public Person(){

    }

    public Person(Long id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Card getCard() {
        return this.card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public List<Document> getDocument() {
        return this.listDocument;
    }

    public void setDocument(List<Document> listDocument) {
        this.listDocument = listDocument;
    }

    public void addDocument(Document document){
        this.listDocument.add(document);
        document.setPerson(this);
    }

    public void addCard(Card card){
        this.card = card;
        card.setPerson(this);
    }

    @Override
    public String toString() {
        return
                "Табельный номер: " + this.id + "\n"
                        + "ФИО сотрудника: " + this.name + "\n"
                        + "Возраст сотрудника: " + this.age + "\n"
                        + "Табель сотрудника - " + cardView() + "\n"
                        + "Документы сотрудника - " + documentView() + "\n";
    }

    public String documentView(){
        if(this.listDocument != null) {
            for (Document document : this.listDocument) {
                return "\n      Имя документа: " + document.getName() +
                        "\n      Описание документа: " + document.getDescription();
            }
        }
        return "Нету документов";
    }

    public String cardView(){
        if(this.card != null){
            return this.card.toString();
        }
        return "Нету карты";
    }


}
