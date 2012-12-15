/**
 * ParameterFactory.java
 *
 * 2012.10.19
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

package BiNGO;


import java.math.BigDecimal;
import java.util.HashMap;

import org.apache.log4j.Logger;

import BiNGO.methods.BingoAlgorithm;

/**
 * @name    ParameterFactory
 * @date    2012.10.19
 * @version $Rev$ : Last Changed $Date$
 * @author  pmoreno
 * @author  $Author$ (this version)
 * @brief   ...class description...
 *
 */
public class ParameterFactory {

    private static final Logger LOGGER = Logger.getLogger( ParameterFactory.class );

    public static BingoParameters makeParametersForChEBIBinomialOverRep(String ontologyFile) {
        BingoParameters parametersBinomial = new BingoParameters();

        parametersBinomial.setTest(BingoAlgorithm.BINOMIAL);
        parametersBinomial.setCorrection(BingoAlgorithm.BENJAMINI_HOCHBERG_FDR);
        parametersBinomial.setOntologyFile(ontologyFile);
        parametersBinomial.setOntology_default(false);
        parametersBinomial.setNameSpace("chebi_ontology");
        parametersBinomial.setOverOrUnder("Overrepresentation");
        parametersBinomial.setSignificance(new BigDecimal(0.05));
        parametersBinomial.setCategory(BingoAlgorithm.CATEGORY_CORRECTION);
        parametersBinomial.setReferenceSet(BingoAlgorithm.GENOME);
        parametersBinomial.setAllNodes(null);
        parametersBinomial.setSelectedNodes(null);

        return parametersBinomial;
    }

    public static BingoParameters makeParametersForChEBISaddleSum(String ontologyFile) {

    	BingoParameters parametersSaddle = new BingoParameters();

    	parametersSaddle.setTest(BingoAlgorithm.SADDLESUM);
    	parametersSaddle.setCorrection(BingoAlgorithm.NONE);
    	parametersSaddle.setOntologyFile(ontologyFile);
    	parametersSaddle.setOntology_default(false);
    	parametersSaddle.setNameSpace("chebi_ontology");
    	parametersSaddle.setOverOrUnder("Overrepresentation");
    	parametersSaddle.setSignificance(new BigDecimal(0.05));
    	parametersSaddle.setCategory(BingoAlgorithm.CATEGORY_CORRECTION);
    	parametersSaddle.setReferenceSet(BingoAlgorithm.GENOME);
    	parametersSaddle.setAllNodes(null);
    	parametersSaddle.setSelectedNodes(null);

    	return parametersSaddle;
    }
   
}
