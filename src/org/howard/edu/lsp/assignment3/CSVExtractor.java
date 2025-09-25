package org.howard.edu.lsp.assignment3;

import java.io.*;
import java.util.*;

public class CSVExtractor {
    public List<String[]> extract(String inputFile) throws IOException {
        List<String[]> rows = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            String line;
            boolean firstLine = true;  // skip header if needed
            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue;
                }
                String[] values = line.split(",");
                rows.add(values);
            }
        }
        System.out.println("DEBUG: Extracted " + rows.size() + " rows.");
        return rows;
    }
}