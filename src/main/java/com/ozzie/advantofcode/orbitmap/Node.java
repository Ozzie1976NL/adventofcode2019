package com.ozzie.advantofcode.orbitmap;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Node {
    private       Node       parent;
    private final String     value;
    private final List<Node> children = new LinkedList<>();

    public Node(Node parent, String value) {
        this.parent = parent;
        this.value = value;
    }

    public void addChild(Node node) {
        children.add(node);
    }

    public List<Node> getChildren() {
        return Collections.unmodifiableList(children);
    }

    public Node(String value) {
        this.value = value;
        parent = null;
    }

    public Node getParent() {
        return parent;
    }

    public String getValue() {
        return value;
    }

    public void setParent(Node node) {
        this.parent = node;
    }
}
