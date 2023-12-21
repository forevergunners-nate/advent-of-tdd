package org.advent.day20;

import java.util.*;

public class Conjunction extends Module {
    private Map<String, Pulse> mostRecentReceived;

    public Conjunction(ModuleType type, String moduleName) {
        super(type, moduleName);
        this.mostRecentReceived = new HashMap<>();
    }

    public void initializeMemory() {
        for (String name : this.getSources()) {
            this.mostRecentReceived.put(name, new Pulse(null, Pulse.PulseSignal.LOW));
        }
    }
    @Override
    public void receive(Pulse incoming, PulseCounter counter) {
        counter.recordCount(incoming);
        this.mostRecentReceived.put(incoming.from(), incoming);
        //System.out.println("Module " + this.getModuleName() + " mostRecentReceived" + Arrays.toString(mostRecentReceived.entrySet().toArray()));
    }

    @Override
    public Pulse getNextPulse(Pulse incoming) {
        //Conjunction modules (prefix &) remember the type of the most recent pulse received from each of
        // their connected input modules;
        // they initially default to remembering a low pulse for each input.
        // When a pulse is received,
        // the conjunction module first updates its memory for that input.
        // Then, if it remembers high pulses for all inputs,
        // it sends a low pulse; otherwise, it sends a high pulse.
        int counter = 0;
        if (!mostRecentReceived.isEmpty()) {
            for (Map.Entry<String, Pulse> entry : this.mostRecentReceived.entrySet()) {
                if (Pulse.PulseSignal.HIGH.equals(entry.getValue().pulseSignal())) {
                    counter++;
                }
            }
        }
        boolean sendLow = counter == mostRecentReceived.size();
        return new Pulse(this.getModuleName(), sendLow ? Pulse.PulseSignal.LOW : Pulse.PulseSignal.HIGH);
    }

}
