package Isomorphism.src;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * This class implements a simple graph isomorphism tester for directed graphs.
 * 
 * @author Rodion "rodde" Efremov
 * @version 1.6 
 */
public class TrivialDirectedGraphIsomorphismChecker 
implements AbstractGraphIsomorphismChecker<DirectedGraphNode>{

    private final Comparator<DirectedGraphNode> permutationComparator =
            new PermutationComparator();

    /**
     * {@inheritDoc } 
     */
    @Override
    public Map<DirectedGraphNode, DirectedGraphNode>
        getIsomorphism(Graph<DirectedGraphNode> graph1, 
                       Graph<DirectedGraphNode> graph2) {
        Objects.requireNonNull(graph1, "The first input graph is null.");
        Objects.requireNonNull(graph2, "The second input graph is null.");

        if (graph1.size() != graph2.size()) {
            return null;
        }

        if (graph1.getNumberOfEdges() != graph2.getNumberOfEdges()) {
            return null;
        }

        List<DirectedGraphNode> nodeList1 = graphToList(graph1);
        List<DirectedGraphNode> nodeList2 = graphToList(graph2);

        Comparator<DirectedGraphNode> comparator = 
                new Comparator<DirectedGraphNode>() {

                    @Override
                    public int compare(DirectedGraphNode o1, 
                                       DirectedGraphNode o2) {
                        int outDegree1 = o1.children().size();
                        int outDegree2 = o2.children().size();

                        if (outDegree1 != outDegree2) {
                            return Integer.compare(outDegree1, outDegree2);
                        }

                        int inDegree1 = o1.parents().size();
                        int inDegree2 = o2.parents().size();
                        return Integer.compare(inDegree1, inDegree2);
                    }
                };

        Collections.sort(nodeList1, comparator);
        Collections.sort(nodeList2, comparator);

        for (int i = 0; i < nodeList1.size(); ++i) {
            if (nodeList1.get(i).children().size() != 
                    nodeList2.get(i).children().size()) {
                return null;
            }

            if (nodeList1.get(i).parents().size() != 
                    nodeList2.get(i).parents().size()) {
                return null;
            }
        }

        return bruteForceIsomorphism(nodeList1, nodeList2);
    }

    private static List<DirectedGraphNode> 
        graphToList(Graph<DirectedGraphNode> graph) {
        List<DirectedGraphNode> ret = new ArrayList<>(graph.size());

        for (DirectedGraphNode node : graph) {
            ret.add(node);
        }

        return ret;
    }

    private static Map<DirectedGraphNode, DirectedGraphNode> 
                bruteForceIsomorphism(List<DirectedGraphNode> nodeList1, 
                                      List<DirectedGraphNode> nodeList2) {
        List<List<DirectedGraphNode>> list1 = new ArrayList<>();
        List<List<DirectedGraphNode>> list2 = new ArrayList<>();

        list1.add(new ArrayList<DirectedGraphNode>());
        list1.get(0).add(nodeList1.get(0));

        list2.add(new ArrayList<DirectedGraphNode>());
        list2.get(0).add(nodeList2.get(0));

        int previousInDegree = nodeList1.get(0).parents().size();
        int previousOutDegree = nodeList2.get(0).children().size();

        for (int i = 1; i < nodeList1.size(); ++i) {
            DirectedGraphNode currentNode = nodeList1.get(i);
            int currentInDegree = currentNode.parents().size();
            int currentOutDegree = currentNode.children().size();

            if (previousInDegree != currentInDegree
                    || previousOutDegree != currentOutDegree) {
                List<DirectedGraphNode> newSubList1 = new ArrayList<>();
                List<DirectedGraphNode> newSubList2 = new ArrayList<>();

                newSubList1.add(currentNode);
                newSubList2.add(nodeList2.get(i));

                list1.add(newSubList1);
                list2.add(newSubList2);

                previousInDegree = currentInDegree;
                previousOutDegree = currentOutDegree;
            } else {
                list1.get(list1.size() - 1).add(currentNode);
                list2.get(list2.size() - 1).add(nodeList2.get(i));
            }
        }

        Map<DirectedGraphNode, DirectedGraphNode> certainMap = new HashMap<>();

        for (int i = 0; i < list1.size(); ++i) {
            List<DirectedGraphNode> currentSubList = list1.get(i);

            if (currentSubList.size() == 1) {
                certainMap.put(currentSubList.get(0), list2.get(i).get(0));
            }
        }

        List<List<DirectedGraphNode>> groupList1 = new ArrayList<>();
        List<List<DirectedGraphNode>> groupList2 = new ArrayList<>();

        for (int i = 0; i < list1.size(); ++i) {
            if (list1.get(i).size() > 1) {
                groupList1.add(new ArrayList<>(list1.get(i)));
                groupList2.add(new ArrayList<>(list2.get(i)));
            }
        }

        if (groupList1.isEmpty()) {
            return Utils.isIsomorphism(certainMap) ? certainMap : null;
        }

        Map<DirectedGraphNode, DirectedGraphNode> isomorphism = 
                findIsomorphismPermutation(groupList1, 
                                           groupList2,
                                           new HashMap<>(certainMap));

        return isomorphism;
    }

    private static Map<DirectedGraphNode, DirectedGraphNode> 
        findIsomorphismPermutation(List<List<DirectedGraphNode>> groupList1,
                                   List<List<DirectedGraphNode>> groupList2,
                                   Map<DirectedGraphNode, 
                                       DirectedGraphNode> certainMap) {
        List<PermutationEnumerator> permutationEnumeratorList = 
                new ArrayList<>(groupList1.size());

        for (List<DirectedGraphNode> group : groupList1) {
            permutationEnumeratorList
                    .add(new PermutationEnumerator(group.size()));
        }

        do {
            Map<DirectedGraphNode, DirectedGraphNode> candidate = 
                    generateIsomorphismCandidate(groupList1,
                                                 groupList2,
                                                 permutationEnumeratorList);

            candidate.putAll(certainMap);

            if (Utils.isIsomorphism(candidate)) {
                return candidate;
            }
        } while (incrementPermutationEnumeratorList(permutationEnumeratorList));

        return null;
    }

    private static Map<DirectedGraphNode, DirectedGraphNode>
            generateIsomorphismCandidate(
                    List<List<DirectedGraphNode>> groupList1,
                    List<List<DirectedGraphNode>> groupList2,
                    List<PermutationEnumerator> permutationEnumeratorList) {
        for (int groupIndex = 0; groupIndex < groupList2.size(); ++groupIndex) {
            permute(groupList2.get(groupIndex),
                    permutationEnumeratorList.get(groupIndex));
        }

        Map<DirectedGraphNode, DirectedGraphNode> isomorphismCandidate = 
                new HashMap<>();

        for (int groupIndex = 0; groupIndex < groupList1.size(); ++groupIndex) {
            for (int nodeIndex = 0; 
                     nodeIndex < groupList1.get(groupIndex).size(); 
                     nodeIndex++) {
                isomorphismCandidate
                        .put(groupList1.get(groupIndex).get(nodeIndex),
                             groupList2.get(groupIndex).get(nodeIndex));
            }
        }

        return isomorphismCandidate;
    }

    private static void 
        permute(List<DirectedGraphNode> groupList,
                PermutationEnumerator permutationEnumeratorList) {
        int[] indices = permutationEnumeratorList.indices;
        List<DirectedGraphNode> tmp = new ArrayList<>(groupList);

        for (int i = 0; i < groupList.size(); ++i) {
            groupList.set(indices[i], tmp.get(i));
        }
    } 

    private static boolean 
        incrementPermutationEnumeratorList(List<PermutationEnumerator> list) {
        for (int i = 0; i < list.size(); ++i) {
            if (list.get(i).next() == null) {
                list.get(i).reset();
            } else {
                return true;
            }
        }

        return false;
    }

    private static final class PermutationComparator 
    implements Comparator<DirectedGraphNode> {
;
        @Override
        public int compare(DirectedGraphNode o1, DirectedGraphNode o2) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

    }

    private static final class PermutationEnumerator {

        private final int[] indices;
        private boolean initial;

        PermutationEnumerator(int length) {
            this.indices = new int[length];
            reset();
        }

        void reset() {
            initial = true;

            for (int i = 0; i < indices.length; ++i) {
                indices[i] = i;
            }
        }

        int[] next() {
            if (initial) {
                initial = false;
                return indices;
            }

            int i = indices.length - 2;

            while (i >= 0 && indices[i] > indices[i + 1]) {
                --i;
            }

            if (i == -1) {
                return null;
            }

            int j = i + 1;
            int minValue = indices[j];
            int minIndex = j;

            while (j < indices.length) {
                if (indices[i] < indices[j] && indices[j] < minValue) {
                    minValue = indices[j];
                    minIndex = j;
                }

                ++j;
            }

            int tmp = indices[i];
            indices[i] = indices[minIndex];
            indices[minIndex] = tmp;

            ++i;
            j = indices.length - 1;

            while (i < j) {
                tmp = indices[i];
                indices[i] = indices[j];
                indices[j] = tmp;

                ++i;
                --j;
            }

            return indices;
        }
    }
}