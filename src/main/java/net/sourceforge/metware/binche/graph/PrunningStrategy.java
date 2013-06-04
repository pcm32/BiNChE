package net.sourceforge.metware.binche.graph;

/**
 * Created with IntelliJ IDEA.
 * User: pmoreno
 * Date: 4/6/13
 * Time: 10:51
 * To change this template use File | Settings | File Templates.
 */
public interface PrunningStrategy {

    /**
     * Applies the implemented prunning strategy to the graph provided. Returns the number of prunned nodes.
     *
     * @param graph
     * @return number of pruned nodes.
     */
    public Integer applyStrategy(ChebiGraph graph);

}
