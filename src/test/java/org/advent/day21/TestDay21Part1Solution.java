package org.advent.day21;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class TestDay21Part1Solution {

    Day21Part1Solution solution;
    @Test
    void a_test_solution() throws IOException, URISyntaxException {
        solution = new Day21Part1Solution(6, "day21/day21-input-sample.txt");
        assertThat(solution.solve(), equalTo(16L));
    }

    @Test
    void b_test_solution_puzzle_input() throws IOException, URISyntaxException {
        solution = new Day21Part1Solution(64, "day21/day21-input.txt");
        assertThat(solution.solve(), equalTo(3762L));
    }
}
