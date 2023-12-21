package org.advent.day20;

import java.util.Map;

public class Broadcast extends Module {
    public Broadcast(ModuleType type, String moduleName) {
        super(type, moduleName);
    }

    @Override
    public void receive(Pulse incoming, PulseCounter counter) {
        counter.recordCount(incoming);
    }

    @Override
    public Pulse getNextPulse(Pulse incoming) {
        return new Pulse(this.getModuleName(), Pulse.PulseSignal.LOW);

    }
}
