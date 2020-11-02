/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timsoft.ehr.org.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Olalekan
 */
@ManagedBean
@ViewScoped
@Entity
@Table(name = "purchase_debt")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PurchaseDebt.findAll", query = "SELECT p FROM PurchaseDebt p")})
public class PurchaseDebt implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "amount")
    private Double amount;
    @Column(name = "datecreated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datecreated;
    @Size(max = 30)
    @Column(name = "debtstatus")
    private String debtstatus;
    @JoinColumn(name = "purchaseid", referencedColumnName = "id")
    @ManyToOne
    private Purchases purchaseid;
    @JoinColumn(name = "customerid", referencedColumnName = "id")
    @ManyToOne
    private Customers customerid;
    @OneToMany(mappedBy = "debtid")
    private List<PurchaseDebtLogs> purchaseDebtLogsList;

    public PurchaseDebt() {
    }

    public PurchaseDebt(Long id) {
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

    public Date getDatecreated() {
        return datecreated;
    }

    public void setDatecreated(Date datecreated) {
        this.datecreated = datecreated;
    }

    public String getDebtstatus() {
        return debtstatus;
    }

    public void setDebtstatus(String debtstatus) {
        this.debtstatus = debtstatus;
    }

    public Purchases getPurchaseid() {
        return purchaseid;
    }

    public void setPurchaseid(Purchases purchaseid) {
        this.purchaseid = purchaseid;
    }

    public Customers getCustomerid() {
        return customerid;
    }

    public void setCustomerid(Customers customerid) {
        this.customerid = customerid;
    }

    @XmlTransient
    public List<PurchaseDebtLogs> getPurchaseDebtLogsList() {
        return purchaseDebtLogsList;
    }

    public void setPurchaseDebtLogsList(List<PurchaseDebtLogs> purchaseDebtLogsList) {
        this.purchaseDebtLogsList = purchaseDebtLogsList;
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
        if (!(object instanceof PurchaseDebt)) {
            return false;
        }
        PurchaseDebt other = (PurchaseDebt) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "timsoft.ehr.org.model.PurchaseDebt[ id=" + id + " ]";
    }
    
}
