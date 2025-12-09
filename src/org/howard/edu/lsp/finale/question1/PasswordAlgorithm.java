package org.howard.edu.lsp.finale.question1;

/**
 * Strategy interface for password generation algorithms.
 * Different implementations provide different character sets
 * and randomness sources.
 */
public interface PasswordAlgorithm {
    /**
     * Generate a password of the given length.
     *
     * @param length the desired password length; must be positive
     * @return the generated password string
     * @throws IllegalArgumentException if length is not positive
     */
    String generate(int length);
}
