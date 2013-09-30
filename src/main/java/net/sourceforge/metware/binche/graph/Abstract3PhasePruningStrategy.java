package net.sourceforge.metware.binche.graph;

import org.apache.commons.logging.impl.Log4JLogger;
import org.apache.log4j.Logger;

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

    private static final Logger LOGGER = Logger.getLogger(Abstract3PhasePruningStrategy.class);

    List<ChEBIGraphPruner> preLoopPruners;
    List<ChEBIGraphPruner> loopPruners;
    List<ChEBIGraphPruner> finalPruners;

    public Integer applyStrategy(ChebiGraph graph) {
        int initial = graph.getVertexCount();
        for (ChEBIGraphPruner pruner : preLoopPruners) {
            pruner.prune(graph);
        }
        LOGGER.info("Removed pre loop : "+(initial - graph.getVertexCount()));
        int difference = 1;
        int afterPreLoop = graph.getVertexCount();
        int beforeIteration = afterPreLoop;
        while (difference>0) {
            for (ChEBIGraphPruner pruner : loopPruners) {
                pruner.prune(graph);
                LOGGER.info(pruner.getClass().getCanonicalName()+" left: "+ graph.getVertexCount());
            }
            int current = graph.getVertexCount();
            difference = beforeIteration - current;
            beforeIteration = current;
        }
        LOGGER.info("Removed in loop : "+(afterPreLoop -  beforeIteration));

        for (ChEBIGraphPruner pruner : finalPruners) {
            pruner.prune(graph);
        }

        LOGGER.info("Removed in final : "+(beforeIteration - graph.getVertexCount()));

        return initial - graph.getVertexCount();
    }
}
