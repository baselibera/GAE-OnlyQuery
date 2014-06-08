/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jolab.service.monitoring;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.jolab.entity.SqlReport;
import org.springframework.stereotype.Component;

/**
 *
 * @author jolab
 */
@Component
public class SqlReportDAO {
    
    
    private EntityManager em;
    
    @PersistenceContext
    public void setEmf(EntityManager em){
        this.em=em;
    }
    //@Transactional
    public void persist(SqlReport sqlItem) { 
        em.persist(sqlItem);
    }
 
    // Retrieves all the guests:
    public List<SqlReport> getAllSqlItems() {
        TypedQuery<SqlReport> query = em.createQuery(
            "SELECT si FROM SqlReport si", SqlReport.class);
        
        List<SqlReport> lista = query.getResultList();
        return lista;
        
    }    
 
    public SqlReport getSqlReportById(Integer id){
        
        SqlReport item = em.find(SqlReport.class, id);
        return item;
                
    }
    
}
