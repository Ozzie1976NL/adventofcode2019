package com.ozzie.advantofcode.orbitmap;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class OrbitTree {

    private final Node              root;
    private       Map<String, Node> valueNodeMap                = new HashMap<>();
    private       Set<Integer>      differentNoOfOrbitTransfers = new HashSet<>();

    public OrbitTree(String rootValue) {
        this.root = new Node(rootValue);
        valueNodeMap.put(rootValue, root);
    }

    public void add(String parentValue, String childValue) {

        if (!valueNodeMap.containsKey(parentValue)) {
            valueNodeMap.put(parentValue, new Node(parentValue));
        }
        if (!valueNodeMap.containsKey(childValue)) {
            valueNodeMap.put(childValue, new Node(childValue));
        }
        final Node child = valueNodeMap.get(childValue);
        final Node parent = valueNodeMap.get(parentValue);
        parent.addChild(child);
        child.setParent(parent);
    }

    public int totalNoOrbits() {
        return valueNodeMap.values().stream().map(this::countNoOrbits).mapToInt(Integer::intValue).sum();
    }

    private int countNoOrbits(Node node) {
        int noOrbit = 0;
        for (Node parent = node.getParent(); parent != null; ) {
            noOrbit++;
            parent = parent.getParent();
        }
        return noOrbit;
    }

    public int noMininumOrbitTransfers(String from, String to) {
        differentNoOfOrbitTransfers.clear();
        final Node startNode = valueNodeMap.get(from);
        if (startNode.getParent() != null) {
            traversUp(from, to, startNode.getParent(), 0);
        }
        return differentNoOfOrbitTransfers.stream().sorted().findFirst().orElseThrow(() -> new IllegalArgumentException("No path exists!"));
    }
    private void traversUp(String startValue, String toValue, Node node, int noTransfers) {
        // Check whether we have a match
        if(node.getParent() != null) {
            traversUp(startValue, toValue, node.getParent(), noTransfers + 1);
        }

        traverseDown(startValue, toValue, node, noTransfers);
    }

    private void traverseDown(String startValue, String toValue, Node node, int noTransfers) {
        node.getChildren().forEach(child -> {
            if (toValue.equals(child.getValue())) {
                differentNoOfOrbitTransfers.add(noTransfers);
            } else {
                traverseDown(startValue, toValue, child, noTransfers + 1);
            }
        });
    }


}
