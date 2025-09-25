# Reflection: Assignment 3 vs Assignment 2

## Design Differences
In Assignment 2, I implemented the ETL pipeline mostly in one class (`CsvParser`) with all the logic for extraction, transformation, and loading handled in a single place. While this worked, it was less modular and harder to extend.

In Assignment 3, I redesigned the pipeline into separate classes:
- `CsvParser` → Responsible only for extracting rows from the input CSV.
- `Transformer` (interface) + `DefaultTransformer` (implementation) → captures transformation logic.
- `CsvWriter` → Handles writing the transformed rows to the output CSV.

The driver class (`ETLDriver`) coordinates these components.

## Object-Oriented Features
- **Encapsulation**: Each class has a single, well-defined responsibility.
- **Inheritance**: `DefaultTransformer` implements the `Transformer` interface.
- **Polymorphism**: The driver can work with any `Transformer` implementation.
- **Abstraction**: Using interfaces for transformation makes the pipeline extensible.

## Benefits of Assignment 3
- Easier to test each component independently.
- Code is more maintainable and readable.
- Flexible to future requirements (e.g., adding JSONExtractor or XMLWriter).

## Testing
I tested Assignment 3 by:
1. Running the pipeline on the same `products.csv` input file as Assignment 2.
2. Comparing the output file (`transformed_products.csv`) to confirm both versions produced the same results.
3. Running edge cases (empty input, missing file) to confirm error handling.
