package com.ozzie.advantofcode.day3;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Wire {
    private final List<Step> stepList;
    public Wire(String path) {
        stepList = Arrays.stream(path.split(","))
                .map(Step::new)
                .collect(Collectors.toList());
    }

    public List<Step> getStepList() {
        return Collections.unmodifiableList(stepList);
    }
}
