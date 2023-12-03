package edu.hw7.Task3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockPersonDatabase implements PersonDatabase {
    private final Map<Integer, Person> personsById = new HashMap<>();
    private final Map<String, List<Person>> personsByName = new HashMap<>();
    private final Map<String, List<Person>> personsByAddress = new HashMap<>();
    private final Map<String, List<Person>> personsByPhone = new HashMap<>();
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    @Override
    public void add(Person person) {
        lock.writeLock().lock();

        try {
            personsById.put(person.id(), person);
            personsByName.computeIfAbsent(person.name(), f -> new ArrayList<>()).add(person);
            personsByAddress.computeIfAbsent(person.address(), f -> new ArrayList<>()).add(person);
            personsByPhone.computeIfAbsent(person.phoneNumber(), f -> new ArrayList<>()).add(person);
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public void delete(int id) {
        lock.writeLock().lock();

        try {
            Person removedPerson = personsById.remove(id);
            if (removedPerson != null) {
                personsByName.get(removedPerson.name()).remove(removedPerson);
                personsByAddress.get(removedPerson.address()).remove(removedPerson);
                personsByPhone.get(removedPerson.phoneNumber()).remove(removedPerson);
            }
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public List<Person> findByName(String name) {
        lock.readLock().lock();

        try {
            return personsByName.getOrDefault(name, List.of());
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public List<Person> findByAddress(String address) {
        lock.readLock().lock();

        try {
            return personsByAddress.getOrDefault(address, List.of());
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public List<Person> findByPhone(String phone) {
        lock.readLock().lock();

        try {
            return personsByPhone.getOrDefault(phone, List.of());
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public Map<Integer, Person> getPersons() {
        return personsById;
    }
}
