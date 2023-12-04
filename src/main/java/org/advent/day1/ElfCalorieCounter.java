package org.advent.day1;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ElfCalorieCounter {
    private int maxElfCalories = Integer.MIN_VALUE;
    private BufferedReader currentReader;
    public ElfCalorieCounter(BufferedReader reader) {
        this.currentReader = reader;
    }

    public int maxElfCalories() {
        //List<Elf> elves = new ArrayList<>();
        try {
            Elf elf = null;
            for (String line; (line = this.currentReader.readLine()) != null; ) {
                if(null == elf || line.isBlank() || line.isEmpty()) {
                    if (elf != null) {
                        this.updateMaxCalories(elf.getTotalCalories());
                    }
                    elf = new Elf();
                }
                if (!(line.isBlank() || line.isEmpty())) {
                    elf.addCalories(Integer.valueOf(line));
                }
            }
            this.updateMaxCalories(elf.getTotalCalories());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //return 0;
        return this.maxElfCalories;
    }

    private void updateMaxCalories(int totalCalories) {
        this.maxElfCalories = Math.max(this.maxElfCalories, totalCalories);
    }
}
