package org.advent.day6;

import org.advent.day5.Day5Part1Solution;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class TestDay6Part1Solution {

    Day6Part1Solution solution;
    @Test
    void test_solution() {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream("day6-input-sample.txt");
        InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(streamReader);
        solution = new Day6Part1Solution(reader);
        assertThat(solution.solve(), equalTo(288L));
    }

    @Test
    void test_solution_puzzle_input() {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream("day6-input.txt");
        InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(streamReader);
        solution = new Day6Part1Solution(reader);
        assertThat(solution.solve(), equalTo(288L));
    }
}
