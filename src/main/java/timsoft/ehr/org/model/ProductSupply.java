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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Olalekan
 */
@ManagedBean
@ViewScoped
@Entity
@Table(name = "product_supply")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductSupply.findAll", query = "SELECT p FROM ProductSupply p")})
public class ProductSupply implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Column(name = "quantity")
    private Integer quantity;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "purchase_price")
    private Double purchasePrice;
    @Column(name = "amount")
    private Double amount;
    @Column(name = "datecreated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datecreated;
    @JoinColumn(name = "supplierid", referencedColumnName = "id")
    @ManyToOne
    private Supplier supplierid;
    @JoinColumn(name = "libraryid", referencedColumnName = "id")
    @ManyToOne
    private Library libraryid;
    @JoinColumn(name = "createdby", referencedColumnName = "id")
    @ManyToOne
    private Accounts createdby;

    public ProductSupply() {
    }

    public ProductSupply(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Double purchasePrice) {
        this.purchasePrice = purchasePrice;
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

    public Supplier getSupplierid() {
        return supplierid;
    }

    public void setSupplierid(Supplier supplierid) {
        this.supplierid = supplierid;
    }

    public Library getLibraryid() {
        return libraryid;
    }

    public void setLibraryid(Library libraryid) {
        this.libraryid = libraryid;
    }

    public Accounts getCreatedby() {
        return createdby;
    }

    public void setCreatedby(Accounts createdby) {
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
        if (!(object instanceof ProductSupply)) {
            return false;
        }
        ProductSupply other = (ProductSupply) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "timsoft.ehr.org.model.ProductSupply[ id=" + id + " ]";
    }
    
}
