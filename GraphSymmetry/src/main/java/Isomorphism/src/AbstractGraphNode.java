package Isomorphism.src;

import java.util.Objects;
import java.util.Set;

/**
 * This class defines the API for a graph node.
 * 
 * @author Rodion "rodde" Efremov
 * @version 1.6 (Oct 11, 2015)
 * @param <N> the actual graph node implementation type.
 */
public abstract class AbstractGraphNode<N extends AbstractGraphNode<N>> {

    private final String name;
    private Graph<N> ownerGraph;

    public AbstractGraphNode(String name) {
        Objects.requireNonNull(name, "The name of the node is null.");
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setOwnerGraph(Graph<N> ownerGraph) {
        if (this.getOwnerGraph() != null) {
            clear();
            this.getOwnerGraph().removeNode(this);
        }

        this.ownerGraph = ownerGraph;
    }

    public Graph<N> getOwnerGraph() {
        return ownerGraph;
    }

    /**
     * Makes {@code child} a child node of this node.
     * 
     * @param child the child node.
     */
    public abstract void addChild(N child);

    /**
     * Tests whether {@code node} is a child node of this node.
     * 
     * @param node the node to test.
     * @return {@code true} only if {@code node} is the child node of this node.
     */
    public abstract boolean hasChild(N node);

    /**
     * Tests whether {@code node} is a parent node of this node.
     * 
     * @param node the node to test.
     * @return {@code true} only if {@code node} is the parent node of this 
     *         node.
     */
    public abstract boolean hasParent(N node);

    /**
     * Removes the child from this node.
     * 
     * @param child the node to remove.
     */
    public abstract void removeChild(N child);

    /**
     * Returns a set of child nodes of this node.
     * 
     * @return a set of nodes.
     */
    public abstract Set<N> children();

    /**
     * Returns a set of parent nodes of this node.
     * 
     * @return a set of nodes.
     */
    public abstract Set<N> parents();

    /**
     * Disconnects this node from all its neighbors.
     */
    public abstract void clear();

    /**
     * {@inheritDoc }
     */
    @Override
    public int hashCode() {
        return name.hashCode();
    }

    /**
     * {@inheritDoc } 
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        AbstractGraphNode<N> other = (AbstractGraphNode<N>) obj;
        return Objects.equals(name, other.name);
    }

    protected void checkNodeBelongsToGraph() {
        if (this.getOwnerGraph() == null) {
            throw new IllegalStateException(
                    "The node does not belong to any graph.");
        }
    }

    protected void addEdges(int edges) {
        ownerGraph.addEdges(edges);
    }
}