package edu.hw7;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.jetbrains.annotations.Nullable;

public class Task3 implements PersonDatabase {
    private final Map<Integer, Person> cache;
    private final Object lock = new Object();

    public Task3() {
        this.cache = new HashMap<>();
    }

    private boolean isNotNull(Object obj) {
        return obj != null;
    }

    private Person mergeBasedOnFuture(Person originalData, Person newData) {
        String name;
        String address;
        String phoneNumber;
        if (newData.getName() == null) {
            name = originalData.getName();
        } else {
            name = newData.getName();
        }
        if (newData.getAddress() == null) {
            address = originalData.getAddress();
        } else {
            address = newData.getAddress();
        }
        if (newData.getPhoneNumber() == null) {
            phoneNumber = originalData.getPhoneNumber();
        } else {
            phoneNumber = newData.phoneNumber();
        }
        return new Person(originalData.getId(), name, address, phoneNumber);
    }

    @Override
    public void add(Person person) {
        synchronized (lock) {
            if (!cache.containsKey(person.id())) {
                cache.put(person.id(), person);
            } else {
                cache.replace(person.id(), mergeBasedOnFuture(cache.get(person.id()), person));

            }
        }
    }

    @Override
    public void delete(int id) {
        synchronized (lock) {
            cache.remove(id);
        }
    }

    public void clearCache() {
        synchronized (lock) {
            cache.clear();
        }
    }

    @Override
    public List<Person> findByName(String name) {
        synchronized (lock) {
            return cache.values().stream()
                .parallel()
                .filter(person -> isNotNull(person.getName())
                    && person.getName().equals(name)
                    && isNotNull(person.getAddress())
                    && isNotNull(person.getPhoneNumber()))
                .collect(Collectors.toList());
        }
    }

    @Nullable
    @Override
    public List<Person> findByAddress(String address) {
        synchronized (lock) {
            return cache.values().stream()
                .parallel()
                .filter(person -> isNotNull(person.getAddress())
                    && person.getAddress().equals(address)
                    && isNotNull(person.getName())
                    && isNotNull(person.getPhoneNumber()))
                .collect(Collectors.toList());
        }
    }

    @Nullable
    @Override
    public List<Person> findByPhone(String phoneNumber) {
        synchronized (lock) {
            return cache.values().stream()
                .parallel()
                .filter(person -> isNotNull(person.getPhoneNumber())
                    && person.getPhoneNumber().equals(phoneNumber)
                    && isNotNull(person.getName())
                    && isNotNull(person.getAddress()))
                .collect(Collectors.toList());
        }
    }
}
