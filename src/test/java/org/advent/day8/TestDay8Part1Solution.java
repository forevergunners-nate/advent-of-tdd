package org.advent.day8;

import org.advent.day7.Day7Part1Solution;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class TestDay8Part1Solution {

    Day8Part1Solution solution;
    @Test
    void a_test_solution() {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream("day8/day8-part1-input-sample.txt");
        InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(streamReader);
        solution = new Day8Part1Solution(reader);
        assertThat(solution.solve(), equalTo(6L));
    }

    @Test
    void b_test_solution_puzzle_input() {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream("day8/day8-part1-input.txt");
        InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(streamReader);
        solution = new Day8Part1Solution(reader);
        assertThat(solution.solve(), equalTo(6L));
    }
}
