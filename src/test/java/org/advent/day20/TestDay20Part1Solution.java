package org.advent.day20;

import org.advent.day19.Day19Part1Solution;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class TestDay20Part1Solution {

    Day20Part1Solution solution;
    @Test
    void a_test_solution() throws IOException, URISyntaxException {
        solution = new Day20Part1Solution("day20/day20-part1-input-sample.txt");
        assertThat(solution.solve(), equalTo(32000000L));
    }
    @Test
    void b_test_solution() throws IOException, URISyntaxException {
        solution = new Day20Part1Solution("day20/day20-part1-input-sample2.txt");
        assertThat(solution.solve(), equalTo(11687500L));
    }
    @Test
    void c_test_solution_puzzle_input() throws IOException, URISyntaxException {
        solution = new Day20Part1Solution("day20/day20-part1-input.txt");
        assertThat(solution.solve(), equalTo(401674L));
    }
}
