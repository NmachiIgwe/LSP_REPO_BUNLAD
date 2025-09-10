# LSP_REPO_BUNLAD
# Assignment 2 — CSV ETL Pipeline

## Overview
This program implements a simple Extract-Transform-Load (ETL) pipeline in Java.  
It reads `data/products.csv`, applies transformations, and writes the results to `data/transformed_products.csv`.

## Features
- Reads CSV file with ProductID, Name, Price, Category.
- Transforms:
  - Converts product names to uppercase.
  - Applies 10% discount for Electronics.
  - Reclassifies expensive Electronics as "Premium Electronics".
  - Adds `PriceRange` based on final price.
- Writes transformed data to a new CSV file with the header:

## Ai implementations
I used AI (ChatGPT) to help clarify the assignment instructions and explain coding concepts step by step.
Using its abilities to suggest and interperate code and their executions.