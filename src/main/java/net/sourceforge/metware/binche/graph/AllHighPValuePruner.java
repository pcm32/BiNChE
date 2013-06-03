/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.sourceforge.metware.binche.graph;

import java.util.Collection;
import java.util.HashSet;

/**
 * @name    AllHighPValuePruner
 * @date    2013.04.23
 * @version $Rev$ : Last Changed $Date$
 * @author  Pablo Moreno <pablacious at users.sf.net>
 * @author  $Author$ (this version)
 * @brief   ...class description...
 *
 */
public class AllHighPValuePruner implements ChEBIGraphPruner {
    
    private Double threshold;
    
    public AllHighPValuePruner(Double threshold) {
        this.threshold = threshold;
    }

    @Override
    public void prune(ChebiGraph graph) {
        Collection<ChebiVertex> toRem = new HashSet<ChebiVertex>();
        for (ChebiVertex chebiVertex : graph.getVertices()) {
            Double pValue = chebiVertex.getCorrPValue() != null 
                    ? chebiVertex.getCorrPValue() : chebiVertex.getpValue();
            
            if(threshold<pValue) {
                toRem.add(chebiVertex);
            }
        }
        
        for (ChebiVertex chebiVertex : toRem) {
            graph.removeVertex(chebiVertex);
        }
    }


}
