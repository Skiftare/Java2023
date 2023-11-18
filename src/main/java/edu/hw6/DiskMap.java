package edu.hw6;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.jetbrains.annotations.NotNull;

public class DiskMap implements Map<String, String> {
    private final static org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger();
    private final String filePath;
    private final Map<String, String> map;

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
                if (!file.createNewFile()) {
                    LOGGER.info("Файл создать не удалось");
                }
            } catch (IOException e) {
                LOGGER.info(e.getMessage());
            }
        }
    }

    private void loadFromFile() {
        map.clear();
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
            LOGGER.info(e.getMessage());
        }
    }

    private void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                writer.write(entry.getKey() + ":" + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            LOGGER.info(e.getMessage());
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
        loadFromFile();
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

        loadFromFile();
        map.putAll(m);
        saveToFile();
    }

    @Override
    public void clear() {
        map.clear();
        saveToFile();
    }

    @NotNull
    @Override
    public Set<String> keySet() {
        loadFromFile();
        return map.keySet();
    }

    @NotNull
    @Override
    public Collection<String> values() {
        loadFromFile();
        return map.values();
    }

    @NotNull
    @Override
    public Set<Entry<String, String>> entrySet() {
        loadFromFile();
        return map.entrySet();
    }

}
