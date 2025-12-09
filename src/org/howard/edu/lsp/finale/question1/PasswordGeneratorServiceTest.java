package org.howard.edu.lsp.finale.question1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * JUnit test suite for {@link PasswordGeneratorService}.
 * Verifies singleton behavior and correctness of each
 * password-generation algorithm.
 */
public class PasswordGeneratorServiceTest {

    private PasswordGeneratorService service;

    @BeforeEach
    public void setup() {
        service = PasswordGeneratorService.getInstance();
        // Ensure a clean state before each test
        service.resetAlgorithmForTesting();
    }

    /**
     * Verifies that the singleton instance is not null.
     */
    @Test
    public void checkInstanceNotNull() {
        assertNotNull(service, "getInstance() should never return null.");
    }

    /**
     * Verifies that getInstance() always returns the exact same object.
     */
    @Test
    public void checkSingleInstanceBehavior() {
        PasswordGeneratorService second = PasswordGeneratorService.getInstance();
        // Must be the SAME object in memory (true singleton)
        assertSame(service, second,
                "getInstance() should return the same singleton instance.");
    }

    /**
     * Verifies that generatePassword(int) throws IllegalStateException
     * if no algorithm has been selected.
     */
    @Test
    public void generateWithoutSettingAlgorithmThrowsException() {
        PasswordGeneratorService s = PasswordGeneratorService.getInstance();
        s.resetAlgorithmForTesting();  // ensure algorithm is not set

        assertThrows(IllegalStateException.class,
                () -> s.generatePassword(10),
                "Calling generatePassword without selecting an algorithm " +
                "must throw IllegalStateException.");
    }

    /**
     * Verifies that the "basic" algorithm generates the correct length
     * and digits only (0–9).
     */
    @Test
    public void basicAlgorithmGeneratesCorrectLengthAndDigitsOnly() {
        service.setAlgorithm("basic");
        String p = service.generatePassword(10);

        assertEquals(10, p.length(),
                "Basic algorithm should generate password of requested length.");
        assertTrue(p.chars().allMatch(Character::isDigit),
                "Basic algorithm must generate digits only (0–9).");
    }

    /**
     * Verifies that the "enhanced" algorithm generates the correct length
     * and uses only A–Z, a–z, and 0–9.
     */
    @Test
    public void enhancedAlgorithmGeneratesCorrectCharactersAndLength() {
        service.setAlgorithm("enhanced");
        String p = service.generatePassword(12);

        assertEquals(12, p.length(),
                "Enhanced algorithm should generate password of requested length.");
        assertTrue(
                p.chars().allMatch(ch -> Character.isLetterOrDigit(ch)),
                "Enhanced algorithm must generate only letters and digits (A–Z, a–z, 0–9)."
        );
    }

    /**
     * Verifies that the "letters" algorithm generates the correct length
     * and letters only (A–Z, a–z).
     */
    @Test
    public void lettersAlgorithmGeneratesLettersOnly() {
        service.setAlgorithm("letters");
        String p = service.generatePassword(8);

        assertEquals(8, p.length(),
                "Letters algorithm should generate password of requested length.");
        assertTrue(
                p.chars().allMatch(Character::isLetter),
                "Letters algorithm must generate letters only (A–Z, a–z)."
        );
    }

    /**
     * Verifies that switching algorithms at runtime changes the
     * observable behavior of password generation.
     */
    @Test
    public void switchingAlgorithmsChangesBehavior() {
        service.setAlgorithm("basic");
        String p1 = service.generatePassword(10);

        service.setAlgorithm("letters");
        String p2 = service.generatePassword(10);

        service.setAlgorithm("enhanced");
        String p3 = service.generatePassword(10);

        // Basic: digits only
        assertEquals(10, p1.length(),
                "Basic password should have length 10.");
        assertTrue(p1.chars().allMatch(Character::isDigit),
                "Basic password should contain digits only.");

        // Letters: letters only
        assertEquals(10, p2.length(),
                "Letters password should have length 10.");
        assertTrue(p2.chars().allMatch(Character::isLetter),
                "Letters password should contain letters only (no digits).");

        // Enhanced: letters and/or digits, but no other characters
        assertEquals(10, p3.length(),
                "Enhanced password should have length 10.");
        assertTrue(
                p3.chars().allMatch(Character::isLetterOrDigit),
                "Enhanced password should contain only letters or digits."
        );
    }
}
