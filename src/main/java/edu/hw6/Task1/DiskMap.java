package edu.hw6.Task1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DiskMap implements Map<String, String> {
    private final File file;
    private final Map<String, String> diskMap;

    public DiskMap(String filePath) {
        try {
            this.file = new File(filePath);

            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.diskMap = new HashMap<>();
        readFromFile();
    }

    private void readFromFile() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                String[] keyValue = line.split(":");
                if (keyValue.length == 2) {
                    diskMap.put(keyValue[0], keyValue[1]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void writeToFile() {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            for (Map.Entry<String, String> entryMap : diskMap.entrySet()) {
                bufferedWriter.write(entryMap.getKey() + ":" + entryMap.getValue() + "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int size() {
        return diskMap.size();
    }

    @Override
    public boolean isEmpty() {
        return diskMap.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return diskMap.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return diskMap.containsValue(value);
    }

    @Override
    public String get(Object key) {
        return diskMap.get(key);
    }

    @Nullable
    @Override
    public String put(String key, String value) {
        String resultOfPut = diskMap.put(key, value);
        writeToFile();
        return resultOfPut;
    }

    @Override
    public String remove(Object key) {
        String resultOfRemove = diskMap.remove(key);
        writeToFile();
        return resultOfRemove;
    }

    @Override
    public void putAll(@NotNull Map<? extends String, ? extends String> m) {
        diskMap.putAll(m);
        writeToFile();
    }

    @Override
    public void clear() {
        for (Iterator<String> iterator = keySet().iterator(); iterator.hasNext();) {
            iterator.next();
            iterator.remove();
            writeToFile();
        }
    }

    @NotNull
    @Override
    public Set<String> keySet() {
        return diskMap.keySet();
    }

    @NotNull
    @Override
    public Collection<String> values() {
        return diskMap.values();
    }

    @NotNull
    @Override
    public Set<Entry<String, String>> entrySet() {
        return diskMap.entrySet();
    }
}
