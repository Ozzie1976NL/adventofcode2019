package com.ozzie.advantofcode.intcode

import spock.lang.Specification
import spock.lang.Unroll

public class InstructionTests extends Specification {

    @Unroll
    def "Test whether for the various opcodes the parameters are decoded correctly: #scenario"() {
        expect:
        long[] memory = [input]
        def intComputer = new IntComputer(memory, new LinkedList<Long>(), new LinkedList<Long>())
        intComputer.decodeInstruction(input)
        intComputer.getOpcode() == expectedOpcode
        intComputer.getParamModes() == expectedParamModes

        where:
        scenario                                   | input | expectedOpcode       | expectedParamModes
        "Add: all position mode"                   | 1     | Opcode.ADD           | [Mode.POSITION, Mode.POSITION, Mode.POSITION]
        "Add: all only first immediate"            | 101   | Opcode.ADD           | [Mode.IMMEDIATE, Mode.POSITION, Mode.POSITION]
        "Add: all only second immediate"           | 1001  | Opcode.ADD           | [Mode.POSITION, Mode.IMMEDIATE, Mode.POSITION]
        "Add: all only third immediate"            | 10001 | Opcode.ADD           | [Mode.POSITION, Mode.POSITION, Mode.IMMEDIATE]
        "Add: all immediate mode"                  | 11101 | Opcode.ADD           | [Mode.IMMEDIATE, Mode.IMMEDIATE, Mode.IMMEDIATE]
        "Multiply: all position mode"              | 2     | Opcode.MULTIPLY      | [Mode.POSITION, Mode.POSITION, Mode.POSITION]
        "Multiply: all only first immediate"       | 102   | Opcode.MULTIPLY      | [Mode.IMMEDIATE, Mode.POSITION, Mode.POSITION]
        "Multiply: all only second immediate"      | 1002  | Opcode.MULTIPLY      | [Mode.POSITION, Mode.IMMEDIATE, Mode.POSITION]
        "Multiply: all only third immediate"       | 10002 | Opcode.MULTIPLY      | [Mode.POSITION, Mode.POSITION, Mode.IMMEDIATE]
        "Multiply: all immediate mode"             | 11102 | Opcode.MULTIPLY      | [Mode.IMMEDIATE, Mode.IMMEDIATE, Mode.IMMEDIATE]
        "Input: position mode"                     | 3     | Opcode.INPUT_INT     | [Mode.POSITION]
        "Input: immediate mode"                    | 103   | Opcode.INPUT_INT     | [Mode.IMMEDIATE]
        "Input: relative mode"                     | 203   | Opcode.INPUT_INT     | [Mode.RELATIVE]
        "Output: position mode"                    | 4     | Opcode.OUTPUT        | [Mode.POSITION]
        "Output: immediate mode"                   | 104   | Opcode.OUTPUT        | [Mode.IMMEDIATE]
        "Jump-if-true: all position mode"          | 5     | Opcode.JUMP_IF_TRUE  | [Mode.POSITION, Mode.POSITION]
        "Jump-if-true: all only first immediate"   | 105   | Opcode.JUMP_IF_TRUE  | [Mode.IMMEDIATE, Mode.POSITION]
        "Jump-if-true: all only second immediate"  | 1005  | Opcode.JUMP_IF_TRUE  | [Mode.POSITION, Mode.IMMEDIATE]
        "Jump-if-true: all immediate mode"         | 1105  | Opcode.JUMP_IF_TRUE  | [Mode.IMMEDIATE, Mode.IMMEDIATE]
        "Jump-if-false: all position mode"         | 6     | Opcode.JUMP_IF_FALSE | [Mode.POSITION, Mode.POSITION]
        "Jump-if-false: all only first immediate"  | 106   | Opcode.JUMP_IF_FALSE | [Mode.IMMEDIATE, Mode.POSITION]
        "Jump-if-false: all only second immediate" | 1006  | Opcode.JUMP_IF_FALSE | [Mode.POSITION, Mode.IMMEDIATE]
        "Jump-if-false: all immediate mode"        | 1106  | Opcode.JUMP_IF_FALSE | [Mode.IMMEDIATE, Mode.IMMEDIATE]
        "Less-than: all position mode"             | 7     | Opcode.LESS_THAN     | [Mode.POSITION, Mode.POSITION, Mode.POSITION]
        "Less-than: all only first immediate"      | 107   | Opcode.LESS_THAN     | [Mode.IMMEDIATE, Mode.POSITION, Mode.POSITION]
        "Less-than: all only second immediate"     | 1007  | Opcode.LESS_THAN     | [Mode.POSITION, Mode.IMMEDIATE, Mode.POSITION]
        "Less-than: all only third immediate"      | 10007 | Opcode.LESS_THAN     | [Mode.POSITION, Mode.POSITION, Mode.IMMEDIATE]
        "Less-than: all immediate mode"            | 11107 | Opcode.LESS_THAN     | [Mode.IMMEDIATE, Mode.IMMEDIATE, Mode.IMMEDIATE]
        "Equals: all position mode"                | 8     | Opcode.EQUALS        | [Mode.POSITION, Mode.POSITION, Mode.POSITION]
        "Equals: all only first immediate"         | 108   | Opcode.EQUALS        | [Mode.IMMEDIATE, Mode.POSITION, Mode.POSITION]
        "Equals: all only second immediate"        | 1008  | Opcode.EQUALS        | [Mode.POSITION, Mode.IMMEDIATE, Mode.POSITION]
        "Equals: all only third immediate"         | 10008 | Opcode.EQUALS        | [Mode.POSITION, Mode.POSITION, Mode.IMMEDIATE]
        "Equals: all immediate mode"               | 11108 | Opcode.EQUALS        | [Mode.IMMEDIATE, Mode.IMMEDIATE, Mode.IMMEDIATE]
    }

    @Unroll
    def "Test execution of various instructions: #scenario"() {
        setup:
        long[] memory = inputMemory
        def inputQueue = new LinkedList<Long>()
        def outputQueue = new LinkedList<Long>()
        def intComputer = new IntComputer(memory, inputQueue, outputQueue)

        expect:
        intComputer.runCurrentInstruction()
        verifyAll {
            expectedInstructionPointer == intComputer.getInstructionPointer()
            if (resultOffset != null) {
                (long) expectedResultValue == intComputer.getMemory()[resultOffset]
            }
        }
        inputQueue.size() == 0
        outputQueue.size() == 0

        where:
        scenario                                                  | inputMemory                  | resultOffset | expectedResultValue | expectedInstructionPointer
        "Add: all position mode"                                  | [1, 5, 6, 7, 99, 3, 4, 0]    | 7            | 7                   | 4
        "Add: all immediate mode"                                 | [1101, 5, 6, 7, 99, 3, 4, 0] | 7            | 11                  | 4
        "Add: first immediate, second position"                   | [101, 5, 6, 7, 99, 3, 4, 0]  | 7            | 9                   | 4
        "Add: first position, second immediate"                   | [1001, 5, 6, 7, 99, 6, 4, 0] | 7            | 12                  | 4

        "Multiply: position mode: result true"                    | [2, 5, 6, 7, 99, 3, 4, 0]    | 7            | 12                  | 4
        "Multiply: immediate mode: result true"                   | [1102, 5, 6, 7, 99, 3, 3, 0] | 7            | 30                  | 4
        "Multiply: first immediate, second position"              | [102, 5, 6, 7, 99, 3, 4, 0]  | 7            | 20                  | 4
        "Multiply: first position, second immediate"              | [1002, 5, 6, 7, 99, 6, 4, 0] | 7            | 36                  | 4

        "Jump-if-true: position mode, first parameter non zero"   | [5, 4, 5, 99, 1, 7, 3]       | null         | null                | 7
        "Jump-if-true: position mode, first parameter zero"       | [5, 4, 5, 99, 0, 7, 3]       | null         | null                | 3
        "Jump-if-true: immediate mode, first parameter non zero"  | [1105, 4, 5, 99, 0, 7, 3]    | null         | null                | 5
        "Jump-if-true: immediate mode, first parameter zero"      | [1105, 0, 5, 99, 0, 7, 3]    | null         | null                | 3

        "Jump-if-false: position mode, first parameter non zero"  | [6, 4, 5, 99, 1, 7, 3]       | null         | null                | 3
        "Jump-if-false: position mode, first parameter zero"      | [6, 4, 5, 99, 0, 7, 3]       | null         | null                | 7
        "Jump-if-false: immediate mode, first parameter non zero" | [1106, 4, 5, 99, 0, 7, 3]    | null         | null                | 3
        "Jump-if-false: immediate mode, first parameter zero"     | [1106, 0, 5, 99, 0, 7, 3]    | null         | null                | 5

        "Less-than: position mode: result true"                   | [7, 5, 6, 7, 99, 3, 3, 0]    | 7            | 0                   | 4
        "Less-than: position mode: result false"                  | [7, 5, 6, 7, 99, 3, 4, 0]    | 7            | 1                   | 4
        "Less-than: immediate mode: result true"                  | [1107, 5, 6, 7, 99, 3, 3, 0] | 7            | 1                   | 4
        "Less-than: immediate mode: result false"                 | [1107, 5, 5, 7, 99, 3, 4, 0] | 7            | 0                   | 4

        "Equals: position mode: result true"                      | [8, 5, 6, 7, 99, 3, 3, 0]    | 7            | 1                   | 4
        "Equals: position mode: result false"                     | [8, 5, 6, 7, 99, 3, 4, 0]    | 7            | 0                   | 4
        "Equals: immediate mode: result true"                     | [1108, 5, 6, 7, 99, 3, 3, 0] | 7            | 0                   | 4
        "Equals: immediate mode: result false"                    | [1108, 5, 5, 7, 99, 3, 4, 0] | 7            | 1                   | 4
    }
}
