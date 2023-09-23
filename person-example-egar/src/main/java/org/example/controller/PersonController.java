package org.example.controller;

import org.example.entity.Person;
import org.example.service.PersonService;
import org.example.service.filter.WorkManager;

public class PersonController {

    private final PersonService personService;
    private final WorkManager<Person> averageTime;

    public PersonController(PersonService personService, WorkManager<Person> averageTime){
        this.personService = personService;
        this.averageTime = averageTime;
    }

    public void getPersons(){
        for(Person person : personService.getList()){
            System.out.println(person.toString());
        }
    }

    public void getAverageTimeOfDay(){
        for(Person person : personService.getList()){
            System.out.println(person.getName());
            System.out.println(averageTime.getWorkTime(person) + "\n");
        }
    }

}
