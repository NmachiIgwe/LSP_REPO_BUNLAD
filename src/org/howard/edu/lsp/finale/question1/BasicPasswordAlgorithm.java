package org.howard.edu.lsp.finale.question1;

import java.util.Random;

/**
 * Basic password generation algorithm.
 * Uses {@link java.util.Random} and produces digits only (0–9).
 */
public class BasicPasswordAlgorithm implements PasswordAlgorithm {

    private static final String DIGITS = "0123456789";
    private final Random random = new Random();

    @Override
    public String generate(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("Length must be positive.");
        }

        StringBuilder password = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(DIGITS.length());
            char c = DIGITS.charAt(index);
            password.append(c);
        }
        return password.toString();
    }
}
