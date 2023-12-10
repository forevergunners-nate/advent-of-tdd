package org.advent.day9;

public class SequenceValuePart2 {
    private long current;
    private SequenceValuePart2 prev;
    public SequenceValuePart2(long current, SequenceValuePart2 sequenceValue) {
        this.current = current;
        this.prev = sequenceValue;
    }

    public long getCurrent() {
        return current;
    }

    public void setCurrent(long current) {
        this.current = current;
    }

    public SequenceValuePart2 getPrev() {
        return prev;
    }

    public void setPrev(SequenceValuePart2 prev) {
        this.prev = prev;
    }

    @Override
    public String toString() {
        return "SequenceValuePart2{" +
                "current=" + current +
                ", prev=" + prev +
                '}';
    }
}
