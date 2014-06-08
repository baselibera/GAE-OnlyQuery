/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jolab.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import org.jolab.report.Interrogazione;
import org.jolab.report.Riga;
import org.jolab.report.bridge.WebFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author jolab
 */
@Controller
@RequestMapping(value="/query")
public class QueryController {
    private Interrogazione query = null;
    private Map rowsPerPageOptionsList = null;
    
    @Autowired
    private WebFacade service;
            
            
    @RequestMapping(method=RequestMethod.GET)
    public String getCreateForm(Interrogazione interrogazione, Model model) {
        
        if(interrogazione.getSql()!=null && !interrogazione.getSql().trim().equals("")){
            try {
                query = service.getGenericResults(interrogazione.getSql(), interrogazione.getPagina(), interrogazione.getRowsPerPage());
                model.addAttribute("SqlError", null);
            } catch (Exception e) {
                String message = null;
                if(e.getCause()!=null)
                    message = e.getCause().getMessage();
                else
                    message = e.getMessage();
                model.addAttribute("SqlError", message);
                query = interrogazione;
            }
        }else{
            query = new Interrogazione("", 0, -1, new ArrayList<Riga>(), new ArrayList<String>(),0);
        }
        
        int ultimaPagina = 0;
        if(interrogazione.getRowsPerPage()!=0){
            if(interrogazione.getRowsPerPage()==-1){
                ultimaPagina=1;
            }else{
                ultimaPagina = Math.round(query.getNumeroRighe()/interrogazione.getRowsPerPage());
                if(query.getNumeroRighe()%interrogazione.getRowsPerPage()!=0){
                    ultimaPagina++;
                }
            }
        }
        
        model.addAttribute("rowsPerPageOptions", this.geRowsPerPageOptions());
        model.addAttribute("paginaCorrente", interrogazione.getPagina());
        model.addAttribute("ultimaPagina", ultimaPagina);
        model.addAttribute("righeVisualizzate", interrogazione.getRowsPerPage());
        model.addAttribute("righeTotali", query.getNumeroRighe());
        
        model.addAttribute(this.query);
        //model.addAttribute("risultati", query.getResults());
        
        return "query/createQueryForm";
    }
    
    @RequestMapping(method=RequestMethod.POST)
    public String getResults(Interrogazione interrogazione, Model model){
        
        return this.getCreateForm(interrogazione, model);
        
    }
    
    @RequestMapping(value = "/csv")
    public ModelAndView getCSV(String sql){
        ModelAndView model = new ModelAndView("query/csvExport");
        
        if(sql!=null && !sql.trim().equals("")){
            try {
                query = service.getGenericResults(sql, 1, -1);
                model.addObject("SqlError", null);
            } catch (Exception e) {
                String message = null;
                if(e.getCause()!=null)
                    message = e.getCause().getMessage();
                else
                    message = e.getMessage();
                model.addObject("SqlError", message);
                
            }
        }
        StringBuffer intestazione = new StringBuffer();
        int numeroColonne = query.getNomiColonne().size();
        for(int i=0; i<numeroColonne; i++){
            intestazione.append(query.getNomiColonne().get(i));
            if(i!=numeroColonne-1)
                intestazione.append(",");
        }
        intestazione.append("\n");
        ArrayList<Riga> righe = query.getResults();
        StringBuffer linee = new StringBuffer();
        for(int i=0; i<righe.size();i++){
            linee.append(righe.get(i).getCSVRiga());
            if(i!=righe.size()-1)
                linee.append("\n");
        }
        model.addObject("exportStuff", intestazione.append(linee));
        return model;
    }
    
    private Map geRowsPerPageOptions(){
        if(rowsPerPageOptionsList==null){
            rowsPerPageOptionsList = new LinkedHashMap(5);
            rowsPerPageOptionsList.put(-1,"Tutte");
            rowsPerPageOptionsList.put(10,"10");
            rowsPerPageOptionsList.put(50,"50");
            rowsPerPageOptionsList.put(100,"100");
            rowsPerPageOptionsList.put(200, "200"); 
        }
        return rowsPerPageOptionsList;
    }
    
    
}
