package com.ozzie.advantofcode.intcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum Mode {
    POSITION("0"), IMMEDIATE("1"), RELATIVE("2");

    private static Map<String, Mode> stringModeHashMap = new HashMap<>();

    static {
        Arrays.stream(Mode.values()).forEach(
                mode -> stringModeHashMap.put(mode.stringValue, mode)
        );
    }

    public static Mode of(String modeString) {
        return stringModeHashMap.get(modeString);
    }

    private final String stringValue;

    Mode(String stringValue) {
        this.stringValue = stringValue;
    }
}
