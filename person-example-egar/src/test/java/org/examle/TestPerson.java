package org.examle;

import org.example.entity.Card;
import org.example.entity.Document;
import org.example.entity.Person;
import org.example.entity.Vacation;
import org.example.repository.PersonRepository;
import org.example.repository.Repository;
import org.example.service.PersonService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class TestPerson {


    Repository<Person, Long> personLongRepository = new PersonRepository();
    PersonService personService = new PersonService(personLongRepository);


    @Test
    void test1() {
        Person person = new Person(5L, "Test name", 40);

        Assertions.assertEquals("Test name", person.getName());
        Assertions.assertEquals(40, person.getAge());

        Assertions.assertNotNull(person.getCard());
        Assertions.assertNotNull(person.getCard().getVacations());
        Assertions.assertNotNull(person.getDocument());
    }

    @Test
    void test2() {

        Person person = new Person(6L, "Test name 2", 30);

        Card card = new Card(6L, 120);
        card.addVacations(new Vacation(LocalDate.parse("2023-04-04"), LocalDate.parse("2023-04-28")));

        Document document = new Document(6L, "Name test doc", "Description doc");

        person.addCard(card);
        person.addDocument(document);

        Assertions.assertEquals(LocalDate.parse("2023-04-04"), person.getCard().getVacations().get(0).getStartVacation());
        Assertions.assertEquals(LocalDate.parse("2023-04-28"), person.getCard().getVacations().get(0).getEndVacation());
    }

    /**
     * Добавление
     */
    @Test
    void testAddMethod() {
        Person person = new Person(10L, "Igor", 20);

        personService.addPerson(person);

        Assertions.assertEquals(person, getEqualsPerson(10L));
        Assertions.assertNull(getEqualsPerson(11L));
    }
    Person getEqualsPerson(Long id) {
        for (Person person : personService.getList()) {
            if (person.getId().equals(id)) {
                return person;
            }
        }
        return null;
    }

    /**
     * Удаление
     */
    @Test
    void testDeleteMethod() {

        Person person = new Person(15L, "Ilya", 60);

        personService.addPerson(person);
        Assertions.assertEquals(person, findPersonById(15L));

        personService.deletePersonById(15L);
        Assertions.assertNull(findPersonById(15L));

    }
    Person findPersonById(Long id) {
        for (Person person : personService.getList()) {
            if (person.getId().equals(id)) {
                return person;
            }
        }
        return null;
    }




}
