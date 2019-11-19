package Isomorphism.src;

import java.util.Map;

/**
 * This class provides some utility methods for working with graph isomorphisms.
 * 
 * @author Rodion "rodde" Efremov
 * @version 1.6 (Oct 11, 2015)
 */
public class Utils {

    /**
     * This method tests that the input mapping is a graph isomorphism.
     * 
     * @param candidate the candidate isomorphism.
     * @return {@code true} only if the input map is a graph isomorphism.
     */
    public static boolean 
        isIsomorphism(Map<DirectedGraphNode, DirectedGraphNode> candidate) {
        for (Map.Entry<DirectedGraphNode, 
                       DirectedGraphNode> mapping : candidate.entrySet()) {
            if (mapping.getKey().children().size() != 
                    mapping.getValue().children().size()) {
                return false;
            }

            if (mapping.getKey().parents().size() != 
                    mapping.getValue().parents().size()) {
                return false;
            }

            for (DirectedGraphNode child : mapping.getKey().children()) {
                if (!candidate.get(child)
                              .hasParent(candidate.get(mapping.getKey()))) {
                    return false;
                }
            }
        }

        return true;
    }
}