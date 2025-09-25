package org.howard.edu.lsp.assignment3;

import java.util.ArrayList;
import java.util.List;

public class DefaultTransformer implements Transformer {
    @Override
    public List<CsvRow> transform(List<CsvRow> rows) throws ETLException {
        List<CsvRow> out = new ArrayList<>();
        for (CsvRow row : rows) {
            // Example: drop rows without required field
            if (row.get("id") == null || row.get("id").isBlank()) {
                continue;
            }
            CsvRow newRow = new CsvRow();
            for (String h : row.headers()) {
                String v = row.get(h);
                if (v == null) v = "";
                v = v.trim();
                // Insert your A2 logic here:
                // if (h.equalsIgnoreCase("price")) v = formatPrice(v);
                // if (h.equalsIgnoreCase("name")) v = toTitleCase(v);
                newRow.set(h, v);
            }
            out.add(newRow);
        }
        return out;
    }

}