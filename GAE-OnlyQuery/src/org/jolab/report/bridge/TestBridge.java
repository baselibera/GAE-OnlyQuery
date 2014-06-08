/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jolab.report.bridge;

import java.util.ArrayList;
import java.util.Arrays;
import org.jolab.report.Cella;
import org.jolab.report.Interrogazione;
import org.jolab.report.Riga;

/**
 *
 * @author jolab
 */
public class TestBridge implements WebFacade{

    public Interrogazione getGenericResults(String statement, int offset, int rowsPerPage) {
              
        ArrayList listaCelle = new ArrayList<Cella>(5);
        Cella nuovaCella = new Cella("Riga 1 Col 1");
        listaCelle.add(nuovaCella);
        nuovaCella = new Cella("Riga 1 Col 2");
        listaCelle.add(nuovaCella);
        nuovaCella = new Cella("Riga 1 Col 3");
        listaCelle.add(nuovaCella);
        nuovaCella = new Cella("Riga 1 Col 4");
        listaCelle.add(nuovaCella);
        nuovaCella = new Cella("Riga 1 Col 5");
        listaCelle.add(nuovaCella);
        
        ArrayList listaTemporanea = new ArrayList<Riga>(2);
        Riga nuovaRiga = new Riga(listaCelle);
        listaTemporanea.add(nuovaRiga);
        
        listaCelle = new ArrayList<Cella>(5);
        nuovaCella = new Cella("Riga 2 Col 1");
        listaCelle.add(nuovaCella);
        nuovaCella = new Cella("Riga 2 Col 2");
        listaCelle.add(nuovaCella);
        nuovaCella = new Cella("Riga 2 Col 3");
        listaCelle.add(nuovaCella);
        nuovaCella = new Cella("Riga 2 Col 4");
        listaCelle.add(nuovaCella);
        nuovaCella = new Cella("Riga 2 Col 5");
        listaCelle.add(nuovaCella);
        
        nuovaRiga = new Riga(listaCelle);
        listaTemporanea.add(nuovaRiga);  
        
        
        return new Interrogazione(statement, 
                offset, rowsPerPage, listaTemporanea, new ArrayList<String>(Arrays.asList("uno", "due", "tre", "quattro", "cinque")), 2);
    }
    
    
}
