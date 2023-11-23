package edu.hw7.task3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jetbrains.annotations.Nullable;

public class CacheDatabasePerson implements PersonDatabase {
    private final Map<Integer, Person> idPersonStorage = new HashMap<>();
    private final Map<String, List<Person>> nameCache = new HashMap<>();
    private final Map<String, List<Person>> addressCache = new HashMap<>();
    private final Map<String, List<Person>> phoneNumberCache = new HashMap<>();

    @Override
    public synchronized void add(Person person) {
        idPersonStorage.put(person.id(), person);
        updateCache(person);
    }

    private void updateCache(Person person) {
        if (nameCache.containsKey(person.name())) {
            nameCache.get(person.name()).add(person);
        } else {
            nameCache.put(person.name(), new ArrayList<>(List.of(person)));
        }
        if (addressCache.containsKey(person.address())) {
            addressCache.get(person.address()).add(person);
        } else {
            addressCache.put(person.address(), new ArrayList<>(List.of(person)));
        }
        if (phoneNumberCache.containsKey(person.phoneNumber())) {
            phoneNumberCache.get(person.phoneNumber()).add(person);
        } else {
            phoneNumberCache.put(person.phoneNumber(), new ArrayList<>(List.of(person)));
        }
    }

    @Override
    public synchronized void delete(int id) {
        Person person = idPersonStorage.remove(id);
        if (person != null) {
            removeFromCache(person);
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
    public synchronized @Nullable List<Person> findByName(String name) {
        return nameCache.get(name);
    }

    @Override
    public synchronized @Nullable List<Person> findByAddress(String address) {
        return addressCache.get(address);
    }

    @Override
    public synchronized @Nullable List<Person> findByPhoneNumber(String phoneNumber) {
        return phoneNumberCache.get(phoneNumber);
    }
}
