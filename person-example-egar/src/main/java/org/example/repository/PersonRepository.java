package org.example.repository;

import org.example.entity.Card;
import org.example.entity.Document;
import org.example.entity.Person;
import org.example.entity.Vacation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PersonRepository implements Repository<Person, Long> {

    private final List<Person> listPerson = new ArrayList<>();
    private final List<String> listName = new ArrayList<>();
    private final List<Integer> listAge = new ArrayList<>();
    private final List<Card> listCard = new ArrayList<>();
    private final List<Vacation> listVacation = new ArrayList<>();
    private final List<Document> listDocument = new ArrayList<>();

    {
        generatedPerson();
    }

    private void generatedPerson() {
        for (int i = 0; i <= 2; i++) {
            listPerson.add(
                    new Person(
                            (long) i,
                            generatedName().get(i),
                            generatedAge().get(i)
                    ).addCard(
                            generatedCard((long) i).get(i)
                    ).addDocument(
                           generatedListDocument().get(i)
                    )
            );
        }
    }


    private List<String> generatedName() {
        listName.addAll(List.of("Igor", "Petr", "Vasiliy"));
        return listName;
    }

    private List<Integer> generatedAge() {
        listAge.addAll(List.of(24, 28, 32));
        return listAge;
    }

    private List<Card> generatedCard(Long id) {
        listCard.addAll(List.of(
                new Card(id, 160),
                new Card(id, 168),
                new Card(id, 152)
        ));
        for (int i = 0; i <= 2; i++) {
            listCard.get(i).addVacations(generatedVacations().get(i));
        }
        return listCard;
    }

    private List<Document> generatedListDocument() {
        listDocument.addAll(List.of(
                new Document(1L, "Секретная секретность", "Описание секретной секретности"),
                new Document(2L, "Что то не очень секретное", "В описании не нуждается")));
        return listDocument;
    }

    private List<Vacation> generatedVacations() {
        return new ArrayList<>(List.of(
                new Vacation(LocalDate.parse("2023-09-02"), LocalDate.parse("2023-09-16")),
                new Vacation(LocalDate.parse("2023-06-06"), LocalDate.parse("2023-06-20")),
                new Vacation(LocalDate.parse("2023-10-10"), LocalDate.parse("2023-10-24"))
        ));
    }

    @Override
    public List<Person> getList() {
        return listPerson;
    }

    @Override
    public void add(Person person) {
        listPerson.add(person);
    }

    @Override
    public void delete(Long id) {
        listPerson.removeIf(person -> person.getId().equals(id));
    }


}
