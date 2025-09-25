package org.howard.edu.lsp.assignment3;

import java.util.List;

public interface Transformer {
    List<CsvRow> transform(List<CsvRow> rows) throws ETLException;
}

