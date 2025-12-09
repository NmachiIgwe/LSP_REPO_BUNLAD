package org.howard.edu.lsp.finale.question1;

import java.security.SecureRandom;

/**
 * Enhanced password generation algorithm.
 * Uses {@link java.security.SecureRandom} and produces characters from
 * A–Z, a–z, and 0–9.
 */
public class EnhancedPasswordAlgorithm implements PasswordAlgorithm {

    private static final String ALLOWED =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
            "abcdefghijklmnopqrstuvwxyz" +
            "0123456789";

    private final SecureRandom secureRandom = new SecureRandom();

    @Override
    public String generate(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("Length must be positive.");
        }

        StringBuilder password = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = secureRandom.nextInt(ALLOWED.length());
            char c = ALLOWED.charAt(index);
            password.append(c);
        }
        return password.toString();
    }
}
