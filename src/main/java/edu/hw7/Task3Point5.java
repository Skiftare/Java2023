package edu.hw7;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Collectors;
import org.jetbrains.annotations.Nullable;

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
        lock.writeLock().lock();
        try {
            if (!cache.containsKey(person.id())) {
                cache.put(person.id(), person);
            } else {
                cache.replace(person.id(), mergeBasedOnFuture(cache.get(person.id()), person));

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
    public List<Person> findByName(String name) {
        lock.readLock().lock();
        try {
            return cache.values().stream()
                .parallel()
                .filter(person -> isNotNull(person.getName())
                    && person.getName().equals(name)
                    && isNotNull(person.getAddress())
                    && isNotNull(person.getPhoneNumber()))
                .collect(Collectors.toList());

        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    @Nullable
    public List<Person> findByAddress(String address) {
        lock.readLock().lock();
        try {
            return cache.values().stream()
                .parallel()
                .filter(person -> isNotNull(person.getAddress())
                    && person.getAddress().equals(address)
                    && isNotNull(person.getName())
                    && isNotNull(person.getPhoneNumber()))
                .collect(Collectors.toList());
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    @Nullable
    public List<Person> findByPhone(String phoneNumber) {
        lock.readLock().lock();
        try {
            return cache.values().stream()
                .parallel()
                .filter(person -> isNotNull(person.getPhoneNumber())
                    && person.getPhoneNumber().equals(phoneNumber)
                    && isNotNull(person.getName())
                    && isNotNull(person.getAddress()))
                .collect(Collectors.toList());
        } finally {
            lock.readLock().unlock();
        }
    }
}

