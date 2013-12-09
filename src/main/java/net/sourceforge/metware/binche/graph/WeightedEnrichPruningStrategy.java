package net.sourceforge.metware.binche.graph;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * Created with IntelliJ IDEA.
 * User: pmoreno
 * Date: 4/6/13
 * Time: 10:58
 * To change this template use File | Settings | File Templates.
 */
public class WeightedEnrichPruningStrategy extends Abstract3PhasePruningStrategy implements PrunningStrategy {

    public WeightedEnrichPruningStrategy() {
        finalPruners = new LinkedList<ChEBIGraphPruner>();
        finalPruners.add(new LinearBranchCollapserPruner());
        finalPruners.add(new ZeroDegreeVertexPruner());
        loopPruners = new LinkedList<ChEBIGraphPruner>();
        preLoopPruners = Arrays.asList(
                new MoleculeLeavesPruner(), new RootChildrenPruner(4,false), new HighPValueBranchPruner(0.05)
        );

    }

}
