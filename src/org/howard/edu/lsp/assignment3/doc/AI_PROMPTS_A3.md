# AI Prompts Log for Assignment 3

I used a generative AI assistant to brainstorm the object-oriented redesign of my ETL pipeline. Below are some of the prompts and excerpts of responses.

## Prompt 1
**My question:** "Step by step, help me through this assignment. Using the same methods as the first and second assignment. help me complete the third one."

**AI Response (excerpt):**
> "We’ll split your ETL pipeline into Extractor, Transformer, and Loader classes, and keep the driver orchestrating them…"

---

## Prompt 2
**My question:** "step by step, show me how I would implement these changes."

**AI Response (excerpt):**
> "First create your package: `src/org/howard/edu/lsp/assignment3/`. Then make four classes: CSVExtractor, CSVTransformer, CSVLoader, ETLDriver…"

---

## Prompt 3
**My question:** "I ran the ETLDriver and got the output: ETL done: wrote 0 rows…"

**AI Response (excerpt):**
> "That means the program structure works, but no rows are flowing through. Add debug prints in Extractor, Transformer, Loader to trace the row counts…"

---

## Prompt 4
**My question:** "Can’t we keep the same names for it?"

**AI Response (excerpt):**
> "Yes — you can keep `CsvParser`, `Transformer`, and `CsvWriter`. The naming doesn’t matter as long as you explain the OO decomposition in your reflection…"
