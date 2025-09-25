package org.howard.edu.lsp.assignment3;


import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class CsvRow {
    private final LinkedHashMap<String, String> data;

    public CsvRow() {
        data = new LinkedHashMap<>();
    }

    public CsvRow(LinkedHashMap<String, String> initial) {
        data = new LinkedHashMap<>(initial);
    }

    public String get(String header) {
        return data.get(header);
    }

    public void set(String header, String value) {
        data.put(header, value);
    }

    public Set<String> headers() {
        return data.keySet();
    }

    public Map<String, String> asMap() {
        return data;
    }

    @Override
    public String toString() {
        return data.toString();
    }
}
