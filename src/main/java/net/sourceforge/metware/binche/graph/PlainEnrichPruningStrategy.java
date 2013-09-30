package net.sourceforge.metware.binche.graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: pmoreno
 * Date: 4/6/13
 * Time: 10:58
 * To change this template use File | Settings | File Templates.
 */
public class PlainEnrichPruningStrategy extends Abstract3PhasePruningStrategy implements PrunningStrategy {

    public PlainEnrichPruningStrategy() {
        preLoopPruners = Arrays.asList(new LowPValueBranchPruner(0.05),
                new LinearBranchCollapserPruner(),
                new RootChildrenPruner(3,false));

        loopPruners = Arrays.asList(new MoleculeLeavesPruner(), new LowPValueBranchPruner(0.05)
                , new LinearBranchCollapserPruner(), new ZeroDegreeVertexPruner());
        finalPruners = new LinkedList<ChEBIGraphPruner>();
    }
}
