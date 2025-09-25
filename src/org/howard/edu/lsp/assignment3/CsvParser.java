package org.howard.edu.lsp.assignment3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class CsvParser {

    private final String inputPath;

    public CsvParser(String inputPath) {
        this.inputPath = inputPath;
    }

    public List<CsvRow> parse() throws ETLException {
        File file = new File(inputPath);
        if (!file.exists()) {
            throw new ETLException("Input file not found: " + inputPath);
        }

        List<CsvRow> rows = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String headerLine = br.readLine();
            if (headerLine == null) {
                System.out.println("DEBUG: CSV is empty.");
                return rows; 
            }

            String[] headers = splitCsvLine(headerLine);
            System.out.println("DEBUG: headers = " + String.join(", ", headers));

            String line;
            while ((line = br.readLine()) != null) {
                if (line.isBlank()) {
                    continue;  // skip blank lines
                }

                String[] values = splitCsvLine(line);
                LinkedHashMap<String, String> map = new LinkedHashMap<>();

                for (int i = 0; i < headers.length; i++) {
                    String val = i < values.length ? values[i] : "";
                    map.put(headers[i], val);
                }

                rows.add(new CsvRow(map));
            }

            System.out.println("DEBUG: parsed rows count = " + rows.size());

        } catch (IOException e) {
            throw new ETLException("Error reading CSV: " + e.getMessage(), e);
        }

        return rows;
    }

    private String[] splitCsvLine(String line) {
        // very simple CSV splitting, assumes no quoted commas
        return line.split(",");
    }
}