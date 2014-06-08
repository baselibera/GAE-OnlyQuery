/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jolab.report.bridge;

import java.util.ArrayList;
import org.jolab.report.Cella;
import org.jolab.report.Interrogazione;
import org.jolab.report.Riga;
import org.jolab.service.jdbc.DynamicDAOService;
import org.jolab.service.jdbc.DynamicRecord;
import org.jolab.service.jdbc.RisultatoQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jolab
 */
@Repository
public class JdbcBridge implements WebFacade{
    
    private DynamicDAOService dynamicDAOService; 

    public DynamicDAOService getDynamicDAOService() {
        return dynamicDAOService;
    }
    @Autowired()
    public void setDynamicDAOService(DynamicDAOService service) {
        this.dynamicDAOService = service;
    }
          
    

    public Interrogazione getGenericResults(String statement, int offset, int rowsPerPage) {
    
        RisultatoQuery risultatoQuery = this.dynamicDAOService.getResults(statement, offset, rowsPerPage);
        
        int numeroColonne = risultatoQuery.getNumeroColonne();
        ArrayList<DynamicRecord> records = risultatoQuery.getRecords();
        ArrayList<Riga> righe = new ArrayList<Riga>();
        
        for(DynamicRecord record : records){     
            ArrayList<String> listaStringhe = record.getValore();
            Cella cella = null;
            ArrayList<Cella> listaCelle = new ArrayList<Cella>(numeroColonne);
            for (String valoreStringa : listaStringhe){
                cella = new Cella(valoreStringa);
                listaCelle.add(cella);
            }
            Riga riga = new Riga(listaCelle);
            righe.add(riga);
        }
        
        
        Interrogazione risultato = new Interrogazione(statement, 
                offset, rowsPerPage, righe , 
                risultatoQuery.getNomiColonne(), risultatoQuery.getTotaleRigheQuery());
        
        return risultato;
    }
    
}
