package pal;

import java.util.PriorityQueue;

public class Dijkstra {

    private Node[] graph;
    private int cStore;

    public Dijkstra(Node[] graph, int cStore) {
        this.graph = graph;
        this.cStore = cStore;
    }

    // starts at central_point
    public void run() {
        PriorityQueue<Component> pq = new PriorityQueue<>();

        Component store = graph[cStore].component;
        store.distanceFromCentral = 0;
        pq.offer(store);

        while (!pq.isEmpty()) {
            Component u = pq.poll();

            // split into 2 options -> incoming edges with cost 1 and outGoing edges with price 0
            for (Component v : u.incomingEdges) {
                if (u.distanceFromCentral + 1 < v.distanceFromCentral) { // new distance is smaller => update
                    v.distanceFromCentral = u.distanceFromCentral + 1;
                    pq.offer(v);
                }
            }

            for (Component v : u.outgoingEdges) {
                if (u.distanceFromCentral < v.distanceFromCentral) { // new distance is smaller => update
                    v.distanceFromCentral = u.distanceFromCentral;
                    pq.offer(v);
                }
            }
        }
    }
}
