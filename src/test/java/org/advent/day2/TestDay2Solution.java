package org.advent.day2;

import org.advent.day1.Puzzle1Solution;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class TestDay2Solution {

    Day2Solution solution;
    @Test
    void test_solution() {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream("day2-input-sample.txt");
        InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(streamReader);
        solution = new Day2Solution(12, 13, 14,reader);
        assertThat(solution.sumOfIDsOfPossibleGames(), equalTo(8));
    }

    @Test
    void test_solution_puzzle_input() {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream("day2-input.txt");
        InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(streamReader);
        solution = new Day2Solution(12, 13, 14,reader);
        assertThat(solution.sumOfIDsOfPossibleGames(), equalTo(8));
    }
}
