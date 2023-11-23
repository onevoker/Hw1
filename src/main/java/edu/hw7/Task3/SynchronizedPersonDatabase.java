package edu.hw7.Task3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SynchronizedPersonDatabase implements PersonDatabase {
    private final Map<Integer, Person> personsById = new HashMap<>();

    @Override
    public synchronized void add(Person person) {
        personsById.put(person.id(), person);
    }

    @Override
    public synchronized void delete(int id) {
        personsById.remove(id);
    }

    @Override
    public synchronized List<Person> findByName(String name) {
        List<Person> result = new ArrayList<>();

        for (Person person : personsById.values()) {
            if (person.name().equals(name)) {
                result.add(person);
            }
        }

        return result;
    }

    @Override
    public synchronized List<Person> findByAddress(String address) {
        List<Person> result = new ArrayList<>();

        for (Person person : personsById.values()) {
            if (person.address().equals(address)) {
                result.add(person);
            }
        }

        return result;
    }

    @Override
    public synchronized List<Person> findByPhone(String phone) {
        List<Person> result = new ArrayList<>();

        for (Person person : personsById.values()) {
            if (person.phoneNumber().equals(phone)) {
                result.add(person);
            }
        }

        return result;
    }

    @Override
    public Map<Integer, Person> getPersons() {
        return personsById;
    }
}
