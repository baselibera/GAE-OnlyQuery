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
public class Interrogazione {

    private String sql = new String();
    private int pagina = 0;
    private int rowsPerPage = 0;
    
    private ArrayList<String> nomiColonne = new ArrayList();
    private int numeroRighe = 0;
    private ArrayList<Riga> results;

    public Interrogazione() {
        
    }

    
    public Interrogazione(String sql, int pagina, int rowsPerPage, ArrayList<Riga> listaRighe, ArrayList<String> colonne, int totaleRigheQuery) {
        this.setSql(sql);
        this.setPagina(pagina);
        this.setRowsPerPage(rowsPerPage);
        this.setResults(listaRighe,colonne);
        this.setNumeroRighe(totaleRigheQuery);
    }


    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public int getPagina() {
        return pagina;
    }

    public void setPagina(int offset) {
        this.pagina = offset;
    }

    public int getRowsPerPage() {
        return rowsPerPage;
    }

    public void setRowsPerPage(int rowsPerPage) {
        this.rowsPerPage = rowsPerPage;
    }

    
    
    public ArrayList<Riga> getResults() {
        return results;
    }

    private void setResults(ArrayList<Riga> results, ArrayList<String> colonne) {
        if (results != null && !results.isEmpty()) {
            this.setNumeroRighe(results.size());
        }
        this.setNomiColonne(colonne);
        this.results = results;
    }

    public void setNomiColonne(ArrayList<String> nomi) {
        this.nomiColonne = nomi;
    }

    public ArrayList<String> getNomiColonne(){
        return this.nomiColonne;
    }
    
    public void setNumeroRighe(int numeroRighe) {
        this.numeroRighe = numeroRighe;
    }

    public int getNumeroRighe() {
        return numeroRighe;
    }
    
}
