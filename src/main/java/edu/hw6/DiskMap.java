package edu.hw6;

import java.io.*;
import org.jetbrains.annotations.NotNull;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DiskMap implements Map<String, String> {
    private String filePath;
    private Map<String, String> map;

    public DiskMap(String filePath) {
        this.filePath = filePath;
        createFileIfNotExists();
        this.map = new HashMap<>();
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
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    String key = parts[0];
                    String value = parts[1];
                    map.put(key, value);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                writer.write(entry.getKey() + ":" + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return map.containsValue(value);
    }

    @Override
    public String get(Object key) {
        return map.get(key);
    }

    @Override
    public String put(String key, String value) {
        String previousValue = map.put(key, value);
        saveToFile();
        return previousValue;
    }

    @Override
    public String remove(Object key) {
        String removedValue = map.remove(key);
        saveToFile();
        return removedValue;
    }

    @Override
    public void putAll(@NotNull Map<? extends String, ? extends String> m) {

    }

    @Override
    public void clear() {

    }

    @NotNull
    @Override
    public Set<String> keySet() {
        return null;
    }

    @NotNull
    @Override
    public Collection<String> values() {
        return null;
    }

    @NotNull
    @Override
    public Set<Entry<String, String>> entrySet() {
        return null;
    }

    // Другие методы интерфейса Map<String, String> можно реализовать аналогичным образом

    public static void main(String[] args) {
        DiskMap diskMap = new DiskMap("data.dat");
        diskMap.put("key1", "value1");
        diskMap.put("key2", "value2");
        diskMap.put("key3", "value3");

        System.out.println(diskMap.get("key1")); // Выводит "value1"
        System.out.println(diskMap.get("key2")); // Выводит "value2"
        System.out.println(diskMap.get("key3")); // Выводит "value3"
    }
}
