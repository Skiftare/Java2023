package edu.hw6;

import java.io.*;
import java.nio.file.Path;
import java.util.*;

public class DiskHashSet<E> implements Set<E> {
    private String filePath;
    private HashSet<E> set;

    public DiskHashSet(Path filePath) {
        this.filePath = String.valueOf(filePath.getFileName());
        createFileIfNotExists();
        this.set = new HashSet<>();
        loadFromFile();
    }
    public DiskHashSet() {
        this.filePath = "DiskHashSet.dat";
        createFileIfNotExists();
        this.set = new HashSet<>();
        loadFromFile();
    }

    private void createFileIfNotExists() {
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void loadFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                E element = (E) line;
                set.add(element);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (E element : set) {
                writer.write(element.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int size() {
        return set.size();
    }

    @Override
    public boolean isEmpty() {
        return set.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return set.contains(o);
    }

    @Override
    public Iterator<E> iterator() {
        return set.iterator();
    }

    @Override
    public Object[] toArray() {
        return set.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return set.toArray(a);
    }

    @Override
    public boolean add(E e) {
        boolean added = set.add(e);
        if (added) {
            saveToFile();
        }
        return added;
    }

    @Override
    public boolean remove(Object o) {
        boolean removed = set.remove(o);
        if (removed) {
            saveToFile();
        }
        return removed;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return set.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean modified = set.addAll(c);
        if (modified) {
            saveToFile();
        }
        return modified;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean modified = set.retainAll(c);
        if (modified) {
            saveToFile();
        }
        return modified;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean modified = set.removeAll(c);
        if (modified) {
            saveToFile();
        }
        return modified;
    }

    @Override
    public void clear() {
        set.clear();
        saveToFile();
    }

    public static void main(String[] args) {
        DiskHashSet<String> diskHashSet = new DiskHashSet<>(Path.of("data.dat"));
        diskHashSet.add("element1");
        diskHashSet.add("element2");
        diskHashSet.add("element3");

        System.out.println(diskHashSet.contains("element1")); // Output: true
        System.out.println(diskHashSet.contains("element2")); // Output: true
        System.out.println(diskHashSet.contains("element3")); // Output: true
    }
}
