package org.howard.edu.lsp.assignment3;

import java.util.List;

public class ETLDriver {
    public static void main(String[] args) {
        String input = "data/products.csv";
        String output = "data/transformed_products.csv";
        if (args.length >= 1) input = args[0];
        if (args.length >= 2) output = args[1];

        CsvParser parser = new CsvParser(input);
        Transformer transformer = new DefaultTransformer();
        CsvWriter writer = new CsvWriter(output);
        
        try {
            List<CsvRow> rows = parser.parse();
            List<CsvRow> transformed = transformer.transform(rows);
            writer.write(transformed);
            System.out.println("ETL done: wrote " + transformed.size() + " rows to " + output);
        } catch (ETLException e) {
            System.err.println("ETL failed: " + e.getMessage());
            System.exit(1);
        }
    }
}

