package org.advent.day20;

public class PulseCounter {
    private long lowPulse;
    private long highPulse;

    public PulseCounter() {
        this.lowPulse = 0L;
        this.highPulse = 0L;
    }
    public void recordCount(Pulse pulse) {
        if (Pulse.PulseSignal.HIGH.equals(pulse.pulseSignal())) {
            this.highPulse++;
        } else {
            this.lowPulse++;
        }
    }

    @Override
    public String toString() {
        return "PulseCounter{" +
                "lowPulse=" + lowPulse +
                ", highPulse=" + highPulse +
                '}';
    }

    public long getLowPulse() {
        return lowPulse;
    }

    public long getHighPulse() {
        return highPulse;
    }
}
