package com.ozzie.advantofcode.orbitmap;

import java.util.List;

public class OrbitMap {

    private final OrbitTree orbitTree;

    public OrbitMap(List<String> orbits) {


        orbitTree = new OrbitTree("COM");
        orbits.forEach(orbit -> {
            String[] orbitPair = orbit.split("\\)");
            orbitTree.add(orbitPair[0], orbitPair[1]);
        });
    }

    public int noOrbits() {
        return orbitTree.totalNoOrbits();
    }

    public int noOrbitTransfers(String from, String to) {
        return orbitTree.noMininumOrbitTransfers(from, to);
    }
}
