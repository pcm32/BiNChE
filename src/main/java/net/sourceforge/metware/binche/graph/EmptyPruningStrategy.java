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
public class EmptyPruningStrategy extends Abstract3PhasePruningStrategy implements PrunningStrategy {

    public EmptyPruningStrategy() {
        finalPruners = new LinkedList<ChEBIGraphPruner>();
        loopPruners = new LinkedList<ChEBIGraphPruner>();
        preLoopPruners = new LinkedList<ChEBIGraphPruner>();
    }

}
