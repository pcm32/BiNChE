/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sourceforge.metware.binche.loader;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author pmoreno
 */
public enum BiNChEOntologyPrefs {
    RoleOntology("CHEBI:50906"),StructureOntology("CHEBI:24431"),RoleAndStructOntology("CHEBI:50906","CHEBI:24431"),RoleAnnot;

    private List<String> rootNodes;
    
    private BiNChEOntologyPrefs(String... chebiRootNodes) {
        rootNodes = Arrays.asList(chebiRootNodes);
    }
    
    public List<String> getRootChEBIEntries() {
        return rootNodes;
    }
    
}
