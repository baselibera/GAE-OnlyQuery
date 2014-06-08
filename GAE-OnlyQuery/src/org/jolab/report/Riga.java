/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jolab.report;

import java.util.ArrayList;

/**
 *
 * @author jolab
 */
public class Riga {
    private ArrayList<Cella> celle = new ArrayList(5);

    public Riga(ArrayList<Cella> valori) {
        this.celle = valori;
    }
    
    public Cella getCella(int i){
        Cella result = new Cella("non trovata");
        if(this.celle!=null && this.celle.size()-1>=i){
            result = (Cella)celle.get(i);
        }
        return result;
    }

    public ArrayList<Cella> getCelle() {
        return celle;
    }
    
    public int getNumeroColonne(){
        if(celle!=null){
            return celle.size();
        }
        return 0;
    }

    
    private static final String FIELD_DELIMETER = ",";    
    private static final String STRING_DELIMETER = "'";
    
    public StringBuffer getCSVRiga(){
        StringBuffer buffer = new StringBuffer();
        for(int i=0; i<celle.size();i++){
            buffer.append("'").append(this.celle.get(i).getValue()).append("'");
            if(i!=celle.size()-1){
                buffer.append(FIELD_DELIMETER);
            }
        }
        return buffer; 
    }
    
}
