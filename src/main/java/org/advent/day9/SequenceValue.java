package org.advent.day9;

public class SequenceValue {
    private long current;
    private SequenceValue next;
    public SequenceValue(long current, SequenceValue sequenceValue) {
        this.current = current;
        this.next = sequenceValue;
    }

    public long getCurrent() {
        return current;
    }

    public void setCurrent(long current) {
        this.current = current;
    }

    public SequenceValue getNext() {
        return next;
    }
    public void setNext(SequenceValue next) {
        this.next = next;
    }
    @Override
    public String toString() {
        return "SequenceValue{" +
                "current=" + current +
                ", next=" + next +
                '}';
    }
}
