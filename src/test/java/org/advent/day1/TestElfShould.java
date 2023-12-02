package org.advent.day1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.fail;

public class TestElfShould {

    // Input and Output from first Elf
    static Stream<Arguments> getInputForFirstElf() {
        return Stream.of(
                Arguments.arguments(1000, 2000, 3000, 6000)
        );
    }

    // Input and Output from second Elf
    static Stream<Arguments> getInputForSecondElf() {
        return Stream.of(
                Arguments.arguments(4000, 4000)
        );
    }

    // Input and Output from third Elf
    static Stream<Arguments> getInputForThirdElf() {
        return Stream.of(
                Arguments.arguments(5000, 6000, 11000)
        );
    }

    // Input and Output from fourth Elf
    static Stream<Arguments> getInputForFourthElf() {
        return Stream.of(
                Arguments.arguments(7000, 8000, 9000, 24000)
        );
    }

    // Input and Output from fifth Elf
    static Stream<Arguments> getInputForFifthElf() {
        return Stream.of(
                Arguments.arguments(10000, 10000)
        );
    }
    @Test
    void have_zero_total_calories_when_created() {
        Elf elf = new Elf();
        assertThat(elf.getTotalCalories(), equalTo(0));
    }

    @Test
    void have_1000_total_calories_after_adding_1000() {
        Elf elf = new Elf();
        elf.addCalories(1000);
        assertThat(elf.getTotalCalories(), equalTo(1000));
    }

    @Test
    void compare_return_positive_value_for_elf_with_more_total_calories() {
        Elf bigElf = new Elf();
        bigElf.addCalories(5000);
        Elf smallElf = new Elf();
        smallElf.addCalories(3000);
        assertThat(bigElf.compareTo(smallElf), greaterThan(0));
    }

    @Test
    void compare_zero_for_equal_elf() {
        Elf elf = new Elf();
        elf.addCalories(5000);
        assertThat(elf.compareTo(elf), equalTo(0));
    }
    @Test
    void compare_return_negative_value_for_elf_with_less_total_calories() {
        Elf bigElf = new Elf();
        bigElf.addCalories(5000);
        Elf smallElf = new Elf();
        smallElf.addCalories(3000);
        assertThat(smallElf.compareTo(bigElf), lessThan(0));
    }

    @ParameterizedTest
    @MethodSource("getInputForFirstElf")
    void first_elf_should_be_carrying_the_sum_of_1k_2k_3k_equals_6k(int input1, int input2, int input3, int expectedResult) {
        // Given
        Elf elf = new Elf();
        // When
        elf.addCalories(input1);
        elf.addCalories(input2);
        elf.addCalories(input3);
        // Then
        assertThat(elf.getTotalCalories(), equalTo(expectedResult));
    }

    @ParameterizedTest
    @MethodSource("getInputForSecondElf")
    void second_elf_is_carrying_only_a_single_item_so_should_have_a_calorie_count_of_4k(int input, int expectedResult) {
        // Given
        Elf elf = new Elf();
        // When
        elf.addCalories(input);
        // Then
        assertThat(elf.getTotalCalories(), equalTo(expectedResult));
    }
    @ParameterizedTest
    @MethodSource("getInputForThirdElf")
    void third_elf_should_be_carrying_the_sum_of_5k_6k_equals_11k_calories(int input1, int input2, int expectedResult) {
        // Given
        Elf elf = new Elf();
        // When
        elf.addCalories(input1);
        elf.addCalories(input2);
        // Then
        assertThat(elf.getTotalCalories(), equalTo(expectedResult));
    }

    @ParameterizedTest
    @MethodSource("getInputForFourthElf")
    void fourth_elf_should_be_carrying_the_sum_of_7k_8k_9k_equals_24k_calories(int input1, int input2, int input3, int expectedResult) {
        // Given
        Elf elf = new Elf();
        // When
        elf.addCalories(input1);
        elf.addCalories(input2);
        elf.addCalories(input3);
        // Then
        assertThat(elf.getTotalCalories(), equalTo(expectedResult));
    }

    @ParameterizedTest
    @MethodSource("getInputForFifthElf")
    void fifth_elf_is_carrying_only_a_single_item_so_should_have_a_calorie_count_of_10k(int input, int expectedResult) {
        // Given
        Elf elf = new Elf();
        // When
        elf.addCalories(input);
        // Then
        assertThat(elf.getTotalCalories(), equalTo(expectedResult));
    }


}