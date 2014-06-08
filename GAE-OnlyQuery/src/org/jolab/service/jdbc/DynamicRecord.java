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
public class DynamicRecord {
    private static final String NULL_VALUE = "";


    int numeroColonne = 0;
    ArrayList<String> valore = null;
    
    public DynamicRecord(int numeroColonne){
        this.numeroColonne = numeroColonne;
        this.valore = new ArrayList<String>(numeroColonne);
    }
    
    public ArrayList<String> getValore(){
        return valore;
    }

    public void setColonna(String valore){
        if(valore!=null && !valore.trim().equals("")){
            this.valore.add(valore);
        }else{
            this.valore.add(NULL_VALUE);
        }
    }
    
    public String getColonna(int i){
        if(valore!=null){
            return valore.get(i);
        }
        return null;
    }
    
}

