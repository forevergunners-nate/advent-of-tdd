package org.advent.day20;

import java.util.*;

public abstract class Module {

    private String moduleName;
    private ModuleType moduleType;
    private List<String> toList;
    private Set<String> sources;
    public ModuleType getModuleType() {
        return moduleType;
    }
    public Module(ModuleType type, String moduleName) {
        this.moduleName = moduleName;
        this.moduleType = type;
        this.toList = new ArrayList<>();
        this.sources = null;
    }
    public void addDestinationModule(String module) {
        this.toList.add(module);
    }
    public List<String> getToList() {
        return toList;
    }
    public String getModuleName() {
        return moduleName;
    }

    public Set<String> getSources() {
        return sources;
    }

    public void setSources(Set<String> sources) {
        this.sources = sources;
    }

    public abstract void receive(Pulse incoming, PulseCounter counter);
    public abstract Pulse getNextPulse(Pulse incoming);

    public enum ModuleType {
        FlipFlop("%"),
        Conjunction("&"),
        Broadcaster("broadcaster"),
        Button(" "),
        Output("Output")
        ; //

        private String identifier;
        ModuleType(String identifier) {
            this.identifier = identifier;
        }
        String getIdentifier(){
            return this.identifier;
        }
    }

    @Override
    public String toString() {
        return "Module{" +
                "moduleName='" + moduleName + '\'' +
                ", moduleType=" + moduleType +
                ", toList=" + toList +
                ", sources=" + sources +
                '}';
    }
}
