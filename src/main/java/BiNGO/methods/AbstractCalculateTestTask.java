/**
 * AbstractCalculateTestTask.java
 *
 * 2013.02.01
 *
 * This file is part of the CheMet library
 * 
 * The CheMet library is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * CheMet is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with CheMet.  If not, see <http://www.gnu.org/licenses/>.
 */

package BiNGO.methods;


import cytoscape.task.TaskMonitor;
import java.util.Map;

/**
 * @name    AbstractCalculateTestTask
 * @date    2013.02.01
 * @version $Rev$ : Last Changed $Date$
 * @author  pmoreno
 * @author  $Author$ (this version)
 * @brief   ...class description...
 *
 */
public abstract class AbstractCalculateTestTask {

    protected boolean interrupted = false;
    /**
     * int containing value for big N.
     */
    protected Map mapBigN;
    /**
     * int containing value for big X.
     */
    protected Map mapBigX;
    /*--------------------------------------------------------------
    FIELDS.
    --------------------------------------------------------------*/
    /**
     * hashmap with as values the values of small n and with as key the GO label.
     */
    protected Map mapSmallN;
    /**
     * hashmap with as values the values of small x and with as key the GO label.
     */
    protected Map mapSmallX;
    // Keep track of progress for monitoring:
    protected int maxValue;
    /**
     * hashmap with the Binomial Test results as values and as key the GO label.
     */
    protected Map significanceTestMap;
    /**
     * 
     */
    protected String title;

    public AbstractCalculateTestTask() {
    }

    public Map getMapBigN() {
        return mapBigN;
    }

    public Map getMapBigX() {
        return mapBigX;
    }

    /**
     * getter for mapSmallN.
     *
     * @return HashMap mapSmallN
     */
    public Map getMapSmallN() {
        return mapSmallN;
    }

    /**
     * getter for mapSmallX.
     *
     * @return HashMap mapSmallX
     */
    public Map getMapSmallX() {
        return mapSmallX;
    }

    /*--------------------------------------------------------------
    GETTERS.
    --------------------------------------------------------------*/
    /**
     * getter for the binomial test map.
     *
     * @return HashMap significanceTestMap
     */
    public Map getTestMap() {
        return significanceTestMap;
    }

    /**
     * Non-blocking call to interrupt the task.
     */
    public void halt() {
        this.interrupted = true;
    }

    /**
     * Run the Task.
     */
    public void run() {
        calculate();
    }

    public void setTaskMonitor(TaskMonitor tm) throws IllegalThreadStateException {
        //throw new UnsupportedOperationException("Not supported yet.");
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    protected abstract void calculate();

        /**
     * Gets the Task Title.
     *
     * @return human readable task title.
     */
    public String getTitle() {
        return title;
    }
}
