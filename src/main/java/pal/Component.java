package pal;

import java.util.*;

public class Component implements Comparable<Component> {

    public int low_link;
    public List<Node> nodes = new ArrayList<>(); // all nodes from component
    public int distanceFromCentral = Integer.MAX_VALUE;
    public List<Component> outgoingEdges = new LinkedList<>();
    public List<Component> incomingEdges = new LinkedList<>();

    public Component(int low_link) {
        this.low_link = low_link;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Component component = (Component) o;
        return low_link == component.low_link;
    }

    @Override
    public int hashCode() {
        return Objects.hash(low_link);
    }

    @Override
    public int compareTo(Component o) {
        if (this.distanceFromCentral > o.distanceFromCentral)
            return 1;
        if (this.distanceFromCentral < o.distanceFromCentral)
            return -1;
        return 0;
    }
}
