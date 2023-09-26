package org.example.entity;

import java.time.LocalDate;

public class Vacation {


    private LocalDate startVacation;
    private LocalDate endVacation;

    private Card card;

    public Vacation(){
    }
    public Vacation(LocalDate startVacation, LocalDate endVacation){
        this.startVacation = startVacation;
        this.endVacation = endVacation;
    }


    public LocalDate getStartVacation(){
        return this.startVacation;
    }
    public void setStartVacation(LocalDate startVacation){
        this.startVacation = startVacation;
    }

    public LocalDate getEndVacation(){
        return this.endVacation;
    }
    public void setEndVacation(LocalDate endVacation){
        this.endVacation = endVacation;
    }

    public Card getCard(){
        return this.card;
    }
    public void setCard(Card card){
        this.card = card;
    }

    @Override
    public String toString(){
        return "\nНачало: " + this.startVacation +
                "\nКонец: " + this.endVacation;
    }


}
