package pal;

import java.util.*;

public class Tarjan {
    private Node[] graph;
    private int n_points;
    private Deque<Integer> stack = new ArrayDeque<>();
    private int next_id = 0;
    private final int UNVISITED = -1;
    public int sccCount = 0;
    public List<Component> components= new ArrayList<>();

    public  Tarjan(Node[] graph, int n_points) {
        this.graph = graph;
        this.n_points = n_points;
    }

    public void find_scc() {
        for (int i = 0;i < n_points; i++){
            if (graph[i].tIndex == UNVISITED)
                dfs(graph[i]);
        }
    }

    public void dfs(Node at){
        stack.addFirst(at.label);
        at.onStack = true;
        at.tIndex = at.low_link = next_id++;

        for (Node to : at.outgoingEdges) {
            if (to.tIndex == UNVISITED)
                dfs(to);
            if (to.onStack) {
                int min = Math.min(at.low_link, to.low_link);
                at.low_link = min;
            }
        }

        if (at.tIndex == at.low_link){
            Component c = new Component(at.low_link);
            for (Integer node = stack.removeFirst(); ; node = stack.removeFirst()) {
                graph[node].low_link = at.tIndex;
                graph[node].onStack = false;
                c.nodes.add(graph[node]);
                graph[node].component = c;
                if (node == at.label) {
                    components.add(c);
                    break;
                }
            }
            sccCount++;
        }
    }

}
