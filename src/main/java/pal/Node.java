package pal;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Node {

    public int label;
    public int tIndex;
    public int low_link;
    public boolean onStack;
    public List<Node> outgoingEdges;
//    public List<Integer> incomingEdges = new ArrayList<>();
    public Node previousNode;
    public Component component;

    public Node(int label) {
        this.label = label;
        this.tIndex = -1;
        this.low_link = 0;
        this.onStack = false;
        this.previousNode = null;
        outgoingEdges = new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return label == node.label;
    }

    @Override
    public int hashCode() {
        return Objects.hash(label);
    }
}
