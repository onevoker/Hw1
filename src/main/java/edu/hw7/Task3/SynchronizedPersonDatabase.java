package edu.hw7.Task3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SynchronizedPersonDatabase implements PersonDatabase {
    private final Map<Integer, Person> personsById = new HashMap<>();
    private final Map<String, List<Person>> personsByName = new HashMap<>();
    private final Map<String, List<Person>> personsByAddress = new HashMap<>();
    private final Map<String, List<Person>> personsByPhone = new HashMap<>();

    @Override
    public synchronized void add(Person person) {
        personsById.put(person.id(), person);
        personsByName.computeIfAbsent(person.name(), f -> new ArrayList<>()).add(person);
        personsByAddress.computeIfAbsent(person.address(), f -> new ArrayList<>()).add(person);
        personsByPhone.computeIfAbsent(person.phoneNumber(), f -> new ArrayList<>()).add(person);
    }

    @Override
    public synchronized void delete(int id) {
        Person removedPerson = personsById.remove(id);
        if (removedPerson != null) {
            personsByName.get(removedPerson.name()).remove(removedPerson);
            personsByAddress.get(removedPerson.address()).remove(removedPerson);
            personsByPhone.get(removedPerson.phoneNumber()).remove(removedPerson);
        }
    }

    @Override
    public synchronized List<Person> findByName(String name) {
        return personsByName.getOrDefault(name, List.of());
    }

    @Override
    public synchronized List<Person> findByAddress(String address) {
        return personsByAddress.getOrDefault(address, List.of());
    }

    @Override
    public synchronized List<Person> findByPhone(String phone) {
        return personsByPhone.getOrDefault(phone, List.of());
    }

    @Override
    public Map<Integer, Person> getPersons() {
        return personsById;
    }
}
