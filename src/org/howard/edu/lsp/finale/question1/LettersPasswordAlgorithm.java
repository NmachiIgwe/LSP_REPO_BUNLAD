package org.howard.edu.lsp.finale.question1;

import java.security.SecureRandom;

/**
 * Password generation algorithm that produces letters only (A–Z, a–z).
 */
public class LettersPasswordAlgorithm implements PasswordAlgorithm {

    private static final String LETTERS =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
            "abcdefghijklmnopqrstuvwxyz";

    private final SecureRandom secureRandom = new SecureRandom();

    @Override
    public String generate(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("Length must be positive.");
        }

        StringBuilder password = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = secureRandom.nextInt(LETTERS.length());
            char c = LETTERS.charAt(index);
            password.append(c);
        }
        return password.toString();
    }
}
