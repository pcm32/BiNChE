package BiNGO.methods.hypergeometric;

/* * Copyright (c) 2005 Flanders Interuniversitary Institute for Biotechnology (VIB)
 * *
 * * Authors : Steven Maere, Karel Heymans
 * *
 * * This program is free software; you can redistribute it and/or modify
 * * it under the terms of the GNU General Public License as published by
 * * the Free Software Foundation; either version 2 of the License, or
 * * (at your option) any later version.
 * *
 * * This program is distributed in the hope that it will be useful,
 * * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. 
 * * The software and documentation provided hereunder is on an "as is" basis,
 * * and the Flanders Interuniversitary Institute for Biotechnology
 * * has no obligations to provide maintenance, support,
 * * updates, enhancements or modifications.  In no event shall the
 * * Flanders Interuniversitary Institute for Biotechnology
 * * be liable to any party for direct, indirect, special,
 * * incidental or consequential damages, including lost profits, arising
 * * out of the use of this software and its documentation, even if
 * * the Flanders Interuniversitary Institute for Biotechnology
 * * has been advised of the possibility of such damage. See the
 * * GNU General Public License for more details.
 * *
 * * You should have received a copy of the GNU General Public License
 * * along with this program; if not, write to the Free Software
 * * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 * *
 * * Authors: Steven Maere, Karel Heymans
 * * Date: Mar.25.2005
 * * Description: Class that calculates the hypergeometric test results for a given cluster    
 * */

import BiNGO.interfaces.CalculateTestTask;
import BiNGO.interfaces.DistributionCount;
import BiNGO.methods.AbstractCalculateTestTask;
import cytoscape.task.TaskMonitor;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;


/**
 * ************************************************************
 * HypergeometricTestCalculate.java
 * -----------------------------------------
 * <p/>
 * Steven Maere & Karel Heymans (c) March 2005
 * <p/>
 * Class that calculates the hypergeometric test results for a given cluster
 * *************************************************************
 */

public class HypergeometricTestCalculate extends AbstractCalculateTestTask implements CalculateTestTask {

    /*--------------------------------------------------------------
    FIELDS.
    --------------------------------------------------------------*/

    /**
     * logger (replacement for cyto's task monitor)
     */
    private static final Logger LOGGER = Logger.getLogger(HypergeometricTestCalculate.class);

    /*--------------------------------------------------------------
    CONSTRUCTOR.
    --------------------------------------------------------------*/

    /**
     * constructor with as argument the selected cluster and the
     * annotation, ontology and alpha.
     */
    public HypergeometricTestCalculate(DistributionCount dc) {

        dc.calculate();
        this.mapSmallN = dc.getMapSmallN();
        this.mapSmallX = dc.getMapSmallX();
        this.mapBigN = dc.getMapBigN();
        this.mapBigX = dc.getMapBigX();
        this.maxValue = mapSmallX.size();
        this.title = "Calculating Hypergeometric Distribution";

    }

    /*--------------------------------------------------------------
      METHODS.
    --------------------------------------------------------------*/

    /**
     * method that redirects the calculation of hypergeometric distribution.
     */
    @Override
    public void calculate() {

        HypergeometricDistribution hd;
        significanceTestMap = new HashMap();

        HashSet set = new HashSet(mapSmallX.keySet());
        Iterator iterator = set.iterator();
        Integer id;
        Integer smallXvalue;
        Integer smallNvalue;
        Integer bigXvalue;
        Integer bigNvalue;
        int currentProgress = 0;
        try {
            while (iterator.hasNext()) {
                id = new Integer(iterator.next().toString());
                smallXvalue = new Integer(mapSmallX.get(id).toString());
                smallNvalue = new Integer(mapSmallN.get(id).toString());
                bigXvalue = new Integer(mapBigX.get(id).toString());
                bigNvalue = new Integer(mapBigN.get(id).toString());
                hd = new HypergeometricDistribution(smallXvalue.intValue(), bigXvalue.intValue(),
                        smallNvalue.intValue(), bigNvalue.intValue());
                significanceTestMap.put(id, hd.calcHypergDistr());

                // set progress

                if (interrupted) {
                    throw new InterruptedException();
                }

            }
        } catch (InterruptedException e) {
            LOGGER.log(Level.ERROR, "Hypergeometric P-value calculation cancelled " + e);
        }
    }

    /*--------------------------------------------------------------
      GETTERS.
    --------------------------------------------------------------*/


    public void setTaskMonitor(TaskMonitor tm) throws IllegalThreadStateException {
        //throw new UnsupportedOperationException("Not supported yet.");
    }
}

