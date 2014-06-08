/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jolab.service.jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
//import javax.activation.DataSource;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jolab
 */
@Repository
public class DynamicDAOService {
    
        
    private DataSource dataSource;
    
    private static final String DATE_FORMAT = "dd-MM-yyyy HH:mm:ss";
    private String databaseProduct;
    private String catalog;
    private String databaseURL;
    private String userName;
    private String driverName;
    private String driverVersion;

    public DataSource getDataSource() {
        return dataSource;
    }
    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource; 
        try {
            if(this.dataSource!=null)
                this.catalog = this.dataSource.getConnection().getCatalog();
            if(this.dataSource.getConnection().getMetaData()!=null){
                DatabaseMetaData metadata = this.dataSource.getConnection().getMetaData();
                this.databaseProduct = metadata.getDatabaseProductVersion();
                this.databaseURL = this.databaseProduct = metadata.getURL();
                this.userName = metadata.getUserName();
                this.driverName = metadata.getDriverName();
                this.driverVersion = metadata.getDriverVersion();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DynamicDAOService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    

/*    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource; 
    }
*/
    public RisultatoQuery getResults(String sql, int pagina, int rowsPerPage) {
        if (sql == null) {
            return null;
        }
        boolean allRows = false;
        if(rowsPerPage==-1)
            allRows=true;
        
        int offset = rowsPerPage*(pagina-1);
        int righeTotali = 0;
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            conn = this.dataSource.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            ArrayList<String> nomiColonne = new ArrayList(columnCount);
            Map<String, Integer> tipiColonne = new HashMap<String, Integer>(columnCount);
            
            // il conteggio parte da 1
            for (int i = 1; i < columnCount + 1; i++) {
                //String name = rsmd.getColumnName(i);
                String name = rsmd.getColumnLabel(i);
                int tipo = rsmd.getColumnType(i);
                nomiColonne.add(name);
                tipiColonne.put(name, new Integer(tipo));
            }
            

            RisultatoQuery risultato = new RisultatoQuery();
            risultato.setNumeroColonne(columnCount);
            risultato.setNomiColonne(nomiColonne);

            if(!allRows && offset>=0){
                rs.relative(offset);
                righeTotali=offset;
            }
            int rowsCount = 0;
            
            while (rs.next()) {
                
                righeTotali++;
                if(!(!allRows && ++rowsCount>rowsPerPage)){
 
                    DynamicRecord record = new DynamicRecord(columnCount);
                    for (String columnName : nomiColonne) {
                        
                        String value = null;
                        int tipo = tipiColonne.get(columnName).intValue();
                        
                        DateFormat df = new SimpleDateFormat(DATE_FORMAT);  
                            
                        if(tipo==java.sql.Types.DATE){
                            
                            Date dateValue = rs.getDate(columnName);    
                            value = df.format(dateValue);  
                            
                        }else if (tipo==java.sql.Types.TIME){
                            
                            Time timeValue = rs.getTime(columnName);
                            java.sql.Date date = new java.sql.Date(timeValue.getTime());
                            value = df.format(date);
                            
                        }else if (tipo==java.sql.Types.TIMESTAMP){
                            
                            Timestamp timeStamp = rs.getTimestamp(columnName);
                            java.sql.Date date = new java.sql.Date(timeStamp.getTime());
                            value = df.format(date);
                            
                        }else{
                            
                            value = rs.getString(columnName);
                        
                        }
                        record.setColonna(value);
                    }
                    risultato.getRecords().add(record);
                }
            }
            risultato.setTotaleRigheQuery(righeTotali);
            rs.close();
            ps.close();
            return risultato;


        } catch (SQLException e) {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException esql) {
                }
            }
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
        }
    }
    
    public ArrayList getSavedSQLCommands(){
        ArrayList results= null;
        return results;
    }
    
}
