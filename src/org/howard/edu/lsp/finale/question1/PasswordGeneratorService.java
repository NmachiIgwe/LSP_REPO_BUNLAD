package org.howard.edu.lsp.finale.question1;

/**
 * Service responsible for generating passwords using pluggable algorithms.
 * <p>
 * This class exposes a singleton instance and delegates the actual
 * password-generation behavior to a {@link PasswordAlgorithm} strategy
 * selected at runtime.
 */
public class PasswordGeneratorService {

    /**
     * The single shared instance of this service (Singleton pattern).
     */
    private static final PasswordGeneratorService INSTANCE =
            new PasswordGeneratorService();

    /**
     * The currently selected password generation strategy (Strategy pattern).
     */
    private PasswordAlgorithm algorithm;

    /**
     * Private constructor to enforce Singleton behavior.
     */
    private PasswordGeneratorService() {
        // prevent external instantiation
    }

    /**
     * Returns the single shared instance of the PasswordGeneratorService.
     *
     * @return the singleton instance
     */
    public static PasswordGeneratorService getInstance() {
        return INSTANCE;
    }

    /**
     * Selects the password generation algorithm by name.
     * <ul>
     *     <li>"basic"    — digits only (0–9), uses {@code java.util.Random}</li>
     *     <li>"enhanced" — A–Z, a–z, 0–9, uses {@code java.security.SecureRandom}</li>
     *     <li>"letters"  — letters only (A–Z, a–z)</li>
     * </ul>
     *
     * @param name the algorithm name ("basic", "enhanced", or "letters"),
     *             case-insensitive
     * @throws IllegalArgumentException if the name is null or not recognized
     */
    public void setAlgorithm(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Algorithm name must not be null.");
        }

        String normalized = name.toLowerCase().trim();
        switch (normalized) {
            case "basic":
                this.algorithm = new BasicPasswordAlgorithm();
                break;
            case "enhanced":
                this.algorithm = new EnhancedPasswordAlgorithm();
                break;
            case "letters":
                this.algorithm = new LettersPasswordAlgorithm();
                break;
            default:
                throw new IllegalArgumentException(
                        "Unknown algorithm name: " + name
                );
        }
    }

    /**
     * Generates a password of the given length using the currently selected
     * algorithm.
     *
     * @param length the desired password length; must be positive
     * @return the generated password string
     * @throws IllegalStateException    if no algorithm has been selected
     * @throws IllegalArgumentException if length is not positive
     */
    public String generatePassword(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("Length must be positive.");
        }
        if (algorithm == null) {
            throw new IllegalStateException(
                    "Password algorithm has not been selected. " +
                    "Call setAlgorithm(String) before generating passwords."
            );
        }
        return algorithm.generate(length);
    }

    /**
     * Package-private helper to clear the algorithm.
     * <p>
     * This method exists to support unit testing of the
     * "generate without setting algorithm" behavior while
     * preserving the Singleton instance.
     */
    void resetAlgorithmForTesting() {
        this.algorithm = null;
    }

    /*
     * =======================================================
     *  Design Pattern Documentation (Part C)
     * =======================================================
     *
     * 1. Patterns used:
     *    - Singleton:
     *      The PasswordGeneratorService class is implemented as a Singleton.
     *      It exposes a single shared instance through the static
     *      getInstance() method and has a private constructor.
     *
     *    - Strategy:
     *      PasswordAlgorithm defines a strategy interface for password
     *      generation. Concrete strategies (BasicPasswordAlgorithm,
     *      EnhancedPasswordAlgorithm, LettersPasswordAlgorithm) implement
     *      different generation behaviors. The service holds a reference to
     *      a PasswordAlgorithm and delegates generatePassword(int) calls to
     *      that strategy.
     *
     * 2. Why these patterns are appropriate:
     *    - Strategy:
     *      The requirements specify support for multiple password-generation
     *      approaches, runtime selection of the approach, swappable behavior,
     *      and easy future expansion without modifying client code.
     *      Strategy addresses all of these:
     *        * Each algorithm is encapsulated in its own class that
     *          implements a common interface.
     *        * setAlgorithm(String) selects which concrete strategy is used
     *          at runtime.
     *        * New algorithms can be added by creating new classes that
     *          implement PasswordAlgorithm, without changing client code
     *          that calls generatePassword(int).
     *        * Behavior is swappable by changing the strategy object held
     *          by the service.
     *
     *    - Singleton:
     *      The system architects require "a single shared access point" and
     *      that "only one instance of the service may exist." The Singleton
     *      pattern guarantees exactly one instance of
     *      PasswordGeneratorService, accessible globally via getInstance(),
     *      ensuring consistent behavior and configuration across the
     *      application.
     *
     *    Together, Singleton + Strategy satisfy all five architectural
     *    expectations listed in Part A.
     */
}
