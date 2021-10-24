package pal;

import java.io.IOException;
import java.util.*;

public class Main {


    // for each components
    // go trough each node's edges and add them to component's edges as its own
    public static void transformComponentsIntoGraph(List<Component> components){
        // if components contains only one node => components edges are the same as node's
        for (Component c : components) {

//            if (c.nodes.size() == 1) {
//                c.outgoingEdges = c.nodes.get(0).outgoingEdges;
//                c.incomingEdges = c.nodes.get(0).incomingEdges;
//                continue;
//            }

            // add edges but not those that ends again in the component
            for (Node n : c.nodes){
                for (Node i : n.outgoingEdges){
                    if (n.component.low_link != i.component.low_link){
                        c.outgoingEdges.add(i.component);
                        i.component.incomingEdges.add(n.component);
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {

        InputReader r = new InputReader();

        int n_points = r.nextInt();
        int n_conveyors = r.nextInt();
        int cStore = r.nextInt();
        Node[] graph = new Node[n_points];

        for (int i =0; i< n_points; i++)
            graph[i] = new Node(i);


        for (int i = 0; i < n_conveyors; i++) {
            int n1 = r.nextInt();
            int n2 = r.nextInt();

            graph[n1].outgoingEdges.add(graph[n2]);
        }


        Tarjan t = new Tarjan(graph, n_points);
        t.find_scc();

        // convert each component into one node
        // add to it all adjacentNodes from all other nodes of component
        transformComponentsIntoGraph(t.components);

        Dijkstra d = new Dijkstra(graph, cStore);
        d.run();


        int sum1 = getCount(t.components);

        System.out.println(sum1);
    }

    // find all points that has no incoming edges -> those are sources
    public static int getCount(List<Component> components)
    {
        int cost = 0;
        for (Component c : components){
            if (c.incomingEdges.size() == 0)
                cost+= c.distanceFromCentral;
        }
        return cost;
    }

}
