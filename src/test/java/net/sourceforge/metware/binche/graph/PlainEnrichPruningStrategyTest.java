package net.sourceforge.metware.binche.graph;

import BiNGO.BingoParameters;
import BiNGO.ParameterFactory;
import net.sourceforge.metware.binche.BiNChe;
import net.sourceforge.metware.binche.loader.BiNChEOntologyPrefs;
import net.sourceforge.metware.binche.loader.OfficialChEBIOboLoader;
import org.junit.Test;

import java.io.IOException;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

/**
 * Created with IntelliJ IDEA.
 * User: pmoreno
 * Date: 4/6/13
 * Time: 12:31
 * To change this template use File | Settings | File Templates.
 */
public class PlainEnrichPruningStrategyTest {
    @Test
    public void testApplyStrategy() throws Exception {
        Preferences binchePrefs = Preferences.userNodeForPackage(BiNChe.class);
        try {
            if (binchePrefs.keys().length == 0) {
                new OfficialChEBIOboLoader();
            }
        } catch (BackingStoreException e) {
            System.err.println("Problems loading preferences");
            e.printStackTrace();
            return;
        } catch (IOException e) {
            System.err.println("Problems loading preferences");
            e.printStackTrace();
            return;
        }

        //String ontologyFile = getClass().getClassLoader().getResource("chebi_clean.obo").getFile();
        String ontologyFile = binchePrefs.get(BiNChEOntologyPrefs.RoleAndStructOntology.name(), null);
        //String ontologyFile = BiNChe.class.getResource("/BiNGO/data/chebiInferred_chemEnt.obo").getFile();
        String elementsForEnrichFile = "/testdata_for_webapp.txt";

        System.out.println("Setting default parameters ...");
        BingoParameters parametersChEBIBin = ParameterFactory.makeParametersForChEBIBinomialOverRep(ontologyFile);

        BiNChe binche = new BiNChe();
        binche.setParameters(parametersChEBIBin);

        System.out.println("Reading input file ...");
        try {
            binche.loadDesiredElementsForEnrichmentFromFile(elementsForEnrichFile);
        } catch (IOException exception) {
            System.out.println("Error reading file: " + exception.getMessage());
            System.exit(1);
        }

        binche.execute();

        ChebiGraph chebiGraph =
                new ChebiGraph(binche.getEnrichedNodes(), binche.getOntology(), binche.getInputNodes());

        PrunningStrategy plainStrat = new PlainEnrichPruningStrategy();
        int removed = plainStrat.applyStrategy(chebiGraph);
        System.out.println("Removed nodes: "+removed);
    }
}
