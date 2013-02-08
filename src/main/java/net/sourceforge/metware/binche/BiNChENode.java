/**
 * BiNChENode.java
 *
 * 2013.02.07
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

package net.sourceforge.metware.binche;


import org.apache.log4j.Logger;

/**
 * @name    BiNChENode
 * @date    2013.02.07
 * @version $Rev$ : Last Changed $Date$
 * @author  pmoreno
 * @author  $Author$ (this version)
 * @brief   ...class description...
 *
 */
public class BiNChENode {

    private static final Logger LOGGER = Logger.getLogger( BiNChENode.class );

    private Double pValue;
    private Double foldOfEnrichment;
    private Double samplePercentage;

    private Double corrPValue;

    /**
     * Get the value of corrPValue. This value can be null.
     *
     * @return the value of corrPValue
     */
    public Double getCorrPValue() {
        return corrPValue;
    }


    private String identifier;

    public BiNChENode(Double pValue, Double corrPValue, Integer bigX, Integer bigN, Integer smallX, Integer smallN, String identifier) {
        this.pValue = pValue;
        this.corrPValue = corrPValue;
        this.foldOfEnrichment = (smallX*1.0/bigX) / (smallN*1.0/bigN);
        this.samplePercentage = smallX*1.0/bigX;
        this.identifier = identifier;
    }
    
    /**
     * Get the value of samplePercentage
     *
     * @return the value of samplePercentage
     */
    public Double getSamplePercentage() {
        return samplePercentage;
    }
    

    /**
     * Get the value of foldOfEnrichment
     *
     * @return the value of foldOfEnrichment
     */
    public Double getFoldOfEnrichment() {
        return foldOfEnrichment;
    }


    /**
     * Get the value of pValue
     *
     * @return the value of pValue
     */
    public Double getPValue() {
        return pValue;
    }

    /**
     * Set the value of pValue
     *
     * @param pValue new value of pValue
     */
    public void setPValue(Double pValue) {
        this.pValue = pValue;
    }


}
