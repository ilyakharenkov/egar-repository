package org.example.entity;

public class Document {

    private Long id;
    private String name;
    private String description;

    private Person person;

    public Document(){

    }

    public Document(Long id, String name, String description){
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Long getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }

    public Person getPerson(){
        return this.person;
    }
    public void setPerson(Person person){
        this.person = person;
    }

}
