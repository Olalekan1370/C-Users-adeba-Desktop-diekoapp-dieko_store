/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timsoft.ehr.org.model;

import java.io.Serializable;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author JIDEX
 */
@ManagedBean
@ViewScoped
@Entity
@Table(name = "bankdeposit")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bankdeposit.findAll", query = "SELECT b FROM Bankdeposit b")})
public class Bankdeposit implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "amount")
    private Double amount;
    @Column(name = "transactiondate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date transactiondate;
    @Column(name = "datedeposit")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datedeposit;
    @Size(max = 250)
    @Column(name = "depositorname")
    private String depositorname;
    @Size(max = 250)
    @Column(name = "bank")
    private String bank;
    @Column(name = "datecreated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datecreated;
    @JoinColumn(name = "createdby", referencedColumnName = "id")
    @ManyToOne
    private Staff createdby;

    public Bankdeposit() {
    }

    public Bankdeposit(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getTransactiondate() {
        return transactiondate;
    }

    public void setTransactiondate(Date transactiondate) {
        this.transactiondate = transactiondate;
    }

    public Date getDatedeposit() {
        return datedeposit;
    }

    public void setDatedeposit(Date datedeposit) {
        this.datedeposit = datedeposit;
    }

    public String getDepositorname() {
        return depositorname;
    }

    public void setDepositorname(String depositorname) {
        this.depositorname = depositorname;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public Date getDatecreated() {
        return datecreated;
    }

    public void setDatecreated(Date datecreated) {
        this.datecreated = datecreated;
    }

    public Staff getCreatedby() {
        return createdby;
    }

    public void setCreatedby(Staff createdby) {
        this.createdby = createdby;
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
        if (!(object instanceof Bankdeposit)) {
            return false;
        }
        Bankdeposit other = (Bankdeposit) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "timsoft.ehr.org.model.Bankdeposit[ id=" + id + " ]";
    }
    
}
