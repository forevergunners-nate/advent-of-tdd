package org.advent.day1;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestElfShould {
    @Test
    void have_zero_total_calories_when_created() {
        Elf elf = new Elf();
        assertThat(elf.getTotalCalories(), equalTo(0));
    }

    @Test
    void have_1000_total_calories_after_adding_1000() {
        // Given
        Elf elf = new Elf();
        int caloriesToBeAdded = 1000;
        int expectTotalCalories = 1000;
        // When
        elf.addCalories(caloriesToBeAdded);
        // Then
        assertThat(elf.getTotalCalories(), equalTo(expectTotalCalories));
    }

    @Test
    void compare_return_positive_value_for_elf_with_more_total_calories() {
        // Given
        Elf elf1 = new Elf();
        int caloriesToBeAdded1 = 2000;
        Elf elf2 = new Elf();
        int caloriesToBeAdded2 = 1000;
        // When
        elf1.addCalories(caloriesToBeAdded1);
        elf2.addCalories(caloriesToBeAdded2);
        // Then
        assertTrue(elf1.compareTo(elf2) > 0);
    }

    @Test
    void compare_zero_for_equal_elf() {
        // Given
        Elf elf1 = new Elf();
        int caloriesToBeAdded1 = 2000;
        Elf elf2 = new Elf();
        // When
        elf1.addCalories(caloriesToBeAdded1);
        elf2.addCalories(caloriesToBeAdded1);
        // Then
        assertTrue(elf1.compareTo(elf2) == 0);
    }

    @Test
    void compare_return_negative_value_for_elf_with_less_total_calories() {
        // Given
        Elf elf1 = new Elf();
        int caloriesToBeAdded1 = 1000;
        Elf elf2 = new Elf();
        int caloriesToBeAdded2 = 2000;
        // When
        elf1.addCalories(caloriesToBeAdded1);
        elf2.addCalories(caloriesToBeAdded2);
        // Then
        assertTrue(elf1.compareTo(elf2) < 0);
    }
}
