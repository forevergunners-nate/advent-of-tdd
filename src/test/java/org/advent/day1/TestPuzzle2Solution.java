package org.advent.day1;

import org.advent.day1.Puzzle2Solution;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class TestPuzzle2Solution {
    Puzzle2Solution solution;
    @Test
    public void test_with_sample_input() {
        // Given
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream("puzzle2-input-sample.txt");
        InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(streamReader);
        solution = new Puzzle2Solution(reader);
        // When
        // Then
        assertThat(solution.sumOfCalibrationValues(), equalTo(281));
    }

//    @Test
//    public void test_with_custom_input() {
//        // Given
//        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
//        InputStream is = classloader.getResourceAsStream("puzzle2-input.txt");
//        InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
//        BufferedReader reader = new BufferedReader(streamReader);
//        solution = new Puzzle2Solution(reader);
//        // When
//        // Then
//        assertThat(solution.sumOfCalibrationValues(), equalTo(281));
//    }
}
