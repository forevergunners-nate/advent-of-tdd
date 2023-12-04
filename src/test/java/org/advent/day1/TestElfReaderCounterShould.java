package org.advent.day1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class TestElfReaderCounterShould {
    private ElfCalorieCounter underTest;
    @Mock
    BufferedReader mockReader;

    @Test
    void test_find_the_max_calories_value_with_day1_test_file() {
        // Given
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream("day1-elf-calories.txt");
        InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(streamReader);
        underTest= new ElfCalorieCounter(reader);
        // When
        int result = underTest.maxElfCalories();
        // Then
        assertThat(result, equalTo(24000));
    }

    @Test
    void test_throws_runtimee_when_reader_throws_ioe() throws IOException {
        // Given
        Mockito.when(mockReader.readLine()).thenThrow(IOException.class);
        underTest = new ElfCalorieCounter(mockReader);
        // Then
        assertThrows(RuntimeException.class, () -> underTest.maxElfCalories());
    }
    @Test
    void test_find_the_max_calories_value_with_mock_case1() throws IOException {
        // Test case1:
        // Creates an Elf when the file has two lines with 1000 and 3000 with value of 4000
        // Given
        Mockito.when(mockReader.readLine()).thenReturn("1000", "3000", null);
        underTest = new ElfCalorieCounter(mockReader);
        // When
        int result = underTest.maxElfCalories();
        // Then
        assertThat(result, equalTo(4000));
    }

    @Test
    void test_find_the_max_calories_value_with_mock_case2() throws IOException {
        // Test case2:
        // Creates a List when the file has 5 lines with 1000, 3000, blank line, 5000, 5000
        // test that it returns 10,000
        // Given
        Mockito.when(mockReader.readLine()).thenReturn("1000", "3000", "", "5000", "5000", null);
        underTest = new ElfCalorieCounter(mockReader);
        // When
        int result = underTest.maxElfCalories();
        // Then
        assertThat(result, equalTo(10000));
    }
}
