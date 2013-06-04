package net.sourceforge.metware.binche.graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: pmoreno
 * Date: 4/6/13
 * Time: 12:41
 * To change this template use File | Settings | File Templates.
 */
public abstract class Abstract3PhasePruningStrategy {
    List<ChEBIGraphPruner> preLoopPruners;
    List<ChEBIGraphPruner> loopPruners;
    List<ChEBIGraphPruner> finalPruners;

    public Integer applyStrategy(ChebiGraph graph) {
        int initial = graph.getVertexCount();
        for (ChEBIGraphPruner pruner : preLoopPruners) {
            pruner.prune(graph);
        }
        int difference = 1;
        int beforeIteration = graph.getVertexCount();
        while (difference>0) {
            for (ChEBIGraphPruner pruner : loopPruners) {
                pruner.prune(graph);
            }
            difference = beforeIteration - graph.getVertexCount();
            beforeIteration = graph.getVertexCount();
        }

        for (ChEBIGraphPruner pruner : finalPruners) {
            pruner.prune(graph);
        }

        return initial - graph.getVertexCount();
    }
}
