package org.advent.day3;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class TestDay3Part2Solution {

    Day3Part2Solution solution;
    @Test
    void test_solution() throws IOException, URISyntaxException {
        solution = new Day3Part2Solution("day3-input-sample.txt");
        assertThat(solution.sumOfGearRatios(), equalTo(467835));
    }

    @Test
    void test_solution_puzzle_input() throws IOException, URISyntaxException {
        solution = new Day3Part2Solution("day3-input.txt");
        assertThat(solution.sumOfGearRatios(), equalTo(467835));
    }
}
