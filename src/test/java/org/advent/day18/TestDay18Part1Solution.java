package org.advent.day18;

import org.advent.day17.Day17Part1Solution;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class TestDay18Part1Solution {

    Day18Part1Solution solution;
    @Test
    void a_test_solution() throws IOException, URISyntaxException {
        solution = new Day18Part1Solution("day18/day18-part1-input-sample.txt");
        assertThat(solution.solve(), equalTo(62L));
    }

    @Test
    void b_test_solution_puzzle_input() throws IOException, URISyntaxException {
        solution = new Day18Part1Solution("day18/day18-part1-input.txt");
        assertThat(solution.solve(), equalTo(62L));
    }
}
