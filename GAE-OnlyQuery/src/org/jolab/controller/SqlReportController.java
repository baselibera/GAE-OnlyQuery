/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jolab.controller;

import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import org.jolab.entity.SqlReport;
import org.jolab.service.monitoring.SqlReportDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.jolab.exception.ResourceNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author jolab
 */
@Controller
@RequestMapping(value="/sqlReport")
public class SqlReportController {
    
    @Autowired
    private SqlReportDAO sqlReportDao;
 
    @RequestMapping(method=RequestMethod.GET)
    public ModelAndView getList(HttpServletRequest request) {
        Collection lista = sqlReportDao.getAllSqlItems();
        ModelAndView model = new ModelAndView("repositorySQL/sqlReportList");
        model.addObject("listaSqlReport", lista);
        return model;
    }

    @RequestMapping(value="{id}", method=RequestMethod.GET)
    public ModelAndView getView(@PathVariable Integer id) {
            SqlReport sqlReport = sqlReportDao.getSqlReportById(id);
            if (sqlReport == null) {
                    throw new ResourceNotFoundException(new Long(id));
            }
            ModelAndView model = new ModelAndView("repositorySQL/sqlReportDetail");
            model.addObject("sqlReport", sqlReport);
            return model;
    }    
}
