package org.example.service;

import org.example.entity.Person;
import org.example.repository.Repository;

import java.util.List;


/**
 * Методы добавление и удаление проверил в тесте
 */
public class PersonService {

    private final Repository<Person, Long> repository;

    public PersonService(Repository<Person, Long> repository){
        this.repository = repository;
    }

    public List<Person> getList(){
        return repository.getList();
    }

    public void addPerson(Person person){
        repository.add(person);
    }

    public void deletePersonById(Long id){
        repository.delete(id);
    }


}
