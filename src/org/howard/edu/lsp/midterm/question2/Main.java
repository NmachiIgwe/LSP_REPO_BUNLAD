package org.howard.edu.lsp.midterm.question2;

public class Main {
    public static void main(String[] args) {
        // Required output
        System.out.println("Circle radius 3.0 → area = " + AreaCalculator.area(3.0));
        System.out.println("Rectangle 5.0 x 2.0 → area = " + AreaCalculator.area(5.0, 2.0));
        System.out.println("Triangle base 10, height 6 → area = " + AreaCalculator.area(10, 6));
        System.out.println("Square side 4 → area = " + AreaCalculator.area(4));

        // Demonstrate exception handling
        try {
            System.out.println("Circle radius -2.0 → area = " + AreaCalculator.area(-2.0));
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /*
     * Conceptual Explanation:
     * Method overloading is the better design choice because it allows multiple versions
     * of the same logical operation (“area”) to coexist under one consistent method name.
     * Using separate names (e.g., circleArea, rectangleArea) would clutter the API and
     * reduce readability since all methods conceptually perform the same function.
     */

}
