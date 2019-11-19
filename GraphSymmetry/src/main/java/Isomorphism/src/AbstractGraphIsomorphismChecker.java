package Isomorphism.src;

import java.util.Map;

/**
 * This interface defines the API for checking whether two graphs are 
 * isomorphic, i.e., whether the two graphs have the same structure.
 * 
 * @author Rodion "rodde" Efremov
 * @version 1.6 (Oct 11, 2015)
 * @param <N> the actual graph node implementation type..
 */
public interface AbstractGraphIsomorphismChecker
<N extends AbstractGraphNode<N>> {

    /**
     * Performs the isomorphism check and returns an isomorphism in case the two
     * input graphs are isomorphic. If the input graphs are not isomorphic,
     * {@code null} is returned.
     * 
     * @param graph1 the first graph.
     * @param graph2 the second graph.
     * @return {@code true} only if the two input graphs are isomorphic.
     */
    public Map<N, N> getIsomorphism(Graph<N> graph1, Graph<N> graph2);
}