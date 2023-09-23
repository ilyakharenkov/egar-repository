package org.example;

import org.example.controller.PersonController;
import org.example.entity.Person;
import org.example.repository.PersonRepository;
import org.example.repository.Repository;
import org.example.service.PersonService;
import org.example.service.filter.AverageTime;
import org.example.service.filter.WorkManager;


public class Di {

    private final Repository<Person, Long> repository = new PersonRepository();

    private final PersonService personService = new PersonService(repository);
    private final WorkManager<Person> averageTime = new AverageTime();

    public final PersonController personController = new PersonController(personService, averageTime);

}
