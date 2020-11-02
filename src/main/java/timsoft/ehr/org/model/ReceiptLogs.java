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
@Table(name = "receipt_logs")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReceiptLogs.findAll", query = "SELECT r FROM ReceiptLogs r")})
public class ReceiptLogs implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Size(max = 30)
    @Column(name = "category")
    private String category;
    @Column(name = "dateprinted")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateprinted;
    @Size(max = 40)
    @Column(name = "reporttype")
    private String reporttype;
    @JoinColumn(name = "printedby", referencedColumnName = "id")
    @ManyToOne
    private Accounts printedby;

    public ReceiptLogs() {
    }

    public ReceiptLogs(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getDateprinted() {
        return dateprinted;
    }

    public void setDateprinted(Date dateprinted) {
        this.dateprinted = dateprinted;
    }

    public String getReporttype() {
        return reporttype;
    }

    public void setReporttype(String reporttype) {
        this.reporttype = reporttype;
    }

    public Accounts getPrintedby() {
        return printedby;
    }

    public void setPrintedby(Accounts printedby) {
        this.printedby = printedby;
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
        if (!(object instanceof ReceiptLogs)) {
            return false;
        }
        ReceiptLogs other = (ReceiptLogs) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "timsoft.ehr.org.model.ReceiptLogs[ id=" + id + " ]";
    }
    
}
