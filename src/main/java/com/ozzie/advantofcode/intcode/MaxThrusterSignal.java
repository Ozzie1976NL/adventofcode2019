package com.ozzie.advantofcode.intcode;

import com.ozzie.advantofcode.Permutations;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static com.ozzie.advantofcode.intcode.IntComputer.State.HALTED;

public class MaxThrusterSignal {

    private final long[] memory;

    public MaxThrusterSignal(long[] memory) {
        this.memory = memory;
    }

    public long thrusterSignal(long[] phaseSettings) {
        final Queue<Long> inputQueue = new LinkedList<>();
        final Queue<Long> outputQueue = new LinkedList<>();

        for (int i = 0; i < phaseSettings.length; i++) {
            inputQueue.clear();
            inputQueue.add((long)phaseSettings[i]);
            if (i == 0) {
                inputQueue.add(0L);
            } else {
                inputQueue.add(outputQueue.remove());
            }
            new IntComputer(Arrays.copyOf(memory, memory.length), inputQueue, outputQueue).run();
        }
        return outputQueue.remove();
    }


    public long thrusterSignalLoop(long[] phaseSettings) {
        // Create first amp computer
        List<IntComputer> ampComputers = new LinkedList<>();
        ampComputers.add(new IntComputer(Arrays.copyOf(memory, memory.length), new LinkedList<>(), new LinkedList<>()));
        // Create the ones in between and hook the last to the first one
        for (int i = 1; i < phaseSettings.length; i++) {

            if (i == (phaseSettings.length - 1)) {
                ampComputers.add(new IntComputer(
                        Arrays.copyOf(memory, memory.length),
                        ampComputers.get(i - 1).getOutputQueue(), ampComputers.get(0).getInputQueue()));
            } else {
                ampComputers.add(new IntComputer(
                        Arrays.copyOf(memory, memory.length),
                        ampComputers.get(i - 1).getOutputQueue(), new LinkedList<Long>()));
            }
        }

        // Add the phase setting to each correct input queue
        for (int i = 0; i < phaseSettings.length; i++) {
            ampComputers.get(i).getInputQueue().add(phaseSettings[i]);
        }

        // To start the process 0 is sent to AMP a
        ampComputers.get(0).getInputQueue().add(0L);

        // Keep running until the last amp computers halts.
        while (!HALTED.equals(ampComputers.get(ampComputers.size() - 1).getState())) {
            for (int i = 0; i < ampComputers.size() ; i++) {
                System.out.println();
                System.out.println(String.format("Running amp computer %d which has state %s", i, ampComputers.get(i).getState()));
                ampComputers.get(i).run();
            }
        }
        return ampComputers.get(ampComputers.size() - 1).getOutputQueue().remove();
    }

    public long determineMaxThrustSetting() {
        long maxThrust = 0;
        for (Long[] setting : Permutations.determinePermutations(new long[]{0, 1, 2, 3, 4})) {

            maxThrust = Math.max(thrusterSignal(Arrays.stream(setting).mapToLong(Long::longValue).toArray()), maxThrust);
        }
        return maxThrust;
    }

    public long determineMaxThrustSettingLocalLoop() {
        long maxThrust = 0;
        for (Long[] setting : Permutations.determinePermutations(new long[]{5, 6, 7, 8, 9})) {

            maxThrust = Math.max(thrusterSignalLoop(Arrays.stream(setting).mapToLong(Long::longValue).toArray()), maxThrust);
        }
        return maxThrust;
    }
}
