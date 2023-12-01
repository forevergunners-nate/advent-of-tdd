package org.advent.day1;

/**
 * Elf that is assigned calories
 */
public class Elf implements Comparable<Elf> {

    private int totalCalories = 0;

    /**
     * Calories carried by the Elf
     * @return total calories
     */
    public int getTotalCalories() {
        return totalCalories;
    }

    @Override
    public int compareTo(Elf arg0) {
        int compareResult = 0;
        if (null != arg0) {
            if (this.totalCalories > arg0.getTotalCalories()) {
                compareResult = 1;
            } else if (this.totalCalories < arg0.getTotalCalories()) {
                compareResult = -1;
            }
        }
        return compareResult;
    }

    public boolean addCalories(int caloriesToBeAdded) {
        if (caloriesToBeAdded >= 0) {
            this.totalCalories += caloriesToBeAdded;
            return true; // return true when OPS addCalories is successful.
        }
        return false; // return false when OPS addCalories fails.
    }
}
