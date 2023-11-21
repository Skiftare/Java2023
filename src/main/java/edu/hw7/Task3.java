package edu.hw7;

import java.util.HashMap;
import java.util.Map;
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

    @Override
    public void add(Person person) {
        synchronized (lock) {
            if (!cache.containsKey(person.id())) {
                cache.put(person.id(), person);
            } else {
                throw new RuntimeException("The uniqueness of the keys is violated");
            }
        }
    }

    @Override
    public void delete(int id) {
        synchronized (lock) {
            cache.remove(id);
        }
    }

    @Override
    @Nullable
    public Person findByName(String name) {
        synchronized (lock) {
            return cache.values().stream()
                .parallel()
                .filter(person -> person.getName().equals(name) && isNotNull(person.getAddress()) && isNotNull(person.getPhoneNumber()))
                .findFirst()
                .orElse(null);
        }
    }

    @Nullable
    @Override
    public Person findByAddress(String address) {
        synchronized (lock) {
            return cache.values().stream()
                .parallel()
                .filter(person -> person.getAddress().equals(address) && isNotNull(person.getName()) && isNotNull(person.getPhoneNumber()))
                .findFirst()
                .orElse(null);
        }
    }

    @Nullable
    @Override
    public Person findByPhone(String phoneNumber) {
        synchronized (lock) {
            return cache.values().stream()
                .parallel()
                .filter(person -> person.getPhoneNumber().equals(phoneNumber) && isNotNull(person.getName()) && isNotNull(person.getAddress()))
                .findFirst()
                .orElse(null);
        }
    }
}
