package com.ozzie.advantofcode.intcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum Opcode {
    ADD("01", 3),
    MULTIPLY("02", 3),
    INPUT_INT("03", 1),
    OUTPUT("04", 1),
    JUMP_IF_TRUE("05", 2),
    JUMP_IF_FALSE("06", 2),
    LESS_THAN("07", 3),
    EQUALS("08", 3),
    ADJUST_RELATIVE_BASE("09", 1),
    HALT("99", 99);

    private static Map<String, Opcode> stringOpcodeHashMap = new HashMap<>();

    static {
        Arrays.stream(Opcode.values()).forEach(
                opcode -> stringOpcodeHashMap.put(opcode.opcodeString, opcode)
        );
    }

    public static Opcode of(String opcodeString) {
        return stringOpcodeHashMap.get(opcodeString);
    }

    private final String opcodeString;
    private final int    noParameters;

    Opcode(String opcodeString, int noParameters) {
        this.opcodeString = opcodeString;
        this.noParameters = noParameters;
    }

    public int getNoParameters() {
        return noParameters;
    }
}
