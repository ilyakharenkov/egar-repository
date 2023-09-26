package org.example.service.filter;

import org.example.entity.Person;

public class AverageTime implements WorkManager<Person, Double>{

    @Override
    public Double getWorkTime(Person person) {
        return (double) person.getCard().getCountWorkTime() / 30;
    }

}
