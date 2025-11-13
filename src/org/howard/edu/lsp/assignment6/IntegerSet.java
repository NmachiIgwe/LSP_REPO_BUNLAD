package org.howard.edu.lsp.assignment6;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a mathematical set of integers.
 * No duplicates allowed. Supports standard set operations.
 */
public class IntegerSet {
    private List<Integer> set = new ArrayList<>();

    /** Removes all elements from the set. */
    public void clear() {
        set.clear();
    }

    /** Returns the number of elements in the set. */
    public int length() {
        return set.size();
    }

    /**
     * Checks if two IntegerSets are equal.
     * Two sets are equal if they contain all of the same values, order-independent.
     * @param o the object to compare
     * @return true if the sets are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IntegerSet)) return false;
        IntegerSet other = (IntegerSet) o;
        return set.containsAll(other.set) && other.set.containsAll(set);
    }

    /** Returns true if the set contains the given value. */
    public boolean contains(int value) {
        return set.contains(value);
    }

    /** Returns the largest element in the set. Throws IllegalStateException if empty. */
    public int largest() {
        if (set.isEmpty()) throw new IllegalStateException();
        int max = set.get(0);
        for (int val : set) if (val > max) max = val;
        return max;
    }

    /** Returns the smallest element in the set. Throws IllegalStateException if empty. */
    public int smallest() {
        if (set.isEmpty()) throw new IllegalStateException();
        int min = set.get(0);
        for (int val : set) if (val < min) min = val;
        return min;
    }

    /** Adds an element to the set if it is not already present. */
    public void add(int item) {
        if (!set.contains(item)) set.add(item);
    }

    /** Removes an element from the set if it is present. */
    public void remove(int item) {
        set.remove(Integer.valueOf(item));
    }

    /** Adds all unique elements from another set to this set. */
    public void union(IntegerSet other) {
        for (int val : other.set) {
            if (!set.contains(val)) set.add(val);
        }
    }

    /** Keeps only elements that exist in both this set and another set. */
    public void intersect(IntegerSet other) {
        set.retainAll(other.set);
    }

    /** Removes all elements found in another set from this set. */
    public void diff(IntegerSet other) {
        set.removeAll(other.set);
    }

    /** Replaces this set with elements in the other set that are not in this set. */
    public void complement(IntegerSet other) {
        List<Integer> newSet = new ArrayList<>();
        for (int val : other.set) {
            if (!set.contains(val)) newSet.add(val);
        }
        set = newSet;
    }

    /** Returns true if the set has no elements. */
    public boolean isEmpty() {
        return set.isEmpty();
    }

    /** Returns a string representation of the set in square brackets, e.g., [1, 2, 3]. */
    @Override
    public String toString() {
        return set.toString();
    }
}
