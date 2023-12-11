package org.advent.day10;

import org.advent.day9.Day9Part1Solution;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class TestDay10Part1Solution {

    Day10Part1Solution solution;
    @Test
    void a_test_solution() throws IOException, URISyntaxException {
        solution = new Day10Part1Solution("day10/day10-part1-input-sample1.txt");
        assertThat(solution.solve(), equalTo(4L));
    }
    @Test
    void b_test_solution() throws IOException, URISyntaxException {
        solution = new Day10Part1Solution("day10/day10-part1-input-sample2.txt");
        assertThat(solution.solve(), equalTo(8L));
    }
    @Test
    void c_test_solution_puzzle_input() throws IOException, URISyntaxException {
        solution = new Day10Part1Solution("day10/day10-part1-input.txt");
        assertThat(solution.solve(), equalTo(8L));
    }
}
