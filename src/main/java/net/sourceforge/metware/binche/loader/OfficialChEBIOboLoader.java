/**
 * OfficialChEBIOboLoader.java
 *
 * 2013.02.11
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

package net.sourceforge.metware.binche.loader;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;
import net.sourceforge.metware.binche.BiNChe;
import net.sourceforge.metware.binche.execs.PreProcessOboFile;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

/**
 * @name    OfficialChEBIOboLoader
 * @date    2013.02.11
 * @version $Rev$ : Last Changed $Date$
 * @author  pmoreno
 * @author  $Author$ (this version)
 * @brief   ...class description...
 *
 */
public class OfficialChEBIOboLoader {

    private static final Logger LOGGER = Logger.getLogger( OfficialChEBIOboLoader.class );
    
    private final String oboURL = "ftp://ftp.ebi.ac.uk/pub/databases/chebi/ontology/chebi.obo";
    
    public OfficialChEBIOboLoader() throws IOException, BackingStoreException {
        Preferences binchePrefs = Preferences.userNodeForPackage(BiNChe.class);
        
        if(binchePrefs.keys().length==0) {
            binchePrefs = (new DefaultPreferenceSetter()).getDefaultSetPrefs();
        }
        
        PreProcessOboFile ppof = new PreProcessOboFile();
        File tmpFileObo = File.createTempFile("BiNChE", ".obo");
        FileUtils.copyURLToFile(new URL(oboURL), tmpFileObo);
        ppof.getTransitiveClosure(tmpFileObo.getAbsolutePath(), binchePrefs.get(BiNChEOntologyPrefs.RoleOntology.name(), null), 
                false, 
                true, BiNChEOntologyPrefs.RoleOntology.getRootChEBIEntries(), 
                Arrays.asList("rdfs:label"), new ArrayList<String>());
        File tmpRoleOnt = new File(BiNChEOntologyPrefs.RoleOntology.name()+".temp");
        tmpRoleOnt.delete();
        ppof.getTransitiveClosure(tmpFileObo.getAbsolutePath(), binchePrefs.get(BiNChEOntologyPrefs.StructureOntology.name(), null), false, 
                false, BiNChEOntologyPrefs.StructureOntology.getRootChEBIEntries(), Arrays.asList("rdfs:label","InChI"), new ArrayList<String>());
        File tmpStructOnt = new File(BiNChEOntologyPrefs.StructureOntology.name()+".temp");
        tmpStructOnt.delete();
        ppof.getTransitiveClosure(tmpFileObo.getAbsolutePath(), binchePrefs.get(BiNChEOntologyPrefs.RoleAndStructOntology.name(),null), false, 
                false, BiNChEOntologyPrefs.RoleAndStructOntology.getRootChEBIEntries(), Arrays.asList("rdfs:label","InChI"), 
                Arrays.asList("http://purl.obolibrary.org/obo/chebi#has_role"));
        File tmpStructRoleOnt = new File(BiNChEOntologyPrefs.RoleAndStructOntology.name()+".temp");
        tmpStructRoleOnt.delete();
        tmpFileObo.delete();
    }
    
    public static void main(String[] args) throws BackingStoreException, IOException {
        new OfficialChEBIOboLoader();
    }


}
