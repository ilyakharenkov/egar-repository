package org.example.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Card {

    private Long id;
    private Integer countWorkTime;
    private List<Vacation> vacations = new ArrayList<>();

    private Person person;

    public Card(){

    }

    public Card(Long id, Integer countWorkTime){
        this.id = id;
        this.countWorkTime = countWorkTime;
    }

    public Long getId(){
        return this.id;
    }

    public Integer getCountWorkTime(){
        return this.countWorkTime;
    }
    public void setCountWorkTime(Integer countWorkTime){
        this.countWorkTime = countWorkTime;
    }

    public List<Vacation> getVacations(){
        return this.vacations;
    }
    public void setVacations(List<Vacation> vacations){
        this.vacations = vacations;
    }

    public Person getPerson(){
        return this.person;
    }
    public void setPerson(Person person){
        this.person = person;
    }

    public void addVacations(Vacation vacation){
        vacations.add(vacation);
        vacation.setCard(this);
    }

    @Override
    public String toString(){
        return "\n      Колличесвто отработанных часов: " + this.countWorkTime
                + viewVacations();
    }

    public String viewVacations(){
        if(this.vacations != null) {
            for (Vacation vacation : this.vacations) {
                return "\n      Начало отпуска: " + vacation.getStartVacation()
                        + "\n      Конец отпуска: " + vacation.getEndVacation();
            }
        }
        return "Нет отпуска";
    }

}