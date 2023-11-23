package edu.hw7.task3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.jetbrains.annotations.Nullable;

public class ReadWriteLockCacheDatabasePerson implements PersonDatabase {
    private final Map<Integer, Person> idPersonStorage = new HashMap<>();
    private final Map<String, List<Person>> nameCache = new HashMap<>();
    private final Map<String, List<Person>> addressCache = new HashMap<>();
    private final Map<String, List<Person>> phoneNumberCache = new HashMap<>();
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock writeLock = lock.writeLock();
    private final Lock readLock = lock.readLock();

    @Override
    public void add(Person person) {
        try {
            writeLock.lock();
            idPersonStorage.put(person.id(), person);
            updateCache(person);
        } finally {
            writeLock.unlock();
        }

    }

    private void updateCache(Person person) {
        if (nameCache.containsKey(person.name())) {
            nameCache.get(person.name()).add(person);
        } else {
            nameCache.put(person.name(), List.of(person));
        }
        if (addressCache.containsKey(person.address())) {
            addressCache.get(person.address()).add(person);
        } else {
            addressCache.put(person.address(), List.of(person));
        }
        if (phoneNumberCache.containsKey(person.phoneNumber())) {
            phoneNumberCache.get(person.phoneNumber()).add(person);
        } else {
            phoneNumberCache.put(person.phoneNumber(), List.of(person));
        }
    }

    @Override
    public void delete(int id) {
        try {
            writeLock.lock();
            Person person = idPersonStorage.remove(id);
            if (person != null) {
                removeFromCache(person);
            }
        } finally {
            writeLock.unlock();
        }

    }

    private void removeFromCache(Person person) {
        nameCache.put(person.name(), nameCache.get(
                person.name()).stream().filter(currPerson -> currPerson.id() != person.id()).toList()
        );
        addressCache.put(person.address(), addressCache.get(
                person.address()).stream().filter(currPerson -> currPerson.id() != person.id()).toList()
        );
        phoneNumberCache.put(person.phoneNumber(), phoneNumberCache.get(
                person.phoneNumber()).stream().filter(currPerson -> currPerson.id() != person.id()).toList()
        );
    }

    @Override
    public @Nullable List<Person> findByName(String name) {
        try {
            readLock.lock();
            return nameCache.get(name);
        } finally {
            readLock.unlock();
        }

    }

    @Override
    public @Nullable List<Person> findByAddress(String address) {
        try {
            readLock.lock();
            return addressCache.get(address);
        } finally {
            readLock.unlock();
        }

    }

    @Override
    public @Nullable List<Person> findByPhoneNumber(String phoneNumber) {
        try {
            readLock.lock();
            return phoneNumberCache.get(phoneNumber);
        } finally {
            readLock.unlock();
        }

    }
}
