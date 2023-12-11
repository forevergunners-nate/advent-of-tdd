package org.advent.day11;

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

public class TestDay11Part1Solution {

    Day11Part1Solution solution;
    @Test
    void a_test_solution() throws IOException, URISyntaxException {
        solution = new Day11Part1Solution("day11/day11-part1-input-sample.txt");
        assertThat(solution.solve(), equalTo(374L));
    }

    @Test
    void b_test_solution_puzzle_input() throws IOException, URISyntaxException {
        solution = new Day11Part1Solution("day11/day11-part1-input.txt");
        assertThat(solution.solve(), equalTo(374L));
    }
}
