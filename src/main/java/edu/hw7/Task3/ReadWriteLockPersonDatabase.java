package edu.hw7.Task3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockPersonDatabase implements PersonDatabase {
    private final Map<Integer, Person> personsById = new HashMap<>();
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    @Override
    public void add(Person person) {
        lock.writeLock().lock();

        try {
            personsById.put(person.id(), person);
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public void delete(int id) {
        lock.writeLock().lock();

        try {
            personsById.remove(id);
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public List<Person> findByName(String name) {
        lock.readLock().lock();
        List<Person> result = new ArrayList<>();

        try {
            for (Person person : personsById.values()) {
                if (person.name().equals(name)) {
                    result.add(person);
                }
            }
        } finally {
            lock.readLock().unlock();
        }

        return result;
    }

    @Override
    public List<Person> findByAddress(String address) {
        lock.readLock().lock();
        List<Person> result = new ArrayList<>();

        try {
            for (Person person : personsById.values()) {
                if (person.address().equals(address)) {
                    result.add(person);
                }
            }
        } finally {
            lock.readLock().unlock();
        }

        return result;
    }

    @Override
    public List<Person> findByPhone(String phone) {
        lock.readLock().lock();
        List<Person> result = new ArrayList<>();

        try {
            for (Person person : personsById.values()) {
                if (person.phoneNumber().equals(phone)) {
                    result.add(person);
                }
            }
        } finally {
            lock.readLock().unlock();
        }

        return result;
    }

    @Override
    public Map<Integer, Person> getPersons() {
        return personsById;
    }
}
