package org.advent.day20;

import java.lang.invoke.SwitchPoint;
import java.util.Map;

public class FlipFlopModule extends Module {

    private SwitchStatus switchStatus;

    private SwitchStatus beforeFlipFlop;

    public FlipFlopModule(ModuleType type, String moduleName) {
        super(type, moduleName);
        this.switchStatus = SwitchStatus.OFF;
    }

    @Override
    public void receive(Pulse incoming, PulseCounter counter) {
        counter.recordCount(incoming);
        if (Pulse.PulseSignal.HIGH.equals(incoming.pulseSignal())) {
            return;
        } else if (Pulse.PulseSignal.LOW.equals(incoming.pulseSignal())) {
            this.beforeFlipFlop = this.switchStatus;
            flipFlop();
        }
    }

    @Override
    public Pulse getNextPulse(Pulse incoming) {
        if (Pulse.PulseSignal.HIGH.equals(incoming.pulseSignal())) {
            return null;
        }
        Pulse.PulseSignal signal = SwitchStatus.OFF.equals(this.beforeFlipFlop) ? Pulse.PulseSignal.HIGH : Pulse.PulseSignal.LOW;
        return new Pulse(this.getModuleName(), signal);
    }

    public void flipFlop() {
        if (SwitchStatus.INIT.equals(this.switchStatus) ||
                SwitchStatus.OFF.equals(this.switchStatus)) {
            this.switchStatus = SwitchStatus.ON;
        } else {
            this.switchStatus = SwitchStatus.OFF;
        }
    }

    enum SwitchStatus{
        INIT,
        ON,
        OFF;
    }
}
