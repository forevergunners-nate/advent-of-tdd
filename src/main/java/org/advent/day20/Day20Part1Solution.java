package org.advent.day20;

import org.advent.day19.Expression;
import org.advent.day19.Rating;
import org.advent.day19.Workflow;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Day20Part1Solution {
    private Map<String, Module> moduleMap;
    private Broadcast broadcast;

    private List<Conjunction> conjunctions;
    private long lowTotal = 0L;
    private long highTotal = 0L;

    public Day20Part1Solution(String filePath) throws IOException, URISyntaxException {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        Path path = Paths.get(classloader.getResource(filePath).toURI());
        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        this.moduleMap = new HashMap<>();
        this.conjunctions = new ArrayList<>();
        int i = 0;
        for (String line : lines) {
            processLine(line);
            i++;
        }
        initConjunctions();
        //System.out.println(Arrays.deepToString(this.moduleMap.entrySet().toArray()));
        int runTimes = 1000;
        PulseCounter counter = new PulseCounter();
        //int testRound = 0;
        //int testOrder = 1;
        for (int j = 0; j < runTimes; j++) {
            Queue<ProcessOrder> queue = new LinkedList<>();
            Pulse initPulse = new Pulse("Button", Pulse.PulseSignal.LOW);
            ProcessOrder processOrder = new ProcessOrder(this.broadcast.getModuleName(), initPulse);
            queue.offer(processOrder);
            while (!queue.isEmpty()) {
                //System.out.println(Arrays.toString(queue.toArray()));
                ProcessOrder currentOrder = queue.poll();
                //System.out.println(currentOrder);
                Module currentModule = this.moduleMap.computeIfAbsent(currentOrder.module(),
                        k -> new DummyModule(Module.ModuleType.Output, "output"));
                Pulse incoming = currentOrder.incoming();
                currentModule.receive(incoming, counter);
                //System.out.printf("%s -%s -> %s%n", incoming.from(), incoming.pulseSignal(), currentModule.getModuleName());
                Pulse nextPulse = currentModule.getNextPulse(incoming);
                if (null != nextPulse) {
                    for (String mName : currentModule.getToList()) {
                        ProcessOrder nextOrder = new ProcessOrder(mName, nextPulse);
                        //System.out.println("added " + (testOrder++) + " " + nextOrder);
                        queue.offer(nextOrder);
                    }
                }
                //testRound++;
            }
        }
        this.lowTotal = counter.getLowPulse();
        this.highTotal = counter.getHighPulse();
        //System.out.println(counter);
    }

    private void initConjunctions() {
        for (Conjunction conjunction : this.conjunctions) {
            Set<String> sources = this.moduleMap.values().stream()
                    .filter(module -> module.getToList().contains(conjunction.getModuleName()))
                    .map(Module::getModuleName).collect(Collectors.toSet());
            conjunction.setSources(sources);
            conjunction.initializeMemory();
        }
    }

    private void processLine(String line) {
        //System.out.println("Processing: " + line);
        //broadcaster -> a, b, c
        //%a -> b
        //%b -> c
        //%c -> inv
        //&inv -> a
        String[] params = line.split("->");
        String moduleName = params[0].trim();
        Module newModule = null;
        if (moduleName.startsWith(Module.ModuleType.FlipFlop.getIdentifier())) {
            moduleName = moduleName.replace(Module.ModuleType.FlipFlop.getIdentifier(), "");
            newModule = new FlipFlopModule(Module.ModuleType.FlipFlop, moduleName);
        } else if (moduleName.startsWith(Module.ModuleType.Conjunction.getIdentifier())) {
            moduleName = moduleName.replace(Module.ModuleType.Conjunction.getIdentifier(), "");
            newModule = new Conjunction(Module.ModuleType.Conjunction, moduleName);
            this.conjunctions.add((Conjunction) newModule);
        } else if (moduleName.equals(Module.ModuleType.Broadcaster.getIdentifier())) {
            newModule = new Broadcast(Module.ModuleType.Broadcaster, moduleName);
            this.broadcast = (Broadcast) newModule;
        } else {
            newModule = new DummyModule(Module.ModuleType.Output, moduleName);
        }
        String toListString = params[1].trim();
        StringTokenizer stringTokenizer = new StringTokenizer(toListString, ",");
        while (stringTokenizer.hasMoreTokens()) {
            String current = stringTokenizer.nextToken();
            newModule.addDestinationModule(current.trim());
        }
        //System.out.println("New Module create:" + newModule);
        this.moduleMap.put(moduleName, newModule);
    }


    public long solve() {
        return this.lowTotal * this.highTotal;
    }
}
