package org.advent.day17;

import org.advent.day16.Day16Part1Solution;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class TestDay17Part1Solution {

    Day17Part1Solution solution;
    @Test
    void a_test_solution() throws IOException, URISyntaxException {
        solution = new Day17Part1Solution("day17/day17-part1-input-sample.txt");
        assertThat(solution.solve(), equalTo(102L));
    }

    @Test
    void b_test_solution_puzzle_input() throws IOException, URISyntaxException {
        solution = new Day17Part1Solution("day17/day17-part1-input.txt");
        assertThat(solution.solve(), equalTo(102L));
    }
}
