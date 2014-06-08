/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jolab.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jolab
 */
@Entity
@Table(name = "SqlReport")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SqlReport.findAll", query = "SELECT s FROM SqlReport s"),
    @NamedQuery(name = "SqlReport.findByIdSqlReport", query = "SELECT s FROM SqlReport s WHERE s.idSqlReport = :idSqlReport"),
    @NamedQuery(name = "SqlReport.findByInsDate", query = "SELECT s FROM SqlReport s WHERE s.insDate = :insDate"),
    @NamedQuery(name = "SqlReport.findByName", query = "SELECT s FROM SqlReport s WHERE s.name = :name"),
    @NamedQuery(name = "SqlReport.findBySql", query = "SELECT s FROM SqlReport s WHERE s.sql = :sql"),
    @NamedQuery(name = "SqlReport.findByComment", query = "SELECT s FROM SqlReport s WHERE s.comment = :comment")})
public class SqlReport implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idSqlReport")
    private Integer idSqlReport;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ins_date")
    @Temporal(TemporalType.DATE)
    private Date insDate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2000)
    @Column(name = "sqlCommand")
    private String sql;
    @Size(max = 500)
    @Column(name = "comment")
    private String comment;

    public SqlReport() {
    }

    public SqlReport(Integer idSqlReport) {
        this.idSqlReport = idSqlReport;
    }

    public SqlReport(Integer idSqlReport, Date insDate, String name, String sql) {
        this.idSqlReport = idSqlReport;
        this.insDate = insDate;
        this.name = name;
        this.sql = sql;
    }

    public Integer getIdSqlReport() {
        return idSqlReport;
    }

    public void setIdSqlReport(Integer idSqlReport) {
        this.idSqlReport = idSqlReport;
    }

    public Date getInsDate() {
        return insDate;
    }

    public void setInsDate(Date insDate) {
        this.insDate = insDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSqlReport != null ? idSqlReport.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SqlReport)) {
            return false;
        }
        SqlReport other = (SqlReport) object;
        if ((this.idSqlReport == null && other.idSqlReport != null) || (this.idSqlReport != null && !this.idSqlReport.equals(other.idSqlReport))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jolab.entity.SqlReport[ idSqlReport=" + idSqlReport + " ]";
    }
    
}
