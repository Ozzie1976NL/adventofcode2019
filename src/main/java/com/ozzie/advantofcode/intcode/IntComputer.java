package com.ozzie.advantofcode.intcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class IntComputer {
    public static boolean debug = false;

    private final Queue<Long> inputQueue;
    private final Queue<Long> outputQueue;
    private final long[]      memory;
    private       State       state              = State.READY;
    private       int         instructionPointer = 0;
    private       int         relativeBase       = 0;
    private       Opcode      opcode;
    private       List<Mode>  paramModes         = new LinkedList<>();
    private       long        param1             = 0;
    private       long        param2             = 0;
    private       long        param3             = 0;
    int outputOffset = 0;


    public IntComputer(String program, Queue<Long> inputQueue, Queue<Long> outputQueue) {
        this(Arrays.stream(program.split(",")).mapToLong(Long::valueOf).toArray(), inputQueue,outputQueue);
    }

    public IntComputer(long[] program, Queue<Long> inputQueue, Queue<Long> outputQueue) {
        this.inputQueue = inputQueue;
        this.outputQueue = outputQueue;
        this.memory = new long[program.length * 10];
        System.arraycopy(program, 0, this.memory, 0, program.length);
    }

    public State run() {
        do {
            runCurrentInstruction();
        } while (State.READY.equals(state));
        return state;
    }

    public State runCurrentInstruction() {
        if (debug) {
            System.out.println(">>>Start<<<");
            System.out.println(String.format("Instuction pointer: %d, relative base %d", instructionPointer, relativeBase));
        }
        decodeInstruction(memory[instructionPointer]);
        decodeParameters();
        executeInstruction();
        if (debug) {
            System.out.println(">>>Done<<<");
        }
        return state;
    }


    private void executeInstruction() {
        // Execute command
        switch (opcode) {
            case ADD:
                memory[outputOffset] = param1 + param2;
                break;
            case MULTIPLY:
                memory[outputOffset] = param1 * param2;
                break;
            case JUMP_IF_FALSE:
                if (param1 == 0) {
                    instructionPointer = (int) param2;
                    return;
                }
                break;
            case JUMP_IF_TRUE:
                if (param1 != 0) {
                    instructionPointer = (int) param2;
                    return;
                }
                break;
            case LESS_THAN:
                if (param1 < param2) {
                    memory[outputOffset] = 1;
                } else {
                    memory[outputOffset] = 0;
                }
                break;
            case EQUALS:
                if (param1 == param2) {
                    memory[outputOffset] = 1;
                } else {
                    memory[outputOffset] = 0;
                }
                break;
            case INPUT_INT:
                if (inputQueue.isEmpty()) {
                    state = State.WAITING_FOR_INPUT;
                    return;
                } else {
                    final Long inputFromQueue = inputQueue.remove();
                    if (debug)
                        System.out.println("Read " + inputFromQueue + " from queue.");
                    memory[outputOffset] = inputFromQueue;
                }
                break;
            case OUTPUT:
                if (debug)
                    System.out.println(param1);
                outputQueue.add(param1);
                break;
            case ADJUST_RELATIVE_BASE:
                relativeBase += param1;
                break;
            case HALT:
                state = State.HALTED;
                return;
            default:
                throw new IllegalArgumentException("Unsupported opcode");
        }
        instructionPointer += opcode.getNoParameters() + 1;
        state = State.READY;
    }

    void decodeParameters() {
        param1 = 0;
        param2 = 0;
        param3 = 0;
        outputOffset = 0;

        // Determine parameters
        switch (opcode) {
            case OUTPUT:
            case ADJUST_RELATIVE_BASE:
                param1 = determineParameterValue(1);
                break;
            case ADD:
            case MULTIPLY:
            case LESS_THAN:
            case EQUALS:
                param1 = determineParameterValue(1);
                param2 = determineParameterValue(2);
                outputOffset = determineOutputOffset(3);
                break;
            case JUMP_IF_FALSE:
            case JUMP_IF_TRUE:
                param1 = determineParameterValue(1);
                param2 = determineParameterValue(2);
                break;
            case INPUT_INT:
                outputOffset = determineOutputOffset(1);
                break;
        }
    }

    void decodeInstruction(Long instruction) {
        String stringInstruction = String.format("%05d", instruction);
        if (debug) System.out.println("Decoding " + stringInstruction);
        paramModes.clear();
        opcode = Opcode.of(stringInstruction.substring(3, 5));
        if (Opcode.HALT.equals(opcode)) {
            return; // No sense to continue
        }
        for (int i = 0; i < opcode.getNoParameters(); i++) {
            paramModes.add(Mode.of(stringInstruction.substring(3 - i - 1, 3 - i)));
        }
        if (debug) printDebugOpcodeMemory();
    }

    private void printDebugOpcodeMemory() {
        System.out.print("memory: ");
        for (int i = instructionPointer; i < instructionPointer + 1 + opcode.getNoParameters(); i++) {
            System.out.print(String.format("%10d", memory[i]));
        }
        System.out.println();
        System.out.println("opcode: " + opcode);
        for (int i = 0; i < paramModes.size(); i++) {
            System.out.println(String.format("Mode param %d: %s", i, paramModes.get(i)));
        }
    }

    private Long determineParameterValue(int paramNo) {
        long paramValue;
        switch (paramModes.get(paramNo - 1)) {
            case POSITION:
                paramValue = memory[(int) memory[instructionPointer + paramNo]];
                break;
            case IMMEDIATE:
                paramValue = memory[instructionPointer + paramNo];
                break;
            case RELATIVE:
                paramValue = memory[relativeBase + (int) memory[instructionPointer + paramNo]];
                break;
            default:
                throw new IllegalArgumentException("Unsupported mode");
        }
        if (debug) System.out.println(String.format("Value of param no %d: %d", paramNo, paramValue));
        return paramValue;
    }

    private int determineOutputOffset(int paramNo) {
        int outputOffset;
        switch (paramModes.get(paramNo - 1)) {
            case POSITION:
                outputOffset = (int) memory[instructionPointer + paramNo];
                break;
            case RELATIVE:
                outputOffset = relativeBase + (int)memory[instructionPointer + paramNo];
                break;
            case IMMEDIATE:
            default:
                throw new IllegalArgumentException("Unsupported mode");
        }
        if (debug)
            System.out.println(String.format("Output based on mode %s of param no %d: %d", paramModes.get(paramNo - 1), paramNo, outputOffset));
        return outputOffset;
    }


    public State getState() {
        return state;
    }

    public Opcode getOpcode() {
        return opcode;
    }

    public List<Mode> getParamModes() {
        return paramModes;
    }

    public long getParam1() {
        return param1;
    }

    public long getParam2() {
        return param2;
    }

    public long getParam3() {
        return param3;
    }

    public int getInstructionPointer() {
        return instructionPointer;
    }

    public Queue<Long> getInputQueue() {
        return inputQueue;
    }

    public Queue<Long> getOutputQueue() {
        return outputQueue;
    }

    public enum State {
        READY,
        HALTED,
        WAITING_FOR_INPUT
    }

    public long[] getMemory() {
        return memory;
    }
}
