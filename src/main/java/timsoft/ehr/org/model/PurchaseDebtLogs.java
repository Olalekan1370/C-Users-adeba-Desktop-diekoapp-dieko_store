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
 * @author Olalekan
 */
@ManagedBean
@ViewScoped
@Entity
@Table(name = "purchase_debt_logs")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PurchaseDebtLogs.findAll", query = "SELECT p FROM PurchaseDebtLogs p")})
public class PurchaseDebtLogs implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "amount")
    private Double amount;
    @Column(name = "datepaid")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datepaid;
    @Size(max = 30)
    @Column(name = "paymenttype")
    private String paymenttype;
    @JoinColumn(name = "debtid", referencedColumnName = "id")
    @ManyToOne
    private PurchaseDebt debtid;

    public PurchaseDebtLogs() {
    }

    public PurchaseDebtLogs(Long id) {
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

    public Date getDatepaid() {
        return datepaid;
    }

    public void setDatepaid(Date datepaid) {
        this.datepaid = datepaid;
    }

    public String getPaymenttype() {
        return paymenttype;
    }

    public void setPaymenttype(String paymenttype) {
        this.paymenttype = paymenttype;
    }

    public PurchaseDebt getDebtid() {
        return debtid;
    }

    public void setDebtid(PurchaseDebt debtid) {
        this.debtid = debtid;
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
        if (!(object instanceof PurchaseDebtLogs)) {
            return false;
        }
        PurchaseDebtLogs other = (PurchaseDebtLogs) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "timsoft.ehr.org.model.PurchaseDebtLogs[ id=" + id + " ]";
    }
    
}
