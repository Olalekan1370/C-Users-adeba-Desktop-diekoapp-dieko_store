/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timsoft.ehr.org.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Olalekan
 */
@ManagedBean
@ViewScoped
@Entity
@Table(name = "accounts_activities")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AccountsActivities.findAll", query = "SELECT a FROM AccountsActivities a")})
public class AccountsActivities implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Column(name = "accountid")
    private BigInteger accountid;
    @Lob
    @Size(max = 65535)
    @Column(name = "description")
    private String description;
    @Column(name = "datecreated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datecreated;
    @Size(max = 50)
    @Column(name = "tablecat")
    private String tablecat;

    public AccountsActivities() {
    }

    public AccountsActivities(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigInteger getAccountid() {
        return accountid;
    }

    public void setAccountid(BigInteger accountid) {
        this.accountid = accountid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDatecreated() {
        return datecreated;
    }

    public void setDatecreated(Date datecreated) {
        this.datecreated = datecreated;
    }

    public String getTablecat() {
        return tablecat;
    }

    public void setTablecat(String tablecat) {
        this.tablecat = tablecat;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AccountsActivities)) {
            return false;
        }
        AccountsActivities other = (AccountsActivities) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "timsoft.ehr.org.model.AccountsActivities[ id=" + id + " ]";
    }
    
}
