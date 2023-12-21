package org.advent.day20;

public record Pulse (String from, PulseSignal pulseSignal) {

    enum PulseSignal {
        LOW, HIGH;
    }
}
