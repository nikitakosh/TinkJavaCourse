package edu.hw7;

import edu.hw7.task3.CacheDatabasePerson;
import edu.hw7.task3.Person;
import edu.hw7.task3.ReadWriteLockCacheDatabasePerson;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Task3Test {

    @Test
    @DisplayName("add person")
    public void add(){
        CacheDatabasePerson cacheDatabasePerson = new CacheDatabasePerson();
        Person egor = new Person(1, "Egor", "Moskva", "83457651231");
        Person andrey = new Person(2, "Andrey", "Samara", "83457651232");
        Person nikita = new Person(3, "Nikita", "Kazan", "83457651233");
        cacheDatabasePerson.add(egor);
        cacheDatabasePerson.add(andrey);
        cacheDatabasePerson.add(nikita);
        Assertions.assertEquals(cacheDatabasePerson.findByName("Egor"), List.of(egor));
        Assertions.assertEquals(cacheDatabasePerson.findByName("Nikita"), List.of(nikita));
        Assertions.assertEquals(cacheDatabasePerson.findByName("Andrey"), List.of(andrey));
    }

    @Test
    @DisplayName("remove person")
    public void remove(){
        CacheDatabasePerson cacheDatabasePerson = new CacheDatabasePerson();
        Person egor = new Person(1, "Egor", "Moskva", "83457651231");
        Person andrey = new Person(2, "Andrey", "Samara", "83457651232");
        Person nikita = new Person(3, "Nikita", "Kazan", "83457651233");
        cacheDatabasePerson.add(egor);
        cacheDatabasePerson.add(andrey);
        cacheDatabasePerson.add(nikita);
        Assertions.assertEquals(cacheDatabasePerson.findByName("Egor"), List.of(egor));
        Assertions.assertEquals(cacheDatabasePerson.findByName("Nikita"), List.of(nikita));
        Assertions.assertEquals(cacheDatabasePerson.findByName("Andrey"), List.of(andrey));
        cacheDatabasePerson.delete(1);
        cacheDatabasePerson.delete(2);
        cacheDatabasePerson.delete(3);
        Assertions.assertEquals(cacheDatabasePerson.findByName("Egor"), List.of());
        Assertions.assertEquals(cacheDatabasePerson.findByName("Nikita"), List.of());
        Assertions.assertEquals(cacheDatabasePerson.findByName("Andrey"), List.of());
    }

    @Test
    @DisplayName("find by name")
    public void findByName(){
        CacheDatabasePerson cacheDatabasePerson = new CacheDatabasePerson();
        Person egor = new Person(1, "Egor", "Moskva", "83457651231");
        Person egor1 = new Person(2, "Egor", "Moskva", "83457651231");
        Person andrey = new Person(3, "Andrey", "Samara", "83457651232");
        Person nikita = new Person(4, "Nikita", "Kazan", "83457651233");
        cacheDatabasePerson.add(egor);
        cacheDatabasePerson.add(egor1);
        cacheDatabasePerson.add(andrey);
        cacheDatabasePerson.add(nikita);
        Assertions.assertEquals(cacheDatabasePerson.findByName("Egor"), List.of(egor, egor1));
        Assertions.assertEquals(cacheDatabasePerson.findByName("Nikita"), List.of(nikita));
        Assertions.assertEquals(cacheDatabasePerson.findByName("Andrey"), List.of(andrey));
        Assertions.assertNull(cacheDatabasePerson.findByName("Gosha"));
    }

    @Test
    @DisplayName("find by address")
    public void findByAddress(){
        CacheDatabasePerson cacheDatabasePerson = new CacheDatabasePerson();
        Person egor = new Person(1, "Egor", "Moskva", "83457651231");
        Person vitya = new Person(2, "Vitya", "Moskva", "83457651231");
        Person andrey = new Person(3, "Andrey", "Samara", "83457651232");
        Person nikita = new Person(4, "Nikita", "Kazan", "83457651233");
        cacheDatabasePerson.add(vitya);
        cacheDatabasePerson.add(egor);
        cacheDatabasePerson.add(andrey);
        cacheDatabasePerson.add(nikita);
        Assertions.assertEquals(cacheDatabasePerson.findByAddress("Moskva"), List.of(vitya, egor));
        Assertions.assertEquals(cacheDatabasePerson.findByAddress("Kazan"), List.of(nikita));
        Assertions.assertEquals(cacheDatabasePerson.findByAddress("Samara"), List.of(andrey));
        Assertions.assertNull(cacheDatabasePerson.findByAddress("Saint-Petersburg"));
    }


    @Test
    @DisplayName("find by phone number")
    public void findByPhoneNumber(){
        CacheDatabasePerson cacheDatabasePerson = new CacheDatabasePerson();
        Person egor = new Person(1, "Egor", "Moskva", "83457651231");
        Person vitya = new Person(2, "Vitya", "Moskva", "83457651231");
        Person andrey = new Person(3, "Andrey", "Samara", "83457651232");
        Person nikita = new Person(4, "Nikita", "Kazan", "83457651233");
        cacheDatabasePerson.add(vitya);
        cacheDatabasePerson.add(egor);
        cacheDatabasePerson.add(andrey);
        cacheDatabasePerson.add(nikita);
        Assertions.assertEquals(cacheDatabasePerson.findByPhoneNumber("83457651231"), List.of(vitya, egor));
        Assertions.assertEquals(cacheDatabasePerson.findByPhoneNumber("83457651233"), List.of(nikita));
        Assertions.assertEquals(cacheDatabasePerson.findByPhoneNumber("83457651232"), List.of(andrey));
        Assertions.assertNull(cacheDatabasePerson.findByPhoneNumber("83457651234"));
    }

    @Test
    @DisplayName("parallel add and find synchronized")
    public void parallelAddAndFindSynchronized() {
        CacheDatabasePerson cacheDatabasePerson = new CacheDatabasePerson();
        ExecutorService executor = Executors.newFixedThreadPool(30);
        for(int i = 0; i < 1000; i++) {
            Person person = new Person(i, "Person" + i, "Address" + i, "PhoneNumber" + i);
            executor.execute(() -> cacheDatabasePerson.add(person));
        }
        for(int i = 0; i < 1000; i++) {
            String name = "Person" + i;
            String address = "Address" + i;
            String phoneNumber = "PhoneNumber" + i;
            executor.execute(() -> {
                List<Person> foundByName = cacheDatabasePerson.findByName(name);
                List<Person> foundAddress = cacheDatabasePerson.findByAddress(address);
                List<Person> foundByPhoneNumber = cacheDatabasePerson.findByPhoneNumber(phoneNumber);
                if (foundByName != null) {
                    Assertions.assertNotNull(foundAddress);
                    Assertions.assertNotNull(foundByPhoneNumber);
                }
            });
        }
        executor.shutdown();
    }

    @Test
    @DisplayName("parallel add and find ReadWriteLock")
    public void parallelAddAndFindReadWriteLock() {
        ReadWriteLockCacheDatabasePerson cacheDatabasePerson = new ReadWriteLockCacheDatabasePerson();
        ExecutorService executor = Executors.newFixedThreadPool(30);
        for(int i = 0; i < 1000; i++) {
            Person person = new Person(i, "Person" + i, "Address" + i, "PhoneNumber" + i);
            executor.execute(() -> cacheDatabasePerson.add(person));
        }
        for(int i = 0; i < 1000; i++) {
            String name = "Person" + i;
            String address = "Address" + i;
            String phoneNumber = "PhoneNumber" + i;
            executor.execute(() -> {
                List<Person> foundByName = cacheDatabasePerson.findByName(name);
                List<Person> foundAddress = cacheDatabasePerson.findByAddress(address);
                List<Person> foundByPhoneNumber = cacheDatabasePerson.findByPhoneNumber(phoneNumber);
                if (foundByName != null) {
                    Assertions.assertNotNull(foundAddress);
                    Assertions.assertNotNull(foundByPhoneNumber);
                }
            });
        }
        executor.shutdown();
    }

}
