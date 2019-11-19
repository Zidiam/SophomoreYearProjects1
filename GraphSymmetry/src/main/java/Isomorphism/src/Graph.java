package Isomorphism.src;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * This class implements the graph data structure.
 * 
 * @author Rodion "rodde" Efremov
 * @version 1.6 (Oct 11, 2015)
 * @param <N> the actual graph node implementation type.
 */
public class Graph<N extends AbstractGraphNode<N>> implements Iterable<N> {

    private final Map<String, N> nameMap = new LinkedHashMap<>();
    private int numberOfEdges;

    @Override
    public Iterator<N> iterator() {
        return nameMap.values().iterator();
    }

    public void addNode(N node) {
        Objects.requireNonNull(node, "The input node is null.");

        if (!nameMap.containsKey(node.getName())) {
            node.setOwnerGraph(this);
            nameMap.put(node.getName(), node);
        }
    }

    public void removeNode(AbstractGraphNode<N> node) {
        Objects.requireNonNull(node, "The input node is null.");
        node.clear();
        nameMap.remove(node.getName());
    }

    public N getNodeByName(String name) {
        return nameMap.get(name);
    }

    public void clear() {
        nameMap.clear();
        numberOfEdges = 0;
    }

    public int size() {
        return nameMap.size();
    }

    public int getNumberOfEdges() {
        return numberOfEdges;
    }

    protected void addEdges(int edges) {
        numberOfEdges += edges;
    }
}