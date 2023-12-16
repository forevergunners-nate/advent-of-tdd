package org.advent.day15;

import java.util.Objects;

public record Lens(String label, int length) {
    @Override
    public boolean equals(Object o) {
        Lens lens = (Lens) o;
        return Objects.equals(label, lens.label);
    }


    @Override
    public String toString() {
        return "[" +
                label + ' ' +
                length +
                ']';
    }
}
