package org.howard.edu.lsp.assignment3;

public class ETLException extends Exception {
    public ETLException(String msg) {
        super(msg);
    }
    public ETLException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
