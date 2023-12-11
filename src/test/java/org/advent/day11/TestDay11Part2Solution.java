package org.advent.day11;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class TestDay11Part2Solution {

    Day11Part2Solution solution;
    @Test
    void a_test_solution() throws IOException, URISyntaxException {
        solution = new Day11Part2Solution("day11/day11-part1-input-sample.txt", 10);
        assertThat(solution.solve(), equalTo(1030L));
    }
    @Test
    void b_test_solution() throws IOException, URISyntaxException {
        solution = new Day11Part2Solution("day11/day11-part1-input-sample.txt", 100);
        assertThat(solution.solve(), equalTo(8410L));
    }
    @Test
    void c_test_solution_puzzle_input() throws IOException, URISyntaxException {
        solution = new Day11Part2Solution("day11/day11-part1-input.txt", 1000000);
        assertThat(solution.solve(), equalTo(8410L));
    }
}
