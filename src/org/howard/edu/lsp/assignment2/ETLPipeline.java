/**
 * Name: Nmachi Igwe
 */
package org.howard.edu.lsp.assignment2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;


/**
 * ETL pipeline for assignment:
 * - Reads data/products.csv (relative to project root)
 * - Transforms per spec
 * - Writes data/transformed_products.csv
 */

public class ETLPipeline {

    private static final String INPUT_PATH  = "data/products.csv";
    private static final String OUTPUT_PATH = "data/transformed_products.csv";

    public static void main(String[] args) {
        Path in = Paths.get(INPUT_PATH);
        Path out = Paths.get(OUTPUT_PATH);

        // 1) Check for missing input file
        if (!Files.exists(in)) {
            System.err.println("ERROR: Input file not found at relative path: " + INPUT_PATH);
            System.err.println("Make sure you run this program from the project root (folder that contains 'src' and 'data').");
            return;
        }

        int rowsRead = 0;
        int transformed = 0;
        int skipped = 0;
        


        try (BufferedReader br = Files.newBufferedReader(in);
            BufferedWriter bw = Files.newBufferedWriter(out)) {

            
            bw.write("productID,Name,Price,Category,PriceRange");
            bw.newLine();

            String header = br.readLine();
            if (header == null) {

                System.out.println("Input file is empty. Created output with header only: " + out.toAbsolutePath());
                printSummary(rowsRead, transformed, skipped, out);
                return;

            }

            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) {
                        skipped++;
                        continue;
                }

                rowsRead++;
                String[] cols = line.split(",", -1);
                if (cols.length != 4) {
                    skipped++;
                    continue;
                }

                try {
                    // Parse input columns (trim to be safe)
                    int productId = Integer.parseInt(cols[0].trim());
                    String name = cols[1].trim();
                    BigDecimal price = new BigDecimal(cols[2].trim());
                    String category = cols[3].trim();
                    String originalCategory = category; // keep original category for recategorization rule

                    // ----- TRANSFORM ORDER -----
                    // (1) uppercase name
                    name = name.toUpperCase(Locale.ROOT);

                    // (2) discount if original is Electronics (10% off)
                    if ("Electronics".equals(originalCategory)) {
                        price = price.multiply(new BigDecimal("0.9"));
                    }

                    // round to two decimals (HALF_UP)
                    price = price.setScale(2, RoundingMode.HALF_UP);

                    // (3) recategorize if post-discount price > 500.00 AND original category was Electronics
                    if (price.compareTo(new BigDecimal("500.00")) > 0 && "Electronics".equals(originalCategory)) {
                        category = "Premium Electronics";
                    }

                    // (4) compute PriceRange from final price
                    String priceRange;
                    if (price.compareTo(new BigDecimal("10.00")) <= 0) {
                        priceRange = "Low";
                    } else if (price.compareTo(new BigDecimal("100.00")) <= 0) {
                        priceRange = "Medium";
                    } else if (price.compareTo(new BigDecimal("500.00")) <= 0) {
                        priceRange = "High";
                    } else {
                        priceRange = "Premium";
                    }

                    // Write transformed row. Use price.toPlainString() to avoid scientific notation.
                    bw.write(String.format("%d,%s,%s,%s,%s",
                            productId, name, price.toPlainString(), category, priceRange));
                    bw.newLine();
                    transformed++;
                } catch (NumberFormatException nfe) {
                    // Malformed numeric data: skip that row, count as skipped
                    skipped++;
                }
            }

            bw.flush();
            printSummary(rowsRead, transformed, skipped, out);

        } catch (IOException ioe) {
            System.err.println("I/O error: " + ioe.getMessage());
        }
    }

    private static void printSummary(int rowsRead, int transformed, int skipped, Path outputPath) {
        System.out.println("---- Run summary ----");
        System.out.println("Rows read (data rows, not header): " + rowsRead);
        System.out.println("Rows transformed: " + transformed);
        System.out.println("Rows skipped: " + skipped);
        System.out.println("Output file written to: " + outputPath.toAbsolutePath());
    }
}