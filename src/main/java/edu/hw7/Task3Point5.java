package edu.hw7;

import org.jetbrains.annotations.Nullable;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Task3Point5 implements PersonDatabase {
    private final Map<Integer, Person> cache;
    private final ReadWriteLock lock;

    public Task3Point5() {
        this.cache = new HashMap<>();
        this.lock = new ReentrantReadWriteLock();
    }

    private boolean isNotNull(Object obj) {
        return obj != null;
    }

    @Override
    public void add(Person person) {
        lock.writeLock().lock();
        try {
            if (!cache.containsKey(person.id())) {
                cache.put(person.id(), person);
            } else {
                throw new RuntimeException("The uniqueness of the keys is violated");
            }
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public void delete(int id) {
        lock.writeLock().lock();
        try {
            cache.remove(id);
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    @Nullable
    public Person findByName(String name) {
        lock.readLock().lock();
        try {
            for (Person person : cache.values()) {
                if (person.getName().equals(name) && isNotNull(person.getAddress()) && isNotNull(person.getPhoneNumber())) {
                    return person;
                }
            }
            return null;
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    @Nullable
    public Person findByAddress(String address) {
        lock.readLock().lock();
        try {
            for (Person person : cache.values()) {
                if (person.getAddress().equals(address) && isNotNull(person.getName()) && isNotNull(person.getPhoneNumber())) {
                    return person;
                }
            }
            return null;
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    @Nullable
    public Person findByPhone(String phoneNumber) {
        lock.readLock().lock();
        try {
            for (Person person : cache.values()) {
                if (person.getPhoneNumber().equals(phoneNumber) && isNotNull(person.getName()) && isNotNull(person.getAddress())) {
                    return person;
                }
            }
            return null;
        } finally {
            lock.readLock().unlock();
        }
    }
}

