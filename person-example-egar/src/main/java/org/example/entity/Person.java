package org.example.entity;

import java.util.List;

public class Person {

    private Long id;
    private String name;
    private Integer age;
    private Card card;
    private List<Document> listDocument;

    public Person(){

    }

    public Person(Long id, String name, Integer age, Card card, List<Document> listDocument) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.card = card;
        this.listDocument = listDocument;
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

    @Override
    public String toString() {
        return
                "Табельный номер: " + this.id + "\n"
                        + "ФИО сотрудника: " + this.name + "\n"
                        + "Возраст сотрудника: " + this.age + "\n"
                        + "Табель сотрудника - " + this.card.toString() + "\n"
                        + "Документы сотрудника - " + documentView() + "\n";
    }

    public String documentView(){
        for(Document document : this.listDocument){
            return "\n      Имя документа: " + document.getName() +
                    "\n      Описание документа: " + document.getDescription();
        }
        return "";
    }

}
