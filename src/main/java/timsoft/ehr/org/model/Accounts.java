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
@Table(name = "accounts")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Accounts.findAll", query = "SELECT a FROM Accounts a")})
public class Accounts implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Size(max = 50)
    @Column(name = "username")
    private String username;
    @Size(max = 50)
    @Column(name = "password")
    private String password;
    @Column(name = "datecreated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datecreated;
    @Size(max = 30)
    @Column(name = "category")
    private String category;
    @OneToMany(mappedBy = "printedby")
    private List<ReceiptLogs> receiptLogsList;
    @OneToMany(mappedBy = "createdBy")
    private List<Library> libraryList;
    @OneToMany(mappedBy = "createdby")
    private List<Customers> customersList;
    @OneToMany(mappedBy = "createdby")
    private List<DeleteLogs> deleteLogsList;
    @OneToMany(mappedBy = "createdby")
    private List<ProductSupply> productSupplyList;

    public Accounts() {
    }

    public Accounts(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDatecreated() {
        return datecreated;
    }

    public void setDatecreated(Date datecreated) {
        this.datecreated = datecreated;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @XmlTransient
    public List<ReceiptLogs> getReceiptLogsList() {
        return receiptLogsList;
    }

    public void setReceiptLogsList(List<ReceiptLogs> receiptLogsList) {
        this.receiptLogsList = receiptLogsList;
    }

    @XmlTransient
    public List<Library> getLibraryList() {
        return libraryList;
    }

    public void setLibraryList(List<Library> libraryList) {
        this.libraryList = libraryList;
    }

    @XmlTransient
    public List<Customers> getCustomersList() {
        return customersList;
    }

    public void setCustomersList(List<Customers> customersList) {
        this.customersList = customersList;
    }

    @XmlTransient
    public List<DeleteLogs> getDeleteLogsList() {
        return deleteLogsList;
    }

    public void setDeleteLogsList(List<DeleteLogs> deleteLogsList) {
        this.deleteLogsList = deleteLogsList;
    }

    @XmlTransient
    public List<ProductSupply> getProductSupplyList() {
        return productSupplyList;
    }

    public void setProductSupplyList(List<ProductSupply> productSupplyList) {
        this.productSupplyList = productSupplyList;
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
        if (!(object instanceof Accounts)) {
            return false;
        }
        Accounts other = (Accounts) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "timsoft.ehr.org.model.Accounts[ id=" + id + " ]";
    }

    public String getAccountsname() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
