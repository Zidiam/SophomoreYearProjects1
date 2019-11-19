package Isomorphism.src;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * This class implements a directed graph node.
 * 
 * @author Rodion "rodde" Efremov
 * @version 1.6 (Oct 11, 2015)
 */
public class DirectedGraphNode extends AbstractGraphNode<DirectedGraphNode> {

    private final Set<DirectedGraphNode> children = new LinkedHashSet<>();
    private final Set<DirectedGraphNode> parents  = new LinkedHashSet<>();

    private final Set<DirectedGraphNode> childrenWrapper = 
            Collections.<DirectedGraphNode>unmodifiableSet(children);

    private final Set<DirectedGraphNode> parentsWrapper = 
            Collections.<DirectedGraphNode>unmodifiableSet(parents);

    /**
     * Constructs a new directed graph node with given name.
     * 
     * @param name the name of the node.
     */
    public DirectedGraphNode(String name) {
        super(name);
    }

    @Override
    public void addChild(DirectedGraphNode child) {
        checkNodeBelongsToGraph();

        if (child == null) {
            return;
        }

        children.add(child);
        child.parents.add(this);
        addEdges(1);
    }

    @Override
    public boolean hasChild(DirectedGraphNode node) {
        return children.contains(node);
    }

    @Override
    public boolean hasParent(DirectedGraphNode node) {
        return parents.contains(node);
    }

    @Override
    public void removeChild(DirectedGraphNode node) {
        if (node == null) {
            return;
        }

        if (node.getOwnerGraph() != this.getOwnerGraph()) {
            return;
        }

        if (!children.contains(node)) {
            return;
        }

        children.remove(node);
        node.parents.remove(this);
        addEdges(-1);
    }

    @Override
    public Set<DirectedGraphNode> children() {
        return childrenWrapper;
    }

    @Override
    public Set<DirectedGraphNode> parents() {
        return parentsWrapper;
    }

    @Override
    public void clear() {
        for (DirectedGraphNode child : children) {
            child.parents.remove(this);
        }

        for (DirectedGraphNode parent : parents) {
            parent.children.remove(this);
        }

        addEdges(-children.size());
        addEdges(-parents.size());
        children.clear();
        parents.clear();
    }

    @Override
    public String toString() {
        return "[DirectedGraphNode \"" + getName() + "\"]";
    }
}