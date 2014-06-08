/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jolab.service.jdbc;

import java.util.ArrayList;

/**
 *
 * @author jolab
 */
public class RisultatoQuery {
    private int numeroColonne = 0;
    // Il numero delle righe totali estratte dalla query.
    // Diverso dal numero totale delle righe che compongono il risultato paginato della query.
    private int totaleRigheQuery = 0;
    private ArrayList<String> nomiColonne = null;
    private ArrayList<DynamicRecord> records = null;
    
    
    //private Srting[]
    public int getNumeroColonne() {
        return numeroColonne;
    }

    public void setNumeroColonne(int numeroColonne) {
        this.numeroColonne = numeroColonne;
    }

    public int getTotaleRigheQuery() {
        return totaleRigheQuery;
    }

    public void setTotaleRigheQuery(int totaleRigheQuery) {
        this.totaleRigheQuery = totaleRigheQuery;
    }
    
    public ArrayList<String> getNomiColonne() {
        return nomiColonne;
    }

    public void setNomiColonne(ArrayList<String> nomiColonne) {
        this.nomiColonne = nomiColonne;
    }

    public ArrayList<DynamicRecord> getRecords() {
        if(records==null){ 
            records = new ArrayList<DynamicRecord>();
        }
        return records;
    }
        
}
