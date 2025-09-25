package org.howard.edu.lsp.assignment3;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Set;

public class CsvWriter {
    private final String outputPath;

    public CsvWriter(String outputPath) {
        this.outputPath = outputPath;
    }

    public void write(List<CsvRow> rows) throws ETLException {
        if (rows.isEmpty()) {
            try (BufferedWriter bw= new BufferedWriter(new FileWriter(outputPath))) {
            } catch (IOException e) {
                throw new ETLException("Error writing CSV: " + e.getMessage(), e);
            }
            return;
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputPath))) {

            Set<String> headers = rows.get(0).headers();
            boolean first = true;
            for (String h : headers) {
                if (!first) bw.write(",");
                bw.write(escape(h));
                first = false;
            }
            bw.newLine();


            for (CsvRow r : rows) {
                first = true;
                for (String h : headers) {
                    if (!first) bw.write(",");
                    String v = r.get(h);
                    bw.write(escape(v));
                    first = false;
                }
                bw.newLine();
            }
        } catch (IOException e) {
            throw new ETLException("Error writing CSV: " + e.getMessage(), e);
        }
    }

    private String escape(String s) {
        if (s == null) return "";
        String s2 = s.replace("\"", "\"\"");
        if (s2.contains(",") || s2.contains("\"") || s2.contains("\n")) {
            return "\"" + s2 + "\"";
        }
        return s2;
    }
}
